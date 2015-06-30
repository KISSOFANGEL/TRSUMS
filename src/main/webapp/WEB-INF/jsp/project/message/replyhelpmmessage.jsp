<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/js_cs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>求助信息管理</title>
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
	<table id="helpMesTable" title="求助信息列表" cellspacing="0" cellpadding="0" width="100%" iconCls="icon-edit"></table>
	
	<!--div id="toolbar">
	  < a href="#" class="easyui-linkbutton" icon="icon-add" onclick="openAddRole();">添加角色</a>
	  <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="batchDelRole();">批量删除</a>
    </div-->
</div>

<!--回复信息页面-->
<div id="helpMesReply" icon="icon-save"  class="easyui-dialog alldialog" >
	<form id="helpMesReplyForm" >
		<input type="hidden" name="messageid"  id="messageid"></input>
		<div class="div_ul">
        <ul>
			<li>
				<label>信息标题：</label>
				<input type="text" class="combo" readonly="readonly"  value="" id="edit_mestitle" ></input>
			</li>
			<li>
				<label>求助者：</label>
				<input type="text" class="combo" readonly="readonly"  value="" id="edit_mesauthor" ></input>
			</li>
			<li>
				<label>求助时间：</label>
				<input type="text" class="combo" readonly="readonly"  value="" id="edit_mestime" ></input>
			</li>
			<li>
				<label>求助内容：</label>
				<textarea  id="edit_mescon" name="mescon" readonly="readonly"  rows="6" class="form-control gray counted" style="width:300px;"></textarea>
				<!--<input type="text" class="combo" readonly="readonly"  value="" id="edit_mescon"></input>-->
			</li>
			<li>
				<label>回复内容：</label>
				<!--  <input type="text" class="combo" name="returnMes" value="" id="edit_returnMes"></input>-->
				<textarea  id="edit_returnMes" name="returnMes" placeholder="在这里输入回复信息" datatype="*2-320" errormsg="正文内容至少2到320位任意字符！" rows="6" class="form-control gray counted" style="width:300px;" ></textarea>
				 <h6 class="pull-right" id="counter" style="margin-top:5px;margin-bottom:0px;">还可输入320个字</h6>
			</li>
			<li align="center">
			<a class="easyui-linkbutton" icon="icon-ok" onclick="replyhelpmes();" >提交</a>
			</li>
		</ul>
		</div>
	</form>
</div>
<!-- 信息查看界面 -->

   <div id="mesInfo" icon="icon-save" class="easyui-dialog alldialog">
   <div class="div_ul">
        <ul >
			<li>
				<label>信息标题：</label>
				<input type="text" class="combo" id="info_mestitle"  disabled="true"></input>
			</li>
			<li>
				<label>求助者：</label>
				<input type="text" class="combo"  id="info_mesauthor"  disabled="true"></input>
			</li>
			<li>
				<label>求助时间：</label>
				<input type="text" class="combo" id="info_mestime"   disabled="true"></input>
			</li>
			<li>
				<label>求助内容：</label>
				<textarea  id="info_mescon"  readonly="readonly"  rows="6" class="form-control gray counted" style="width:300px;"></textarea>
				<!--<input type="text" class="combo" readonly="readonly"  value="" id="edit_mescon"></input>-->
			</li>
			<li>
				<label>回复内容：</label>
				<textarea  id="info_returnMes" readonly="readonly"  rows="6" class="form-control gray counted" style="width:300px;" ></textarea>
			</li>
			<li>
				<label>回复时间：</label>
				<input type="text" class="combo"  id="info_replytime"  disabled="true"></input>
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
	$(function(){
		$("#helpMesReplyForm").Validform({
			 tiptype:3,
			ajaxPost:true
						
		 });
		$("#Validform_msg").remove();
		 $(".counted").charCounter(320,{container: "#counter"});
		
	});
	function initPage(){
		//closeFun:关闭窗口事件 可选    可添加关闭窗口事件
		var dialog_edit = {
				id:"helpMesReply",
				title:"回复信息"
		};
		var dialog_info = {
				id:"mesInfo",
				title:"查看信息"
		};
		initDialog(dialog_edit);
		$('#helpMesReply').dialog('close');// 初始化三个窗体，然后隐藏起来
		//查看窗口
		initDialog(dialog_info);
		$('#mesInfo').dialog('close');
	} 
	/**--------加载表格数据 ------*/
	function ajaxTable(){
		//加载表格
		$('#helpMesTable').datagrid({
			url: '<%=root%>/message/getSysHelpMessage',   //这里的命名空间默认是action配置跳转到里面时的空间，所以不加命名空间
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
						{field:'UMSMessageid',checkbox:'true',width:20},//这些数据在data的集合json对象里面
						{field:'mestitle',title:'信息标题',width:100},
						{field:'mescon',title:'信息正文',width:100},
						{field:'mesauthor',title:'求助者',width:100},
						{field:'mestime',title:'求助时间',width:100},
						{field:'replytime',title:'回复时间',width:100},
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
		var _id = "'"+row.UMSMessageid+"'";
		var _mesCon = "'"+row.mescon+"'";
		var _returnMes = "'"+row.returnMes+"'";
		var _mesTitle = "'"+row.mestitle+"'";//前台求助信息要加mestitle
		var _mestime = "'"+row.mestime+"'";
		var _mesauthor = "'"+row.mesauthor+"'";
		var _replytime = "'"+row.replytime+"'";
		var mesInfo='<a href="#" onclick="mesInfo('+_id+','+_mesCon+','+_returnMes+','+_mesTitle+','+_mesauthor+','+_mestime+','+_replytime+')">详细</a>  |';
		var mesReply='<a href="#" onclick="replyMes('+_id+','+_mesCon+','+_returnMes+','+_mesTitle+','+_mesauthor+','+_mestime+','+_replytime+')">回复</a>';
		if(row.returnMes!="未回复"){
			mesReply='已回复';
		}
		return mesInfo+mesReply;
	};
	
	// 这里的方法是编辑与详细时调用的方法能得到当前行的信息，后台是根据传的id到数据库里面查询得到的
	function mesInfo(id,mesCon,returnMes,mestitle,mesauthor,mestime,replytime){
		$("#info_mestitle").val(mestitle);
		$("#info_mescon").val(mesCon);
		$("#info_mestime").val(mestime);
		$("#info_mesauthor").val(mesauthor);
		$("#info_replytime").val(replytime);
		$("#info_returnMes").val(returnMes);
		$('#mesInfo').dialog('open');
		}
	function replyMes(id,mesCon,returnMes,mestitle,mesauthor,mestime,replytime){
		$("#messageid").val(id); 
		$("#edit_mestitle").val(mestitle);
		$("#edit_mescon").val(mesCon);
		$("#edit_mestime").val(mestime);
		$("#edit_mesauthor").val(mesauthor);
		$("#edit_replytime").val(replytime);
		$("#edit_returnMes").val(returnMes);
		$('#helpMesReply').dialog('open');
		}
	/**
	 * 执行添加操作
	**/
	function addRole(){
		// js验证表单
		var isbol = Validator.Validate(document.getElementById("roleAddForm"),3);
		if(isbol){
			var options = {
					url: 'addRole',//请求的action路径
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
	/**
	 * 执行修改操作
	**/
	function editRole(){
		
		var isbol = Validator.Validate(document.getElementById("roleEditForm"),3);
		if(isbol){
			var options = {
					url: 'updateRole'+"?id="+$("#id").val(),//请求的action路径
					data : $("#roleEditForm").serialize(),
					callBackFun:function(data){
						if(data.isSuccessOrfail=="SUCCESS"){
							reloadTable("roleTable");
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
	function delRole(id){	
		$.messager.confirm('删除提示', '你确定要删除该数据吗?',function(r){
			if(r){
				var url = 'delRole';
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
					var url ='batchDelRole';
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
	function replyhelpmes(){
		$.messager.confirm('回复提示', '确定要回复该条求助信息吗? ',function(r){
			if(r){
				var url ='<%=root %>/message/replyMes';
				var options = {	url:url,
								data:{returnMes:$("#edit_returnMes").val(),ids:$("#messageid").val()},
								callBackFun:function(data){  //凡是ajax请求的action返回的都是BaseEntity的json对象
									if(data.isSuccessOrfail=="SUCCESS"){
										// 重置form表单
										//关闭回复框
										$('#helpMesReply').dialog('close');
										$.messager.show({title:'提示信息', msg:'发送成功'});
										reloadTable("helpMesTable");
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
	</body>
</html>