package com.trsnj.ums.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uri = request.getRequestURI();//得到请求的路径
		request.setCharacterEncoding("UTF-8");
		if(uri.contains("login")||uri.contains("register")||uri.contains("findpwd")||uri.contains("updatepwd")||uri.contains("getUserByUserName")||uri.contains("getUserByEmail")||uri.contains("checkRegisterEmail")||uri.contains("commentSubmit")||uri.contains("getCommentByDocId")||uri.contains("shareDoc")||uri.contains("chnlsub")) 
		{
			return arg0.invoke();
		}
		else
		{
		    HttpSession session=request.getSession();
		    String username=(String) session.getAttribute("userName");
		    if("".equals(username)||username==null)
		    {
		    	return "loginerror";// 到nologin页面去了
		    }
		    else
		    {
		    	return arg0.invoke();
		    }
		    
		}
	}

}
