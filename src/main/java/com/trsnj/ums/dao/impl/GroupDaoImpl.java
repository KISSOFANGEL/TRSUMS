/*
 * 文 件 名:  GroupDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IGroupDao;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class GroupDaoImpl extends BaseDao<UMSGroup> implements IGroupDao
{
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseDao#getEntityClass()
     */
    @Override
    public Class<UMSGroup> getEntityClass()
    {
        return UMSGroup.class;
    }
    /**
     * 根据组织名来查找
     * @param groupName  组织名是唯一的，不可以重复
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSGroup getGroupByGroupName(String groupName){
        String hql=" from UMSGroup g where g.groupName= "+"'"+groupName+"'";
        // 这里的session一定要用父类的getCurrentSession的方法，不要直接用HibernateFactroy这个类
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSGroup> list=query.list();
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 根据组织名来查找
     * @param groupName  组织名是唯一的，不可以重复
     * @param groupId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSGroup getGroupByGroupName(String groupName,long groupId){
        String hql=" from UMSGroup g where g.groupName= "+"'"+groupName+"'"+" and g.groupId != "+groupId;
        // 这里的session一定要用父类的getCurrentSession的方法，不要直接用HibernateFactroy这个类
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSGroup> list=query.list();
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 更具组织父id查询子组织
     * @param parentId
     * @return
     * @see com.trsnj.ums.dao.IGroupDao#getGroupsByParentId(long)
     */
    public List<UMSGroup> getGroupsByParentId(long parentId){
        String hql="from UMSGroup g where g.parentId= "+parentId;
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSGroup> list=query.list();
        return list;
    }
    /**
     * 根据用户id来查询组织信息
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSGroup> getGroupsByUserId(long userId){
        String sql="select gu.user_id userId,gu.group_id groupId,g.groupName groupName,g.parentId parentId from umsusergroup gu ,umsgroup g where gu.group_id=g.groupId and gu.user_id = "+userId;
        Session session =this.getCurrentSession();
        Query sqlquery=null;
        sqlquery= session.createSQLQuery(sql)
            .addScalar("userId", Hibernate.STRING)
            .addScalar("groupId", Hibernate.STRING)
            .addScalar("groupName", Hibernate.STRING)
            .addScalar("parentId", Hibernate.STRING);
        List list = sqlquery.list();
        //封装成UMSGroup对象
        List<UMSGroup> listGroup=new ArrayList<UMSGroup>();
        if (list!=null&&list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                UMSGroup group=new UMSGroup();
                group.setGroupName((String)ob[2]);
                group.setGroupId(Long.parseLong((String)ob[1]));
                group.setParentId(Long.parseLong((String)ob[3]));
                listGroup.add(group);
            }
        }
        return listGroup;
    }
     /**
      * 分页获取当前组织下的用户
      * @param groupId
      * @param page
      * @param rows
      * @return
      * @see [类、类#方法、类#成员]
      */
    public List<UMSUser> getUsersByGroupId(long groupId,int page,int rows){
        String sql=" select u.userid userid,u.address address,u.email email,u.mobile mobile,u.password passWord,u.qq qq,u.status status,u.tel tel,u.userName userName,u.userType userType,u.role_id role_id,u.cruser cruser,u.crutime crutime from umsuser u left join umsusergroup gu on u.userid=gu.user_id where gu.group_id="+groupId+" and u.delnote !=0 and u.type !=0";
        Session session=this.getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql)
            .addScalar("userid", Hibernate.STRING)
            .addScalar("address", Hibernate.STRING)
            .addScalar("email", Hibernate.STRING)
            .addScalar("mobile", Hibernate.STRING)
            .addScalar("password", Hibernate.STRING)
            .addScalar("qq", Hibernate.STRING)
            .addScalar("status", Hibernate.STRING)
            .addScalar("tel", Hibernate.STRING)
            .addScalar("username", Hibernate.STRING)
            .addScalar("usertype", Hibernate.STRING)
            .addScalar("role_id", Hibernate.STRING)
            .addScalar("cruser", Hibernate.STRING)
            .addScalar("crutime", Hibernate.STRING);
        List list=query.setMaxResults(rows).setFirstResult((page-1)*rows).list();
        //封装成UMSUser对象
        List<UMSUser> listUser=new ArrayList<UMSUser>();
        if (list!=null&&list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                UMSUser user=new UMSUser();
                 user.setAddress((String)ob[1]);
                 user.setCruser((String)ob[11]);
                 user.setCrutime((String)ob[12]);
                 user.setEmail((String)ob[2]);
                 user.setMobile((String)ob[3]);
                 user.setPassWord((String)ob[4]);
                 user.setQq((String)ob[5]);
                 user.setStatus(Integer.parseInt((String)ob[6]));
                 user.setTel((String)ob[7]);
                 user.setUserId(Long.parseLong((String)ob[0]));
                 user.setUserName((String)ob[8]);
                 user.setUserType(Integer.parseInt((String)ob[9]));
                 // 是为了前台显示角色名称
                 if(ob[10]!=null&&(String)ob[10]!=""){
                 UMSRole r= (UMSRole)session.get(UMSRole.class,Long.parseLong((String)ob[10]));
                 user.setUmsrole(r);
                 }
                listUser.add(user);
            }
        }
        return listUser;
    } 
    /**
     * 获取当前组织下的用户总数
     * @param groupId
     * @return
     * @see com.trsnj.ums.dao.IGroupDao#getUserCountsInNowGroupId(long)
     */
    public long getUserCountsInNowGroupId(long groupId){
        String sql="select count(u.userid) from umsuser u, umsusergroup gu where u.userid=gu.user_id and gu.group_id="+groupId+" and u.delnote !=0 and u.type !=0";
        Query query=null;
        Session session=this.getCurrentSession();
        query=session.createSQLQuery(sql);
        return (Integer)query.uniqueResult();
    }
    /**
     * 获取不在当前组织下的用户数
     * @param groupId
     * @return
     * @see com.trsnj.ums.dao.IGroupDao#getUserCountsNotInNowGroupId(long)
     */
    public long getUserCountsNotInNowGroupId(long groupId){
        String sql="select count(u.userid) from umsuser u left join umsusergroup gu on u.userid=gu.user_id where gu.group_id !="+groupId+" and u.delnote !=0 and u.type !=0 or (gu.group_id is null and u.delnote !=0 and u.type !=0)";
        Query query=null;
        Session session=this.getCurrentSession();
        query=session.createSQLQuery(sql);
        return (Integer)query.uniqueResult();
    }
    
    /**
     * 分页获取不在当前组织下的用户
     * @param groupId
     * @param page
     * @param rows
     * @return
     * @see com.trsnj.ums.dao.IGroupDao#getUsersNotInNowGroupId(long, int, int)
     */
    public List<UMSUser> getUsersNotInNowGroupId(long groupId, int page, int rows){
        String sql=" select distinct u.userid userid,u.address address,u.email email,u.mobile mobile,u.password passWord,u.qq qq,u.status status,u.tel tel,u.userName userName,u.userType userType,u.role_id role_id,u.cruser cruser,u.crutime crutime from umsuser u left join umsusergroup gu on u.userid=gu.user_id where gu.group_id !="+groupId+" and gu.user_id not in(select user_id from umsusergroup where group_id ="+groupId+")"+" and u.delnote !=0 and u.type !=0 or (gu.group_id is null and u.delnote !=0 and u.type !=0)";
        Session session=this.getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql)
            .addScalar("userid", Hibernate.STRING)
            .addScalar("address", Hibernate.STRING)
            .addScalar("email", Hibernate.STRING)
            .addScalar("mobile", Hibernate.STRING)
            .addScalar("password", Hibernate.STRING)
            .addScalar("qq", Hibernate.STRING)
            .addScalar("status", Hibernate.STRING)
            .addScalar("tel", Hibernate.STRING)
            .addScalar("username", Hibernate.STRING)
            .addScalar("usertype", Hibernate.STRING)
            .addScalar("role_id", Hibernate.STRING)
            .addScalar("cruser", Hibernate.STRING)
            .addScalar("crutime", Hibernate.STRING);
        List list=query.setMaxResults(rows).setFirstResult((page-1)*rows).list();
        //封装成UMSUser对象
        List<UMSUser> listUser=new ArrayList<UMSUser>();
        if (list!=null&&list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                UMSUser user=new UMSUser();
                 user.setAddress((String)ob[1]);
                 user.setCruser((String)ob[11]);
                 user.setCrutime((String)ob[12]);
                 user.setEmail((String)ob[2]);
                 user.setMobile((String)ob[3]);
                 user.setPassWord((String)ob[4]);
                 user.setQq((String)ob[5]);
                 user.setStatus(Integer.parseInt((String)ob[6]));
                 user.setTel((String)ob[7]);
                 user.setUserId(Long.parseLong((String)ob[0]));
                 user.setUserName((String)ob[8]);
                 user.setUserType(Integer.parseInt((String)ob[9]));
                 // 是为了前台显示角色名称
                 if(ob[10]!=null&&(String)ob[10]!=""){
                     UMSRole r= (UMSRole)session.get(UMSRole.class,Long.parseLong((String)ob[10]));
                     user.setUmsrole(r);
                     }
                listUser.add(user);
            }
        }
        return listUser;
    }
    /**
     * 添加当期用户到当前用户组下面
     * @param userId
     * @param groupId
     * @see com.trsnj.ums.dao.IGroupDao#updateUserToGroupId(long, long)
     */
    public void updateUserToGroupId(long userId, long groupId){
        String strDate=CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm");
        String sql=" insert into umsusergroup (group_id,user_id,cruser,crutime) values ("+groupId+","+userId+","+"'admin'"+","+"'"+strDate+"'"+")";
        Session session=this.getCurrentSession();
       // Transaction ts=session.beginTransaction();
        SQLQuery query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
       // ts.commit();
    }
    /**
     * 删除组织下的用户
     * @param userId
     * @param groupId
     * @see com.trsnj.ums.dao.IGroupDao#delUserInNowGroupId(long, long)
     */
    public void delUserInNowGroupId(long userId, long groupId){
        
        String sql=" delete from umsusergroup where user_id="+userId+" and group_id="+groupId;
        Session session=this.getCurrentSession();
       // Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
       // ts.commit();
    }
    
}
