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
								
									<div class="col-md-2 col-md-offset-0-5" >1)&nbsp<%=ques.getOption1()%></div>
									<div class="col-md-2 " >2)&nbsp<%=ques.getOption2()%></div>
									<div class="col-md-2 " >3)&nbsp<%=ques.getOption3()%></div>
									<div class="col-md-2 " >4)&nbsp<%=ques.getOption4()%></div>
								</div>
								<br><br>
								<div class="row">
									<div class="col-md-4 col-md-offset-0-5" ><b>Choice marked:</b>&nbsp&nbsp<%= ques.getAnswerChoosen() %></div>
									<%if(ques.getDueDatePassed() == 1) {%>
										<div class="col-md-4 col-md-offset-0-5" ><b>Correct choice:</b>&nbsp&nbsp<%= ques.getCorrectOption() %></div>
									<%}else{ %>
										<div class="col-md-4 col-md-offset-0-5" ><b>Correct choice:</b>&nbsp&nbspDue date not passed</div>
									<%} %>
								</div>
								<div class="row">
									<div class="col-md-4 col-md-offset-0-5" ><b>Explanation:</b>&nbsp&nbsp<%= ques.getExplanation() %></div>
									<%if(ques.getDueDatePassed() == 1) {%>
										<div class="col-md-4 col-md-offset-0-5" ><b>Complete Explanation:</b>&nbsp&nbsp<%= ques.getFullExplanation()%></div>
									<%}else{ %>
										<div class="col-md-4 col-md-offset-0-5" ><b>Complete Explanation:</b>&nbsp&nbspDue date not passed</div>
									<%} %>
								</div>
								<hr>
							<%}%>   
				 	 </div>
				</div>
			</div>
		</div>
	</div>

</body>