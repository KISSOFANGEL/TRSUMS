/*
 * 文 件 名:  UserLogsServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.service.impl;

import java.util.Date;
import java.util.List;

import com.trsnj.ums.dao.IUMSUserLogsDao;
import com.trsnj.ums.dao.impl.UMSUserLogsDaoImpl;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.pojo.UMSUserLogs;
import com.trsnj.ums.service.IUserLogsService;
import com.trsnj.ums.service.IUserService;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserLogsServiceImpl implements IUserLogsService
{
    //private IUMSUserLogsDao userlogsdao=new UMSUserLogsDaoImpl();
    private IUMSUserLogsDao userlogsdao=null;
    /**
     * 获取 userlogsdao
     * @return 返回 userlogsdao
     */
    public IUMSUserLogsDao getUserlogsdao()
    {
        return userlogsdao;
    }

    /**
     * 设置 userlogsdao
     * @param 对userlogsdao进行赋值
     */
    public void setUserlogsdao(IUMSUserLogsDao userlogsdao)
    {
        this.userlogsdao = userlogsdao;
    }
    /**
     * 保存登陆的日志信息
     * @param userId
     * @param ip
     * @see [类、类#方法、类#成员]
     */
    public void saveUserLoginLog(String userId,String ip){
        UMSUserLogs userlogs=new UMSUserLogs();
        userlogs.setIp(ip);
        userlogs.setLogintime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userId));
        userlogs.setUser(user);
        userlogs.setRemark("用户登陆与退出");
        userlogsdao.save(userlogs);
    }
    /**
     * 保存用户注册日志
     * @param u
     * @param ip
     * @see com.trsnj.ums.service.IUserLogsService#saveUserRegisterLog(com.trsnj.ums.pojo.UMSUser, java.lang.String)
     */
    public void saveUserRegisterLog(String userName, String ip){
        UMSUserLogs userlogs=new UMSUserLogs();
        userlogs.setIp(ip);
        userlogs.setRegtime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        userlogs.setUserName(userName);
        userlogs.setRemark("用户注册");
        userlogsdao.save(userlogs);
    }
    /**
     * 修改用户退出系统的日志
     * @param userId
     * @see com.trsnj.ums.service.IUserLogsService#updateUserQuitSystme(java.lang.String)
     */
    public void updateUserQuitSystme(String userId){
        //获得当前用户最后一次登陆时的日志记录
       List <UMSUserLogs> userlogs= userlogsdao.getUserLogsByUserId(Long.parseLong(userId), 0, 1);
       if(userlogs.size()>0&&userlogs!=null){
           UMSUserLogs userlog=userlogs.get(0);
           userlog.setLoginouttime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
           userlogsdao.saveOrUpdate(userlog);
       }
    }
}
