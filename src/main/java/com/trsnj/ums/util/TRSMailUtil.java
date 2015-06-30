package com.trsnj.ums.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;

public class TRSMailUtil {
	private static final String PROTOCOL = "smtp";	
	private static final int PORT = 25;
	private static  String HOST = "smtp.163.com";
	private static  String FROM = "wangjiang19881226@163.com";
	private static  String PWD = "wj475683936";
	private static String  mailWithAppendix="false";
	private static String fileName;
	static{
		Properties prop = new Properties();   
		InputStream in = TRSMailUtil.class.getResourceAsStream("/TRSMailSend.properties");   
		try {   
			prop.load(in); 
			FROM = prop.getProperty("FROM_MAIL").trim();   
			PWD = prop.getProperty("FROM_MAIL_PASSWORD").trim();   
			HOST = prop.getProperty("FROM_MAIL_HOST").trim();  
			mailWithAppendix = prop.getProperty("Mail_With_Appendix").trim();
			fileName = prop.getProperty("Mail_Appendix").trim();			
		} catch (IOException e) { 
			e.printStackTrace();   
		}
	}	
	/**
	 * 获取Session
	 * @return
	 */
	private static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);//设置服务器地址
		props.put("mail.store.protocol" , PROTOCOL);//设置协议
		props.put("mail.smtp.port", PORT);//设置端口
		props.put("mail.smtp.auth" , true);
		
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}
			
		};
		Session session = Session.getDefaultInstance(props , authenticator);
		
		return session;
	}
	
	public static void send(String toEmail , String content) {
		Session session = getSession();
		try {
            // Instantiate a message
            Message msg = new MimeMessage(session);
            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");
            if(mailWithAppendix.equals("true")) msg.setFileName(fileName);
            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            throw new AppRunTimeException(ExceptionConstants.Code_9999);
           // mex.printStackTrace();
        }
	}
	public static void sendFindpwd(String toEmail , String content,String subject) {
        Session session = getSession();
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);
            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);//邮箱主题
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");
            if(mailWithAppendix.equals("true")) msg.setFileName(fileName);
            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

