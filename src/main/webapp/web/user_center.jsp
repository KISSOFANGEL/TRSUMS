<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<%@ include file="/WEB-INF/jsp/common/js_cs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/personpagesessioncheck.jsp" %>
<%--<%
    String userName=(String)session.getValue("userName");
%>
--%><html>
  <head>    
    <title>个人页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
  <link rel="icon" type="image/png" href="../js/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="../js/assets/i/app-icon72x72@2x.png">
   <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="../js/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="../js/assets/css/admin.css">
 <style>
     a:hover{text-decoration:none;} 
 </style>
  </head>
   
  <body >
  <!--[if lte IE 9]>
  <div class="am-alert am-alert-danger ie-warning" data-am-alert="">
  
  <button class="am-close" type="button">×</button>
  <div class="am-container">
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，TRS 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
  </div>
  </div>
<![endif]-->
  <header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>江苏省产权交易所</strong> <small>用户个人中心</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:addTable('myhelpmessage.jsp')"><img alt="新求助回复" id="helpimg"  src="<%=root%>/images/msg_deactive.gif"  />新求助回复 <span id="helpnum" class="am-badge am-badge-warning">0</span></a></li>
      <li><a href="javascript:addTable('systemmessage.jsp');"><img alt="系统消息" id="systemimg"  src="<%=root%>/images/msg_deactive.gif"  /><%--<span class="am-icon-envelope-o"></span>--%> 系统消息 <span id="systemnum" class="am-badge am-badge-warning">0</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" id="dropdownli" data-am-dropdown-toggle href="javascript:;">
           <span class="am-icon-users"></span><label id="usernamespan">管理员</label> 
        </a>
        <ul class="am-dropdown-content">
          <li><a href="javascript:addTable('personal_data.jsp');"><span class="am-icon-user"></span> 个人资料</a></li>
          <li><a href="<%=root%>/web/quitSystem"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <!-- li><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li-->
    </ul>
  </div>
</header>
<div class="am-cf admin-main">

  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
      <li><a href="javascript:addTable('cjs_personal_default.jsp');"><span class="am-icon-home"></span> 首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 查看/修改我的信息 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li><a href="javascript:addTable('personal_data.jsp');" class="am-cf"><span class="am-icon-info"></span> 个人资料<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
          <li><a href="javascript:addTable('modifyPassword.jsp');"><span class="am-icon-lock"></span> 修改密码</a></li>
          <!-- li><a href="admin-gallery.html"><span class="am-icon-th"></span> 相册页面<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
          <li><a href="admin-log.html"><span class="am-icon-calendar"></span> 系统日志</a></li>
          <li><a href="admin-404.html"><span class="am-icon-bug"></span> 404</a></li-->
        </ul>
      </li>
      <li><a href="javascript:addTable('cjs_myprogram.jsp');"><span class="am-icon-paw"></span> 我的项目</a></li>
     <!-- <li><a href="javascript:addTable('myComment.jsp');"><span class="am-icon-pencil-square-o"></span> 我的评论</a></li>
      <li><a href="javascript:addTable('myShare.jsp');"><span class="am-icon-puzzle-piece"></span> 我的分享</a></li>
      <li><a class="am-cf" data-am-collapse="{target: '#message-nav'}"><span class="am-icon-envelope-square"></span> 我的消息 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
      	<ul class="am-list am-collapse admin-sidebar-sub am-in" id="message-nav">
          <li><a href="javascript:addTable('systemmessage.jsp');" class="am-cf"><span class="am-icon-comment"></span> 系统消息<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
          <li><a href="javascript:addTable('myhelpmessage.jsp');" class="am-cf"><span class="am-icon-comments"></span> 求助消息<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
          </ul>
      </li>
      <li><a href="javascript:addTable('myCollect.jsp');"><span class="am-icon-tags"></span> 我的收藏</a></li>
      <li><a href="javascript:addTable('mySubscription.jsp');"><span class="am-icon-rss"></span> 我的订阅</a></li> --> 
      <li><a href="<%=root%>/web/quitSystem"><span class="am-icon-sign-out"></span> 注销</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>时光静好，与君语；细水流年，与君同。</p>
      </div>
    </div>

     
     </div> 
  <!-- sidebar end -->
  
 <div class="admin-content">

 <iframe id="iframetab" src="" width="96%" height="750px;" scrolling="no" style="border: 0px solid #D8D4D4; " >右边的框</iframe>
    </div>
   </div>
  <footer>
  <hr>
  <p class="am-padding-left" align="center">© 2014 TRS, 江苏省产权交易所</p>
</footer>
  <!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="../js/assets/js/polyfill/rem.min.js"></script>
<script src="../js/assets/js/polyfill/respond.min.js"></script>
<script src="../js/assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="../js/assets/js/jquery.min.js"></script>
<script src="../js/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="../js/assets/js/app.js"></script>
<script type="text/javascript">	
function addTable(page){
	  $("#iframetab").attr("src",page);
		$("#iframetab").load(function(){
			//var mainheight = $(this).contents().find("body").height()+30;
			//$(this).height(mainheight);
		});
}
$(function(){
	addTable("cjs_personal_default.jsp");
	
		}); 
	var userName='<%=userName%>';
	if(userName!=""&&userName.length>0&&userName!="null"){
		   $("#usernamespan").html(userName+'<span class="am-icon-caret-down"></span>');
	}else {
		   $("#usernamespan").html("<a href='login.jsp'>登陆</a>");
		   $("#dropdownli").removeClass("am-dropdown-toggle");
	}
/*
 * function addTable(page){
		var name=$("#iframetab").attr("name");
		if(name==""|| name!=page){
		$.get(page,function(data){
		 　　　　　　$("#iframetab").html(data); 
		 $("#iframetab").attr("name",page);
		 　　　　});
		}
		else{
			$.get(name,function(data){
			 　　　　　　$("#iframetab").html(data); 
			 　　　　});
		}
	}
	$(function(){
		
		addTable("personal_default.jsp");
		
			}); 
		
 */
 //获得新的系统消息的数目
   function getnewsysmesnum(){
	 var options={
				url:'<%=root%>/message/getnewsysmesnum',  
				callBackFun:function(data){//这里的函数可以自己定义的ajax回调方法
					if(data.isSuccessOrfail=="SUCCESS"){
					//返回total信息
					   var total=data.total;
						if(total!=0){
 						$('#systemnum').html(total);
 						$('#systemimg').attr("src","<%=root%>/images/icon-messenger.gif");
 					}
				}
			}
		};
		sendAjaxRequest(options);
 }
 //获得新的求助消息回复的数目
 function getnewreturnhelpnum(){
	 var options={
				url:'<%=root%>/message/getnewreturnhelpnum',  
				callBackFun:function(data){//这里的函数可以自己定义的ajax回调方法
					if(data.isSuccessOrfail=="SUCCESS"){
					//返回total信息
					   var total=data.total;
						if(total!=0){
 						$('#helpnum').html(total);
 						$('#helpimg').attr("src","<%=root%>/images/icon-messenger.gif");
 					}
				}
			}
		};
		sendAjaxRequest(options);
 }
 getnewsysmesnum();
 getnewreturnhelpnum();
</script>
  </body>
</html>
