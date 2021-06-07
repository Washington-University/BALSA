<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Edit News Item</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Edit News Item</div>
				<div class="btn-group btn-bar">
					<button type="submit" class="btn btn-primary" form="newsForm">Update</button>
					<g:link action="index" class="btn btn-light">Cancel</g:link>
				</div>
				<div class="card-body">
				${newsInstance.errors}
					<g:form action="update" name="newsForm">
						<input type="hidden" name="id" value="${newsInstance.id}">
						<input type="hidden" name="dateCreated" value="${newsInstance.dateCreated}">
						<div class="form-group">
							<label for="contents">
								Contents
							</label>
							<g:textArea class="form-control" name="contents" id="contents" rows="25" cols="100" value="${newsInstance.contents}" />
						</div>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>
