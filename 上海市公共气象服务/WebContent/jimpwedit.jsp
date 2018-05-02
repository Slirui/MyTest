<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>极值查询-重要天气-编辑</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="commoncss/commoncss.css">
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<%-- <script type="text/javascript">
	function deleteConfirm(Id) {
		if (confirm("您确认要删除吗？")) {
			location.href = "deleteAnother.shtml?Id=" + Id;
		} else {
			return false;
		}
	}
</script> --%>
<style>
.etable table th, .etable table td {
	font-size: 10px;
}
</style>
</head>
<body>
	<div class="econtent">
		<div class="etable">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th>序号</th>
						<th>入春时间</th>
						<th>入夏时间</th>
						<th>入秋时间</th>
						<th>入冬时间</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<s:foreach list="${qr.list }" var="t" status="st">
						<tr>
							<td>${qr.start+st.index+1 }</td>
							<td>${s:formatDate(t.springDate,'yyyy-MM-dd') }</td>
							<td>${s:formatDate(t.summerDate,'yyyy-MM-dd') }</td>
							<td>${s:formatDate(t.autumnDate,'yyyy-MM-dd') }</td>
							<td>${s:formatDate(t.winterDate,'yyyy-MM-dd') }</td>
							<td>
								<button onclick="edit('getAnotherYearById.shtml?Id=${t.id}')"
									type="button" class="btn btn-lg btn-primary edit">编辑</button>
								<button onclick="del(${t.id})" type="button"
									class="btn btn-lg btn-primary delete">删除</button>
							</td>
						</tr>
					</s:foreach>
				</tbody>
			</table>

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
		</div>
	</div>

	<script>
		$(".etable tr:even td").css("background-color", "#f9fafb"); //改变偶数行背景色
		
		function del(Id) {
			BootstrapDialog.confirm({
				title: '提示',
	            message: '确定删除?',
	            type: BootstrapDialog.TYPE_WARNING,
	            closable: false,
	            btnCancelLabel: '取消',
	            btnOKLabel: '确定',
	            btnOKClass: 'btn-warning',
	            callback: function(result) {
	                if(result) {
	                	location.href = "deleteAnother.shtml?Id=" + Id;
	                	BootstrapDialog.close();
	                }else {
	                	return;
	                }
	            }
	        });
		}	
		function edit(url) {
			BootstrapDialog.show({
				title : '编辑',
				message : $('<iframe id="" src="'+url+'" width="100%" height="100px" scrolling="auto" frameborder="0"></iframe>'),
				buttons: [{
	                label: '确定',
	                cssClass: 'btn-primary',
	                action: function(dialogItself){
	                	var springDate = $("iframe").contents().find("#springDate").val(); 
	                	var summerDate = $("iframe").contents().find("#summerDate").val(); 
	                	var autumnDate = $("iframe").contents().find("#autumnDate").val(); 
	                	var winterDate = $("iframe").contents().find("#winterDate").val();
	                	var Id = $("iframe").contents().find("#anotherId").val();
	                	location.href="updateAnother.shtml?springDate="+springDate+"&summerDate="+summerDate+"&autumnDate="+autumnDate+"&winterDate="+winterDate+"&Id="+Id;
	                	dialogItself.close();
	                }
				}]
			});
		}
	</script>

</body>
</html>