package floorplan

class Shop {

	String name
	String desc
	SortedSet categories
	SortedSet paytypes
	SortedSet trademarks

	static belongsTo = [room: Room]
	static hasMany=[categories:Category, paytypes:Paytype, trademarks:Trademark]

	/*
	 * category
	 * paytype
	 * service_desc
	 * url
	 * city
	 * aadress
	 * phone
	 * company
	 */
	static mapping ={ categories lazy: false }
	static constraints = {
		room nullable:true
		name unique:true
	}
	String toString() {
		name
	}
}
