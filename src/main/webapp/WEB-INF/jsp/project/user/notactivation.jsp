<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未激活用户</title>
<style>
    *{font-size:14px;}
</style>
</head>
<body >
<div class="div_grid">
<!-- 用户列表一览   数据表格-->
	<!-- 数据列表加分页-->
	<table id="notactivationTable" title="未激活用户列表" cellspacing="0" cellpadding="0" width="100%"  iconCls="icon-edit"></table>
	
	<div id="toolbar">
	  <a href="#" class="easyui-linkbutton" icon="icon-add" onclick="batchActivateUser();">批量激活</a>
	  <%--
	  <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelUser();">批量删除</a>
    --%>
    </div>
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
		var dialog_info = {
				id:"userInfo",
				title:"查看用户信息"
		} 
		//查看窗口
		initDialog(dialog_info);
		$('#userInfo').dialog('close');
		
	}
	/**--------加载表格数据 ------*/
	function ajaxTable(){
		//加载表格
		$('#notactivationTable').datagrid({
			url: 'getNotActivationUsers',   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
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
		var activation = '<a href="#" onclick=activateUser('+_id+')>激活用户</a>';
		return detail+activation;
	};
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
		
		if(flag=='info'){
			ajaxroleTree("inforoleTree");
			ajaxgroupTree("infogroupTree");
			}
		var url = 'getUserByUserId?userId='+id;//根据用户id获取用户信息
		var options = {
						url:url,
						callBackFun:function(data){
							if(flag=='info'){
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
				sendAjaxRequest(options);
		}

	//执行用户添加操作   上面的添加
	function openActivateUser(){
		//加载其树形
		ajaxroleTree("addroleTree");
		ajaxgroupTree("addgroupTree");
		$('#userAdd').dialog('open');//给那个div打开一个窗体，之前窗体已经生成
		$('#addroleTree').combotree("setValue","-1");//给其中一个select标签添加树形且设置值，0表示根目录
		$('#addgroupTree').combotree("setValue","-1");
	}

	/**
	 * 执行激活操作
	**/
	function activateUser(id){	
		$.messager.confirm('提示', '你确定要开通改用户吗?',function(r){
			if(r){
				var url = 'activateUser';//后台激活用户
				var options = {
						url:url,
						data:{"id":id},
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
								reloadTable("notactivationTable");
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
	function batchActivateUser(){
		if($('#notactivationTable').datagrid('getSelected')){// 被选中
			var ids = [];
			var ids_yz =[];
			var dpnames = [];
			var selectedRow = $('#notactivationTable').datagrid('getSelections');
			for(var i=0;i<selectedRow.length;i++){
				ids.push(selectedRow[i].userId);
				ids_yz.push("'"+selectedRow[i].userId+"'");
				dpnames.push(selectedRow[i].userName);
			}
			var dpid = ids.join(',');
			var ids_yz = ids_yz.join(',');
			var dpname =dpnames.join(","); 
			$.messager.confirm('提示', '你确定要开通选中的所有用户吗? '+"\n"+dpname,function(r){
				//首先如果用户选择了数据，则获取选择的数据集合
				if(r){
					var url ='batchActivateUser';
					var options = {	url:url,
									data:{"ids":dpid,
										  "ids_yz":ids_yz
										},
									callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
										if(data.isSuccessOrfail=="SUCCESS"){
											reloadTable("notactivationTable");
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
