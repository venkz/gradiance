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
						<%ArrayList<HWRecords> arr_hw=(ArrayList)request.getAttribute("hwList");%>
						<div class="row text-center">
									<div class="col-md-4 "><h5>Homework Name</h5></div>
									<div class="col-md-4"><h4>Attempt Number</h4></div>
									<div class="col-md-4"><h4>Score</h4></div>
						</div><hr>
							<%for(HWRecords hw:arr_hw){%>
								<div class="row table table-striped text-center">
									<div class="col-md-4 "><a href="HomeworkController?attemptId=<%=hw.getAttemptId()%>"><%=hw.getHwName()%></a></div>
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
					<div class="panel-body"></div>
				</div>
			</div>
		</div>
	</div>
</body>