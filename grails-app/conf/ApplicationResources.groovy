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
	'font-awesome' { resource url: 'font-awesome/css/font-awesome.css' }
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
	editor {
		dependsOn 'jquery'
		resource url:'/js/svg-edit/jgraduate/css/jPicker-1.0.12.css'
		resource url:'/js/svg-edit/jgraduate/css/jgraduate.css'
		resource url:'/js/svg-edit/svg-editor.css'
		resource url:'/js/svg-edit/spinbtn/JQuerySpinBtn.css'

		resource url:'/js/svg-edit/js-hotkeys/jquery.hotkeys.min.js'
		resource url:'/js/svg-edit/jgraduate/jquery.jgraduate.min.js'
		resource url:'/js/svg-edit/svgicons/jquery.svgicons.min.js'
		resource url:'/js/svg-edit/jquerybbq/jquery.bbq.min.js'
		resource url:'/js/svg-edit/spinbtn/JQuerySpinBtn.min.js'
		resource url:'/js/svg-edit/svgcanvas.min.js'
		resource url:'/js/svg-edit/svg-editor.js'
		resource url:'/js/svg-edit/locale/locale.min.js'
		resource url:'/js/svg-edit/jquery-ui/jquery-ui-1.8.custom.min.js'
		resource url:'/js/svg-edit/jgraduate/jpicker-1.0.12.min.js'
		//resource url:'/js/svg-edit/extensions/ext-markers.js', exclude:'bundle'
		//resource url:'/js/svg-edit/extensions/ext-connector.js', exclude:'bundle'
		resource url:'/js/svg-edit/extensions/ext-roomselector.js', exclude:'bundle'
		resource url:'/js/svg-edit/extensions/ext-server_opensave.js.gsp', exclude:'bundle'
		
		
		resource url:'/js/svg-edit/images/svg_edit_icons.svg',nohashandcache: true, exclude:"*", attrs:[type:'css']

		
		resource url:'/js/jquery.autocomplete/jquery.autocomplete.js'
		resource url:'/js/jquery.autocomplete/styles.css'
		resource url:'/js/jquery.autocomplete/shadow.png'
		//getFilesForPath('/js/svg-edit/').each { resource url: it }
		//resource url:'/js/changepolling.joelpurra.js'
	}

}

