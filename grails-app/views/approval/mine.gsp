<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>My Approvals</title>
	</head>
	<body>
		<g:each in="${approvalInstanceList}">
			<g:link action="show" resource="${it}">${it.title}</g:link>
		</g:each>
	</body>
</html>

