<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>极值查询-重要天气-新增-编辑</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="commoncss/commoncss.css">
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
</script>
<style>
[class^="icon-"], [class*=" icon-"] {
	display: inline-block;
	width: 14px;
	height: 14px;
	*margin-right: .3em;
	line-height: 14px;
	vertical-align: text-top;
	background-image: url("images/glyphicons-halflings.png");
	background-position: 14px 14px;
	background-repeat: no-repeat;
	margin-top: 1px;
}

.icon-remove {
	background-position: -312px 0;
}

.icon-th {
	background-position: -240px 0;
}

.add-on {
	margin: 3px;
}

.add-on:hover {
	box-shadow: 0 0 10px #6699ff;
}

.icon-arrow-left {
	background-position: -240px -96px;
}

.icon-arrow-right {
	background-position: -264px -96px;
}

.impwadd .impwdate {
	height: 35px;
}
</style>
</head>
<body>
	<form action="updateAnother.shtml" method="post" id="myForm">
		<div class="impwaddEdit">
			<input type="hidden" name="Id" value="${ano.id }" id="anotherId" />
			<ul>
				<li>
					<p>入春时间：</p>
					<div class="controls input-append date form_date" data-date=""
						data-date-format="yyyy-MM-dd" data-link-field="dtp_input2"
						data-link-format="yyyy-MM-dd">
						<input type="text" name="springDate" id="springDate"
							value="${s:formatDate(ano.springDate,'yyyy-MM-dd') }" readonly>
						<span class="add-on"><i class="icon-remove"></i></span> <span
							class="add-on"><i class="icon-th"></i></span>
					</div>
				</li>
				<li>
					<p>入夏时间：</p>
					<div class="controls input-append date form_date" data-date=""
						data-date-format="yyyy-MM-dd" data-link-field="dtp_input2"
						data-link-format="yyyy-MM-dd">
						<input type="text" name="summerDate" id="summerDate"
							value="${s:formatDate(ano.summerDate,'yyyy-MM-dd') }" readonly>
						<span class="add-on"><i class="icon-remove"></i></span> <span
							class="add-on"><i class="icon-th"></i></span>
					</div>
				</li>
				<li>
					<p>入秋时间：</p>
					<div class="controls input-append date form_date" data-date=""
						data-date-format="yyyy-MM-dd" data-link-field="dtp_input2"
						data-link-format="yyyy-MM-dd">
						<input id="autumnDate" type="text" name="autumnDate"
							value="${s:formatDate(ano.autumnDate,'yyyy-MM-dd') }" readonly>
						<span class="add-on"><i class="icon-remove"></i></span> <span
							class="add-on"><i class="icon-th"></i></span>
					</div>
				</li>
				<li>
					<p>入冬时间：</p>
					<div class="controls input-append date form_date" data-date=""
						data-date-format="yyyy-MM-dd" data-link-field="dtp_input2"
						data-link-format="yyyy-MM-dd">
						<input id="winterDate" type="text" name="winterDate"
							value="${s:formatDate(ano.winterDate,'yyyy-MM-dd') }" readonly>
						<span class="add-on"><i class="icon-remove"></i></span> <span
							class="add-on"><i class="icon-th"></i></span>
					</div>
				</li>
			</ul>
		</div>

		<!-- <div class="btbtn">
			<button type="submit" class="btn btn-lg btn-primary commit">确定</button>
		</div> -->
	</form>
	<script>
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
		});
	</script>

</body>
</html>