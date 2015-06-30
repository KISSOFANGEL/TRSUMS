/**
 * 共同ajax调用
 * 
 * @param options
 *            是一个自定义对象属性有 data:提交参数信息 url:请交路径 callBackFun:回调函数
 */
function sendAuthorityAjaxRequest(options) {
	$.ajax({
		async : false,
		cache : false,
		type : "POST",
		dataType : "jsonp",// 跨域的时候必须是jsonp类型
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		jsonp : options.jsonp,// 服务端用于接收callback调用的function名的参数
		jsonpCallback : options.jsonpCallback,// callback的function名称
		data : options.data,
		url : options.url,// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) {// 这是ajax定义的回调函数 且将data传给可以是自己自定义的函数的参数
			if (data.isSuccessOrfail == "SUCCESS") {
				// 当自定义回调函数为null时，调用默认函数处理
				if (options.callBackFun == null) { // 当回调函数未定义时
					$.messager.show({
						title : '提示信息',
						msg : data.message
					});
					// 是否重新加载table
					if (options.reloadTable != "undefined") {
						reloadTable(options.reloadTable);// options.reloadTable为加载
															// 表格的id
					}
				} else {// 当有回调函数的时候，回调里面也加提示框来提示
					options.callBackFun(data);// 当回调函数已经定义时就直接调用
												// 且将data传给可以是自己自定义的函数的参数
				}
			} else {
				options.callBackFun(data);
			}

		}
	});
}
/**
 * wcm 前台文档权限的js检查
 */
function authoritydocment(url, data) {

	// ajax可以跨域请求
	var options = {
		url : url,
		data : data,
		jsonp : "callbackparam",// 服务端用于接收callback调用的function名的参数
		jsonpCallback : "success_AuthorityCallback",// callback的function名称
		callBackFun : function(data) {
			if (data.result) {
				// 能看

			} else if (!data.result) {
				// 关闭当前页，打开pubindex.html
				// window.opener=null;
				// window.open("/pubindex.html","_blank") //打开新网页
				windowOpen('/bzhyjs/pubindex.html', '_blank');
				CloseWebPage();
				/*
				 * var inst = $("[data-remodal-id=modal]").remodal();
				 * inst.open(); if(data.online){//如果是在线状态的
				 * $(".reglogin").css("display","none"); }
				 */
				// alert("对不起您的权限不足,不能查看此篇文档!");
				// parent.window.close();
			}

		}
	}
	sendAuthorityAjaxRequest(options);

}
// 关闭当前页面
function CloseWebPage() {
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
			window.opener = null;
			window.close();
		} else {
			window.open('', '_top');
			window.top.close();
		}
	} else if (navigator.userAgent.indexOf("Firefox") > 0) {
		window.location.href = 'about:blank ';
	} else {
		window.opener = null;
		window.open('', '_self', '');
		window.close();
	}
}
// 打开一个页面
function windowOpen(url, target) {
	var a = document.createElement("a");
	a.setAttribute("href", url);
	if (target == null) {
		target = '';
	}
	a.setAttribute("target", target);
	document.body.appendChild(a);
	if (a.click) {
		a.click();
	} else {
		try {
			var evt = document.createEvent('Event');
			a.initEvent('click', true, true);
			a.dispatchEvent(evt);
		} catch (e) {
			window.open(url);
		}
	}
	document.body.removeChild(a);
}
