<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.task.CimissData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript">
	function register() {
		var userName = $("[name='userName']").val();
		var userPwd = $("[name='userPwd']").val();
		if (userName == "") {
			alert("用户名不能为空！");
		} else if (userPwd == "") {
			alert("密码不能为空！");
		} else {
			$.ajax({
				//几个参数需要注意一下
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "register.shtml",//url
				data : $('#form').serialize(),
				success : function(result) {
					if (result.resultCode == 200) {
						alert("注册成功");
						$(".pop_bg", parent.document).fadeOut();
						$(".pop", parent.document).fadeOut();
					}
				},
				error : function() {
					alert("注册失败");
				}
			});
		}
	}
</script>
<style>
</style>

</head>

<body>

	<form id="form">
		<div
			style="width: calc(100% - 10px); background-color: #29ba9c; padding: 10px 0 5px 10px; border-top-left-radius: 5px; border-top-right-radius: 5px;">
			<p
				style="display: inline-block; width: 90%; color: #fff; font-size: 15px; margin: 0;">添加成员</p>
			<p
				style="display: inline-block; float: right; color: #fff; font-size: 17px; margin: -5px 15px 0 0; cursor: pointer;"
				onclick="parent.close()">x</p>
		</div>

		<div class="u_content">
			<div class="uc_a">
				<p>用户名：</p>
				<input type="text" name="userName">
			</div>
			<div class="uc_a">
				<p>密&nbsp;&nbsp;码：</p>
				<input type="password" name="userPwd">
			</div>
		</div>

		<div class="u_btn">
			<input type="button" value="注册" onclick="register()"
				style="background-color: #29ba9c">
		</div>
	</form>
</body>
</html>