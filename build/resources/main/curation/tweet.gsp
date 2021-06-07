<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Add Tweet</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Tweet</div>
				<div class="btn-group btn-bar">
					<button type="submit" class="btn btn-primary" form="tweetForm">Post</button>
					<a href="/" class="btn btn-light">Cancel</a>
				</div>
				<div class="card-body">
					<g:form action="postTweet" name="tweetForm" class="ajaxForm" data-success="bootbox.alert('Tweet successful')">
						<div class="form-group mb-0">
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

