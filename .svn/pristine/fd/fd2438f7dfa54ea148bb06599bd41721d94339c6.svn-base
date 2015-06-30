<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<%
   String roleId=request.getParameter("roleId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色用户管理</title>
<style>
    *{font-size:14px;}
</style>
</head>
<body >
<div class="div_grid">
	<!-- 当前角色下面的数据列表加分页-->
	<table id="roleuserTable" title="用户列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	
	<div id="toolbar">
	  <a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddUser();">添加用户</a>
	 <!--   <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelUser();">批量删除</a>-->
    </div>
</div>

<!-- 当前角色新增用户 -->
<div id="userAdd"   icon="icon-save" class="easyui-dialog alldialog">
	<!-- 非当前角色下的用户列表 -->
	<table id="userTableNotInRole" title="角色用户列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	<div class="div_ul">
	   <ul>
	     <li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addUser();">添加</a>
		 </li>
	   </ul>
	</div>
</div>
   <script type="text/javascript">
   $(function(){
		//初始化页面模块
		initPage();
		//加载表格数据
		roleuserTable();
		//userTableNotInRole();
	});
   function initPage(){
		//closeFun:关闭窗口事件 可选    可添加关闭窗口事件
		var dialog_add = {
				id:"userAdd",
				title:"增加用户"
		}
		//新增窗口
		initDialog(dialog_add);
		$('#userAdd').dialog('close');
		
	}
   
   /**--------加载表格数据 ------*/
	function roleuserTable(){
		//加载表格
		$('#roleuserTable').datagrid({
			url: 'getUsersByRoleId?id='+<%=roleId%>,   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
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
						{field:'userId',checkbox:'true',width:20},//这些数据在data的集合json对象里面
						{field:'userName',title:'用户名称',width:100},
						{field:'email',title:'用户邮箱',width:100},
						{field:'mobile',title:'手机号码',width:100},
						{field:'tel',title:'电话号码',width:100},
						{field:'qq',title:'qq',width:100},
						{field:'groupIds',title:'所属组织',width:100,formatter:mdGroupFormater},
						{field:'opt',title:'操作',formatter:optFormater,width:150}
					]]
		});
		 
	}
   
	/**--------加载表格数据 ----userTableNotInRole--*/
	function userTableNotInRole(){
		//加载表格
		$('#userTableNotInRole').datagrid({
			url: 'getUsersNotInNowRoleId?id='+<%=roleId%>,  //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
			//toolbar:'#toolbar',//这是div的id属性值
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
						{field:'userId',checkbox:'true',width:20},//这些数据在data的集合json对象里面
						{field:'userName',title:'用户名称',width:100},
						{field:'email',title:'用户邮箱',width:100},
						{field:'mobile',title:'手机号码',width:100},
						{field:'tel',title:'电话号码',width:100},
						{field:'qq',title:'qq',width:100},
						{field:'roleName',title:'所属角色',width:100,formatter:mdRoleFormater},
						{field:'groupIds',title:'所属组织',width:100,formatter:mdGroupFormater}
					]]
		});
		 
	}
	//
	function optFormater(value,row,index){
		var _id = "'"+row.userId+"'";
		var del = '<a href="#" onclick=delUser('+_id+')>删除</a>';
		return del;
	};
	//获取所属角色
	function mdRoleFormater(value,row,index){
		if (row.umsrole){
			return row.umsrole.roleName;//根据角色获得角色名
		} else {
			return "无";
		}
	};
	//获取所属组织
	function mdGroupFormater(value,row,index){// 这里就不通过懒加载模式来获取值了，通过ajax后台取数据
		if (row.userId){
			// 如果存在userId,根据userId来获取所属组织
			var options={url:"<%=root%>/user/getStrGroupNameByUserId",data:"userId="+row.userId,callBackFun:function(data){
				if(data.isSuccessOrfail=="SUCCESS"){
					value=data.obj.groupName;
					if(data.obj.groupName==""){
						value="无";
					}
    			}
				else{
					value="无";
				}
			}};
			sendAjaxRequest(options);
			return value;
		}
	};
	
	//执行用户添加操作   上面的添加
	function openAddUser(){
		userTableNotInRole();
		//加载其树形
		//ajaxgroupTree("addgroupTree");
		$('#userAdd').dialog('open');//给那个div打开一个窗体，之前窗体已经生成
	}
	//执行用户添加操作    
	function addUser(){
			if($('#userTableNotInRole').datagrid('getSelected')){
				var ids = [];
				var ids_yz =[];
				var dpnames = [];
				var selectedRow = $('#userTableNotInRole').datagrid('getSelections');
				var count=0;
				for(var i=0;i<selectedRow.length;i++){
					ids.push(selectedRow[i].userId);
					ids_yz.push("'"+selectedRow[i].userId+"'");
					dpnames.push(selectedRow[i].userName);
					if(selectedRow[i].umsrole!=null){
						count++;
					}
				}
				var dpid = ids.join(',');
				var ids_yz = ids_yz.join(',');
				var dpname =dpnames.join(","); 
				if(count>0){
					$.messager.confirm('提示', '当前选中的用户中包含已有唯一角色,是否全部修改为当前角色? '+"\n"+dpname,function(r){
						//首先如果用户选择了数据，则获取选择的数据集合
						if(r){
							updateRole(dpid,ids_yz);
						}
					});
				}else{
					updateRole(dpid,ids_yz);
				}
			}
	}
	function updateRole(dpid,ids_yz){
		var url ='updateUserToRoleId';
		var options = {	url:url,
						data:{"ids":dpid,
							  "ids_yz":ids_yz,
							  "id":<%=roleId%>
							},
						callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
							if(data.isSuccessOrfail=="SUCCESS"){
								// 重新加载两个表格数据
								reloadTable("userTableNotInRole");
								reloadTable("roleuserTable");
							}
							showMessage(data);
						}
		
		}
		sendAjaxRequest(options);//调用ajax方法
	}
	/**
	 * 执行删除操作
	**/
	function delUser(id){	
		$.messager.confirm('提示', '你确定要删除当前角色下的该用户吗?',function(r){
			if(r){
				var url = 'delUserInNowRole';
				var options = {
						url:url,
						data:{"id":id,"rid":<%=roleId%>},
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								reloadTable("roleuserTable");
							}
							showMessage(data);
						}
				}
				sendAjaxRequest(options);
			}
		});
	}
   </script>
	</body>
</html>