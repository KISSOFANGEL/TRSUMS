<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/js_cs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/personpagesessioncheck.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>个人资料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
  <link rel="stylesheet" href="../css/formvalidate.css" type="text/css"></link>
  <link rel="stylesheet" href="../css/style.css" type="text/css"></link>
  <link rel="stylesheet" href="../js/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="../js/assets/css/admin.css">
  <link rel="stylesheet" href="../css/sweet-alert.css" type="text/css"></link>
  <script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
  <script type="text/javascript" src="../js/passwordStrength-min.js"></script>
  <script type="text/javascript" src="../js/sweetalert.js"></script>
<script type="text/javascript">
$(function(){
	$("#modifypassform").Validform({
		tiptype:3,
		btnSubmit:"#btn_sub", 
		ajaxPost:true,
		callback:function(data){
			infoalert();
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
						$("#passwordStrength").hide();
						//obj.parent().next().find(".passwordStrength").hide();
					}else{
						obj.parent().next().find(".Validform_checktip").hide();
						$("#passwordStrength").show();
						//obj.parent().parent().next().find(".passwordStrength").show();	
					}
				}
			}
		}
	});	 $("#Validform_msg").remove();
});
function infoalert(){
   swal({
     title: "",
     text: "亲！你确定要修改密码吗？",
     type: "warning",
     confirmButtonClass: 'btn-info',
     confirmButtonText: '确定'
   },function(isConfirm){   if (isConfirm) {    location.reload() ;} });
 }
</script>
</head>

<body >
  <div class="am-cf am-padding">
      <div class="am-fl am-cf"  ><strong class="am-text-primary am-text-lg">修改密码</strong> / <small>Modify Password</small></div>
    </div>
    <hr>
       <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
 
            </div></div></div></div>
 <div class="am-tab-panel am-fade am-in am-active" id="tab2">
        <form class="am-form form-horizontal" role="form" id="modifypassform" action="<%=root%>/user/updatePassword">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right " >
              原密码：
            </div>
            <div class="am-u-sm-4">
              <input type="password" name="oldPassWord" class="am-form-field form-control" placeholder="原密码" ajaxurl="<%=root %>/user/checkPasswordByUserName"  datatype="s"  >
            </div>           
            <div class="am-u-sm-6"> </div>
          </div>

			<div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right " >
              新密码：
            </div>
            <div class="am-u-sm-4">
                <div>  <input id="rgpassword" name="newPassword" type="password" class="form-control" nullmsg="请填写密码！"  placeholder="新密码" plugin="passwordStrength"  datatype="*6-18" errormsg="密码至少6个字符,最多18个字符！"/></div>
            </div> 
            <div class="passwordStrength am-u-sm-3" id="passwordStrength" style="display:none;width: 27%;"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></div>          
            <div class="am-u-sm-3 Validform_checktip"></div>
          </div>        
                    <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right " >
              再次输入密码：
            </div>
            <div class="am-u-sm-4">
              <input type="password" class="am-form-field form-control" recheck="newPassword" placeholder="请再次输入"  datatype="*6-18" errormsg="两次输入的密码不一致！" >
            </div>           
            <div class="am-u-sm-6">  </div>
          </div>

          <div class="am-fr" id="subbutton">
          <button type='button' class='btn btn-primary' id='btn_sub'>提交修改</button>
</div>
        </form>
      </div>

</body>
</html>
