<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/bootstrap-theme.css"
	type="text/css" media="screen" title="no title" charset="utf-8" />
<link rel="stylesheet" href="css/bootstrap.css"
	type="text/css" media="screen" title="no title" charset="utf-8" />
	
<title>Gradiance CSC540</title>

</head>
<script src="js/bootstrap.min.js" type="text/javascript"
		charset="utf-8"></script>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
	    
	    <div class="navbar-inner" >
		    <div class="col-lg-6">
		    <% if(request.getRequestURI().contains("Login")) {%>
		    </div>
		    <%} else {%>
		    	<%request.getSession(false);%>
		    		<h4 style="color:white"><b> Welcome <%=session.getAttribute("Session_UserName")%> </b></h4>
		    </div>
	     	<div class="col-lg-6">
	     		<%if(Integer.parseInt(session.getAttribute("Session_UserRole").toString())==0){ %>
	     			<h4 class="text-right" style="color:white" ><b> Logged in as Student </b></h4>
	     		<% }else{%>
	     			<h4 class="text-right" style="color:white" ><b> Logged in as Professor </b></h4>
	     		<%} %>
	     	</div>
		    <%} %>
	    </div>
	</div>
	<br><br><br><br>
	
</body>
</html>
