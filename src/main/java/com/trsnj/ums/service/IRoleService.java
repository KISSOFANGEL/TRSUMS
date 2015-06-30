/*
 * 文 件 名:  IRoleService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.trsnj.ums.pojo.UMSChannel;
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
public interface IRoleService
{
    public void add(UMSRole role);
    public void update(UMSRole role);
    public List<UMSRole> loadRoles(int firstResult,int maxResult);
    public void delete(UMSRole role);
    public List<UMSRole> loadAllRoles();
    public Long getRowsCounts();
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseLong
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSRole getRoleByRoleId(long roleId);
    public List<UMSUser> getUsersByRoleId(long roleId,int page,int rows);
    public Long getUsersCountsByRoleId(long roleId);//获取总数
    public List<UMSUser> getUsersNotInNowRoleId(long roleId,int page,int rows);
    public Long getUsersConuntsNotInNowRoleId(long roleId);
    public void updateUserToRoleId(String userIds,String roleId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @see [类、类#方法、类#成员]
     */
    public void updateUserToRoleId(String userId);
    public UMSRole getRoleByRoleName(String roleName);
    public void add(UMSRole role,String textSelect);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JSONObject getRoleAndChannelByRoleId(String roleId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param role
     * @param editchannel
     * @see [类、类#方法、类#成员]
     */
    public void updateRoleAndChannel(UMSRole role, String editchannel);
    public void deleteRoleAndChannel(UMSRole role);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param roleid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSChannel> getchannelsbyroleid(long roleid);
}
