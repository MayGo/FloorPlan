package floorplan

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class RoomController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        cache false
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        response.setIntHeader('X-Pagination-Total', Room.count())
        render Room.list(params) as JSON
    }

    def save() {
        def roomInstance = new Room(request.JSON)
        def responseJson = [:]
        if (roomInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = roomInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'room.label', default: 'Room'), roomInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = roomInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
		cache false
        render responseJson as JSON
    }

    def get() {
        def roomInstance = Room.get(params.id)
        if (roomInstance) {
			cache false
			render roomInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def roomInstance = Room.get(params.id)
        if (!roomInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (roomInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'room.label', default: 'Room')],
						default: 'Another user has updated this Room while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        roomInstance.properties = request.JSON

        if (roomInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = roomInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'room.label', default: 'Room'), roomInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = roomInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

		cache false
		render responseJson as JSON
    }

    def delete() {
        def roomInstance = Room.get(params.id)
        if (!roomInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            roomInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'room.label', default: 'Room'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'room.label', default: 'Room'), params.id])
        }
		cache false
		render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'room.label', default: 'Room'), params.id])]
        render responseJson as JSON
    }
}
