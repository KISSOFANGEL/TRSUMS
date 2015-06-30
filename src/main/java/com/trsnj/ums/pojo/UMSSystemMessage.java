/*
 * 文 件 名:  UMSSystemMessage.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-3-12
 */
package com.trsnj.ums.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <一句话功能简述>
 *  管理员向用户发送信息后，记录表
 * @author  dzy
 * @version  [V1.00, 2015-3-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name = "UMSSystemMessage")
public class UMSSystemMessage implements Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 628941613233344357L;
    @GenericGenerator(name = "smgenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "smgenerator")// 引用这个生成器
    @Column(name = "UMSSystemMessageid", unique = true, nullable = false)
    private long UMSSystemMessageid;
    private long author;//系统信息的发送者
    private String authorname;//系统信息发送者的名称（加个赘余字段）
    private String title;//发送系统信息的标题
    @Column(length=1000)
    private String mescontent;//发送系统信息的内容
    private String cretime;//发送系统信息的时间
    private String roleid;//接受系统信息的角色id。(可能有多个，所以用id字符串，中间用‘,’号隔开)
    private int type;//发送系统信息的类型（以后扩展，文本消息为默认0，带有附件，以后根据情况拓展附件表）
    /**
     * 获取 uMSSystemMessageid
     * @return 返回 uMSSystemMessageid
     */
    public long getUMSSystemMessageid()
    {
        return UMSSystemMessageid;
    }
    /**
     * 设置 uMSSystemMessageid
     * @param 对uMSSystemMessageid进行赋值
     */
    public void setUMSSystemMessageid(long uMSSystemMessageid)
    {
        UMSSystemMessageid = uMSSystemMessageid;
    }
    /**
     * 获取 author
     * @return 返回 author
     */
    public long getAuthor()
    {
        return author;
    }
    /**
     * 设置 author
     * @param 对author进行赋值
     */
    public void setAuthor(long author)
    {
        this.author = author;
    }
    /**
     * 获取 authorname
     * @return 返回 authorname
     */
    public String getAuthorname()
    {
        return authorname;
    }
    /**
     * 设置 authorname
     * @param 对authorname进行赋值
     */
    public void setAuthorname(String authorname)
    {
        this.authorname = authorname;
    }
    /**
     * 获取 title
     * @return 返回 title
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * 设置 title
     * @param 对title进行赋值
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * 获取 mescontent
     * @return 返回 mescontent
     */
    public String getMescontent()
    {
        return mescontent;
    }
    /**
     * 设置 mescontent
     * @param 对mescontent进行赋值
     */
    public void setMescontent(String mescontent)
    {
        this.mescontent = mescontent;
    }
    /**
     * 获取 cretime
     * @return 返回 cretime
     */
    public String getCretime()
    {
        return cretime;
    }
    /**
     * 设置 cretime
     * @param 对cretime进行赋值
     */
    public void setCretime(String cretime)
    {
        this.cretime = cretime;
    }
    /**
     * 获取 roleid
     * @return 返回 roleid
     */
    public String getRoleid()
    {
        return roleid;
    }
    /**
     * 设置 roleid
     * @param 对roleid进行赋值
     */
    public void setRoleid(String roleid)
    {
        this.roleid = roleid;
    }
    /**
     * 获取 type
     * @return 返回 type
     */
    public int getType()
    {
        return type;
    }
    /**
     * 设置 type
     * @param 对type进行赋值
     */
    public void setType(int type)
    {
        this.type = type;
    }
    
}
