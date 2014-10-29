<%@page import="ncsu.csc.db.beans.Question"%>
<jsp:include page="Layout.jsp"></jsp:include>
<%@page import="java.util.*"%>
<body>


<div class="container-fluid">
		<div class="row col-md-offset-2">
			<div class="col-lg-10">
				<div class="panel panel-default">
					<div class="panel-heading text-center">
						<h3 class="panel-title">Homework ${hwId} Questions</h3>
					</div>
					
					<div class="panel-body">
						<%ArrayList<Question> arr_completed_ques=(ArrayList)request.getAttribute("quesCompletedList");%>
							<%for(Question ques:arr_completed_ques) {%>
								<div class="row table">
									<div class="col-md-9 "><b><%=ques.getSeqId()%>)&nbsp<%=ques.getText()%></b></div>
								</div>
								<div class="row">
									<div class="col-md-4 col-md-offset-0-5" >1)&nbsp<%=ques.getOption1()%></div>
								</div>
								<div class="row">
									<div class="col-md-4 col-md-offset-0-5" >2)&nbsp<%=ques.getOption2()%></div>
								</div>
								<div class="row">
									<div class="col-md-4 col-md-offset-0-5" >3)&nbsp<%=ques.getOption3()%></div>
								</div>
								<div class="row">
									<div class="col-md-4 col-md-offset-0-5" >4)&nbsp<%=ques.getOption4()%></div>
								</div>
								<hr>
							<%}%>   
				 	 </div>
				</div>
			</div>
		</div>
	</div>

</body>