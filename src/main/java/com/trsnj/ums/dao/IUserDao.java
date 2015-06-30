/*
 * 文 件 名:  IUserDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.dao;

import java.util.List;
import java.util.Map;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSDemo;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUserDao extends IBaseDao<UMSUser>
{
    public void add(UMSUser user);
    public void delete(UMSUser user);
    public void update(UMSUser user);
    public UMSUser select(long userId);
    public List<UMSUser> findByHQL(String hql);
    public List<UMSUser> loadAllUser();
    public List<UMSUser> getUsersByRoleId(long roleId);
    public List<UMSUser> getUsersByRoleId(long roleId,UMSRole role);
    public List<UMSUser> getUsersByRoleId(long roleId,int page,int rows);
    public List<UMSUser> getUsersNotInNowRoleId(long roleId,int page,int rows);
    public UMSUser getUserByUserName(String userName);
    public UMSUser getUserByUserName(String userName,long userId);
    public List<UMSUser> getUsersByStatus(int firstResult,int maxResult,int status);
	public List<UMSUser> getAllUsers();
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param email
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSUser getUserByEmail(String email);
    public List<UMSUser> loadUsers(int firstResult,int maxResult);
    public Long getRowsCounts();
    public List<UMSUser> getUsersNotActivation(int firstResult,int maxResult);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getCountsNotActivationUsers();
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userName
     * @param crutime
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSUser getActivateUserByUserName(String userName, String crutime);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param roleName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUserByRoleName(String roleName);
    public UMSUser checkPasswordByUserName(String userName,String password);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> getAuthenticationUserInfo(long userId);
    public void flushSession();
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param name
     * @param value
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> loadUsers(String name, String value, int firstResult, int maxResult);

}
