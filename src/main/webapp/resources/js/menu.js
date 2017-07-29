/**
 * Set event after loading DOM
 */
$(document).ready(function(){
	
	// Setting jsTree
	$('#contentsTree').jstree({
		"core" : {
			'themes' : {			// themes config
				'name'       : false,	// theme name
				'url'        : false,	// Automatic CSS theme loading
				'dir'        : false,	// CSS themes destination path (url=true only)
				'dots'       : true,
				'icons'      : true,
				'stripes'    : true,
				'variant'    : false,	// 'large', 'small', 'responsive', false
				'responsive' : false,
			},
			'multiple'       : false,
			'animation'      : 100,		// mili sec, false
//			'data' : {	// ajax example
//				'url'        : 'ajax_nodes.html',
//				'data' : function (node) {
//					return { 'id' : node.id };
//				}
//			}
		}
	});
	
	// Click event for jsTree node
	$('#contentsTree').on('select_node.jstree', function(e, data){
		// Determines whether it is a leaf node
		if(data.instance.is_leaf(data.node)) {
			// If it is a leaf node, get the lower level anchor tag
			var atag = data.instance.get_node(data.node, true).children('a');
			// Open link of anchor tag in target frame
			window.open(atag.attr('href'), atag.attr('target'));
		}
	});
	
	// Click event for openall link
	$('#openall').on('click',function(){
		$("#contentsTree").jstree('open_all');
	});
	
	// Click event for openall link
	$('#closeall').on('click',function(){
		$("#contentsTree").jstree('close_all');
	});
	
});
