/*
 * 文 件 名:  IShareService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-31
 */
package com.trsnj.ums.service;

import java.util.List;

import com.trsnj.ums.pojo.UMSShare;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-31]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IShareService
{
    public JSONArray getWcmDocInfo(String docids);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param i
     * @param perpage
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSShare> getSharesByUserId(long userId, int firstResult, int maxResult);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getShareCountByUserId(long userId);
    public String delShareByIds(long userId,String ids);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JSONArray hotShareDoc(String userId,String firstResult,String maxResult);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param sharedocid
     * @param sharedoctitle
     * @param where
     * @see [类、类#方法、类#成员]
     */
    public void shareDoc(String sharedocid, String sharedoctitle, String where,String userId);
}
