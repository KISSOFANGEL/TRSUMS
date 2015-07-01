/*
 * 文 件 名:  IUMSBehaviorLogsDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.dao;

import java.util.List;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSBehaviorLogs;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUMSBehaviorLogsDao extends IBaseDao<UMSBehaviorLogs>
{

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param currpage
     * @param perpage
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UMSBehaviorLogs> getMyDynamic(int currpage, int perpage, long userId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    long getMyDynamicCount(long userId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param ids
     * @see [类、类#方法、类#成员]
     */
    void delMyDynamic(String userId, String ids);

/*	List<UMSBehaviorLogs> getMyprogram(int currpage, int perpage, long userId);

	long getgetMyprogramCount(long userId);*/
    
}
