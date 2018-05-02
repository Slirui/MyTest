<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.task.CimissData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<!-- 树 -->
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">

<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />

<!-- 折叠面板 -->
<link href="commoncss/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="commoncss/panelstyle.css" media="screen"
	type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/panel.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="js/impWeather.js"></script>
<script type="text/javascript" src="js/holiday.js"></script>
<script type="text/javascript" src="js/dxal.js"></script>
<script type="text/javascript" src="js/cpmb.js"></script>
<script type="text/javascript" src="js/dateTime.js"></script>
<script type="text/javascript">
	var result = "${param.result}";
	if (result == "undefined" || result == "") {
		result = "${result}";
	}
	$(function() {
		var userName = "${sessionScope.userName}";
		if (userName == "") {
			window.top.location.href = "login.jsp";
			return;
		}
		//var timeOut = setInterval("cimissCount()", 1000 * 60 * 5);
		//var timeOut = setInterval("cimissCount()", 1000 * 10);
		if (result == "重要天气") {
			//默认展开
			$(".leftpanel .accordion li:nth-child(2) div").parent()
					.toggleClass("open");
			$(".leftpanel .accordion li:nth-child(2) ul").show();
			$("#table").attr("src", "getImpWeatherContent.shtml");
		} else {
			//默认展开
			$(".leftpanel .accordion li:nth-child(1) div").parent()
					.toggleClass("open");
			$(".leftpanel .accordion li:nth-child(1) ul").show();
			$("#table").attr("src", "getInfo.shtml");
		}
	});

	function cimissCount() {
		$
				.ajax({
					url : 'cimissCount.shtml',
					type : 'POST',
					dataType : 'json',
					success : function(result) {
						if (result.list.length > 0) {
							for (var i = 0; i < result.list.length; i++) {
								BootstrapDialog
										.show({
											title : '提示',
											message : '<font style="color:red;font-weight:bold;">'
													+ datetime(result.list[i].dateDate)
													+ '</font>' + "数据不全，请补齐！",
											msgId : result.list[i].id,
											buttons : [
													{
														label : '取消',
														action : function(
																dialogItself) {
															dialogItself
																	.close();
														}
													},
													{
														label : '确定',
														cssClass : 'btn-primary',
														action : function(
																dialogItself) {
															$
																	.ajax({
																		url : 'updateCimissCount.shtml',
																		type : 'POST',
																		dataType : 'json',
																		data : {
																			"id" : dialogItself.options.msgId
																		},
																		success : function() {
																		}
																	});
															dialogItself
																	.close();
														}
													} ],
										});
							}
						}
					}
				});
	}

	function pop(id) {
		var id = id;
		$(".pop_bg").fadeIn();
		$(".pop").fadeIn();
		var srcc = id + '.jsp';
		$(".pop iframe").attr("src", srcc);
	}

	//关闭窗口
	function close() {
		$(".pop_bg").fadeOut();
		$(".pop").fadeOut();
	}

	//确认
	function check() {
		$(".pop_bg").fadeOut();
		$(".pop").fadeOut();
	}
</script>
<style>
.accordion {
	border: none;
}

.modal-backdrop {
	display: none;
}
</style>

</head>
<body>
	<div class="title">
		<div class="titleson">
			<img src="images/title.png">
			<div class="user">
				<ul>
					<li><img class="u_icon" src="images/user.png">
						<p class="u_p">${sessionScope.userName }</p></li>
					<li style="${sessionScope.userName == 'admin'?'':'display:none;'}"><img class="u_icon" src="images/u_add.png">
						<p id="adduser" class="u_p cursor" onclick="pop(this.id)">添加用户</p>
					</li>
					<li><img class="u_icon" src="images/u_change.png">
						<p id="changepaw" class="u_p cursor" onclick="pop(this.id)">修改密码</p>
					</li>
					<li><img class="u_icon" src="images/exit.png"> <a
						href="loginout.shtml" title="注销" class="u_p cursor">注销</a></li>
				</ul>
			</div>
		</div>
	</div>
	<input id="treeC" type="text" style="display: none;">
	<input id="treeT" type="text" style="display: none;">
	<input id="treeF" type="text" style="display: none;">
	<div class="entity">
		<!--左侧折叠面板 -->
		<div class="leftpanel">
			<ul id="accordion" class="accordion">
				<li>
					<div class="link">
						数据查询<i class="fa fa-chevron-down"></i>
					</div>
					<ul class="submenu" id="Tree" class="easyui-tree"
						data-options="url:'json/yscx.json'"></ul>
				</li>

				<li>
					<div class="link">
						决策服务建议<i class="fa fa-chevron-down"></i>
					</div>
					<ul class="submenu">
						<li id="implwetherTree"></li>
						<li id="holidayTree"></li>
					</ul>
				</li>

				<li>
					<div class="link">
						典型案例<i class="fa fa-chevron-down"></i>
					</div>
					<ul class="submenu" id="tree"></ul>
				</li>

				<li><div class="link">
						产品模板<i class="fa fa-chevron-down"></i>
					</div>
					<ul class="submenu" id="tree2"></ul>
			</ul>
		</div>

		<!--右侧页面切换 -->
		<div class="rightiframe">
			<iframe id="table" src="getInfo.shtml" frameborder="0"
				name="pageGetInfo" height="100%" width="100%" scrolling="no"></iframe>
		</div>
	</div>

	<!-- 重要天气 -->
	<input id="nodelevel" type="hidden" value="">
	<input id="nodeid" type="hidden" value="">
	<input id="check" type="hidden" value="">
	<input id="erji" type="hidden" value="">
	<input id="deletename" type="hidden" value="" />

	<!-- 右击菜单 -->
	<div id="leaf" class="easyui-menu"
		style="width: 150px; display: none; border-radius: 5px; background-color: #ffffff;">
		<div id="one">添加一级节点</div>
		<div id="two" class="">添加二级节点</div>
		<div id="three" class="" style="display: none;">添加三级节点</div>
		<div id="delete" class="">删除</div>
	</div>

	<div id="impWeatherBDialog1"></div>
	<div id="impWeatherBDialog2"></div>
	<div id="impWeatherBDialog3"></div>

	<div class="pop_bg"></div>
	<div class="pop">
		<iframe id="fabuwindow" name="fabuwindow" width="100%" height="100%"
			frameborder="0" scrolling="no"></iframe>
	</div>

	<script src="js/impWeatherDialog.js"></script>
	<script src="js/dataSearch.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>