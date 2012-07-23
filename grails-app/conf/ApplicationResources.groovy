//modules = {
//    application {
//        resource url:'js/application.js'
//    }
//}

import grails.util.GrailsUtil

def fileVersion = GrailsUtil.isDevelopmentEnv() ? '1.0.0' : '1.0.0.min'

modules = {
	
	bootstrap {
		dependsOn 'jquery'
		resource url: 'bootstrap/css/bootstrap.min.css'
		resource url: 'bootstrap/js/bootstrap.min.js'
	}
	'font-awesome' {
		resource url: 'font-awesome/css/font-awesome.css'
	}
	'ui-common' {
		dependsOn 'bootstrap', 'font-awesome'
		resource url: 'css/ui-common.css'
	}
	
	'angular-scaffolding' {
		dependsOn 'jquery', 'angular'
		resource  url: [ dir: 'js', file: 'grails-default.js']
		resource url: [dir: 'js', file: 'scaffolding.js']
		resource  url: [dir: 'css', file: 'scaffolding.css']
	}
	application { resource url:'js/application.js' }
	svg {
		dependsOn 'jquery'

		resource url:'/js/jquery.svg.min.js'
		resource url:'/js/jquery.svganim.min.js'
		
		resource url:'/js/jquery.svgdom.min.js'
		resource url:'/js/jquery.svgfilter.min.js'
	}

	map {
		dependsOn 'angular-scaffolding'
		dependsOn 'svg'
		resource url:'/js/port.script.js'
		resource url:'/css/map.css'
	}


}