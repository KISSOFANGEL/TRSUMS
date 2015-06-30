<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已发送系统信息列表</title>
<link rel="stylesheet" href="../css/formvalidate.css" type="text/css"></link>
 <script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
   <!-- 字数限制 -->
  <script type="text/javascript" src="../js/messagebox.js"></script>
<style>
    *{font-size:14px;}
</style>
</head>
<body >
<div class="div_grid">
<!-- 用户列表一览   数据表格-->
	<!-- 数据列表加分页-->
	<table id="sysMesTable" title="已发系统信息列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	
	<div id="toolbar">
	  <!--<a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddRole();">添加角色</a>-->
	  <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelSysMes();">批量删除</a>
    </div>
</div>

<!-- 信息查看界面 -->

   <div id="mesInfo" icon="icon-save" class="easyui-dialog alldialog">
   <div class="div_ul">
        <ul >
			<li>
				<label>信息标题：</label>
				<input type="text" class="combo" id="info_title"  disabled="true"></input>
			</li>
			<li>
				<label>发送者：</label>
				<input type="text" class="combo"  id="info_authorname"  disabled="true"></input>
			</li>
			<li>
				<label>发送时间：</label>
				<input type="text" class="combo" id="info_cretime"   disabled="true"></input>
			</li>
			<li>
				<label>发送内容：</label>
				<textarea  id="info_mescontent"  readonly="readonly"  rows="6" class="form-control gray counted" style="width:300px;"></textarea>
				<!--<input type="text" class="combo" readonly="readonly"  value="" id="edit_mescon"></input>-->
			</li>
			<li>
				<label>接收角色：</label>
				<input type="text" class="combo"  id="info_rolename"  disabled="true"></input>
			</li>
		</ul>
		</div>
	</div>

	<script type="text/javascript">
	
	$(function(){
		//初始化页面模块
		initPage();
		//加载表格数据
		ajaxTable();
	});
	function initPage(){
		//closeFun:关闭窗口事件 可选    可添加关闭窗口事件
		var dialog_info = {
				id:"mesInfo",
				title:"查看信息"
		};
		//查看窗口
		initDialog(dialog_info);
		$('#mesInfo').dialog('close');
	} 
	/**--------加载表格数据 ------*/
	function ajaxTable(){
		//加载表格
		$('#sysMesTable').datagrid({
			url: '<%=root%>/systemmessage/getSystemMessages',   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
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
						{field:'UMSSystemMessageid',checkbox:'true',width:20},//这些数据在data的集合json对象里面
						{field:'title',title:'信息标题',width:100},
						{field:'mescon',title:'信息正文',formatter:getmescon,width:100},
						{field:'authorname',title:'发送者',width:100},
						{field:'cretime',title:'发送时间',width:100},
						{field:'rolename',title:'接收角色',formatter:getrolename,width:100},
						{field:'opt',title:'操作',formatter:optFormater,width:150}
					]]
		});
		 
	}
	function getmescon(value,row,index){
		var mescon=row.mescontent;
		if(mescon.length>20){
			return '<a href="javascript:void(0)">'+mescon.substring(0,20)+'</a>';
		}else{
			return row.mescontent;
		}
	}
	function getrolename(value,row,index){
		var roleid=row.roleid.split(',');
		var _rolename = '';
		for(var i=0;i<roleid.length;i++){
			if(roleid[i]==1){
				_rolename=_rolename+"普通用户";
			}else if(roleid[i]==2){
				_rolename=_rolename+"认证用户";
			}else if(roleid[i]==3){
				_rolename=_rolename+"vip用户";
			}
			if(i!=roleid.length-1){
				_rolename=_rolename+",";
			}
		}
		return _rolename;
	}
	
	/**
	 *  value 设置操作列的信息
	 * row   当前行的数据
	 * index 当前行的索引  从0 开始
	 */
	function optFormater(value,row,index){
		var _id = "'"+row.UMSSystemMessageid+"'";
		var _mesCon = "'"+row.mescontent+"'";
		var _mesTitle = "'"+row.title+"'";//前台求助信息要加mestitle
		var _cretime = "'"+row.cretime+"'";
		var _authorname = "'"+row.authorname+"'";
		var roleid=row.roleid.split(',');
		var _rolename = '';
		for(var i=0;i<roleid.length;i++){
			if(roleid[i]==1){
				_rolename=_rolename+"'"+"普通用户"+"'";
			}else if(roleid[i]==2){
				_rolename=_rolename+"'"+"认证用户"+"'";
			}else if(roleid[i]==3){
				_rolename=_rolename+"'"+"vip用户"+"'";
			}
			if(i!=roleid.length-1){
				_rolename=_rolename+",";
			}
		}
		var mesInfo='<a href="#" onclick="mesInfo('+_id+','+_mesCon+','+_mesTitle+','+_authorname+','+_cretime+','+_rolename+')">详细</a>  |';
		var mesDel='<a href="#" onclick=delMes('+_id+')>删除</a>';
		return mesInfo+mesDel;
	};
	
	// 这里的方法是编辑与详细时调用的方法能得到当前行的信息，后台是根据传的id到数据库里面查询得到的
	function mesInfo(id,mesCon,mestitle,authorname,cretime,rolename){
		$("#info_title").val(mestitle);
		$("#info_mescontent").val(mesCon);
		$("#info_cretime").val(cretime);
		$("#info_authorname").val(authorname);
		$("#info_rolename").val(rolename);
		$('#mesInfo').dialog('open');
		}

	/**
	 * 批量操作
	 * @return
	 */
	function batchDelSysMes(){
		if($('#systemMesTable').datagrid('getSelected')){// 被选中
			var ids = [];
			var ids_yz =[];
			var selectedRow = $('#roleTable').datagrid('getSelections');
			for(var i=0;i<selectedRow.length;i++){
				ids.push(selectedRow[i].UMSSystemMessageid);
				ids_yz.push("'"+selectedRow[i].UMSSystemMessageid+"'");
			}
			var dpid = ids.join(',');
			var ids_yz = ids_yz.join(',');
			$.messager.confirm('删除提示', '你确定永久删除该数据吗? ',function(r){
				//首先如果用户选择了数据，则获取选择的数据集合
				if(r){
					var url ='<%=root %>/systemmessage/delMes';
					var options = {	url:url,
									data:{"id":ids_yz},
									callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
										if(data.isSuccessOrfail=="SUCCESS"){
											reloadTable("systemMesTable");
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
	function delMes(id){
			var url ='<%=root %>/systemmessage/delMes';
			var options = {	url:url,
							data:{id:id},
							callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
								if(data.isSuccessOrfail=="SUCCESS"){
									// 重置form表单
									$.messager.show({title:'提示信息', msg:'删除成功'});
									reloadTable("systemMesTable");
								}else{
									$.messager.alert('错误提示','删除失败','error');
								}
							}
			
			}
			   sendAjaxRequest(options);//调用ajax方法
			 
	}
	</script>
	</body>
</html>