package floorplan

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class TagController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        cache false
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        response.setIntHeader('X-Pagination-Total', Tag.count())
        render Tag.list(params) as JSON
    }

    def save() {
        def tagInstance = new Tag(request.JSON)
        def responseJson = [:]
        if (tagInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = tagInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'tag.label', default: 'Tag'), tagInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = tagInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
		cache false
        render responseJson as JSON
    }

    def get() {
        def tagInstance = Tag.get(params.id)
        if (tagInstance) {
			cache false
			render tagInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def tagInstance = Tag.get(params.id)
        if (!tagInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (tagInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'tag.label', default: 'Tag')],
						default: 'Another user has updated this Tag while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        tagInstance.properties = request.JSON

        if (tagInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = tagInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'tag.label', default: 'Tag'), tagInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = tagInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

		cache false
		render responseJson as JSON
    }

    def delete() {
        def tagInstance = Tag.get(params.id)
        if (!tagInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            tagInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])
        }
		cache false
		render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])]
        render responseJson as JSON
    }
}
