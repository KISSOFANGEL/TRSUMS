/*
 * 文 件 名:  IUMSCollectDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao;

import java.util.List;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSCollect;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUMSCollectDao extends IBaseDao<UMSCollect>
{
    public  List<UMSCollect> getCollectByUserId(long userId);
    public  List<UMSCollect> getCollectByUserId(long userId,int firstResult,int maxResult);
    public long getCollectCountByUserId(long userId);
    public String delCollectByIds(long userId,String ids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userid
     * @param first
     * @param max
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<String> hotRecommendDoc(long userid, int first, int max);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param docid
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSCollect getCollectByUserIdAndDocid(String docid, String userId);
}
