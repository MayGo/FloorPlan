package floorplan

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class ShopController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        cache false
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        response.setIntHeader('X-Pagination-Total', Shop.count())
        render Shop.list(params) as JSON
    }

    def save() {
        def shopInstance = new Shop(request.JSON)
        def responseJson = [:]
        if (shopInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = shopInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'shop.label', default: 'Shop'), shopInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = shopInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
		cache false
        render responseJson as JSON
    }

    def get() {
        def shopInstance = Shop.get(params.id)
        if (shopInstance) {
			cache false
			render shopInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def shopInstance = Shop.get(params.id)
        if (!shopInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (shopInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'shop.label', default: 'Shop')],
						default: 'Another user has updated this Shop while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        shopInstance.properties = request.JSON

        if (shopInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = shopInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'shop.label', default: 'Shop'), shopInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = shopInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

		cache false
		render responseJson as JSON
    }

    def delete() {
        def shopInstance = Shop.get(params.id)
        if (!shopInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            shopInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'shop.label', default: 'Shop'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'shop.label', default: 'Shop'), params.id])
        }
		cache false
		render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'shop.label', default: 'Shop'), params.id])]
        render responseJson as JSON
    }
}
