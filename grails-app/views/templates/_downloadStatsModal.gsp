<div id="downloadStatsModal" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width:1000px">
		<div class="modal-content">
			<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Download Statistics</h4>
			</div>
			<div class="modal-body" id="downloadStatsModalContents">
				Loading...
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
		$.get("${createLink(action: 'downloadStats', id: dataset.id)}", function(data, status){
			$('#downloadStatsModalContents').html(data);
		});
		updateRecentActivity();
	});
	</script>
</div>