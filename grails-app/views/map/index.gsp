
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
	<script type="text/javascript">
			$(function() {
				$('#toggleFloorView').click( function() {
				    if( ! $(this).hasClass('active') ) {
						var FLOOR_BETWEEN=70;
						$(".floor").each(function(index) {
							//$(this).offset({top:$(this).parent().position().top+FLOOR_BETWEEN * index});
							//var zIndex=$(this).css('z-index');
							//$(this).css('z-index', zIndex-index);
							$(this).Floor("animate");
						});
				    }else{
				    	$(".floor").each(function(index) {
				    		$(this).Floor("deAnimate");
				    		
				   		 });
					    }
				  });

			});
		</script>
	<button class="btn" id="toggleFloorView" data-toggle="button">Full View</button>
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

				var mapLocations=["${resource(dir: 'maps', file:'1_korrus_1.svg')}","${resource(dir: 'maps', file:'1_korrus_2.svg')}","${resource(dir: 'maps', file:'1_korrus_3.svg')}","${resource(dir: 'maps', file:'1_korrus_4.svg')}"]

				for(i in mapLocations){
						var floorName='floor'+i;
						$('<div id="'+floorName+'" class="floor"></div>').appendTo($("#floors"));
						$('#'+floorName).Floor({});
						$('#'+floorName).Floor("load", mapLocations[i]);
				}
				var firstFloorName="floor1"
				var svg = $('#'+firstFloorName).svg('get');
				var defs = svg.defs();
				var filter = svg.filter(defs, "myGaussianBlur", null, null, null, null, {
				//	filterUnits : "userSpaceOnUse"
				});
				svg.filters.gaussianBlur(filter,null, "SourceAlpha",  4);
				//svg.filters.offset(filter, "offsetBlur", "blur", 4, 4);
				$(parseSVG('<filter id="myGlowFilter"><feGaussianBlur stdDeviation="6" result="coloredBlur"/><feMerge><feMergeNode in="coloredBlur"/><feMergeNode in="SourceGraphic"/></feMerge></filter>')).appendTo(svg.defs());
				
				
				

			});
		</script>
		<div id="floors">

		</div>
	</div>
</body>
</html>
