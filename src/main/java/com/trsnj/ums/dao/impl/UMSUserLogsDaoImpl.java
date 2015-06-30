/*
 * 文 件 名:  UMSUserLogsDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUMSUserLogsDao;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSUserLogs;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSUserLogsDaoImpl extends BaseDao<UMSUserLogs> implements IUMSUserLogsDao
{

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSUserLogs> getEntityClass()
    {
        return UMSUserLogs.class;
    }
    
    /**
     * 分页获取用户最后登陆的日志
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public  List<UMSUserLogs> getUserLogsByUserId(long userId,int firstResult,int maxResult){
        String sql="select * from umsuserlogs where userid="+userId+" order by logintime DESC";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSUserLogs> userlogs=query.list();
        return userlogs;
    }
    
}
