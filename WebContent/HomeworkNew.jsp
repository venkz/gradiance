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
					
					<form accept-charset="UTF-8" role="form" method="post" action="HomeworkController">
                    <fieldset>
					<div class="panel-body">
						<%ArrayList<Question> arr_completed_ques=(ArrayList)request.getAttribute("quesNewList");%>
							<%for(Question ques:arr_completed_ques) {%>
								<div class="row table">
									<div class="col-md-9 "><b><%=ques.getSeqId()%>)&nbsp<%=ques.getText()%></b></div>
									<div class="form-group col-md-3">
			    		    			<input class="form-control" placeholder="answer choice" name="ans<%=ques.getSeqId()%>"  id="ans<%=ques.getSeqId()%>" type="text">
			    		    			<input class="form-control" name="ques<%=ques.getSeqId()%>"  id="ques<%=ques.getSeqId()%>" type="hidden" value="<%=ques.getQid()%>">
			    					</div>
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
				 	 <input type="hidden" name="count" id="count" value="<%=arr_completed_ques.size()%>">
				 	 <input type="hidden" name="token" id="token" value="${token }">
				 	 <input type="hidden" name="attemptId" id="attemptId" value="<%=arr_completed_ques.get(0).getAttemptId()%>">
				 	 <input class="btn btn-lg btn-success btn-block" type="submit" value="Submit Answers">
			    	</fieldset>
			      	</form>
					</div>
				</div>
		</div>
	</div>

</body>