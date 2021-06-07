<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Batch Add Publications</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Batch Add Publications</div>
				<div class="btn-group btn-bar">
					<button class="btn btn-light" form="batchAddForm">Submit Batch</button>
					<g:link class="btn btn-light" action="index">Return to Index</g:link>
				</div>
				<div class="card-body">
					<g:form action="addBatch" name="batchAddForm">
						<div class="form-group mb-0">
							<div style="color:gray;margin-bottom:5px">Official name followed by a semicolon and a comma-separated list of other names</div>
							<g:textArea class="form-control" name="batch" rows="35"/>
						</div>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>
