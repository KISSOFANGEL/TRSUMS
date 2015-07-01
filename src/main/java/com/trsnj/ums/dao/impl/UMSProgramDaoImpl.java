/*
 * 文 件 名:  UMSCollectDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUMSCollectDao;
import com.trsnj.ums.dao.IUMSProgramDao;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSProgram;
import com.trsnj.ums.pojo.UMSSubscription;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSProgramDaoImpl extends BaseDao<UMSProgram> implements IUMSProgramDao
{

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSProgram> getEntityClass()
    {
        return UMSProgram.class;
    }
    /**
     * 获取当前用户的搜藏信息
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public  List<UMSProgram> getProgramByUserId(long userId){
        String sql="select * from umsprogram where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSProgram> collects=query.list();
        return collects;
    }
    /**
     * 分页获取当前用户的搜藏
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public  List<UMSProgram> getProgramByUserId(long userId,int firstResult,int maxResult){
        String sql="select * from umsprogram where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSProgram> collects=query.list();
        return collects;
    }
    /**
     * 获取当前用户搜藏的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getProgramCountByUserId(long userId){
        String sql="select count(*) from umsprogram where userId ="+userId;
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
    public String delProgramByIds(long userId,String ids){
        String sql = "delete from umsprogram where userid ="+userId+" and UMSCollectid in("+ids+")";
        Session session=this.getCurrentSession();
       // Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
        //ts.commit();
        return "删除成功";
    }
    /**
     * 获得热门推荐，默认是5片文章
     * @param userid
     * @param first
     * @param max
     * @return
     * @see com.trsnj.ums.dao.IUMSCollectDao#hotShareDoc(long, int, int)
     */
    public List<String> hotRecommendDoc(long userid, int first, int max){
        String sql="select docid from umsprogram where docid not in (select docid from umscollect where userid ="+userid+")  group by(docid) order by count(docid) desc";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql)
            .addScalar("docid", Hibernate.STRING);
        query.setMaxResults(first);
        query.setFirstResult(max);
        List list=query.list();
        List<String> docids=new ArrayList<String>();
        if (list!=null&&list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
               docids.add((String)ob[0]);
            }
        }
        return docids;
    }
    /**
     * 根据用户id和文档id来判断是否收藏
     * @param docid
     * @param userId
     * @return
     * @see com.trsnj.ums.dao.IUMSCollectDao#getCollectByUserIdAndDocid(java.lang.String, java.lang.String)
     */
    public UMSProgram getProgramByUserIdAndDocid(String docid, String userId){
        String sql="select * from umsprogram where userId ="+userId+" and docid ="+docid;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSProgram> collects=query.list();
        if(collects!=null&&collects.size()>0){
            return  collects.get(0);
        }
        return null;
    }
}
