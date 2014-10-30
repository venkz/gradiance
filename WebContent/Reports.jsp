<%@page import="ncsu.csc.db.beans.Notifications"%>
<%@page import="ncsu.csc.db.beans.Question"%>
<jsp:include page="Layout.jsp"></jsp:include>
<%@page import="java.util.*"%>
<body>


	<div class="container-fluid">
		<div class="row ">
			<div class="col-lg-12">

				<div class="panel panel-default">
					<div class="panel-heading center">
						<h3 class="panel-title text-center">
							<b>Reports for Gradiance</b>
						</h3>
					</div>

					<div class="panel-body">
						<form accept-charset="UTF-8" role="form" method="post" action="ReportsController">
							<fieldset>
							
							<div class="row">
								<div class="col-lg-4 form-group col-md-offset-1">
									<input class="form-control" type="text" id="homework" name="homework" placeholder="Enter homework name">
								</div>
								<div class="col-lg-4 form-group col-md-offset-1">
									<input class="form-control" type="text" id="student" name="student" placeholder="Enter username">
								</div>
							</div>
							
							<br>
								<input name="func" id="func" type="hidden" value="addTA">
								<input name="token" id="token" type="hidden" value="<%= request.getParameter("token")%>">
								<div class="col-lg-9 form-group col-md-offset-1"><input class="btn btn-lg btn-success btn-block" type="submit" value="Generate"></div>
								<br><br>
								<div class="col-lg-9 form-group col-md-offset-1"><input class="btn btn-lg btn-success btn-block" type="submit" value="Generate Course Report"></div>
							</fieldset>
						</form>
					</div>
				</div>

			</div>
		</div>
		
		<div class="row" >
			<% %>
		</div>
		
		
	</div>

</body>