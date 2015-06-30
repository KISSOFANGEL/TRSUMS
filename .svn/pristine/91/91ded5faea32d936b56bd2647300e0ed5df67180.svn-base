/*
 * 文 件 名:  UserGroupDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUserGroupDao;
import com.trsnj.ums.pojo.UMSUserGroup;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserGroupDaoImpl extends BaseDao<UMSUserGroup> implements IUserGroupDao
{

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSUserGroup> getEntityClass()
    {
        return UMSUserGroup.class;
    }
    /**
     * 根据id来删除
     * @see [类、类#方法、类#成员]
     */
    public void deleteById(long id){
         Session session=getCurrentSession();
         Query query=null;
         String sql="delete from umsusergroup where usergroupid="+id;
        query= session.createSQLQuery(sql);
        query.executeUpdate();
    }
   
    
}
