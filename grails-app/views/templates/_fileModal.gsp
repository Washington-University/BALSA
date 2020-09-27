<div id="fileModal" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width:1000px">
		<div class="modal-content">
			<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Files</h4>
			</div>
			<div class="modal-body">
				<div style="overflow-y:scroll;height: 600px;">
					<g:form name="removeFilesForm" action="removeFiles" id="${datasetInstance.id}">
					<table class="table table-striped table-bordered tablesorter" id="fileTable" style="table-layout: fixed; width: 950px; margin-bottom: 0px">
						<thead>
							<tr>
								<g:if test="${datasetInstance.canEdit()}">
								<th style="width: 30px; cursor: pointer"></th>
								</g:if>
								<th style="width: 100px; cursor: pointer">Type&nbsp;</th>
								<th style="width: 100%; max-width: 780px; cursor: pointer">Name&nbsp;</th>
								<th style="width: 70px; cursor: pointer">Size&nbsp;</th>
								<g:if test="${datasetInstance.canEdit()}">
								<th style="width: 70px; cursor: pointer">Used&nbsp;</th>
								</g:if>
							</tr>
						</thead>
						<tbody>
							<g:each in="${files}" var="file">
							<tr>
								<g:if test="${datasetInstance.canEdit()}">
								<td>
									<input <g:if test="${!(file instanceof balsa.file.SceneFile || file instanceof balsa.file.Documentation || versionInstance.isDependentFile(file))}">class="unusedFile"</g:if>type="checkbox" id="fileToRemove" name="fileToRemove" value="${file.id}" />
								</td>
								</g:if>
								<td>
									${shortFileTerm('filename':file.filename)}
								</td>
								<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
									<span  data-toggle="tooltip" title="${file.filepath.replace('/',' / ')}" data-placement="top" data-template="<div class='tooltip' role='tooltip'><div class='tooltip-arrow'></div><div class='tooltip-inner' style='white-space:nowrap;max-width:2000px;'></div></div>">
										<g:link controller="file" action="show" id="${file.id}">${file.filename}</g:link>
									</span>
								</td>
								<td>
									<span style="display: none">${file.filesize.toString().padLeft( 10, '0' )}</span>
									<g:displaySize bytes="${file.filesize}"/>
								</td>
								<g:if test="${datasetInstance.canEdit()}">
								<td style="text-align:center">
									<g:if test="${file instanceof balsa.file.SceneFile || file instanceof balsa.file.Documentation || versionInstance.isDependentFile(file)}">
									<span style="display: none">1</span>
									<span class="glyphicon glyphicon-ok" style="color:green"></span>
									</g:if>
									<g:else>
									<span style="display: none">0</span>
									<span class="glyphicon glyphicon-remove" style="color:red"></span>
									</g:else>
								</td>
								</g:if>
							</tr>
							</g:each>
						</tbody>
					</table>
					</g:form>
				</div>
				<g:if test="${datasetInstance.canEdit()}">
					<div style="display:table;margin-top:15px;width:100%">
						<div class="btn-group pull-left">
							<button type="button" class="btn btn-default" onclick="selectAllUnused()">Check All Unused Files</button>
						</div>
						<div class="btn-group pull-right">
							<button type="button" class="btn btn-default" onclick="bootbox.confirm({size: 'small', message: 
							'${ 'Remove selected files from this ' + datasetTerm('item':datasetInstance) + '?' + 
								((versionInstance.isPublic() || versionInstance.isApproved()) ? ' Removing files creates a new working version of the dataset, but the files will remain in the current public version.' : '') }', 
								callback: function(result) {if (result) { jQuery('#removeFilesForm').submit(); } } })">
								Remove Checked Files
							</button>
							<g:link class="btn btn-default" action="upload" id="${datasetInstance.id}">Upload Files</g:link>
						</div>
					</div>
				</g:if>
			</div>
		</div>
	</div>
</div>

<script>
$('#fileTable').tablesorter({
	sortList: [[0,0]]
});

function selectAllUnused() {
	$(".unusedFile").each(function() { $(this).prop('checked', true); });
}
</script>