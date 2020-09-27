<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Create New Tag Category</title>
	</head>
	<body>
		<div id="list-fileMetadata" class="content scaffold-list" role="main">
			<g:form name="categorySearchType" action="saveCategory">
				<div class="form-group">
					<label for="name">Category Name</label>
					<input type="text" class="form-control" name="name">
				</div>
				<div class="form-group">
					<label for="description">Description</label>
					<g:textArea name="description" rows="5" cols="50" />
				</div>
				<div class="form-group">
					<label for="searchType">How should users search this category?</label><br>
					<label class="radio-inline">
						<input type="radio" name="searchType" value="DROPDOWN"> Dropdown menu
					</label>
					<label class="radio-inline">
						<input type="radio" name="searchType" value="FIELD"> Text field
					</label>
					<label class="radio-inline">
						<input type="radio" name="searchType" value="RADIO"> Radio buttons
					</label>
					<label class="radio-inline">
						<input type="radio" name="searchType" value="CHECKBOX"> Checkboxes
					</label>
				</div>
				<g:submitButton class="btn btn-primary" name="create" value="Create" />
			</g:form>
		</div>
	</body>
</html>
