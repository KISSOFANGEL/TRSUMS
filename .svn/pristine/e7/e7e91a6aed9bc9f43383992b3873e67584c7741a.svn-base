/*
 * 文 件 名:  UMSComment.java
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
@Table(name = "UMSComment")
public class UMSComment implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8422124813462476321L;
    @GenericGenerator(name = "comgenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "comgenerator")// 引用这个生成器
    private long UMSCommentid;
    @Column(nullable=false)
    private long docid;
    @Column(length=2000,nullable=false)
    private String content;// 品论的内容
    @Column(length=1000,nullable=false)
    private String doctitile;
    @Column(length=1000)
    private String remark;
    @Column(length=50)
    private String comtime;
    @Column(nullable=true)
    private int comstatus;
    @ManyToOne(fetch = FetchType.EAGER)//不懒加载    在加载根据文档来查评论的时候，查询用户的时候改成不懒
    @JoinColumn(name = "userid",nullable=false)
    private UMSUser user;//多对一单项关联
  
    /**
     * 获取 uMSCommentid
     * @return 返回 uMSCommentid
     */
    public long getUMSCommentid()
    {
        return UMSCommentid;
    }
    /**
     * 设置 uMSCommentid
     * @param 对uMSCommentid进行赋值
     */
    public void setUMSCommentid(long uMSCommentid)
    {
        UMSCommentid = uMSCommentid;
    }
    /**
     * 获取 doctitile
     * @return 返回 doctitile
     */
    public String getDoctitile()
    {
        return doctitile;
    }
    /**
     * 设置 doctitile
     * @param 对doctitile进行赋值
     */
    public void setDoctitile(String doctitile)
    {
        this.doctitile = doctitile;
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
     * 获取 content
     * @return 返回 content
     */
    public String getContent()
    {
        return content;
    }
    /**
     * 设置 content
     * @param 对content进行赋值
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    /**
     * 获取 comtime
     * @return 返回 comtime
     */
    public String getComtime()
    {
        return comtime;
    }
    /**
     * 设置 comtime
     * @param 对comtime进行赋值
     */
    public void setComtime(String comtime)
    {
        this.comtime = comtime;
    }
    /**
     * 获取 comstatus
     * @return 返回 comstatus
     */
    public int getComstatus()
    {
        return comstatus;
    }
    /**
     * 设置 comstatus
     * @param 对comstatus进行赋值
     */
    public void setComstatus(int comstatus)
    {
        this.comstatus = comstatus;
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
