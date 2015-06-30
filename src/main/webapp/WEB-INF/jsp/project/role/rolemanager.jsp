<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<style>
    *{font-size:14px;}
</style>
</head>
<body >
<div class="div_grid">
<!-- 用户列表一览   数据表格-->
	<!-- 数据列表加分页-->
	<table id="roleTable" title="角色列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	
	<div id="toolbar">
	  <a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddRole();">添加角色</a>
	  <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelRole();">批量删除</a>
    </div>
</div>

<!-- 角色新增页面 -->
<div id="roleAdd"   icon="icon-save" class="easyui-dialog alldialog">
	<form id="roleAddForm">
	<input type="hidden" id ="addTextSelect" name="addTextSelect" value=""/>
	<input type="reset" id ="addReset" />
	<div class="div_ul">
        <ul>
			<li>
				<label>角色名称：</label>
				<input type="text" class="combo" name="roleName" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>角色描述：</label>
				<input type="text" class="combo" name="roleDesc"   ></input>
			</li>
			<li>
				<label>角色类型：</label>
				<input type="text" class="combo" name="roleType" ></input>
			</li>
			<li>
				<label>角色标准：</label>
				<input type="text" class="combo" name="roleLevel" ></input>
			</li>
			<li>
				<label>排序：</label>
				<input type="text" class="combo" name="roleOrder" ></input>
			</li>
			<li>
				<label>栏目权限：</label>
				<select  multiple="multiple" cascadeCheck="false" id="addchannelTree"  name="channels" class="easyui-combotree channelTree"  style="width:180px;" >
				</select>
				<span>权限是否继承:  </span>
		        <input type="checkbox"  onclick="changerCascadeCheck('#addchannelTree',this)">
			</li>
			<li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="addRole();">提交</a>
				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="roleAddReset();">重置</a>
			</li>
		 </ul>
		</div>
	</form>
</div>
<!--角色编辑页 面-->
<div id="roleEdit" icon="icon-save"  class="easyui-dialog alldialog" >
	<form id="roleEditForm">
	    <input type="reset" id ="editReset" style="display:none"/>
	    <input type="hidden" id ="editTextSelect" name="editTextSelect" value=""/>
		<input type="hidden" name="roleId"  id="id"></input>
		<div class="div_ul">
        <ul>
			<li>
				<label>角色名称：</label>
				<input type="text" class="combo" name="roleName" value="" id="edit_roleName" dataType="Require" msg="不能为空"></input>
			</li>
			<li>
				<label>角色描述：</label>
				<input type="text" class="combo" name="roleDesc"  value="" id="edit_roleDesc" ></input>
			</li>
			<li>
				<label>角色类型：</label>
				<input type="text" class="combo" name="roleType" value="" id="edit_roleType" ></input>
			</li>
			<li>
				<label>角色标准：</label>
				<input type="text" class="combo" name="roleLevel" value="" id="edit_roleLevel"></input>
			</li>
			<li>
				<label>排序：</label>
				<input type="text" class="combo" name="roleOrder" value="" id="edit_roleOrder"></input>
			</li>
			<li>
				<label>栏目权限：</label>
				<select  multiple="multiple" cascadeCheck="false" id="editchannelTree"  name="channels" class="easyui-combotree channelTree"  style="width:180px;" >
				</select>
				<span>权限是否继承: </span>
		<input type="checkbox"  onclick="changerCascadeCheck('#editchannelTree',this)">
			</li>
			<li align="center">
			<div id="editprogressbar" class="easyui-progressbar" style="width:400px;display:none"></div>
			</li>
			<li align="center">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="editRole();">提交</a>
			</li>
		</ul>
		</div>
	</form>
</div>
<!-- 角色详细信息查看页面 -->

   <div id="roleInfo" icon="icon-save" class="easyui-dialog alldialog">
   <div class="div_ul">
     <form action="">
     <input type="reset" id ="infoReset" style="display:none"/>
        <ul >
			<li>
				<label>角色名称：</label>
				<input type="text" class="combo" id="info_roleName"  disabled="true"></input>
			</li>
			<li>
				<label>角色描述：</label>
				<input type="text" class="combo"  id="info_roleDesc"  disabled="true"></input>
			</li>
			<li>
				<label>角色类型：</label>
				<input type="text" class="combo" id="info_roleType"   disabled="true"></input>
			</li>
			<li>
				<label>角色标准：</label>
				<input type="text" class="combo"  id="info_roleLevel"  disabled="true"></input>
			</li>
			<li>
				<label>创建者：</label>
				<input type="text" class="combo" id="info_cruser"   disabled="true"></input>
			</li>
			<li>
				<label>创建时间：</label>
				<input type="text" class="combo"  id="info_crutime"  disabled="true"></input>
			</li>
			<li>
				<label>排序：</label>
				<input type="text" class="combo"  id="info_roleOrder"  disabled="true"></input>
			</li>
			<li>
				<label>栏目权限：</label>
				<select  multiple="multiple" cascadeCheck="false" id="infochannelTree"   name="channels" class="easyui-combotree channelTree"  style="width:180px;" >
				 
				</select>
			</li>
		</ul>
		</form>
		</div>
	</div>
	<script type="text/javascript">
	/**----------------加载整体表格,初始化总过树形-------------------------**/
	$(function(){
		//初始化页面模块
		initPage();
		//加载表格数据
		ajaxTable();
		//加载栏目树形
		getChannelTree();
		
	});
	function initPage(){
		//closeFun:关闭窗口事件 可选    可添加关闭窗口事件
		var dialog_add = {
				id:"roleAdd",
				title:"增加角色信息"
		}
		var dialog_edit = {
				id:"roleEdit",
				title:"修改角色信息"
		}
		var dialog_info = {
				id:"roleInfo",
				title:"查看角色信息"
		} 
		//新增窗口
		initDialog(dialog_add);
		$('#roleAdd').dialog('close');
		//编缉窗口
		initDialog(dialog_edit);
		$('#roleEdit').dialog('close');// 初始化三个窗体，然后隐藏起来
		//查看窗口
		initDialog(dialog_info);
		$('#roleInfo').dialog('close');
		
	} 
	/**--------加载表格数据 ------*/
	function ajaxTable(){
		//加载表格
		$('#roleTable').datagrid({
			url: 'getRoles',   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
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
						{field:'roleId',checkbox:'true',width:20},//这些数据在data的集合json对象里面
						{field:'roleName',title:'角色名称',width:100},
						{field:'roleDesc',title:'角色描述',width:100},
						{field:'cruser',title:'创建者',width:100},
						{field:'crutime',title:'创建时间',width:100},
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
		var _id = "'"+row.roleId+"'";
		var detail = '<a href="#" onclick=getInfo('+_id+',"info")>详细</a> |  ';
		var edit = '<a href="#" onclick=getInfo('+_id+',"edit")>修改</a> | ';//这里是修改，给edittree添加树形
		var del = '<a href="#" onclick=delRole('+_id+')>删除</a>';
		return detail+edit+del;
	};
	
	function getChannelTree(){
		
		//初始化栏目树形
		 $('.channelTree').combotree({//http://www.tbtguide.com:8082/wcm/channel/getWcmTree.jsp?website=6
		        checkbox: true,//http://localhost:8080/wcm/channel/getWcmTree.jsp?website=1,2
		        url: 'http://www.tbtguide.com:8082/wcm/channel/getWcmTree.jsp?website=6',
		        onClick: function(node){
		            $(this).tree('toggle', node.target);
		            //alert('you dbclick '+node.text);
		        },
		        onContextMenu: function(e, node){
		            e.preventDefault();
		            $('#addchannelTree').tree('select', node.target);
		           // $('#mm').menu('show', {
		            //    left: e.pageX,
		            //    top: e.pageY
		            //});
		        }
		    });
	}
	
	// 这里的方法是编辑与详细时调用的方法能得到当前行的信息，后台是根据传的id到数据库里面查询得到的
	function getInfo(id,flag){
		
		var url = 'getRoleAndChannelByRoleId?roleId='+id;//根据用户id获取用户信息
		var options = {
						url:url,
						callBackFun:function(data){
							if(flag=='edit'){
								//重置表单 
								//$('#editReset').click();
								//加载用户信息
								$("#id").val(data.roleId);
								$("#edit_roleName").val(data.roleName);
								$("#edit_roleDesc").val(data.roleDesc);
								$("#edit_roleType").val(data.roleType);
								$("#edit_roleLevel").val(data.roleLevel);
								$("#edit_roleOrder").val(data.roleOrder);
								//打开用户编缉页面
								if(data.chnlids!=null&&data.chnlids!=""){
									
								$('#editchannelTree').combotree('setValues',data.chnlids);
								}else{
									$('#editchannelTree').combotree('setValues',[]);		
								}
								$('#roleEdit').dialog('open');

							}if(flag=='info'){
								//重置表单 
								//$('#infoReset').click();
								//加载用户信息
								$("#info_roleName").val(data.roleName);
								$("#info_roleDesc").val(data.roleDesc);
								$("#info_roleType").val(data.roleType);
								$("#info_roleLevel").val(data.roleLevel);
								$("#info_cruser").val(data.cruser);
								$("#info_crutime").val(data.crutime);
								$("#info_roleOrder").val(data.roleOrder);
								//打开模块查看页面
								if(data.chnlids!=null&&data.chnlids!=""){
								     $('#infochannelTree').combotree('setValues',data.chnlids);
									}else{
										$('#infochannelTree').combotree('setValues',[]);		
									}
								$('#roleInfo').dialog('open');
							}
							
						}
					};
				sendAjaxRequest(options);
		}
	//执行角色添加操作   上面的添加
	function openAddRole(){
		$('#roleAdd').dialog('open');//给那个div打开一个窗体，之前窗体已经生成
	}
	
	/**
	 * 获得多选框的text与attribute的值，并隐藏在表单里面提交
	**/
	function getTextAndAttribute(noteid){
		var t = $(noteid).combotree('tree');	// combotree获取树对象
		var nodes = t.tree('getChecked');		// 获取选择的多节点  这个获取的是单节点  如果是单节点t.tree('getSelected')
		 var s = '';
		   
			   for (var i = 0; i < nodes.length; i++) {
			        if (s != '') 
			            s += ',';
			        s += nodes[i].id+"@"+nodes[i].text+"@"+nodes[i].attributes;//attributes
			    }
		  
		return s;
	}
	
	/**
	 * 执行添加操作
	**/
	function addRole(){
       	var textselect=getTextAndAttribute("#addchannelTree");
		$("#addTextSelect").val(textselect);
		// js验证表单
		var isbol = Validator.Validate(document.getElementById("roleAddForm"),3);
		if(isbol){
			var options = {
					url: 'addRoleChannel',//请求的action路径
					data :$("#roleAddForm").serialize(),
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							resetAddForm();// 将添加用户的框重置
							reloadTable("roleTable");//重新加载数据
		    			}
						showMessage(data);
					}
			};
			sendAjaxRequest(options);
		}
	}
	var dings;
	function setBeginTime(){
		var value = $('#editprogressbar').progressbar('getValue'); 
		if (value < 80){ 
		value += Math.floor(Math.random() * 10); 
		$('#editprogressbar').progressbar('setValue', value); 
		}else{
			$('#editprogressbar').progressbar('setValue', value); 
			clearInterval(dings);
		}
	}
	
	/**
	 * 执行修改操作
	**/
	function editRole(){
		var textselect=getTextAndAttribute("#editchannelTree");
		$("#editTextSelect").val(textselect);
		var isbol = Validator.Validate(document.getElementById("roleEditForm"),3);
		if(isbol){
			$('#editprogressbar').css("display","block");
			var options = {
					url: 'updateRoleAndChannel'+"?id="+$("#id").val(),//请求的action路径
					data : $("#roleEditForm").serialize(),
					errorFun:function(){
						alert("请求失败");
					},
					completeFun:function(){
						$('#editprogressbar').css("display","none");
						$('#editprogressbar').progressbar("setValue",0);
					},
					beforeSendFun:function(){
						
						$('#editprogressbar').progressbar("setValue", 28);
						// 定时任务
						//alert($('#editprogressbar').progressbar('getValue'));
						dings=setInterval(setBeginTime,500);
						
					},
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							clearInterval(dings);
							$('#editprogressbar').progressbar("setValue", 100);
							reloadTable("roleTable");
							//修改成功后关闭编辑窗口
							$('#roleEdit').dialog('close');
		    			}
						showMessage(data);
					}
			};
			sendEditRoleAjaxRequest(options);//在custom中定义的方法,开启进度条，ajax必须是异步的
		}
		
		
	}


	/**
	 * 执行删除操作
	**/
	function delRole(id){	
		$.messager.confirm('删除提示', '你确定要删除该数据吗?',function(r){
			if(r){
				var url = 'delRoleAndChannel';
				var options = {
						url:url,
						data:{"id":id},
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								reloadTable("roleTable");
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
	function batchDelRole(){
		if($('#roleTable').datagrid('getSelected')){// 被选中
			var ids = [];
			var ids_yz =[];
			var dpnames = [];
			var selectedRow = $('#roleTable').datagrid('getSelections');
			for(var i=0;i<selectedRow.length;i++){
				ids.push(selectedRow[i].roleId);
				ids_yz.push("'"+selectedRow[i].roleId+"'");
				dpnames.push(selectedRow[i].roleName);
			}
			var dpid = ids.join(',');
			var ids_yz = ids_yz.join(',');
			var dpname =dpnames.join(","); 
			$.messager.confirm('删除提示', '你确定永久删除该数据吗? '+"\n"+dpname,function(r){
				//首先如果用户选择了数据，则获取选择的数据集合
				if(r){
					var url ='batchDelRoleAndChannel';
					var options = {	url:url,
									data:{"ids":dpid,
										  "ids_yz":ids_yz
										},
									callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
										if(data.isSuccessOrfail=="SUCCESS"){
											reloadTable("roleTable");
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
	// casecadeCheck
	function changerCascadeCheck(noteid,obj){
		var t = $(noteid).combotree('tree');	// combotree获取树对象
		var nodes = t.tree('getChecked');		// 获取选择的多节点  这个获取的是单节点  如果是单节点t.tree('getSelected')
		 var s = '';
		   for (var i = 0; i < nodes.length; i++) {
			  if (s != '') 
			      s += ',';
			   s += nodes[i].id;
		  }
		$(noteid).combotree({cascadeCheck:$(obj).is(':checked')});
		$(noteid).combotree('setValues', s);


	}
	</script>
	</body>
</html>