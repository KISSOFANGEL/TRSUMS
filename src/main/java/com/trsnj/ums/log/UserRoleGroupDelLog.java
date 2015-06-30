/*
 * 文 件 名:  UserRoleGroupDelLog.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-2-11
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
 * @version  [V1.00, 2015-2-11]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserRoleGroupDelLog
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
        //System.out.println(methodName+"||"+cName+"=============================");
        //管理员操作删除用户  用户是假删除操作
        if("delete".equals(methodName)&&cName.endsWith("UserServiceImpl")){
            UMSUser u=(UMSUser)args[0];
            long deluserId=u.getUserId();//删除的用户id
            String userId=""+session.getAttribute("userId");//操作用户的id
            if(!"null".equals(userId))
            behaviorService.saveDelUserLog(deluserId,Long.parseLong(userId));
        }else if("deleteRoleAndChannel".equals(methodName)&&cName.endsWith("RoleServiceImpl")){
            UMSRole r=(UMSRole)args[0];
            long delroleid=r.getRoleId();
            String userId=""+session.getAttribute("userId");//操作用户的id
            if(!"null".equals(userId))
            behaviorService.saveDelRoleLog(delroleid,Long.parseLong(userId));
            
        }else if("deleteGroup".equals(methodName)&&cName.endsWith("GroupServiceImpl")){
            UMSGroup g=(UMSGroup)args[0];
            long delgroupid=g.getGroupId();
            String userId=""+session.getAttribute("userId");//操作用户的id
            if(!"null".equals(userId))
            behaviorService.saveDelGroupLog(delgroupid,Long.parseLong(userId));
        }
       // System.out.println("被拦截方法调用之后调用此方法，输出此语句"+username);  
    }  
    
    
}
