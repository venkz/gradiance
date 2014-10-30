<%@page import="ncsu.csc.db.beans.HWRecords"%>
<jsp:include page="Layout.jsp"></jsp:include>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<html>
<body>
<div class="container-fluid">
  <div class="row-fluid">
  	
  	<div class="row">
  		<div class="col-lg-6">
      			   <div class="panel panel-default">
				  	<div class="panel-heading center">
				    	<h3 class="panel-title"> Assigned HomeWorks </h3>
				 	</div>
				  		<div class="panel-body">
						<%ArrayList<HWRecords> hw_assign=(ArrayList)request.getAttribute("hwAssignedList");%>
							<%for(HWRecords hw:hw_assign){%>
								<div class="row">
									<div class="col-md-4"><%=hw.getHwName()%></div>
									<div class="col-md-1"><a href="CourseController?token=<%=request.getAttribute("token")%>&hwtoken=<%= hw.getHwName()%>&action=View&isTA=<%=request.getAttribute("isTA")%>">View</a></div>
								</div>
							<%}%>   
				 	   </div>
					</div>
					
					<br><br><br>
								<a href="Reports.jsp?token=${token}" class="btn btn-lg btn-success btn-block" >Reports</a>
		
    	</div>
    	
    			</div>
			</div>		
    	</div>
  	</div>
</body>
</html>