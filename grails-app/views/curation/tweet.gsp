<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Add Tweet</title>
	</head>
	<body>
		<div id="create-news" class="content scaffold-create" role="main">
			<g:formRemote id="tweetForm" name="tweetForm" url="[action: 'postTweet']" method="POST" onSuccess="bootbox.alert('Save successful');">
				<div class="form-group">
					<label for="tweetText">
						Contents
					</label>
					<g:textArea class="form-control" name="tweetText" id="tweetText" rows="25" cols="100"/>
				</div>
				<button type="submit" class="btn btn-primary" style="width:87px">Post</button>
			</g:formRemote>
		</div>
	</body>
</html>
