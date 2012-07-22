package floorplan

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class MapController {

	static final int SC_UNPROCESSABLE_ENTITY = 422

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def taxonomyService

	def index() {
	}

	def get() {
		def roomInstance = Room.findByNr(params.int('id'))

		if (roomInstance) {

			Map m= [nr:roomInstance.nr,
						floor:[nr:roomInstance.floor?.nr],
						shop:[
							name:roomInstance.shop?.name,
							desc:roomInstance.shop?.desc,
							categories:roomInstance.shop.categories.collect{
								[
											id:it.id,
											name:it.name,
											count:it.shops.size()
											//,shops:it.shops.collect{it.room.nr}
										]
							},
							paytypes:roomInstance.shop.paytypes.collect{
								[id:it.id, name:it.name,
											count:it.shops.size()
											//,shops:it.shops.collect{it.room.nr}
										]
							},
							trademarks:roomInstance.shop.trademarks.collect{
								[id:it.id, name:it.name,
											count:it.shops.size()
											//,shops:it.shops.collect{it.room.nr}
											]
							}

						]
					]


			render m as JSON
		} else {
			notFound params.id
		}
	}
	def category() {
		def inst = Category.get(params.long('id'))
		if (inst) {
			Map m= ["categories":inst.shops.collect{it.room.nr}]
			render m as JSON
		} else {
			notFound params.id
		}
	}
	def paytypes() {
		def inst = Category.get(params.long('id'))
		if (inst) {
			Map m= ["paytype":inst.shops.collect{it.room.nr}]
			render m as JSON
		} else {
			notFound params.id
		}
	}
	def trademarks() {
		def inst = Category.get(params.long('id'))
		if (inst) {
			Map m= ["trademark":inst.shops.collect{it.room.nr}]
			render m as JSON
		} else {
			notFound params.id
		}
	}


	private void notFound(id) {
		response.status = SC_NOT_FOUND
		def responseJson = [message: message(code: 'default.not.found.message', args: [
				message(code: 'room.label', default: 'Room'),
				params.id
			])]
		render responseJson as JSON
	}
}
