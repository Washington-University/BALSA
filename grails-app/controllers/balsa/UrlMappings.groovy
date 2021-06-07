package balsa

class UrlMappings {
	static mappings = {
		"/robots.txt" (controller: "download", action: "robots") // redirect to robots.txt gsp
        "/$controller/$action?/$id?(.$format)?"{ // default grails URL pattern
            constraints {
				id(matches:/[bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ1234567890]+/)
            }
        }
		"/$controller/$id?(.$format)?"(action: "show"){ // given just the controller and an id, default to show
			constraints {
				id(matches:/[bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ1234567890]+/)
			}
		}
		"/$controller"{ // necessary to allow standard controller default actions (grails otherwise assumes the above pattern as show/null)
			
		}
		"/$id"(controller: "scene", action: "show"){ // given just an id, assume user is looking for a scene
			constraints {
				id(matches:/[bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ1234567890]+/)
			}
		}
        "/"(view:"/index")
        "500"(view:'/error')
		"404"(view:'/404')
	}
}
