<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Technical Issues</title>
	</head>
	<body>
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">Technical Issues</div>
				<div class="card-body p-0">
					<ul class="list-group">
						<g:each in="${messages}" var="issue">
						<div class="btn-group btn-bar">
							<button type="button" class="btn btn-primary" role="button" data-toggle="modal" data-target="#resolveIssueModal${issue.id}">Mark Resolved</button>
							<g:link class="btn btn-light" role="button" action="deleteTechMessage" id="${issue.id}">Delete</g:link>
						</div>

						<div id="resolveIssueModal${issue.id}" class="modal fade" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title mb-0">Resolve Issue</h4>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									</div>
									<g:form name="resolveIssueForm" action="resolveTechMessage" id="${issue.id}">
										<div class="modal-body">
											<div class="form-group">
												<label for="resolvedReason">Comments</label>
												<g:textArea class="form-control" name="resolvedReason" rows="10" cols="40"/>
											</div>
										</div>
										<div class="modal-footer">
											<div class="btn-group">
												<button type="submit" class="btn btn-primary">Resolve</button>
												<button type="button" class="btn btn-light" data-toggle="modal" data-target="#resolveIssueModal${issue.id}">Cancel</button>
											</div>
										</div>
									</g:form>
								</div>
							</div>
						</div>
						
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
						</li>
						</g:each>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
