<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<style>
    *{font-size:14px;}
</style>
</head>
<body >
<div class="div_grid">
<!-- 用户列表一览   数据表格-->
	<!-- 数据列表加分页-->
	<table id="alreadyuserTable" title="用户列表" cellspacing="0" cellpadding="0" width="100%"  iconCls="icon-edit"></table>
	
	<div id="toolbar">
	  <a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddUser();">添加用户</a>
	  <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelUser();">批量删除</a>
	  <!--搜索框  begin-->
	  <input class="easyui-searchbox" data-options="prompt:'请输入查询值',menu:'#mm',searcher:doSearch" style="width:300px"></input>
		<div id="mm" style="width:120px">
			<div data-options="name:'userName'">用户名</div><!--,iconCls:'icon-ok'  -->
			<div data-options="name:'email'">邮箱</div>
		</div>
	  <!--搜索框  end-->
		<script>
			function doSearch(value,name){
				var param=name+'@'+value;
				ajaxTable(param);
			}
		</script>
    </div>
</div>

<!-- 用户新增页面 -->
<div id="userAdd"   icon="icon-save" class="easyui-dialog alldialog">
	<form id="userAddForm">
	<input type="reset" id ="addReset">
	<div class="div_ul">
        <ul>
			<li>
				<label>用户名：</label>
				<input type="text" class="combo" name="userName" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>密码：</label>
				<input type="password" class="combo" name="passWord"  id="passWord" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>确认密码：</label>
				<input type="password" class="combo" name="confirpassWord" dataType="Compare" to="passWord" msg="密码不一致"></input>
			</li>
			<li>
				<label>邮箱：</label>
				<input type="text" class="combo" name="email" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>手机号码：</label>
				<input type="text" class="combo" name="mobile" ></input>
			</li>
			<li>
				<label>电话号码：</label>
				<input type="text" class="combo" name="tel" ></input>
			</li>
			<li>
				<label>QQ：</label>
				<input type="text" class="combo" name="qq" ></input>
			</li>
			<li>
				<label>地址：</label>
				<input type="text" class="combo" name="address" ></input>
			</li>
			<li>
				<label>角色：</label>
				<select  id="addroleTree"  name="umsroles" class="easyui-combotree"  style="width:130px;" >
				</select>
			</li>
			<li>
				<label>组织：</label>
				<select  multiple="multiple" cascadeCheck="false" id="addgroupTree"  name="groups" class="easyui-combotree"  style="width:130px;" >
				</select>
			</li>
			<li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addUser();">提交</a>
				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="userAddReset();">重置</a>
			</li>
		 </ul>
		</div>
	</form>
</div>
<!--用户编辑页 面-->
<div id="userEdit" icon="icon-save"  class="easyui-dialog alldialog" >
	<form id="userEditForm">
		<input type="hidden" name="userId"  id="id"></input>
		<div class="div_ul">
        <ul>
			<li>
				<label>用户名：</label>
				<input type="text" class="combo" name="userName" value="" id="edit_userName" dataType="Require" msg="不能为空"></input>
			</li>
			<%--<li>
				<label>密码：</label>
				<input type="text" class="combo" name="passWord"  value="" id="edit_passWord"  dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>确认密码：</label>
				<input type="text" class="combo" name="confirpassWord" value="" id="edit_confirpassWord" dataType="Require" msg="不能为空"></input>
			</li>
			--%><li>
				<label>邮箱：</label>
				<input type="text" class="combo" name="email" value="" id="edit_email" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>手机号码：</label>
				<input type="text" class="combo" name="mobile" value="" id="edit_mobile" ></input>
			</li>
			<li>
				<label>电话号码：</label>
				<input type="text" class="combo" name="tel"  value="" id="edit_tel"></input>
			</li>
			<li>
				<label>QQ：</label>
				<input type="text" class="combo" name="qq" value="" id="edit_qq" ></input>
			</li>
			<li>
				<label>地址：</label>
				<input type="text" class="combo" name="address" value="" id="edit_address" ></input>
			</li>
			<li>
				<label>角色：</label>
				<select  id="editroleTree"  name="umsroles" class="easyui-combotree"  style="width:130px;" >
				</select>
			</li>
			<li>
				<label>组织：</label>
				<select multiple="multiple" cascadeCheck="false" id="editgroupTree"  name="groups" class="easyui-combotree"  style="width:130px;" >
				</select>
			 
			</li>
			<li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editUser();">提交</a>
			</li>
		</ul>
		</div>
	</form>
</div>
<!-- 用户详细信息查看页面 -->

   <div id="userInfo" icon="icon-save" class="easyui-dialog alldialog">
   <div class="div_ul">
        <ul >
			<li>
				<label>用户名称：</label>
				<input type="text" class="combo" id="info_userName"  disabled="true"></input>
			</li>
			<li>
				<label>邮箱：</label>
				<input type="text" class="combo"  id="info_email"  disabled="true"></input>
			</li>
			<li>
				<label>手机号码：</label>
				<input type="text" class="combo" id="info_mobile"   disabled="true"></input>
			</li>
			<li>
				<label>电话号码：</label>
				<input type="text" class="combo"  id="info_tel"  disabled="true"></input>
			</li>
			<li>
				<label>QQ：</label>
				<input type="text" class="combo" id="info_qq"   disabled="true"></input>
			</li>
			<li>
				<label>地址：</label>
				<input type="text" class="combo"  id="info_address"  disabled="true"></input>
			</li>
			<li>
				<label>角色：</label>
				<select id="inforoleTree" class="easyui-combotree" disabled="true" style="width:130px;"  >
				</select>
			</li>
			<li>
				<label>组织：</label>
				<select multiple="multiple" cascadeCheck="false" id="infogroupTree" class="easyui-combotree" disabled="true" style="width:130px;"  >
				</select>
			</li>
		</ul>
		</div>
	</div>
<!-- 管理员用户的密码修改框 -->

   <div id="updatesec" icon="icon-save" class="easyui-dialog alldialog">
   
   <form id="updatesecForm">
   <input type="reset" id ="updatesecReset" style="display:none"/>
   <input type="hidden" name="userId"  id="secid"></input>
   <div class="div_ul">
        <ul >
			<li>
				<label>原密码：</label>
				<input type="password" class="combo"  name="oldpassword"  ></input>
			</li>
			<li>
				<label>新密码：</label>
				<input type="password" class="combo"  name="password" id="password" dataType="Require" msg="不能为空" ></input>
			</li>
			<li>
				<label>重复密码：</label>
				<input type="password" class="combo"   id="repassword" dataType="Compare" to="password" msg="密码不一致" ></input>
			</li>
			<li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="updatesec();">提交</a>
			</li>
		</ul>
		</div>
	</form>	
	</div>	
	<script type="text/javascript">
	/**----------------加载整体表格,初始化总过树形-------------------------**/
	$(function(){
		//初始化页面模块
		initPage();
		//加载表格数据
		ajaxTable();
		//加载树型
		//ajaxroleTree();
		//ajaxgroupTree();
		
	});

	function initPage(){
		//closeFun:关闭窗口事件 可选    可添加关闭窗口事件
		var dialog_add = {
				id:"userAdd",
				title:"增加用户信息"
		};
		var dialog_edit = {
				id:"userEdit",
				title:"修改用户信息"
		};
		var dialog_info = {
				id:"userInfo",
				title:"查看用户信息"
		}; 
		var dialog_updatesec = {
				id:"updatesec",
				title:"修改密码"
		}; 
		//新增窗口
		initDialog(dialog_add);
		$('#userAdd').dialog('close');
		//编缉窗口
		initDialog(dialog_edit);
		$('#userEdit').dialog('close');// 初始化三个窗体，然后隐藏起来
		//查看窗口
		initDialog(dialog_info);
		$('#userInfo').dialog('close');
		//密码修改窗口
		initDialog(dialog_updatesec);
		$('#updatesec').dialog('close');
		
	}
	/**--------加载表格数据 ------*/
	function ajaxTable(param){
		//加载表格
		$('#alreadyuserTable').datagrid({
			url: 'getUsers',   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
			queryParams:{searchparam:param},
			toolbar:'#toolbar',//这是div的id属性值
			checkOnSelect:true,
			pagination:true,//是否分页
			fitColumns:true,//列自适应表格宽度
			striped:true,//当true时，单元格显示条纹
			autoRowHeight:true,
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
						{field:'roleId',title:'所属角色',width:100,formatter:mdRoleFormater},//这里的formatter是给前面赋值的
						{field:'groupIds',title:'所属组织',width:100,formatter:mdGroupFormater},
						{field:'opt',title:'操作',formatter:optFormater,width:150}
					]]
		});
		 
	}


	/**
	 * 加载树型   是给添加用户,修改用户与查询用户详细添加树形结构
	 */
	function ajaxroleTree(type){
		var options={
				url:'initroletree',  // 这是角色加载树形
				callBackFun:function(data){//这里的函数可以自己定义的ajax回调方法
					if(data.isSuccessOrfail=="SUCCESS"){
						if(type=="addroleTree"){
						$('#addroleTree').combotree('loadData', data.treeList);//data 是baseEntity实体里面的属性//返回一个TreeEntity的list的对象
						}
						if(type=="editroleTree"){
						$('#editroleTree').combotree('loadData', data.treeList);
						}
						if(type=="inforoleTree"){
						$('#inforoleTree').combotree('loadData', data.treeList);
						}
	    			}
				}
		};
		sendAjaxRequest(options);
	} 
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

	/**
	 *  value 设置操作列的信息
	 * row   当前行的数据
	 * index 当前行的索引  从0 开始
	 */
	function optFormater(value,row,index){
		var _id = "'"+row.userId+"'";
		var detail = '<a href="#" onclick=getInfo('+_id+',"info")>详细</a> |  ';
		var edit = '<a href="#" onclick=getInfo('+_id+',"edit")>修改</a> | ';//这里是修改，给edittree添加树形
		var del = '<a href="#" onclick=delUser('+_id+')>删除</a>';
		var extra='';
		if(row.addUserType==1){
			extra+=' |<a href="#" onclick=resetsec('+_id+')>重置密码</a>';
		}else if(row.addUserType==2){
			extra+=' |<a href="#" onclick=showpassworddialog('+_id+')>修改密码</a>';
		}
		return detail+edit+del+extra;
	};
	//修改密码
	function showpassworddialog(_id){
		$('#secid').val(_id);
		$('#updatesec').dialog('open');
		
	}
	//重置密码（只适合注册用户）
	function resetsec(id){
		$.messager.confirm('密码重置提示', '你确定要重置当前注册用户的密码吗?',function(r){
			if(r){
				var url = '<%=root %>/user/resetsec';
				var options = {
						url:url,
						data:{"id":id},
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								$.messager.show({title:'提示信息', msg:'密码重置成功'});
							}
							showMessage(data);
						}
				}
				sendAjaxRequest(options);
			}
		});
	}
	//修改密码（只适合后台用户）
	function updatesec(){
		var isbol = Validator.Validate(document.getElementById("updatesecForm"),3);
		if(isbol){
		$.messager.confirm('密码修改提示', '你确定要修改当前管理用户的密码吗?',function(r){
			if(r){
				var url = '<%=root %>/user/updatesec';
				var options = {
						url:url,
						data:$("#updatesecForm").serialize(),
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								$.messager.show({title:'提示信息', msg:'密码修改成功'});
								$('#updatesec').dialog('close');//关闭div
								$('#updatesecReset').click();
							}
							showMessage(data);
						}
				};
				sendAjaxRequest(options);
			}
		});
	  }
	}
	
	// 这两个方法，是列表中生成的值时所提供的方法！
	function mdRoleFormater(value,row,index){
		if (row.umsrole){
			return row.umsrole.roleName;//根据角色获得角色名
		} else {
			return "无";
		}
	};
	function mdGroupFormater(value,row,index){// 这里就不通过懒加载模式来获取值了，通过ajax后台取数据
		if (row.userId){
			// 如果存在userId,根据userId来获取所属组织
			var options={url:"getStrGroupNameByUserId",data:"userId="+row.userId,callBackFun:function(data){
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
	// 这里的方法是编辑与详细时调用的方法能得到当前行的信息，后台是根据传的id到数据库里面查询得到的
	function getInfo(id,flag){
		if(flag=='edit'){
			ajaxroleTree("editroleTree");
			ajaxgroupTree("editgroupTree");
			}
		if(flag=='info'){
			ajaxroleTree("inforoleTree");
			ajaxgroupTree("infogroupTree");
			}
		var url = 'getUserByUserId?userId='+id;//根据用户id获取用户信息
		var options = {
						url:url,
						callBackFun:function(data){
							if(flag=='edit'){
								//加载用户信息
								$("#id").val(data.obj.userId);
								$("#edit_userName").val(data.obj.userName);
								//$("#edit_passWord").val(data.obj.passWord);
								//$("#edit_confirpassWord").val(data.obj.passWord);
								$("#edit_email").val(data.obj.email);
								$("#edit_mobile").val(data.obj.mobile);
								$("#edit_tel").val(data.obj.tel);
								$("#edit_qq").val(data.obj.qq);
								$("#edit_address").val(data.obj.address);
								//给两个树形设置具体的值
								if(data.obj.umsrole!=null)
								$("#editroleTree").combotree("setValue",data.obj.umsrole.roleId);
								if(data.obj.umsrole==null)
								$('#editroleTree').combotree("setValue","-1");
								//根据userid获得组织的id
								var value="";
								var options={url:"getStrGroupIdByUserId",data:"userId="+id,callBackFun:function(data){
									if(data.isSuccessOrfail=="SUCCESS"){
										value=data.obj.groupName;// 后台将groupids存在groupName属性里了
										if(data.obj.groupName==""){
											value="-1";// 默认设置根角色
										}
					    			}
									else{
										value="-1";
									}
								}};
								sendAjaxRequest(options);
								$("#editgroupTree").combotree("setValues",value.split(","));
								//$("#infogroupTree").combotree("setValue",value);
								//打开用户编缉页面
								$('#userEdit').dialog('open');

							}if(flag=='info'){
								//加载用户信息
								$("#info_userName").val(data.obj.userName);
								$("#info_passWord").val(data.obj.passWord);
								$("#info_confirpassWord").val(data.obj.passWord);
								$("#info_email").val(data.obj.email);
								$("#info_mobile").val(data.obj.mobile);
								$("#info_tel").val(data.obj.tel);
								$("#info_qq").val(data.obj.qq);
								$("#info_address").val(data.obj.address);
								//给两个树形设置具体的值
								//alert(data.obj.umsrole.roleId);
								if(data.obj.umsrole!=null)
								$('#inforoleTree').combotree("setValue",data.obj.umsrole.roleId);
								if(data.obj.umsrole==null)
								$('#inforoleTree').combotree("setValue","-1");
								//根据userid获得组织的id
								var value="";
								var options={url:"getStrGroupIdByUserId",data:"userId="+id,callBackFun:function(data){
									if(data.isSuccessOrfail=="SUCCESS"){
										//alert(data.obj.groupName);
										value=data.obj.groupName;// 后台将groupids存在groupName属性里了
										if(data.obj.groupName==""){
											value="-1";
										}
					    			}
									else{
										value="-1";
									}
								}};
								sendAjaxRequest(options);
								$('#infogroupTree').combotree("setValues",value.split(","));
								//打开模块查看页面
								$('#userInfo').dialog('open');
							}
							
						}
					};
					/*if(flag=='edit'){
					      $('#editTree').combotree({
					    	  url:'inittree?upid='+id,
					    	  loadFilter:function(data,parent){
					    		  return data.treeList;
					    	  }
						   });
					}*/
				sendAjaxRequest(options);
		}

	//执行用户添加操作   上面的添加
	function openAddUser(){
		//加载其树形
		ajaxroleTree("addroleTree");
		ajaxgroupTree("addgroupTree");
		$('#userAdd').dialog('open');//给那个div打开一个窗体，之前窗体已经生成
		$('#addroleTree').combotree("setValue","-1");//给其中一个select标签添加树形且设置值，0表示根目录
		$('#addgroupTree').combotree("setValue","-1");
	}
	//执行用户添加操作    添加模块的时候提交js方法
	function addUser(){
		// js验证表单
		var isbol = Validator.Validate(document.getElementById("userAddForm"),3);
		if(isbol){
			var groups = $("#addgroupTree").combotree('getValues');
			var umsgroups=groups.join(",");//变成字符串
			//var pid = $("#addTree").combotree('getValue');
			var options = {
					url: 'addUser'+"?umsgroups="+umsgroups,//请求的action路径
					data :$("#userAddForm").serialize(),
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							resetAddForm();// 将添加用户的框重置
							reloadTable("alreadyuserTable");//重新加载数据
		    			}
						showMessage(data);
					}
			};
			sendAjaxRequest(options);
		}
	}

	//执行用户编辑操作
	function editUser(){
		
		var isbol = Validator.Validate(document.getElementById("userEditForm"),3);
		if(isbol){
			var groups = $("#editgroupTree").combotree('getValues');
			var umsgroups=groups.join(",");
			//var id = $("#id").val();
			//if(pid==id){//判断父节点是否是自己本身
			//	$.messager.alert('错误提示',"选父节点不能是自己本身,请重新修改...",'error');
			//	return false
			//}
			var options = {
					url: 'updateUser'+"?id="+$("#id").val()+"&umsgroups="+umsgroups,//请求的action路径
					data : $("#userEditForm").serialize(),
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							reloadTable("alreadyuserTable");
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
	function delUser(id){	
		$.messager.confirm('删除提示', '你确定要删除该数据吗?',function(r){
			if(r){
				var url = 'deluser';
				var options = {
						url:url,
						data:{"id":id},
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								reloadTable("alreadyuserTable");
								//ajaxTree();??
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
	function batchDelUser(){
		if($('#alreadyuserTable').datagrid('getSelected')){// 被选中
			var ids = [];
			var ids_yz =[];
			var dpnames = [];
			var selectedRow = $('#alreadyuserTable').datagrid('getSelections');
			for(var i=0;i<selectedRow.length;i++){
				ids.push(selectedRow[i].userId);
				ids_yz.push("'"+selectedRow[i].userId+"'");
				dpnames.push(selectedRow[i].userName);
			}
			var dpid = ids.join(',');
			var ids_yz = ids_yz.join(',');
			var dpname =dpnames.join(","); 
			$.messager.confirm('删除提示', '你确定永久删除该数据吗? '+"\n"+dpname,function(r){
				//首先如果用户选择了数据，则获取选择的数据集合
				if(r){
					var url ='batchdeluser';
					var options = {	url:url,
									data:{"ids":dpid,
										  "ids_yz":ids_yz
										},
									callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
										if(data.isSuccessOrfail=="SUCCESS"){
											reloadTable("alreadyuserTable");
											//ajaxTree(); ??
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
		$("#addTree").combotree("setValue","0");
	}
	//这是添加模块式的重置方法
	function userAddReset()
	{
		resetAddForm();
	}
	
	</script>
</body>
</html>
