package balsa.scene

import balsa.Dataset
import balsa.file.SceneFile
import balsa.security.BalsaUser

class SceneLine {
	String id
	Dataset dataset
	
	static hasMany = [scenes: Scene, linkedDatasets: Dataset]
	static belongsTo = [Dataset]
	static mappedBy = [dataset: "sceneLines", linkedDatasets: "linkedScenes"]

    static constraints = {
    }
	
	static mapping = {
		id generator: "assigned" // unlike other BALSA ids, scene ids must be assigned to maintain continuity
		dataset lazy: false
	}
	
	boolean isPublic(versionInfo = null) {
		if (versionInfo) {
			def datVersion = dataset.getVersion(versionInfo)
			datVersion && datVersion?.isPublic() && sceneForVersion(versionInfo)
		}
		else {
			sceneForVersion('public') || sceneForVersion('preprint')
		}
	}
	
	boolean canEdit() {
		dataset.canEdit()
	}
	
	boolean canView() {
		dataset.canView()
	}
	
	boolean hasAccess() {
		dataset.hasAccess()
	}
	
	def terms() {
		dataset.terms()
	}
	
	def datasetId() {
		dataset.id
	}
	
	Scene current() {
		sceneForVersion(null) ?: lastAppearance()
	}
	
	Scene sceneForVersion(versionInfo) {
		def versionScenes = dataset.getVersion(versionInfo)?.scenes()
		for (scene in versionScenes) {
			if (scenes.contains(scene)) {
				return scene
			}
		}
		if (canView()) {
			return lastAppearance()
		}
		return null
	}
	
	Scene sceneForSceneFile(SceneFile sceneFile) {
		return sceneFile.scenes.find { it.sceneLine == focusSceneLine }
	}
	
	Scene lastAppearance() {
		def versions = [] as Set
		for (sceneFile in scenes*.sceneFile) {
			versions.addAll(sceneFile.versions)
		}
		def mostRecentDate = (versions*.updatedDate)?.max()
		versions.find { it.updatedDate == mostRecentDate }
	}
}
