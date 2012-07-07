package houseplan

class Floor {
	int nr
	static hasMany=[rooms:Room]
	
    static constraints = {
		nr unique:true
    }
}
