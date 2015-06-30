/*
 * 文 件 名:  IUMSCompanyService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.service;

import com.trsnj.ums.pojo.UMSCompanyInfo;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUMSCompanyService
{
    public UMSCompanyInfo getComInfoByUserId(long userId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param companyInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSCompanyInfo updateCurrentCompanyInfo(UMSCompanyInfo companyInfo,String userId);
}
