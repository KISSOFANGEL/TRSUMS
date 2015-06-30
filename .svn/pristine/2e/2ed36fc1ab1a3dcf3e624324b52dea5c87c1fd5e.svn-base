<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/custom.js"></script>
<title>Demo</title>
</head>
<body>
   <form action="demo/loadalldemo" method="post">
      <input type="text" name="demoName"  />
      <input type="submit" value="提交" />
   </form>
   <script type="text/javascript" >
   var root='<%=basePath%>';// js中的目录
   var options={url:root+"user/getUsers",data:"page=1&rows=10",callBackFun:function(data){
	   var rows=data.rows;
	   for(var i=0;i<rows.length;i++){
		   document.write(rows[i].userName);
		   document.write("<br/>");
	   }
	   
   }};
      sendAjaxRequest(options);
   
   
   </script>
</body>
</html>