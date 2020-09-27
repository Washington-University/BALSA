
<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.Version" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${datasetType} Index</title>
	</head>
	<body>
		<div role="main">
			<h3>${datasetType} Index</h3>
			
			<div class="well ${datasetType.toLowerCase()}" style="overflow: hidden;">
				<table class="table table-striped table-bordered tablesorter" id="datasetTable" style="table-layout: fixed; margin-bottom: 0px">
					<thead>
						<tr>
							<th style="border-top:none">
								Title
							</th>
							<th style="width: 100px; cursor: pointer">
								Species
							</th>
							<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
							<th style="width: 150px; cursor: pointer">
								Most Recent Status
							</th>
							</sec:ifAnyGranted>
							<g:if test="${datasetType=='Study'}">
							<th style="width: 80px; cursor: pointer">
								PMID
							</th>
							<th style="width: 250px; cursor: pointer">
								DOI
							</th>
							<th style="width: 100px; cursor: pointer">
								Owners
							</th>
							<th style="width: 170px; cursor: pointer" data-sorter="false">
								Other Versions
							</th>
							</g:if>
						</tr>
					</thead>
					
					<tbody>
						<g:each in="${datasetInstanceList}" var="datasetInstance">
						<tr>
							<td style="border-top:1px solid rgb(190,192,218)">
								<g:link action="show" id="${datasetInstance.id}">${datasetInstance.shortTitle ?: datasetInstance.title}</g:link>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								${datasetInstance.species*.name.join(', ')}
							</td>
							<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
							<td style="border-top:1px solid rgb(190,192,218)">
								${datasetInstance.workingVersion().status}
							</td>
							</sec:ifAnyGranted>
							<g:if test="${datasetType=='Study'}">
							<td style="border-top:1px solid rgb(190,192,218)">
								<a href="https://www.ncbi.nlm.nih.gov/pubmed/${datasetInstance.publicVersion()?.pmid}" target="_blank">${datasetInstance.publicVersion()?.pmid}</a>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<a href="https://doi.org/${datasetInstance.publicVersion()?.doi}" target="_blank">${datasetInstance.publicVersion()?.doi}</a>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<a class="balsaPopover" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" data-placement="left" data-content="${datasetInstance.owners*.username.sort().join(', ')}">click to view</a>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<g:if test="${datasetInstance.publicVersion() && (datasetInstance.workingVersion() != datasetInstance.publicVersion())}">
									<g:link action="show" id="${datasetInstance.id}" params="[version: 'public']" style="display: block">PUBLIC</g:link>
								</g:if>
								<g:if test="${datasetInstance.preprintVersion() && (datasetInstance.workingVersion() != datasetInstance.preprintVersion())}">
									<g:link action="show" id="${datasetInstance.id}" params="[version: 'preprint']" style="display: block">PREPRINT</g:link>
								</g:if>
								
								<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_CURATOR'>
								<g:if test="${datasetInstance.submittedVersion() && (datasetInstance.workingVersion() != datasetInstance.submittedVersion())}">
									<g:link action="show" id="${datasetInstance.id}" params="[version: 'submitted']" style="display: block">SUBMITTED</g:link>
								</g:if>
								<g:if test="${datasetInstance.approvedVersion() && (datasetInstance.workingVersion() != datasetInstance.approvedVersion())}">
									<g:link action="show" id="${datasetInstance.id}" params="[version: 'approved']" style="display: block">APPROVED</g:link>
								</g:if>
								<select class="form-control" id="selectedVersion" name="selectedVersion" onchange="gotoVersion(this)">
									<option value="">ALL VERSIONS</option>
									<g:each in="${datasetInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}}" var="version">
									<option value="/${datasetType.toLowerCase()}/show/${datasetInstance.id}?version=${version.id}">${version.status == Version.Status.PUBLIC && version.preprint ? 'PREPRINT' : version.status} - ${version.updatedDate}</option>
									</g:each>
								</select>
								</sec:ifAnyGranted>
							</td>
							</g:if>
						</tr>
						</g:each>
					</tbody>
				</table>
				<div id="pager" class="pager" style="text-align:left;margin:15px 0 0 0">
					<form>
						<div class="first" style="color: grey;display:inline-block"><span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span></div>
						<div class="prev" style="color: grey;display:inline-block"><span class="glyphicon glyphicon-backward" aria-hidden="true"></span></div>
						<input type="text" class="pagedisplay" style="width:50px;text-align:center">
						<div class="next" style="color: grey;display:inline-block"><span class="glyphicon glyphicon-forward" aria-hidden="true"></span></div>
						<div class="last" style="color: grey;display:inline-block"><span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span></div>
						<select class="pagesize">
							<option selected="selected" value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
						</select>
					</form>
				</div>
			</div>
		</div>
		<script>
		$('.balsaPopover').popover();
		$('#datasetTable').tablesorter({
			sortList: [[0,0]],
			<g:if test="${datasetType=='Study'}">
			headers: { 4: { sorter: false }, 6: { sorter: false } }
			</g:if>
		}).tablesorterPager({container: $("#pager")});

		function gotoVersion(selectObject) {
			var url = selectObject.value;
			if (url) {
				window.location = url;
			}
			return false;
		}
		</script>
	</body>
</html>
