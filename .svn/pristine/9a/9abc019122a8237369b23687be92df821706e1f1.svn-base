/*
 * 文 件 名:  UserDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUserDao;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.trsnj.ums.pojo.UMSDemo;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSMessage;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.util.MD5Util;
import com.trsnj.ums.util.SetChangToListUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserDaoImpl extends BaseDao<UMSUser> implements IUserDao
{

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSUser> getEntityClass()
    {
        return UMSUser.class;
    }
    /**
     *添加用户
     */
    public void add(UMSUser user){
        super.save(user);
    }
    /**
     * 
     * 假删除用户
     * @param user
     * @see com.trsnj.ums.basedao.imp.BaseHDao#delete(java.io.Serializable)
     */
    public void delete(UMSUser user){
        // 假删除用户
       // super.delete(user);
        Session session=getCurrentSession();
       // Transaction ts=session.beginTransaction();
        Query query=null;
        String sql="update umsuser set delnote=0 where userid="+user.getUserId();
       query= session.createSQLQuery(sql);
       query.executeUpdate();
      // ts.commit();
    }
    /**
     * 
     * 修改用户
     * @param user
     * @see com.trsnj.ums.basedao.imp.BaseHDao#update(java.io.Serializable)
     */
    public void update(UMSUser user){
        super.update(user);
    }
    /**
     * 
     * 根据用户id查询用户
     * <功能详细描述>
     * @param userId
     * @return UMSUser
     * @see [类、类#方法、类#成员]
     */
    public UMSUser select(long userId){
        return super.get(userId);
    }
    /**
     * 
     * 根据hql语句查询用户
     * @param hql
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> findByHQL(String hql){
       return  super.find(hql);
    }
    /**
     * 
     * 查询所有的用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> loadAllUser(){
        return super.loadAll();
    }
    /**
     * 根据roleId查询用户
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersByRoleId(long roleId){
       
       /* String hql=" from UMSRole r where r.roleId= "+roleId;
        Session session=this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSRole> roles=query.list();
        UMSRole role=roles.get(0);
        Set<UMSUser> set=role.getUsers();
        List<UMSUser> list=SetChangToListUtil.setToList(set);
        return list;*/
        // 根据sql语句来写
        String sql="select userid,address,email,mobile,password,qq,status,tel,username,usertype,role_id,cruser,crutime,delnote,type,addUserType,relname from umsuser where role_id="+roleId+" and delnote !=0 and type !=0";
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
            .addScalar("crutime", Hibernate.STRING)
            .addScalar("delnote", Hibernate.STRING)
            .addScalar("type", Hibernate.STRING)
            .addScalar("addUserType", Hibernate.STRING)
            .addScalar("relname", Hibernate.STRING);
        List list=query.list();
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
                 user.setDelnote(Integer.parseInt((String)ob[13]));
                 user.setType(Integer.parseInt((String)ob[14]));
                 user.setAddUserType(Integer.parseInt((String)ob[15]));
                 user.setRelname((String)ob[16]);
                listUser.add(user);
            }
        }
        return listUser;
    }
    
    /**
     * 此方法在RoleServiceImpl类的delete方法里面使用了，避免了role多次查询，使得通一个session里面查了两次的role实体
     * @param roleId
     * @param role  为null
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersByRoleId(long roleId,UMSRole role){
        // 根据sql语句来写
        String sql="select userid,address,email,mobile,password,qq,status,tel,username,usertype,role_id,cruser,crutime,delnote,type,addUserType,relname from umsuser where role_id="+roleId+" and delnote !=0 and type !=0";
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
            .addScalar("crutime", Hibernate.STRING)
            .addScalar("delnote", Hibernate.STRING)
            .addScalar("type", Hibernate.STRING)
            .addScalar("addUserType", Hibernate.STRING)
            .addScalar("relname",Hibernate.STRING);
        List list=query.list();
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
                 user.setDelnote(Integer.parseInt((String)ob[13]));
                 user.setType(Integer.parseInt((String)ob[14]));
                 user.setAddUserType(Integer.parseInt((String)ob[15]));
                 user.setRelname((String)ob[16]);
                listUser.add(user);
            }
        }
        return listUser;
    }
    /**
     * 分页获取用户根据角色id
     * @param roleId
     * @param page
     * @param rows
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersByRoleId(long roleId,int page,int rows){
        // 根据sql语句来写
        String sql="select userid,address,email,mobile,password,qq,status,tel,username,usertype,role_id,cruser,crutime,delnote,type,addUserType,relname from umsuser where role_id="+roleId+" and delnote !=0 and type !=0";
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
            .addScalar("crutime", Hibernate.STRING)
            .addScalar("delnote", Hibernate.STRING)
            .addScalar("type", Hibernate.STRING)
            .addScalar("addUserType", Hibernate.STRING)
            .addScalar("relname", Hibernate.STRING);
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
                 user.setDelnote(Integer.parseInt((String)ob[13]));
                 user.setType(Integer.parseInt((String)ob[14]));
                 user.setAddUserType(Integer.parseInt((String)ob[15]));
                 user.setRelname((String)ob[16]);
                listUser.add(user);
            }
        }
        return listUser;
    }
    
    /**
     * 分页获取不在当前角色下的用户
     * @param roleId
     * @param page
     * @param rows
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersNotInNowRoleId(long roleId,int page,int rows){
        // 根据sql语句来写
        String sql="select userid,address,email,mobile,password,qq,status,tel,username,usertype,role_id,cruser,crutime,delnote,type,addUserType,relname from umsuser where role_id !="+roleId+" or role_id is null and delnote !=0 and type !=0";
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
            .addScalar("crutime", Hibernate.STRING)
            .addScalar("delnote", Hibernate.STRING)
            .addScalar("type", Hibernate.STRING)
            .addScalar("addUserType", Hibernate.STRING)
            .addScalar("relname", Hibernate.STRING);
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
                 user.setDelnote(Integer.parseInt((String)ob[13]));
                 user.setType(Integer.parseInt((String)ob[14]));
                 user.setAddUserType(Integer.parseInt((String)ob[15]));
                 user.setRelname((String)ob[16]);
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
     * 根据用户名来查找
     * @param roleName  用户名是唯一的，不可以重复
     * @return  所有用户，包括已经假删除的数据
     * @see [类、类#方法、类#成员]
     */
    public UMSUser getUserByUserName(String userName){
        String hql=" from UMSUser u where u.userName= "+"'"+userName+"'";
        // 这里的session一定要用父类的getCurrentSession的方法，不要直接用HibernateFactroy这个类
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSUser> list=query.list();
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 根据用户名来查找
     * @param userName  用户名是唯一的，不可以重复
     * @param userId
     * @return 所有用户，包括已经假删除的数据
     * @see [类、类#方法、类#成员]
     */
    public UMSUser getUserByUserName(String userName,long userId){
        String hql=" from UMSUser u where u.userName= "+"'"+userName+"'"+" and u.userId != "+userId;
        // 这里的session一定要用父类的getCurrentSession的方法，不要直接用HibernateFactroy这个类
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSUser> list=query.list();
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 根据状态来分页查询用户
     * @param firstResult 
     * @param maxResult
     * @param status
     * @return list
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersByStatus(int firstResult,int maxResult,int status){
        String hql=" from UMSUser u where u.status= "+status;
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSUser> list=query.list();
        return list;
    }
    /**
     * 分页加载用户,不包括注册但没激活的用户
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> loadUsers(int firstResult,int maxResult){
        String hql=" from UMSUser u where u.delnote !=0 and u.type !=0";
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSUser> list=query.list();
        return list;
    }
    /**
     * 根据条件分页查询用户
     * @param name 字段名称
     * @param value 字段值
     * @param firstResult
     * @param maxResult
     * @return
     * @see com.trsnj.ums.dao.IUserDao#loadUsers(java.lang.String, java.lang.String, int, int)
     */
    public List<UMSUser> loadUsers(String name, String value, int firstResult, int maxResult){
        String hql=" from UMSUser where delnote !=0 and type !=0 and "+name+" like "+"'%"+value+"%'";
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSUser> list=query.list();
        return list;
    }
    /**
     * 分页获取未被激活的用户
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersNotActivation(int firstResult,int maxResult){
        String hql=" from UMSUser u where u.delnote =1 and u.type =0";
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSUser> list=query.list();
        return list;
    }
    /**
     * 获取未被激活用户的总数
     * @return
     * @see com.trsnj.ums.dao.IUserDao#getCountsNotActivationUsers()
     */
    public long getCountsNotActivationUsers(){
        String sql=" select count(*) from umsuser where delnote=1 and type=0";
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
         
        return (Integer)query.uniqueResult();
    }
    /**
     * 获得所有用户，包括假删除的数据和已经使用的用户
     * @return
     * @see com.trsnj.ums.dao.IUserDao#getAllUsers()
     */
    @Override
    public List<UMSUser> getAllUsers()
    {
        return super.loadAll();
    }
    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     * @see com.trsnj.ums.dao.IUserDao#getUserByEmail(java.lang.String)
     */
    public UMSUser getUserByEmail(String email){
        
        String hql=" from UMSUser u where u.email= "+"'"+email+"'";
        // 这里的session一定要用父类的getCurrentSession的方法，不要直接用HibernateFactroy这个类
        Session session =this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSUser> list=query.list();
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 获得未删除的用户总数
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getRowsCounts(){
        
        String sql="select count(*) from umsuser where delnote !=0 and type !=0";
        Query query =null;
        Session session=getCurrentSession();
        query=session.createSQLQuery(sql);
       long r= (Integer)query.uniqueResult();
        return r;
    }
    /**
     * 根据用户名与创建时间获取待激活的用户
     * @param userName
     * @param crutime
     * @return
     * @see com.trsnj.ums.dao.IUserDao#getActivateUserByUserName(java.lang.String, java.lang.String)
     */
    public UMSUser getActivateUserByUserName(String userName, String crutime){
        String hql="from UMSUser u where u.userName="+"'"+userName+"'"+" and u.crutime="+"'"+crutime+"'";
        Session session=this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSUser> users=query.list();
        if(users!=null&&users.size()>0){
            return users.get(0);
        }
        return null;
    }
    /**
     * 根据角色名获取用户
     * @param roleName
     * @return
     * @see com.trsnj.ums.dao.IUserDao#getUserByRoleName(java.lang.String)
     */
    public List<UMSUser> getUserByRoleName(String roleName){
        String sql=" select u.relname relname,u.updatepwdkey updatepwdkey,u.updatepwdtime updatepwdtime,u.addUserType addUserType,u.delnote delnote,u.type type, u.userid userid,u.address address,u.email email,u.mobile mobile,u.password passWord,u.qq qq,u.status status,u.tel tel,u.userName userName,u.userType userType,u.role_id role_id,u.cruser cruser,u.crutime crutime from umsuser u left join umsrole r on u.role_id=r.roleid where r.roleName="+"'"+roleName+"'"+" and u.delnote !=0 and u.type !=0";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSUser> users=query.list();
        return users;
    }
    /**
     * 前台一步检查用户名的密码是否正确
     * @param userName
     * @param password
     * @return
     * @see com.trsnj.ums.dao.IUserDao#checkPasswordByUserName(java.lang.String, java.lang.String)
     */
    public UMSUser checkPasswordByUserName(String userName,String password){
        UMSUser u=this.getUserByUserName(userName);
        String pwd=MD5Util.getSecretStr(password);
        if(u.getPassWord().equals(pwd)){
            return u;
        }
        return null;
    }
    /**
     * 得到认证用户的详细信息
     * @param userId
     * @return
     * @see com.trsnj.ums.dao.IUserDao#getAuthenticationUserInfo(long)
     */
    public Map<String, String> getAuthenticationUserInfo(long userId){
        Map<String,String> map=new HashMap<String,String>();
        String sql=" select u.userid userid,u.address address,u.cruser cruser,u.crutime crutime,u.email email,u.mobile mobile,u.qq qq,u.tel tel,u.userName userName,u.role_id roleid,u.relname relname,c.companyAddress companyAddress,c.companyName companyName,c.companyNature companyNature,c.deptName deptName,c.companytel companytel from umsuser u left join umscompanyinfo c on u.userid=c.userid where u.userid= "+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql)
            .addScalar("userid", Hibernate.STRING)
            .addScalar("address", Hibernate.STRING)
            .addScalar("cruser", Hibernate.STRING)
            .addScalar("crutime", Hibernate.STRING)
            .addScalar("email", Hibernate.STRING)
            .addScalar("mobile", Hibernate.STRING)
            .addScalar("qq", Hibernate.STRING)
            .addScalar("tel", Hibernate.STRING)
            .addScalar("userName", Hibernate.STRING)
            .addScalar("roleid", Hibernate.STRING)
            .addScalar("relname", Hibernate.STRING)
            .addScalar("companyAddress", Hibernate.STRING)
            .addScalar("companyName", Hibernate.STRING)
            .addScalar("companyNature", Hibernate.STRING)
            .addScalar("deptName", Hibernate.STRING)
            .addScalar("companytel", Hibernate.STRING);
        List list=query.list();
        if (list!=null&&list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                map.put("userid",(String)ob[0]);
                map.put("address", (String)ob[1]);
                map.put("cruser", (String)ob[2]);
                map.put("crutime", (String)ob[3]);
                map.put("email", (String)ob[4]);
                map.put("mobile", (String)ob[5]);
                map.put("qq", (String)ob[6]);
                map.put("tel", (String)ob[7]);
                map.put("userName", (String)ob[8]);
                map.put("roleid", (String)ob[9]);
                map.put("relname", (String)ob[10]);
                map.put("companyAddress", (String)ob[11]);
                map.put("companyName", (String)ob[12]);
                map.put("companyNature", (String)ob[13]);
                map.put("deptName", (String)ob[14]);
                map.put("companytel", (String)ob[15]);
            }
        }
        return map;
    }
    
    public void flushSession(){
        this.getCurrentSession().flush();
    }
}
