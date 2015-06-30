<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/js_cs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>找回密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
<link rel="stylesheet" href="../css/formvalidate.css" type="text/css"></link>
<link rel="stylesheet" href="../css/style.css" type="text/css"></link>
<link rel="stylesheet" href="../js/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="../js/assets/css/admin.css">

<style>
a:hover {
	text-decoration: none;
	color: #227dc5;
}

.centercontent {
	margin-left: auto;
	margin-right: auto;
	margin-top: 60px;
}
</style>
<script type="text/javascript">
	function changeimg() {
		var myimg = document.getElementById("code");
		var now = new Date();
		myimg.src = "makeCertPic.jsp?code=" + now.getTime();
	}
	var ajaxpic = false;

	function checkpic() {
		$.post("checkpic.jsp", {
			rand : $("#rand").val()
		}, function(data, status) {
			if (data == 3) {
				ajaxpic = true;
			} else {
				ajaxpic = false;
			}
		});
	};

	$(function() {
		$(".registerform").Validform({
			tiptype : 2,
			btnSubmit : "#btn_sub"
		});

	});
</script>
</head>
<body>
	<div class="centercontent" style="width: 45%" align="center">
		<h2>密码必须按注册邮箱来找回</h2>
		<form id="register" role="form" action="<%=root%>/web/findpwd" method="post"
			class="registerform">

			<div>

				<br />
				<div class="input-group regist">

					<div style="width:360px;">
						<input id="registeremail" name="registeremail" type="text"
							class="form-control" nullmsg="请填写邮箱！" style="width: 250px;"
							ajaxurl="<%=root %>/user/checkRegisterEmail" placeholder="注册邮箱"
							datatype="e" errormsg="邮箱格式不正确！" />
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<br />
				<div class="input-group " style="width:360px;">
					<label style="font-weight:600;">验证码:</label> <input id="rand"
						name="rand" type="text" class="form-control" style="width: 135px;"
						placeholder="验证码" onmouseout="checkpic()" /> &emsp;&emsp;<span><img
						id="code" style="" src="makeCertPic.jsp" onclick="changeimg()" />
					</span>
				</div>
				<br />
				<div class="input-group" style="">
					<button type="button" class="btn btn-success" id="btn_sub">提交</button>
				</div>
			</div>
		</form>
		<h6>找回密码后，原密码会停用，请在验证通过后重新设置新密码</h6>
	</div>
</body>
</html>