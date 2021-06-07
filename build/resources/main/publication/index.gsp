<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Publications</title>
	</head>
	<body>
<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Publications</div>
				<div class="btn-group btn-bar">
					<g:link class="btn btn-light" action="batchAdd">Batch Add</g:link>
				</div>
				<div class="card-body p-0">
					<table class="table table-hover tablesorter pager mb-0 filterer" data-cssfilter='["form-control","form-control","d-none"]' data-sort='[[2,0],[0,0]]'>
						<thead class="authority-control-table">
							<tr>
								<th class="border-0">Official Name</th>
								<th class="border-0 sorter-false">Other Names</th>
								<th class="border-0 filter-false" style="width:200px">Actions</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${publications}" var="publication">
							<tr>
								<g:form action="update" id="${publication.id}">
									<td>
										<div class="d-none">${publication.officialName}</div>
										<input class="form-control btn-thin" type="text" name="officialName" value="${publication.officialName}">
									</td>
									<td>
										<div class="d-none">${publication.abbrNames.join(',')}</div>
										<input class="form-control btn-thin" type="text" name="otherNames" value="${publication.abbrNames.join(',')}">
									</td>
									<td>
										<g:if test="${!publication.approved}">
										<div class="d-none">0</div>
										</g:if>
										<g:else>
										<div class="d-none">1</div>
										</g:else>
										<div class="btn-group btn-thin">
											<g:if test="${!publication.approved}">
											<g:link action="approve" id="${publication.id}" class="btn btn-primary" type="submit">Approve</g:link>
											</g:if>
											<g:else>
											<button class="btn btn-light" type="submit">Update</button>
											</g:else>
											<button type="button" class="btn btn-light" onclick="bootbox.prompt({title: 'Delete or Merge?', inputType: 'select', inputOptions: selectOptions, value: 'delete', callback: function(result) { if (result) {$(location).attr('href','<g:createLink action="delete" id="${publication.id}"/>/?replacement=' + result);} } });">
												Delete/Merge
											</button>
										</div>
									</td>
								</g:form>
							</tr>
							</g:each>
							<tr>
								<g:form action="create">
									<td>
										<input class="form-control" type="text" name="officialName">
									</td>
									<td>
										<input class="form-control" type="text" name="otherNames">
									</td>
									<td>
										<div class="d-none">2</div>
										<div class="btn-group">
											<button class="btn btn-light" type="submit">Add</button>
										</div>
									</td>
								</g:form>
							</tr>
						</tbody>
						<g:render template="/templates/tableFooter" model="[cols: 3]" />
					</table>
				</div>
			</div>
		</div>
		<script>
			var selectOptions = [{text: 'Delete', value: 'delete'}<g:each in="${publications.sort { it.officialName }}" var="publication">,{text: '${publication.officialName}', value: '${publication.id}'}</g:each>];
		</script>
	</body>
</html>
