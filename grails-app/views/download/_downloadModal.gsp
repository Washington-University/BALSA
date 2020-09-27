<g:if test="${(item?.canView() || item?.isPublic()) && item?.hasAccess()}">

<div id="downloadModal" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width:1000px">
		<div class="modal-content">
			<div class="panel-heading" style="border-top-left-radius:6px;border-top-right-radius:6px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Download</h4>
			</div>
			<div class="modal-body" id="downloadModalContents">
				Loading...
			</div>
			<div class="modal-footer">				
				<div class="pull-left" style="font-size: 1.4em;line-height:1.4em">
					Estimated Download Size: <span id="zipsize"></span>
				</div>
				<div class="btn-group">
					<button type="button" class="btn btn-primary" onclick="presubmitCheck()">Download Selected</button>
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#downloadModal" style="width:87px">Cancel</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$.get("${createLink(controller: 'download', action: 'downloadModalContents', id: item?.datasetId(), params: [version: versionId])}", function(data, status){
			$('#downloadModalContents').html(data);

			// turn on indeterminate checkboxes and set up triggers
			IndeterminateCheckbox.init();
			$('input[type="checkbox"].indeterminate-checkbox').change(function() {
				checkFiles();
			});
			$('input[name=file]').change(function() {
				estimateSize();
			});
			
			// check initial item and all item descendents and dependent files
			var initialId = '${item.id}';
			$('#' + initialId).prop('checked', true);
			$('#' + initialId).trigger('change');

			// initialize table sorter
			$('#downloadFileTable').tablesorter({
				headers: { 0: {sorter: 'checkbox'}},
				sortList: [[0,1],[1,0],[2,0]]
			});
			
			// initialize tooltips
			$('[data-toggle="tooltip"]').tooltip();

			// if initial item is file rather than hierarchy, change tabs
			if ($('#' + initialId).attr("class") != 'indeterminate-checkbox') {
				$('#filesTabTab').tab('show');
			}
		});
		updateRecentActivity();
	});

	$.tablesorter.addParser({ 
        id: "checkbox", 
        is: function(s) { 
            return false; 
        }, 
        format: function(s, t, node) {
            return $(node).children("input[type=checkbox]").is(':checked') ? 1 : 0;
        }, 
        type: "numeric" 
    });

	function checkFiles() {
		var checkboxes = $("input:checked[class=indeterminate-checkbox]");
		var filesToCheck = ",";
		checkboxes.each(function() {
			filesToCheck += $(this).attr('dependencies') + ",";
		})
		
		var files = $("input[name=file]");
		files.each(function() {
			if (filesToCheck.includes(',' + this.id + ',')) {
				$(this).prop('checked', true);
			}
			else {
				$(this).prop('checked', false);
			}
		})
		
		$('#downloadFileTable').trigger('update');
		setTimeout(function(){ $('#downloadFileTable').trigger( 'sorton', [ [[0,1],[1,0],[2,0]] ] ) },10);
		
		estimateSize();
	}

	function estimateSize() {
		var totalSize = 0;
		var scenes = $("input:checked[name=scene]");
		scenes.each(function() {
			totalSize += parseInt($(this).attr('zipsize'), 10);
		})
		var files = $("input:checked[name=file]");
		files.each(function() {
			totalSize += parseInt($(this).attr('zipsize'), 10);
		})
		$('#zipsize').text(formatBytes(totalSize));
	}

	function formatBytes(bytes) {
	    if(bytes < 1024) return bytes + " bytes";
	    else if(bytes < 1048576) return(bytes / 1024).toFixed(0) + " KB";
	    else if(bytes < 1073741824) return(bytes / 1048576).toFixed(1) + " MB";
	    else return(bytes / 1073741824).toFixed(2) + " GB";
	}

	function presubmitCheck() {
		var checkboxes = $("input:checked[class=indeterminate-checkbox]");
		var filesToCheck = ",";
		checkboxes.each(function() {
			filesToCheck += $(this).attr('dependencies') + ",";
		})

		var allSupportingFiles = true;
		var files = $("input[name=file]");
		files.each(function() {
			if (filesToCheck.includes(',' + this.id + ',')) {
				if (!$(this).prop('checked')) {
					allSupportingFiles = false;
				}
			}
		});

		if (!allSupportingFiles) {
			bootbox.confirm("The items you have selected for download do not include all of the files marked as required by the scenes you have selected. This could cause problems with displaying the selected scenes. Do you wish to proceed?",
				function (result) {
					if (result) {
						$('#downloadForm').submit();
						$('#downloadModal').modal('hide');
					}
				}
			);
		}
		else {
			$('#downloadForm').submit();
			$('#downloadModal').modal('hide');
		}
	}
</script>
</g:if>