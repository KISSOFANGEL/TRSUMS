/*
 * 文 件 名:  UserServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.trsnj.ums.dao.IGroupDao;
import com.trsnj.ums.dao.IUMSMessageDao;
import com.trsnj.ums.dao.IUserDao;
import com.trsnj.ums.dao.IUserGroupDao;
import com.trsnj.ums.dao.impl.GroupDaoImpl;
import com.trsnj.ums.dao.impl.UMSMessageDaoImpl;
import com.trsnj.ums.dao.impl.UserDaoImpl;
import com.trsnj.ums.dao.impl.UserGroupDaoImpl;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSMessage;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.pojo.UMSUserGroup;
import com.trsnj.ums.service.IUserService;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.MD5Util;
import com.trsnj.ums.util.TRSMailUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserServiceImpl implements IUserService
{
    //private IUserDao userdao=new UserDaoImpl();
    //private IGroupDao groupdao=new GroupDaoImpl();
    //private IUserGroupDao usergroupdao=new UserGroupDaoImpl();
    //private IUMSMessageDao messagedao=new UMSMessageDaoImpl();
    private IUserDao userdao=null;
    private IGroupDao groupdao=null;
    private IUserGroupDao usergroupdao=null;
    private IUMSMessageDao messagedao=null;
    
    /**
     * 获取 groupdao
     * @return 返回 groupdao
     */
    public IGroupDao getGroupdao()
    {
        return groupdao;
    }

    /**
     * 设置 groupdao
     * @param 对groupdao进行赋值
     */
    public void setGroupdao(IGroupDao groupdao)
    {
        this.groupdao = groupdao;
    }

    /**
     * 获取 messagedao
     * @return 返回 messagedao
     */
    public IUMSMessageDao getMessagedao()
    {
        return messagedao;
    }

    /**
     * 设置 messagedao
     * @param 对messagedao进行赋值
     */
    public void setMessagedao(IUMSMessageDao messagedao)
    {
        this.messagedao = messagedao;
    }

    /**
     * 获取 userdao
     * @return 返回 userdao
     */
    public IUserDao getUserdao()
    {
        return userdao;
    }

    /**
     * 获取 usergroupdao
     * @return 返回 usergroupdao
     */
    public IUserGroupDao getUsergroupdao()
    {
        return usergroupdao;
    }

    /**
     * 设置 usergroupdao
     * @param 对usergroupdao进行赋值
     */
    public void setUsergroupdao(IUserGroupDao usergroupdao)
    {
        this.usergroupdao = usergroupdao;
    }

    /**
     * 设置 userdao
     * @param 对userdao进行赋值
     */
    public void setUserdao(IUserDao userdao)
    {
        this.userdao = userdao;
    }
    /**
     * 添加用户,这个添加用户可以关联组织添加，给中间表集合里UMSUserGroup的对象的group设置值，然后关联添加
     * @param user
     * @see [类、类#方法、类#成员]
     */
    public void add(UMSUser user){
        // 验证用户名是否重复
        UMSUser u=userdao.getUserByUserName(user.getUserName());
        if(u!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0090);//这是运行时异常，Code_0090表示用户名已经存在
        }
        // 验证邮箱是否被注册了
        UMSUser ue=userdao.getUserByEmail(user.getEmail());
        if(ue!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0091);//改邮箱已经被注册
        }
        // 给用户名的密码加密
        String password=user.getPassWord();
        password=MD5Util.getSecretStr(password);
        user.setPassWord(password);
        Set<UMSUserGroup> usergroups=user.getUmsusergroups();      
        if(usergroups==null||usergroups.size()==0) {
            userdao.save(user);
        }
        else{
            user.setUmsusergroups(null);// 防止关联保存中间表数据
            userdao.save(user);//这个在保存的时候也关联了
            Iterator<UMSUserGroup> it=usergroups.iterator();
            while(it.hasNext()){
                UMSUserGroup usergroup=it.next();
              UMSGroup group= groupdao.get(usergroup.getGroup().getGroupId());
              usergroup.setGroup(group);
              usergroup.setUser(user);
              // 保存创建时间，创建用户，可以从session里面去取出来
              usergroup.setCruser("admin");// 从session里面去拿
              usergroup.setCrutime("crutime");
              usergroupdao.save(usergroup);
                
            }
        }
       // 向用户发送email
       // StringBuffer sb=new StringBuffer();
       // sb.append("http://www.baidu.com");
       // TRSMailUtil.send(user.getEmail(), sb.toString());
    }
    /**
     * 查询用户
     * @param firstResult 从哪里开始
     * @param maxResult   查询多少条
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> loadUsers(int firstResult,int maxResult){
        
       // List <UMSUser> users=userdao.findByCriteria(userdao.createDetachedCriteria(UMSUser.class), firstResult, maxResult);
        List <UMSUser> users=userdao.loadUsers(firstResult, maxResult);
        return users;
    }
    /**
     * 根据条件分页查询用户
     * @param name  字段名
     * @param value  字段值
     * @param firstResult
     * @param maxResult
     * @return
     * @see com.trsnj.ums.service.IUserService#loadUsers(java.lang.String, java.lang.String, int, int)
     */
    public List<UMSUser> loadUsers(String name, String value, int firstResult, int maxResult){
        List <UMSUser> users=userdao.loadUsers(name,value,firstResult, maxResult);
        return users;
    }
    
    /**
     * 修改用户信息，这里修改用户的关联中间表时要修改，中间表修改只要传中间表实体类的集合，中间表实体类的两个对象属性只传id即可！
     * @param user
     * @see [类、类#方法、类#成员]
     */
    public void update(UMSUser user){
        UMSUser uupdate=userdao.get(user.getUserId());
        // 验证修改后的用户名是否重复
        UMSUser u=userdao.getUserByUserName(user.getUserName(),user.getUserId());
        if(u!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0090);//这是运行时异常，Code_0090表示用户名已经存在
        }
        // 将user里的参数传给uupdate里去   修改必须放在此处，否侧session里面不能放两个用户名相同的对象，报sql异常
        CommonUtil.updateUser(user, uupdate);// 这里如果修改组织的话不能用关联修改???
        if(user.getUmsrole()==null){
            // 设置用户的角色为null
            uupdate.setUmsrole(null);
        }
        if(user.getUmsusergroups()==null||user.getUmsusergroups().size()==0){
            //删除中间表            
            Set<UMSUserGroup> set= uupdate.getUmsusergroups();// 不能懒加载
            for (Iterator<UMSUserGroup> it = set.iterator(); it.hasNext();)
            {
                usergroupdao.delete(it.next());//修改的时候将中间表删除，然后重新插入
            }
        }
        // 修改与用户关联的数据
        if(user.getUmsusergroups()!=null&&user.getUmsusergroups().size()>0){
            //删除原来的中间表数据
            Set<UMSUserGroup> set= uupdate.getUmsusergroups();// 不能懒加载
            uupdate.setUmsusergroups(null);//不设置null下面的删除无法删除
            for (Iterator<UMSUserGroup> it = set.iterator(); it.hasNext();)
            {
                UMSUserGroup umsug=it.next();
               // usergroupdao.delete(umsug);//修改的时候将中间表删除，然后重新插入
                usergroupdao.deleteById(umsug.getUserGroupId());
            }
            //userdao.flushSession();
            Set<UMSUserGroup> usergroups=user.getUmsusergroups();
            for (Iterator<UMSUserGroup> iterator = usergroups.iterator(); iterator.hasNext();)
            {
                //每次修改的时候要判断联合主键在数据库里面是否存在
                UMSUserGroup usergroup =iterator.next();
                UMSUser uuser=new UMSUser();
                uuser.setUserId(uupdate.getUserId());
                UMSGroup group= groupdao.get(usergroup.getGroup().getGroupId());
                usergroup.setGroup(group);// 这两个实体对象都是持久化对象
                usergroup.setUser(uuser);
                usergroupdao.save(usergroup);
            }
        }
        uupdate.setUmsusergroups(null);
        userdao.saveOrUpdate(uupdate);// 修改的只是用户数据库，与组织关联没修改
        
    }
    /**
     * 删除用户，删除用户与组织的中间表
     * @param user
     * @see [类、类#方法、类#成员]
     */
    public void delete(UMSUser user){
        UMSUser u=userdao.get(user.getUserId());
        Set<UMSUserGroup> usergroups=u.getUmsusergroups();
        if(usergroups!=null&&usergroups.size()>0)
        {
            Iterator<UMSUserGroup> it=usergroups.iterator();
            while(it.hasNext())
            {
                usergroupdao.delete(it.next());// 删除用户与组织的中间表
            }
        }
        u.setUmsusergroups(null);// 将关联的中间表去取
        userdao.delete(u);//删除用户 等一下改成状态删除（假删除）
    }

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.service.IUserService#getAllUsers()
     */
    @Override
    public List<UMSUser> getAllUsers()
    {
        return userdao.getAllUsers();
    }

	@Override
	public UMSUser getUserByName(String username) {
		UMSUser user=userdao.getUserByUserName(username);
		return user;
	}
	/**
	 * 获得总记录数
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Long getRowsCounts(){
	    
	  //return  (Long)userdao.getRowCount(userdao.createDetachedCriteria(UMSUser.class));
	    return userdao.getRowsCounts(); 
	}
	/**
	 * 根据用户id来获取用户
	 * @param userId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public UMSUser getUserByUserId(long userId){
	    
	    return userdao.get(userId);
	}
	/**
	 * 根据用户id获取用户名
	 * @param userId
	 * @return
	 * @see com.trsnj.ums.service.IUserService#getUserNameByUserId(long)
	 */
	 public String getUserNameByUserId(long userId){
	     return userdao.get(userId).getUserName();
	 }
	/**
	 * 检查用户名是否存在
	 * @see [类、类#方法、类#成员]
	 */
	public void checkUserName(String userName){
	    UMSUser u=userdao.getUserByUserName(userName);
	    if(u!=null){
	        throw new AppRunTimeException(ExceptionConstants.Code_0090);//抛出用户名已经存在的异常
	    }
	}
	/**
	 * 检查邮箱是否存在
	 * @param eamil
	 * @see com.trsnj.ums.service.IUserService#getEmail(java.lang.String)
	 */
	public void getEmail(String eamil){
	    UMSUser u=userdao.getUserByEmail(eamil);
	    if(u!=null){
	        throw new AppRunTimeException(ExceptionConstants.Code_0091);//抛出邮箱已经被注册
	    }
	}
	/**
	 * 分页获取未被激活的用户
	 * @param firstResult
	 * @param maxResult
	 * @return
	 * @see com.trsnj.ums.service.IUserService#getNotActivationUsers(int, int)
	 */
	 public List<UMSUser> getNotActivationUsers(int firstResult, int maxResult){
	     
	     return userdao.getUsersNotActivation(firstResult, maxResult);
	 }
	 /**
	  * 获取未被激活的用户的总数
	  * @return
	  * @see com.trsnj.ums.service.IUserService#getCountsNotActivationUsers()
	  */
	 public long getCountsNotActivationUsers(){
	     
	     return userdao.getCountsNotActivationUsers();
	 }
	 
	 /**
	     * 激活用户
	     * @param u
	     * @see com.trsnj.ums.service.ILoginService#activateUser(com.trsnj.ums.pojo.UMSUser)
	     */
	    public void activateUser(String userId){
	        
	       UMSUser u=userdao.get(Long.parseLong(userId));
	       u.setType(1);//激活用户
	        userdao.saveOrUpdate(u);
	    }
	    /**
	     * 批量激活用户
	     * @param ids
	     * @see com.trsnj.ums.service.IUserService#batchActivateUser(java.lang.String)
	     */
	    public void batchActivateUser(String ids){
	        String[] userIds=ids.split(",");
	         for(String userId:userIds){
	             activateUser(userId);//循环激活用户
	         }
	    }
	    /**
	     * 修改当前用户
	     * @param user
	     * @return
	     * @see com.trsnj.ums.service.IUserService#updatCurrentUser(com.trsnj.ums.pojo.UMSUser)
	     */
	    
	    public UMSUser updatCurrentUser(UMSUser user,String oldpassword){
	        //带密码修改的用户修改
	        UMSUser u= userdao.get(user.getUserId());
	        if(user.getPassWord()!=""&&oldpassword!=""&&oldpassword!=null&&user.getPassWord()!=null){
	            // 校验old密码是否正确
	          String secretoldPWD= MD5Util.getSecretStr(oldpassword);
	          if(!u.getPassWord().equals(secretoldPWD)){
	              throw new AppRunTimeException(ExceptionConstants.Code_0801);//原密码输入错误
	          }
	          if(user.getAddress()!=""&&user.getAddress()!=null){
	              u.setAddress(user.getAddress());
	          } 
	          u.setPassWord(MD5Util.getSecretStr(user.getPassWord()));
	          if(user.getEmail()!=""&&user.getEmail()!=null){
                  u.setEmail(user.getEmail());
              }
	          if(user.getMobile()!=""&&user.getMobile()!=null){
                  u.setMobile(user.getMobile());
              }
	          if(user.getQq()!=""&&user.getQq()!=null){
                  u.setQq(user.getQq());
              } 
	          userdao.update(u);
	        }else{
	            if(user.getAddress()!=""&&user.getAddress()!=null){
	                  u.setAddress(user.getAddress());
	              } 
	              if(user.getEmail()!=""&&user.getEmail()!=null){
	                  u.setEmail(user.getEmail());
	              }
	              if(user.getMobile()!=""&&user.getMobile()!=null){
	                  u.setMobile(user.getMobile());
	              }
	              if(user.getQq()!=""&&user.getQq()!=null){
	                  u.setQq(user.getQq());
	              }
	              if(user.getRelname()!=""&&user.getRelname()!=null){
	                  u.setRelname(user.getRelname());
	              }
	              userdao.update(u);
	        }
	        return u;
	    }
	   /**
	    * 根据角色名获取用户
	    * @param roleName
	    * @return
	    * @see com.trsnj.ums.service.IUserService#getUserByRoleName(java.lang.String)
	    */
	    public List<UMSUser> getUserByRoleName(String roleName){
	        
	        return userdao.getUserByRoleName(roleName);
	    }
	    /**
	     * 修改密码
	     * @param userId
	     * @param oldpassword
	     * @param newpassword
	     * @return
	     * @see com.trsnj.ums.service.IUserService#updatePassword(java.lang.String, java.lang.String, java.lang.String)
	     */
	    public String updatePassword(String userId, String oldpassword, String newpassword){
	       UMSUser user=userdao.get(Long.parseLong(userId));
	       String secretOldpassword=MD5Util.getSecretStr(oldpassword);
	       if(!secretOldpassword.equals(user.getPassWord())){
	           return "FAIL";
	       }
	        user.setPassWord(MD5Util.getSecretStr(newpassword));
	        userdao.update(user);
	        return "SUCCESS";
	    }
	    /**
	     * 获得认证用户的详细信息
	     * @param userId
	     * @return
	     * @see com.trsnj.ums.service.IUserService#getAuthenticationUserInfo(long)
	     */
	    public Map<String, String> getAuthenticationUserInfo(long userId){
	        return userdao.getAuthenticationUserInfo(userId);
	    }
	    /**
	     * 修改用户的认证请求
	     * @param messageid
	     * @param userid
	     * @return
	     * @see com.trsnj.ums.service.IUserService#passAuthenticationUser(long, long)
	     */
	    public String passAuthenticationUser(long messageid, long userid){
	        UMSUser u=userdao.get(userid);
	        if(u.getUmsrole()!=null){
	            UMSRole r=new UMSRole();
	            r.setRoleId(2);
	            u.setUmsrole(r);
	        }
	        userdao.update(u);
	        //普通用户变成认证用户后，向用户邮箱发送一份完成认证通知！
	        String email=u.getEmail();
	        String username=u.getUserName();
	        toEmailPassAuthority(email,username);//向其发送邮箱通知，完成认证
	       UMSMessage m= messagedao.get(messageid);
	       m.setReplytime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
	       messagedao.update(m); 
	       return "SUCCESS";
	    }
	    /** 
         * 向用户发送认证完成通知，并转换成认证用户
         * @param email
         * @param username
         * @see [类、类#方法、类#成员]
         */
        private void toEmailPassAuthority(String email, String username)
        {
            StringBuffer sb=new StringBuffer();      
            sb.append("亲爱的" + username + "您好：<br/><br/>");
            sb.append("&nbsp;&nbsp;&nbsp;&nbsp;您在江苏省标准化研究院个人中心完成个人信息详情后，由普通用户升级成认证用户。<br/><br/>");
            sb.append("感谢您的持续关注、");
             sb.append("我们将一如既往、热忱的为您服务！<br/><br/>");
            TRSMailUtil.send(email, sb.toString());
        }

        /**
	     * 根据用户名获得用户
	     * @param userName
	     * @return
	     * @see [类、类#方法、类#成员]
	     */
	    public UMSUser getUserByUserName(String userName){
	        
	        return userdao.getUserByUserName(userName);
	    }
	    /**
	     * 根据邮箱获得用户
	     * @param email
	     * @return
	     * @see com.trsnj.ums.service.IUserService#getUserByEmail(java.lang.String)
	     */
	    public UMSUser getUserByEmail(String email){
	        return userdao.getUserByEmail(email);
	    }
	    /**
	     * 根据用户名验证密码是否匹配
	     * @param userName
	     * @param password
	     * @return
	     * @see com.trsnj.ums.service.IUserService#checkPasswordByUserName(java.lang.String, java.lang.String)
	     */
	    public UMSUser checkPasswordByUserName(String userName,String password){
	       return userdao.checkPasswordByUserName(userName, password);
	    }
	    /**
	     * 根据id对密码重置
	     * @param id
	     * @see com.trsnj.ums.service.IUserService#resetsec(java.lang.String)
	     */
	    public void resetsec(String id){
	        UMSUser user=userdao.get(Long.parseLong(id));
	        user.setPassWord(MD5Util.getSecretStr("99999999"));
	        userdao.update(user);
	    }
	    /**
	     * 管理员对管理员用户的密码修改
	     * @param loginUser 判断是否是admin，admin超级管理员可以修改任意管理员的密码而且不需要原密码。
	     * @param id 待修改的用户
	     * @param password 新密码
	     * @param oldpassword 原密码
	     * @see com.trsnj.ums.service.IUserService#updatesec(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	     */
	    public void updatesec(String loginUser, String id, String password, String oldpassword){
	        UMSUser user=userdao.get(Long.parseLong(id));
	        if("admin".equals(loginUser)){
	            user.setPassWord(MD5Util.getSecretStr(password));
	            userdao.update(user);
	        }else{
	           if(oldpassword==null||"".equals(oldpassword)){
	               throw new AppRunTimeException(ExceptionConstants.Code_0802);//请输入原密码验证后进行修改
	           }else{
	               //验证密码的正确性
	               String oldpasswordsec=MD5Util.getSecretStr(oldpassword);
	               if(!oldpasswordsec.equals(user.getPassWord())){
	                   throw new AppRunTimeException(ExceptionConstants.Code_0801);//原密码不正确
	               }else{
	                   user.setPassWord(MD5Util.getSecretStr(password));
	                   userdao.update(user);
	               }
	           }  
	        }
	        
	    }
}
