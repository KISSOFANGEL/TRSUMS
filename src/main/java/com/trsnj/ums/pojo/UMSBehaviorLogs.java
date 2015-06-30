/*
 * 文 件 名:  UMSBehaviorLogs.java
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
 *  用户操作日志表
 * @author  dzy
 * @version  [V1.00, 2014-12-18]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name="UMSBehaviorLogs")
public class UMSBehaviorLogs implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1672580006589403018L;
    @GenericGenerator(name = "behaviorgenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "behaviorgenerator")// 引用这个生成器
    @Column(name = "UMSBehaviorLogsid", unique = true, nullable = false)
    private long UMSBehaviorLogsid;
    @Column(length=50)
    private String operatetime;
    @Column(length=100)
    private String operatetable;
    @Column(length=400)
    private String action;
    private long objid;  //操作表中的记录id
    @Column(length=500)
    private String objname;  //文章标题或栏目名称
    @Column(length=1000)
    private String remark;
    @ManyToOne(fetch = FetchType.LAZY)//不懒加载
    @JoinColumn(name = "userid")
    private UMSUser user;
    private int delnote=0;//在最新动态里表示假删除标记,默认是0,删除是1
    
    /**
     * 获取 delnote
     * @return 返回 delnote
     */
    public int getDelnote()
    {
        return delnote;
    }
    /**
     * 设置 delnote
     * @param 对delnote进行赋值
     */
    public void setDelnote(int delnote)
    {
        this.delnote = delnote;
    }
    /**
     * 获取 uMSBehaviorLogsid
     * @return 返回 uMSBehaviorLogsid
     */
    public long getUMSBehaviorLogsid()
    {
        return UMSBehaviorLogsid;
    }
    /**
     * 设置 uMSBehaviorLogsid
     * @param 对uMSBehaviorLogsid进行赋值
     */
    public void setUMSBehaviorLogsid(long uMSBehaviorLogsid)
    {
        UMSBehaviorLogsid = uMSBehaviorLogsid;
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
     * 获取 operatetime
     * @return 返回 operatetime
     */
    public String getOperatetime()
    {
        return operatetime;
    }
    /**
     * 设置 operatetime
     * @param 对operatetime进行赋值
     */
    public void setOperatetime(String operatetime)
    {
        this.operatetime = operatetime;
    }
    /**
     * 获取 operatetable
     * @return 返回 operatetable
     */
    public String getOperatetable()
    {
        return operatetable;
    }
    /**
     * 设置 operatetable
     * @param 对operatetable进行赋值
     */
    public void setOperatetable(String operatetable)
    {
        this.operatetable = operatetable;
    }
    /**
     * 获取 action
     * @return 返回 action
     */
    public String getAction()
    {
        return action;
    }
    /**
     * 设置 action
     * @param 对action进行赋值
     */
    public void setAction(String action)
    {
        this.action = action;
    }
    /**
     * 获取 objid
     * @return 返回 objid
     */
    public long getObjid()
    {
        return objid;
    }
    /**
     * 设置 objid
     * @param 对objid进行赋值
     */
    public void setObjid(long objid)
    {
        this.objid = objid;
    }
    /**
     * 获取 objname
     * @return 返回 objname
     */
    public String getObjname()
    {
        return objname;
    }
    /**
     * 设置 objname
     * @param 对objname进行赋值
     */
    public void setObjname(String objname)
    {
        this.objname = objname;
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
