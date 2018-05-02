$(function() {
	// 重要天气的编辑查询
	$("#implwetherTree")
			.tree(
					{
						url : "getImpWeather.shtml",
						onLoadSuccess : function(node, data) {
							$("#implwetherTree").tree("collapseAll");
						　　    // 获取根节点
						　　	var rooNode = $("#implwetherTree").tree("getRoot");
						　　	// alert(rooNode.text);
						　　    // 调用expand方法
						　　	$("#implwetherTree").tree("expand",rooNode.text);
						　　	//alert(rooNode.text);
						},
						onClick : function(node) {
							if (node.id == 0) {
								return;
							} else {
								var n = $("#implwetherTree").tree("isLeaf",
										node.target);
								if (n) {
									parent.pageGetInfo.location.href = "getImpWeatherContent.shtml?node="
											+ encodeURIComponent(node.text);
								}
							}
						}/*,*/
						/* 编辑结点 */
						/*onDblClick : function(node) {
							if (node.text == "重要天气") {
								return;
							} else {
								$(this).tree('beginEdit', node.target);
								$("#nodeid").val(node.text);
							}
						},*/
						/* 获取编辑过的节点 */
						/*onAfterEdit : function(node) {
							$("#nodetxt3").val(node.text);
							$("#check").val(node.text);
							$("#nodelevel").val(node.attributes);
							// $("#nodeid").val(node.id);
							// $("#nodeid").val(node.text);
							location.href = "updateImpWeatherNode.shtml?node="
									+ encodeURIComponent($("#check").val())
									+ "&level="
									+ encodeURIComponent($("#nodelevel").val())
									+ "&id="
									+ encodeURIComponent($("#nodeid").val())
						},
						onContextMenu : function(e, node) { // 给结点添加右键菜单
							e.preventDefault(); // 阻止右键默认事件
							$(this).tree('select', node.target);
							$('#leaf').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
							$("#nodeid").val(node.attributes);
							$("#deletename").val(node.text);
							if (node.attributes != "1"
									&& node.attributes != "0") {
								$("#three").css("display", "block");
							} else {
								$("#three").css("display", "none");
							}
							var leafNode = $("#implwetherTree").tree("isLeaf",
									node.target);
							var nodePar = $("#implwetherTree").tree(
									"getParent", node.target);
							if (node.id == 0) {
								return;
							} else {
								if (leafNode) {
									if (nodePar.text != "重要天气") {
										$("#check").val(nodePar.text);
										$("#erji").val(node.text);
									} else {
										$("#check").val(node.text);
									}
								} else {
									$("#check").val(node.text);
								}
								if (node.attributes == "2" && leafNode == false) {
									var parent_node = $(this).tree('getParent',
											node.target);
									$("#check").val(parent_node.text);
									$("#erji").val(node.text);
								}
								if (node.attributes == "3" && leafNode == true) {
									var parent_node = $(this).tree('getParent',
											node.target);
									$("#erji").val(parent_node.text);
									var parent_node2 = $(this).tree(
											'getParent', parent_node.target);
									$("#check").val(parent_node2.text);
								}
							}
						}*/
					});

	$("#one").click(function() {
		$("#leaf").css("display", "none");
		$(".menu-shadow").css("display", "none");
		$("#impWeatherBDialog1", parent.document).click();
	})

	$("#two").click(function() {
		$("#leaf").css("display", "none");
		$(".menu-shadow").css("display", "none");
		$("#impWeatherBDialog2", parent.document).click();
	})
	$("#three").click(function() {
		$("#leaf").css("display", "none");
		$(".menu-shadow").css("display", "none");
		$("#impWeatherBDialog3", parent.document).click();
	})

	$("#delete").click(
			function() {
				$("#leaf").css("display", "none");
				$(".menu-shadow").css("display", "none");
				var id = $("#nodeid").val();
				var name = $("#deletename").val();
				if (confirm("确认要删除吗？")) {
					location.href = "deleteImpWeather.shtml?id="
							+ encodeURIComponent(id) + "&name="
							+ encodeURIComponent(name);
				} else {
					return;
				}
			})

})