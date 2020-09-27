<html>
	<head>
		<meta name="layout" content="main">
		<s2ui:title messageCode='spring.security.ui.resetPassword.title'/>
	</head>
	<body>
		<div style="width:600px;margin:0 auto;">
			<g:hasErrors bean="${resetPasswordCommand}">
			<ul class="errors" role="alert">
				<g:eachError bean="${resetPasswordCommand}" var="error">
				<li><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form class="form-horizontal" controller="register" action="resetPassword" name="resetPasswordForm" autocomplete='off' focus='password'>
				<g:hiddenField name='t' value='${token}'/>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input name="password" class="form-control" id="password" placeholder="Password" type="password"/>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password (again)</label>
					<div class="col-sm-10">
						<input name="password2" class="form-control" id="password" placeholder="Password (again)" type="password"/>
					</div>
				</div>
				<div style="text-align:right;padding-top:15px;border-top:1px solid #e5e5e5;">
					<div class="btn-group">
						<button type="submit" class="btn btn-primary" style="width:87px">Register</button>
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>
