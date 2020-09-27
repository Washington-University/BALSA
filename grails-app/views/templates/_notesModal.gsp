<div id="notesModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Notes</h4>
			</div>
			<div class="modal-body">
				<span style="color:gray">These notes are visible only to other owners of this study and BALSA curators</span>
				<g:formRemote url="[action: 'updateNotes', id: datasetInstance.id]" name="notesForm" class="form-horizontal" action="updateNotes" id="notesForm" onSuccess="jQuery('#notesModal').modal('hide');jQuery('#notesModalButton').text('Notes (saved)');updateRecentActivity();">
					<div class="form-group" style="margin-bottom:0">
						<div class="col-sm-12">
							<g:textArea class="form-control" style="resize: none;" name="notes" value="${datasetInstance.notes}" rows="20" />
						</div>
					</div>
				</g:formRemote>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<button type="submit" style="width:87px" class="btn btn-primary" form="notesForm">Update</button>
					<button style="width:87px" class="btn btn-default" data-toggle="modal" data-target="#notesModal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$('#notesModal').on('shown.bs.modal', function () {
	$.get("${createLink(action:'readNotes',id:datasetInstance.id)}");
	updateRecentActivity();
	$('#notesModalButton').text('Notes');
});
</script>