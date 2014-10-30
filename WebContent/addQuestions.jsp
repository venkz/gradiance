<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="Layout.jsp"></jsp:include>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	
<div class="row">
<div class="col-lg-3"></div>
	<div class="col-lg-6">
			<div class="panel panel-default col-md-offset-1">
				<div class="panel-heading center">
					<h3 class="panel-title">Select Topic</h3>
				</div>
				<div class="panel-body">
					<form accept-charset="UTF-8" role="form" method="post" action="HomeworkController">
	                    <fieldset>
				    	  	<div class="form-group">
				    	  		<input class="form-control" placeholder="Search Topic" name="searchtopic"  id="searchtopic" type="text" required="required">
								<input name="hwaction" id="hwaction" type="hidden" value="searchTopic">
								<input name="hwtoken" id="hwtoken" type="hidden" value="<%=request.getAttribute("hwtoken")%>">
								<input name="coursetoken" id="coursetoken" type="hidden" value="<%=request.getAttribute("coursetoken")%>">
							</div>
							<input class="btn btn-lg btn-success btn-block" type="submit" value="Search">
				    	</fieldset>
					</form>
				</div>
			</div>
	</div>
<div class="col-lg-3"></div>
</div>

</body>
</html>