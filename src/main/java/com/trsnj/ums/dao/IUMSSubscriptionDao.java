/*
 * 文 件 名:  IUMSSubscription.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao;

import java.util.List;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSSubscription;

/**
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUMSSubscriptionDao extends IBaseDao<UMSSubscription>
{
    public List<UMSSubscription> getSubscriptionByUserId(long userId);
    public List<UMSSubscription> getSubscriptionByUserId(long userId,int firstResult,int maxResult);
    public long getSubscriptionCountByUserId(long userId);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param ids
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delSubscriptionByIds(long userId, String ids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param chnlid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSSubscription getSubscriptionByChnlid(String userId, String chnlid);
}
