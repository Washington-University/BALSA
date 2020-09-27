<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${approvalInstance.title}</title>
	</head>
	<body>
		<g:if test="${isOwner}">
		<g:link action="queue" resource="${approvalInstance}">Queue</g:link>
		<g:link action="edit" resource="${approvalInstance}">Edit</g:link>
		</g:if>
		<div id="show-profile" role="main">
			<h1>${approvalInstance.title}</h1>
			<p>${approvalInstance.contents.encodeAsPreserveWhitespace()}</p>
			<span>Link:&nbsp;${approvalInstance.link.encodeAsRemoveScript()}</span><br>
		</div>
	</body>
</html>