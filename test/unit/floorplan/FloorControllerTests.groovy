package floorplan



import org.junit.*
import grails.test.mixin.*

@TestFor(FloorController)
@Mock(Floor)
class FloorControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/floor/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.floorInstanceList.size() == 0
        assert model.floorInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.floorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.floorInstance != null
        assert view == '/floor/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/floor/show/1'
        assert controller.flash.message != null
        assert Floor.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/floor/list'


        populateValidParams(params)
        def floor = new Floor(params)

        assert floor.save() != null

        params.id = floor.id

        def model = controller.show()

        assert model.floorInstance == floor
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/floor/list'


        populateValidParams(params)
        def floor = new Floor(params)

        assert floor.save() != null

        params.id = floor.id

        def model = controller.edit()

        assert model.floorInstance == floor
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/floor/list'

        response.reset()


        populateValidParams(params)
        def floor = new Floor(params)

        assert floor.save() != null

        // test invalid parameters in update
        params.id = floor.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/floor/edit"
        assert model.floorInstance != null

        floor.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/floor/show/$floor.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        floor.clearErrors()

        populateValidParams(params)
        params.id = floor.id
        params.version = -1
        controller.update()

        assert view == "/floor/edit"
        assert model.floorInstance != null
        assert model.floorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/floor/list'

        response.reset()

        populateValidParams(params)
        def floor = new Floor(params)

        assert floor.save() != null
        assert Floor.count() == 1

        params.id = floor.id

        controller.delete()

        assert Floor.count() == 0
        assert Floor.get(floor.id) == null
        assert response.redirectedUrl == '/floor/list'
    }
}
