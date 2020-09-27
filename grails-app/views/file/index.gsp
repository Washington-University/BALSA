
<%@ page import="balsa.file.FileMetadata" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Files</title>
	</head>
	<body>
		<div id="list-fileMetadata" role="main">
			<h3>Files</h3>
			
			<div class="well" style="overflow: hidden;">
				<table class="table table-hover">
					<tr>
						<th style="border-top:none">
							File Name
						</th>
						<th style="border-top:none">
							Dataset
						</th>
						<th style="border-top:none">
							Added
						</th>
						<th style="border-top:none">
							Size
						</th>
					</tr>
					
					<g:each in="${fileList}" var="file">
					<tr>
						<td>
							<g:link action="show" id="${file.id}">${file.filename}</g:link>
						</td>
						<td>
							<g:link action="show" controller="${datasetTerm('item':file)}" id="${file.dataset.id}">${file.dataset.shortTitle ?: file.dataset.title}</g:link>
						</td>
						<td>
							<g:formatDate format="yyyy-MM-dd" date="${file.added}"/>
						</td>
						<td>
							<g:displaySize bytes="${file.filesize}"/>
						</td>
					</tr>
					</g:each>
				</table>
				
				<g:if test="${fileCount > 10}">
				<div style="text-align:center">
					<g:paginate omitPrev="true" omitNext="true" total="${fileCount ?: 0}"/>
				</div>
				</g:if>
			</div>
		</div>
	</body>
</html>
