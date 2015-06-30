<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
      String userName=(String)session.getValue("userName");
       if(userName!=null){
           
       }else{
           //默认是游客
           
       }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>wcm发布页面权限检测</title>
</head>
<body>
   <script type="text/javascript">
       var userName=<%=userName%>;
       if(userName==null||userName==""){
    	   alert("对不起，该页面访问受限！");
    	   parent.window.close();
       }
   </script>
</body>
</html>