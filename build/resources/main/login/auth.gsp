<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div role="main">
			<div class="card modal-dialog allow-interact">
				<div class="card-header h3 mb-0">Login to BALSA</div>
				<div class="card-body">
					<g:form controller="login" action="authenticate" name="loginForm" autocomplete='off'>
						<div id="loginError" class="form-group has-error" style="text-align:center;vertical-align:middle;display:none;">
							<label id="loginErrorLabel" class="control-label p-0"></label>
						</div>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="username">Username</label>
							<div class="col-8">
								<input name="username" class="form-control" placeholder="Username"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="password">Password</label>
							<div class="col-8">
								<input name="password" class="form-control" placeholder="Password" type="password"/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-4">
								<div class="form-check">
									<input class="form-check-input" name="remember-me" type="checkbox" checked>
									<label class="form-check-label" for="remember_me">Remember me</label>
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