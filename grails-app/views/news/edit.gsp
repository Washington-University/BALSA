<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Edit News Item</title>
	</head>
	<body>
		<div id="edit-news" role="main">
			<g:form url="[resource:newsInstance, action:'update']" >
				<div class="form-group">
					<label for="contents">
						Contents
					</label>
					<g:textArea class="form-control" name="contents" required="" value="${newsInstance?.contents}" rows="25" cols="100"/>
				</div>
				<button type="submit" class="btn btn-primary" style="width:87px">Update</button>
			</g:form>
		</div>
	</body>
</html>
