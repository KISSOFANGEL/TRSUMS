/*
 * 文 件 名:  IShareDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-29
 */
package com.trsnj.ums.dao;

import java.util.List;
import java.util.Map;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSShare;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-29]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IShareDao extends IBaseDao<UMSShare>
{
    public List<UMSShare> getSharesByUserId(long userId,int firstResult,int maxResult);
    public long getShareCountByUserId(long userid);
    public String delShareByIds(long userId,String ids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param first
     * @param max
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> hotShareDoc(long userid,int first, int max);
    
}
