/*
 * 文 件 名:  ILoginService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-12
 */
package com.trsnj.ums.service;

import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface ILoginService
{
    public void register(UMSUser user);
    public UMSUser getActivateUserByUserName(String userName,String crutime);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param u
     * @see [类、类#方法、类#成员]
     */
    public void activateUser(UMSUser u);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param email
     * @see [类、类#方法、类#成员]
     */
    public void findpwd(String email);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param username
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String findpwdcheck(String username, String key);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userName
     * @param passWord
     * @see [类、类#方法、类#成员]
     */
    public String updatepwd(String userName, String passWord);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param roleId
     * @param url
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isValidAuthority(String roleId, String url,String chnlid);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param roleId
     * @param path
     * @see [类、类#方法、类#成员]
     */
    public boolean isValidAuthority(String roleId, String path);
}
