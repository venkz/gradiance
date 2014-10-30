<%@page import="ncsu.csc.db.beans.Notifications"%>
<%@page import="ncsu.csc.db.beans.Question"%>
<jsp:include page="Layout.jsp"></jsp:include>
<%@page import="java.util.*"%>
<body>


	<div class="container-fluid">
		<div class="row col-md-offset-2">
			<div class="col-lg-10">
			
			
			<div class="panel-body">
						<%ArrayList<Notifications> notifications=(ArrayList)request.getAttribute("notifications");%>
						<div class="row text-center">
									<div class="col-md-4 "><h5><b>Notification Information</b></h5></div>
									<div class="col-md-4"><h5><b>Date Logged</b></h5></div>
						</div><hr>
							<%for(Notifications nf:notifications){%>
								<div class="row table table-striped text-center">
									<div class="col-md-4 "><%=nf.getText()%></div>
									<div class="col-md-4 "><%=nf.getNotificationDate()%></div>
								</div>
							<%}%>   
							<form accept-charset="UTF-8" role="form" method="post" action="LoginController">
	                    		<fieldset>
				    	  			<input class="form-control" name="deleteNotif"  id="deleteNotif" type="hidden" value="deleteNotif">
				    				<div class="col-md-4 col-md-offset-4"><input class="btn btn-success" type="submit" value="Clear and go Back"></div>
				    			</fieldset>
			     			</form>
							
			</div>
			</div>
		</div>
	</div>

</body>