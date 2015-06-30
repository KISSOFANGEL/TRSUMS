/*
 * 文 件 名:  UserDaoImplTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.dao.impl;

import java.util.List;

import org.junit.Test;

import com.trsnj.ums.dao.IUserDao;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserDaoImplTest
{
    IUserDao userdao=new UserDaoImpl();
    @Test
	public void testFind(){

//		String result = udi.getAllUsers();
//		System.out.println(result);
	}
    @Test
    public void testAdd(){
        UMSRole role =new UMSRole();
        role.setRoleId(2);
        for(int i=25;i<26;i++){
            UMSUser u=new UMSUser();
            u.setUserName("测试"+i);
            u.setPassWord("123456");
            u.setStatus(1);
            u.setUserType(3);
            u.setQq("602717968");
            u.setEmail("602717968@qq.com"+i);
            u.setMobile("15205181213");
            u.setUmsrole(null);
            userdao.save(u);
        }
    }
    @Test
    public void testFindByCriteria(){
      List <UMSUser> users=  userdao.findByCriteria(userdao.createDetachedCriteria(UMSUser.class), 0, 10);
        for(UMSUser u:users){
            System.out.println(u.getUserName());
        }
    }
}
