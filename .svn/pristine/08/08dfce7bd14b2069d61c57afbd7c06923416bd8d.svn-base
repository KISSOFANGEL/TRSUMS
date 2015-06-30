<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送系统信息</title>
<style>
    *{font-size:14px;}
</style>
<link href="<%=root%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="../css/formvalidate.css" type="text/css"></link>
 <script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
   <!-- 字数限制 -->
  <script type="text/javascript" src="../js/messagebox.js"></script>
 <script>
$(function(){
	$("#mesform").Validform({
		 tiptype:3,
		ajaxPost:true
					
	 });
	$("#Validform_msg").remove();
	 $(".counted").charCounter(320,{container: "#counter"});
	
});
 
 $(function(){
	 $('#roleid').combobox('setValues', ['1']);
 });
  //发送系统信息
	 function sendMes(){
	$.messager.confirm('发送提示', '你确定要发送系统消息吗? ',function(r){
		if(r){
			var url ='<%=root %>/message/addSystemMes?roleid='+$('#roleid').combobox('getValues');
			var options = {	url:url,
							data:$(".form-horizontal").serialize(),
							callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
								if(data.isSuccessOrfail=="SUCCESS"){
									// 重置form表单
									$.messager.show({title:'提示信息', msg:'发送成功'});
									$('.form-horizontal')[0].reset();//表单重置
								}else{
									$.messager.alert('错误提示','发送失败','error');
								}
							}
			
			};
			   sendAjaxRequest(options);//调用ajax方法
		}
		
	});
	 }
 </script>
 
</head>
<body >
<div class="container">
	<div class="row">
      <div class="col-md-6 col-md-offset-3">
        <div class="well well-sm">
          <form class="form-horizontal" action="<%=root %>/message/addSystemMes" method="post" id="mesform">
          <fieldset>
            <legend class="text-center">发送系统信息</legend>
    
            <!-- Name input-->
            <div class="form-group">
              <label class="col-md-3 control-label" for="name">发送对象</label>
              <div class="col-md-9">
                   <select id="roleid" multiple="multiple" class="form-control easyui-combobox" name="roleids" style="width:160px;">
      		   <!--  <option value="0">所有用户</option>-->
         		<option value="1">普通用户</option>
         		<option value="2">认证用户</option>
         		<option value="3">VIP用户</option>
     			 </select>
              </div>
            </div>
       <!-- title input-->
            <div class="form-group">
              <label class="col-md-3 control-label" for="titel">信息标题</label>
              <div class="col-md-9">
                <input id="title" name="mestitle" type="text" datatype="*2-20" errormsg="用户名至少2到20位任意字符！" placeholder="信息标题" class="form-control">
              </div>
          
            </div>
            <!-- Message body -->
            <div class="form-group">
              <label class="col-md-3 control-label" for="message">发送内容</label>
              <div class="col-md-9">
                <textarea  	 id="message" name="mescon" placeholder="在这里输入正文信息" datatype="*2-320" errormsg="正文内容至少2到320位任意字符！" rows="6" class="form-control gray counted" ></textarea>
              	 <h6 class="pull-right" id="counter">还可输入320个字</h6>
              </div>
            </div>
    
            <!-- Form actions -->
            <div class="form-group">
              <div class="col-md-12 text-right">
                <button type="button" class="btn btn-primary btn-lg" onclick="sendMes()">发送</button>
              </div>
            </div>
          </fieldset>
          </form>
        </div>
      </div>
	</div>
</div>
	</body>
</html>