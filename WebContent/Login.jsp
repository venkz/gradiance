<jsp:include page="Layout.jsp"></jsp:include>
<body>

<div class="row">
		<div class="col-md-4 col-md-offset-4 text-center">
			<h2>Welcome to Gradiance System</h2><br><br>
		</div>
</div>

<div class="row">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading center">
			    	<h3 class="panel-title">Please sign in</h3>
			 	</div>
			  	<div class="panel-body">
			    	<form accept-charset="UTF-8" role="form" method="post" action="LoginController">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="Username" name="username"  id="username" type="text" required="required">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="Password" name="password" id="password" type="password" required="required">
			    		</div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>

</body>