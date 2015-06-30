<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/js_cs.jsp" %>
<%
  String userStatus=(String)request.getAttribute("userStatus");
  String info=(String)request.getAttribute("info");
  // 登陆页如果有session就直接跳到个人中心
  String userName=(String)session.getValue("userName");
  String addUserType=session.getValue("addUserType")+"";
  if(userName!=null&&!"2".equals(addUserType)){
      //request.getRequestDispatcher("user_center.jsp").forward(request, response);
      response.sendRedirect(root+"/web/user_center.jsp");
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
  <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="../js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
  <link rel="stylesheet" href="../css/formvalidate.css" type="text/css"></link>
  <link rel="stylesheet" href="../css/style.css" type="text/css"></link>
   <link rel="stylesheet" href="../js/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="../js/assets/css/admin.css">
 
  <script type="text/javascript" src="../js/passwordStrength-min.js"></script>
   <script type="text/javascript">
 function changeimg	()
 {
 var myimg = document.getElementById("code"); 
 var now = new Date(); 
 myimg.src="makeCertPic.jsp?code="+now.getTime();
 } 
   var ajaxpic=false;
   
 	 function checkpic(){
 	    $.post("checkpic.jsp",
 	    {
 	      rand:$("#rand").val()
 	    },
 	    function(data,status){
 	    	if(data==3){
       		  ajaxpic=true;
       	  }else{
       		  ajaxpic=false;
       	  }
 	    });
 	  };
 		function valid(){
 			var username=$('#rgusername').val();
 			var rg=$('#rg').val();
 			var password=$('#rgpassword').val();
 			var email=$('#email').val();
 			var rand=$('#rand').val();
 		var reg= /\<|\>|%|\&|\+|\-/g;
 			if(rg==""){
 				alert('注册协议不可为空');
 				return false;
 			}
 			  if(username==""){
 				alert('用户名不可为空');
 				return false;
 			}  if(reg.test(username)){
 				alert('用户名含有非法字符');
 				return false;
 			}
 			  if(password==""){
 				alert('密码不可为空');
 				return false;
 			}
 			 if(password.length<6||password.length>18){
         		 alert('密码至少6个字符,最多18个字符');
  				return false;
         	  }
 			  if(email==""){
 				 alert('邮箱不可为空');
  				return false;  
 			  }
 		var reg1=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
 			  if(!reg1.test(email)){
 				 alert('邮箱格式不对 ');
   				return false;
 			  }
         	  if(rand==""){
 				alert('验证码不能为空');
 				return false;
 			}         	  
 			if(!ajaxpic){
 				alert('验证码错误');
 				return false;
 			}
 			return true;
 			
 		} 
 		
 		function check(){
 			var username=$('#username').val();
 			var password=$('#password').val();
 			var reg= /\<|\>|%|\&|\+|\-/g;
 			  if(username==""){
 				 $("#infodiv").css("visibility","visible");
 				 $("#info").html("用户名不能为空");
 				return false;
 			} 
 			 if(reg.test(username)){
 				$("#infodiv").css("visibility","visible");
				 $("#info").html("用户名不能含有非法字符");
  				return false;
  			}
 			  if(password==""){
 				 $("#infodiv").css("visibility","visible");
 				 $("#info").html("密码不能为空");
 	 				return false;
 	 			}
 			 $("#infodiv").css("visibility","hidden");
 			 return true;
 		}
 		
 		
 		function getcookie(objname){//获取指定名称的cookie的值 
 			var arrstr = document.cookie.split(";"); 
 			for(var i = 0;i < arrstr.length;i ++){ 
 			var temp = arrstr[i].split("="); 
 			if(temp[0] == objname)
 				return unescape(temp[1]); 
 			} 
 		 }
     function btn_logindao(){
    	 var flag=check();
    	 var checked=$("#remeberMe").val();// on 或者off
    	 if(flag==true){
    		 // 写cook
    		 if(checked=="on"){
    			 $.cookie('usernamecook', null);
    			 $.cookie('usernamecook',$('#username').val(),{expires:30});
    			 $.cookie('passwordcook', null);
    			 $.cookie('passwordcook',$('#password').val(),{expires:30});
    		 }else{
    			 $.cookie('usernamecook', null);
    			 $.cookie('passwordcook', null);
    		 }
    		 $("#loginform").submit();
    	 }
     }
     function submitkeyenvent(){
    	 var  keynum=event.keyCode;
    	 if(keynum==13){
    		 btn_logindao();
    	 }else{
    		 return;
    	 }
     }
 </script>
 <script type="text/javascript">
 $(function(){
	 // 引入cookie插件
	 jQuery.cookie = function(name, value, options) { 
    if (typeof value != 'undefined') { // name and value given, set cookie 
        options = options || {}; 
        if (value === null) { 
            value = ''; 
            options.expires = -1; 
        } 
        var expires = ''; 
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) { 
            var date; 
            if (typeof options.expires == 'number') { 
                date = new Date(); 
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000)); 
            } else { 
                date = options.expires; 
            } 
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE 
        } 
        var path = options.path ? '; path=' + options.path : ''; 
        var domain = options.domain ? '; domain=' + options.domain : ''; 
        var secure = options.secure ? '; secure' : ''; 
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join(''); 
    } else { // only name given, get cookie 
        var cookieValue = null; 
        if (document.cookie && document.cookie != '') { 
            var cookies = document.cookie.split(';'); 
            for (var i = 0; i < cookies.length; i++) { 
                var cookie = jQuery.trim(cookies[i]); 
                // Does this cookie string begin with the name we want? 
                if (cookie.substring(0, name.length + 1) == (name + '=')) { 
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1)); 
                    break; 
                } 
            } 
        } 
        return cookieValue; 
    } 
}; 
	 
	//点击切换的方法
		 $("#remeberMe").click(function (){
			      
			       if($(this).val()=="on"){
			    	   $(this).val("off"); 
			    	 }else{
			    		 $(this).val("on");  
			    	 }
            });
		// 获取指定名称的cook
		var usernamecook=$.cookie('usernamecook');
		var passwordcook=$.cookie('passwordcook');
		$('#username').val(usernamecook);
		$('#password').val(passwordcook);
		
	 $(".registerform").Validform({
			tiptype:2,
			btnSubmit:"#btn_sub", 
			ajaxPost:true,
			showAllError:false,
			datatype:{"checkbox":function(gets,obj,curform,regxp){
				//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
				if(obj[0].checked){//如果多个类型选择不能用0
					return true;
				}else{
					return false;
				}
				//注意return可以返回true 或 false 或 字符串文字，true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
			}},
			callback:function(data){
			if(data.isSuccessOrfail=="SUCCESS"){
				window.location.replace("mail.jsp?ml="+data.obj.email);
			}
			return true;
		},
			usePlugin:{
				passwordstrength:{
					minLen:6,
					maxLen:18,
					trigger:function(obj,error){
						//该表单元素的keyup和blur事件会触发该函数的执行;
						//obj:当前表单元素jquery对象;
						//error:所设密码是否符合验证要求，验证不能通过error为true，验证通过则为false;
						
						//console.log(error);
						if(error){
							obj.parent().next().find(".Validform_checktip").show();
							obj.parent().next().find(".passwordStrength").hide();
						}else{
							obj.parent().next().find(".Validform_checktip").hide();
							obj.parent().next().find(".passwordStrength").show();	
						}
					}
				}
			}
		});	
	 // 验证激活的用户状态
	 var userStatus='<%=userStatus%>';
	 var info='<%=info%>';
	 if(userStatus!=""&&userStatus.length>0&&userStatus!="null"){
		 $("#infodiv").css("visibility","visible");
		 $("#info").html(userStatus);
	 }
	 if(info!=""&&info.length>0&&info!="null"){
		 $("#infodiv").css("visibility","visible");
		 $("#info").html(info);
	 }
	 //
 });
 </script>
 <style type="text/css">
.regist{
width:600px;
}
 .Validform_checktip{
 font-size: 14px;
 }
  a:hover{text-decoration:none;color:#EC3410;} 
  body{min-height:initial;}
  
  
 </style>
  </head>
  
  <body style="background:url('../images/abstract_color_and_lights_deviant_skyblue2.jpg');">
  <div align="right" style="margin-right:50px;margin-top: 40px;">
  <form class="am-form-inline" id="loginform" role="form" method="post" action="logindao" onkeydown="submitkeyenvent()">
  <div class="am-form-group">
    <input type="text" id="username" name="userName" class="am-form-field" placeholder="用户名或邮箱"  >
  </div>

  <div class="am-form-group">
    <input type="password" id="password" name="passWord" class="am-form-field" placeholder="密码"  >
  </div>

  <div class="am-checkbox">
    <label>
      <input type="checkbox" id="remeberMe" checked="checked" value="on"> 记住我
    </label>
  </div>

  <button type="button"  class="am-btn am-btn-default" id="btn_login" onclick="btn_logindao()">登录</button>
   <a href="findpwd.jsp" style="margin-left:20px;color:#AAA6A6">忘记密码?</a>
</form>
  
 </div> 
 <div id="infodiv" style="border:0px solid #eee;width:200px;height:36px;margin-left:50%;margin-top:3px;padding: 5px 5px;visibility:hidden" ><span id="info" style="margin-left: 2%;color:red;">错误提示</span></div>
    
 <div align="right" >
   <form id="register"  role="form" action="<%=root%>/web/register" method="post" class="registerform">
    <h1 style="margin-right:450px;margin-top:10px;">用户注册</h1>
    <div>
    <div style="float: left;margin-left: 17%;">
       <img src="../images/umsloginleft.png" />
    </div>
    <div style="margin-right:50px;width: 600px;padding-top: 35px;">
    <div class="input-group regist" >
    <div><input id="rgusername" name="userName" type="text" class="form-control" nullmsg="请填写用户名！" ajaxurl="<%=root %>/user/getUserByUserName" style="width: 300px;" datatype="*2-16" errormsg="用户名至少2到16位任意字符！"  placeholder="用户名（唯一）" /></div>
    <div class="Validform_checktip"></div>
    </div>     
     <br/>
    <div class="input-group regist">
    <div>
     <input id="rgpassword" name="passWord" type="password" class="form-control" nullmsg="请填写密码！" style="width: 300px;" placeholder="密码" plugin="passwordStrength"  datatype="*6-18" errormsg="密码至少6个字符,最多18个字符！"/>
     
     </div>
     <div>
    <div class="Validform_checktip"></div>
     <div class="passwordStrength" style="display:none;"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></div>
    </div>
    </div>
     <br/>
      <div class="input-group regist">
    <div>
     <input id="rgpassword2" name="rgpassword2" type="password" class="form-control" tip="test" nullmsg="请再输入一次密码！" style="width: 300px;" placeholder="确认密码" recheck="passWord"  datatype="*" errormsg="您两次输入的密码不一致！"/>   
     </div>
    <div class="Validform_checktip"></div>
    </div>
    <br/>
     <div class="input-group regist">
    <div><input id="email" name="email" type="text" class="form-control"  nullmsg="请填写邮箱！" style="width: 300px;" ajaxurl="<%=root %>/user/getUserByEmail" placeholder="注册邮箱（唯一）" datatype="e"  errormsg="邮箱不正确！"/></div> 
     <div class="Validform_checktip"></div>
    </div>
     <br/>
     <div class="input-group " style="margin-right: 300px;"> 
    <input id="rand" name="rand" type="text" class="form-control" style="width: 135px;" placeholder="验证码" onmouseout="checkpic()"/>
     &emsp;&emsp;<span><img id="code" style="width: 135px; height: 34px;border: 1px solid #CCC;" src="makeCertPic.jsp" onclick="changeimg()" /></span>
    </div> 
    <!--添加网络协议确认按钮  -->
    <div class="input-group" style="margin-right: 0px;margin-top:10px;width:570px;">
      <div style="float:left;">
      <input type="checkbox" datatype="checkbox" style="margin-top:0px;margin-right:5px;"/>接受相关的网络安全协议。<a href="#" >《阅读本协议》</a></div>
      <div class="Validform_checktip"></div>
      
    </div>
   
     <div class="input-group"  style="margin-right:350px;">
    <button type="button"  class="btn btn-success" style="width: 200px;" id="btn_sub">注册</button>
    </div> 
   </div> 
    
    </div>
    </form>    
 </div>

   <script type="text/javascript">
     // 还没异步验证用户名，邮箱的唯一性
      function sendRegisterAjaxRequest(options){
    	$.ajax({
    		async : false,
    		cache:false,
    		type: "POST",
    		dataType:"json",
    		data : options.data,
    		url: options.url,//请求的action路径
    		error: function () {//请求失败处理函数
    			alert('请求失败');
    		},
    		success:function(data){// 这是ajax定义的回调函数  且将data传给可以是自己自定义的函数的参数
    			if(data.isSuccessOrfail=="SUCCESS"){
    				//当自定义回调函数为null时，调用默认函数处理
        			if(options.callBackFun==null){   //当回调函数未定义时
        				$.messager.show({title:'提示信息', msg:data.message});
        				//是否重新加载table
        				if(options.reloadTable!="undefined"){
        					reloadTable(options.reloadTable);//options.reloadTable为加载 表格的id
        				}
        			}else{//当有回调函数的时候，回调里面也加提示框来提示
        				options.callBackFun(data);//当回调函数已经定义时就直接调用  且将data传给可以是自己自定义的函数的参数
        			}
    			}else{
    				$("#infodiv").css("visibility","visible");
					 $("#info").html(data.message);
    				//$.messager.alert('错误提示',data.message,'error');
    			}
    		
    		}
    	});
    }
     //注册用户
	  function addUser(){
		     
				var options = {
						url: '<%=root%>/web/register',
						data :$("#register").serialize(),
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								// 如果成功，重置表单的数据
								$("#rgusername").val("");
								$("#rgpassword").val("");
								$("#email").val("");
								//跳转到指定的页面（用了返回的邮箱）
								window.location.replace("mail.jsp?ml="+data.obj.email);
			    			}
							
						}
				};
				sendRegisterAjaxRequest(options);
	      //  }
		}
   </script>
  </body>
</html>
