<html>
<head>
 <?php 
$a = setcookie("UserCookie", $username, time()+3600); 
if ($a == true) print 'true';
if ($a == false) print 'flase';
if ($a == null) print 'null';
?>
</head>
