package houseplan

class Shop {

	String name
	String desc
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
	
    static constraints = {
		name unique:true
    }
}
