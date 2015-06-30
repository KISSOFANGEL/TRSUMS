/*
 * 文 件 名:  UMSBehaviorLogsDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUMSBehaviorLogsDao;
import com.trsnj.ums.pojo.UMSBehaviorLogs;
import com.trsnj.ums.pojo.UMSCollect;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSBehaviorLogsDaoImpl extends BaseDao<UMSBehaviorLogs> implements IUMSBehaviorLogsDao
{
    @Override
    public Class getEntityClass()
    {
        return UMSBehaviorLogs.class;
    }
   /**
    * 分页获取当前用户的最新动态
    * @param currpage
    * @param perpage
    * @param userId
    * @return(delnote=0表示未删除,delnote=1表示我的动态前台数据删除了)
    * @see com.trsnj.ums.dao.IUMSBehaviorLogsDao#getMyDynamic(int, int, long)
    */
   public List<UMSBehaviorLogs> getMyDynamic(int currpage, int perpage, long userId){
       String sql="select * from UMSBehaviorLogs where userId ="+userId+" and delnote=0 order by operatetime DESC";
       Session session=getCurrentSession();
       Query query=null;
       query=session.createSQLQuery(sql).addEntity(getEntityClass());
       query.setMaxResults(perpage);
       query.setFirstResult((currpage-1)*perpage);
       List<UMSBehaviorLogs> behaviors=query.list();
       return behaviors;
    }
   /**
    * 获取当前用户动态的总数
    * @param userId
    * @return
    * @see com.trsnj.ums.dao.IUMSBehaviorLogsDao#getMyDynamicCount(long)
    */
    public long getMyDynamicCount(long userId){
        String sql="select count(*) from UMSBehaviorLogs where userId ="+userId+" and delnote=0 ";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        long r= (Integer)query.uniqueResult();
        return r;
    }
    /**
     * 批量删除用户的动态
     * @param userId
     * @param ids
     * @see com.trsnj.ums.dao.IUMSBehaviorLogsDao#delMyDynamic(java.lang.String, java.lang.String)
     */
    public void delMyDynamic(String userId, String ids){
        String sql=" update umsbehaviorlogs set delnote=1 where userId="+userId+" and umsbehaviorlogsid in ("+ids+")";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
    }
}
