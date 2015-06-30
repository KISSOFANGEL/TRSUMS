<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="java.io.*"%>
<%@ page import="org.apache.http.HttpResponse"%>
<%@ page import="org.apache.http.client.methods.HttpGet"%>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.util.EntityUtils" %>

<%
		String result = null;
		String url="http://localhost:8080/wcm/channel/getWcmTree.jsp";		
		BufferedWriter bw = null;
		HttpGet httpget = new HttpGet(url);
		HttpResponse content=null;
		try{
			content = new DefaultHttpClient().execute(httpget);
			if (content.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(content.getEntity());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().print(result);
%>
