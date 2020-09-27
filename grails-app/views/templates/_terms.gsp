<g:if test="${terms?.numberOfTerms > 0}">
	<div id="termsModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width:1000px">
			<div class="modal-content">
				<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Data Use Terms</h4>
				</div>
				<g:if test="${terms.notAgreed}">
				<div class="modal-body">
				You have not yet agreed to all data access terms. Please read through the terms marked <span class="glyphicon glyphicon-remove" style="color:red"></span> and register agreement in order to access data.
				</div>
				</g:if>
				<div class="modal-body">
					<div class="panel-group" id="termsAccordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px">
					<g:each in="${terms.notAgreed}">
						<div class="panel panel-default">
							<div class="panel-heading" style="cursor:pointer" role="tab" id="${it.id}Heading" data-toggle="collapse" data-parent="#termsAccordion" href="#${it.id}terms" aria-expanded="true" aria-controls="details">
								<h4 class="panel-title"><span id="${it.id}title" class="glyphicon glyphicon-remove" style="color:red"></span> ${it.title}</h4>
							</div>
							<div id="${it.id}terms" class="panel-collapse collapse" role="tabpanel" aria-labelledby="${it.id}Heading">
								<div class="panel-body" style="height:${600 - terms?.numberOfTerms*42}px; overflow: auto">
									<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${it}" field="contents"/></g:encodeAs>
									<div>
										<sec:ifLoggedIn>
										<g:remoteLink controller="terms" action="agree" id="${it.id}" onSuccess="agree(data, '${it.id}');agreementChanged=true;updateRecentActivity();" onFailure="displayError();updateRecentActivity();">
											<button type="button" id="${it.id}button" class="btn btn-primary pull-right">I Agree</button>
										</g:remoteLink>
										</sec:ifLoggedIn>
										<sec:ifNotLoggedIn>
											<button type="button" class="btn btn-primary pull-right" disabled="disabled">Login to Agree</button>
										</sec:ifNotLoggedIn>
									</div>
								</div>
							</div>
						</div>
					</g:each>
					<g:each in="${terms.notApproved}">
					
					</g:each>
					<g:each in="${terms.agreed}">
						<div class="panel panel-default">
							<div class="panel-heading" style="cursor:pointer" role="tab" id="${it.id}Heading" data-toggle="collapse" data-parent="#termsAccordion" href="#${it.id}terms" aria-expanded="true" aria-controls="details">
								<h4 class="panel-title"><span class="glyphicon glyphicon-ok" style="color:green"></span> ${it.title}</h4>
							</div>
							<div id="${it.id}terms" class="panel-collapse collapse" role="tabpanel" aria-labelledby="${it.id}Heading">
								<div class="panel-body" style="height:${600 - terms?.numberOfTerms*42}px; overflow: auto">
									<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${it}" field="contents"/></g:encodeAs>
									<div>
										<button type="button" class="btn btn-default pull-right" disabled="disabled"><span class="glyphicon glyphicon-ok" style="color:green"></span> Agreed</button>
									</div>
								</div>
							</div>
						</div>
					</g:each>
					<g:each in="${terms.approved}">
					
					</g:each>
					
					<g:if test="${terms.customTermsTitle && terms.customTermsContent}">
						<div class="panel panel-default">
							<div class="panel-heading" style="cursor:pointer" role="tab" id="customTermsHeading" data-toggle="collapse" data-parent="#termsAccordion" href="#customTerms" aria-expanded="true" aria-controls="details">
								<h4 class="panel-title"><span class="glyphicon glyphicon-warning-sign" style="color:orange"></span> ${terms.customTermsTitle}</h4>
							</div>
							<div id="customTerms" class="panel-collapse collapse" role="tabpanel" aria-labelledby="customTermsHeading">
								<div class="panel-body" style="height:${600 - terms?.numberOfTerms*42}px; overflow: auto">
									<g:encodeAs codec="PreserveWhitespace">${terms.customTermsContent}</g:encodeAs>
									<div>
										<button type="button" class="btn btn-primary pull-right" onclick="bootbox.confirm({size: 'small', message: 'Approve these data use terms for public use?', 
											callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="approveCustomTerms" id="${terms.datasetId}"/>');} } })">
											Approve These Terms
										</button>
									</div>
								</div>
							</div>
						</div>
					</g:if>
					</div>
					<script>
						var agreementChanged = false;
						
						function agree(data, id) {
							$('#'+id+'title').removeClass('glyphicon-remove');
							$('#'+id+'title').addClass('glyphicon-ok');
							$('#'+id+'title').css('color', 'green');
							$('#'+id+'button').removeClass('btn-primary');
							$('#'+id+'button').addClass('btn-default');
							$('#'+id+'button').attr('disabled','disabled');
							$('#'+id+'button').html('<span class="glyphicon glyphicon-ok" style="color:green"></span> Agreed');
						}
						function displayError() {
							$('#cdbErrorModal').modal('toggle');
						}

						$('.panel-collapse').first().addClass('in');
						$('#termsModal').on('hidden.bs.modal', function(e) {if (agreementChanged) {location.reload(true);} });
					</script>
				</div>
			</div>
		</div>
	</div>
	<div id="cdbErrorModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width:500px">
			<div class="modal-content">
				<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Error</h4>
				</div>
				<div class="modal-body">
					<p>Something has gone wrong in the attempt to record your agreement to the Open Access data use terms. If you are using a 
					ConnectomeDB account, we recommend following these steps:</p>
					<ol>
						<li>Log out of BALSA</li>
						<li>Log into your ConnectomeDB account</li>
						<li>Locate any HCP data set</li>
						<li>Click on the 'Data Use Terms Required' button</li>
						<li>Accept the terms</li>
						<li>Log back into BALSA</li>
					</ol>
					<p>We apologize for any inconvenience.</p>
				</div>
			</div>
		</div>
	</div>
</g:if>