<div id="searchControls" class="panel" style="vertical-align:top;">
	<form onsubmit="return false;" id="searchForm">
		<div class="form-group" style="margin-bottom:10px">
			<span style="float:right;margin-left:10px;margin-top:-5px">
				<label class="checkbox-inline">
					<input type="checkbox" id="searchDomainReference" name="searchDomain" value="Reference" checked onchange="refreshSearchResults();adjustDatasetTabLabels();">
					<span data-toggle="tooltip" title="BALSA Reference is a curated repository of multimodal reference data accurately mapped to brain atlas surfaces and volumes. It includes various types of anatomically and functionally derived spatial maps, such as cortical and subcortical parcellations, myelin maps, and retinotopic maps. It will also include connectivity data derived from retrograde tracers (macaque), tractography, and resting-state functional connectivity (human and macaque) in a well-defined spatial coordinate system for each species.">
						Search Reference
					</span>
				</label><br>
				<label class="checkbox-inline">
					<input type="checkbox" id="searchDomainStudies" name="searchDomain" value="Studies" checked onchange="refreshSearchResults();adjustDatasetTabLabels();">
					<span data-toggle="tooltip" title="BALSA Studies enables sharing of extensively analyzed neuroimaging and neuroanatomical datasets associated with published studies, as voluntarily submitted by authors. It is particularly well suited for sharing of neuroimaging data as displayed in published figures, such as those in a recent human cortical parcellation study (Glasser et al., 2016).">
						Search Studies
					</span>
				</label>
			</span>
			<span style="display:block;overflow:hidden"><input class="form-control" id="keyword" name="keyword" type="text" placeholder="Search by keyword..."></span>
		</div>
		<div id="searchFilters"></div>
		<div class="form-group">
			<label for="rows">Result Rows</label>
			<g:select class="form-control" style="margin-left:5px;display:inline-block;width:auto" name="rows" from="${1..20}" value="2" onchange="refreshSearchResults()"/>
			&nbsp;
			<label for="columns">Result Columns</label>
			<g:select class="form-control" style="margin-left:5px;display:inline-block;width:auto" name="columns" from="${1..4}" value="3" onchange="refreshSearchResults()"/>
			&nbsp;
			<label for="sortBy">Sort By</label>
			<g:select class="form-control" style="margin-left:5px;display:inline-block;width:auto" name="sortBy" from="${['Newest','Oldest','Name']}" onchange="refreshSearchResults()"/>
			&nbsp;
			<label>Scene File</label>
			<g:select id="visibilityControl" class="form-control" style="margin-left:5px;display:inline-block;width:auto" name="sceneFileVisibility" from="${['Hide', 'Show']}" onchange="changeSceneFileVisibility()"/>
			
			<button style="float:right;height:100%;" class="btn btn-default" type="button" onclick="jQuery.get('/search/searchFilter', function(data) {jQuery('#searchFilters').append(data);});updateRecentActivity();">Add Filter</button>
		</div>
	</form>
</div>
<div>
	<ul class="nav nav-tabs" role="tablist" style="height:42px;">
		<li role="presentation" class="active" style="font-weight: bold;width:50%;">
			<a href="#datasetSearchResults" aria-controls="dataset" role="tab" data-toggle="tab">
				<span id="studiesLabel">Studies</span>
				<span id="spacerLabel"> / </span>
				<span id="referenceLabel">Reference</span>
			</a>
		</li>
		<li role="presentation" style="font-weight: bold;width:50%;">
			<a href="#sceneSearchResults" aria-controls="dataset" role="tab" data-toggle="tab">Scenes</a>
		</li>
	</ul>
	
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="datasetSearchResults"></div>
		<div role="tabpanel" class="tab-pane" id="sceneSearchResults"></div>
	</div>
</div>

<script>
$( document ).ready(function(){
	refreshSearchResults();
});

var timeout = null;
$('#keyword').keydown(function() {
	clearTimeout(timeout);
	timeout = setTimeout(function () {refreshSearchResults();}, 750);
});

function refreshSearchResults() {
	jQuery.ajax({type:'POST',
		data:jQuery('#searchForm').serialize(), 
		url:'search/datasetSearch',
		success:function(data,textStatus){$('#datasetSearchResults').html(data);},
		error:function(XMLHttpRequest,textStatus,errorThrown){}});
	jQuery.ajax({type:'POST',
		data:jQuery('#searchForm').serialize(), 
		url:'search/sceneSearch',
		success:function(data,textStatus){$('#sceneSearchResults').html(data);},
		error:function(XMLHttpRequest,textStatus,errorThrown){}});
	return false;
}

function adjustDatasetTabLabels() {
	var studiesChecked = $('#searchDomainStudies').is(':checked');
	var referenceChecked = $('#searchDomainReference').is(':checked');
	if (studiesChecked) {
		$('#studiesLabel').show();
	}
	else {
		$('#studiesLabel').hide()
	}
	
	if (referenceChecked) {
		$('#referenceLabel').show();
	}
	else {
		$('#referenceLabel').hide()
	}
	
	if (studiesChecked && referenceChecked) {
		$('#spacerLabel').show()
	}
	else {
		$('#spacerLabel').hide()
	}
}



function changeSceneFileVisibility() {
	var value = $('#visibilityControl').val();	
	if (value=="Hide") {
		$('.sceneFileAbove').hide();
	}
	if (value=="Show") {
		$('.sceneFileAbove').show();
	}
}
</script>