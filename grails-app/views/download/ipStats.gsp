<%@ page import="balsa.security.Approval" %>
<%@ page import="balsa.Dataset" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Downloads By IP</title>
	</head>
	<body>
		<div id="list-study" role="main">
			<h3>Downloads By IP</h3>
			
			<div class="btn-group">
				<g:link class="btn btn-default" action="stats">Download Stats</g:link>
			</div>
			
			<br><br>
			<div class="well" style="overflow: hidden;">
				<table class="table table-striped table-bordered" id="myStudiesTable" style="table-layout: fixed; margin-bottom: 0px">
					<thead>
						<tr>
							<th>
								IP
							</th>
							<th>
								Downloads
							</th>
							<th>
								Purge
							</th>
						</tr>
					</thead>
					
					<tbody>
						<g:each in="${results}" var="item">
						<tr>
							<td style="border-top:1px solid rgb(190,192,218)">
								<a href="https://whatismyipaddress.com/ip/${item[0]}">${item[0]}</a>
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								${item[1]}
							</td>
							<td style="border-top:1px solid rgb(190,192,218)">
								<a style="cursor:pointer;" onclick="bootbox.confirm({size: 'small', message: 'Purge all download records with this IP?', 
									callback: function(result) {if (result) {$(location).attr('href','<g:createLink action="purgeByIp" params="[ipAddress: item[0]]"/>');} } })">
									Purge
								</a>
							</td>
						</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>