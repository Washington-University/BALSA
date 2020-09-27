<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Tag Categories</title>
	</head>
	<body>
		<div id="list-fileMetadata" class="content scaffold-list" role="main">
			<h1>Tag Categories</h1>
			<g:link class="btn btn-primary" action="createCategory">Add new tag category</g:link>
			<g:each in="${categoryInstanceList}">
			<div class="well">
				${it.name}
				<g:link class="btn btn-primary" action="category" id="${it.id}">Details/Edit</g:link>
				<g:link class="btn btn-default" action="deleteCategory" id="${it.id}" onclick="return confirm('Delete category?')">Remove</g:link>
			</div>
			</g:each>
			<g:if test="${categoryInstanceCount > 10}">
			<g:paginate omitPrev="true" omitNext="true" total="${categoryInstanceCount ?: 0}"/>
			</g:if>
		</div>
	</body>
</html>
