package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IRoleDao;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.util.HibernateFactory;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class RoleDaoImpl extends BaseDao<UMSRole> implements IRoleDao
{

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSRole> getEntityClass()
    {
        return UMSRole.class;
    }
    /**
     * 根据角色名来查找
     * @param roleName  角色名是唯一的，不可以重复
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSRole getRoleByRoleName(String roleName){
        String hql=" from UMSRole r where r.roleName= "+"'"+roleName+"'";
        // 这里的session一定要用父类的getCurrentSession的方法，不要直接用HibernateFactroy这个类
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSRole> list=query.list();
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 根据角色名来查找
     * @param roleName  角色名是唯一的，不可以重复
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSRole getRoleByRoleName(String roleName,long roleId){
        String hql=" from UMSRole r where r.roleName= "+"'"+roleName+"'"+" and r.roleId != "+roleId;
        // 这里的session一定要用父类的getCurrentSession的方法，不要直接用HibernateFactroy这个类
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSRole> list=query.list();
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 根据角色id获取用户数
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getUsersCountsByRoleId(long roleId){
        String sql="select count(*) from umsuser as u where u.role_id="+roleId+" and u.delnote !=0 and u.type !=0";
        Query query=null;
        Session session=this.getCurrentSession();
        query=session.createSQLQuery(sql);
        return (Integer)query.uniqueResult();
    }
    /**
     * 获取不在当前角色下的用户总数
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long  getUsersConuntsNotInNowRoleId(long roleId){
        String sql="select count(*) from umsuser as u where u.role_id !="+roleId +" and u.delnote !=0 and u.type !=0";
        Query query=null;
        Session session=this.getCurrentSession();
        query=session.createSQLQuery(sql);
        return (Integer)query.uniqueResult();
    }
    /**
     * 修改当前用户为当前角色
     * @param userId
     * @param roleId
     * @see [类、类#方法、类#成员]
     */
    public void updateUserToRoleId(long userId,long roleId){
        Session session=this.getCurrentSession();
       // Transaction ts=session.beginTransaction();//事物不提交不能执行，在spring中要去掉
        String sql="update umsuser set role_id="+roleId+" where userid="+userId;
        SQLQuery query=null;
            query=session.createSQLQuery(sql);
            query.executeUpdate();
           // ts.commit();
            
    }
    
}
