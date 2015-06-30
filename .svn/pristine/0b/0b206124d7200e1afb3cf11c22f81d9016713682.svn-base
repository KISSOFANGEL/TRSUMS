package com.trsnj.ums.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trsnj.ums.entity.BaseEntity;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 增强action功能 1、封装普通对象的CURD操作 2、取得requst,response,session对象
 * 
 * @author dzy
 * 
 */
public abstract class BaseAction  extends ActionSupport
		implements SessionAware, ServletRequestAware, ServletResponseAware {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	// Action上下文
	protected ActionContext context = ActionContext.getContext();
	// 客户端请求request
	protected HttpServletRequest request;
	// 服务端响应response
	protected HttpServletResponse response;
	// session会话
	protected SessionMap session;
	
	// 当前表格所有数据   
	protected BaseEntity baseEntity = new BaseEntity();
	// 分页数据
	protected int page = 1;
	protected int rows = 10;


	
	/**
	 * 取当前会话用户的用户名
	 * @return
	 */
	protected String getUserName(){
		//return "test";
		Object obj = session.get("userName");
		if(obj!=null){
			return obj.toString();
		}else{
			throw new AppRunTimeException(ExceptionConstants.Code_0002);
		}
	}

	/**
	 * 取当前会话用户的ID
	 * @return
	 */
	protected String getUserId(){
		//return "1";
		Object obj = session.get("userId");		if(obj!=null){
			return obj.toString();
		}else{
		throw new AppRunTimeException(ExceptionConstants.Code_0002);
		}
	}
	/******************** 通过IOC方式获取 request,response,session *****************************************/
	public void setSession(Map map) {
		this.session = (SessionMap) map;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	public BaseEntity getBaseEntity() {
		return baseEntity;
	}
  //直接new的实体
	public void setBaseEntity(BaseEntity baseEntity) {
		this.baseEntity = baseEntity;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	
}
