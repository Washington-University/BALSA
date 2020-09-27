<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Publications</title>
	</head>
	<body>
		<div role="main">
			<h3>Publications</h3>
			
			<div class="btn-group">
				<g:link class="btn btn-default" action="batchAdd">Batch Add</g:link>
			</div>
			
			<br><br>
			<div class="well" style="overflow: hidden;">
				<div style="display: table; width: 100%">
					<div style="display: table-row; font-weight: bold">
						<div style="display: table-cell;width:500px">
							Official Name
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
					
					<g:each in="${publications}" var="publication">
					<g:form action="update" id="${publication.id}" style="display: table-row">
						<div style="display: table-cell">
							<input class="form-control" type="text" name="officialName" value="${publication.officialName}">
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell">
							<input class="form-control" type="text" name="otherNames" value="${publication.abbrNames.join(',')}">
						</div>
						<div>&nbsp;</div>
						<div style="display: table-cell">
							<div class="btn-group" style="display: flex">
							<button class="btn btn-default" type="submit" style="width:70px">Update</button>
							<g:if test="${!publication.approved}">
							<g:link action="approve" id="${publication.id}" class="btn btn-default" type="submit" style="width:70px">Approve</g:link>
							</g:if>
							<button type="button" class="btn btn-default" onclick="bootbox.prompt({title: 'Delete or Merge?', inputType: 'select', inputOptions: selectOptions, value: 'delete',
								callback: function(result) { if (result) {$(location).attr('href','<g:createLink action="delete" id="${publication.id}"/>/?replacement=' + result);} } });">
								Delete
							</button>
							</div>
						</div>
					</g:form>
					</g:each>
					<g:form action="create" style="display: table-row">
						<div style="display: table-cell">
							<input class="form-control" type="text" name="officialName">
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
					var selectOptions = [{text: 'Delete', value: 'delete'}<g:each in="${publications}" var="publication">,{text: '${publication.officialName}', value: '${publication.id}'}</g:each>];
				</script>
			</div>
		</div>
	</body>
</html>
