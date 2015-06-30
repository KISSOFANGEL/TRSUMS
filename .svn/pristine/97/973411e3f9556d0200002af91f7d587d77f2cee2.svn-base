/*
 * 文 件 名:  BaseHDaoTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-13
 */
package com.trsnj.ums.basedao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import com.trsnj.ums.dao.impl.UserDaoImpl;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-13]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class BaseHDaoTest
{
    @Test
    public void testFindByCriteria(){
        
        UserDaoImpl userdao=new UserDaoImpl();
        DetachedCriteria  criteria= userdao.createDetachedCriteria(UMSUser.class);
        List<UMSUser> users= userdao.findByCriteria(criteria,0,10);
       for(UMSUser user:users){
           System.out.println(user);
       }
        
    }
}
