/*
 * 文 件 名:  GroupDaoImplTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-2
 */
package com.trsnj.ums.dao.impl;

import java.util.List;

import org.junit.Test;

import com.trsnj.ums.dao.IGroupDao;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-2]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class GroupDaoImplTest
{
    private IGroupDao groupdao=new GroupDaoImpl();
    @Test
    public void testGetGroupsByUserId(){
      List<UMSGroup> list=  groupdao.getGroupsByUserId(4);
      for(UMSGroup group:list)
      {
          System.out.println(group.getGroupName());
      }
    }
    @Test
    public void testUpdateUserToGroupId(){
        groupdao.updateUserToGroupId(10, 8);
        
    }
    @Test
    public void testGetUsersByGroupId(){
        List<UMSUser> users=groupdao.getUsersByGroupId(2, 1, 10);
        for(UMSUser user:users){
            System.out.println(user);
        }
    }
    
}
