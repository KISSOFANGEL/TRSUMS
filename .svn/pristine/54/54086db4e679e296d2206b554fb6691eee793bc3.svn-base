/*
 * 文 件 名:  ISubscriptionService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.trsnj.ums.pojo.UMSSubscription;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface ISubscriptionService
{
    public List<UMSSubscription> getSubscriptionsByUserId(long userId,int firstResult,int maxResult);
    public long getSubscriptionCountByUserId(long userId);
    public JSONArray getWcmChnlInfo(String chnlids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parseLong
     * @param ids
     * @see [类、类#方法、类#成员]
     */
    public String delSubscriptionByIds(long userId, String ids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JSONObject docInfoInSubscription(long userId,String count);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param chnlid
     * @param chnldesc
     * @param userId
     * @see [类、类#方法、类#成员]
     */
    public String chnlsub(String chnlid, String chnldesc, String userId);
}
