<div id="contactUsModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Contact Our Curators</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<div class="modal-body">
				<g:form name="contactUsForm" controller="contact" action="curators" class="ajaxForm" data-success="sendingSuccess()">
					<div class="form-group row">
						<label class="col-4 col-form-label" for="name">Email for Replies</label>
						<div class="col-8">
							<input type="text" class="form-control" name="emailAddress">
						</div>
					</div>
				
					<div class="form-group row">
						<label class="col-4 col-form-label" for="subject">Subject</label>
						<div class="col-8">
							<input type="text" class="form-control" name="subject">
						</div>
					</div>
					
					<div class="form-group mb-0">
						<div>
							<textArea class="form-control static" rows="10" name="contents" placeholder="Write your message here."></textArea>
						</div>
					</div>
				</g:form>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<g:recaptcha form="contactUsForm" value="Send Message"/>
					<button class="btn btn-light" data-toggle="modal" data-target="#contactUsModal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>