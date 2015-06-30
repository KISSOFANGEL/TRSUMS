/*
 * 文 件 名:  UMSCompanyInfo.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name = "UMSCompanyInfo")
public class UMSCompanyInfo implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7032172018468851615L;
    @GenericGenerator(name = "companygenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "companygenerator")// 引用这个生成器
    private long companyInfoId;
    
    private String companyName;
    private String companyAddress;
    @Column(length=200)
    private String deptName;
    @Column(length=50)
    private String companyNature;
    @Column(length=50)
    private String companytel;
    @OneToOne(fetch = FetchType.LAZY)//不懒加载
    @JoinColumn(name = "userid")
    private UMSUser user;
    /**
     * 获取 companyInfoId
     * @return 返回 companyInfoId
     */
    public long getCompanyInfoId()
    {
        return companyInfoId;
    }
    /**
     * 设置 companyInfoId
     * @param 对companyInfoId进行赋值
     */
    public void setCompanyInfoId(long companyInfoId)
    {
        this.companyInfoId = companyInfoId;
    }
    
    /**
     * 获取 companytel
     * @return 返回 companytel
     */
    public String getCompanytel()
    {
        return companytel;
    }
    /**
     * 设置 companytel
     * @param 对companytel进行赋值
     */
    public void setCompanytel(String companytel)
    {
        this.companytel = companytel;
    }
    /**
     * 获取 companyName
     * @return 返回 companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }
    /**
     * 设置 companyName
     * @param 对companyName进行赋值
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    /**
     * 获取 companyAddress
     * @return 返回 companyAddress
     */
    public String getCompanyAddress()
    {
        return companyAddress;
    }
    /**
     * 设置 companyAddress
     * @param 对companyAddress进行赋值
     */
    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }
    /**
     * 获取 deptName
     * @return 返回 deptName
     */
    public String getDeptName()
    {
        return deptName;
    }
    /**
     * 设置 deptName
     * @param 对deptName进行赋值
     */
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
    /**
     * 获取 companyNature
     * @return 返回 companyNature
     */
    public String getCompanyNature()
    {
        return companyNature;
    }
    /**
     * 设置 companyNature
     * @param 对companyNature进行赋值
     */
    public void setCompanyNature(String companyNature)
    {
        this.companyNature = companyNature;
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
