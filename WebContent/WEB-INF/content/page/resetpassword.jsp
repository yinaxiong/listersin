<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset password - ListersIn</title>
<link rel="stylesheet" type="text/css" href="/css/index.css">
<link rel="shortcut icon" href="/img/favicon.png">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-32099440-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</head>
<body>

<div id="container-white">
	
  <div id="header" class="clearfix">
	  <div id="logo">
	    <a href="/"><img src="/img/logo.png" alt="ListersIn" /></a>
	  </div>
    <div id="header_right">
      <a href="/?request_locale=en">English</a> | <a href="/?request_locale=ja">日本語</a>
    </div>
  </div>
  
  <div id="content">
		
	  <div id="resetpassworddiv">
		  
		  <div style="margin-bottom:10px; font-weight:bold;">
        <s:text name="ResetPassword"/>
		  </div>
		  <div id="resetpasswordmessage"></div>
		  
		  <form method="post" action="">
		    <s:text name="Email"/>
        <div style="font-weight:bold;">${user.email}</div>
		    <input type="hidden" name="email" value="${user.email}"><br />
		    
		    <s:text name="Password"/><br />
		    <input type="text" name="password" size="40"><br /><br />
		    
		    <s:text name="ConfirmPassword"/><br />
		    <input type="text" name="password2" size="40"><br /><br />
		    
		    <button><s:text name="ResetPassword"/></button>
		  </form>
		  
	  </div><!-- #resetpassworddiv -->
		
	</div><!-- #content -->
	
	<div id="footer">
		ListersIn - <s:text name="eBayListingSoftware"/>
	</div>
	
</div><!-- #container -->

</body>
</html>
