<jsp:include page="Layout.jsp"></jsp:include>
<body>

	<div class="row">
		<div class="col-lg-6">
			<div class="col-md-6 col-md-offset-2">
				<div class="panel panel-default">
					<div class="panel-heading center">
						<h3 class="panel-title text-center">Create User</h3>
					</div>
					<div class="panel-body">
						<form accept-charset="UTF-8" role="form" method="post"
							action="UserContoller">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Username"
										name="username" id="username" type="text" required="required">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" id="password" type="password"
										required="required">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="First Name"
										name="fname" id="fname" type="text" required="required">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Last Name"
										name="lname" id="lname" type="text" required="required">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Email" name="email"
										id="email" type="email" required="required">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="0 - Undergraduate; 1 - Graduate"
										name="degree" id="degree" type="text" required="required">
								</div>
									<input name="func" id="func" type="hidden" value="addUser">
								<input class="btn btn-lg btn-success btn-block" type="submit"
									value="create">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			<br> <br>
			<div class="col-md-6 col-md-offset-2">
				<div class="panel panel-default">
					<div class="panel-heading center">
						<h3 class="panel-title text-center">Add User as TA</h3>
					</div>
					<div class="panel-body">
						<form accept-charset="UTF-8" role="form" method="post"
							action="UserContoller">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Username"
										name="username" id="username" type="text" required="required">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Course Token" name="token"
										id="token" type="text" required="required">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="0 - student;  1 - teaching assistant" name="ista"
										id="ista" type="text" required="required">
								</div>
								<input name="func" id="func" type="hidden" value="addTA">
								<input class="btn btn-lg btn-success btn-block" type="submit"
									value="add">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="row">
				<div class="col-md-3"><a href="#" class="btn btn-lg btn-info">sample<br>test</a></div>
				<div class="col-md-3">item 2</div>
				<div class="col-md-3">item 3</div>
				<div class="col-md-3">item 4</div>
				<div class="col-md-3">item 5</div>
				<div class="col-md-3">item 6</div>
				<div class="col-md-3">item 7</div>
			</div>
		</div>
	</div>

</body>
</html>