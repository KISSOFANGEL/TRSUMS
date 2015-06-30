<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>个人页面</title>
    
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
 background-color: #006699;
 }
 .navbar-default .navbar-brand {
 color:#FFF;
 }
 .navbar-default .navbar-text{
  color:#FFF;
 }
 .navbar-default .navbar-link {
   color:#FFF;
 }
.row-fluid > .span3{
width: 20%;
}
.nav-header{
color: #999999;
}
.well{
border:none;
box-shadow: none;
}
.row-fluid > .span9 {
    width: 78%;
}
.row-fluid > [class*="span"] {
    float: left;
    margin-left: 1%;
}
.span3{
border-right: 1px solid #D3CCCC;
border-top: 1px solid #D3CCCC;
border-bottom: 1px solid #D3CCCC;
}
.nav-header{
cursor: pointer;
}
 </style>
  </head>
  
  <body style="background-color:#F5F5F5;">
  <!-- 头部文件 -->
  <%@ include file="headpage.jsp" %>
   <div class="container-fluid">
   <div class="row-fluid">
   <div class="span3">
<ul class="nav nav-pills nav-stacked nav-list tree" role="tablist">
  <li role="presentation" class="active" onclick="refresh()"><a href="#">首页</a></li>
  <li role="presentation"><label class="tree-toggler nav-header">个人信息</label>
  <ul class="nav nav-list tree"> 
                             <li><a href="javascript:addTable('personal_data.jsp');">查看修改个人信息</a></li>
                        </ul></li>
    <li role="presentation"><label class="tree-toggler nav-header">我的动态</label>
  <ul class="nav nav-list tree"> 
                             <li><a href="#">动态信息</a></li>
                        </ul></li>
    <li role="presentation"><label class="tree-toggler nav-header">我的评议</label>
  <ul class="nav nav-list tree"> 
                              <li><a href="javascript:addTable('myComment.jsp');">我的评议</a></li>
                        </ul></li>
    <li role="presentation"><label class="tree-toggler nav-header">我的分享</label>
  <ul class="nav nav-list tree"> 
                            <li><a href="javascript:addTable('myShare.jsp');">我的分享</a></li>
                        </ul></li>
    <li role="presentation"><label class="tree-toggler nav-header">我的消息</label>
  <ul class="nav nav-list tree"> 
                            <li><a href="javascript:addTable('systemmessage.jsp');">系统消息</a></li>
              				<li><a href="javascript:addTable('myhelpmessage.jsp')">求助消息</a></li>
                        </ul></li>
    <li role="presentation"><label class="tree-toggler nav-header">我的收藏</label>
  <ul class="nav nav-list tree"> 
                             <li><a href="javascript:addTable('myCollect.jsp');">我的收藏</a></li>
                        </ul></li>
    <li role="presentation"><label class="tree-toggler nav-header">我的订阅</label>
  <ul class="nav nav-list tree"> 
                              <li><a href="javascript:addTable('mySubscription.jsp');">订阅信息</a></li>
                        </ul></li>
</ul>

          <!--div class="well sidebar-nav">
            <ul class="nav nav-list" style="margin-left: 30px;">
            <li class="nav-header" onclick="refresh()" style=" cursor: pointer;">首页</li>
              <li class="nav-header">个人资料</li>
              <li><a href="javascript:addTable('personal_data.jsp');">查看修改个人信息</a></li>
              <li class="nav-header">我的动态</li>
              <li><a href="#">动态信息</a></li>
             <li class="nav-header">我的评议</li>
              <li><a href="javascript:addTable('myComment.jsp');">我的评议</a></li>
               <li class="nav-header">我的分享</li>
              <li><a href="javascript:addTable('myShare.jsp');">我的分享</a></li>
               <li class="nav-header">我的消息</li>
              <li><a href="javascript:addTable('systemmessage.jsp');">系统消息</a></li>
              <li><a href="javascript:addTable('myhelpmessage.jsp')">我的求助</a></li>
              <!-- li class="nav-header">我的提醒</li>
              <li><a href="#">提醒信息</a></li-->
              <!-- li class="nav-header" >我的收藏</li>
              <li><a href="javascript:addTable('myCollect.jsp');">我的收藏</a></li>
              <li class="nav-header">我的订阅</li>
              <li><a href="javascript:addTable('mySubscription.jsp');">订阅信息</a></li>
           </ul>
          </div-->
        </div>
   <div class="span9" >
     <iframe id="iframetab" src="personal_default.jsp" width="99%" height="550px;" scrolling="no" style="border: 0px solid #D8D4D4;" sc>右边的框</iframe>
   </div>
   </div>
   </div>
  </body>
  <script type="text/javascript">
  $(function(){
	  $('label.tree-toggler').click(function () {
	        $(this).parent().children('ul.tree').toggle(300);
	    });
	  
  })
     function addTable(page){
    	  $("#iframetab").attr("src",page);
     }
     function refresh(){
    	location.reload();
    	 
     }
  </script>
</html>
