<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.curation.Issue" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${datasetInstance.shortTitle ?: datasetInstance.title} Issues</title>
	</head>
	<body>
		<div role="main">
			<g:render template="/templates/notesModal" />
			
			<div class="card overflow-hidden ${datasetTerm('item':datasetInstance)}">
				<div class="card-header h3 mb-0">Issues for ${datasetInstance.shortTitle ?: datasetInstance.title}</div>
				<div class="btn-group btn-bar">
					<g:link class="btn btn-light" action="show" id="${datasetInstance.id}">Main</g:link>
					<button id="notesModalButton" type="button" class="btn btn-light" role="button" data-toggle="modal" data-target="#notesModal">
						Notes <g:if test="${!datasetInstance.hasReadNotes()}">(new)</g:if>
					</button>
					<sec:ifAnyGranted roles="ROLE_CURATOR,ROLE_ADMIN">
					<g:if test="${versionInstance.isSubmitted()}">
					<button type="button" class="btn btn-light" role="button" data-toggle="modal" data-target="#createIssueModal">New Issue</button>
					</g:if>
					<a class="btn btn-light" href="mailto:${(datasetInstance.owners*.profile*.emailAddress).minus(null).join(',')}">Email Owners</a>
					<g:link class="btn btn-light" controller="curation">Curation Queue</g:link>
					<button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Authority Control <span class="caret"></span>
					</button>
					<div class="dropdown-menu">
						<g:link controller="institution" class="dropdown-item">Institutions</g:link>
						<g:link controller="publication" class="dropdown-item">Publications</g:link>
					</div>
					</sec:ifAnyGranted>
					<g:render template="/templates/submissionButtons" bean="${versionInstance}" var="item" />
				</div>
				<div class="card-body">
					<g:if test="${warnings || lacksSpeciesTag || missingFiles || notUsed || identicalPaths || identicalData}">
					<span class="attributeLabel">Warnings</span>
					<ul class="text-danger">
						<g:each in="${warnings}">
						<li>${it}</li>
						</g:each>

						<g:if test="${lacksSpeciesTag}">
						<li>
							Scenes lacking species tag:
							<ul>
							<g:each in="${lacksSpeciesTag}">
							<li><g:link controller="scene" action="show" id="${it.sceneLine.id}">${it.shortName ?: it.name}</g:link></li>
							</g:each>
							</ul>
						</li>
						</g:if>

						<g:if test="${missingFiles}">
						<li>
							Missing supporting files:
							<ul>
							<g:each in="${missingFiles}">
							<li>${it}</li>
							</g:each>
							</ul>
						</li>
						</g:if>

						<g:if test="${notUsed}">
						<li>
							Files not used by any scene:
							<ul>
							<g:each in="${notUsed}">
							<li><g:link controller="file" action="show" id="${it.id}">${it.filepath}</g:link></li>
							</g:each>
							</ul>
						</li>
						</g:if>

						<g:if test="${identicalPaths}">
						<li>
							Files with identical file paths:
							<ul>
							<g:each in="${identicalPaths}">
							<li><g:link controller="file" action="show" id="${it.id}">${it.filepath}</g:link></li>
							</g:each>
							</ul>
						</li>
						</g:if>

						<g:each in="${identicalData}" var="identicalDataSet">
						<li>
							Files with identical data:
							<ul>
							<g:each in="${identicalDataSet}">
							<li><g:link controller="file" action="show" id="${it.id}">${it.filepath}</g:link></li>
							</g:each>
							</ul>
						</li>
						</g:each>
					</ul>
					</g:if>

					<h3>Issues</h3>

					<div id="createIssueModal" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">New Issue</h4>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								</div>
								<g:form name="createIssueForm" action="createIssue" id="${datasetInstance.id}">
									<div class="modal-body">
										<div class="form-group">
											<label for="title">Title</label>
											<g:field type="text" class="form-control" name="title" required="required"/>
										</div>
										<div class="form-group">
											<label for="newStatus">Issue Type</label>
											<g:select class="form-control" name="newStatus" from="${['PROBLEM', 'SUGGESTION']}" />
										</div>

										<div class="form-group">
											<label for="comment">Comment</label>
											<g:textArea class="form-control" name="comment" rows="10" cols="40" required="required"/>
										</div>
									</div>
									<div class="modal-footer">
										<div class="btn-group">
											<button type="submit" class="btn btn-primary">Create</button>
											<button type="button" class="btn btn-light" data-toggle="modal" data-target="#createIssueModal">Cancel</button>
										</div>
									</div>
								</g:form>
							</div>
						</div>
					</div>

					<g:if test="${!datasetInstance.issues}">
						No issues have been created for this dataset
					</g:if>

					<g:each in="${datasetInstance.issues}" var="issue">
					<p>
						<div class="card">
							<div class="card-body">
								<p>
									<span class="attributeLabel">TITLE:</span><br>
									<span>${issue.title}</span>
								</p>
								<p>
									<span class="attributeLabel">STATUS:</span><br>
									<span <g:if test="${issue.status.toString() == 'PROBLEM'}">class="text-danger"</g:if>>${issue.status}</span>
								</p>
								<p>
									<span class="attributeLabel">VERSION:</span><br>
									<g:link controller="${datasetTerm('item':datasetInstance)}" action="show" id="${datasetInstance.id}" params="[version: issue.versionId]">${issue.versionId}</g:link>
								</p>

								<g:each in="${issue.comments?.take(1)}" var="comment">
								<p>
									<span class="attributeLabel">MOST RECENT COMMENT:</span><br>
									<span><g:formatDate format="yyyy-MM-dd HH:mm" date="${comment.createdDate}"/> - ${comment.createdBy}: ${comment.comment}</span>
								</p>
								</g:each>

								<g:if test="${issue.comments?.size() > 1}">
								<p>
									<span class="attributeLabel">PREVIOUS COMMENTS:</span><br>
									<a data-toggle="collapse" href="#previousComments" class="toggle-text">click to show/hide previous comments</a>
									<div class="collapse in" id="previousComments">
										<g:each in="${issue.comments.drop(1)}" var="comment">
										<span><g:formatDate format="yyyy-MM-dd HH:mm" date="${comment.createdDate}"/> - ${comment.createdBy}: ${comment.comment}</span><br>
										</g:each>
									</div>
								</p>
								</g:if>

								<div class="btn-group mt-4">
									<button type="button" class="btn btn-light" role="button" data-toggle="modal" data-target="#addCommentModal${issue.id}">Add Comment</button>
									<button type="button" class="btn btn-light" role="button" data-toggle="modal" data-target="#updateIssueModal${issue.id}">Update</button>
									<button type="button" class="btn btn-light" onclick="bootbox.confirm({size: 'small', message: 'Delete this issue?', 
										callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="deleteIssue" id="${datasetInstance.id}" params="[issueId:issue.id]"/>');} } })">
										Delete
									</button>
								</div>
							</div>
						</div>

						<div id="addCommentModal${issue.id}" class="modal fade" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Add Comment</h4>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									</div>
									<g:form name="addCommentForm" action="updateIssue" id="${datasetInstance.id}">
										<g:hiddenField name="issueId" value="${issue.id}"/>
										<div class="modal-body">
											<div class="form-group">
												<g:textArea class="form-control" name="newComment" rows="10" cols="40" required="required"/>
											</div>
										</div>
										<div class="modal-footer">
											<div class="btn-group">
												<button type="submit" class="btn btn-primary">Add Comment</button>
												<button type="button" class="btn btn-light" data-toggle="modal" data-target="#addCommentModal${issue.id}">Cancel</button>
											</div>
										</div>
									</g:form>
								</div>
							</div>
						</div>
						<div id="updateIssueModal${issue.id}" class="modal fade" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">Update Issue</h4>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									</div>
									<g:form name="updateIssueForm" action="updateIssue" id="${datasetInstance.id}">
										<g:hiddenField name="issueId" value="${issue.id}"/>
										<div class="modal-body">
											<div class="form-group">
												<label for="newStatus">New Status</label>
												<g:if test="${versionInstance.isSubmitted()}">
												<g:select class="form-control" name="newStatus" from="${['PROBLEM', 'SUGGESTION', 'RESOLVED']}" />
												</g:if>
												<g:else>
												<g:select class="form-control" name="newStatus" from="${['FIXED', 'INTENTIONAL']}" />
												</g:else>
											</div>
											<div class="form-group">
												<label for="newComment">Comment</label>
												<g:textArea class="form-control" name="newComment" rows="10" cols="40" required="required"/>
											</div>
										</div>
										<div class="modal-footer">
											<div class="btn-group">
												<button type="submit" class="btn btn-primary">Update</button>
												<button type="button" class="btn btn-light" data-toggle="modal" data-target="#updateIssueModal${issue.id}">Cancel</button>
											</div>
										</div>
									</g:form>
								</div>
							</div>
						</div>
					</p>
					</g:each>
				</div>
			</div>
		</div>
	</body>
</html>
