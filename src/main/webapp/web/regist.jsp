<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>用户注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="../js/layer/layer.min.js"></script>
    <style type="text/css">
   .xubox_main {
    height: 0px;
    }
    </style>
<script type="text/javascript">
$(function(){
	$("#bt").click(function(){
		$.layer({
		    type: 2,
		    shadeClose: true,
		    title: false,
		    closeBtn: [0, false],
		    shade: [0.8, '#000'],
		    border: [0],
		    offset: ['20px',''],
		    area: ['600px', '500px'],
		    iframe: {src: 'phone_checking.jsp'}
		}); 
	});
	
});

</script>
 </head>
  
  <body>
    <button id="bt">确定</button>
  </body>
</html>
