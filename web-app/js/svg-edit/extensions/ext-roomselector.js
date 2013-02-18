
svgEditor.addExtension("Room Selector", function() {
	
		return {
/*			context_tools: [{
				type: "input",
				panel: "selected_panel",
				title: "Select Room",
				label:"",
				id: "room_id",
				defval: "none",
				events: {
					change:  function() { alert('Option was changed') } 
				}
			}],*/
			name: "Room Selector",
			selectedChanged: function(opts) {
				// Use this to update the current selected elements
				selElems = opts.elems;
				
				var i = selElems.length;
				
				while(i--) {
					var elem = selElems[i];
					
					if(elem) {
						console.log(elem.tagName );	
						console.log("Changing..");
						var v=elem.id;
						if(v){
							var v2=v.replace(/room_/g, '')
							if(v2){
								$('#room_id').val(v2);
								console.log(".. changed: "+v2);
							}
						}	
					}
				}
			},
			elementChanged: function(opts) {
				var elem = opts.elems[0];

			}
		};
});

