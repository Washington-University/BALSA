<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Add News Item</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Add News Item</div>
				<div class="btn-group btn-bar">
					<button type="submit" class="btn btn-primary" form="newsForm">Create</button>
					<g:link action="index" class="btn btn-light">Cancel</g:link>
				</div>
				<div class="card-body">
					<g:form action="save" name="newsForm">
						<div class="form-group">
							<label for="contents">
								Contents
							</label>
							<g:textArea class="form-control" name="contents" id="contents" rows="25" cols="100"/>
						</div>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>
