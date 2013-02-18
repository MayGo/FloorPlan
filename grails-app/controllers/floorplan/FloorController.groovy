package floorplan

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class FloorController {

	static final int SC_UNPROCESSABLE_ENTITY = 422

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
	}

	def list() {
		cache false
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Floor.count())
		render Floor.list(params) as JSON
	}
	def savefile(){

		String content=params.output_svg
		def floorId=1
		if(content){
			//remove unnessasary text from string, so only that remains is pure svg without svg tags
			content=content.replace('<?xml version="1.0"?><svg width="640" height="480" xmlns="http://www.w3.org/2000/svg">', "")
			content=content.replace('<!-- Created with SVG-edit - http://svg-edit.googlecode.com/ -->', "")
			content=content.replace('</svg>', "")
			println content
			Floor floor=Floor.get(floorId)
			if(floor){
				floor.svg=content
				//content.bytes.encodeBase64().toString()
				if(floor.save(flush:true)){
					render "OK"
					return
				}
//				def decoded = new String("SGVsbG8gV29ybGQ=".decodeBase64())
				
			}
			
		}
		render "NOT OK"
	}
	def save() {
		def floorInstance = new Floor(request.JSON)
		def responseJson = [:]
		if (floorInstance.save(flush: true)) {
			response.status = SC_CREATED
			responseJson.id = floorInstance.id
			responseJson.message = message(code: 'default.created.message', args: [
				message(code: 'floor.label', default: 'Floor'),
				floorInstance.id
			])
		} else {
			response.status = SC_UNPROCESSABLE_ENTITY
			responseJson.errors = floorInstance.errors.fieldErrors.collectEntries {
				[(it.field): message(error: it)]
			}
		}
		cache false
		render responseJson as JSON
	}

	def get() {
		def floorInstance = Floor.get(params.id)
		if (floorInstance) {
			cache false
			render floorInstance as JSON
		} else {
			notFound params.id
		}
	}

	def update() {
		def floorInstance = Floor.get(params.id)
		if (!floorInstance) {
			notFound params.id
			return
		}

		def responseJson = [:]

		if (request.JSON.version != null) {
			if (floorInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [
							message(code: 'floor.label', default: 'Floor')
						],
						default: 'Another user has updated this Floor while you were editing')
				cache false
				render responseJson as JSON
				return
			}
		}

		floorInstance.properties = request.JSON

		if (floorInstance.save(flush: true)) {
			response.status = SC_OK
			responseJson.id = floorInstance.id
			responseJson.message = message(code: 'default.updated.message', args: [
				message(code: 'floor.label', default: 'Floor'),
				floorInstance.id
			])
		} else {
			response.status = SC_UNPROCESSABLE_ENTITY
			responseJson.errors = floorInstance.errors.fieldErrors.collectEntries {
				[(it.field): message(error: it)]
			}
		}

		cache false
		render responseJson as JSON
	}

	def delete() {
		def floorInstance = Floor.get(params.id)
		if (!floorInstance) {
			notFound params.id
			return
		}

		def responseJson = [:]
		try {
			floorInstance.delete(flush: true)
			responseJson.message = message(code: 'default.deleted.message', args: [
				message(code: 'floor.label', default: 'Floor'),
				params.id
			])
		} catch (DataIntegrityViolationException e) {
			response.status = SC_CONFLICT
			responseJson.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'floor.label', default: 'Floor'),
				params.id
			])
		}
		cache false
		render responseJson as JSON
	}

	private void notFound(id) {
		response.status = SC_NOT_FOUND
		def responseJson = [message: message(code: 'default.not.found.message', args: [
				message(code: 'floor.label', default: 'Floor'),
				params.id
			])]
		render responseJson as JSON
	}
}
