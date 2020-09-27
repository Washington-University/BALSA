<%@ page import="balsa.security.Approval" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>${termsInstance.title}</title>
	</head>
	<body>
		<div role="main">
			<h3>${termsInstance.title}</h3>
			<div class="well" style="overflow: hidden;">
				<div id="termsContents">
				<g:encodeAs codec="PreserveWhitespace"><g:fieldValue bean="${termsInstance}" field="contents"/></g:encodeAs>
				</div>
			<input class="btn btn-primary pull-right" type="button" onclick="printDiv('termsContents')" value="Print" />
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