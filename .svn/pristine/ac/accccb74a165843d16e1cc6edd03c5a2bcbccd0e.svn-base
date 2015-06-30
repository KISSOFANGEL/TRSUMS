<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String personpath = request.getContextPath();
String personroot = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+personpath;

// 前台个人中心页面的跳转
String userName=(String)session.getValue("userName");
if(userName==null){
    //request.getRequestDispatcher("user_center.jsp").forward(request, response);
    //response.sendRedirect(personroot+"/web/login.jsp");
	response.getWriter().write("<script>window.parent.location.href='"+personroot+"/web/login.jsp';</script>");
  
}



%>