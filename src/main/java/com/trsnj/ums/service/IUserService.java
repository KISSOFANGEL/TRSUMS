/*
 * 文 件 名:  IUserService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums.service;

import java.util.List;
import java.util.Map;

import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUserService
{
    public void add(UMSUser user);
    public List<UMSUser> loadUsers(int firstResult,int maxResult);
    public void update(UMSUser user);
    public void delete(UMSUser user);
	public List<UMSUser> getAllUsers();
	public UMSUser getUserByName(String username);
	public Long getRowsCounts();
	public UMSUser getUserByUserId(long userId);
	public void checkUserName(String userName);
	public UMSUser getUserByUserName(String userName);
	public UMSUser getUserByEmail(String email);
	public UMSUser checkPasswordByUserName(String userName,String password);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param eamil
     * @see [类、类#方法、类#成员]
     */
    public void getEmail(String eamil);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param i
     * @param rows
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getNotActivationUsers(int firstResult, int maxResult);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getCountsNotActivationUsers();
    public void activateUser(String userId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param ids
     * @see [类、类#方法、类#成员]
     */
    public void batchActivateUser(String ids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSUser updatCurrentUser(UMSUser user,String oldpassword);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getUserNameByUserId(long userId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param roleName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUserByRoleName(String roleName);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param oldpassword
     * @param newpassword
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updatePassword(String userId, String oldpassword, String newpassword);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseLong
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> getAuthenticationUserInfo(long userId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param messageid 
     * @param userid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String passAuthenticationUser(long messageid, long userid);
    /** 
     * @param name
     * @param value
     * @param i
     * @param rows
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> loadUsers(String name, String value, int firstResult, int maxResult);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param id
     * @see [类、类#方法、类#成员]
     */
    public void resetsec(String id);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param loginUser
     * @param id
     * @param password
     * @param oldpassword
     * @see [类、类#方法、类#成员]
     */
    public void updatesec(String loginUser, String id, String password, String oldpassword);
}
