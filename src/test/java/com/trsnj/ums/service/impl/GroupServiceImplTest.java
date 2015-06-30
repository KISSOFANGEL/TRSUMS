/*
 * 文 件 名:  GroupServiceImplTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums.service.impl;

import org.junit.Test;

import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.service.IGroupService;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class GroupServiceImplTest
{
    IGroupService groupservice=new GroupServiceImpl();
    
    @Test
    public void testAdd(){//测试通过
        UMSGroup group = new UMSGroup();
        group.setCruser("dzy");
        group.setGroupDesc("组织1");
        group.setGroupName("组织1");
        group.setGroupOrder(1);
        group.setParentId(0);//根组织的parentId 为0
        UMSGroup group1 = new UMSGroup();
        group1.setCruser("dzy");
        group1.setGroupDesc("组织1");
        group1.setGroupName("组织2");
        group1.setGroupOrder(2);
        group1.setParentId(1);
        groupservice.add(group);
        groupservice.add(group1);
    }
    @Test
    public void testDelete(){//测试通过
        UMSGroup group=new UMSGroup();
        group.setGroupId(1);
        UMSGroup group1=new UMSGroup();
        group1.setGroupId(2);
       groupservice.delete(group);//存在子组织的时候不能删除
       groupservice.delete(group1);
    }
    @Test
    public void testUpdate(){//测试通过
        
        UMSGroup group=new UMSGroup();
        group.setGroupId(3);
        group.setGroupName("组织update");
        groupservice.update(group);
    }
    
    
}
