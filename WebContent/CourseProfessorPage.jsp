<%@page import="ncsu.csc.db.beans.HWRecords"%>
<jsp:include page="Layout.jsp"></jsp:include>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Course Page</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#startdate" ).datepicker();
  });
  $(function() {
	    $( "#enddate" ).datepicker();
  });
  </script>

</head>
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
									<div class="col-md-1"><a href="CourseController?coursetoken=<%=request.getAttribute("coursetoken")%>&hwtoken=<%= hw.getHwName()%>&action=Edit">Edit</a></div>
									<div class="col-md-1"><a href="CourseController?coursetoken=<%=request.getAttribute("coursetoken")%>&hwtoken=<%= hw.getHwName()%>&action=View">View</a></div>
									<div class="col-md-3"><a href="CourseController?coursetoken=<%=request.getAttribute("coursetoken")%>&hwtoken=<%= hw.getHwName()%>&action=RQ">Remove Questions</a></div>
									<div class="col-md-3"><a href="CourseController?coursetoken=<%=request.getAttribute("coursetoken")%>&hwtoken=<%= hw.getHwName()%>&action=AQ">Add Questions</a></div>
					
								</div>
							<%}%>   
				 	   </div>
					</div>
		
		<br><br><br>
								<a href="Reports.jsp?token=${coursetoken}" class="btn btn-lg btn-success btn-block" >Reports</a>
		
    	</div>
    	
    	<div class="col-lg-6">
	    		<div class="panel panel-default">
				  	<div class="panel-heading center">
				  	<% HWRecords hwr=(HWRecords)request.getAttribute("hwParameters");%>
				  	<%if(hwr==null){ %>
				    	<h3 class="panel-title">Add HomeWork</h3>
				    <%}else{%>
				    	<h3 class="panel-title">Update HomeWork</h3>
				   	<%}%>
				   	
				 	</div>
				  		<div class="panel-body">
				    	<form accept-charset="UTF-8" role="form" method="post" action="HomeworkController">
	                    	
	                    	
	                    	<%if(hwr==null){ %>
	                    	
	                    	<fieldset>
					    	  	<div class="form-group">
					    	  		<input class="form-control" placeholder="Homework Name" name="hwname"  id="hwname" type="text" required="required">
					    		    <input class="form-control" placeholder="Start Date" name="startdate"  id="startdate" data-provide="datepicker" required="required">
					    		    <input class="form-control" placeholder="End Date" name="enddate"  id="enddate" data-provide="datepicker" required="required">
					    		    <input class="form-control" placeholder="Number of Attempts(Max 100)" name="numattempts"  id="numattempts" type="text" required="required">
					    		    <input class="form-control" placeholder="Topics" name="topics"  id="topics" type="text" required="required">
					    		    <input class="form-control" placeholder="Min Difficulty Range(1-6)" name="mindiffrange"  id="mindiffrange" type="text" required="required">
					    		    <input class="form-control" placeholder="Max Difficulty Range(1-6)" name="maxdiffrange"  id="maxdiffrange" type="text" required="required">
					    		    <input class="form-control" placeholder="1-latest Attempt, 2-Max Score, 3-Avg Score" name="scorescheme"  id="scorescheme" type="text" required="required">
					    		    <input class="form-control" placeholder="Number of Questions" name="numquestions"  id="numquestions" type="text" required="required">
					    		    <input class="form-control" placeholder="Correct answer points" name="correctpoints"  id="correctpoints" type="text" required="required">
					    		    <input class="form-control" placeholder="InCorrect answer points" name="incorrectpoints"  id="incorrectpoints" type="text" required="required">
					    		    <input class="form-control" placeholder="Randomization seed" name="randomseed"  id="randomseed" type="text">
					    		    <input name="cors_token" id="cors_token" type="hidden" value=<%=request.getAttribute("coursetoken")%>>
					    			<input name="hwaction" id="hwaction" type="hidden" value="addHw">
					    		</div>
				    			<input class="btn btn-lg btn-success btn-block" type="submit" value="Add HomeWork">
				    		</fieldset>
				    		<%}else{%>
				    			<fieldset>
					    	  	<div class="form-group">
					    	  		<input class="form-control" placeholder="Homework Name" name="hwname"  id="hwname" type="text" value="<%=hwr.getHwName() %>" disabled="disabled">
					    	  		<input name="hwnameHidden" id="hwnameHidden" type="hidden" value="<%=hwr.getHwName() %>">
					    		    <input class="form-control" placeholder="Start Date" name="startdate"  id="startdate" data-provide="datepicker" value="<%=new SimpleDateFormat("MM/dd/yyyy").format(hwr.getStartdate()) %>" required="required">
					    		    <input class="form-control" placeholder="End Date" name="enddate"  id="enddate" data-provide="datepicker" value="<%=new SimpleDateFormat("MM/dd/yyyy").format(hwr.getEnddate()) %>" required="required">
					    		    <input class="form-control" placeholder="Number of Attempts(Max 100)" name="numattempts"  id="numattempts" type="text"  value="<%=hwr.getNumattempts()%>" required="required">
					    		    <input class="form-control" placeholder="Topics" name="topics"  id="topics" type="text" value="<%=hwr.getTopics() %>" required="required">
					    		    <input class="form-control" placeholder="Min Difficulty Range(1-6)" name="mindiffrange"  id="mindiffrange" type="text" value="<%=hwr.getMindiffrange() %>" required="required">
					    		    <input class="form-control" placeholder="Max Difficulty Range(1-6)" name="maxdiffrange"  id="maxdiffrange" type="text" value="<%=hwr.getMaxdiffrange() %>" required="required">
					    		    <input class="form-control" placeholder="1-latest Attempt, 2-Max Score, 3-Avg Score" name="scorescheme"  id="scorescheme" type="text" value="<%=hwr.getScorescheme() %>" required="required">
					    		    <input class="form-control" placeholder="Number of Questions" name="numquestions"  id="numquestions" type="text" value="<%=hwr.getNumquestions()%>" required="required">
					    		    <input class="form-control" placeholder="Correct answer points" name="correctpoints"  id="correctpoints" type="text" value="<%=hwr.getCorrectpoints() %>" required="required">
					    		    <input class="form-control" placeholder="InCorrect answer points" name="incorrectpoints"  id="incorrectpoints" type="text" value="<%=hwr.getIncorrectpoints() %>" required="required">
					    		    <input class="form-control" placeholder="Randomization seed" name="randomseed"  id="randomseed" type="text" value="<%=hwr.getRandomseed() %>">
					    			<input name="cors_token" id="cors_token" type="hidden" value="<%=request.getAttribute("coursetoken")%>">
					    		    <input name="hwaction" id="hwaction" type="hidden" value="updateHw">
					    		</div>
				    			<input class="btn btn-lg btn-success btn-block" type="submit" value="Update HomeWork">
				    		</fieldset>
				    		<%}%>
				    		
				    		
				    		
				      	</form>
				 	   </div>
					</div>
				</div>
			</div>		
    	</div>
  	</div>
  </div>
</body>
</html>