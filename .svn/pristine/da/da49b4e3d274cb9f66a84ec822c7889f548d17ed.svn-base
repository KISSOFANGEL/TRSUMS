/*
 * 文 件 名:  UMSUserLogs.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-18
 */
package com.trsnj.ums.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 *  登陆日志表
 * @author  dzy
 * @version  [V1.00, 2014-12-18]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name="UMSUserLogs")
public class UMSUserLogs implements Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8095860645171995118L;
    @GenericGenerator(name = "userloggenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "userloggenerator")// 引用这个生成器
    @Column(name = "UMSUserLogsid", unique = true, nullable = false)
    private long UMSUserLogsid;
    @Column(length=50)
    private String logintime;
    @Column(length=50)
    private String loginouttime;
    @Column(length=100)
    private String ip;
    @Column(length=200)
    private String source;//来源
    @Column(length=1000)
    private String remark;
    @ManyToOne(fetch = FetchType.LAZY)//不懒加载
    @JoinColumn(name = "userid")
    private UMSUser user;//注册的时候用户没绑定,登陆与退出的时候绑定用户
    private String userName;
    private String regtime;//注册时间
    
    /**
     * 获取 regtime
     * @return 返回 regtime
     */
    public String getRegtime()
    {
        return regtime;
    }
    /**
     * 设置 regtime
     * @param 对regtime进行赋值
     */
    public void setRegtime(String regtime)
    {
        this.regtime = regtime;
    }
    /**
     * 获取 userName
     * @return 返回 userName
     */
    public String getUserName()
    {
        return userName;
    }
    /**
     * 设置 userName
     * @param 对userName进行赋值
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    /**
     * 获取 uMSUserLogsid
     * @return 返回 uMSUserLogsid
     */
    public long getUMSUserLogsid()
    {
        return UMSUserLogsid;
    }
    /**
     * 设置 uMSUserLogsid
     * @param 对uMSUserLogsid进行赋值
     */
    public void setUMSUserLogsid(long uMSUserLogsid)
    {
        UMSUserLogsid = uMSUserLogsid;
    }
    /**
     * 获取 remark
     * @return 返回 remark
     */
    public String getRemark()
    {
        return remark;
    }
    /**
     * 设置 remark
     * @param 对remark进行赋值
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    /**
     * 获取 logintime
     * @return 返回 logintime
     */
    public String getLogintime()
    {
        return logintime;
    }
    /**
     * 设置 logintime
     * @param 对logintime进行赋值
     */
    public void setLogintime(String logintime)
    {
        this.logintime = logintime;
    }
    /**
     * 获取 loginouttime
     * @return 返回 loginouttime
     */
    public String getLoginouttime()
    {
        return loginouttime;
    }
    /**
     * 设置 loginouttime
     * @param 对loginouttime进行赋值
     */
    public void setLoginouttime(String loginouttime)
    {
        this.loginouttime = loginouttime;
    }
    /**
     * 获取 ip
     * @return 返回 ip
     */
    public String getIp()
    {
        return ip;
    }
    /**
     * 设置 ip
     * @param 对ip进行赋值
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    /**
     * 获取 source
     * @return 返回 source
     */
    public String getSource()
    {
        return source;
    }
    /**
     * 设置 source
     * @param 对source进行赋值
     */
    public void setSource(String source)
    {
        this.source = source;
    }
    /**
     * 获取 user
     * @return 返回 user
     */
    public UMSUser getUser()
    {
        return user;
    }
    /**
     * 设置 user
     * @param 对user进行赋值
     */
    public void setUser(UMSUser user)
    {
        this.user = user;
    }
    
}
