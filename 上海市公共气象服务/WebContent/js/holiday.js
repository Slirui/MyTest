$(function() {
	// 国定假日服务
	$("#holidayTree")
			.tree(
					{
						url : "getHolidayTitle.shtml",
						onLoadSuccess : function(node, data) {
							$("#holidayTree").tree("collapseAll");
						},
						onClick : function(node) {
							if (node.id == 8) {
								return;
							} else {
								var n = $("#holidayTree").tree("isLeaf",
										node.target);
								if (n) {
									parent.pageGetInfo.location.href = "getHoliday.shtml?title="
											+ encodeURIComponent(node.text);
								}
							}
						}
					});
});