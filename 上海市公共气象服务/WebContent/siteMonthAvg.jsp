<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平均值查询</title>
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function search() {
		var site = $("[name='site']").val();
		parent.pageGetInfo.location.href = "getInfo.shtml?site="
				+ encodeURIComponent(site);
	}
</script>
</head>

<body>
	<div class="select">
		<ul>
			<li>平均值查询</li>
			<li>
				<div>
					<p>气象站名：</p>
					<s:select list="${siteNames }" value="${site }" name="site"
						style="width:220px;"></s:select>
				</div>
			</li>
		</ul>
	</div>
</body>
</html>