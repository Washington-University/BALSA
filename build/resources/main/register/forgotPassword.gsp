<html>
	<head>
		<meta name="layout" content="main">
		<title>Forgotten Password</title>
	</head>
	<body>
		<div role="main">
			<div class="card modal-dialog allow-interact">
				<div class="card-header h3 mb-0">Forgotten Password</div>
				<div class="card-body">
					<g:form controller="register" action="forgotPassword" name="resetPasswordForm" autocomplete='off'>
						<div class="form-group row">
							<label class="col-3 col-form-label" for="username">Username</label>
							<div class="col-9">
								<input name="username" class="form-control"/>
							</div>
						</div>
					</g:form>
					
					<span class="font-weight-bold text-danger">Special note for ConnectomeDB users:</span> <br>Please reset forgotten passwords through <a href="https://db.humanconnectome.org/">ConnectomeDB</a>.
				</div>
				<div class="modal-footer">
					<div class="mr-auto text-success"><span <g:if test="${!emailSent}">class="d-none"</g:if>>A password reset email was sent - check your mail!</span></div>
					<div class="btn-group">
						<button type="submit" form="resetPasswordForm" class="btn btn-primary">Reset Password</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
