<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Technical Issues</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Resolved Technical Issues</div>
				<div class="card-body p-0">
					<ul class="list-group">
						<g:each in="${messages}" var="issue">
						<li class="list-group-item border-0 mb-0">
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
						</li>
						</g:each>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
