<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Add News Item</title>
	</head>
	<body>
		<div id="create-news" class="content scaffold-create" role="main">
			<g:form url="[resource:newsInstance, action:'save']" >
				<div class="form-group">
					<label for="tweetText">
						Contents
					</label>
					<g:textArea class="form-control" name="contents" id="contents" rows="25" cols="100"/>
				</div>
				<button type="submit" class="btn btn-primary" style="width:87px">Create</button>
			</g:form>
		</div>
	</body>
</html>
