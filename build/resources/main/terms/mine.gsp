<%@ page import="balsa.security.Approval" %>
<%@ page import="balsa.Dataset" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Data Access Terms</title>
	</head>
	<body>
		<div id="list-study" role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Agreed Data Access Terms</div>
				<div class="btn-group btn-bar">
					<g:link class="btn btn-light" controller="profile" action="mine">Profile</g:link>
					<g:link class="btn btn-light" controller="download" action="mine">Download History</g:link>
				</div>
				<div class="card-body">
					<ul class="list-group">
						<g:each in="${terms}">
							<li class="list-group-item"><g:link class="h5 mb-0" action="show" id="${it.id}">${it.title}</g:link></li>
						</g:each>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>