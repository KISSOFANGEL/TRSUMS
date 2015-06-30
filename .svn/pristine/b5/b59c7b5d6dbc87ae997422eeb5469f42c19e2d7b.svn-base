/*
 * 文 件 名:  IUMSMessageDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao;

import java.util.List;
import java.util.Map;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSMessage;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUMSMessageDao extends IBaseDao<UMSMessage>
{
    public List<UMSMessage> getMessageByUserId(long userId);
    public List<UMSMessage> getMessageByUserId(long userId,int firstResult,int maxResult,int status);
    public long getMessageCountByUserId(long userId,int status);
	public String delMessageByUserIds(long userId, String ids);
	public List<UMSMessage> getHelpMessageByUserId(long userId,
			int firstResult, int maxResult,int status);
	public String delHelpMessageByUserIds(long userId, String ids);
	public long getHelpMessageCountByUserId(long userId,int status);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param mescon
     * @param userId
     * @param parseLong
     * @see [类、类#方法、类#成员]
     */
    public void addSystemMes(String mestitle,String mescon, long userId, long parseLong);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param replyCon
     * @param ids
     * @param parseLong
     * @see [类、类#方法、类#成员]
     */
    public void replyMes(String replyCon, String ids, long userId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> getAuthenticationUsers(String roleId, int firstResult, int maxResult);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getAuthenticationUsersCounts(long roleid);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param messageids
     * @see [类、类#方法、类#成员]
     */
    public void readMessage(String messageids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param firstResult
     * @param maxResult
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSMessage> getSysMessage(long userId, int firstResult, int maxResult, int status);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getSysMessageCount(long userId, int status);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseLong
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSMessage getMessageByCreuserid(long userid,long roleid);
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
     * @param roleid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getnewAuthoritynum(long roleid);
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
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getnewsysmesnum(long userId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getnewreturnhelpnum(long userId);
}
