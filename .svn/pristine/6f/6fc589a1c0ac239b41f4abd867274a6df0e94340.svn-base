/*
 * 文 件 名:  UMSShare.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-29
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
 * @version  [V1.00, 2014-12-29]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name = "UMSShare")
public class UMSShare implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -136688115185103252L;
    @GenericGenerator(name = "sharegenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "sharegenerator")// 引用这个生成器
    @Column(name = "UMSShareid", unique = true, nullable = false)
    private long UMSShareid;
    private long docid;
    @Column(length=500)
    private String doctitle;
    @Column(length=50,nullable=false)
    private String sharetime;
    @Column(length=100)
    private String swhere;
    @Column(length=500)
    private String surl;  
    @Column(length=1000)
    private String remark;
    @ManyToOne(fetch = FetchType.LAZY)//懒加载
    @JoinColumn(name = "userid",nullable=false)
    private UMSUser user;
   
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
     * 获取 uMSShareid
     * @return 返回 uMSShareid
     */
    public long getUMSShareid()
    {
        return UMSShareid;
    }
    /**
     * 设置 uMSShareid
     * @param 对uMSShareid进行赋值
     */
    public void setUMSShareid(long uMSShareid)
    {
        UMSShareid = uMSShareid;
    }
    /**
     * 获取 docid
     * @return 返回 docid
     */
    public long getDocid()
    {
        return docid;
    }
    /**
     * 设置 docid
     * @param 对docid进行赋值
     */
    public void setDocid(long docid)
    {
        this.docid = docid;
    }
    /**
     * 获取 doctitle
     * @return 返回 doctitle
     */
    public String getDoctitle()
    {
        return doctitle;
    }
    /**
     * 设置 doctitle
     * @param 对doctitle进行赋值
     */
    public void setDoctitle(String doctitle)
    {
        this.doctitle = doctitle;
    }
    
    /**
     * 获取 surl
     * @return 返回 surl
     */
    public String getSurl()
    {
        return surl;
    }
    /**
     * 设置 surl
     * @param 对surl进行赋值
     */
    public void setSurl(String surl)
    {
        this.surl = surl;
    }
    /**
     * 获取 sharetime
     * @return 返回 sharetime
     */
    public String getSharetime()
    {
        return sharetime;
    }
    /**
     * 设置 sharetime
     * @param 对sharetime进行赋值
     */
    public void setSharetime(String sharetime)
    {
        this.sharetime = sharetime;
    }
    /**
     * 获取 swhere
     * @return 返回 swhere
     */
    public String getSwhere()
    {
        return swhere;
    }
    /**
     * 设置 swhere
     * @param 对swhere进行赋值
     */
    public void setSwhere(String swhere)
    {
        this.swhere = swhere;
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
