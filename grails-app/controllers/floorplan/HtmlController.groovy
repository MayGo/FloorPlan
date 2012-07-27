package floorplan


class HtmlController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		String controller=params.contr
		String action=params.act
		render (view:"/"+controller+"/"+action)
	}
}
