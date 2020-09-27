<html>
	<head>
		<meta name="layout" content="main">
		<title>Forgot Username</title>
	</head>
	<body>		
		<div style="width:600px;margin:0 auto;">
			<g:hasErrors bean="${forgotUsernameCommand}">
			<ul class="errors" role="alert">
				<g:eachError bean="${forgotUsernameCommand}" var="error">
				<li><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:if test='${emailSent}'>
			<br/>
			<div style="text-align: center;">Your username email was sent - check your mail!</div>
			</g:if>
			<g:else>
			<div style="margin-right:auto;margin-left:auto;width:525px;">Enter the email address you used to register your account and we will send you an email with your username.</div>
			<br>
			<g:form class="form-horizontal" controller="register" action="forgotUsername" name="forgotUsernameForm" autocomplete='off' focus='email'>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input name="email" class="form-control" id="email" placeholder="Email"/>
					</div>
				</div>
				<div style="text-align:right;padding-top:15px;border-top:1px solid #e5e5e5;">
					<div class="btn-group">
						<button type="submit" class="btn btn-primary">Send email</button>
					</div>
				</div>
			</g:form>
			</g:else>
		</div>
	</body>
</html>
