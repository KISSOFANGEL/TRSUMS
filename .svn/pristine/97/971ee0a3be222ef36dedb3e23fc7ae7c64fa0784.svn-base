/*
 * 文 件 名:  UMSSystemMessageDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-3-12
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.dao.IUMSSystemMessageDao;
import com.trsnj.ums.pojo.UMSSystemMessage;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-3-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSSystemMessageDaoImpl extends BaseDao<UMSSystemMessage> implements IUMSSystemMessageDao
{
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseDao#getEntityClass()
     */
    public Class<UMSSystemMessage> getEntityClass()
    {
        return UMSSystemMessage.class;
    }
    /**
     * 分页获取已发送的系统信息
     * @param firstResult
     * @param maxResult
     * @return
     * @see com.trsnj.ums.dao.IUMSSystemMessageDao#getSystemMessages(int, int)
     */
    public List<UMSSystemMessage> getSystemMessages(int firstResult, int maxResult){
        String hql="from UMSSystemMessage";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        return query.list();
    }
    /**
     * 获取已发送系统信息的总数
     * @return
     * @see com.trsnj.ums.dao.IUMSSystemMessageDao#getSystemMessageCount()
     */
    public long getSystemMessageCount(){
        Session session=getCurrentSession();
        Query query=null;
        query=session.createQuery(" select count(*) from UMSSystemMessage ");
        Number num = (Number)query.uniqueResult(); // 返回单个实例 
        if(num==null){
            return 0;
        }
        long count = num.longValue();
        return count;
    }
    /**
     * 批量删除
     * @param id
     * @see com.trsnj.ums.dao.IUMSSystemMessageDao#batchdel(java.lang.String)
     */
    public void batchdel(String id){
        Session session=getCurrentSession();
        Query query=null;
        String sql="delete from UMSSystemMessage where UMSSystemMessageid in "+"("+id+")";
        query=session.createSQLQuery(sql);
        query.executeUpdate();
    }
}
