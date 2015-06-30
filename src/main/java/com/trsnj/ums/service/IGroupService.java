/*
 * 文 件 名:  IGroupService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums.service;

import java.util.List;

import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IGroupService
{
    public void add(UMSGroup group);
    public void update(UMSGroup group);
    public List<UMSGroup> loadGroups(int firstResult,int maxResult);
    public void delete(UMSGroup group);
    public void deleteGroup(UMSGroup group);//如果组织被用户使用就不能删除
    public List<UMSGroup> getGroupsByUserId(long userId);
    public List<UMSGroup> getGroupsByPrentId(long parentId);
    public Long getRowsCounts();
    public UMSGroup getGroupByGroupId(long groupId);
    public List<UMSUser> getUsersByGroupId(long groupId,int page,int rows);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getUserCountsInNowGroupId(long groupId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param groupId
     * @param page
     * @param rows
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersNotInNowGroupId(long groupId, int page, int rows);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param groupId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getUserCountsNotInNowGroupId(long groupId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userIds
     * @param groupId
     * @see [类、类#方法、类#成员]
     */
    public void updateUserToGroupId(String userIds, String groupId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param groupId
     * @see [类、类#方法、类#成员]
     */
    public void delUserInNowGroupId(String userId, String groupId);
}
