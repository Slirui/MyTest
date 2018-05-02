<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重要天气</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript">
	$(function() {
		var content = $("[name='content']").val();
		if (content != "") {
			$("#imcedit").hide();
		}

	});
</script>
<style type="text/css">
</style>
</head>
<body>
	<form action="getImpWeatherContent.shtml" method="post">
			<div class="advice">
				<div class="adtitle">
					<img src="images/a.png" />
					<p>重要天气</p>
				</div>
				<div class="adcontent">
					<div class="adtxt">
						<s:foreach list="${list }" var="contents">
							${contents.contents }
						</s:foreach>
					</div>
				</div>
			</div>

			<div class="bottombtns">
				<button type="button" id="imcedit" class="bttn-1"
					onclick="edit('getEditImpWeatherInfo.shtml')"  style="${sessionScope.userName == 'admin'?'':'display:none;'}">修改</button>
				<button type="submit" class="bttn-1" style="margin-right: 20px;">查询</button>
				<input type="text" name="content" value="${content }" class="xiugaiinput"/>
			</div>
	</form>



	<script type="text/javascript">
		function edit(url) {
			BootstrapDialog
					.show({
						title : '编辑',
						message : $('<iframe src="'
								+ url
								+ '" width="100%" height="280px" frameborder="0" scrolling="yes" name="weatheredit"></iframe>'),
						size : 'size-wide',
						buttons : [ {
							label : '确定',
							cssClass : 'btn-primary',
							action : function(dialogItself) {
								var content = weatheredit.froalaEditor();
								$
										.ajax({
											url : 'saveImpWeatherUrlParam.shtml',
											type : 'POST',
											data : {
												"contents" : content
											},
											dataType : 'json',
											success : function(data) {
												location.href = "updateImpWeather.shtml";
											}
										});
								dialogItself.close();
							}
						} ]
					})
		}
	</script>
</body>
</html>