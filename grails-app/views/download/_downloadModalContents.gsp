<form id="downloadForm" action="<g:createLink action='download' id='${versionInstance.id}'/>" class="form-horizontal">
	<ul class="nav nav-tabs" role="tablist" style="height:42px;">
		<li role="presentation" class="active" style="font-weight: bold;width:50%;">
			<a href="#hierarchyTab" aria-controls="hierarchyTab" role="tab" data-toggle="tab" style="padding-left:10px;">Hierarchy</a>
		</li>
		<li role="presentation" style="font-weight: bold;width:50%;">
			<a href="#filesTab" aria-controls="filesTab" role="tab" data-toggle="tab" style="padding-left:10px;" id="filesTabTab">Files</a>
		</li>
	</ul>
	
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="hierarchyTab">
			<div style="overflow-y:scroll;height: 600px;">
				<div class="checkbox" style="padding-left:8px">
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
											<label for="${scene.id}" data-toggle="tooltip" data-html="true" data-placement="right"
												title="<div class='scalingBox' style='width:173px;height:120px;'><img src='/scene/image/${scene?.id}'/></div>" >
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
		</div>
		<div role="tabpanel" class="tab-pane" id="filesTab">
			<div style="overflow-y:scroll;height: 600px;">
				<table class="table table-striped table-bordered tablesorter" id="downloadFileTable" style="table-layout: fixed; width: 950px; margin-bottom: 0px">
					<thead>
						<tr>
							<th style="width: 30px; cursor: pointer"></th>
							<th style="width: 100px; cursor: pointer">Type&nbsp;</th>
							<th style="width: 750px; cursor: pointer">Name&nbsp;</th>
							<th style="width: 70px; cursor: pointer">Size&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${versionInstance.files}" var="file">
						<tr>
							<td>
								<input type="checkbox" name="file" value="${file.id}" id="${file.id}" zipsize="${file.zipsize}" />
							</td>
							<td>
								${shortFileTerm('filename':file.filename)}
							</td>
							<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
								<span  data-toggle="tooltip" title="${file.filepath.replace('/',' / ')}" data-placement="top" data-template="<div class='tooltip' role='tooltip'><div class='tooltip-arrow'></div><div class='tooltip-inner' style='white-space:nowrap;max-width:2000px;'></div></div>">
									${file.filename}
								</span>
							</td>
							<td>
								<span style="display: none">${file.filesize.toString().padLeft( 10, '0' )}</span>
								<g:displaySize bytes="${file.filesize}"/>
							</td>
						</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div style="margin-top:5px;margin-left:9px;margin-bottom:-10px">
		<input type="checkbox" class="indeterminate-checkbox" name="documentation" checked dependencies="${versionInstance.documentation().id.join(',')}"/>&nbsp;&nbsp;
		<label for="documentation" style="margin-bottom:0px">Include all documentation files?</label>
	</div>
</form>