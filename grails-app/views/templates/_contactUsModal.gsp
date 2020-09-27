<%@ page import="balsa.security.BalsaUser" %>

<div id="contactUsModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Contact Our Curators</h4>
			</div>
			<div class="modal-body">
				<g:formRemote name="contactUsForm" class="form-horizontal" style="padding-right:15px;padding-left:15px" url="[controller: 'contact', action: 'curators']" method="POST" onSuccess="sendingSuccess();updateRecentActivity();">
					<div class="form-group">
						<label class="control-label" for="name">Email Address For Replies</label>
						<div>
							<input type="text" class="form-control" name="emailAddress">
						</div>
					</div>
				
					<div class="form-group">
						<label class="control-label" for="subject">Subject</label>
						<div>
							<input type="text" class="form-control" name="subject">
						</div>
					</div>
					
					<div class="form-group">
						<div>
							<textArea class="form-control" rows="10" name="contents" placeholder="Write your message here."></textArea>
						</div>
					</div>
				
					<div class="form-group" style="text-align: center;">
						<div style="display: inline-block">
							<recaptcha:recaptcha/>
						</div>
					</div>
				
				</g:formRemote>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary" form="contactUsForm">Send Message</button>
					<button style="width:87px" class="btn btn-default" data-toggle="modal" data-target="#contactUsModal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
function sendingSuccess() {
	$('#contactUsModal').modal('hide');
	bootbox.alert('Message sent.');
}
</script>