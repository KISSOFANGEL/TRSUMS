/*
 * 文 件 名:  UMSSubscriptionDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUMSSubscriptionDao;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSComment;
import com.trsnj.ums.pojo.UMSSubscription;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSSubscriptionDaoImpl extends BaseDao<UMSSubscription> implements IUMSSubscriptionDao
{
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSSubscription> getEntityClass()
    {
        return UMSSubscription.class;
    }
    /**
     * 获取当前用户的订阅信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSSubscription> getSubscriptionByUserId(long userId){
        
        String sql="select * from UMSSubscription where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSSubscription> subscripts=query.list();
        return subscripts;
    }
    /**
     * 分页获取当前用户的订阅信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSSubscription> getSubscriptionByUserId(long userId,int firstResult,int maxResult){
        
        String sql="select * from UMSSubscription where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<UMSSubscription> subscripts=query.list();
        return subscripts;
    }
    /**
     * 获取当前用户订阅总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getSubscriptionCountByUserId(long userId){
        String sql="select count(*) from UMSSubscription where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        long r= (Integer)query.uniqueResult();
        return r;
    }
    /**
     * 批量删除订阅的信息
     * @param userId
     * @param ids
     * @return
     * @see com.trsnj.ums.dao.IUMSSubscriptionDao#delSubscriptionByIds(long, java.lang.String)
     */
    public String delSubscriptionByIds(long userId, String ids){
        String sql = "delete from umssubscription where userid ="+userId+" and UMSSubscriptionid in("+ids+")";
        Session session=this.getCurrentSession();
       // Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
       // ts.commit();
        return "删除成功";
    }
    /**
     * 
     * @param userId
     * @param chnlid
     * @return
     * @see com.trsnj.ums.dao.IUMSSubscriptionDao#getSubscriptionByChnlid(java.lang.String, java.lang.String)
     */
    public UMSSubscription getSubscriptionByChnlid(String userId, String chnlid){
        String sql="select * from UMSSubscription where userId ="+userId+" and subchnl ="+chnlid;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSSubscription> subs=query.list();
        if(subs!=null&&subs.size()>0){
            return  subs.get(0);
        }
        return null;
    }
}
