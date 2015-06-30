/*
 * 文 件 名:  ICollectService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.trsnj.ums.pojo.UMSCollect;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface ICollectService
{
    public List<UMSCollect> getCollectsByUserId(long userId,int firstResult,int maxResult);
    public long getCollectCountByUserId(long userId);
    public JSONArray getWcmDocInfo(String docid);
    public String delCollectByIds(long userId,String ids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JSONArray hotRecommendDoc(String userId, String firstResult, String maxResult);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param doctitle
     * @param docid
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String saveCollectFront(String doctitle, String docid, String userId);
}
