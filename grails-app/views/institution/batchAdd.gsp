<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Batch Add Institutions</title>
	</head>
	<body>
		<div role="main">
			<h3>Batch Add Institutions</h3>
			
			<div class="btn-group">
				<button class="btn btn-primary" form="batchAddForm">Submit Batch</button>
				<g:link class="btn btn-default" action="show" resource="${datasetInstance}">Return to Index</g:link>
			</div>
			
			<br><br>
			<div class="well" style="overflow: hidden;">
				<g:form action="addBatch" name="batchAddForm">
				<div class="form-group" style="margin-bottom:0px">
				<div style="color:gray;margin-bottom:5px">Canonical name followed by a semicolon and a comma-separated list of other names</div>
				<g:textArea class="form-control" name="batch" rows="35"/>
				</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
