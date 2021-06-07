<div id="create${datasetType}Modal" class="modal fade" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header ${datasetType.toLowerCase()}">
				<h4 class="modal-title">Create New ${datasetType}</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			
			<div class="modal-body">
				<g:form name="create${datasetType}Form" controller="${datasetType.toLowerCase()}" action="create">
					<div class="form-group row mb-0">
						<label class="col-2 col-form-label" for="name">Title</label>
						<div class="col-10">
							<input type="text" class="form-control" name="title">
						</div>
					</div>
				</g:form>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary" form="create${datasetType}Form">Create</button>
					<button class="btn btn-light" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>