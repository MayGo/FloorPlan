package floorplan


class Tag implements Comparable{

	static belongsTo = Shop
	String name
	static hasMany=[shops:Shop]
	
    void setName(String name) {
        this.@name =  name.toLowerCase()
    }

    String toString() {
        name
    }
	int compareTo(obj) {
		name.compareTo(obj.name)
	}


	static constraints = {
		name blank:false, unique:true
	}
	
	static mapping = {
		cache 'read-write'

	}

}