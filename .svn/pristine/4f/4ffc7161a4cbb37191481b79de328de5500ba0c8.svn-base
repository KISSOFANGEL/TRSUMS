/*
 * 文 件 名:  UMSSaveLog.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-2-9
 */
package com.trsnj.ums.log;

import java.util.Iterator;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;

import com.opensymphony.xwork2.ActionContext;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IBehaviorService;
import com.trsnj.ums.service.IUserLogsService;
import com.trsnj.ums.service.impl.BehaviorServiceImpl;
import com.trsnj.ums.service.impl.UserLogsServiceImpl;

/**
 *注册，登陆等日志
 *  
 * @author  dzy
 * @version  [V1.00, 2015-2-9]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSSaveLog
{
  //在类里面写方法，方法名诗可以任意的。此处我用标准的before和after来表示  
    //JoinPoint joinpoint
    //此处的JoinPoint类可以获取，action所有的相关配置信息和request等内置对象。  
  private Logger logger = Logger.getLogger(UMSSaveLog.class);
  private IUserLogsService userLogService=null;
  private IBehaviorService behaviorService=null;
  
  /**
 * 获取 userLogService
 * @return 返回 userLogService
 */
public IUserLogsService getUserLogService()
{
    return userLogService;
}
/**
 * 设置 userLogService
 * @param 对userLogService进行赋值
 */
public void setUserLogService(IUserLogsService userLogService)
{
    this.userLogService = userLogService;
}
/**
 * 获取 behaviorService
 * @return 返回 behaviorService
 */
public IBehaviorService getBehaviorService()
{
    return behaviorService;
}
/**
 * 设置 behaviorService
 * @param 对behaviorService进行赋值
 */
public void setBehaviorService(IBehaviorService behaviorService)
{
    this.behaviorService = behaviorService;
}
public void before(JoinPoint joinpoint){  
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession session=request.getSession();
      Class className = joinpoint.getTarget().getClass();
      Object[] args = joinpoint.getArgs();
      String methodName = joinpoint.getSignature().getName();
      for(Object obj:args){
          System.out.println(obj+"==========system.out===============");
      }
      //退出系统之前保存
      if("quitSystem".equals(methodName)){
          // 退出系统的日志
          String userId=session.getAttribute("userId")+"";
          if(userId!=null&&!"null".equals(userId)){
              userLogService.updateUserQuitSystme(userId);
          }
      }
      //joinpoint.getArgs();//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象  
      //System.out.println(joinpoint.getArgs());
     // System.out.println("被拦截方法调用之前调用此方法，输出此语句"+className+"||"+methodName);  
      logger.info("INFO[方法调用:]"+className+"||"+methodName+"||"+args);
  }  
  public void after(JoinPoint joinpoint){ 
      Class className = joinpoint.getTarget().getClass();
      String cName=className.getName();
      Object[] args = joinpoint.getArgs();
      String methodName = joinpoint.getSignature().getName();
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession session=request.getSession();
      String username=(String) session.getAttribute("userName");
      if("logindao".equals(methodName)||"loginmanagerdao".equals(methodName)){
          
          String userId=session.getAttribute("userId")+"";
          String ip=request.getLocalAddr();
          if(userId!=null&&!"null".equals(userId))
          userLogService.saveUserLoginLog(userId, ip);
          logger.info("FROM"+" "+ip+" "+"的"+username+"正在登陆");
      }
      if("register".equals(methodName)){// service层与action层都有此方法,怎么获取action层的方法的参数
          String ip=request.getLocalAddr();
          ActionContext actionContext = ActionContext.getContext();
          Map map = actionContext.getParameters();
          String[] s=(String[])map.get("userName");
          String userName="";
          for(String sr:s){
              userName=sr;
          }
         // System.out.println(userName+"======userName==========");
          userLogService.saveUserRegisterLog(userName, ip);
          logger.info("FROM"+" "+ip+" "+"的正在注册用户");
      }
     // System.out.println("被拦截方法调用之后调用此方法，输出此语句"+username);  
  }  
}
