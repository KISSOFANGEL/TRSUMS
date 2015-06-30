
     // url集合	www.tbtguide.com:8083 localhost:8000 
  var personCollecturl='http://www.tbtguide.com:8083/ums/collect/loginfront';
  var loginsuburl='http://www.tbtguide.com:8083/ums/collect/logindaofront';
  var logindaourl='http://www.tbtguide.com:8083/ums/collect/logindaofront';
  var shareDocurl='http://www.tbtguide.com:8083/ums/share/shareDoc';
  var getCommentByDocIdurl='http://www.tbtguide.com:8083/ums/comment/getCommentByDocId';
  var commentSubmiturl='http://www.tbtguide.com:8083/ums/comment/commentSubmit';
  var chnlsuburl='http://www.tbtguide.com:8083/ums/subscription/chnlsub';
  var personCollectdata={"doctitle":$("#doctitle").val(),"docid":$("#docid").val()};

//登陆与收藏  begin
    $(function(){
	   // jquery 的cookie
	    // 引入cookie插件  begin
	 jQuery.cookie = function(name, value, options) { 
    if (typeof value != 'undefined') { // name and value given, set cookie 
        options = options || {}; 
        if (value === null) { 
            value = ''; 
            options.expires = -1; 
        } 
        var expires = ''; 
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) { 
            var date; 
            if (typeof options.expires == 'number') { 
                date = new Date(); 
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000)); 
            } else { 
                date = options.expires; 
            } 
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE 
        } 
        var path = options.path ? '; path=' + options.path : ''; 
        var domain = options.domain ? '; domain=' + options.domain : ''; 
        var secure = options.secure ? '; secure' : ''; 
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join(''); 
    } else { // only name given, get cookie 
        var cookieValue = null; 
        if (document.cookie && document.cookie != '') { 
            var cookies = document.cookie.split(';'); 
            for (var i = 0; i < cookies.length; i++) { 
                var cookie = jQuery.trim(cookies[i]); 
                // Does this cookie string begin with the name we want? 
                if (cookie.substring(0, name.length + 1) == (name + '=')) { 
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1)); 
                    break; 
                } 
            } 
        } 
        return cookieValue; 
    } 
}; //引入cookie插件  end
	//读取cookie中的值
	   var username=$.cookie('usernamefront');
		var userid=$.cookie('useridfront');
		if(username!=null&&username!=""){
		    $(".headlogin").html("<a href='http://www.tbtguide.com:8083/ums/web/user_center.jsp' >"+username+"</a>");
		}
	
	});//页面加载完之后在加载
	
     /**
     * 共同ajax调用
     * @param options 是一个自定义对象属性有
     * data:提交参数信息
     * url:请交路径
     * callBackFun:回调函数
     */
    function sendAjaxRequest(options){
    	$.ajax({
    		async : false,
    		cache:false,
    		type: "POST",
    		dataType:"jsonp",//跨域的时候必须是jsonp类型
    		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    		jsonp: options.jsonp,//服务端用于接收callback调用的function名的参数
            jsonpCallback:options.jsonpCallback,//callback的function名称
    		data : options.data,
    		url: options.url,//请求的action路径
    		error: function () {//请求失败处理函数
    			alert('请求失败');
    		},
    		success:function(data){// 这是ajax定义的回调函数  且将data传给可以是自己自定义的函数的参数
				if(data.isSuccessOrfail=="SUCCESS"){
    				//当自定义回调函数为null时，调用默认函数处理
        			if(options.callBackFun==null){   //当回调函数未定义时
        				$.messager.show({title:'提示信息', msg:data.message});
        				//是否重新加载table
        				if(options.reloadTable!="undefined"){
        					reloadTable(options.reloadTable);//options.reloadTable为加载 表格的id
        				}
        			}else{//当有回调函数的时候，回调里面也加提示框来提示
        				options.callBackFun(data);//当回调函数已经定义时就直接调用  且将data传给可以是自己自定义的函数的参数
        			}
    			}else{
				    options.callBackFun(data);
    			}
    		
    		}
    	});
    }
     function personCollect(){
	  
	    // ajax可以跨域请求
	    var url = personCollecturl;
				var options = {
						url:url,
						data:{"doctitle":encodeURI($("#doctitle").val()),"docid":$("#docid").val()},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallback",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							    
								alert(data.result);
							}else if(data.isSuccessOrfail=="FAIL"){
							     // 开启模态登陆框登陆框
								 $("#modaltrigger").click();
								 unbindallfun();
								 $("#loginbtn").on('click',loginsub);	
							}
							
						}
				}
				sendAjaxRequest(options);
	 
	 }
	 function loginsub(){    
	      // ajax可以跨域请求
	    var url = logindaourl;
				var options = {
						url:url,
						data:{"username":encodeURI($("#username").val()),"password":$("#password").val()},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallbacklogindaosub",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							  //登陆成功后在收藏(返回的信息包含userName,userId)
							  // 将个人信息保存在cookie里面，页面可以实时监听		    
							     personCollect();
								 $.cookie('usernamefront',data.userName,{path:"/"});
								 $.cookie('useridfront',data.userId,{path:"/"});
								 $.cookie('UMSJSESSIONID',data.sessionid,{path:"/"});
								 $(".headlogin").html("<a href='http://www.btbguide.com:8083/ums/web/user_center.jsp' >"+data.userName+"</a>");
							}else if(data.isSuccessOrfail=="FAIL"){
							     // 开启模态登陆框登陆框
								 $("#modaltrigger").click();
		                         unbindallfun();
								 $("#loginbtn").on('click',loginsub);	
								 $("#erroinfo").html("<font color='red'>"+data.message+"</font>");
								 
							}
							
						}
				}
				sendAjaxRequest(options);
	 
	 }
	
	 // 单纯的登陆按钮
	  function logindao(){
	      // ajax可以跨域请求
	    var url = logindaourl;
				var options = {
						url:url,
						data:{"username":encodeURI($("#username").val()),"password":$("#password").val()},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallbacklogindao",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							  //登陆成功后(返回的信息包含userName,userId)
							  // 将个人信息保存在cookie里面，页面可以实时监听		    
								 $.cookie('usernamefront',data.userName,{path:"/"});
								 $.cookie('useridfront',data.userId,{path:"/"});
								 $.cookie('UMSJSESSIONID',data.sessionid,{path:"/"});
								 $(".headlogin").html("<a href='http://www.btbguide.com:8083/ums/web/user_center.jsp' >"+data.userName+"</a>");
							}else if(data.isSuccessOrfail=="FAIL"){
							     // 开启模态登陆框登陆框
								 $("#modaltrigger").click();
								  unbindallfun();
								 $("#loginbtn").on('click',logindao);//注册事件
								 $("#erroinfo").html("<font color='red'>"+data.message+"</font>");
								 
							}
							
						}
				}
				sendAjaxRequest(options);
	 
	 }
	  function unbindallfun(){
	    $("#loginbtn").unbind('click',loginsub);
		$("#loginbtn").unbind('click',logindao);
		$("#loginbtn").unbind('click',chnlsublogindao);
		$("#loginbtn").unbind('click',shareDoclogindao);
	  }
      function login(){
	    $("#modaltrigger").click();
		unbindallfun();
		$("#loginbtn").on('click',logindao);//注册事件
	 }
	 
	 // 收藏文章与登陆
     // document.write("<a href='javascript:void(0)' onClick='personCollect()'>收藏该文章</a>&nbsp;");
     // document.write("<div class='headlogin'><a href='#' onclick='login()'>登陆</a></div>");//div 可以挑位置
       



//登陆与收藏   end

//分享   begin
// 分享的  cmd 为分享的id
function shareDoc(cmd,docid,doctitle){
     // ajax可以跨域请求
	       var url = shareDocurl;
				var options = {
						url:url,
						data:{"sharedocid":docid,"sharedoctitle":encodeURI(doctitle),"where":encodeURI(cmd)},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallbackshareDoc",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							    // 分享成功	
								//alert("分享成功");
							}else if(data.isSuccessOrfail=="FAIL"){
							     if(data.message=="未登陆"){
								      // 开启模态登陆框登陆框
								 $("#modaltrigger").click();
								 unbindallfun();
								 $("#loginbtn").on('click',shareDoclogindao);//注册事件
								 }
							}
							
						}
				}
				sendAjaxRequest(options);
}
function shareDoclogindao(){
   logindao();	// 异步关闭了	  
   shareDoc(cmd,docid,doctitle);//在保存文档
}


//分享   end

//栏目订阅  begin
  
       function chnlsub(url,chnlid,chnldesc){
	        // ajax可以跨域请求
				var options = {
						url:url,
						data:{"chnlid":chnlid,"chnldesc":encodeURI(chnldesc)},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallbackchnlsub",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							    // 分享成功	
								alert(data.message);
							}else if(data.isSuccessOrfail=="FAIL"){
							     if(data.message=="未登陆"){
								      // 开启模态登陆框登陆框
								 $("#modaltrigger").click();
								 unbindallfun();
								 $("#loginbtn").on('click',chnlsublogindao);//注册事件
								 }
							}
							
						}
				}
				sendAjaxRequest(options);
	   }
        function chnlsublogindao(){
		    logindao();			  
		    chnlsub(url,chnlid,chnldesc);//在保存文档
		}
		
         
  

//栏目订阅  end

//文章评论   begin

	    //加载评论列表
	    function getCommentByDocId(docid,pagecount,pagenum){
		    // ajax可以跨域请求
	       var url = getCommentByDocIdurl;
				var options = {
						url:url,
						data:{"docid":docid,"pagecount":pagecount,"pagenum":pagenum},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallbackcommentlist",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							     $(".listcomment").css("display","block");
							     $(".morecomment").css("display","block");
							    // 获得当前文档的评论列表
								$("#commentnum").html("("+"<font color='red'>"+data.count+"</font>"+")");//评论总数
								var ul=$("#listcommentcon ul")[0];
								for(var i=0;i<data.pagecount;i++){
								
								 var li='<li>'+
										 '<div>'+
											  '<div class="info">'+
											   '<span class="infoname" style="color:#ff8500">'+data.rows[i].userName+'</span>&nbsp;&nbsp;'+
											   '<span class="infocomtime">'+data.rows[i].comtime+'</span>'+
											  '</div>'+
											  '<div class="commencon">'+
											  '&nbsp;&nbsp;&nbsp;&nbsp;'+data.rows[i].content+	  
											  '</div>'+
										 '</div>'+
									  '</li>'
								 var jqueryli=$(li);
                                 ul.appendChild(jqueryli.get(0));								 
							   }
							  // 如果评论数小于查询的一页数，则跟多按钮不需要
                               if(data.pagecount<pagecount){
							      $(".morecomment").css("display","none");
							   }							  
							}else if(data.isSuccessOrfail=="FAIL"){
							     //全部隐藏
								 $("#commentnum").html("("+"<font color='red'>"+"还没有评论"+"</font>"+")");
							     $(".listcomment").css("display","none");
							     $(".morecomment").css("display","none"); 
							}
							
						}
				}
				sendAjaxRequest(options);
		
		}
		// 加载更多的全局常量
         var morenowpage=1;
		//加载更多评论
	    function getMoreComment(docid,pagecount){
		   morenowpage++;
		    // ajax可以跨域请求
	       var url = getCommentByDocIdurl;
				var options = {
						url:url,
						data:{"docid":docid,"pagecount":pagecount,"pagenum":morenowpage},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallbackmorecommentlist",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							    // 获得当前文档的评论列表
								var ul=$("#listcommentcon ul")[0];
								for(var i=0;i<data.pagecount;i++){
								
								 var li='<li>'+
										 '<div>'+
											  '<div class="info">'+
											   '<span class="infoname" style="color:#ff8500">'+data.rows[i].userName+'</span>&nbsp;&nbsp;'+
											   '<span class="infocomtime">'+data.rows[i].comtime+'</span>'+
											  '</div>'+
											  '<div class="commencon">'+
											  '&nbsp;&nbsp;&nbsp;&nbsp;'+data.rows[i].content+	  
											  '</div>'+
										 '</div>'+
									  '</li>'
								 var jqueryli=$(li);
                                 ul.appendChild(jqueryli.get(0));								 
							   }
                               // 如果评论数小于查询的一页数，则跟多按钮不需要
                               if(data.pagecount<pagecount){
							      $(".morecomment").css("display","none");
							   }							   
							}else if(data.isSuccessOrfail=="FAIL"){
							     $(".morecomment").css("display","none");// 如果请求失败也隐藏
							}
							
						}
				}
				sendAjaxRequest(options);
		}
        //评论提交
        function commentSubmit(){
		  // ajax可以跨域请求
	       var url = commentSubmiturl;
				var options = {
						url:url,
						data:{"commentcon":encodeURI($("#commentcon").val()),"doctitle":encodeURI($("#doctitle").val()),"docid":$("#docid").val()},
						jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
                        jsonpCallback:"success_jsonpCallbackSubmit",//callback的function名称
						callBackFun:function(data){
							if(data.isSuccessOrfail=="SUCCESS"){
							    // 评论保存成功，刷新评论列表
								$("#commentcon").val("");//设置品论内容为空
								 alert("评论成功");
								 $("#listcommentcon ul li").remove();
								 getCommentByDocId($("#docid").val(),5,1);//重新加载评论
								 morenowpage=1;//让更多又变回1
							}else if(data.isSuccessOrfail=="FAIL"){
							     // 开启模态登陆框登陆框
								 $("#modaltrigger").click();
								 unbindallfun();
								 $("#loginbtn").on('click',logindao);//注册事件
							}
							
						}
				}
				sendAjaxRequest(options);
		  
		}	
	
//文章评论   end

