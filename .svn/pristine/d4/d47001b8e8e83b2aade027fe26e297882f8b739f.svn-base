package com.trsnj.ums.exception;

import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.trsnj.ums.entity.BaseEntity;
import com.trsnj.ums.entity.CONST;

public class ExceptionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	   @Override
	    public String intercept(ActionInvocation invocation) throws Exception {
	        String result;
	        try {
	            result = invocation.invoke();
	        } catch (Exception e) {//拦截异常
	            result = handleJsonResponseEx(invocation, e);
	            if (result != null) {
	                return result; //已处理json异常
	            }
	            throw e;
	        }
	        return result;
	    }

	    private String handleJsonResponseEx(ActionInvocation invocation, Exception ex) throws Exception {
	        String methodName = invocation.getProxy().getMethod();
	        Method method = invocation.getAction().getClass().getMethod(methodName);
	        if (method == null) {
	            return null;
	        }
	        //处理Json响应异常
	        Map<String, ResultConfig> results = invocation.getProxy().getConfig().getResults();
	        if (results.containsKey("jsonexception")) {
	            BaseEntity msg = new BaseEntity();
	            msg.setIsSuccessOrfail(CONST.FAIL);
	            msg.setMessage(CONST.MESSAGE_FAIL);//操作失败
	            // 如果是具体的异常则信息提示更具体一点（ExceptionConstants下面去注册具体的信息）
	             if(ex instanceof AppRunTimeException)
	            {
	                 // 运行时异常
	            	 AppRunTimeException exception = (AppRunTimeException)ex;
	            	 String errorMessage = ExceptionConstants.getReturnCodeMap().get(  
	            			 exception.getExceptionCode());
	            	 msg.setMessage(errorMessage);
	            }else{
	            	logger.error("未转换获异常",ex);
	            }
	           // 获得action下面的值栈
	            invocation.getStack().setValue("baseEntity", msg);
	            // 在返回值，将baseEntity转换成json格式传递到页面去
	            return "jsonexception";
	        }
	        return null;

	    }
   
}
