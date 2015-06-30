<%@page import="com.trsnj.ums.pojo.UMSUser"%>
<%@page import="com.trsnj.ums.service.impl.UserServiceImpl"%>
<%@page import="com.trsnj.ums.service.IUserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String  param = request.getParameter("param");
String name = request.getParameter("name");
IUserService udi =new UserServiceImpl();//依赖不注入userdao，roledao等对象
String username = session.getValue("userName")+"";
UMSUser tempuser ;


if("userName".equals(name)){
	tempuser =  udi.getUserByUserName(param);
	if(tempuser!=null){out.print("{\"info\":\"用户名已存在！\",\"status\":\"n\"}");return;}
	
}
if("email".equals(name)){
tempuser = udi.getUserByEmail(param);
if(tempuser!=null){out.print("{\"info\":\"邮箱名已存在！\",\"status\":\"n\"}");return;}
}
if("oldPassWord".equals(name)){
tempuser = udi.checkPasswordByUserName(username, param);
if(tempuser==null){out.print("{\"info\":\"密码不正确！\",\"status\":\"n\"}");return;}
}

if("registeremail".equals(name)){
tempuser = udi.getUserByEmail(param);
if(tempuser==null){out.print("{\"info\":\"该邮箱未被注册！\",\"status\":\"n\"}");return;}
}
out.print("{\"info\":\"验证通过！\",\"status\":\"y\"}");
%>



