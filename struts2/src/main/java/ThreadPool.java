package ebaytool;

import java.util.Map;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.concurrent.*;
import java.util.*;
import ebaytool.GetSellerList;
import ebaytool.AddItems;

public class ThreadPool {
	
	private ExecutorService pool;
	private Mongo m;
	private DB db;
	
	private String token = "AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB";
	
	public ThreadPool () {
		
		try {
			 m = new Mongo();
			 db = m.getDB("ebay");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		pool = Executors.newFixedThreadPool(18);
		
	}
	
    public static void main(String[] args) throws Exception {
		
		ThreadPool threadpool = new ThreadPool();
		
		threadpool.addItems();
		//threadpool.getSellerLists();
		
		threadpool.shutdown();
		return;
    }
	
	private void shutdown() {
		pool.shutdown();
	}
	
	private void addItems() throws Exception {
		
		BasicDBObject requestdbo = new BasicDBObject();
		requestdbo.append("WarningLevel", "High");
		requestdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		
		BasicDBObject query = new BasicDBObject();
		query.put("SellingStatus.ListingStatus", "Completed");
		query.put("UserID", "testuser_hal");
		
		BasicDBObject field = new BasicDBObject();
		field.put("Description", 1);
		field.put("Title", 1);
		
		DBCollection coll = db.getCollection("items");
		
		int messageid = 0;
		List<DBObject> ldbo = new ArrayList<DBObject>();
		DBCursor cur = coll.find(query, null).limit(1);
		while (cur.hasNext()) {
			messageid++;
			DBObject item = cur.next();
			item.removeField("_id");
			ldbo.add(new BasicDBObject("MessageID", messageid).append("Item", item));
		}		
		requestdbo.append("AddItemRequestContainer", ldbo);
		
		
		JSONObject jso = JSONObject.fromObject(requestdbo.toString());
		jso.getJSONArray("AddItemRequestContainer").setExpandElements(true);
		
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("AddItemsRequest");
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String requestxml = xmls.write(jso);
		
		Future<BasicDBObject> future = pool.submit(new AddItems(requestxml));
		
		return;
	}
	
	private void getSellerLists() throws Exception {
		
		DBCollection coll = db.getCollection("users");
		DBObject user = coll.findOne();
		
		Map userids = ((BasicDBObject) user.get("userids")).toMap();
		for (Object userid : userids.keySet()) {
			JSONObject json = JSONObject.fromObject(userids.get(userid).toString());
			String token = json.get("ebaytkn").toString();
			getSellerList(token);
		}
		
		return;
	}
	
	private void getSellerList(String token) throws Exception {
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("StartTimeFrom", "2010-06-01 00:00:00");
		dbobject.put("StartTimeTo",   "2010-08-01 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage", 10).append("PageNumber", 1));
		dbobject.put("Sort", "1");
		
		Future<BasicDBObject> future = pool.submit(new GetSellerList(dbobject));
		BasicDBObject result = future.get();
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		System.out.println("TotalNumberOfPages : "+pages);
		
		for (int i=2; i<=pages; i++) {
			((BasicDBObject) dbobject.get("Pagination")).put("PageNumber", i);
			pool.submit(new GetSellerList(dbobject));
		}
		
		return;
	}
	
}
