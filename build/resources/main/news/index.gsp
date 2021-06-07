
<%@ page import="balsa.News" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>BALSA News</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden border-bottom-0">
				<div class="card-header h3 mb-0">BALSA News</div>
				<div class="btn-group btn-bar">
					<a class="btn btn-light" href="/">Main</a>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR">
					<g:link class="btn btn-light" action="create">Add News Item</g:link><br>
					</sec:ifAnyGranted>
				</div>
				<div class="card-body p-0 mb-0">
					<ul class="list-group">
						<g:each in="${newsInstanceList}">
						<li class="list-group-item border-right-0 border-left-0 border-top-0 mb-0">
							<span class="font-weight-bold">${it.formattedDate()}</span> - 
							<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR">
								<span class="badge badge-light badge-pill float-right"><g:link action="edit" id="${it.id}">Edit this item</g:link></span>
								
							</sec:ifAnyGranted>
							<br><br>
							
							<g:encodeAs codec="PreserveWhitespace">${it.contents}</g:encodeAs>
						</li>
						</g:each>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>