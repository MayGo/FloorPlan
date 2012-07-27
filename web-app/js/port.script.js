/**
 * @author Maigo Erit
 */
(function($) {

	var methods = {

		init : function(settings) {
			console.log("init:" + settings);
			var options = $.extend({}, $.fn.Floor.defaults, settings);
			$(this).data("settings", options);
			// $(this).bind("click", methods.myOtherFunction);
			this.svg();
		},
		colorRooms : function(rooms) {
			var $this = $(this);

			console.log("colorRooms:" + rooms);
			$.each(rooms, function(index, value) {
				console.log("colorRooms-color room:" + "#"
						+ $this.data("settings").roomPrefix + value);
				methods.colorRoom($this, $("#"
						+ $this.data("settings").roomPrefix + value))
			});
		},
		colorRoom : function($this, room) {
			console.log("color room:" + room);
			var prevRoom = $this.data("settings").prevRoom = $this
					.data("settings").activeRoom;
			var activeRoom = $this.data("settings").activeRoom = room;

			if (findShapeType(activeRoom))
				findShapeType(activeRoom).addClass("activeRoom");
		},
		deColorRooms : function() {
			$("g *").removeClass("activeRoom");
		},
		animate : function() {
			var scaleFactor=0.2;
			$(this).children(":first-child").children("g").animate({
				svgTransform : 'skewX(50) scale(1, '+scaleFactor+')'
			}, 0);
			
			var h=$(this).data("settings").h;
			var w=$(this).data("settings").w;
			$(this).svg('get').configure({height: h*scaleFactor, width:w}, true);
			
			methods.addNumbers()
		},
		deAnimate : function() {
			
			$(this).children(":first-child").children("g").animate({
				svgTransform : ''
			}, 0);
			var h=$(this).data("settings").h;
			var w=$(this).data("settings").w;
			$(this).svg('get').configure({height: h, width:w}, true);
			
			methods.addNumbers()
		},
		addNumbers : function() {
			$('#floors').children("span").remove();
			jQuery.each($("#ruuminr g"), function(i, val) {

				cutUid = val.id.match(/[\d\.]+/g);
				sp = $(
						'<span class="label label-info">' + cutUid
								+ '</span>').appendTo('#floors');
				sp.offset($(val).offset());
				// console.log($(val).offset());

				$('.p_' + val.id).click(function() {
					$($(this).attr("parentId")).trigger("click");
				});

			});
		},
		// myOtherFunction : function() {
		// // alert($(this).data("settings").activeRoom);
		// },

		load : function(map) {// loads a SVG map
			console.log("loadMap:" + map);
			var $this = $(this);

			allDoneLoading = function() {// gives rooms all nessesary
				// functionality
				console.log("allDoneLoadingFloors");
				methods.addNumbers()
				$("#ruuminr g").hover(function() {
					// $(this).children(":first-child").addClass("shadow_1");
					$(this).attr('filter', 'url(#myGlowFilter)');
				}, function() {
					// $(this).children(":first-child").removeClass("shadow_1");
					$(this).attr('filter', '');
				});
				$("#ruuminr g").click(function() {
					console.log("Ruumi nr" + this.id);
					methods.deColorRooms()
					methods.colorRoom($this, this)
					gotoMapUrl(this.id);
				})
				//$(this).svg('get').configure({viewBox: '0 0 1000 731.25'}, true);
				
				$(this).data("settings").h=parseInt($(this).children(":first-child").attr('height'));
				$(this).data("settings").w=parseInt($(this).children(":first-child").attr('width'));
			}
			this.svg('get').load(map, {
				changeSize : true,
				addTo : true,
				onLoad : allDoneLoading,
			});
		}

	};

	$.fn.Floor = function(method) {

		findShapeType = function(shopId) {// AI imports floors in various
			if (shopId) {
				console.log("findShapeType:" + shopId);
				// elements,
				// returns right one
				if ($('polygon', shopId).length > 0)
					return $('polygon', shopId);
				if ($('rect', shopId).length > 0)
					return $('rect', shopId);
				if ($('polyline', shopId).length > 0)
					return $('polyline', shopId);
				if ($('path', shopId).length > 0)
					return $('polyline', shopId);
			}
			return false;
		}

		gotoMapUrl = function(uid) {
			var cutUid = uid.match(/[\d\.]+/g);// take only number from string
			console.log("gotoMapUrl " + cutUid);
			window.location.href = "#/show/" + cutUid;
		}

		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(
					arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.tooltip');
		}

	};
	$.fn.Floor.defaults = {
		prevRoom : null,
		activeRoom : null,
		roomPrefix : "room",
		w:null,
		h:null
	};
})(jQuery);
