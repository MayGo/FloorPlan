
<%@ page import="floorplan.Floor"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="ng-app">
<g:set var="entityName" value="${message(code: 'map.label')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<r:require module="map" />
</head>

<body data-ng-app="scaffolding">

	<div id="content" class="modal hide fade in" style="display: none;">
		<div class="modal-header" style="border-bottom:none">  
		<a class="close" data-dismiss="modal">×</a>  
		</div>  
		<div class="modal-body" data-ng-view></div>
	</div>

	<div class="map_full" id='contentMap'>
		<script type="text/javascript">

			
			$(function() {
				var mapSvg = "${resource(dir: 'maps', file:'1_korrus_2.svg')}";
				console.log(mapSvg);

				$('#floors').Floor({});
				$('#floors').Floor("load", mapSvg);
			});
		</script>
		<div id="floors"></div>
	</div>
</body>
</html>
