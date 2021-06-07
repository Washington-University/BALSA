<html>
	<head>
		<meta name="layout" content="main">
		<title>Reset Password</title>
	</head>
	<body>
		<div role="main">
			<div class="card modal-dialog allow-interact">
				<div class="card-header h3 mb-0">Reset Password</div>
				<div class="card-body">
					<div class="text-secondary mb-4">Passwords requirements: at least eight characters, must contain at least one letter, one number, and one special character (!@#$%^&).</div>
					<g:form controller="register" action="resetPassword" name="resetPasswordForm" autocomplete='off'>
						<g:hiddenField name='t' value='${token}'/>
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
					</g:form>
				</div>
				<div class="modal-footer">
					<div class="mr-auto text-danger"><span <g:if test="${!resetPasswordCommand?.hasErrors()}">class="d-none"</g:if>>Your password does not meet requirements.</span></div>
					<div class="btn-group">
						<button type="submit" form="resetPasswordForm" class="btn btn-primary">Reset Password</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
