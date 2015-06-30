package com.trsnj.ums.dao.impl;

import org.junit.Test;

import com.trsnj.ums.dao.IRoleDao;
import com.trsnj.ums.pojo.UMSRole;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class RoleDaoImplTest
{
    IRoleDao roledao=new RoleDaoImpl();
    @Test
    public void testAdd(){
        UMSRole role=new UMSRole();
        role.setRoleName("roletest2");
        role.setRoleDesc("t");
        role.setRoleOrder(2);
        roledao.save(role);
    }
    @Test
    public void testGet(){
        System.out.println(roledao.get(1).getRoleName());//????????
    }
    @Test
    public void testUpdateUserToRoleId(){
        roledao.updateUserToRoleId(7, 2);
        
    }
}
