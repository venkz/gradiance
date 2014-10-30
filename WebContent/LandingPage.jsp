<%@page import="ncsu.csc.db.beans.Enrollments"%>
<%@page import="java.util.*"%>

<jsp:include page="Layout.jsp"></jsp:include>

<body>
<div class="container-fluid">
  <div class="row-fluid">
  	
  	<div class="row">
  		<div class="col-lg-6">
      			   <div class="panel panel-default">
				  	<div class="panel-heading center">
				    	<h3 class="panel-title"> Available Courses </h3>
				 	</div>
				  		<div class="panel-body">
						<%ArrayList<Enrollments> course_arr=(ArrayList)request.getAttribute("CourseList");%>
							<%for(Enrollments e:course_arr){%>
								<div class="row">
									<div class="col-md-4"><a href="CourseController?token=<%= e.getToken()%>&isTA=<%=e.getIstaStr()%>&action=transition"><%=e.getToken()%></a></div>
									<div class="col-md-4"><%=e.getCoursename()%></div>
									<div class="col-md-4"><%=e.getIstaStr()%></div>
								</div>
							<%}%>   
				 	   </div>
					</div>
    	</div>
    	<div class="col-lg-6">
	    		<div class="panel panel-default">
				  	<div class="panel-heading center">
				    	<h3 class="panel-title">Add course</h3>
				 	</div>
				  		<div class="panel-body">
				    	<form accept-charset="UTF-8" role="form" method="post" action="UserContoller">
	                    	<fieldset>
					    	  	<div class="form-group">
					    		    <input class="form-control" placeholder="Course Token" name="token"  id="token" type="text" required="required">
					    		</div>
					    		<input name="func" id="func" type="hidden" value="addTA">
				    			<input class="btn btn-lg btn-success btn-block" type="submit" value="Add">
				    		</fieldset>
				      	</form>
				 	   </div>
					</div>
				</div>
			</div>		
    	</div>
  	</div>
  </div>


</body>



