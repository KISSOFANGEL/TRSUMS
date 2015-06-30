<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/js_cs.jsp" %>
<%
   // String userName=(String)session.getAttribute("uesrName");
    String userName=(String)session.getValue("userName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头部</title>
</head>
<body>
     <nav class="navbar navbar-default" role="navigation">
   <!-- div class="navbar-header">
      <a class="navbar-brand" href="personage.jsp">首页</a>
   </div-->
   <div>
      <p class="navbar-text navbar-right" style=" margin-right: 200px;">
         <span id="userName" style="margin-right: 20px;">用户名</span>
         <a href="personal_center.jsp" class="navbar-link">首页</a>
         <a href="<%=root%>/web/quitSystem" class="navbar-link" style="margin-left: 20px;">退出</a>
      </p>
   </div>
   </nav>
   <script type="text/javascript">
       var userName='<%=userName%>';
       if(userName!=""&&userName.length>0&&userName!="null"){
    	   $("#userName").html(userName);
       }else {
    	   $("#userName").html("<a href='login.jsp'>登陆</a>");
       }
       //退出登录
       
   </script>
</body>
</html>