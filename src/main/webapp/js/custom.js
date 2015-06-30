    /**  
     * 创建新选项卡  
     * @param tabId    选项卡id  
     * @param title    选项卡标题  
     * @param url      选项卡远程调用路径  
     */  
    function addTab(tabId,title,url){  
        //如果当前id的tab不存在则创建一个tab  
        if($("#"+tabId).html()==null){  
            var name = 'iframe_'+tabId;  
            $('#centerTab').tabs('add',{  // 动态在centerTab下添加div
                title: title,           
                closable:true,  
                cache : false,  
                //注：使用iframe即可防止同一个页面出现js和css冲突的问题  //这里的src属性是url能得到jsp页面
                content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'  
            });  
        }else{
        	$("#centerTab").tabs('select',title);//否侧就选中已存在的表界面
        }  
    }  
    /**
     * 共同ajax调用
     * @param options 是一个自定义对象属性有
     * data:提交参数信息
     * url:请交路径
     * reloadTable:要刷新的表格名称
     * callBackFun:回调函数
     */
    function sendAjaxRequest(options){
    	$.ajax({
    		async : false,
    		cache:false,
    		type: "POST",
    		dataType:"json",
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
    				$.messager.alert('错误提示',data.message,'error');
    			}
    		
    		}
    	});
    }
    /**
     * 为角色的请求定制的（带进度条）
     * @param options 是一个自定义对象属性有
     * data:提交参数信息
     * url:请交路径
     * reloadTable:要刷新的表格名称
     * callBackFun:回调函数
     */
    function sendEditRoleAjaxRequest(options){
    	$.ajax({
    		async : true,
    		cache:false,
    		type: "POST",
    		dataType:"json",
    		data : options.data,
    		url: options.url,//请求的action路径
    		error: function () {//请求失败处理函数
    			if(options.errorFun==null){
    				alert('请求失败');
    			}else{
    				options.errorFun();
    			}
    		},
    		beforeSend:function(){
    			if(options.errorFun==null){
    				
    			}else{
    				options.beforeSendFun();
    			}
    		},
    		complete:function(){
                if(options.completeFun==null){
    				
    			}else{
    				options.completeFun();
    			}
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
    				$.messager.alert('错误提示',data.message,'error');
    			}
    		
    		}
    	});
    }
    function showMessage(data){
    	if(data.isSuccessOrfail=="SUCCESS"){
			$.messager.show({title:'提示信息', msg:data.message});// 就是下面的提示信息
		}else{
			$.messager.alert('错误提示',data.message,'error');
		}
    }
    /**
     * 初始化窗口
     * @param options 是一个自定义对象属性有
     * id:窗口ID必须
     * title:窗口title必须
     * closeFun:关闭窗口事件 可选
     */
    function initDialog(options){
    	$("#"+options.id).dialog({
    		title :options.title,
    	//	position:[100,400],
    		modal: true,         	//模式窗口：窗口背景不可操作
    		resizable : true,   	//可拖动边框大小
    		center:true,
    		onClose : function(){   //继承自panel的关闭事件
    			if(options.closeFun!="undefined")
    				options.closeFun;
    		}
    	});
    }

    function reloadTable(tableId){
    	$('#'+tableId).datagrid('reload');
    }
    /**
     * 共同ajax调用
     * @param ops 是一个自定义对象属性有
     * url:请交路径
     * formId:表单ID
     * reloadTable:要刷新的表格名称
     * callBackFun:回调函数
     */
    function sendAjaxForm(ops){
    	
    	var options = {
    		url: ops.url,//请求的action路径
    		success:function(data){
    			if(data.isSuccessOrfail=="SUCCESS"){
    				//当自定义回调函数为null时，调用默认函数处理
        			if(ops.callBackFun=="undefined"){
        				$.messager.show({title:'提示信息', msg:data.message});
        				//是否重新加载table
        				if(ops.reloadTable!="undefined"){
        					$('#'+ops.reloadTable).datagrid('reload');
        				}
        			}else{
        				ops.callBackFun(data);
        			}
    			}else{
    				$.messager.alert('错误提示',data.message,'error');
    			}
    		
    		}
    	}
    	
    	$('#'+ops.formId).ajaxSubmit(options);
    	return false;//防止页面刷新
    	
    }