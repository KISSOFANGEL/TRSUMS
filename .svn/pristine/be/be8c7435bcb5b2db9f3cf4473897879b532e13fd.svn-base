/*
 * 文 件 名:  UMSChannelDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-1-16
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUMSChannelDao;
import com.trsnj.ums.pojo.UMSChannel;
import com.trsnj.ums.pojo.UMSCollect;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-1-16]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSChannelDaoImpl extends BaseDao<UMSChannel> implements IUMSChannelDao
{

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSChannel> getEntityClass()
    {
        return UMSChannel.class;
    }
    /**
     * 根据角色id获取栏目的权限
     * @param roleId
     * @return
     * @see com.trsnj.ums.dao.IUMSChannelDao#getChannelsByRoleId(java.lang.String)
     */
    public List<UMSChannel> getChannelsByRoleId(String roleId){
        
        String sql="select * from UMSChannel where roleid="+roleId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSChannel> channels=query.list();
        return channels;
    }
    
}
