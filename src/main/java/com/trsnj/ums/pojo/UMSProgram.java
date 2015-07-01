/*
 * 文 件 名:  UMSCollect.java
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
@Table(name="UMSProgram")
public class UMSProgram implements Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6951765963856006895L;
    @GenericGenerator(name="colgenerator",strategy="native")
    @Id
    @GeneratedValue(generator="colgenerator")
    @Column(name="UMSProgramid",unique=true,nullable=false)
    private long UMSProgramid;
    @Column(nullable=false)
    private long docid;
    @Column(nullable=false,length=1000)
    private String doctitle;
    @Column(nullable=true,length=1000)
    private String remark;
    @Column(length=50,nullable=false)
    private String remarktime;
    @Column(nullable=true)
    private Integer programstatus;
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
	public long getUMSProgramid() {
		return UMSProgramid;
	}
	public void setUMSProgramid(long uMSProgramid) {
		UMSProgramid = uMSProgramid;
	}
	public String getRemarktime() {
		return remarktime;
	}
	public void setRemarktime(String remarktime) {
		this.remarktime = remarktime;
	}
	public Integer getProgramstatus() {
		return programstatus;
	}
	public void setProgramstatus(Integer programstatus) {
		this.programstatus = programstatus;
	}
    
    
    
}
