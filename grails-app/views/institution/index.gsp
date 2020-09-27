<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Institutions</title>
	</head>
	<body>
		<div role="main">
			<h3>Institutions</h3>
			
			<div class="btn-group">
				<g:link class="btn btn-default" action="batchAdd">Batch Add</g:link>
			</div>
			
			<br><br>
			<div class="well" style="overflow: hidden;">
				<div style="display: table; width: 100%">
					<div style="display: table-row; font-weight: bold">
						<div style="display: table-cell;width:500px">
							Canonical Name
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell;width:1000px">
							Other Names
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell">
							Actions
						</div>
					</div>
					
					<g:each in="${institutions}" var="institution">
					<g:form action="update" id="${institution.id}" style="display: table-row">
						<div style="display: table-cell">
							<input class="form-control" type="text" name="canonicalName" value="${institution.canonicalName}">
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell">
							<input class="form-control" type="text" name="otherNames" value="${institution.names.join(',')}">
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell">
							<div class="btn-group" style="display: flex">
							<button class="btn btn-default" type="submit" style="width:70px">Update</button>
							<g:if test="${!institution.approved}">
							<g:link action="approve" id="${institution.id}" class="btn btn-default" type="submit" style="width:70px">Approve</g:link>
							</g:if>
							<button type="button" class="btn btn-default" onclick="bootbox.prompt({title: 'Delete or Merge?', inputType: 'select', inputOptions: selectOptions, value: 'delete',
								callback: function(result) { if (result) {$(location).attr('href','<g:createLink action="delete" id="${institution.id}"/>/?replacement=' + result);} } });">
								Delete/Merge
							</button>
							</div>
						</div>
					</g:form>
					</g:each>
					<g:form action="create" style="display: table-row">
						<div style="display: table-cell">
							<input class="form-control" type="text" name="canonicalName">
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell">
							<input class="form-control" type="text" name="otherNames">
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell">
							<div class="btn-group" style="display: flex">
							<button class="btn btn-default" type="submit" style="width:70px">Add</button>
							</div>
						</div>
					</g:form>
				</div>
				<script>
					var selectOptions = [{text: 'Delete', value: 'delete'}<g:each in="${institutions}" var="institution">,{text: '${institution.canonicalName}', value: '${institution.id}'}</g:each>];
				</script>
			</div>
		</div>
	</body>
</html>
