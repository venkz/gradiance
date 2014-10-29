<%@page import="ncsu.csc.db.beans.HWRecords"%>
<jsp:include page="Layout.jsp"></jsp:include>
<%@page import="java.util.*"%>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading text-center">
						<h3 class="panel-title">View Past Submissions/Scores</h3>
					</div>
					<div class="panel-body">
						<%ArrayList<HWRecords> arr_completed_hw=(ArrayList)request.getAttribute("hwCompletedList");%>
						<div class="row text-center">
									<div class="col-md-4 "><h5><b>Homework Name</b></h5></div>
									<div class="col-md-4"><h5><b>Attempt Number</b></h5></div>
									<div class="col-md-4"><h5><b>Score</b></h5></div>
						</div><hr>
							<%for(HWRecords hw:arr_completed_hw){%>
								<div class="row table table-striped text-center">
									<div class="col-md-4 "><a href="HomeworkController?token=${token}&attemptId=<%=hw.getAttemptId()%>"><%=hw.getHwName()%></a></div>
									<div class="col-md-4 "><%=hw.getAttemptNumber()%></div>
									<div class="col-md-4 "><%=hw.getScore()%></div>
								</div>
							<%}%>   
				 	 </div>
					</div>
				</div>
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading text-center">
						<h3 class="panel-title">Attempt Homeworks</h3>
					</div>
					<div class="panel-body">
						<%ArrayList<HWRecords> arr_new_hw=(ArrayList)request.getAttribute("hwNewList");%>
						<div class="row text-center">
									<div class="col-md-4 "><h5><b>Homework Name</b></h5></div>
									<div class="col-md-4"><h5><b>Attempts Allowed</b></h5></div>
									<div class="col-md-4"><h5><b>Attempts Made</b></h5></div>
						</div><hr>
							<%for(HWRecords hw:arr_new_hw){%>
								<%if(hw.getMaxAttempts() - hw.getAttempsMade() > 0) {%>
									<div class="row table table-striped text-center">
										<div class="col-md-4 "><a href="HomeworkController?token=${token}&hwid=<%=hw.getHwId()%>"><%=hw.getHwName()%></a></div>
										<div class="col-md-4 "><%=(hw.getMaxAttempts() == 100)?"Unlimited":hw.getMaxAttempts()%></div>
										<div class="col-md-4 "><%=hw.getAttempsMade()%></div>
									</div>
								<%} else{ %>
									<div class="row table table-striped text-center">
										<div class="col-md-4 "><%=hw.getHwName()%></div>
										<div class="col-md-4 "><%=(hw.getMaxAttempts() == 100)?"Unlimited":hw.getMaxAttempts()%></div>
										<div class="col-md-4 "><%=hw.getAttempsMade()%></div>
									</div>
								<%} %>
							<%}%>  
					</div>
				</div>
			</div>
		</div>
	</div>
</body>