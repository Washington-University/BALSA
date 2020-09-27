package balsa

import grails.transaction.Transactional
import balsa.file.SceneFile
import balsa.scene.Scene
import balsa.tagging.TagCategory

@Transactional
class SearchService {
	
	def datasetSearch(params) {
		if (!params || !params.rows || !params.columns) {
			return [datasets:[], totalCount:0]
		}
		params.max = Integer.valueOf(params.rows) * Integer.valueOf(params.columns) ?: 6
		params.offset = params.offset ?: 0
		def searchTags = extractSearchTags(params)
		
		def c = SceneFile.createCriteria()
		def allPublicSceneFileIds = c.list() {
			projections {
				property("id")
			}
			versions {
				eq("status", Version.Status.PUBLIC)
			}
		}
		
		def keywordSearchPublicSceneFileIds = []
		if (params.keyword) {
			c = SceneFile.createCriteria()
			keywordSearchPublicSceneFileIds = c.list() {
				projections {
					property("id")
				}
				versions {
					eq("status", Version.Status.PUBLIC)
					or {
						ilike("doi",'%'+params.keyword+'%')
						ilike("pmid",'%'+params.keyword+'%')
						pgArrayILike 'authors', '%'+params.keyword+'%'
					}
				}
			}
		}
		
		c = Scene.createCriteria()
		def allDatasetIds = c.list() {
			sceneFile {
				'in'("id", allPublicSceneFileIds)
				dataset {
					projections {
						property("id")
					}
					or { // should be using the 'in' criteria here, but it interacts oddly with pagination if more than one search domain is checked
						if (params.searchDomain.contains("Reference")) {
							eq("class", "balsa.Reference")
						}
						if (params.searchDomain.contains("Studies")) {
							eq("class", "balsa.Study")
						}
						eq("class", "null")
					}
				}
			}
			if (params.keyword) {
				or {
					ilike("name",'%'+params.keyword+'%')
					ilike("description",'%'+params.keyword+'%')
					sceneFile {
						or {
							if (keywordSearchPublicSceneFileIds) {
								'in'("id", keywordSearchPublicSceneFileIds)
							}
							dataset {
								or {
									ilike("title",'%'+params.keyword+'%')
									ilike("shortTitle",'%'+params.keyword+'%')
								}
							}
						}
					}
				}
			}
			pgArrayContains 'tags', searchTags['and']
			for (orSet in searchTags['or']) {
				pgArrayOverlaps 'tags', orSet
			}
		}
		
		// if no dataset ids, then no need for other queries
		if (allDatasetIds.size() == 0) {
			return [datasets:[], totalCount:0]
		}
		
		// seems to be the only way to accurately apply params, due to problems with distinct
		c = Dataset.createCriteria()
		def datasets = c.list(params) {
			'in'("id", allDatasetIds)

			switch (params.sortBy) {
				case "Name":
					order('title', 'asc')
					break
				case "Newest":
					order('publicDate', 'desc')
					break
				case "Oldest":
					order('publicDate', 'asc')
					break
			}
		}
		
		[datasets:datasets, totalCount:datasets.totalCount]
	}
	
	def sceneSearch(params) {
		if (!params || !params.rows || !params.columns) {
			return [scenes:[], totalCount:0]
		}
		params.max = Integer.valueOf(params.rows) * Integer.valueOf(params.columns) ?: 6
		params.offset = params.offset ?: 0
		def searchTags = extractSearchTags(params)
		
		def c = SceneFile.createCriteria()
		def allPublicSceneFileIds = c.list() {
			projections {
				property("id")
			}
			versions {
				eq("status", Version.Status.PUBLIC)
			}
		}
		
		c = Scene.createCriteria()
		def scenes = c.list(params) {
			sceneFile {
				'in'("id", allPublicSceneFileIds)
				dataset {
					or { // should be using the 'in' criteria here, but it interacts oddly with pagination if more than one search domain is checked
						if (params.searchDomain.contains("Reference")) {
							eq("class", "balsa.Reference")
						}
						if (params.searchDomain.contains("Studies")) {
							eq("class", "balsa.Study")
						}
						eq("class", "null")
					}
					
					switch (params.sortBy) {
						case "Name":
							order('title', 'asc')
							break
						case "Newest":
							order('publicDate', 'desc')
							break
						case "Oldest":
							order('publicDate', 'asc')
							break
					}
				}
				
				order('filename', 'asc')
			}
			
			if (params.keyword) {
				or {
					ilike("name",'%'+params.keyword+'%')
					ilike("description",'%'+params.keyword+'%')
				}
			}
			pgArrayContains 'tags', searchTags['and']
			for (orSet in searchTags['or']) {
				pgArrayOverlaps 'tags', orSet
			}

			order('index', 'asc')
		}
		
		[scenes:scenes, totalCount:scenes.totalCount]
	}
	
	

//    def basicSearch(params) {
//		params.max = Integer.valueOf(params.rows) * Integer.valueOf(params.columns) ?: 6
//		params.offset = params.offset ?: 0
//		def searchTags = extractSearchTags(params)
//		
//		// we're going to group by and paginate by scene group, and pagination doesn't work with listDistinct()
//		// so first we're getting a list of the ids of all scene group that have matching scenes
//		// we have to use 'distinct' because searching by child objects returns the parent multiple times
//		def c = SceneFile.createCriteria()
//		def allSceneFileIds = c.list() {
//			projections {
//				distinct("id")
//			}
//			isNull("removed")
//			dataset {
//				or { // should be using the 'in' criteria here, but it interacts oddly with pagination if more than one search domain is checked
//					if (params.searchDomain.contains("Reference")) {
//						eq("class", "balsa.Reference")
//					}
//					if (params.searchDomain.contains("Studies")) {
//						eq("class", "balsa.Study")
//					}
//					eq("class", "null")
//				}
//				eq("status", Dataset.Status.PUBLIC)
//			}
//			scenes {
//				if (params.keyword) {
//					or {
//						ilike("name",'%'+params.keyword+'%')
//						ilike("description",'%'+params.keyword+'%')
//					}
//				}
//				pgArrayContains 'tags', searchTags['and']
//				for (orSet in searchTags['or']) {
//					pgArrayOverlaps 'tags', orSet
//				}
//			}
//		}
//		
//		// if no scene group ids, then no need for other queries
//		if (allSceneFileIds.size() == 0) {
//			return [sceneFiles:[], scenes:[], totalCount:0]
//		}
//		
//		// we're also going to need details about the individual scene groups
//		// this is the only place we can handle pagination
//		// ordering interacts oddly with distinct for some reason, so we'll order here as well
//		c = SceneFile.createCriteria()
//		def sceneFiles = c.list(params) {
//			'in'("id", allSceneFileIds)
//			dataset {
//				order("class", "asc")
//			}
//			order("filename", "asc")
//		}
//		
//		// now we just want the ids of scene groups we're actually showing
//		def sceneFileIds = []
//		for (sceneFile in sceneFiles) {
//			sceneFileIds.add(sceneFile.id)
//		}
//		
//		// we want only those scenes in the specified scene groups that actually meet the criteria
//		c = Scene.createCriteria()
//		def scenes = c.list() {
//			sceneFile {
//				'in'("id", sceneFileIds)
//			}
//			if (params.keyword) {
//				or {
//					ilike("name",'%'+params.keyword+'%')
//					ilike("description",'%'+params.keyword+'%')
//				}
//			}
//			pgArrayContains 'tags', searchTags['and']
//			for (orSet in searchTags['or']) {
//				pgArrayOverlaps 'tags', orSet
//			}
//			order("index", "asc")
//		}
//		
//		[sceneFiles:sceneFiles, scenes:scenes, totalCount:sceneFiles.totalCount]
//	}
	
	def extractSearchTags(params) {
		def and = [] // a list of tags that must all be present
		def or = [] // a list of 'or' lists, each of which must be satisfied
		for (category in TagCategory.list()) {
			def tagValues = params.list(category.name)
			
			def tags = []
			for (tagValue in tagValues) {
				if (tagValue.length() > 0) {
					tags.add(category.name + ":" + tagValue)
				}
			}
		
			if (category.searchType.or() && tags.size > 0) {
				or.add(tags)
			}
			else {
				and.addAll(tags)
			}
		}
		[and: and, or: or]
	}
}
