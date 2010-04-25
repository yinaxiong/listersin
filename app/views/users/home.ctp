imp:<?
foreach ($accounts as $accountid => $row) {
	print '<a href="/users/import/'.$row['ebayuserid'].'" target="_blank">'
		. $row['ebayuserid']. '</a> ';
}
?><br>
upd:<?
foreach ($accounts as $accountid => $row) {
	print '<a href="/users/getsellerlist/'.$accountid.'" target="_blank">'
		. $row['ebayuserid']. '</a> ';
}
?>
<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc">regacc</a>

<br><br>

<div>
<input type="button" value="全て" onclick="chkall();">
<input type="button" value="リセット" onclick="unchkall();">

<input type="button" value="出品"   onclick="submititems();">
<input type="button" value="コピー" onclick="copyitems();">
<input type="button" value="更新"   onclick="update();">
<input type="button" value="削除"   onclick="delete();">

</div>

<div id="paging"></div>

<form id="filter">
<input type="hidden" name="offset" value="0">
<input type="hidden" name="limit" value="30">
<table id="items" class="items">
<tr id="r0">
<th>&nbsp;</th>
<th>ID</th>
<th>画像</th>
<th>タイトル</th>
<th>eBayアカウント</th>
<th nowrap>eBay商品ID</th>
<th nowrap>終了日</th>
<th nowrap>開始価格</th>
</tr>

<tr>
<td></td>
<td><input type="text" name="itemid" size="4"></td>
<td></td>
<td><input type="text" name="title" size="30"></td>
<td>
<select name="accountid" onchange="filter();">
<option value=""></option>
<?
foreach ($accounts as $accountid => $row) {
	echo '<option value="'.$accountid.'">'.$row['ebayuserid'].'</option>';
}	
?>
</select>
</td>
<td><input type="text" name="itemid" size="6"></td>
<td></td>
<td></td>
</tr>
<tbody id="rowtemplate" class="itemrow">
<tr class="row1">
	<td><input type="checkbox" name="item[]"></td>
	<td class="itemid"></td>
	<td><img class="galleryurl" height="20"></td>
	<td><a href="" class="title"></a></td>
	<td class="ebayuserid"></td>
	<td><a href="" class="ebayitemid" target="_blank"></a></td>
	<td class="endtime"></td>
	<td class="startprice"></td>
</tr>
<tr class="row2">
	<td colspan="8">
	<div class="detail">
	
	<table class="detail">
	<tr><td width="100">タイトル</td><td class="title"></td></tr>
	<tr><td>カテゴリ</td><td class="categoryname"></td></tr>
	<tr><td>説明</td><td class="description"></td></tr>
	<tr><td>画像</td><td><img class="galleryurl"></td></tr>
	<tr><td>開始価格</td><td class="startprice"></td></tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" class="edit" value="編集する">
		<input type="button" class="update" value="更新する">
		<input type="button" class="cancel" value="キャンセル">
		</td>
	</tr>
	</table>
	
	</div>
	</td>
</tr>
</tbody>
</table>
</form>

<div style="width=300px;" id="debug"></div>

<div id="editform">
<input class="title" name="title" type="text" size="60">
<input class="startprice" name="startprice" type="text" size="10">
<textarea class="description" name="description" cols="90" rows="5"></textarea>
<div class="categoryname">
category select form
</div>
</div>
