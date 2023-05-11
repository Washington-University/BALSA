<html>
	<head>
		<meta name="layout" content="main">
		<title>Forgotten Username</title>
	</head>
	<body>
		<div role="main">
			<div class="card modal-dialog allow-interact">
				<div class="card-header h3 mb-0">Forgotten Username</div>
				<div class="card-body">
					<g:form controller="register" action="forgotUsername" name="forgotUsernameForm" autocomplete='off'>
						<div class="form-group row">
							<label class="col-4 col-form-label" for="username">Email Address</label>
							<div class="col-8">
								<input name="email" class="form-control"/>
							</div>
						</div>
					</g:form>
					
					Please enter the email address you used to register your account and we will send you an email with your username.
				</div>
				<div class="modal-footer">
					<div class="mr-auto text-success"><span <g:if test="${!emailSent}">class="d-none"</g:if>>Your username email was sent - check your mail!</span></div>
					<div class="btn-group">
						<g:recaptcha form="forgotUsernameForm" value="Send Email"/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html> 
