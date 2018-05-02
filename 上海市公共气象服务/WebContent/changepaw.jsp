<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.task.CimissData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript">
	function checkForm() {
		var name = $("[name='userName']").val();
		var pwd = $("[name='userPwd']").val();
		var pwd2 = $("[name='userPwd2']").val();
		if(pwd == ""){
			alert("新密码不能为空！");
		}else if (pwd2 == "") {
			alert("确认密码不能为空！");
		}else if(pwd != pwd2){
			alert("两次密码不一致！");
		} else {
			$.ajax({
				//几个参数需要注意一下
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "updatePwd.shtml",//url
				data : $('#form').serialize(),
				success : function(result) {
					if (result.resultCode == 200) {
						alert("修改成功，请重新登录！");
						$(".pop_bg",parent.document).fadeOut();
						$(".pop",parent.document).fadeOut();
						location.href = "loginout.shtml";
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

		<div class="u_content" style="padding: 15px 15px 0 15px;">
			<div class="uc_a">
				<p>&nbsp;用户名：</p>
				<input type="text" name="userName" value="${sessionScope.userName }"
					readonly="readonly">
			</div>

			<div class="uc_a">
				<p>&nbsp;新密码：</p>
				<input type="password" name="userPwd" />
			</div>
			<div class="uc_a">
				<p>确认密码：</p>
				<input type="password" name="userPwd2" />
			</div>
		</div>

		<div class="u_btn" style="padding-top: 0;">
			<input type="button" value="确定" onclick="checkForm()"
				style="background-color: #29ba9c">
		</div>
	</form>
</body>
</html>