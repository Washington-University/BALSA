<div id="reportIssuesModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Report Technical Issue</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<div class="modal-body">
				<g:form name="reportIssuesForm" controller="contact" action="tech" class="ajaxForm" data-success="reportingSuccess()">
					<div class="form-group row">
						<label class="col-4 col-form-label" for="subject">Subject</label>
						<div class="col-8">
							<input type="text" class="form-control" name="title" id="issueTitle">
						</div>
					</div>
					
					<div class="form-group">
						<div>
							<textArea class="form-control" rows="10" name="contents" id="issueContents" placeholder="Please describe the issue concisely."></textArea>
						</div>
					</div>
				</g:form>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<g:recaptcha form="reportIssuesForm" value="Submit Issue"/>
					<button class="btn btn-light" data-toggle="modal" data-target="#reportIssuesModal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>