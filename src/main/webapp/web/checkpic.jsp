<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="errorPage.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<% 
response.setContentType("text/html;charset=utf-8"); 
request.setCharacterEncoding("utf-8");
String rand = (String)session.getAttribute("rand");
String input = request.getParameter("rand");

  if (rand.equals(input)) {
	  response.getWriter().print(3);
  } else {
	  response.getWriter().print(0);
  }
%>

