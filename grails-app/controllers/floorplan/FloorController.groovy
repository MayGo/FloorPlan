package floorplan

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class FloorController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        cache false
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        response.setIntHeader('X-Pagination-Total', Floor.count())
        render Floor.list(params) as JSON
    }

    def save() {
        def floorInstance = new Floor(request.JSON)
        def responseJson = [:]
        if (floorInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = floorInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'floor.label', default: 'Floor'), floorInstance.id])
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
						args: [message(code: 'floor.label', default: 'Floor')],
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
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'floor.label', default: 'Floor'), floorInstance.id])
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
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'floor.label', default: 'Floor'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'floor.label', default: 'Floor'), params.id])
        }
		cache false
		render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'floor.label', default: 'Floor'), params.id])]
        render responseJson as JSON
    }
}
