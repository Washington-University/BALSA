<div id="fileModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-wide">
		<div class="modal-content">
			<div class="modal-header ${datasetTerm('item':datasetInstance)}">
				<h4 class="modal-title">Files</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>
			<div class="modal-body p-0">
				<g:if test="${versionInstance && datasetInstance.canEdit()}">
				<div class="btn-group btn-bar">
					<g:link class="btn btn-primary" action="upload" id="${datasetInstance.id}">Upload Files</g:link>

					<button type="button" class="btn btn-light" onclick="$('.unusedFile').each(function() { $(this).prop('checked', true).triggerHandler('change'); })">Check All Unused Files</button>

					<button type="button" class="btn btn-light" onclick="bootbox.confirm({size: 'small', message: 
					'${ 'Remove selected files from this ' + datasetTerm('item':datasetInstance) + '?' + 
						((versionInstance.isPublic() || versionInstance.isApproved()) ? ' Removing files creates a new working version of the dataset, but the files will remain in the current public version.' : '') }', 
						callback: function(result) {if (result) { jQuery('#removeFilesForm').submit(); } } })">
						Remove Checked Files
					</button>
				</div>
				</g:if>
				
				
				<g:form name="removeFilesForm" action="removeFiles" id="${datasetInstance.id}">
					<g:if test="${versionInstance && datasetInstance.canEdit()}">
					<table id="fileModalTable" class="table table-hover tablesorter filterer pager mb-0" data-cssfilter='["d-none","form-control custom-select","form-control","d-none","d-none"]' data-sort='[[1,0]]'>
					</g:if>
					<g:else>
					<table id="fileModalTable" class="table table-hover tablesorter filterer pager mb-0" data-cssfilter='["form-control custom-select","form-control","form-control"]' data-sort='[[0,0]]'>
					</g:else> 
						<thead>
							<tr>
								<g:if test="${versionInstance && datasetInstance.canEdit()}">
									<th class="border-0 filter-false" style="font-size:1.5rem">&#9745;</th>
								</g:if>
								<th class="border-0 filter-select filter-exact" data-placeholder="All">Type&nbsp;</th>
								<th class="border-0">Name&nbsp;</th>
								<th class="border-0 filter-false">Size&nbsp;</th>
								<g:if test="${versionInstance && datasetInstance.canEdit()}">
								<th class="border-0 filter-false">Used&nbsp;</th>
								</g:if>
							</tr>
						</thead>
						<tbody>
							<g:each in="${files}" var="file">
							<tr>
								<g:if test="${versionInstance && datasetInstance.canEdit()}">
								<td>
									<span class="d-none">1</span>
									<input <g:if test="${!(file instanceof balsa.file.SceneFile || file instanceof balsa.file.Documentation || versionInstance.isDependentFile(file))}">class="unusedFile"</g:if>type="checkbox" name="fileToRemove" value="${file.id}" onchange="$(this).prev().html(this.checked ? '0' : '1');$('#fileModalTable').trigger('update',[true]);" />
								</td>
								</g:if>
								<td>
									${shortFileTerm('filename':file.filename)}
								</td>
								<td>
									<g:link class="d-inline-block text-truncate" style="width:500px" controller="file" action="show" id="${file.id}">
										<span  data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="${file.filepath.replace('/',' / ')}" data-placement="right" data-template="<div class='popover popover-file' role='tooltip'><div class='arrow'></div><div class='popover-body popover-file' style='white-space:nowrap;max-width:2000px;'></div></div>">
											${file.filename}
										</span>
									</g:link>
								</td>
								<td>
									<span style="display: none">${file.filesize.toString().padLeft( 10, '0' )}</span>
									<g:displaySize bytes="${file.filesize}"/>
								</td>
								<g:if test="${versionInstance && datasetInstance.canEdit()}">
								<td class="text-center">
									<g:if test="${file instanceof balsa.file.SceneFile || file instanceof balsa.file.Documentation || versionInstance.isDependentFile(file)}">
									<span class="d-none">1</span>
									<span class="text-success font-weight-bold">&#10003;</span>
									</g:if>
									<g:else>
									<span class="d-none">0</span>
									<span class="text-danger" aria-hidden="true">&times;</span>
									</g:else>
								</td>
								</g:if>
							</tr>
							</g:each>
						</tbody>
						<g:if test="${versionInstance && datasetInstance.canEdit()}">
							<g:render template="/templates/tableFooter" model="[cols: 5]" />
						</g:if>
						<g:else>
							<g:render template="/templates/tableFooter" model="[cols: 3]" />
						</g:else>
					</table>
				</g:form>
			</div>
		</div>
	</div>
</div>