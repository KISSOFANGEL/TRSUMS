/*
 * 文 件 名:  UserServiceImplTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.pojo.UMSUserGroup;
import com.trsnj.ums.service.IGroupService;
import com.trsnj.ums.service.IRoleService;
import com.trsnj.ums.service.IUserService;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UserServiceImplTest
{
    IUserService userservice=new UserServiceImpl();
    IRoleService roleservice=new RoleServiceImpl();
    IGroupService groupservice=new GroupServiceImpl();
    @Test
    public void testAdd(){//测试通过
        List<UMSRole> role=roleservice.loadRoles(0, 2);
        UMSUser user=new UMSUser();
        user.setAddress("中环大厦");
        user.setEmail("602717968@qq.com");// email 必填
        user.setMobile("15205181213");
        user.setPassWord("123");
        user.setStatus(1);
        user.setTel("025-12345678");
        user.setUserName("张三");
        user.setUserType(1);
       user.setUmsrole(role.get(1));
       
       UMSUser user1=new UMSUser();
       user1.setAddress("中环大厦");
       user1.setEmail("1002717968@qq.com");// email 必填  且不能重复
       user1.setMobile("15205181213");
       user1.setPassWord("123");
       user1.setStatus(1);
       user1.setTel("025-12345678");
       user1.setUserName("张三4");
       user1.setUserType(1);
       user1.setUmsrole(role.get(1));// 
       UMSUserGroup usergroup=new UMSUserGroup();
       usergroup.setGroup(groupservice.loadGroups(0, 2).get(1));
       Set<UMSUserGroup> ug=new HashSet<UMSUserGroup>();
       ug.add(usergroup);
       user1.setUmsusergroups(ug);
       userservice.add(user1);
       
       
       
    }
    @Test
    public void testDelete(){// 测试通过
        
      UMSUser user=new UMSUser();
      user.setUserId(3);
        userservice.delete(user);
        
    }
    
    @Test
    public void testUpdate(){//测试通过
        
      List<UMSUser> users= userservice.loadUsers(0, 5);
       UMSUser user= users.get(1);
     Set<UMSUserGroup> set= user.getUmsusergroups();//得到组织中间表
        user.setUserName("张三1update");
        for (Iterator iterator = set.iterator(); iterator.hasNext();)
        {
            UMSUserGroup umsUserGroup = (UMSUserGroup)iterator.next();
            UMSGroup group=new UMSGroup();
            group.setGroupId(3);
            umsUserGroup.setGroup(group);
        }
        userservice.update(user);
    }
    @Test
    public void testUpdate1(){
        UMSRole role =new UMSRole();
        role.setRoleId(1);
        
        UMSUser user=new UMSUser();
        user.setUserName("张三update");
        user.setUserId(1);
        user.setMobile("15215000000");
        user.setUmsrole(role);
        user.setPassWord("1234");
        Set<UMSUserGroup> umsgus=new HashSet<UMSUserGroup>();
        UMSUserGroup umsgu=new UMSUserGroup();
        UMSGroup group=new UMSGroup();
        group.setGroupId(5);
        UMSUserGroup umsgu1=new UMSUserGroup();
        UMSGroup group1=new UMSGroup();
        group1.setGroupId(4);
        umsgu.setGroup(group);
        umsgu1.setGroup(group1);
        umsgus.add(umsgu);
        umsgus.add(umsgu1);
        user.setUmsusergroups(umsgus);
        userservice.update(user);
        
        
        
        
        
        
        
        
        
    }
    
    
    @Test
    public void testLoadUsers(){//测试通过，注意在转化json格式的时候，防止因为实体对象下的属性因懒加载而不能查询的时候，要过滤实体里面的某些对象
        List<UMSUser> users= userservice.loadUsers(0, 10);
        for(UMSUser user:users){
            System.out.println(user.getUserName());
            System.out.println(user);// toString方法容易因为懒加载造成错误
        }
    }
    
}
