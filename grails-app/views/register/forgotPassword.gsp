<html>
	<head>
		<meta name="layout" content="main">
		<title>Forgot Password</title>
	</head>
	<body>		
		<div style="width:600px;margin:0 auto;">
			<g:hasErrors bean="${forgotPasswordCommand}">
			<ul class="errors" role="alert">
				<g:eachError bean="${forgotPasswordCommand}" var="error">
				<li><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:if test='${emailSent}'>
			<br/>
			<div style="text-align: center;">Your password reset email was sent - check your mail!</div>
			</g:if>
			<g:else>
			<g:form class="form-horizontal" controller="register" action="forgotPassword" name="forgotPasswordForm" autocomplete='off' focus='username'>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<input name="username" class="form-control" id="username" placeholder="Username" value="${forgotPasswordCommand.username}"/>
					</div>
				</div>
				<div class="form-group" style="padding-top:15px;border-top:1px solid #e5e5e5;">
					<div style="display:inline-block;width:65%;color:purple;font-weight:500">
						ConnectomeDB users: Please reset forgotten passwords through <a href="https://db.humanconnectome.org/">ConnectomeDB</a>.
					</div>
					<div style="float:right;">
						<button type="submit" class="btn btn-primary">Reset password</button>
					</div>
				</div>
			</g:form>
			</g:else>
		</div>
	</body>
</html>
