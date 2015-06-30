/*
 * 文 件 名:  CompanyInfoDaoImplTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao.impl;

import org.junit.Test;

import com.trsnj.ums.dao.IUMSCompanyInfoDao;
import com.trsnj.ums.pojo.UMSCompanyInfo;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CompanyInfoDaoImplTest
{
    private IUMSCompanyInfoDao companydao=new CompanyInfoDaoImpl();
    @Test
    public void testGetComInfoByUserId(){
        
       UMSCompanyInfo company= companydao.getComInfoByUserId(1);
       System.out.println(company.getCompanyAddress());
    }
}
