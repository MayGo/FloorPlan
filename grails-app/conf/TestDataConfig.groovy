testDataConfig {
    sampleData {
        "floorplan.Room"{
			def i = 1
			nr = {-> i++ }
			
		}
		"floorplan.Shop"{
			def i = 100
			name = {-> "Shop ${i++}" }
		}
		"floorplan.Floor"{
			def i = 0
			nr = {-> i++ }
		}
		
		"floorplan.Paytype"{
			List l=["visa","pangakaart","sularaha"]
			def i = 0
			name=l.get(new Random().nextInt(l.size()-1))+ " "+ i
		}
		"floorplan.Category"{
			List l=["jalatsid","kotid","kodu"]
			def i = 0
			name=l.get(new Random().nextInt(l.size()-1))+ " "+ i
		}
    }
}

/*
// sample for creating a single static value for the com.foo.Book's title property:
// title for all Books that we "build()" will be "The Shining", unless explicitly set

testDataConfig {
    sampleData {
        'com.foo.Book' {
            title = "The Shining"
        }
    }
}
*/

/*
// sample for creating a dynamic title for com.foo.Book, useful for unique properties:
// just specify a closure that gets called

testDataConfig {
    sampleData {
        'com.foo.Book' {
            def i = 1
            title = {-> "title${i++}" }   // creates "title1", "title2", ...."titleN"
        }
    }
}
*/

/*
// When using a closure, if your tests expect a particular value, you'll likely want to reset
// the build-test-data config in the setUp of your test, or in the test itself.  Otherwise if
// your tests get run in a different order you'll get different values

// (in test/integration/FooTests.groovy)

void setUp() {
    grails.buildtestdata.TestDataConfigurationHolder.reset()
}
*/


/*
// if you'd like to disable the build-test-data plugin in an environment, just set
// the "enabled" property to false

environments {
    production {
        testDataConfig {
            enabled = false
        }
    }
}
*/