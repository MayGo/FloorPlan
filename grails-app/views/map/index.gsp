
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
				function parseSVG(s) {
			        var div= document.createElementNS('http://www.w3.org/1999/xhtml', 'div');
			        div.innerHTML= '<svg xmlns="http://www.w3.org/2000/svg">'+s+'</svg>';
			        var frag= document.createDocumentFragment();
			        while (div.firstChild.firstChild)
			            frag.appendChild(div.firstChild.firstChild);
			        return frag;
			    }

				
				var mapSvg1 = "${resource(dir: 'maps', file:'1_korrus_1.svg')}";
				var mapSvg2 = "${resource(dir: 'maps', file:'1_korrus_2.svg')}";
				console.log(mapSvg1);

				$('#floor1').Floor({});
				var svg = $('#floor1').svg('get');

				var defs = svg.defs();
				var filter = svg.filter(defs, "myGaussianBlur", null, null, null, null, {
				//	filterUnits : "userSpaceOnUse"
				});
				svg.filters.gaussianBlur(filter,null, "SourceAlpha",  4);
				//svg.filters.offset(filter, "offsetBlur", "blur", 4, 4);
				$(parseSVG('<filter id="myGlowFilter"><feGaussianBlur stdDeviation="6" result="coloredBlur"/><feMerge><feMergeNode in="coloredBlur"/><feMergeNode in="SourceGraphic"/></feMerge></filter>')).appendTo(svg.defs());
				
				$('#floor1').Floor("load", mapSvg1);
				$('#floor2').Floor({});
				$('#floor2').Floor("load", mapSvg2);
				
				var FLOOR_BETWEEN=70;
				$(".floor").each(function(index) {
					$(this).offset({top:$(this).offset().top+FLOOR_BETWEEN * index});
					var zIndex=$(this).css('z-index');
					$(this).css('z-index', zIndex-index)
				});
			});
		</script>
		<div id="floors">
		<div id="floor1" class="floor"></div>
		<div id="floor2" class="floor" ></div>
		</div>
	</div>
</body>
</html>
