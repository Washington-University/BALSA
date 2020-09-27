<%@ page import="balsa.security.Approval" %>
<%@ page import="balsa.Dataset" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>My Studies</title>
	</head>
	<body>
		<div id="list-study" role="main">
			<h3>My Studies</h3>
			
			<div class="well study" style="overflow: hidden;">
				<table class="table table-striped table-bordered tablesorter" id="myStudiesTable" style="table-layout: fixed; margin-bottom: 0px">
					<thead>
						<tr>
							<th style="cursor: pointer">
								Title
							</th>
							<th style="width: 170px; cursor: pointer">
								Most Recent Status
							</th>
							<th style="width: 170px; cursor: pointer" data-sorter="false">
								Other Versions
							</th>
							<th style="width: 120px; cursor: pointer" data-sorter="false">
								Co-owners
							</th>
							<th style="width: 70px; cursor: pointer">
								Delete
							</th>
						</tr>
					</thead>
					
					<tbody>
						<g:each in="${studyInstanceList}" var="studyInstance">
						<tr>
							<td style="border-top:1px solid rgb(190,192,218)">
								<g:link action="show" id="${studyInstance.id}">${studyInstance.shortTitle ?: studyInstance.title}</g:link>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								${studyInstance.workingVersion().status}
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<g:if test="${studyInstance.publicVersion() && (studyInstance.workingVersion() != studyInstance.publicVersion())}">
									<g:link action="show" id="${studyInstance.id}" params="[version: 'public']" style="display: block">PUBLIC</g:link>
								</g:if>
								<g:if test="${studyInstance.preprintVersion() && (studyInstance.workingVersion() != studyInstance.preprintVersion())}">
									<g:link action="show" id="${studyInstance.id}" params="[version: 'preprint']" style="display: block">PREPRINT</g:link>
								</g:if>
								<g:if test="${studyInstance.submittedVersion() && (studyInstance.workingVersion() != studyInstance.submittedVersion())}">
									<g:link action="show" id="${studyInstance.id}" params="[version: 'submitted']" style="display: block">SUBMITTED</g:link>
								</g:if>
								<g:if test="${studyInstance.approvedVersion() && (studyInstance.workingVersion() != studyInstance.approvedVersion())}">
									<g:link action="show" id="${studyInstance.id}" params="[version: 'approved']" style="display: block">APPROVED</g:link>
								</g:if>
								<select id="selectedVersion" class="form-control" name="selectedVersion" onchange="gotoVersion(this)">
									<option value="">ALL VERSIONS</option>
									<g:each in="${studyInstance.versions.sort {a,b-> b.updatedDate<=>a.updatedDate}}" var="version">
									<option value="/study/show/${studyInstance.id}?version=${version.id}">${version.status} - ${version.updatedDate}</option>
									</g:each>
								</select>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<a class="balsaPopover" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" data-placement="left" data-content="${studyInstance.owners*.username.sort().join(', ')}">click to view</a>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<a style="cursor:pointer;" onclick="bootbox.confirm({size: 'small', message: 'Delete this study?', 
									callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="delete" id="${studyInstance.id}"/>');} } })">
									Delete
								</a>
							</td>
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
		$('#myStudiesTable').tablesorter({
			sortList: [[0,0]],
			headers: { 4: { sorter: false }, 5: { sorter: false } }
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