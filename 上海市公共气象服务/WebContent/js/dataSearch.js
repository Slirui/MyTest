$(function() {
	$('#Tree')
			.tree(
					{
						onClick : function(node) {
							$("#treeC").val(node.attributes.code);
							$("#treeT").val(node.text);
							$("#treeF")
									.val(
											$(this).tree("getParent",
													node.target).attributes.code);
							var Cid = $("#treeC").val();
							var Tid = $("#treeT").val();
							var fCid = $("#treeF").val();
							if (Cid == '11') {
								$("#table").attr("src", "getInfo.shtml");
							} else if (Cid == '302') { // 日降水量
							} else if (Cid == '301') { // 日雨量
								$("#table")
										.attr("src", "getRainTypePre1.shtml");
							} else if (fCid == "303" || fCid == "304"
									|| fCid == "305") {
								$("#table")
										.attr(
												"src",
												"getElementSort.shtml?dx="
														+ encodeURIComponent($(
																'#treeC').val()));
							} else if (fCid == '41') { // 四季
								$("#table")
										.attr(
												"src",
												"getAnotherYears2.shtml?dx="
														+ encodeURIComponent($(
																'#treeC').val()));
							} else if (fCid == '42') { // 梅雨
								$("#table")
										.attr(
												"src",
												"getAnotherYears2.shtml?dx="
														+ encodeURIComponent($(
																'#treeC').val()));
							} else if (fCid == '43') { // 高温
								$("#table")
										.attr(
												"src",
												"getAnotherYears2.shtml?dx="
														+ encodeURIComponent($(
																'#treeC').val()));
							} else if (Cid == '331') {
								$("#table").attr("src",
										"listPrecipitation.shtml");
							}
						}
					});
})