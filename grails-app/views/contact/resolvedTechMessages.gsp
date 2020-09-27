<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Technical Issues</title>
	</head>
	<body>
		<div role="main">
			<h3>Technical Issues</h3>
			
			<div class="well" style="overflow: hidden;">
				<g:each in="${messages}" var="issue">
					<div class="panel panel-default">
						<div class="panel-body">
							<p>
								<span class="attributeLabel">CREATED:</span><br>
								<span>${issue.createdDate}</span>
							</p>
							
							<p>
								<span class="attributeLabel">SUBMITTED BY:</span><br>
								<span>${issue.createdBy.username} : ${issue.createdBy.profile.emailAddress}</span>
							</p>
							
							<p>
								<span class="attributeLabel">TITLE:</span><br>
								<span>${issue.title}</span>
							</p>
							
							<p>
								<span class="attributeLabel">MESSAGE:</span><br>
								<span>${issue.contents}</span>
							</p>
							
							<p>
								<span class="attributeLabel">RESOLVED DATE:</span><br>
								<span>${issue.resolved}</span>
							</p>
							
							<p>
								<span class="attributeLabel">RESOLVED REASON:</span><br>
								<span>${issue.resolvedReason}</span>
							</p>
						</div>
					</div>
				</g:each>
			</div>
		</div>
	</body>
</html>
