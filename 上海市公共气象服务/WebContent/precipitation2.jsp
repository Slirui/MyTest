<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/keantag" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>雨量信息分页查询</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript">
	$(function() {
		var precount = "${precount}";
		var start = "${start}";
		if (precount > 0 && start == 0) {
			BootstrapDialog.confirm({
				title : '查询提示',
				message : '<font style="color:red;font-weight:bold;">'
						+ '${site}有' + precount + '条缺失，请补齐！' + '</font>',
				type : BootstrapDialog.TYPE_WARNING,
				closable : false,
				btnCancelLabel : '取消',
				btnOKLabel : '确定',
				btnOKClass : 'btn-warning',
				callback : function(result) {
					return;
				}
			});
		}
		$(".sltresult tr:even td").css("background-color", "#f9f9f9"); //改变偶数行背景色

	});
	function search() {
		var site = $("[name='site']").val();
		parent.pageGetInfo.location.href = "listPrecipitation.shtml?name="
				+ encodeURIComponent(site) + "&site="
				+ encodeURIComponent(site);
	}
</script>

<style type="text/css">
input {
	width: 45px;
}
</style>
</head>
<body>
	<div class="topselect">
		<ul>
			<li>数据监控</li>
			<li>
				<div>
					<p>站名：</p>
					<c:select list="${siteNames }" value="${site }" name="site"></c:select>
				</div>
				<div>
					<button type="button" onclick="search()" class="bttn">查 询</button>
				</div>
			</li>
		</ul>
	</div>
	<div class="sltresult" style="width: 100%;">
		<div class="rsttitle">
			<p>查询结果</p>
		</div>
		<div align="center"
			style="line-height: 30px; width: 100%; font-family: '楷体'; font-size: 20px;">${site }数据库信息
		</div>
		<form action="updatePrecipitation.shtml" method="post">
			<div class="table">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<th>编号</th>
						<th>站点</th>
						<th>日期</th>
						<th>平均温度</th>
						<th>最高温度</th>
						<th>最低温度</th>
						<th>降水（2008）</th>
						<th>降水（0820）</th>
						<th>降水（2020）</th>
						<th>降水（0808）</th>
					</tr>
					<c:foreach list="${qr.list }" var="t" status="st">
						<input type="hidden" name="prelist[${st.index }].id"
							value="${t.id }" />
						<tr>
							<td>${qr.start+st.index+1 }</td>
							<td>${t.stationName }</td>
							<td>${c:formatDate(t.dateDate,'yyyy-MM-dd') }</td>
							<td><input type="text" name="prelist[${st.index }].temAvg"
								value="${t.temAvg }" /></td>
							<td><input type="text" name="prelist[${st.index }].temMax"
								value="${t.temMax }" /></td>
							<td><input type="text" name="prelist[${st.index }].temMin"
								value="${t.temMin }" /></td>
							<td><input type="text"
								name="prelist[${st.index }].preTime2008"
								value="${t.preTime2008 }" /></td>
							<td><input type="text"
								name="prelist[${st.index }].preTime0820"
								value="${t.preTime0820 }" /></td>
							<td><input type="text"
								name="prelist[${st.index }].preTime2020"
								value="${t.preTime2020}" /></td>
							<td><input type="text"
								name="prelist[${st.index }].preTime0808"
								value="${t.preTime0808 }" /></td>
						</tr>
					</c:foreach>
				</table>
			</div>
			<div class="pages">
				<div class="pages-l">
					共${qr.total}条 &nbsp;每页显示${qr.len}条 &nbsp;第<span>${qr.page }</span>/
					${qr.totalpage }页
				</div>
				<div class="pages-r">
					<a href="${qr.firstPageURL }" class="t1" title="首页"></a> <a
						href="${qr.previousPageURL }" class="t2" title="上一页"></a> <a
						href="${qr.nextPageURL }" class="t3" title="下一页"></a> <a
						href="${qr.moWeiPageURL }" class="t4" title="尾页"></a>
				</div>
			</div>
			<div align="center">
				<button type="submit" class="bttn-save">保 存</button>
			</div>
		</form>
	</div>
</body>
</html>