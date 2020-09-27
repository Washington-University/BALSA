<!DOCTYPE html>
<%@ page import="balsa.Dataset" %>
<%@ page import="balsa.curation.Issue" %>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Curation</title>
	</head>
	<body>
		<div id="show-curation-queue" role="main">
			<div class="well" style="overflow: hidden;">
				<h4>My Queue</h4>
				<table class="table table-striped table-bordered tablesorter" id="myQueueTable" style="table-layout: fixed; width: 1130px; margin-bottom: 0px">
					<thead>
						<tr>
							<th style="width: 480px; cursor: pointer">Title&nbsp;</th>
							<th style="width: 85px; cursor: pointer">Status&nbsp;</th>
							<th style="width: 130px; cursor: pointer">Creation Date&nbsp;</th>
							<th style="width: 130px; cursor: pointer">Release Date&nbsp;</th>
							<th style="width: 130px; cursor: pointer">Active Issues&nbsp;</th>
							<th style="width: 32px"></th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${myQueue}" var="queueItem">
						<g:if test="${queueItem.submittedVersion() || (queueItem.publicVersion() ?: queueItem.preprintVersion()) != queueItem.workingVersion()}">
						<tr>
							<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
								<g:link controller="${datasetTerm('item':queueItem)}" action="issues" id="${queueItem.id}">${queueItem.shortTitle ?: queueItem.title}</g:link>
								-
								<select id="selectedVersion" class="form-control" name="selectedVersion" onchange="gotoVersion(this)" style="width:130px;float:right;margin:-8px 0">
									<option value="">Versions</option>
									<g:each in="${queueItem.versions.findAll { it.submittedDate }.sort {a,b-> b.submittedDate<=>a.submittedDate}}" var="version">
									<option value="/${datasetTerm('item':queueItem)}/show/${queueItem.id}?version=${version.id}">${version.id} - ${version.submittedDate}</option>
									</g:each>
								</select>
							</td>
							<td>
								${queueItem.submittedVersion()?.status ?: queueItem.workingVersion()?.status}
							</td>
							<td>
								<g:formatDate format="yyyy-MM-dd HH:mm" date="${queueItem.submittedVersion()?.createdDate ?: queueItem.workingVersion()?.createdDate}"/>
							</td>
							<td>
								<g:formatDate format="yyyy-MM-dd HH:mm" date="${queueItem.submittedVersion()?.actualReleaseDate() ?: queueItem.workingVersion()?.actualReleaseDate()}"/>
							</td>
							<td>
								${queueItem.issues?.count({ it.status != Issue.Status.RESOLVED })}
							</td>
							<td style="text-align:center">
								<g:link action="removeFromMyQueue" id="${queueItem.id}" data-toggle="tooltip" data-placement="bottom" title="Remove from my queue"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></g:link>
							</td>
						</tr>
						</g:if>
						</g:each>
					</tbody>
				</table>
				<script>
				function gotoVersion(selectObject) {
					var url = selectObject.value;
					if (url) {
						window.location = url;
					}
					return false;
				}
				</script>
				<br>
				<h4>Main Queue</h4>
				<table class="table table-striped table-bordered tablesorter" id="mainQueueTable" style="table-layout: fixed; width: 1130px; margin-bottom: 0px">
					<thead>
						<tr>
							<th style="width: 520px; cursor: pointer">Title&nbsp;</th>
							<th style="width: 140px; cursor: pointer">Creation Date&nbsp;</th>
							<th style="width: 140px; cursor: pointer">Release Date&nbsp;</th>
							<th style="width: 140px; cursor: pointer">Active Issues&nbsp;</th>
							<th style="width: 175px; cursor: pointer">Curator&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${mainQueue}" var="queueItem">
						<tr>
							<td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
								<g:link controller="${datasetTerm('item':queueItem)}" action="issues" id="${queueItem.dataset.id}">${queueItem.dataset.shortTitle ?: queueItem.dataset.title}</g:link>
								
							</td>
							<td>
								<g:formatDate format="yyyy-MM-dd HH:mm" date="${queueItem.createdDate}"/>
							</td>
							<td>
								<g:formatDate format="yyyy-MM-dd HH:mm" date="${queueItem.actualReleaseDate()}"/>
							</td>
							<td>
								${queueItem.dataset.issues?.count({ it.status != Issue.Status.RESOLVED })}
							</td>
							<td>
								<g:if test="${queueItem.dataset.curator}">${queueItem.dataset.curator.username}</g:if>
								<g:else><g:link action="addToMyQueue" id="${queueItem.dataset.id}">Add To My Queue</g:link></g:else>
							</td>
						</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>
		<script>
		$('#myQueueTable').tablesorter({
			sortList: [[2,0]],
			headers: { 5: { sorter: false } }
		});
		$('#mainQueueTable').tablesorter({
			sortList: [[2,0]]
		});
		</script>
	</body>
</html>