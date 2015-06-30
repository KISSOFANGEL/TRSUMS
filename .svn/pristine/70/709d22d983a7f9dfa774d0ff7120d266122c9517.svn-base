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
  <script type="text/javascript" src="../js/passwordStrength-min.js"></script>
  <script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
  <script type="text/javascript" src="../js/sweetalert.js"></script>
 <script type="text/javascript">
 
 function  initializeForm(){
	 $(".Validform_wrong").empty();
	 $("select").attr("disabled","true");
	 $("input").attr("readonly","readonly");//将input元素设置为readonly
	 $("#subbutton").css('display','none');
	 getCurrentUser();//加载个人信息
	 getCurrentCompanyInfo();//加载公司信息
 }
 function modifyForm(){
	 $("#subbutton").css('display','block');
	 $("select").removeAttr("disabled");
	 $("input").removeAttr("readonly");//去除input元素的readonly属性
	 $("#userName").attr("readonly","readonly");
	 $("#mail").attr("readonly","readonly");
	// $("#subbutton").html("<button type='button' class='btn btn-primary' id='btn_sub'>提交修改</button>");
 }
 $(function(){	
	 initializeForm();
	 $("#userform").Validform({
			tiptype:3,
			btnSubmit:"#btn_sub", //直接修改提交表单
			ajaxPost:true,
			callback:function(data){
				if(data.result=="suceess"){
					infoalert();			
				}		
			}	
	 });
	 $("#Validform_msg").remove();
 });
 function infoalert(){	
    swal({
      title: "",
      text: "信息更新成功！",
      type: "success",
      confirmButtonClass: 'btn-info',
      confirmButtonText: '确定'
    },function(isConfirm){   if (isConfirm) {   location.reload(); } });
  }
 // 加载当前用户的数据     
 function getCurrentUser(){
			var options = {
					url: '<%=root%>/user/getCurrentUser',//请求的action路径
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							// 如果成功，重置表单的数据
							//给dt赋值，给input赋值
							if(data.obj!=null){
								$("#userName").val(data.obj.userName);
								$("#relname").val(data.obj.relname);
								$("#address").val(data.obj.address);
								$("#QQ").val(data.obj.qq);
								$("#phone").val(data.obj.mobile);
								$("#mail").val(data.obj.email);
							}
						   //showMessage(data);
		    			}
						if(data.isSuccessOrfail=="FAIL"){
						//跳转到指定的页面
						window.location.replace('<%=root%>/web/login.jsp');
					  }
					}
			};
			sendAjaxRequest(options);
}
// 获取当前公司信息
 function getCurrentCompanyInfo(){
			var options = {
					url: '<%=root%>/user/getCurrentCompanyInfo',//请求的action路径
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							// 如果成功，重置表单的数据
							//给dt赋值，给input赋值
							if(data.obj!=null){
								$("#unitname").val(data.obj.companyName);
								$("#workaddress").val(data.obj.companyAddress);
								$("#department").val(data.obj.deptName);
								$("#companytel").val(data.obj.companytel);
								 $("#nature").find("option[value='"+data.obj.companyNature+"']").attr("selected",true);
							}
						   //showMessage(data);
		    			}
						if(data.isSuccessOrfail=="FAIL"){
						//跳转到指定的页面
						window.location.replace('<%=root%>/web/login.jsp');
					  }
					}
			};
			sendAjaxRequest(options);
}



 </script>
</head>
<body >
  <div class="am-cf am-padding">
      <div class="am-fl am-cf"  ><strong class="am-text-primary am-text-lg">查看修改个人信息</strong> / <small>View and Modify </small></div>
    </div>
    <hr>
       <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
       <div class="am-btn-group am-btn-group-xs "data-am-button >
    		<label class="am-btn am-btn-default am-btn-xs">
              <input type="radio" name="option" checked="checked" class="am-btn am-btn-default am-checked" ><div onclick="initializeForm()"><span class="am-icon-eye"></span> 查看</div>
              </label>
              <label class="am-btn am-btn-default am-btn-xs" >
              <input type="radio" name="option" class="am-btn am-btn-default" ><div onclick="modifyForm()"> <span class="am-icon-edit"></span> 修改</div>
            </label>
            </div>
            </div></div></div></div>
 <div class="am-tab-panel am-fade am-in am-active" id="tab2">
        <form class="am-form form-horizontal" role="form" id="userform" action="<%=root%>/user/updateCurrentUserAndCompanyInfo">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right " >
              用户名：
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-form-field" id="userName" >
            </div>
            <div class="am-u-sm-2 am-text-right">注册邮箱:</div>
            <div class="am-u-sm-4">  <input type="text" class="am-form-field" id="mail" ></div>
          </div>

          <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right">
            真实姓名：
            </div>
            <div class="am-u-sm-4 col-end">
              <input type="text" class="am-input-sm" id="relname" name="relname" datatype="s1-10" errormsg="姓名错误!">
            </div>
            <div class="am-u-sm-2 am-text-right">
            手机号：
            </div>
            <div class="am-u-sm-4 col-end">
              <input type="text" class="am-input-sm" id="phone" name="mobile" datatype="m" errormsg="请输入11位手机号！">
            </div>
              </div>
          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
            住址：
            </div>
            <div class="am-u-sm-4 col-end">
              <input type="text" class="am-input-sm" id="address" name="address" datatype="s1-40" errormsg="地址长度超出范围">
            </div>
            <div class="am-u-sm-2 am-text-right">
       QQ：
            </div>
            <div class="am-u-sm-4 col-end">
              <input type="text" class="am-input-sm" id="QQ" name="qq" datatype="n5-15" errormsg="请输入正确的QQ号">
            </div>

          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              单位电话：
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" id="companytel" name="companytel" datatype="/^[0-9]{3,4}-[0-9]{7,8}$/" errormsg="电话格式错误">
            </div>
            <div class="am-u-sm-6" style="padding-left:0px;padding-top:5px;"><font color="red">如:025-12345678</font></div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
             部门：
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" id="department" name="deptName" datatype="s1-15" errormsg="部门名称过长">
            </div>
            <div class="am-u-sm-2 am-text-right">
             单位性质：
            </div>
            <div class="am-u-sm-4">    <select id="nature" name="companyNature">
            <option value="0">请选择单位性质</option>
                <option value="1" >政府部门</option>
                      <option value="2">质监系统</option>
                      <option value="3">标准化院所</option>
                      <option value="4">行业协会</option>
                      <option value="5">企业</option>
                      <option value="6">检测认证机构</option>
                      <option value="7">高校</option>
                      <option value="8">其他科研院所</option>
              </select></div>
          </div>
          
		   <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              单位：
            </div>
            <div class="am-u-sm-6">
              <input type="text" class="am-input-sm" id="unitname" name="companyName" datatype="s1-30" errormsg="单位名称过长">
            </div>
            <div class="am-u-sm-4"></div>
          </div>
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right">
              单位地址：
            </div>
            <div class="am-u-sm-8">
              <input type="text" class="am-text-sm" id="workaddress" name="companyAddress" datatype="s1-40" errormsg="单位地址过长">
            </div>
            <div class="am-u-2"></div>
          </div>
          <div class="am-fr" id="subbutton">
          <button type='button' class='btn btn-primary' id='btn_sub'>提交修改</button>
</div>
        </form>
      </div>

</body>
</html>
