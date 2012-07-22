package floorplan



import org.junit.*
import grails.test.mixin.*

@TestFor(ShopController)
@Mock(Shop)
class ShopControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/shop/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.shopInstanceList.size() == 0
        assert model.shopInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.shopInstance != null
    }

    void testSave() {
        controller.save()

        assert model.shopInstance != null
        assert view == '/shop/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/shop/show/1'
        assert controller.flash.message != null
        assert Shop.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/shop/list'


        populateValidParams(params)
        def shop = new Shop(params)

        assert shop.save() != null

        params.id = shop.id

        def model = controller.show()

        assert model.shopInstance == shop
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/shop/list'


        populateValidParams(params)
        def shop = new Shop(params)

        assert shop.save() != null

        params.id = shop.id

        def model = controller.edit()

        assert model.shopInstance == shop
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/shop/list'

        response.reset()


        populateValidParams(params)
        def shop = new Shop(params)

        assert shop.save() != null

        // test invalid parameters in update
        params.id = shop.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/shop/edit"
        assert model.shopInstance != null

        shop.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/shop/show/$shop.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        shop.clearErrors()

        populateValidParams(params)
        params.id = shop.id
        params.version = -1
        controller.update()

        assert view == "/shop/edit"
        assert model.shopInstance != null
        assert model.shopInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/shop/list'

        response.reset()

        populateValidParams(params)
        def shop = new Shop(params)

        assert shop.save() != null
        assert Shop.count() == 1

        params.id = shop.id

        controller.delete()

        assert Shop.count() == 0
        assert Shop.get(shop.id) == null
        assert response.redirectedUrl == '/shop/list'
    }
}
