<form id="downloadForm" action="<g:createLink action='download' id='${versionInstance.id}'/>" class="form-horizontal">
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="nav-item col-6 font-weight-bold">
			<a class="nav-link active border-left-0" href="#hierarchyTab" aria-controls="hierarchyTab" role="tab" data-toggle="tab">Hierarchy</a>
		</li>
		<li role="presentation" class="nav-item col-6 font-weight-bold">
			<a class="nav-link border-right-0" href="#filesTab" aria-controls="filesTab" role="tab" data-toggle="tab" id="filesTabTab">Files</a>
		</li>
	</ul>
	
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active hierarchy overflow-auto" id="hierarchyTab">
			<div class="checkbox">
				<ul>
					<li>
						<input type="checkbox" class="indeterminate-checkbox" name="wholeDataset" value="${versionInstance.dataset.id}" id="${versionInstance.dataset.id}" dependencies="${versionInstance.files.id.join(',')}" />
						<label for="${versionInstance.dataset.id}">${versionInstance.dataset.shortTitle ?: versionInstance.dataset.title}</label>
						<ul>
							<g:each in="${versionInstance.sceneFiles()}" var="sceneFile">
							<li>
								<input type="checkbox" class="indeterminate-checkbox" name="sceneFile" value="${sceneFile.id}" id="${sceneFile.id}" dependencies="${sceneFile.id}"/>
								<label for="${sceneFile.id}">${sceneFile.label ?: sceneFile.filename}</label>
								<ul>
									<g:each in="${sceneFile.scenesSorted()}" var="scene">
									<li>
										<input type="checkbox" class="indeterminate-checkbox" name="scene" value="${scene.id}" id="${scene.id}" dependencies="${scene.dependencies().id.join(',')}" zipsize="${sceneFile.zipsize/sceneFile.scenesSorted().size()}"/>
										<label for="${scene.id}" data-toggle="popover" data-trigger="hover" data-placement="right"  data-html="true"
											data-content="<div class='scalingBox' style='width:173px;height:120px;'><img src='/scene/image/${scene?.id}'/></div>" >
											${scene.shortName ?: scene.name}
										</label>
									</li>
									</g:each>
								</ul>
							</li>
							</g:each>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="filesTab">
			<table class="table table-hover tablesorter" id="downloadFileTable" data-cssfilter='["d-none", "form-control custom-select","form-control","d-none"]' data-sort='[[0,0]]'>
				<thead>
					<tr>
						<th class="border-0 filter-false"></th>
						<th class="border-0 filter-select filter-exact" data-placeholder="All">Type&nbsp;</th>
						<th class="border-0">Name&nbsp;</th>
						<th class="border-0 filter-false">Size&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${versionInstance.files}" var="file">
					<tr>
						<td>
							<span class="d-none">1</span>
							<input type="checkbox" name="file" value="${file.id}" id="${file.id}" zipsize="${file.zipsize}" onchange="$(this).prev().html(this.checked ? '0' : '1');$('#downloadFileTable').trigger('update',[true]);estimateSize();" />
						</td>
						<td>
							${shortFileTerm('filename':file.filename)}
						</td>
						<td>
							<div class="text-truncate" style="width:700px">
								<span data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="${file.filepath.replace('/',' / ')}" data-template="<div class='popover popover-file' role='tooltip'><div class='arrow'></div><div class='popover-body popover-file'></div></div>">
									${file.filename}
								</span>
							</div>
						</td>
						<td>
							<span style="display: none">${file.filesize.toString().padLeft( 10, '0' )}</span>
							<g:displaySize bytes="${file.filesize}"/>
						</td>
					</tr>
					</g:each>
				</tbody>
				<g:render template="/templates/tableFooter" model="[cols: 4]" />
			</table>
		</div>
	</div>
	<div style="margin-top:5px;margin-left:9px;margin-bottom:-10px">
		<input type="checkbox" class="indeterminate-checkbox" name="documentation" checked dependencies="${versionInstance.documentation().id.join(',')}"/>&nbsp;&nbsp;
		<label for="documentation" style="margin-bottom:0px">Include all documentation files?</label>
	</div>
</form>