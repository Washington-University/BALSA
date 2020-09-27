package balsa.scene

class ScenePreview {
	String id
	String imageFormat
	byte[] image

	static belongsTo = [scene: Scene]
	
	static mapping = {
		id column: 'scene_id', generator: 'foreign', params: [property: 'scene']
		scene insertable: false, updateable: false
		imageFormat type: "text"
	}
	
    static constraints = {
    }
}
