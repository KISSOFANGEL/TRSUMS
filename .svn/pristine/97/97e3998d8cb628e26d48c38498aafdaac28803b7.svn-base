/*
 * 文 件 名:  RoleServiceImplTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.service.impl;

import org.junit.Test;

import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.service.IRoleService;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class RoleServiceImplTest
{
    IRoleService roleservice=new RoleServiceImpl();
    @Test
    public void testDelete(){
       
        try
        {
            UMSRole role=new UMSRole();
            role.setRoleId(3);
            roleservice.delete(role);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    @Test
    public void testAdd(){
        UMSRole role=new UMSRole();
        role.setRoleName("角色1");
        role.setRoleOrder(1);
        role.setCruser("dzy");
        UMSRole role1=new UMSRole();
        role1.setRoleName("角色2");
        role1.setRoleOrder(2);
        role1.setCruser("dzy");
        roleservice.add(role);
        roleservice.add(role1);
    }
    
}
