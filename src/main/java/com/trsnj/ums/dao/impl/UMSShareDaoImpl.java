/*
 * 文 件 名:  UMSShareDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-29
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IShareDao;
import com.trsnj.ums.pojo.UMSMessage;
import com.trsnj.ums.pojo.UMSShare;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-29]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSShareDaoImpl extends BaseDao<UMSShare> implements IShareDao
{
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSShare> getEntityClass()
    {
        return UMSShare.class;
    }
    /**
     * 分页获取当前用户的分享文章
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSShare> getSharesByUserId(long userId,int firstResult,int maxResult){
        
        String sql="select * from umsshare where userid ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<UMSShare> shares=query.list();
        return shares;
    }
    /**
     * 获取当前用户分享文档的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getShareCountByUserId(long userid){
        String sql="select count(*) from UMSShare where userid ="+userid;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        long r= (Integer)query.uniqueResult();
        return r;
    }
    /**
     * 批量删除
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delShareByIds(long userId,String ids){
        String sql = "delete from umsshare where userid ="+userId+" and UMSShareid in("+ids+")";
        Session session=this.getCurrentSession();
     //   Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
      //  ts.commit();
        return "删除成功";
    }
    /**
     * 按降序获得分享表中分享文章最多的文章，默认5篇
     * @param first
     * @param max
     * @return
     * @see com.trsnj.ums.dao.IShareDao#hotShareDoc(int, int)
     */
    public List<Map> hotShareDoc(long userid,int first, int max){
        String sql="select docid ,count(docid) as sharecount from umsshare where docid not in (select docid from umsshare where userid ="+userid+") group by(docid) order by count(docid) desc";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql)
            .addScalar("docid", Hibernate.STRING)
            .addScalar("sharecount", Hibernate.STRING);
        query.setMaxResults(max);
        query.setFirstResult(first);
        List list=query.list();
        List<Map> docids=new ArrayList<Map>();
        if (list!=null&&list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map map=new HashMap();
                Object[] ob = (Object[]) list.get(i);
                map.put("docid", (String)ob[0]);
                map.put("sharecount", (String)ob[1]);
               docids.add(map);
            }
        }
        return docids;
    }
}
