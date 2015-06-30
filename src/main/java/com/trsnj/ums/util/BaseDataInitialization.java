/*
 * 文 件 名:  BaseDataInitialization.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-1-5
 */
package com.trsnj.ums.util;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.trsnj.ums.dao.IRoleDao;
import com.trsnj.ums.dao.IUserDao;
import com.trsnj.ums.dao.impl.RoleDaoImpl;
import com.trsnj.ums.dao.impl.UserDaoImpl;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-1-5]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class BaseDataInitialization implements ServletContextListener
{
    private  IRoleDao roledao=null;//spring 不能将此类管理起来beanid= roleDaoImpl
    private  IUserDao userdao=null;//beanid = userDaoImpl
    
    /**
     * 获取 roledao
     * @return 返回 roledao
     */
    public IRoleDao getRoledao()
    {
        return roledao;
    }

    /**
     * 设置 roledao
     * @param 对roledao进行赋值
     */
    public void setRoledao(IRoleDao roledao)
    {
        this.roledao = roledao;
    }

    /**
     * 获取 userdao
     * @return 返回 userdao
     */
    public IUserDao getUserdao()
    {
        return userdao;
    }

    /**
     * 设置 userdao
     * @param 对userdao进行赋值
     */
    public void setUserdao(IUserDao userdao)
    {
        this.userdao = userdao;
    }

    /**
     * @param sce
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ApplicationContext context=null;
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()); 
        roledao=(RoleDaoImpl)context.getBean("roleDaoImpl");
        userdao=(UserDaoImpl)context.getBean("userDaoImpl");
        String email=CommonUtil.getValue("FROM_MAIL");
        String flag="false";
        flag=CommonUtil.getValue("initialize");
        if("false".equals(flag)){
            return;
        }
       
        // 初始化角色的数据
       
        UMSRole role=new UMSRole();
        role.setCruser("admin");
        role.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
        role.setRoleDesc("普通用户");
        role.setRoleName("普通用户");
        role.setRoleLevel(1);
        role.setRoleOrder(1);
        role.setRoleType(1);
        UMSRole role2=new UMSRole();
        role2.setCruser("admin");
        role2.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
        role2.setRoleDesc("认证用户");
        role2.setRoleName("认证用户");
        role2.setRoleLevel(2);
        role2.setRoleOrder(2);
        role2.setRoleType(2);
        UMSRole role3=new UMSRole();
        role3.setCruser("admin");
        role3.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
        role3.setRoleDesc("vip用户");
        role3.setRoleName("vip用户");
        role3.setRoleLevel(3);
        role3.setRoleOrder(3);
        role3.setRoleType(3);
        UMSRole role4=new UMSRole();
        role4.setCruser("admin");
        role4.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
        role4.setRoleDesc("系统管理员");
        role4.setRoleName("系统管理员");
        role4.setRoleLevel(4);
        role4.setRoleOrder(4);
        role4.setRoleType(4);
        UMSRole role5=new UMSRole();
        role5.setCruser("admin");
        role5.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
        role5.setRoleDesc("游客");
        role5.setRoleName("游客");
        role5.setRoleLevel(5);
        role5.setRoleOrder(5);
        role5.setRoleType(5);
        roledao.save(role);
        roledao.save(role2);
        roledao.save(role3);
        roledao.save(role4);
        roledao.save(role5);
        //初始化一个用户数据，并且赋予系统管理员的角色
       
         UMSUser user=new UMSUser();
         user.setCruser("admin");
         user.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
         user.setPassWord(MD5Util.getSecretStr("trsadmin"));
         user.setStatus(1);
         user.setAddUserType(2);//2表示后台管理员的用户  1表示前台注册的用户
         user.setDelnote(1);
         user.setType(1);
         user.setUserName("admin");
         user.setEmail(email);
         UMSRole umsrole5=new UMSRole();
         umsrole5.setRoleId(4);//将admin用户添加到‘系统管理员’的角色里面去
         user.setUmsrole(umsrole5);
         userdao.add(user); 
    }
    
    /**
     * @param sce
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        
    }
    
}
