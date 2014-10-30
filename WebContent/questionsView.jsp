<jsp:include page="Layout.jsp"></jsp:include>
<%@page import="ncsu.csc.db.beans.Question"%>
<%@page import="java.util.*"%>
<%@page import="ncsu.csc.db.beans.HWRecords"%>
<%@page import="java.text.SimpleDateFormat"%>

<body>
<div class="container-fluid">
  <div class="row-fluid">
  	
  	<div class="row">
  		<div class="col-lg-6">
      			   <div class="panel panel-default">
				  	<div class="panel-heading center">
				    	<h3 class="panel-title"> Questions </h3>
				 	</div>
				  		<div class="panel-body">
						<%ArrayList<Question> list_qts=(ArrayList)request.getAttribute("searchResults");%>
							<%for(Question lq:list_qts){%>
								<div class="row">
								<form accept-charset="UTF-8" role="form" method="post" action="HomeworkController">
									<fieldset>
										<div class="form-group">
											<input name="qid" id="qid" type="hidden" value="<%=lq.getQid()%>">
											<input name="hwtoken" id="hwtoken" type="hidden" value="<%=request.getAttribute("hwtoken")%>">
											<input name="searchtopic" id="searchtopic" type="hidden" value="<%=request.getAttribute("searchtopic")%>">
											<input name="coursetoken" id="coursetoken" type="hidden" value="<%=request.getAttribute("coursetoken")%>">
											
											<div class="col-md-10"><a href="ReportsController?qid=<%= lq.getQid()%>&hwtoken=<%=request.getAttribute("hwtoken")%>&coursetoken=<%=request.getAttribute("coursetoken")%>"><%=lq.getText()%></a></div>
											<%if(request.getAttribute("qts_action").toString().equalsIgnoreCase("add")){%>
												<input name="hwaction" id="qaction" type="hidden" value="add">
												<div class="col-md-2"><input class="btn btn-default btn-success btn-block" type="submit" value="Add"></div>
											<%} else if(request.getAttribute("qts_action").toString().equalsIgnoreCase("view")){%>
														
											<%} else if(request.getAttribute("qts_action").toString().equalsIgnoreCase("delete")){%>
												<input name="hwaction" id="qaction" type="hidden" value="delete">
												<div class="col-md-2"><input class="btn btn-danger btn-error btn-block" type="submit" value="Delete"></div>
												
											<%}%>
																
										</div>
									</fieldset>
								</form>
								</div>
								<br>
							<%}%>
							<br><br>
						
						<%if(!request.getAttribute("qts_action").toString().equalsIgnoreCase("view")){%>
							<form accept-charset="UTF-8" role="form" method="post" action="HomeworkController">
								<input name="hwaction" id="hwaction" type="hidden" value="finish">
								<input name="coursetoken" id="coursetoken" type="hidden" value="<%=request.getAttribute("coursetoken")%>">
								<input class="btn btn-default btn-success btn-block" type="submit" value="Finish"> 
							</form>
						<%}else{%>
							<form accept-charset="UTF-8" role="form" method="post" action="HomeworkController">
								<input name="hwaction" id="hwaction" type="hidden" value="finish">
								<%if(!request.getAttribute("isTA").toString().equalsIgnoreCase("TA")){%>
									<input name="coursetoken" id="coursetoken" type="hidden" value="<%=request.getAttribute("coursetoken")%>">
									<input name="isTA" id="isTA" type="hidden" value="<%=request.getAttribute("isTA")%>">
									<input class="btn btn-default btn-success btn-block" type="submit" value="Done">
								<%}else{ %>
									<input name="coursetoken" id="coursetoken" type="hidden" value="<%=request.getAttribute("token")%>">
									<input name="isTA" id="isTA" type="hidden" value="<%=request.getAttribute("isTA")%>">
									<input class="btn btn-default btn-success btn-block" onclick=window.history.back() value="Done">
								<%} %>
								 
							</form>
						<%} %>
						  
				 	   </div>
					</div>
			</div>
			<%if(request.getAttribute("qts_action").toString().equalsIgnoreCase("view")){%>
			<div class="col-lg-6">
					<div class="panel panel-default">
				  	<div class="panel-heading center">
				  	<% HWRecords hwr=(HWRecords)request.getAttribute("hwParameters");%>
				    	<h3 class="panel-title">HomeWork Parameters</h3>
				   	
				 	</div>
				  		<div class="panel-body">
				    	<form accept-charset="UTF-8" role="form">
				    	<div class="row">
				    			<fieldset>
					    	  	<div class="form-group">
									<div class="col-lg-3"><label></label>Homework Name</div>
					    	  		<div class="col-lg-9"><input class="form-control" placeholder="Homework Name" name="hwname"  id="hwname" type="text" value="<%=hwr.getHwName() %>" disabled="disabled"></div>
					    	  		<div class="col-lg-3"><label></label>Start Date</div>
					    	  		<input name="hwnameHidden" id="hwnameHidden" type="hidden" value="<%=hwr.getHwName() %>">
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Start Date" name="startdate"  id="startdate" data-provide="datepicker" value="<%=new SimpleDateFormat("MM/dd/yyyy").format(hwr.getStartdate()) %>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>End Date</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="End Date" name="enddate"  id="enddate" data-provide="datepicker" value="<%=new SimpleDateFormat("MM/dd/yyyy").format(hwr.getEnddate()) %>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>Number of Attempts</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Number of Attempts(Max 100)" name="numattempts"  id="numattempts" type="text"  value="<%=hwr.getNumattempts()%>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>Topics</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Topics" name="topics"  id="topics" type="text" value="<%=hwr.getTopics() %>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>Min Difficulty</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Min Difficulty Range(1-6)" name="mindiffrange"  id="mindiffrange" type="text" value="<%=hwr.getMindiffrange() %>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>Max Difficulty</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Max Difficulty Range(1-6)" name="maxdiffrange"  id="maxdiffrange" type="text" value="<%=hwr.getMaxdiffrange() %>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>Latest/Max/Avg</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="1-latest Attempt, 2-Max Score, 3-Avg Score" name="scorescheme"  id="scorescheme" type="text" value="<%=hwr.getScorescheme() %>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>No.of Questions</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Number of Questions" name="numquestions"  id="numquestions" type="text" value="<%=hwr.getNumquestions()%>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>Correct points</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Correct answer points" name="correctpoints"  id="correctpoints" type="text" value="<%=hwr.getCorrectpoints() %>" disabled="disabled"></div>
					    		   	<div class="col-lg-3"><label></label>InCorrect points</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="InCorrect answer points" name="incorrectpoints"  id="incorrectpoints" type="text" value="<%=hwr.getIncorrectpoints() %>" disabled="disabled"></div>
					    		    <div class="col-lg-3"><label></label>Randomization seed</div>
					    		    <div class="col-lg-9"><input class="form-control" placeholder="Randomization seed" name="randomseed"  id="randomseed" type="text" value="<%=hwr.getRandomseed() %>" disabled="disabled"></div>
									<input name="cors_token" id="cors_token" type="hidden" value="<%=request.getAttribute("coursetoken")%>">
					    		    <input name="hwaction" id="hwaction" type="hidden" value="updateHw">
					    		</div>
				    	</fieldset>
						</div>
				      	</form>
				 	   </div>
				
			
			</div>
    	</div>
    	<%} %>
    </div>
 </div>
 </div>
</body>
</html>