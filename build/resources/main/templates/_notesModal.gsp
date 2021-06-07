<div id="notesModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header ${datasetTerm('item':datasetInstance)}">
				<h4 class="modal-title">Notes</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<div class="modal-body">
				<p class="text-secondary">These notes are visible only to owners of this study and BALSA curators</p>
				<p>
					<g:form action="updateNotes" id="${datasetInstance.id}" name="notesForm" class="ajaxForm" data-success="jQuery('#notesModal').modal('hide');jQuery('#notesModalButton').text('Notes (saved)');">
						<div class="form-group mb-0">
							<g:textArea class="form-control" style="resize: none;" name="notes" value="${datasetInstance.notes}" rows="20" />
						</div>
					</g:form>
				</p>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary" form="notesForm">Update</button>
					<button class="btn btn-light" data-toggle="modal" data-target="#notesModal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
jQuery('#notesModal').on('shown.bs.modal', function () {
	jQuery.get("${createLink(action:'readNotes',id:datasetInstance.id)}");
	updateRecentActivity();
	jQuery('#notesModalButton').text('Notes');
});
</script>