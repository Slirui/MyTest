<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重要天气(梅雨早晚)</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="commoncss/commoncss.css">
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#export").click(
				function() {
					var dx = $("[name='dx']").val();
					var modBeginYear = $("[name='modBeginYear']").val();
					var modEndYear = $("[name='modEndYear']").val();
					location.href = "exportDate.shtml?dx=" + encodeURIComponent(dx)
							+ "&modBeginYear=" + modBeginYear + "&modEndYear="
							+ modEndYear;
				});
	});
</script>

</head>
<body>
	<input type="hidden" name="dx" value="${dx }" />
	<input type="hidden" name="modBeginYear" value="${modBeginYear }" />
	<input type="hidden" name="modEndYear" value="${modEndYear }" />
	<div id="impwtab"
		style="width: 100%; margin: 10px 0; text-align: center;">
		<table class="table table-bordered"
			style="width: 800px; margin: 0 auto;">
			<tr>
				<td>排名</td>
				<td>最早日期</td>
				<td>最晚日期</td>
			</tr>
			<c:forEach items="${rcs }" begin="0" end="9" var="rc"
				varStatus="status">
				<tr>
					<td>${status.index + 1 }</td>
					<td>${rc.col1List }</td>
					<td>${rc.col2List}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center" style="width: 800px;">
		<button id="add" type="button" class="btn btn-lg btn-primary add">新增</button>
		<button id="edit" type="button" class="btn btn-lg btn-primary edit">编辑</button>
		<button id="export" type="button" class="btn btn-lg btn-primary edit">导出数据</button>
	</div>

	<script>
		$("#add").click(
				function() {
					BootstrapDialog.show({
						title : '新增',
						/* message : $('<div style="border: none;"></div>').load(
								'jimyadd.jsp'), */
						message : $('<div style="border: none;"></div>').load(
								'saveOrUpdateMod.shtml'),
					})
				})

		$("#edit")
				.click(
						function() {
							BootstrapDialog
									.show({
										title : '编辑',
										message : $('<iframe src="listMod.shtml" width="100%" height="350px" scrolling="auto" frameborder="0"></iframe>'),
										size : 'size-wide',
									})
						})
	</script>
</body>
</html>