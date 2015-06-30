<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/js_cs.jsp" %>
<%
  String userStatus=(String)request.getAttribute("userStatus");
  String info=(String)request.getAttribute("info");
  // 登陆页如果有session就直接跳到个人中心
  String userName=(String)session.getValue("userName");
  String addUserType=session.getValue("addUserType")+"";
  if(userName!=null&&"2".equals(addUserType)){
      //request.getRequestDispatcher("user_center.jsp").forward(request, response);
      response.sendRedirect(root+"/user/main");
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户管理登陆页面</title>    
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <%--<script type="text/javascript" src="easyui/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
  <link rel="stylesheet" href="easyui/themes/icon.css" type="text/css"></link>
  --%><link rel="stylesheet" href="<%=root%>/easyui/themes/default/easyui.css" type="text/css"></link> 
<script type="text/javascript">
 $(function(){
		changeimg();	
		document.getElementById("username").focus();
 });
 var ajaxpic=false;
 var namestatus=false;
 function key(){
		 checkpic();
 }
 function changeimg(){
 	var myimg = document.getElementById("code"); 
 	var now = new Date(); 
 	myimg.src="makeCertPic.jsp?code="+now.getTime();
 } 
   
 function checkpic(){
 	    $.post("checkpic.jsp",{
 	      rand:$("#rand").val()
 	    },
 	    function(data,status){
 	    	if(data==3){
       		  ajaxpic=true;
       	  }else if(data==1){
       		  alert("用户不存在");
       		   ajaxpic=false;
       	  }else{
       		   ajaxpic=false;
       	  }
 	    });
 	  }
 function checkuserstatus(){
 	       
	       
 	  }

function valid(){
 			var username=$('#username').val();
 			var password=$('#password').val();
 			var rand=$('#rand').val();
 		var reg= /\<|\>|%|\&|\+|\-/g;
 			  if(username==""){
 				//alert('姓名不可为空');
 				$("#mesinfo").html("<font color='red'>姓名不可为空</font>");
 				return false;
 			}  if(reg.test(username)){
 				$("#mesinfo").html("<font color='red'>含有非法字符</font>");
 				return false;
 			}
 			  if(password==""){
 				 $("#mesinfo").html("<font color='red'>密码不能为空</font>");
 				return false;
 			}
         	  if(rand==""){
         		 $("#mesinfo").html("<font color='red'>验证码不能为空</font>");
 				return false;
 			}
         	  
 			if(!ajaxpic){
 				$("#mesinfo").html("<font color='red'>验证码错误</font>");
 				return false;
 			}
 			$("form")[0].submit();
 		    
 		}
 		
  $(function(){
	  var info='<%=info%>';
	  if(info!=null&&info!=""&&info!="undefine"&&info!="null"){
		  $("#mesinfo").html("<font color='red'>"+info+"</font>");
	  }
  });
 		
 
 </script>
<style type="text/css">
body {
	background: url("../images/bg.jpg");
}

.d {
	background: url('../images/login_bg.jpg') no-repeat scroll 0px 0px
		transparent;
	width: 526px;
	height: 323px;
	margin-left: 30%;
	padding-top: 138px;
	margin-top: 10%;
}

#username {
	background: url('../images/kuang1.jpg') no-repeat scroll 0% 0% transparent;
	padding-left: 30px;
	width: 158px;
	height: 28px;
}

#password {
	background: url('../images/kuang2.jpg') no-repeat scroll 0% 0% transparent;
	padding-left: 30px;
	width: 158px;
	height: 28px;
}

#rand {
	width: 80px;
	height: 28px;
}

#code {
	width: 60px;
	height: 25px;
}
</style>
</head>
<body>
	<div class="d">
		
		<div style="margin-top: -30px;margin-bottom: 10px;">
			<span id="mesinfo" style="color: #FFF;margin-left: 200px;">&nbsp;</span>
		</div>
		
		<form method="post" action="loginmanagerdao" id="loginform">
			<table style="margin: 0px 0px 0px 190px;">
				<tr>
					<td colspan="2" style="width:188px;"><input id="username"
						type="text" name="userName" /></td>
				</tr>
				<tr>
					<td colspan="2" style="width:188px;"><input id="password"
						name="passWord" type="password" />
					</td>
				</tr>
				<tr>
					<td><input id="rand" name="rand" type="text"
						onmouseout="checkpic()" onkeyup="key()" />
					</td>
					<td><img id="code" src="makeCertPic.jsp" /><a
						href="javascript:changeimg()" style="color: #FFF;margin-left: 10px;">看不清，换一张 </a></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="sb"
						style="width: 83px;height: 32px;" value="登录" onclick="valid()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>
