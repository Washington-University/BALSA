<%@ page import="balsa.security.Approval" %>
<%@ page import="balsa.Dataset" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Data Access Terms</title>
	</head>
	<body>
		<div id="list-study" role="main">
			<h3>Data Access Terms</h3>
			
			<div class="btn-group">
				<g:link class="btn btn-default" controller="profile" action="mine">Profile</g:link>
				<g:link class="btn btn-default" controller="download" action="mine">Download History</g:link>
			</div>
			
			<br><br>
			<div class="well" style="overflow: hidden;">
				<h4 style="margin-top:0">Agreed Data Access Terms:</h4>
				<div class="panel-group" id="termsAccordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px">
				<g:each in="${terms}">
					<div class="panel panel-default">
						<div class="panel-heading" style="cursor:pointer" role="tab" id="${it.id}Heading" data-toggle="collapse" data-parent="#termsAccordion" href="#${it.id}terms" aria-expanded="true" aria-controls="details">
							<h4 class="panel-title"><span class="glyphicon glyphicon-ok" style="color:green"></span> ${it.title}</h4>
						</div>
						<div id="${it.id}terms" class="panel-collapse collapse" role="tabpanel" aria-labelledby="${it.id}Heading">
							<div class="panel-body" style="overflow: auto">
								<div  id="${it.id}termsContents">
								<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${it}" field="contents"/></g:encodeAs>
								</div>
								<input class="btn btn-primary pull-right" type="button" onclick="printDiv('${it.id}termsContents')" value="Print" />
							</div>
						</div>
					</div>
				</g:each>
				</div>				
			</div>
		</div>
		<script>
		function printDiv(divName) {
		     w=window.open();
		     w.document.write($('#'+divName).html());
		     w.print();
		     w.close();
		}
		</script>
	</body>
</html>