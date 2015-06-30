<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/js_cs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/personpagesessioncheck.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统消息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
  <link rel="stylesheet" href="../css/sweet-alert.css" type="text/css"></link>
    <link rel="stylesheet" href="../css/jquery.paginate.css" type="text/css"></link>
    <link rel="stylesheet" href="../js/layer/skin/layer.css" type="text/css"></link>
      <link rel="stylesheet" href="../js/assets/css/amazeui.min.css"/>
  	<link rel="stylesheet" href="../js/assets/css/admin.css">
  <script type="text/javascript" src="../js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../js/sweetalert.js"></script>
   <script type="text/javascript" src="../js/jquery.paginate.js"></script>
   <script type="text/javascript" src="../js/layer/layer.min.js"></script>


  <style>
  a:hover {text-decoration:underline;}
.delbtn{
padding:0 12px;
}
</style> 
  
<script type="text/javascript">
var total=0;
var count=0;
function loadcont(currpage){
var perpage=10;  //每页数
	var html="<tr><th><input type='checkbox' id='checkall'  onclick='changecheckbox()'/>全选</th><th>文章标题</th><th>所在栏目</th><th>分享去向</th><th>分享时间</th></tr>";
	$.ajax({
		type:"POST",
		url:"<%=root%>/share/getShareByUserId",
		dataType:"json",
		async:false,
		data:{currpage:currpage,perpage:perpage},
		beforeSend:function(){ 
			 load = layer.load();
			}, 
		complete:function(){ 
		
			$("#htmlcon").empty();
			$("#htmlcon").append(html);
			if(total==0)$("#pagination").remove();
			$("#totalcounts").html("共"+total+"条记录");
			count = total%perpage>0?parseInt(total/perpage+1):total/perpage;
			layer.close(load);
			//setTimeout(function(){layer.closeAll();},1000); 
			} ,
		success:function(result){
			for(i in result){
				var k = parseInt(i)+1+(currpage-1)*perpage;
				html = html +"<tr><td><input type='checkbox' name='collids' value='"+result[i].shareid+"'/>"+k+"</td><td><label  style='cursor:pointer;font-weight:normal;line-height:normal' title='"+result[i].doctitle+"'  ><a href='"+result[i].puburl+"' target='_blank'>"+result[i].doctitle.substring(0,10)+"</a></label></td><td>"+result[i].chnlname+"</td><td>"+result[i].swhere+"</td><td>"+result[i].sharetime+"</td></tr>";
				total =  result[i].total;			
			}
		}
	});
}
</script>
<script>
function warningalert(){
    swal({
     title: "",
      text: "亲，请先选择要删除的分享信息!",
      type: "warning",
      //showCancelButton: true,
      confirmButtonClass: 'btn-warning',
      confirmButtonText: '关闭'
    });
  }
function successalert(){
    swal({
      title: "",
      text: "亲！你已经成功删除了这些收藏信息!",
      type: "success",
     // showCancelButton: true,
      confirmButtonClass: 'btn-success',
      confirmButtonText: '关闭'
    });
  }
function infoalert(){
	 if($("input[name='collids']:checked").length==0){
		 warningalert();
		 return;
	 }
    swal({
      title: "",
      text: "亲！你确定要删除这些分享信息么？",
      type: "warning",
      showCancelButton: true,
      confirmButtonClass: 'btn-info',
      confirmButtonText: '我确定'
    },function(isConfirm){   if (isConfirm) {    delselectedmes();   }});
  }
$(function(){
	loadcont(1);
	$("#pagination").paginate({
		count 		: count,
		start 		: 1,
		display     : 10,
		border					: false,
		text_color  			: '#79B5E3',
		background_color    	: 'none',	
		text_hover_color  		: '#2573AF',
		background_hover_color	: 'none', 
		images		: false,
		onChange: function (page) {
			loadcont(page);
        },
		mouse		: 'press'
	});

});
function changecheckbox(){
	var checkedOfAll=$("#checkall").prop("checked");
	$("input[name='collids']").prop("checked",checkedOfAll);
	
}
function delselectedmes(){
	 var ids="";
	
	 $("input[name='collids']:checkbox").each(function(i){ 
         if($(this).prop("checked")){
        	 ids += $(this).val()+",";
         }
     });
	 if($("input[name='collids']:checked").length>0){
		 ids = ids.substring(0,ids.lastIndexOf(","));
 	}
     $.ajax({
 		type:"POST",
 		url:"<%=root%>/share/delShareByIds",
 		dataType:"html",
 		async:false,
 		data:{ids:ids},
 		success:function(result){
 			//successalert();
 			location.reload() ;
 		}
 	});
	
}
</script>
  </head>
  <body>
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分享</strong> / <small>Share</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <!--button type="button" class="am-btn am-btn-default" data-target="#sendhelpmes" data-toggle="modal"><span class="am-icon-plus"></span> 发送求助信息</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button-->
              <button type="button" class="am-btn am-btn-default" onclick="infoalert()"><span class="am-icon-trash-o"></span> 删除选中项</button>
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <!-- div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field">
                <span class="am-input-group-btn">
                  <button class="am-btn am-btn-default" type="button">搜索</button>
                </span>
          </div-->
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">   
          <table class="am-table am-table-striped am-table-hover table-main" id="htmlcon">        
        </table>
          <div class="am-cf">
<span id="totalcounts"></span>
  <div class="am-fr"  id="pagination" >
  
  </div>
</div>
      </div>
    </div>
   <!-- div class="panel panel-info">
   <div class="panel-heading">
      <h3 class="panel-title">我的分享<button type='button' class='btn btn-danger delbtn' onclick="infoalert()" style="float:right"
 >删除选中的分享</button></h3>
   </div>
   <div class="panel-body">
    <table class="table" id="htmlcon">
   </table>
<div id="pagination" ></div>
   </div>
</div-->


  </body>
</html>
