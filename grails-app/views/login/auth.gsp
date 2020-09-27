<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div style="width:600px;margin:0 auto;">
			<g:form class="form-horizontal" controller="j_spring_security_check" id="loginForm" name="loginForm" type='login' autocomplete='off' focus='username'>
				<div id="loginError" class="form-group has-error" style="text-align:center;vertical-align:middle;display:none;">
					<label id="loginErrorLabel" class="control-label" style="padding:0"></label>
				</div>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<input name="j_username" class="form-control" id="username" placeholder="Username"/>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input name="j_password" class="form-control" id="password" placeholder="Password" type="password"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label>
								<input name="_spring_security_remember_me" id="remember_me" type="checkbox" checked> Remember me
							</label>
						</div>
					</div>
				</div>
				<div style="text-align:right;padding-top:15px;border-top:1px solid #e5e5e5;">
					<div class="pull-left" style="line-height: 2.3em;">
						<g:link action="forgotPassword" controller="register">Forgot Password?</g:link>
						<g:link action="forgotUsername" controller="register">Forgot Username?</g:link>
					</div>
					<div class="btn-group">
						<button type="submit" class="btn btn-primary" style="width:87px">Login</button>
						<g:link class="btn btn-default" controller="register" action="register" style="width:87px">Register</g:link>
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>