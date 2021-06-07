<div id="searchControls" class="card-body" style="vertical-align:top;">
	<form onsubmit="return false;" id="searchForm">
		<div class="form-group mb-3">
			<span class="float-right ml-3 mb-1 mt-n1">
				<label class="checkbox-inline mb-0">
					<input type="checkbox" id="searchDomainReference" name="searchDomain" value="Reference" checked class="searchRefresh datasetLabels">
					<span data-toggle="popover" data-trigger="hover" data-placement="right" data-delay="400" data-content="BALSA Reference is a curated repository of multimodal reference data accurately mapped to brain atlas surfaces and volumes. It includes various types of anatomically and functionally derived spatial maps, such as cortical and subcortical parcellations, myelin maps, and retinotopic maps. It will also include connectivity data derived from retrograde tracers (macaque), tractography, and resting-state functional connectivity (human and macaque) in a well-defined spatial coordinate system for each species.">
						Search Reference
					</span>
				</label><br>
				<label class="checkbox-inline mb-0">
					<input type="checkbox" id="searchDomainStudies" name="searchDomain" value="Studies" checked class="searchRefresh datasetLabels">
					<span data-toggle="popover" data-trigger="hover" data-placement="right" data-delay="400" data-content="BALSA Studies enables sharing of extensively analyzed neuroimaging and neuroanatomical datasets associated with published studies, as voluntarily submitted by authors. It is particularly well suited for sharing of neuroimaging data as displayed in published figures, such as those in a recent human cortical parcellation study (Glasser et al., 2016).">
						Search Studies
					</span>
				</label>
			</span>
			<span style="display:block;overflow:hidden"><input class="form-control searchRefreshText" id="keyword" name="keyword" type="text" placeholder="Search"></span>
		</div>
		<ul id="searchFilters" class="list-group filterList"></ul>
		<div class="form-group">
			<label for="rows">Result Rows</label>
			<g:select class="form-control search-control searchRefresh" name="rows" from="${1..20}" value="3"/>
			&nbsp;
			<label for="columns">Result Columns</label>
			<g:select class="form-control search-control searchRefresh" name="columns" from="${2..6}" value="4"/>
			&nbsp;
			<label for="sortBy">Sort By</label>
			<g:select class="form-control search-control searchRefresh" name="sortBy" from="${['Newest','Oldest','Name']}"/>
			&nbsp;
			<label for="visibilityControl">Scene File</label>
			<g:select id="visibilityControl" class="form-control search-control" name="sceneFileVisibility" from="${['Hide', 'Show']}" onchange="changeSceneFileVisibility()"/>
			
			<button class="search-control form-control float-right" type="button" id="addFilterButton">Add Filter</button>
		</div>
	</form>
</div>
<div>
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="nav-item  w-50 font-weight-bold">
			<a href="#datasetSearchResults" class="nav-link active border-left-0" aria-controls="dataset" role="tab" data-toggle="tab">
				<span id="studiesLabel">Studies</span>
				<span id="spacerLabel"> / </span>
				<span id="referenceLabel">Reference</span>
			</a>
		</li>
		<li role="presentation" class="nav-item w-50 font-weight-bold">
			<a href="#sceneSearchResults" class="nav-link border-right-0" aria-controls="dataset" role="tab" data-toggle="tab">Scenes</a>
		</li>
	</ul>
	
	<div class="tab-content card-body">
		<div role="tabpanel" class="tab-pane active" id="datasetSearchResults"></div>
		<div role="tabpanel" class="tab-pane" id="sceneSearchResults"></div>
	</div>
</div>