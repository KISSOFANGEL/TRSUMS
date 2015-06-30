<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/js_cs.jsp" %>
 <%
       // 将用户名存放在session里面
       String userName=(String)session.getAttribute("userName");
    String addUserType=session.getValue("addUserType")+"";
    if(userName!=null&&!"2".equals(addUserType)){
        //request.getRequestDispatcher("user_center.jsp").forward(request, response);
        response.sendRedirect(root+"/web/mlogin");
    }
    %>
<html>
<head>
    <title>用户管理系统</title>
   
    <link href="<%=root%>/css/layout.css" rel="stylesheet" type="text/css" />
<style type="text/css">

</style>
    <!-- 分类自加的样式 -->
    <style>
      .mycss_right_menu{list-style: none;font-size: 14px;padding-left: 0px;line-height: 30px;margin:0px}
      .mycss_right_menu li{border-bottom: 1px;border-bottom-style: dashed;border-bottom-color: rgb(88, 77, 77);}
      .mycss_right_menu a{text-decoration: none;margin-left: 40px}
      .mycss_right_menu li:hover{background-color: #eee}
    </style>
    
</head>
<body >
    <div class="easyui-layout" fit="true"  style="width:100%;height:100%;">
        <div region="north"  border="false"   id="north" >
         <a href="<%=root%>/web/quitManagerSystem"><img alt="退出系统"  align="right" src="<%=root%>/images/hd_loginout.jpg"></a>
         
        <a href="http://www.tbtguide.com:8084/monitor/count/UMStoMonitor.jsp?tablespace=flow" target="blank"> <img alt="进入统计"  align="right" src="<%=root%>/images/hd_bbs.jpg"></a>
        <a href="javascript:addTab('tableid_authenticationmanager','用户认证消息','<%=root %>/authentication/authenticationmanager')" target="blank"> <img alt="新认证消息" id="authorityimg"  align="right" src="<%=root%>/images/msg_deactive.gif" style="margin-top:12px;margin-right:20px;" /><font color="white" align="right" style="float:right;margin-top:18px;">新认证消息(<span id="authoritynum">0</span>)</font></a>
        <a href="javascript:addTab('tableid_messagehelpmanager','回复求助信息','<%=root %>/message/replyhelpmmessage')" target="blank"> <img alt="新求助消息" id="helpimg" align="right" src="<%=root%>/images/msg_deactive.gif" style="margin-top:12px;margin-right:20px;" /><font color="white" align="right" style="float:right;margin-top:17px;">新求助消息(<span id="helpnum">0</span>)</font></a>
        <h1 style="color: #FFF;">TRS用户管理系统</h1>
        <%-- <a><img alt="首页" align="right" src="<%=root%>/images/hd_home.jpg"></a>
        --%>
        </div>
        <div region="west" split="true" id="west" title="用户管理系统" style="width:18%">
	        <div id="right_panel" class="easyui-accordion" fit="true" border="false">
	            <div title="系统管理"  data-options="selected:true" style="padding:5px;">  
		            <ul class="mycss_right_menu">  
		                  <!--本来是已开通用户  -->
		                 <li><a href="javascript:addTab('tableid_alreadyUser','用户管理','user/alreadyUser')">用户管理</a></li>
		                 <%--
	                     <li><a href="javascript:addTab('tableid_vipUser','vip用户','user/vipUser')">vip用户</a></li>
	                     --%>
	                     <li><a href="javascript:addTab('tableid_awaitUser','待开通用户','user/awaitUser')">待开通用户</a></li>
	                     <!--因为在不同的命名空间下面，所以加个root根目录，root的java脚本在jsp页面嵌入了  -->
	                     <li><a href="javascript:addTab('tableid_rolemanager','角色管理','<%=root %>/role/rolemanager')">角色管理</a></li>
	                     <li><a href="javascript:addTab('tableid_groupmanager','组织管理','<%=root %>/group/groupmanager')">组织管理</a></li>
	                     
		            </ul>  
	             </div>  
	             <div title="角色用户管理">
	                 <!--角色树形遍历  -->
	                 <ul id="roleTree"></ul> 
	             </div>
	             <div title="组织用户管理">
	                 <!--组织树形遍历  -->
	                 <ul id="groupTree"></ul> 
	             </div>
	              <div title="消息中心">
	              <ul  class=" mycss_right_menu">
                        <li class="list-group-item"><a href="javascript:addTab('tableid_messagemanager','发送系统信息','<%=root %>/message/sendsystemmessage')">发送系统信息</a></li>
                        <li class="list-group-item"><a href="javascript:addTab('tableid_areadymessagemanager','已发送系统信息','<%=root %>/systemmessage/areadysystemmessage')">已发送系统信息列表</a></li>
                        <li class="list-group-item"><a href="javascript:addTab('tableid_messagehelpmanager','回复求助信息','<%=root %>/message/replyhelpmmessage')">回复求助信息</a></li>
                         <li><a href="javascript:addTab('tableid_authenticationmanager','用户认证消息','<%=root %>/authentication/authenticationmanager')">用户认证消息</a></li>
                    </ul>
	               
	             </div>
	        </div>
        </div>
        <script type="text/javascript">

        //$(function(){   //不要等页面加载完毕后在执行，否则下面的右击方法调用不了
    		
    		//给两个树形注册添加表事件
    		$("#roleTree").tree({
    			onClick:function(node){
    				if(node.id==0){
    					return;
    				}
    				addTab('tableroleid_'+node.id,node.text+'管理','<%=root %>/role/roleusermanager?roleId='+node.id);
    			}
    		});
    		$("#groupTree").tree({
    			onClick:function(node){
    				if(node.id==0){
    					return;
    				}
    				addTab('tablegroupid_'+node.id,node.text+'管理','<%=root %>/group/groupusermanager?groupId='+node.id);
    			}
    		});
    		// 添加面板事件
    		//var panel1 = $('#right_panel').accordion('getPanel',"角色用户管理");
    		//var panel2 = $('#right_panel').accordion('getPanel',"组织用户管理");
    		// 下面方法不用了，每次点击都刷新太耗内存了
    		$('#right_panel').accordion({onSelect:function(title,index){
    			//面板监听事件
    			if(title=="角色用户管理"){
    				//ajaxroleTree();
    			}
                if(title=="组织用户管理"){
            		//ajaxgroupTree();
    			}
    		}});
    		
            // 右键点击节点并显示快捷菜单
    		$('#roleTree').tree({
    			onContextMenu: function(e, node){
    				e.preventDefault();
    				// 查找节点
    				$('#roleTree').tree('select', node.target);
    				// 显示快捷菜单
    				if(node.id==0){
    				$('#rolerighthit_menu').menu('show', {
    					left: e.pageX,
    					top: e.pageY
    				});
    			  }
    			}
    		});
    		$('#groupTree').tree({
    			onContextMenu: function(e, node){
    				e.preventDefault();
    				// 查找节点
    				$('#groupTree').tree('select', node.target);
    				// 显示快捷菜单
    				if(node.id==0){
    				$('#grouprighthit_menu').menu('show', {
    					left: e.pageX,
    					top: e.pageY
    				});
    			  }
    			}
    		});
    	    //右击刷新事件
    	    function rolerefresh(){
    	    	//alert(1234);
    	    	ajaxroleTree();
    	    }
            function grouprefresh(){
            	ajaxgroupTree();
    	    }
    	//});
    	/**
    	 * 加载树型   
    	 */
    	function ajaxroleTree(){
    		var options={
    				url:'initroletreemain',  // 这是角色加载树形
    				callBackFun:function(data){//这里的函数可以自己定义的ajax回调方法
    					if(data.isSuccessOrfail=="SUCCESS"){
    						$('#roleTree').tree({lines:true,data:data.treeList});
    	    			}
    				}
    		};
    		sendAjaxRequest(options);
    	}
    	
    	function ajaxgroupTree(){
    		var options={
    				url:'initgrouptreemain',  // 这是组织加载树形
    				callBackFun:function(data){//这里的函数可以自己定义的ajax回调方法
    					if(data.isSuccessOrfail=="SUCCESS"){
    					$('#groupTree').tree({lines:true,data:data.treeList});
    				}
    			}
    		};
    		sendAjaxRequest(options);
    	}
    	//加载树型
		ajaxroleTree();
		ajaxgroupTree();
		//获得新的认证信息
		function getnewAuthoritynum(){
			var options={
    				url:'<%=root%>/authentication/getnewAuthoritynum',  
    				callBackFun:function(data){//这里的函数可以自己定义的ajax回调方法
    					if(data.isSuccessOrfail=="SUCCESS"){
    					//返回total信息
    					var total=data.total;
    					if(total!=0){
    						$('#authoritynum').html(total);
    						$('#authorityimg').attr("src","<%=root%>/images/icon-messenger.gif");
    					}
    				}
    			}
    		};
    		sendAjaxRequest(options);
		}
		//获得新的求助信息
		function getnewHelpnum(){
			var options={
    				url:'<%=root%>/message/getnewHelpnum',  
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
		getnewAuthoritynum();
		getnewHelpnum();
        </script>
        <!--右击事件菜单  -->
		<div id="rolerighthit_menu" class="easyui-menu" style="width:120px;">
			<div onclick="rolerefresh()" >刷新</div>
		</div>
		<div id="grouprighthit_menu" class="easyui-menu" style="width:120px;">
			<div onclick="grouprefresh()" >刷新</div>
		</div>
        <div region="center" split="true"   id="center">
	        <div class="easyui-tabs" id="centerTab" fit="true" border="false">  
	            <div title="欢迎页" style="padding:20px;overflow:hidden;">   <!--一个页面  -->
	                <div style="margin-top:20px;">  
	                    <h2>你好，欢迎<%=userName %>使用用户管理系统</h2>  
	                </div>  
	            </div>  
	        </div> 
        </div>
        
        <div region="south" border="false" id="south" >
        <h3 align="center">trs公司版权信息</h3>
        </div>
    </div>
</body>
</html>