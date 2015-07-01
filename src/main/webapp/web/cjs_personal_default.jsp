<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/common/js_cs.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/personpagesessioncheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>默认的界面</title>
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="../js/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="../js/assets/css/admin.css">
       <link rel="stylesheet" href="../js/layer/skin/layer.css" type="text/css"></link>
 <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
  <link rel="stylesheet" href="../css/sweet-alert.css" type="text/css"></link>
    <script type="text/javascript" src="../js/sweetalert.js"></script>
  <script type="text/javascript" src="../js/layer/layer.min.js"></script>
  
<script type="text/javascript">
//默认页系统简讯的预览，以及标记已读
function showdetail(title,mescon,time,isread,messageid){
	//alert(1);
	$("#infotitle").html(title);
	$("#infotime").html(time);
	$("#infocon").html(mescon);
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
	if(isread==0){
		
	 setReaded(messageid);
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
					//不要做什么了
				}
			}
		});
	}
function loadcont(){
	var perpage=3;  //每页数
	var status=-1;
	var currpage = 1;
	var panel1html="";
	$.ajax({
		type:"POST",
		url:"<%=root%>/message/getMessageByUserId",
		dataType:"json",
		async:false,
		data:{currpage:currpage,perpage:perpage,status:status},
		success:function(result){
			for(i in result){
				var _mestitle = "'"+result[i].mestitle+"'";
				var _mescon = "'"+result[i].mescon+"'";
				var _mestime = "'"+result[i].mestime+"'";
				var _messtatus = "'"+result[i].messtatus+"'";
				var _messageid = "'"+result[i].messageid+"'";
				panel1html += '<li><div class="admin-task-meta">'+result[i].messAuthor+'于 '+result[i].mestime+' 向您发送 </div><div class="admin-task-bd">'+'<a href="#" onclick="showdetail('+_mestitle+','+_mescon+','+_mestime+','+_messtatus+','+_messageid+')" >'+result[i].mescon.substring(0,30)+'</a>'+'</div>  </li>';
			}
			if(result.length==0)$("#firstpanul").html($("#nullcontent").html());
			else{$("#firstpanul").html(panel1html);}
		}
	});
}
//载入订阅简讯
function loadsubscription(){
	var count=2; 
	var panel2html="";
	var times =count;
	$.ajax({
		type:"POST",
		url:"<%=root%>/subscription/docInfoInSubscription",
		dataType:"json",
		async:false,
		data:{count:count},
		success:function(result){
			if(result.channelname2==null) times = 1;
			if(result.channelname1==null) times = 0;
			if(result.channelname0==null) times = -1;
				for(var i=0;i<=times;i++){
					var data ;
					var channelname ="";
					if(i==0) {data = result.chnldata0;channelname = result.channelname0;}
					if(i==1) {data = result.chnldata1;channelname = result.channelname1;}
					if(i==2) {data = result.chnldata2;channelname = result.channelname2;}
					panel2html += '  <div class="am-panel am-panel"><div class="am-panel-hd">';
					panel2html +='<h4 class="am-panel-title" data-am-collapse="{parent: \'#accordion\', target:\'#'+channelname+'\'}">';
					panel2html += channelname;
					if(i==0)panel2html +='</h4></div><div id="'+channelname+'" class="am-panel-collapse am-collapse am-in">' ;
					else {panel2html +='</h4></div><div id="'+channelname+'" class="am-panel-collapse am-collapse ">' ;}
					for(k in data){	
						panel2html += ' <div class="am-panel-bd"> <a href="'+data[k].puburl+'" target="_blank">'+data[k].doctitle.substring(0,25)+'</a></div>'
					}
					panel2html +=' </div></div>';
				}
			if(times==-1)$("#accordion").html($("#nullcontent").html());
			else{$("#accordion").html(panel2html);}
		}
	});
}

//热门分享
function loadshare(){
	var maxResult=5; 
	var panel3html = '<tr><th class="am-text-center">排名</th><th>文章标题</th><th>分享次数</th></tr>';
	var rank=0;
	$.ajax({
		type:"POST",
		url:"<%=root%>/share/hotShareDoc",
		dataType:"json",
		async:false,
		data:{maxResult:maxResult},
		success:function(result){
			if(result.isSuccessOrfail=="FAIL"){return;}
			for(i in result ){	
				if(result[i].isSuccessOrfail=="FAIL"){continue;}
				var title=result[i].doctitle;
				if(title!=null&&title!=''&&title.length>10){
					title=title.substring(0,10);
				}
				rank = parseInt(i)+1;
			panel3html += '<tr><td class="am-text-center">'+rank+'</td><td><a href="'+result[i].puburl+'" target="_blank">'+title+'</td><td>'+result[i].sharecount+'</a></td></tr>';
			}
			$("#panel-3-tbody").html(panel3html);
		}
	});
}
$(function(){
	
});

$(function(){

});

</script>
</head>
<body style="height: 800px;" > 
 <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>Index</small></div>
    </div>

   <div class="am-g">
    
 <div class="am-u-md-12">
        <div class="am-panel am-panel-default">
          <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-4'}">我的项目<span class="am-icon-chevron-down am-fr" ></span></div>
          <div id="collapse-panel-4" class="am-panel-bd am-collapse am-in">
            <ul class="am-list admin-content-task" id="firstpanul">
            </ul>
          </div>
        </div>
  
      </div>
      <!--  
 <div  class="am-u-md-6">
        <div class="am-panel am-panel-default">
          <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-3'}"><span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-in" id="collapse-panel-3">
           <table class="am-table am-table-bd am-table-bdrs am-table-striped am-table-hover">
              <tbody id="panel-3-tbody">
              
              </tbody>
            </table>
   
          </div>
        </div>
 </div>
      --> 
 </div>
  <!-- content start -->
  <div class="admin-content" id="nullcontent" style="display:none">

    <div class="am-g">
      <div class="am-u-sm-12">
        <p class="am-text-center">暂时还没有内容</p>
        <pre class="page-404">
          .----.
       _.'__    `.
   .--($)($$)---/#\
 .' @          /###\
 :         ,   #####
  `-..__.-' _.-\###/
        `;_:    `"'
 
        </pre>
      </div>
    </div>
  </div>
  <!-- content end -->
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
        <!--[if (gte IE 9)|!(IE)]><!-->
        <!--这个jquery导致default默认页的系统消息不能预览  -->
		<%--<script src="../js/assets/js/jquery.min.js"></script>--%>
		<script src="../js/assets/js/amazeui.min.js"></script>
		<!--<![endif]-->
		<script src="../js/assets/js/app.js"></script>
</body>
</html>