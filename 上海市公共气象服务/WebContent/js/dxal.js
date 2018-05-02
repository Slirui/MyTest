$(function() {
	$("#tree")
			.tree(
					{
						url : "getFolders.shtml",
						onLoadSuccess : function(node, data) {
							$("#tree").tree("collapseAll");
						},
						onClick : function(node) {
							var p = $("#tree").tree("getParent", node.target);
							var n = $("#tree").tree("isLeaf", node.target);
							if (n) {
								if (p) {
									var path = getNodePath(p, p.text);
									parent.pageGetInfo.location.href = "getFiles.shtml?root="
											+ encodeURIComponent(path)
											+ "&node="
											+ encodeURIComponent(node.text);
								} else {
									parent.pageGetInfo.location.href = "getFiles.shtml?node="
											+ encodeURIComponent(node.text);
								}
							} else {
								return;
							}
						}
					});

	$("#dxaldelete").click(
			function() {
				$("#leaf2").css("display", "none");
				$(".menu-shadow").css("display", "none");
				var node1 = $("#nodetxt1").val();
				var node2 = $("#nodetxt2").val();
				if (confirm("确认要删除吗？")) {
					location.href = "dxalDeleteNodes.shtml?node1="
							+ encodeURIComponent(node1) + "&node2="
							+ encodeURIComponent(node2) + "&root="
							+ encodeURIComponent(node1);
				} else {
					return;
				}
			})
})

function getNodePath(node, path) {
	var p = $("#tree").tree("getParent", node.target);
	var nPath = node.text;
	if (p) {
		var t = getNodePath(p, path);
		nPath = t + "/" + path;
	} else {
		nPath = "/" + nPath;
	}
	return nPath;
}