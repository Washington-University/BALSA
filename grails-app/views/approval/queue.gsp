<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${approvalInstance.title}</title>
			<script>
				function approvalGranted(userId, data) {
					$("#seeker"+userId).remove();
					$("#granted-approvals").append(data);
				}

				function approvalDenied(userId) {
					$("#seeker"+userId).remove();
				}
			
				function approvalRevoked(userId) {
					$("#approved"+userId).remove();
				}
			</script>
	</head>
	<body>
		<div role="main">
			Seeking Approval:
			<ul id="approval-seekers">
				<g:render template="seeking" collection="${approvalInstance.approvalSeekers}"/>
			</ul>
			Currently Approved:
			<ul id="granted-approvals">
				<g:render template="approved" collection="${approvalInstance.approvals}"/>
			</ul>
		</div>
	</body>

</html>