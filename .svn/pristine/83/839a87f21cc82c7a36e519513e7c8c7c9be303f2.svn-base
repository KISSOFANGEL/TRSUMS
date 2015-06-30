<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.trsnj.ums.util.DESUtil" %>
<%@ page import="com.trsnj.ums.util.CommonUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册成功，请验证邮箱</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
  <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="../js/bootstrap.min.js"></script>
  <style type="text/css">
  .navbar-default {
    background-color: #069;
}
.h{
margin-top: 10px;
margin-left: 300px;
font-size: 16px;
color:#FFF;
}
.t1 {
    position: absolute;
    left: 350px;
    top: 130px;
    margin-bottom: 10px;
    background: url("../images/smailreg2.png") no-repeat scroll left center transparent;
    padding-left: 100px;
}
.t1 h1 {
    color: #76AAD6;
    font-size: 28px;
    font-family: "Microsoft Yahei";
    margin-bottom: 13px;
    line-height: 1em;
}
.regconfirmbtn {
    position: absolute;
    left: 500px;
    top: 250px;
}
.regconfirmbtn a.input-submit:link, .regconfirmbtn a.input-submit:visited {
    font-size: 18px;
    font-family: "Microsoft Yahei";
    border: 1px solid #3B6E22;
    background-color: #5A9E41;
    border-radius: 5px;
    color: #FFF;
    padding: 0px 38px;
    height: 43px;
    line-height: 43px;
    display: inline-block;
    text-decoration: none;
    margin-right: 12px;
}
a:link, a:visited {
    color: #369;
    text-decoration: none;
}
  </style>
  </head>  
  <%
  String mail=request.getParameter("ml");// email是加密的
  mail=DESUtil.decryption(mail);
  String mailMarked=mail.substring(mail.lastIndexOf("@")+1,mail.lastIndexOf("."));
  String href="";
  if("qq".equals(mailMarked)){
      href="https://mail.qq.com/cgi-bin/loginpage";
  }else if("163".equals(mailMarked)){
      href="http://mail.163.com/";
  }else if("126".equals(mailMarked)){
      href="http://mail.126.com/";
  }
  else if("sina".equals(mailMarked)){
      href="http://mail.sina.com.cn/";
  } else if("sohu".equals(mailMarked)){
      href="http://mail.sohu.com/";
  }else if("yeah".equals(mailMarked)){
      href="http://www.yeah.net/";
  }else if("139".equals(mailMarked)){
      href="http://mail.10086.cn/";
  }else if("21cn".equals(mailMarked)){
      href="http://mail.21cn.com/";
  }else if("tom".equals(mailMarked)){
      href="http://web.mail.tom.com/webmail/login/index.action";
  }else{
      href="http://www.baidu.com/s?wd=%E9%82%AE%E7%AE%B1%E7%99%BB%E9%99%86";
  }
  %>
  <body>
   <nav class="navbar navbar-default" role="navigation">
    <div class="h">
      <span></span>
      <a style="margin-left: 600px;color: #FFF;" href="login.jsp">退出</a>
   </div>  
   </nav>
   <div >
   <div class="t1">
   <h1>注册成功, 请验证邮箱</h1>
               验证邮件已发送到
   <strong>
    <%=mail %>
   </strong>
      ，您需要登录邮箱完成认证，体验站内全部功能~
   </div>
   <div class="regconfirmbtn">
    <a class="input-submit" target="_blank" href="<%=href%>">登陆邮箱验证</a>
    <a href="#">暂不认证，继续访问>></a>
   </div>
   
   
   </div>
  
   
  </body>
</html>
