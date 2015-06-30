/*
 * 文 件 名:  IGroupDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.dao;

import java.util.List;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IGroupDao extends IBaseDao<UMSGroup>
{
    public UMSGroup getGroupByGroupName(String groupName);
    public UMSGroup getGroupByGroupName(String groupName,long groupId);
    public List<UMSGroup> getGroupsByParentId(long parentId);
    public List<UMSGroup> getGroupsByUserId(long userId);
    public List<UMSUser> getUsersByGroupId(long groupId,int page,int rows);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param groupId
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
     * @param parseLong
     * @param parseLong2
     * @see [类、类#方法、类#成员]
     */
    public void updateUserToGroupId(long userId, long groupId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseLong
     * @param parseLong2
     * @see [类、类#方法、类#成员]
     */
    public void delUserInNowGroupId(long userId, long groupId);
}
