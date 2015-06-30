/*
 * 文 件 名:  IMessageService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-24
 */
package com.trsnj.ums.service;

import java.util.List;
import java.util.Map;

import com.trsnj.ums.pojo.UMSMessage;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IMessageService
{
    public List<UMSMessage> getMessageByUserId(long userId,int firstResult,int maxResult,int status);

	public String delMessageByUserIds(long userId, String ids);

	public List<UMSMessage> getHelpMessageByUserId(long parseLong, int i,
			int perpage,int status);

	public String delHelpMessageByUserIds(long userId, String ids);
	public long getHelpMessageCountByUserId(long userId,int status);
	public long getMessageCountByUserId(long userId,int status);

    /** 
     *添加求助信息
     * @param mescon
     * @param userId
     * @param adminid
     * @see [类、类#方法、类#成员]
     */
    public void add(String mescon,String mestitle, String userId, String adminid);

    public void addMesToRole(String mescon,String mestitle, String userId, long adminid);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param mescon
     * @param userId
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String addSystemMes(String mestitle,String mescon,String userId, String roleId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param replyCon
     * @param ids
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String replyMes(String replyCon, String ids, String userId);
    public String getUserNameByUserId(long userId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @see [类、类#方法、类#成员]
     */
    public void addAuthentication(String userId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> getAuthenticationUsers(String roleId,int firstResult,int maxResult);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseLong
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getAuthenticationUsersCounts(long roleid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param messageids
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String readMessage(String messageids);
    /**
     * 分页获取向系统管理员求助的信息(后台展示)
     * @param userId
     * @param firstResult
     * @param maxResult
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSMessage> getSysMessage(long userId,int firstResult,int maxResult,int status);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseLong
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getSysMessageCount(long parseLong, int status);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param ids
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String batchDelAuthority(String ids);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getnewAuthoritynum();

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getnewHelpnum();

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getnewsysmesnum(String userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getnewreturnhelpnum(String userid);

}
