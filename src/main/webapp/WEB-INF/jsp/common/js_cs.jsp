<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
String path = request.getContextPath();
String root = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript">
 var root = "<%=root%>"; //js中存放当前页面的root路径方便调用
</script>
<link href="<%=root%>/easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
<%--<link href="<%=root%>/css/icon.css" rel="stylesheet" type="text/css" />
--%>
<link href="<%=root%>/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=root%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=root%>/css/custom.css" rel="stylesheet" type="text/css" />


<script src="<%=root%>/easyui/jquery.min.js" type="text/javascript"></script>
<script src="<%=root%>/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=root%>/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=root%>/easyui/jquery.form.js" type="text/javascript"></script>
<script src="<%=root%>/js/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=root%>/js/bootstrap.min.js"></script>

<script src="<%=root%>/js/custom.js" type="text/javascript"></script>
<script src="<%=root%>/js/validator.js" type="text/javascript"></script>



