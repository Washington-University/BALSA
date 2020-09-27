<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
		<div style="width:600px;margin:0 auto;">
			<g:hasErrors bean="${registerCommand}">
			<ul class="errors" role="alert">
				<g:eachError bean="${registerCommand}" var="error">
				<li><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form controller="register" action="register" name="registerForm" type='register' autocomplete='off' focus='username'>
				<div class="form-group<g:if test="${hasErrors(bean: registerCommand, field: 'username', 'error')}"> has-error</g:if>">
					<label for="username" class="control-label">Username</label>
					<div class="">
						<g:field type="text" name="username" class="form-control" id="username" placeholder="Username" value="${registerCommand.username}" required="true" />
					</div>
				</div>
				<div class="form-group<g:if test="${hasErrors(bean: registerCommand, field: 'email', 'error')}"> has-error</g:if>">
					<label for="username" class="control-label">Email</label>
					<div class="">
						<g:field type="email" name="email" class="form-control" id="username" placeholder="Email Address" value="${registerCommand.email}" required="true" />
					</div>
				</div>
				<div class="form-group<g:if test="${hasErrors(bean: registerCommand, field: 'password', 'error')}"> has-error</g:if>">
					<label for="password" class="control-label">Password</label>
					<div class="">
						<g:field name="password" class="form-control" id="password" placeholder="Password" type="password" required="true" />
					</div>
				</div>
				<div class="form-group<g:if test="${hasErrors(bean: registerCommand, field: 'password2', 'error')}"> has-error</g:if>">
					<label for="password" class="control-label">Password (again)</label>
					<div class="">
						<g:field name="password2" class="form-control" id="password" placeholder="Password (again)" type="password" required="true" />
					</div>
				</div>
				<div class="form-group" style="text-align: center;">
				<div style="display: inline-block">
					<recaptcha:recaptcha/>
				</div>
				</div>
				<div class="form-group" style="padding-top:15px;border-top:1px solid #e5e5e5;">
					<div style="display:inline-block;width:75%;color:purple;font-weight:500">
						* Special note for all <a href="https://db.humanconnectome.org/">ConnectomeDB</a> users: BALSA will accept your existing ConnectomeDB login. There's no need to register again.
					</div>
					<div style="float:right;">
						<button type="submit" class="btn btn-primary" style="width:87px">Register</button>
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>
