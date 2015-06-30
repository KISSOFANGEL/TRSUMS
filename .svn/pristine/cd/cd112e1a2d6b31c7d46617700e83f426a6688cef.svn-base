/*
 * 文 件 名:  CompanyInfoDaoImpl.java
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
import com.trsnj.ums.dao.IUMSCompanyInfoDao;
import com.trsnj.ums.pojo.UMSCompanyInfo;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CompanyInfoDaoImpl extends BaseDao<UMSCompanyInfo> implements IUMSCompanyInfoDao
{
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSCompanyInfo> getEntityClass()
    {
        return UMSCompanyInfo.class;
    }
    /**
     * 根据用户id获取用户的公司信息
     * @return  * 以后拓展的时候方便
     * @see [类、类#方法、类#成员]
     */
    public UMSCompanyInfo getComInfoByUserId(long userId){
        String sql="select * from umscompanyinfo where userId ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSCompanyInfo> companys=query.list();
        if(companys!=null&&companys.size()>0)
        {
            return companys.get(0);
        }
        return null;
    }
    /**
     * 修改当前用户的公司信息
     * @param companyInfo
     * @param userId
     * @param sql
     * @return
     * @see com.trsnj.ums.dao.IUMSCompanyInfoDao#updateCurrentCompanyInfo(com.trsnj.ums.pojo.UMSCompanyInfo, java.lang.String, java.lang.String)
     */
    public UMSCompanyInfo updateCurrentCompanyInfo(UMSCompanyInfo companyInfo, String userId, String sql){
        Session session=getCurrentSession();
        //Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.setString(0, companyInfo.getCompanyName()).setString(1, companyInfo.getCompanyNature()).setString(2, companyInfo.getCompanyAddress())
        .setString(3, companyInfo.getDeptName()).setString(4, companyInfo.getCompanytel()).setLong(5, Long.parseLong(userId));
        query.executeUpdate();
       // ts.commit();
        return companyInfo;
    }
   
    
    
    
}
