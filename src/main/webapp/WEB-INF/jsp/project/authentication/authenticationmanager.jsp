<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>认证管理</title>
<style>
    *{font-size:14px;} 
</style>
</head>
<body >
<div class="div_grid">
<!-- 用户列表一览   数据表格-->
	<!-- 数据列表加分页-->
	<table id="authenticationTable" title="申请认证列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	
	<div id="toolbar">  
	  <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelAuthority();">批量删除</a>
    </div>
</div>


<!-- 组织详细信息查看页面 -->

   <div id="authenticationInfo" icon="icon-save" class="easyui-dialog alldialog">
   <div class="div_ul">
        <ul >
			<li>
				<label>用户名：</label>
				<input type="text" class="combo" id="info_userName"  disabled="true"></input>
			</li>
			<li>
				<label>注册邮箱：</label>
				<input type="text" class="combo"  id="info_email"  disabled="true"></input>
			</li>
			<li>
				<label>真实姓名：</label>
				<input type="text" class="combo"  id="info_relname"  disabled="true"></input>
			</li>
			<li>
				<label>注册时间：</label>
				<input type="text" class="combo" id="info_crutime"   disabled="true"></input>
			</li>
			<li>
				<label>手机号码：</label>
				<input type="text" class="combo"  id="info_mobile"  disabled="true"></input>
			</li>
			
			<li>
				<label>qq：</label>
				<input type="text" class="combo"  id="info_qq"  disabled="true"></input>
			</li>
			<li>
				<label>公司名称：</label>
				<input type="text" class="combo"  id="info_companyName"  disabled="true"></input>
			</li>
			<li>
				<label>所属部门：</label>
				<input type="text" class="combo"  id="info_deptname"  disabled="true"></input>
			</li>
			<li>
				<label>公司地址：</label>
				<input type="text" class="combo"  id="info_companyAddress"  disabled="true"></input>
			</li>
			<li>
				<label>公司电话：</label>
				<input type="text" class="combo"  id="info_companytel"  disabled="true"></input>
			</li>
			<li>
				<label>公司属性：</label>
				<select   id="info_companyNature"  disabled="true" style="width:130px;"  >
					 <option value="0">请选择单位性质</option>
		              <option value="1" >政府部门</option>
                      <option value="2">质监系统</option>
                      <option value="3">标准化院所</option>
                      <option value="4">行业协会</option>
                      <option value="5">企业</option>
                      <option value="6">检测认证机构</option>
                      <option value="7">高校</option>
                      <option value="8">其他科研院所</option>
				</select>
			</li>
		</ul>
		</div>
	</div>
	<script type="text/javascript">
	/**----------------加载整体表格,初始化总过树形-------------------------**/
	$(function(){
		//初始化页面模块
		initPage();
		//加载表格数据
		ajaxTable();
	});
	function initPage(){
		
		var dialog_info = {
				id:"authenticationInfo",
				title:"信息详情"
		} 
		
		//查看窗口
		initDialog(dialog_info);
		$('#authenticationInfo').dialog('close');
		
	} 
	
	/**--------加载表格数据 ------*/
	function ajaxTable(){
		//加载表格
		$('#authenticationTable').datagrid({
			url: '<%=root%>/authentication/getAuthenticationUsers',   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
			toolbar:'#toolbar',//这是div的id属性值
			checkOnSelect:true,
			pagination:true,//是否分页
			fitColumns:true,//列自适应表格宽度
			striped:true,//当true时，单元格显示条纹
			loadMsg:'数据加载中,请稍后...',
			onLoadError:function(){
				alert('数据加载失败!');
			},
			onLoadSuccess:function(data){
			},
			columns:[[
						{field:'messageid',checkbox:'true',width:20},//这些数据在data的集合json对象里面
						{field:'username',title:'用户名称',width:100},
						{field:'mescon',title:'请求',width:100},
						{field:'mestime',title:'请求时间',width:100},
						{field:'replytime',title:'审批时间',width:100},
						{field:'opt',title:'操作',formatter:optFormater,width:150}
					]]
		});
		 
	}
	
	/**
	 *  value 设置操作列的信息
	 * row   当前行的数据
	 * index 当前行的索引  从0 开始
	 */
	function optFormater(value,row,index){
		var _id = "'"+row.messageid+"'";
		var mesauthor_id = "'"+row.mesauthor+"'";
		var detail = '<a href="#" onclick=getInfo('+mesauthor_id+')>详细</a> |  ';
		var pass='';
		if(row.replytime!=null&&row.replytime!=""&&row.replytime!="未审批"){
			pass='已认证';
		}else{
		 pass = '<a href="#" onclick=passAuthentication('+_id+','+mesauthor_id+')>通过认证</a>';
		}
		return detail+pass;
	};
	
	// 这里的方法是编辑与详细时调用的方法能得到当前行的信息，后台是根据传的id到数据库里面查询得到的
	function getInfo(id){
		var url = '<%=root%>/authentication/getAuthenticationUserInfo?mesauthor='+id;//根据用户id获取用户信息
		var options = {
						url:url,
						callBackFun:function(data){
							
								$("#info_userName").val(data.userName);
								$("#info_email").val(data.email);
								$("#info_relname").val(data.relname);
								$("#info_crutime").val(data.crutime);
								$("#info_mobile").val(data.mobile);
								$("#info_qq").val(data.qq);
								$("#info_companyName").val(data.companyName);
								$("#info_companyAddress").val(data.companyAddress);
								$("#info_companytel").val(data.companytel);
								$("#info_deptname").val(data.deptName);
								$("#info_companyNature").val(data.companyNature);
								//打开模块查看页面
								$('#authenticationInfo').dialog('open');
							}
						
					};
				sendAjaxRequest(options);
		}
	//通过认证
	function passAuthentication(id,author){
		$.messager.confirm('认证提示', '通过后，该用户变成认证用户，确定执行此操作吗?',function(r){
			if(r){
				var url = '<%=root%>/authentication/passAuthenticationUser';//根据用户id获取用户信息
				var options = {
								url:url,
								data:{messageid:id,userid:author},
								callBackFun:function(data){
										if(data.isSuccessOrfail=="SUCCESS"){
											//重新加载数据
											reloadTable("authenticationTable");
										}
									}
								
							};
						sendAjaxRequest(options);
			}
		});
		}

	/**
	 * 批量操作
	 * @return
	 */
	function batchDelAuthority(){
		if($('#authenticationTable').datagrid('getSelected')){// 被选中
			var ids = [];
			var ids_yz =[];
			//var dpnames = [];
			var selectedRow = $('#authenticationTable').datagrid('getSelections');
			for(var i=0;i<selectedRow.length;i++){
				ids.push(selectedRow[i].messageid);
				ids_yz.push("'"+selectedRow[i].messageid+"'");
				//dpnames.push(selectedRow[i].groupName);
			}
			var dpid = ids.join(',');
			var ids_yz = ids_yz.join(',');
			//var dpname =dpnames.join(","); 
			$.messager.confirm('删除提示', '你确定永久删除该数据吗? ',function(r){
				//首先如果用户选择了数据，则获取选择的数据集合
				if(r){
					var url ='<%=root%>/authentication/batchDelAuthority';
					var options = {	url:url,
									data:{"ids":dpid,
										  "ids_yz":ids_yz
										},
									callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
										if(data.isSuccessOrfail=="SUCCESS"){
											reloadTable("authenticationTable");
										}
										showMessage(data);
									}
					
					}
					sendAjaxRequest(options);//调用ajax方法
				}
			});
		}
		
	}

	</script>
	</body>
</html>