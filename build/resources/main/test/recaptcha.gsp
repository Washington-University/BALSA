<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Recaptcha</title>
	</head>
	<body>
		<div role="main">
			<h3>Recaptcha</h3>
			

			<g:form name="testForm" action="verify">
				<g:recaptcha form="testForm" value="Submit Test"/>
			</g:form>
		</div>
	</body>
</html>
