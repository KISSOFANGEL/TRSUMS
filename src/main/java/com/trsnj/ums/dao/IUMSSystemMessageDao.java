/*
 * 文 件 名:  IUMSSystemMessageDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-3-12
 */
package com.trsnj.ums.dao;

import java.util.List;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSSystemMessage;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-3-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUMSSystemMessageDao extends IBaseDao<UMSSystemMessage>
{

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UMSSystemMessage> getSystemMessages(int firstResult, int maxResult);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    long getSystemMessageCount();

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    void batchdel(String id);
    
}
