package com.trsnj.ums.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;


public class CommunicateWithWCM {
	private static Logger logger =  Logger.getLogger(CommunicateWithWCM.class);
	/**
	 * test
	 * @throws UnsupportedEncodingException 
	 */
	public static String getdocsinfo(String ids,String url) throws UnsupportedEncodingException{
		System.out.println(ids+"||ids==================getdocsinfo");
	    String str = "";
		HttpClient httpclient = new DefaultHttpClient();   		
		HttpPost httpPost = new HttpPost(url);   //"http://192.168.1.55:8080/wcm/umscomunicationservice"
		List<NameValuePair> params=new ArrayList<NameValuePair>();   
		String pa = "{'docids':'"+ids+"'}";
		params.add(new BasicNameValuePair( "param",pa ));   
		httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));   
		try {
			
			HttpResponse response = httpclient.execute(httpPost);  
			int statuscode=response.getStatusLine().getStatusCode();
			System.out.println(httpclient.getParams().getParameter("param")+"||ids==param=============");
			System.out.println(statuscode+"||status=getdocsinfo===================");
			if(statuscode!=200){
			    return "";
			}
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				InputStream instreams = entity.getContent();
				str = convertStreamToString(instreams);
				httpPost.abort();
			}

			if (str != null) { //处理查询到的JSON串信息
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return str;

	}
	public static String getchnlsinfo(String ids,String url) throws UnsupportedEncodingException{
        String str = "";
        HttpClient httpclient = new DefaultHttpClient();        
        HttpPost httpPost = new HttpPost(url);   //"http://192.168.1.55:8080/wcm/umscomunicationservice"
        List<NameValuePair> params=new ArrayList<NameValuePair>();   
        String pa = "{'chnlids':'"+ids+"'}";
        params.add(new BasicNameValuePair( "param",pa ));   
        httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));   
        try {
            
            HttpResponse response = httpclient.execute(httpPost);
            int statuscode=response.getStatusLine().getStatusCode();
            if(statuscode!=200){
                return "";
            }
            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                InputStream instreams = entity.getContent();
                str = convertStreamToString(instreams);
                httpPost.abort();
            }

            if (str != null) { //处理查询到的JSON串信息
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return str;

    }
	
	public static String getdocsinfoinchnl(String ids,String url,int count) throws UnsupportedEncodingException{
        String str = "";
        HttpClient httpclient = new DefaultHttpClient();        
        HttpPost httpPost = new HttpPost(url);   //"http://192.168.1.55:8080/wcm/umscomunicationservice"
        List<NameValuePair> params=new ArrayList<NameValuePair>();   
        String pa = "{'chnlids':'"+ids+"','count':'"+count+"'}";
        params.add(new BasicNameValuePair( "param",pa ));   
        httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));   
        try {
            
            HttpResponse response = httpclient.execute(httpPost);
            int statuscode=response.getStatusLine().getStatusCode();
            if(statuscode!=200){
                return "";
            }
            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                InputStream instreams = entity.getContent();
                str = convertStreamToString(instreams);
                httpPost.abort();
            }

            if (str != null) { //处理查询到的JSON串信息
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return str;

    }
	
	public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
