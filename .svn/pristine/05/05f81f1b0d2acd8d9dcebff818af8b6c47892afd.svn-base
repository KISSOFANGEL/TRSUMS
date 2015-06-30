/*
 * 文 件 名:  UMSSubscription.java
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
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-18]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name = "UMSSubscription")
public class UMSSubscription implements Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 163137288781089678L;
    @GenericGenerator(name = "subgenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "subgenerator")// 引用这个生成器
    @Column(name = "UMSSubscriptionid", unique = true, nullable = false)
    private long UMSSubscriptionid;
    private int substatus;
    private int subchnl;
    @Column(length=100,nullable=false)
    private String chnldesc;
    @Column(length=1000)
    private String remark;
    @Column(length=50,nullable=false)
    private String subtime;
    @ManyToOne(fetch = FetchType.LAZY)//懒加载
    @JoinColumn(name = "userid",nullable=false)
    private UMSUser user;
   
   
    /**
     * 获取 uMSSubscriptionid
     * @return 返回 uMSSubscriptionid
     */
    public long getUMSSubscriptionid()
    {
        return UMSSubscriptionid;
    }
    /**
     * 设置 uMSSubscriptionid
     * @param 对uMSSubscriptionid进行赋值
     */
    public void setUMSSubscriptionid(long uMSSubscriptionid)
    {
        UMSSubscriptionid = uMSSubscriptionid;
    }
    /**
     * 获取 chnldesc
     * @return 返回 chnldesc
     */
    public String getChnldesc()
    {
        return chnldesc;
    }
    /**
     * 设置 chnldesc
     * @param 对chnldesc进行赋值
     */
    public void setChnldesc(String chnldesc)
    {
        this.chnldesc = chnldesc;
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
     * 获取 substatus
     * @return 返回 substatus
     */
    public int getSubstatus()
    {
        return substatus;
    }
    /**
     * 设置 substatus
     * @param 对substatus进行赋值
     */
    public void setSubstatus(int substatus)
    {
        this.substatus = substatus;
    }
    /**
     * 获取 subchnl
     * @return 返回 subchnl
     */
    public int getSubchnl()
    {
        return subchnl;
    }
    /**
     * 设置 subchnl
     * @param 对subchnl进行赋值
     */
    public void setSubchnl(int subchnl)
    {
        this.subchnl = subchnl;
    }
    /**
     * 获取 subtime
     * @return 返回 subtime
     */
    public String getSubtime()
    {
        return subtime;
    }
    /**
     * 设置 subtime
     * @param 对subtime进行赋值
     */
    public void setSubtime(String subtime)
    {
        this.subtime = subtime;
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
