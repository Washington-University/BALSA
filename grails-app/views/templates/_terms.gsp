<g:if test="${terms?.numberOfTerms > 0}">
	<div id="termsModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-wide">
			<div class="modal-content">
				<div class="modal-header ${datasetTerm('item':datasetInstance)}">
					<h4 class="modal-title">Data Use Terms</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				</div>
				<g:if test="${terms.notAgreed}">
				<div class="modal-body">
					You have not yet agreed to all data access terms. Please read through the terms marked <span class="text-danger h5 mb-0 lh-0" aria-hidden="true">&times;</span> and register agreement in order to access data.
				</div>
				</g:if>
				<div class="modal-body">
					<div id="termsAccordion" class="accordion">
						<g:each in="${terms.notAgreed}">
						<div class="card">
							<div class="card-header h5 mb-0 pointer terms-title" data-toggle="collapse" data-target="#terms${it.id}">
								<span id="title${it.id}" class="text-danger h4 mb-0 lh-0" aria-hidden="true">&times;</span> ${it.title}
							</div>
							<div id="terms${it.id}" class="card-body collapse" data-parent="#termsAccordion">
								<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${it}" field="contents"/></g:encodeAs>
								<br>
								<sec:ifLoggedIn>
								<g:form controller="terms" action="agree" id="${it.id}" data-success="agree('${it.id}');checkTermsReload();" class="ajaxForm">
									<button type="submit" id="button${it.id}" class="btn btn-primary float-right">I Agree</button>
								</g:form>
								</sec:ifLoggedIn>
								<sec:ifNotLoggedIn>
								<button type="button" class="btn btn-primary float-right" disabled="disabled">Login to Agree</button>
								</sec:ifNotLoggedIn>
							</div>
						</div>
						</g:each>
						<g:each in="${terms.agreed}">
						<div class="card">
							<div class="card-header h5 mb-0 pointer" data-toggle="collapse"  data-target="#terms${it.id}">
								<span class="text-success font-weight-bold">&#10003;</span> ${it.title}
							</div>
							<div id="terms${it.id}" class="card-body collapse" data-parent="#termsAccordion">
								<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${it}" field="contents"/></g:encodeAs>
								<br>
								<button type="button" class="btn btn-light float-right" disabled="disabled">
									<span class="text-success font-weight-bold">&#10003;</span> Agreed
								</button>
							</div>
						</div>
						</g:each>
						<g:if test="${terms.customTermsTitle && terms.customTermsContent}">
						<div class="card">
							<div class="card-header h5 mb-0 pointer" data-toggle="collapse" href="#customTerms">
								<span class="text-warning">&#9888;</span> ${terms.customTermsTitle}
							</div>
							<div id="customTerms" class="card-body collapse" data-parent="#termsAccordion">
								<g:encodeAs codec="PreserveWhitespace">${terms.customTermsContent}</g:encodeAs>
								<br>
								<button type="button" class="btn btn-primary float-right" onclick="bootbox.confirm({size: 'small', message: 'Approve these data use terms for public use?', callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="approveCustomTerms" id="${terms.datasetId}"/>');} } })">
									Approve These Terms
								</button>
							</div>
						</div>
						</g:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</g:if>