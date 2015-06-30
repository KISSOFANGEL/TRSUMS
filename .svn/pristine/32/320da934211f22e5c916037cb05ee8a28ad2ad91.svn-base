/*
 * 文 件 名:  UMSCommentDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUMSCommentDao;
import com.trsnj.ums.pojo.UMSComment;
import com.trsnj.ums.pojo.UMSMessage;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSCommentDaoImpl extends BaseDao<UMSComment> implements IUMSCommentDao
{
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSComment> getEntityClass()
    {
        return UMSComment.class;
    }
    /**
     * 获取当前用户所有文章的品论
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSComment> getCommentByUserId(long userId){
        String sql="select * from UMSComment where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSComment> comments=query.list();
        return comments;
    }
    /**
     * 分页获取当前用户所有文章的品论
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSComment> getCommentByUserId(long userId,int firstResult,int maxResult){
        String sql="select * from UMSComment where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<UMSComment> comments=query.list();
        return comments;
    }
    /**
     * 获取当前用户评论的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getCommentCountByUserId(long userId){
        String sql="select count(*) from UMSComment where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        long r= (Integer)query.uniqueResult();
        return r;
    }
    /**
     * 获取当前文档的所有评论
     * @param docid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSComment> getCommentByDocid(long docid){
        String sql="select * from UMSComment where docid ="+docid;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSComment> comments=query.list();
        return comments;
    }
    /**
     * 分页获取当前文档的所有评论
     * @param docid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSComment> getCommentByDocid(long docid,int firstResult,int maxResult){
        String sql="select * from UMSComment where docid ="+docid+" order by comtime desc";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<UMSComment> comments=query.list();
        return comments;
    }
    /**
     * 获取当前文档评论的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getCommentCountByDocid(long docid){
        String sql="select count(*) from UMSComment where docid ="+docid;
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
    public String delCommentByIds(long userId,String ids){
        String sql = "delete from umscomment where userid ="+userId+" and UMSCommentid in("+ids+")";
        Session session=this.getCurrentSession();
       // Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
       // ts.commit();
        return "删除成功";
    }
}
