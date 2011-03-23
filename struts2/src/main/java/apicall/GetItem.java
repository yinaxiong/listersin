package ebaytool.apicall;

import com.mongodb.*;
import ebaytool.apicall.ApiCall;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class GetItem extends ApiCall implements Callable {
	
	public GetItem() throws Exception {
	}
	
	public String call() throws Exception {
		
		BasicDBObject query = new BasicDBObject();
		query.put("ext.deleted", new BasicDBObject("$exists", 0));
		//query.put("SellingStatus.ListingStatus", "Active");
		
		BasicDBObject field = new BasicDBObject();
		field.put("ItemID", 1);
		
		DBCursor cur = db.getCollection("items").find(query, field).limit(50);
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String itemid  = row.get("ItemID").toString();
			
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel", "ReturnAll");
			reqdbo.append("ItemID", itemid);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetItem");
			writelog("GI.req."+itemid+".xml", requestxml);
			
			Future<String> future = ecs18.submit(new ApiCallTask(0, requestxml, "GetItem"));
			
			String responsexml = future.get();
			writelog("GI.res."+itemid+".xml", responsexml);
			
			parseresponse(responsexml);
		}
		
		return "";
	}
	
	public BasicDBObject parseresponse(String responsexml) throws Exception {
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		BasicDBObject item = (BasicDBObject) responsedbo.get("Item");
		
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = new BasicDBObject();
		query.put("ItemID", item.getString("ItemID"));
		
		BasicDBObject upditem = new BasicDBObject();
		upditem.put("ConditionID", item.getString("ConditionID"));
		upditem.put("ProductListingDetails", item.get("ProductListingDetails"));
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", upditem);
		
		WriteResult result = coll.update(query, update);
		System.out.println("WriteResult: "+result);
		
		return responsedbo;
	}
}