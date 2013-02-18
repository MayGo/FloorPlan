package floorplan

class Floor {
	int nr
	String svg
	static hasMany=[rooms:Room]
	
    static constraints = {
		nr unique:true
    }
	static mapping ={
		svg type: 'text'
	}
	String toString() {
		nr
	}
}
