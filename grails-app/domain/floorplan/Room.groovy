package floorplan

class Room {
 
	int nr
	Shop shop
	static belongsTo = [floor: Floor]
	
    static constraints = {
		nr unique:true
		shop nullable:true
    }
	String toString() {
		nr
	}
}
