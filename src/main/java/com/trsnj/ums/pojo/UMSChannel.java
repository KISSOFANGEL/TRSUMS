/*
 * 文 件 名:  UMSChannel.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-1-14
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
 *  角色对应的了栏目权限表
 * @author  dzy
 * @version  [V1.00, 2015-1-14]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name="UMSChannel")
public class UMSChannel implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6093544434209563716L;
    @GenericGenerator(name="chnlgenerator",strategy="native")
    @Id
    @GeneratedValue(generator="chnlgenerator")
    @Column(name="UMSChannelid",unique=true,nullable=false)
    private long UMSChannelid;
    private int chnlid;
    private int parentid;
    @Column(length=50)
    private String chnldesc;
    private String path;
    private int roleid;
    /**
     * 获取 uMSChannelid
     * @return 返回 uMSChannelid
     */
    public long getUMSChannelid()
    {
        return UMSChannelid;
    }
    /**
     * 设置 uMSChannelid
     * @param 对uMSChannelid进行赋值
     */
    public void setUMSChannelid(long uMSChannelid)
    {
        UMSChannelid = uMSChannelid;
    }
    /**
     * 获取 chnlid
     * @return 返回 chnlid
     */
    public int getChnlid()
    {
        return chnlid;
    }
    /**
     * 设置 chnlid
     * @param 对chnlid进行赋值
     */
    public void setChnlid(int chnlid)
    {
        this.chnlid = chnlid;
    }
    /**
     * 获取 parentid
     * @return 返回 parentid
     */
    public int getParentid()
    {
        return parentid;
    }
    /**
     * 设置 parentid
     * @param 对parentid进行赋值
     */
    public void setParentid(int parentid)
    {
        this.parentid = parentid;
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
     * 获取 path
     * @return 返回 path
     */
    public String getPath()
    {
        return path;
    }
    /**
     * 设置 path
     * @param 对path进行赋值
     */
    public void setPath(String path)
    {
        this.path = path;
    }
    /**
     * 获取 roleid
     * @return 返回 roleid
     */
    public int getRoleid()
    {
        return roleid;
    }
    /**
     * 设置 roleid
     * @param 对roleid进行赋值
     */
    public void setRoleid(int roleid)
    {
        this.roleid = roleid;
    }
    
    
    
    
}
