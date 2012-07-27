class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{

			constraints {
				// apply constraints here
			}
		}
		"/html/$contr/$act"{
			
			controller= "html"
			action="index"
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
