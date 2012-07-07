package houseplan

class Room {

	int nr
	Shop shop
	
    static constraints = {
		nr unique:true
    }
}
