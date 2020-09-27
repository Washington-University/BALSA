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
							
							<div class="btn-group">
								<button type="button" class="btn btn-default" role="button" data-toggle="modal" data-target="#resolveIssueModal${issue.id}">Mark Resolved</button>
								<g:link class="btn btn-default" role="button" action="deleteTechMessage" id="${issue.id}">Delete</g:link>
							</div>
						</div>
					</div>
					
					<div id="resolveIssueModal${issue.id}" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color:white"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title">Resolve Issue</h4>
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
											<button type="submit" class="btn btn-primary" style="width:87px">Resolve</button>
											<button type="button" class="btn btn-default" data-toggle="modal" data-target="#resolveIssueModal${issue.id}" style="width:87px">Cancel</button>
										</div>
									</div>
								</g:form>
							</div>
						</div>
					</div>
				</g:each>
			</div>
		</div>
	</body>
</html>
