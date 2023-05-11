<html>
	<head>
		<meta name="layout" content="main">
		<title>Register</title>
	</head>
	<body>
		<div role="main">
			<div class="card modal-dialog allow-interact">
				<div class="card-header h3 mb-0">Register a BALSA Account</div>
				<div class="card-body">
					<div class="d-none">
						<g:hasErrors bean="${registerCommand}">
						<ul class="errors" role="alert">
							<g:eachError bean="${registerCommand}" var="error">
							<li><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
						</g:hasErrors>
					</div>
					
					<g:form controller="register" action="register" name="registerForm" autocomplete='off'>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="username">Username</label>
							<div class="col-8">
								<input name="username" class="form-control" required="true"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="email">Email Address</label>
							<div class="col-8">
								<input type="email" name="email" class="form-control" required="true"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="password">Password</label>
							<div class="col-8">
								<input type="password" name="password" class="form-control" required="true"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="password2">Password (again)</label>
							<div class="col-8">
								<input type="password" name="password2" class="form-control" required="true"/>
							</div>
						</div>
						<div class="text-secondary">Passwords requirements: at least eight characters, must contain at least one letter, one number, and one special character (!@#$%^&).</div>
					</g:form>
				</div>
				<div class="modal-footer">
					<div class="btn-group">
						<g:recaptcha form="registerForm" value="Register"/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
