<g:if test="${(item?.canView() || item?.isPublic()) && item?.hasAccess()}">
<div id="downloadModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header ${datasetTerm('item':datasetInstance)}">
				<h4 class="modal-title">Download</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<div class="btn-group btn-bar">
				<button type="button" class="btn btn-primary" onclick="presubmitCheck()">Download Selected (Est. Size: <span id="zipsize"></span>)</button>
				<button type="button" class="btn btn-light" data-toggle="modal" data-target="#downloadModal">Cancel</button>
			</div>
			<div class="modal-body pl-0 pr-0" id="downloadModalContents">
				Loading...
			</div>
			<input type="hidden" id="itemIdField" value="${item.id}">
			<input type="hidden" id="datasetIdField" value="${item.datasetId()}">
			<input type="hidden" id="datasetTermField" value="${datasetTerm('item':datasetInstance)}">
			<input type="hidden" id="versionIdField" value="${versionId}">
		</div>
	</div>
</div>
</g:if>