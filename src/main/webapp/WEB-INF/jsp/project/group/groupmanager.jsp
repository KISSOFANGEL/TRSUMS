<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织管理</title>
<style>
    *{font-size:14px;}
</style>
</head>
<body >
<div class="div_grid">
<!-- 用户列表一览   数据表格-->
	<!-- 数据列表加分页-->
	<table id="groupTable" title="组织列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	
	<div id="toolbar">
	  <a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddGroup();">添加组织</a>
	  <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelGroup();">批量删除</a>
    </div>
</div>

<!-- 组织新增页面 -->
<div id="groupAdd"   icon="icon-save" class="easyui-dialog alldialog">
	<form id="groupAddForm">
	<input type="reset" id ="addReset">
	<div class="div_ul">
        <ul>
			<li>
				<label>组织名称：</label>
				<input type="text" class="combo" name="groupName" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>组织描述：</label>
				<input type="text" class="combo" name="groupDesc"   ></input>
			</li>
			<li>
				<label>排序：</label>
				<input type="text" class="combo" name="groupOrder" ></input>
			</li>
			<li>
				<label>父组织：</label>
				<select   cascadeCheck="true" id="addgroupTree"  name="parentId" class="easyui-combotree"  style="width:130px;" >
				</select>
			</li>
			<li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addGroup();">提交</a>
				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="groupAddReset();">重置</a>
			</li>
		 </ul>
		</div>
	</form>
</div>
<!--组织编辑页 面-->
<div id="groupEdit" icon="icon-save"  class="easyui-dialog alldialog" >
	<form id="groupEditForm">
		<input type="hidden" name="groupId"  id="id"></input>
		<div class="div_ul">
        <ul>
			<li>
				<label>组织名称：</label>
				<input type="text" class="combo" name="groupName" value="" id="edit_groupName" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>组织描述：</label>
				<input type="text" class="combo" name="groupDesc"  value="" id="edit_groupDesc" ></input>
			</li>
			<li>
				<label>排序：</label>
				<input type="text" class="combo" name="groupOrder" value="" id="edit_groupOrder"></input>
			</li>
			<li>
				<label>父组织：</label>
				<select  cascadeCheck="true" id="editgroupTree"  name="parentId" class="easyui-combotree"  style="width:130px;" >
				</select>
			 
			</li>
			<li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editGroup();">提交</a>
			</li>
		</ul>
		</div>
	</form>
</div>
<!-- 组织详细信息查看页面 -->

   <div id="groupInfo" icon="icon-save" class="easyui-dialog alldialog">
   <div class="div_ul">
        <ul >
			<li>
				<label>组织名称：</label>
				<input type="text" class="combo" id="info_groupName"  disabled="true"></input>
			</li>
			<li>
				<label>组织描述：</label>
				<input type="text" class="combo"  id="info_groupDesc"  disabled="true"></input>
			</li>
			<li>
				<label>创建时间：</label>
				<input type="text" class="combo" id="info_crutime"   disabled="true"></input>
			</li>
			<li>
				<label>创建者：</label>
				<input type="text" class="combo"  id="info_cruser"  disabled="true"></input>
			</li>
			
			<li>
				<label>排序：</label>
				<input type="text" class="combo"  id="info_groupOrder"  disabled="true"></input>
			</li>
			<li>
				<label>父组织：</label>
				<select   id="infogroupTree" class="easyui-combotree" disabled="true" style="width:130px;"  >
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
		//closeFun:关闭窗口事件 可选    可添加关闭窗口事件
		var dialog_add = {
				id:"groupAdd",
				title:"增加组织信息"
		}
		var dialog_edit = {
				id:"groupEdit",
				title:"修改组织信息"
		}
		var dialog_info = {
				id:"groupInfo",
				title:"查看组织信息"
		} 
		//新增窗口
		initDialog(dialog_add);
		$('#groupAdd').dialog('close');
		//编缉窗口
		initDialog(dialog_edit);
		$('#groupEdit').dialog('close');// 初始化三个窗体，然后隐藏起来
		//查看窗口
		initDialog(dialog_info);
		$('#groupInfo').dialog('close');
		
	} 
	/**  
	  加载父组织的树形
	**/
	function ajaxgroupTree(type){
		var options={
				url:'initgrouptree',  // 这是组织加载树形
				callBackFun:function(data){//这里的函数可以自己定义的ajax回调方法
					if(data.isSuccessOrfail=="SUCCESS"){
						if(type=="addgroupTree"){
							$('#addgroupTree').combotree('loadData', data.treeList);//data 是baseEntity实体里面的属性//返回一个TreeEntity的list的对象
							}
							if(type=="editgroupTree"){
							$('#editgroupTree').combotree('loadData', data.treeList);
							}
							if(type=="infogroupTree"){
							$('#infogroupTree').combotree('loadData', data.treeList);
							}
					   }
				}
		};
		sendAjaxRequest(options);
	}

	
	/**--------加载表格数据 ------*/
	function ajaxTable(){
		//加载表格
		$('#groupTable').datagrid({
			url: 'getGroups',   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
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
						{field:'groupId',checkbox:'true',width:20},//这些数据在data的集合json对象里面
						{field:'groupName',title:'组织名称',width:100},
						{field:'groupDesc',title:'组织描述',width:100},
						{field:'cruser',title:'创建者',width:100},
						{field:'crutime',title:'创建时间',width:100},
						{field:'parentId',title:'父组织',width:100,formatter:parentFormater},
						{field:'opt',title:'操作',formatter:optFormater,width:150}
					]]
		});
		 
	}
	function parentFormater(value,row,index){// 这里就不通过懒加载模式来获取值了，通过ajax后台取数据
		if(row.parentId==0){
			return "根组织";
		}
		if (row.parentId!=0){
			var options={
					   url:"getGroupByGroupId",
					    data:"groupId="+row.parentId,
					    callBackFun:function(data){
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
		else{
			return "无";
		}
	};
	
	/**
	 *  value 设置操作列的信息
	 * row   当前行的数据
	 * index 当前行的索引  从0 开始
	 */
	function optFormater(value,row,index){
		var _id = "'"+row.groupId+"'";
		var detail = '<a href="#" onclick=getInfo('+_id+',"info")>详细</a> |  ';
		var edit = '<a href="#" onclick=getInfo('+_id+',"edit")>修改</a> | ';//这里是修改，给edittree添加树形
		var del = '<a href="#" onclick=delGroup('+_id+')>删除</a>';
		return detail+edit+del;
	};
	
	// 这里的方法是编辑与详细时调用的方法能得到当前行的信息，后台是根据传的id到数据库里面查询得到的
	function getInfo(id,flag){
		var url = 'getGroupByGroupId?groupId='+id;//根据用户id获取用户信息
		var options = {
						url:url,
						callBackFun:function(data){
							if(flag=='edit'){
								ajaxgroupTree("editgroupTree");
								//加载用户信息
								$("#id").val(data.obj.groupId);
								$("#edit_groupName").val(data.obj.groupName);
								$("#edit_groupDesc").val(data.obj.groupDesc);
								$("#edit_groupOrder").val(data.obj.groupOrder);
								if(data.obj.parentId!=null&&data.obj.parentId!="")
								$('#editgroupTree').combotree("setValue",data.obj.parentId);
								else
									{
									$('#editgroupTree').combotree("setValue","0");
									}
								//打开用户编缉页面
								$('#groupEdit').dialog('open');

							}if(flag=='info'){
								ajaxgroupTree("infogroupTree");
								//加载用户信息
								$("#info_groupName").val(data.obj.groupName);
								$("#info_groupDesc").val(data.obj.groupDesc);
								$("#info_cruser").val(data.obj.cruser);
								$("#info_crutime").val(data.obj.crutime);
								$("#info_groupOrder").val(data.obj.groupOrder);
								if(data.obj.parentId!=null&&data.obj.parentId!="")
									$('#infogroupTree').combotree("setValue",data.obj.parentId);
									else
										{
										$('#infogroupTree').combotree("setValue","0");
										}
								//打开模块查看页面
								$('#groupInfo').dialog('open');
							}
							
						}
					};
				sendAjaxRequest(options);
		}
	//执行角色添加操作   上面的添加
	function openAddGroup(){
		ajaxgroupTree("addgroupTree");
		$('#groupAdd').dialog('open');//给那个div打开一个窗体，之前窗体已经生成
		$('#addgroupTree').combotree("setValue","0");//默认添加的是根组织
	}
	/**
	 * 执行添加操作
	**/
	function addGroup(){
		// js验证表单
		var isbol = Validator.Validate(document.getElementById("groupAddForm"),3);
		if(isbol){
			var options = {
					url: 'addGroup',//请求的action路径
					data :$("#groupAddForm").serialize(),
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							resetAddForm();// 将添加用户的框重置
							reloadTable("groupTable");//重新加载数据
		    			}
						showMessage(data);
					}
			};
			sendAjaxRequest(options);
		}
	}
	/**
	 * 执行修改操作
	**/
	function editGroup(){
		
		var isbol = Validator.Validate(document.getElementById("groupEditForm"),3);
		if(isbol){
			var options = {
					url: 'updateGroup'+"?id="+$("#id").val(),//请求的action路径
					data : $("#groupEditForm").serialize(),
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							reloadTable("groupTable");
		    			}
						showMessage(data);
					}
			};
			sendAjaxRequest(options);
		}
		
		
	}


	/**
	 * 执行删除操作
	**/
	function delGroup(id){	
		$.messager.confirm('删除提示', '你确定要删除该数据吗?',function(r){
			if(r){
				var url = 'delGroup';
				var options = {
						url:url,
						data:{"id":id},
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								reloadTable("groupTable");
							}
							showMessage(data);
						}
				}
				sendAjaxRequest(options);
			}
		});
	}


	/**
	 * 批量操作
	 * @return
	 */
	function batchDelGroup(){
		if($('#groupTable').datagrid('getSelected')){// 被选中
			var ids = [];
			var ids_yz =[];
			var dpnames = [];
			var selectedRow = $('#groupTable').datagrid('getSelections');
			for(var i=0;i<selectedRow.length;i++){
				ids.push(selectedRow[i].groupId);
				ids_yz.push("'"+selectedRow[i].groupId+"'");
				dpnames.push(selectedRow[i].groupName);
			}
			var dpid = ids.join(',');
			var ids_yz = ids_yz.join(',');
			var dpname =dpnames.join(","); 
			$.messager.confirm('删除提示', '你确定永久删除该数据吗? '+"\n"+dpname,function(r){
				//首先如果用户选择了数据，则获取选择的数据集合
				if(r){
					var url ='batchDelGroup';
					var options = {	url:url,
									data:{"ids":dpid,
										  "ids_yz":ids_yz
										},
									callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
										if(data.isSuccessOrfail=="SUCCESS"){
											reloadTable("groupTable");
										}
										showMessage(data);
									}
					
					}
					sendAjaxRequest(options);//调用ajax方法
				}
			});
		}
		
	}
	
	
	//这是添加模块后对里面的数据重置
	function resetAddForm(){
		$('#addReset').click();
		
	}
	//这是添加模块式的重置方法
	function roleAddReset()
	{
		resetAddForm();
	}
	</script>
	</body>
</html>