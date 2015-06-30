/*
 * 文 件 名:  UMSMessage.java
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
 *  消息表要重新设计构思  对用户，组织，角色都有关联
 * @author  dzy
 * @version  [V1.00, 2014-12-18]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name="UMSMessage")
public class UMSMessage implements Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8444516375541409310L;
    @GenericGenerator(name = "msggenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "msggenerator")// 引用这个生成器
    @Column(name = "UMSMessageid", unique = true, nullable = false)
    private long UMSMessageid;
    @Column(nullable=false)
    private long mesauthor;//当前用户
    @Column(length=2000,nullable=false)
    private String mescon;   //消息内容
    @Column(length=2000)
    private String returnMes;
    @Column(length=1000)
    private String remark;
    @Column(length=50)
    private String mestime;
    @Column(length=50)
    private String replytime;
    private int mestype;//信息类型0表示其他类型   在完成认证个人信息的时候，用户向系统管理员发送信息，此时信息类型的mestype=1，此时在后台审批认证用户的时候，根据1来查找,mestype=2表示用户向管理员的求助信息
    private int messtatus;// 0未读 1已读
    private int meslev;   // 0一般信息 1重要通知 2紧急通知
    @ManyToOne(fetch = FetchType.EAGER)//懒加载   在回复求助信息的时候，后台展示时用的不是懒加载（replysysmessage.jsp），原来是懒加载
    @JoinColumn(name = "reception")//userid
    private UMSUser user;
    @Column(length=500)
    private  String mestitle;
    private Long receprole;// 用户的求助信息,发送给系统管理员角色,配合mestype=2来使用，认证信息配合mestype=1来使用
    
    
    /**
     * 获取 receprole
     * @return 返回 receprole
     */
    public Long getReceprole()
    {
        return receprole;
    }
    /**
     * 设置 receprole
     * @param 对receprole进行赋值
     */
    public void setReceprole(Long receprole)
    {
        this.receprole = receprole;
    }
    /**
     * 获取 replytime
     * @return 返回 replytime
     */
    public String getReplytime()
    {
        return replytime;
    }
    /**
     * 设置 replytime
     * @param 对replytime进行赋值
     */
    public void setReplytime(String replytime)
    {
        this.replytime = replytime;
    }
    
    /**
     * 获取 mestype
     * @return 返回 mestype
     */
    public int getMestype()
    {
        return mestype;
    }
    /**
     * 设置 mestype
     * @param 对mestype进行赋值
     */
    public void setMestype(int mestype)
    {
        this.mestype = mestype;
    }
    /**
     * 获取 mestitle
     * @return 返回 mestitle
     */
    public String getMestitle()
    {
        return mestitle;
    }
    /**
     * 设置 mestitle
     * @param 对mestitle进行赋值
     */
    public void setMestitle(String mestitle)
    {
        this.mestitle = mestitle;
    }
    /**
     * 获取 returnMes
     * @return 返回 returnMes
     */
    public String getReturnMes()
    {
        return returnMes;
    }
    /**
     * 设置 returnMes
     * @param 对returnMes进行赋值
     */
    public void setReturnMes(String returnMes)
    {
        this.returnMes = returnMes;
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
     * 获取 mescon
     * @return 返回 mescon
     */
    public String getMescon()
    {
        return mescon;
    }
    /**
     * 设置 mescon
     * @param 对mescon进行赋值
     */
    public void setMescon(String mescon)
    {
        this.mescon = mescon;
    }
    /**
     * 获取 uMSMessageid
     * @return 返回 uMSMessageid
     */
    public long getUMSMessageid()
    {
        return UMSMessageid;
    }
    /**
     * 设置 uMSMessageid
     * @param 对uMSMessageid进行赋值
     */
    public void setUMSMessageid(long uMSMessageid)
    {
        UMSMessageid = uMSMessageid;
    }
    /**
     * 获取 mesauthor
     * @return 返回 mesauthor
     */
    public long getMesauthor()
    {
        return mesauthor;
    }
    /**
     * 设置 mesauthor
     * @param 对mesauthor进行赋值
     */
    public void setMesauthor(long mesauthor)
    {
        this.mesauthor = mesauthor;
    }
    /**
     * 获取 mestime
     * @return 返回 mestime
     */
    public String getMestime()
    {
        return mestime;
    }
    /**
     * 设置 mestime
     * @param 对mestime进行赋值
     */
    public void setMestime(String mestime)
    {
        this.mestime = mestime;
    }
    /**
     * 获取 messtatus
     * @return 返回 messtatus
     */
    public int getMesstatus()
    {
        return messtatus;
    }
    /**
     * 设置 messtatus
     * @param 对messtatus进行赋值
     */
    public void setMesstatus(int messtatus)
    {
        this.messtatus = messtatus;
    }
    /**
     * 获取 meslev
     * @return 返回 meslev
     */
    public int getMeslev()
    {
        return meslev;
    }
    /**
     * 设置 meslev
     * @param 对meslev进行赋值
     */
    public void setMeslev(int meslev)
    {
        this.meslev = meslev;
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
