<div id="create${datasetType}Modal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Create New ${datasetType}</h4>
			</div>
			<div class="modal-body">
				<g:form name="create${datasetType}Form" class="form-horizontal" controller="${datasetType.toLowerCase()}" action="create">
					<div class="form-group">
						<label class="col-sm-1 control-label" for="name">Title</label>
						<div class="col-sm-11">
							<input type="text" class="form-control" name="title">
						</div>
					</div>
				</g:form>
			</div>
			<div class="modal-footer">
				<div class="btn-group">
					<button type="submit" style="width:87px" class="btn btn-primary" form="create${datasetType}Form">Create</button>
					<button style="width:87px" class="btn btn-default" data-toggle="modal" data-target="#create${datasetType}Modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>