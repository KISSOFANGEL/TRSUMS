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
  <script type="text/javascript" src="../js/sweetalert.js"></script>
   <script type="text/javascript" src="../js/jquery.paginate.js"></script>
<script type="text/javascript" src="../js/layer/layer.min.js"></script>

  <style>
.delbtn{
padding:0 12px;
}
</style> 
  
<script type="text/javascript">
var count=0;
var status=-1;
var total = 0;
function loadcont(currpage,status){
var perpage=10;  //每页数
	var html="<tr ><th><input type='checkbox' id='checkall'  onclick='changecheckbox()'/>全选</th><th>标题</th><th>消息来源</th><th>消息内容</th><th>消息时间</th><th>是否已读</th><th class='table-set'>操作</th></tr>";
	$.ajax({
		type:"POST",
		url:"<%=root%>/message/getMessageByUserId",
		dataType:"json",
		async:false,
		data:{currpage:currpage,perpage:perpage,status:status},
		beforeSend:function(){ 
			 load = layer.load();
			}, 
		complete:function(){ 		
			$("#htmlcon").empty();
			$("#htmlcon").append(html);
			$("#totalcounts").html("共"+total+"条记录");
			if(total==0)$("#pagination").remove();
			count = total%perpage>0?parseInt(total/perpage+1):total/perpage;
			//setTimeout(function(){layer.closeAll();},1000); 
			} ,
		success:function(result){
			for(i in result){
				var k = parseInt(i)+1+(currpage-1)*perpage;
				html = html +"<tr class='"+result[i].messageid+"'><td><input type='checkbox' name='messids' value='"+result[i].messageid+"'/>"+k+"</td>";
				html = html+"<td>"+result[i].mestitle+"</td>";
				html = html+"<td>"+result[i].messAuthor+"</td>";
				html = html+"<td><label  style='cursor:pointer;font-weight:normal;line-height:normal' title='"+result[i].mescon+"'  >"+result[i].mescon.substring(0,10)+"</label></td>";
				html = html+"<td>"+result[i].mestime+"</td>";
				if(result[i].messtatus=="0")html = html+"<td><span class='am-icon-envelope am-icon-sm'></span></td>";
				else{html = html+"<td><span class='am-icon-envelope-o am-icon-sm'></span></td>";}
				html = html+"<td>  <div class='am-btn-toolbar'> <div class='am-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs ' onclick='showdetail("+result[i].messageid+")'><span class='am-icon-info'></span> 详情</button></div></div></td></tr>";
				total =  result[i].total;	
			}
			layer.close(load);
		}
	});
}
</script>
<script>
function warningalert(){
    swal({
     title: "",
      text: "亲，请先选择要删除的消息!",
      type: "warning",
      //showCancelButton: true,
      confirmButtonClass: 'btn-warning',
      confirmButtonText: '关闭'
    });
  }
function successalert(){
    swal({
      title: "",
      text: "亲！你已经成功删除了这些信息!",
      type: "success",
     // showCancelButton: true,
      confirmButtonClass: 'btn-success',
      confirmButtonText: '关闭'
    });
  }
function infoalert(){
	 if($("input[name='messids']:checked").length==0){
		 warningalert();
		 return;
	 }
    swal({
      title: "",
      text: "亲！你确定要删除这些信息么？",
      type: "warning",
      showCancelButton: true,
      confirmButtonClass: 'btn-info',
      confirmButtonText: '我确定'
    },function(isConfirm){   if (isConfirm) {    delselectedmes()} });
  }
$(function(){
	loadcont(1,status);
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
			loadcont(page,status);
			
        },
		mouse		: 'press'
	});

});
function changecheckbox(){
	var checkedOfAll=$("#checkall").prop("checked");
	$("input[name='messids']").prop("checked",checkedOfAll);
	
}
function delselectedmes(){
	 var mesids="";
	 $("input[name='messids']:checkbox").each(function(i){ 
         if($(this).prop("checked")){
        	 mesids += $(this).val()+",";
        	 //$(this).parent().parent().remove();
         }
     });
	 if($("input[name='messids']:checked").length>0){
		 mesids = mesids.substring(0,mesids.lastIndexOf(","));
 	}
     $.ajax({
 		type:"POST",
 		url:"<%=root%>/message/delMessageByUserIds",
 		dataType:"html",
 		async:false,
 		data:{mesids:mesids},
 		success:function(result){
 			//successalert();
 			location.reload() ;
 		}
 	});
	
}

function showdetail(i){
	var trobj = $("[class='"+i+"']");
	$("#infotitle").html(trobj.children('td').eq(1).html());
	$("#infotime").html(trobj.children('td').eq(4).html());
	$("#infocon").html(trobj.children('td').eq(3).children("label").attr("title"));
	$.layer({
		   type: 1,   //0-4的选择,
		    title:false ,
		    border: [0],
		    bgcolor:'',
		    closeBtn: [1,true],
		    shadeClose: false,
		    area: ['560px', '380px'],
		    page: {dom:'#container'
		    }
		});
	// 当已经是已读标签了就不读了
	var obj = $("[class='"+i+"']");
	var isreadclass=obj.children('td').eq(5).children("span").attr("class");
	if(isreadclass!="am-icon-envelope-o am-icon-sm"){
		
	 setReaded(i);
	}
	
}
//标记已读
function setReaded(messageid){
		$.ajax({
			type:"POST",
			url:"<%=root%>/message/readMessage",
			dataType:"json",
			async:false,
			data:{messageid:messageid},
			success:function(result){
				if(result.isSuccessOrfail=="SUCCESS"){
					var obj = $("[class='"+messageid+"']");
					obj.children('td').eq(5).children("span").attr("class","am-icon-envelope-o am-icon-sm");
				}
			}
		});
	}
</script>
  </head>
  <body>
  <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统信息</strong> / <small>SystemMessages</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
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
    <!-- 详情 begin-->
<div class="container" id="container" style="display:none">
	<div >
      <div class="col-md-6 col-md-offset-3" style="width:560px; margin-left:auto;">
        <div class="well well-sm">
          <form class="form-horizontal" >
          <fieldset>
            <legend class="text-center">消息详情</legend>
            <!-- Name input-->
            <div class="form-group">
              <label class="col-md-3 control-label" for="name">消息标题</label>
              <div class="col-md-9" id="infotitle">
              </div>
            </div>
       <!-- title input-->
            <div class="form-group">
              <label class="col-md-3 control-label" for="titel">接收时间</label>
              <div class="col-md-9" id="infotime">
              </div>   
            </div>
            <!-- Message body -->
            <div class="form-group">
              <label class="col-md-3 control-label" for="message">消息内容</label>
              <div class="col-md-9" >    
                  <textarea id="infocon" disabled name="mescon"class="form-control gray counted" rows="6"altercss="gray" ></textarea>               	
              </div>
            </div>
            <!-- Form actions -->
          </fieldset>
          </form>
        </div>
      </div>
	</div>
</div>
   <!--详情 end   -->
   <!--  div class="panel panel-warning">
   <div class="panel-heading">
      <h3 class="panel-title">系统消息<button type='button' class='btn btn-danger delbtn' onclick="infoalert()" style="float:right"
 >删除选中信息</button></h3>
   </div>
   <div class="panel-body">
    <table class="table" id="htmlcon">
   </table>
<div id="pagination" ></div>
   </div>
</div-->


  </body>
</html>
