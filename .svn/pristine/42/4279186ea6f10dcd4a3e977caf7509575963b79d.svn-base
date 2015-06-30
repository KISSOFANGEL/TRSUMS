/*
 * 文 件 名:  ISystemMessageService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-3-12
 */
package com.trsnj.ums.service;

import java.util.List;

import com.trsnj.ums.pojo.UMSSystemMessage;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-3-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface ISystemMessageService
{

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param mestitle
     * @param mescon
     * @param userId
     * @param userName
     * @param roleId
     * @see [类、类#方法、类#成员]
     */
    void saveSystemMes(String mestitle, String mescon, String userId, String userName, String roleId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param rows
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UMSSystemMessage> getSystemMessages(int page, int rows);
    public long getSystemMessagesCount();

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param id
     * @see [类、类#方法、类#成员]
     */
    void batchdel(String id);
}
