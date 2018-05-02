$(function() {
	$("#tree2")
			.tree(
					{
						url : "cpmbFolder.shtml",
						onLoadSuccess : function(node, data) {
							$("#tree2").tree("collapseAll");
						},
						onClick : function(node) {
							var n = $("#tree2").tree("isLeaf", node.target);
							var p = $("#tree2").tree("getParent", node.target);
							if (n) {
								var rootNode = $("#tree2").tree("getParent",p.target);
								parent.pageGetInfo.location.href = "cpmbFiles.shtml?node="
										+ encodeURIComponent(p.text)
										+ "&root="
										+ encodeURIComponent(rootNode.text)
										+ "&nodes="
										+ encodeURIComponent(node.text) + ".docx";
							} else {
								return;
							}
						}
					});
})
