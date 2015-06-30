/*
 * 文 件 名:  UserActionTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-2
 */
package com.trsnj.ums.action;

import java.util.List;

import org.junit.Test;

import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-2]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserActionTest
{
    UserAction useraction=new UserAction();
    @Test
    public void testGetStrGroupNameByUserId(){
        
       String s= useraction.getStrGroupNameByUserId();
       System.out.println(s+"dddd");
    }
    @Test
    public void testGetUsers(){
      //  String s=useraction.getUsers(1, 10);
    }
}
