<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>BALSA File Types</title>
	</head>
	<body>		
		<div role="main">
			<div class="card overflow-hidden">
				<div class="card-header h3 mb-0">BALSA File Types</div>
				<div class="btn-group btn-bar">
					<a class="btn btn-light" href="/">Main</a>
					<g:link class="btn btn-light" action="index">About BALSA</g:link>
					<g:link class="btn btn-light" action="submission">Data Submission</g:link>
					<g:link class="btn btn-light" action="editing">Editing a Study</g:link>
				</div>
				<div class="card-body">
					<p>
						BALSA supports a variety of file types used by Connectome Workbench. One category 
						includes several standardized (community-endorsed) formats: NIFTI (volumetric), GIFTI
						(surface), and CIFTI (‘grayordinate’ surface + volume) types. Another category includes
						various formats customized for Workbench, but not generally readable by other
						platforms.
					</p>
					<br>

					<h4>Standard (multi-platform) files:</h4>

					<h5>NIfTI Volume Format (*.nii)</h5><a name="NIFTI"></a>
					<p>
						NIFTI volume files consist of a rectangular 3D grid of voxels, and often a 4th dimension of 
						timepoints or maps. Connectome Workbench only supports volume files in NIfTI format, but 
						accepts volumes in NIfTI-2 format as well as NIfTI-1, as NIfTI-2 avoids the dimension size 
						limit of 32,767 in NIfTI-1.
					</p>
					<p>
						Workbench can also create and view volume files that contain label data instead of continuous 
						values (see gifti label files below).  Other software can read the data values in these label 
						volume files, but will display them like any other type of data value, ignoring the area names 
						and colors.
					</p>
					<p>
						For further details of the NIFTI format, see
						<a href="https://nifti.nimh.nih.gov/">http://nifti.nimh.nih.gov/</a>
					</p>
					<br>

					<h5>GIFTI format (*.gii)</h5><a name="GIFTI"></a>
					<p>
						GIFTI is an established data file format designed for surface-based data. Its three major 
						subtypes represent surface geometry (vertices), continuous values for each vertex, and integer 
						label data for each vertex.
					</p>
					<p>
						<ul>
							<li>
								<b>Surface geometry files (*.surf.gii)</b> specify 3D vertex coordinates and their topological 
								relationships (a triangular tessellation). GIFTI files that specify data rather than geometry 
								consist mainly of a 2D array, with one dimension of length equal to the number of vertices in 
								the surface, and the other dimension representing the number of ‘maps’ that can be selected 
								for display.
							</li>
							<li>
								<b>Metric files (*.func.gii, *.shape.gii)</b> encode continuous values for each vertex. 
								The file format is identical for .func.gii and .shape.gii files; the use of one extension 
								or the other is purely by convention (e.g., fMRI data for *.func.gii, cortical folding 
								for *.shape.gii).
							</li>
							<li>
								<b>Label files (*.label.gii)</b> encode integer values for each vertex.  Additionally, the 
								file contains a table of a name and a color for each value, known as a ‘label table’ (e.g. 
								{(5, 'V1', <red>), (7, 'V2', <green>), ...}).
							</li>
						</ul>
					</p>
					<p>
						Note that some software platforms may put data arrays (e.g., the equivalent of a metric file) into the 
						same file as the geometry information. Workbench does not support this kind of combined format; you 
						must use other tools to separate the data array from the geometry (e.g., mris_convert in FreeSurfer).
					</p>
					<p>
						For further details of the GIFTI format, see
						<a href="http://www.nitrc.org/projects/gifti/">http://www.nitrc.org/projects/gifti/</a>
					</p>
					<br>

					<h5>CIFTI format (*.nii)</h5><a name="CIFTI"></a>
					<p>
						CIFTI files are designed to handle data contained in multiple disjoint anatomical structures, 
						such as both hemispheres of cerebral cortex (vertex-based surface data) and/or specific 
						subcortical structures (gray matter voxels). Structures not of interest for the analysis at 
						hand can be excluded (e.g., white matter, CSF, “medial wall”), thereby making the representation 
						more compact. The vertices and voxels used in a CIFTI file are generically referred to as 
						'brainordinates' and as 'grayordinates' for the specific case of 'all gray matter locations'. 
					</p>
					<p>
						A CIFTI file is a single rectangular data matrix (usually 2 dimensions, but supports 3). Each 
						dimension is described using one of five available 'mapping types', and it is their combinations 
						that give rise to the diversity of CIFTI file types. Each mapping contains all information needed 
						to figure out what every index along the dimension means. A mapping of type 'brain models' (also 
						known as 'dense') can represent both hemispheres and all subcortical structures concurrently, thus 
						allowing one dimension to represent over a dozen structures, both surface-based and voxel-based. 
						CIFTI supports an alternative spatial mapping (i.e., allowing display on surfaces and/or in the 
						volume) that is based on ‘parcels’ (e.g., cortical areas, subcortical nuclei); parcels group related 
						brainordinates into areas, resulting in much smaller data files by having fewer values to store (per 
						parcel instead of per vertex/voxel). CIFTI also supports three non-spatial mapping types: 
					</p>
					<p>
						<ul>
							<li>
								<b>Scalars:</b> Each index is simply given a name (e.g., 'Myelin').
							</li>
							<li>
								<b>Series:</b> Each index is assigned a quantity in a linear series (e.g., a frequency in Hz or a timeseries of 0 sec, 
								0.7 sec, 1.4 sec, ...)
							</li>
							<li>
								<b>Labels:</b> each index is assigned a name (e.g., 'Visual Areas'), but also a label table that maps data values to 
								names and colors (see gifti label file, above).  Using this mapping type also implies that the data values in the 
								file are integers, matching the entries in the label table.
							</li>
						</ul>
					</p>
					<p>
						The common types of cifti files and the mapping types they use are:
						<ul>
							<li><b>dconn:</b> a ‘dense’ by ‘dense’ matrix (typically a ‘dense connectome’ from resting-state fMRI)</li>
							<li><b>dscalar:</b> one or more dense maps of scalar values (e.g., myelin maps, curvature maps)</li>
							<li><b>dtseries:</b> one or more dense timeseries datasets (e.g., fMRI timeseries) or other data at equal intervals</li>
							<li><b>dlabel:</b> one or more dense maps of integer values, plus a ‘label table’ that defines each integer (e.g., 
								one or more parcellations of cerebral cortex, subcortical nuclei, or all grayordinates)</li>
							<li><b>dpconn:</b> a parcel by dense matrix</li>
							<li><b>pconn:</b> a parcel by parcel matrix (typically, a ‘parcellated connectome’)</li>
							<li><b>pdconn:</b> a dense by parcel matrix</li>
							<li><b>pscalar:</b> one or more parcellated scalar maps (e.g., a parcellated thickness map having uniform thickness 
								within each parcel</li>
							<li><b>ptseries:</b> parcellated timeseries</li>
							<li><b>plabel:</b> one or more parcel maps, where each parcel is identified and colored using a label</li>
							<li><b>sdseries:</b> contains rows of series data (frequency, time, etc.). This data type is different than most 
								other CIFTI files, as it is not mapped to brainordinates (i.e., it has neither a dense nor a parcels dimension). 
								The data is viewed in its entirety as a matrix chart, or individual rows are viewed as a line chart.					
							<li><b>fiberTemp:</b> contains orientations of dMRI fibers.  This is actually the same format as a dscalar file (above), 
								but the arrangement of maps in the file is fixed so that wb_view knows how to translate them into fiber orientations.</li>
						</ul>
					</p>
					<p>
						For futher details of the CIFTI format, see
						<a href="http://www.nitrc.org/projects/cifti/">http://www.nitrc.org/projects/cifti/</a>
					</p>
					<br>

					<h4>Customized Workbench (wb_view) files:</h4>
					<h5>Scene file (*.scene)</h5>
					<p>
						A scene file contains one or more scenes; each scene is effectively a snapshot of the internal 
						state of wb_view at the time it was saved. This includes information about which files were loaded, 
						how they were displayed, how the tabs were configured (what surfaces are selected, what file is 
						selected in each layer, which layers are turned on, what view rotation, palette settings, tile 
						tabs, etc), and what annotations (text, arrows, circles, etc) were added over them. Opening a scene 
						from a scene file restores the exact state of wb_view, allowing further exploration of the data or 
						modification of the annotations or view setup. These capabilities allow publication-ready figures 
						to be set up entirely in wb_view, for capture to image format, and to be modified subsequently 
						with minimal hassle.
					</p>
					<p>
						Workbench can create a zip archive of a scene file and all the data files that are used by its scenes.  
						While this can be useful by itself for sharing data, BALSA builds on this ability by making a public 
						repository for scene files and related data files, organized by publication and figure, where web 
						browsers can view scene previews and information about the data files, as well as download the files.
					</p>
					<h5>Spec file (*.spec)</h5>
					<p>
						A spec (‘specification’) file is a list of data files that can be concurrently viewed in wb_view. 
						It is useful for organizing sets of related files, such that they can easily be opened together.
					</p>
					<h5>Border file (*.border)</h5>
					<p>
						A border file contains one or more borders (usually many). Each border is a sequence of points on a 
						surface, which may or may not have a connection from the final point back to the starting point 
						("closed" versus "open" borders). Borders can be viewed on different surface configurations (e.g., 
						anatomical vs inflated surfaces). Borders can represent many types of surface contour and are often 
						used to denote the boundary between regions or areas.
					</p>
					<h5>Foci (*.foci)</h5>
					<p>
						A foci file contains one or more foci (foci is plural of focus).  Required properties of a focus 
						include: (1) a stereotaxic (3D) coordinate that may also be projected to a barycentric coordinate 
						(relative to a particular triangle in a surface); (2) a name with an associated color; (3) a class 
						with an associated color.  The class may be used to group related foci.  Optional properties of a 
						focus include area, comment, geography, extent, ROI, and statistic.
					</p>
					<h5>Trajectory file (*.trajTEMP.wbsparse)</h5>
					<p>
						A trajectory file contains probabilistic trajectories constructed using probabilistic tractography 
						applied to fiber orientation data (fiberTEMP.nii files).  For each seed point, the file contains the 
						counts of how often each voxel was used by a probabilistic streamline.
					</p>
					<h5>Annotation (*.annot)</h5>
					<p>
						Annotations consist of text labels, images, or symbols viewable in a wb_view window allowing one to 
						create figures for use in publications without having to use another application (GIMP, Photoshop).
						For more information about annotations, see the 
						<a href="https://humanconnectome.org/storage/app/media/documentation/tutorials/Guide_to_WB_Annotations_1.3.2.pdf">
						Guide to WB Annotations</a>.
					</p>
					<br>

					<h4>Miscellaneous</h4><a name="Miscellaneous"></a>
					<p>
						Assorted other file types that can be included in a study or reference dataset. Examples include
						.png files (images) and .txt file (raw text).
					</p>
				</div>
			</div>
		</div>
	</body>
</html>