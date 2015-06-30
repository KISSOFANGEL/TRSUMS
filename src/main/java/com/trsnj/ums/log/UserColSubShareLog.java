/*
 * 文 件 名:  UserColSubShareLog.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-2-12
 */
package com.trsnj.ums.log;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;

import com.opensymphony.xwork2.ActionContext;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IBehaviorService;
import com.trsnj.ums.service.IUserLogsService;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-2-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserColSubShareLog
{
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
        /*for(Object obj:args){
            System.out.println(obj+"==========system.out===============");
        }*/
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
        //保存文档日志
        if(cName.endsWith("CollectServiceImpl")){
            if("saveCollectFront".equals(methodName)){
                String doctitle=(String)args[0];
                String docid=(String)args[1];
                String userid=(String)args[2];
                behaviorService.saveCollectLog(doctitle,docid,userid);
            }else if("delCollectByIds".equals(methodName)){
                String ids=(String)args[1];
                long userid=(Long)args[0];
                behaviorService.saveDelCollectDocLog(ids,userid); 
            }
        }else if(cName.endsWith("ShareServiceImpl")){
            if("shareDoc".equals(methodName)){
                String doctitle=(String)args[1];
                String docid=(String)args[0];
                String sharewhere=(String)args[2];
                String userid=(String)args[3];
                behaviorService.saveShareDocLog(doctitle,docid,sharewhere,userid);
            }else if("delShareByIds".equals(methodName)){
               //long userId,String ids
                String ids=(String)args[1];
                String userid=(String)args[0];
                behaviorService.saveDelShareDocLog(ids,userid);
            }
        }else if(cName.endsWith("CommentService")){
            //String userId, String docid,String doctitle, String commentcon
            if("saveCommentFront".equals(methodName)){
            String doctitle=(String)args[2];
            String docid=(String)args[1];
            String commentcon=(String)args[3];
            String userid=(String)args[0];
            behaviorService.saveCommentDocLog(doctitle,docid,commentcon,userid);
          }else if("delCommentByIds".equals(methodName)){
              //long userId,String ids
              String ids=(String)args[1];
              String userid=(String)args[0];
              behaviorService.saveDelCommentDocLog(ids,userid);
          }
            
        }else if(cName.endsWith("SubscriptionServiceImpl")){
            //String chnlid, String chnldesc, String userId
            if("chnlsub".equals(methodName)){
                
                String chnldesc=(String)args[1];
                String chnlid=(String)args[0];
                String userid=(String)args[2];
                behaviorService.saveChnlSubLog(chnlid,chnldesc,userid);
            }else if("delSubscriptionByIds".equals(methodName)){
                long userid=(Long)args[0];
                String ids=(String)args[1];
                behaviorService.saveDelChnlSubLog(ids,userid);
            }
        }
       // System.out.println("被拦截方法调用之后调用此方法，输出此语句"+username);  
    }  
    
    
}
