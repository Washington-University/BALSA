<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div role="main">
			<div class="card modal-dialog allow-interact">
				<div class="card-header h3 mb-0">Login to BALSA</div>
				<div class="card-body">
					<g:form controller="j_spring_security_check" name="loginForm" autocomplete='off' class="ajaxForm" data-success="isLoggedIn(data)">
						<div id="loginError" class="form-group has-error" style="text-align:center;vertical-align:middle;display:none;">
							<label id="loginErrorLabel" class="control-label p-0">
								Sorry, we were not able to find a user with that username and password. This may be due to BALSA's recent transition to a new authentication system.
								For continued access, <a onclick="sendPasswordResetEmail()">click here</a> to receive an email link to reset your password.
							</label>
						</div>
						<div class="mr-auto text-success" id="loginEmailSent" style="display:none;">A password reset email was sent - check your mail!</div>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="j_username">Username</label>
							<div class="col-8">
								<input id="j_username" name="j_username" class="form-control" placeholder="Username"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="j_password">Password</label>
							<div class="col-8">
								<input id="j_password" name="j_password" class="form-control" placeholder="Password" type="password"/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-4">
								<div class="form-check">
									<input class="form-check-input" id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" checked>
									<label class="form-check-label" for="_spring_security_remember_me">Remember me</label>
								</div>
							</div>
							<div class="col-8 d-flex">
								<g:link class="mr-auto" action="forgotUsername" controller="register">Forgotten Username?</g:link>
								<g:link action="forgotPassword" controller="register">Forgotten Password?</g:link>
							</div>
						</div>
					</g:form>
				</div>
				<div class="modal-footer">
					<div class="btn-group">
						<button type="submit" form="loginForm" class="btn btn-primary">Login</button>
						<g:link  class="btn btn-light" action="register" controller="register">Register</g:link>
						<button type="button" class="btn btn-light" data-toggle="modal" data-target="#loginModal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>