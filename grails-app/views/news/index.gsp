
<%@ page import="balsa.News" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>BALSA News</title>
	</head>
	<body>
		<div id="list-news" style="margin:20px" role="main">
			<h1>News</h1>
			<div>
			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR">
			<g:link class="btn btn-primary" action="create">Add News Item</g:link><br>
			</sec:ifAnyGranted>
			<g:each in="${newsInstanceList}">
				<div class="panel panel-default" style="margin-top:10px">
					<div class="panel-heading">${it.dateCreated.format("MMM. dd, yyyy")}</div>
					<div class="panel-body"><g:encodeAs codec="PreserveWhitespace">${it.contents}</g:encodeAs></div>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_CURATOR">
					<div class="panel-footer"><g:link action="edit" id="${it.id}">Edit this item</g:link></div>
					</sec:ifAnyGranted>
				</div>
			</g:each>
			</div>
			<div class="pagination">
				<g:paginate total="${newsInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
