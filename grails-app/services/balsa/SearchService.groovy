package balsa

import balsa.file.SceneFile
import balsa.scene.Scene
import balsa.tagging.TagCategory

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
