/*
 * 文 件 名:  GroupServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.trsnj.ums.dao.IGroupDao;
import com.trsnj.ums.dao.IUserGroupDao;
import com.trsnj.ums.dao.impl.GroupDaoImpl;
import com.trsnj.ums.dao.impl.UserGroupDaoImpl;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.pojo.UMSUserGroup;
import com.trsnj.ums.service.IGroupService;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class GroupServiceImpl implements IGroupService
{
    //private IGroupDao groupdao=new GroupDaoImpl();
    //private IUserGroupDao usergroupdao=new UserGroupDaoImpl();
    private IGroupDao groupdao=null;
    private IUserGroupDao usergroupdao=null;
    
    /**
     * 获取 groupdao
     * @return 返回 groupdao
     */
    public IGroupDao getGroupdao()
    {
        return groupdao;
    }
    /**
     * 获取 usergroupdao
     * @return 返回 usergroupdao
     */
    public IUserGroupDao getUsergroupdao()
    {
        return usergroupdao;
    }
    /**
     * 设置 usergroupdao
     * @param 对usergroupdao进行赋值
     */
    public void setUsergroupdao(IUserGroupDao usergroupdao)
    {
        this.usergroupdao = usergroupdao;
    }
    /**
     * 设置 groupdao
     * @param 对groupdao进行赋值
     */
    public void setGroupdao(IGroupDao groupdao)
    {
        this.groupdao = groupdao;
    }
    /**
     * 
     * 添加组织名
     * @param group
     * @see [类、类#方法、类#成员]
     */
    public void add(UMSGroup group){
        // 验证组织明是否重复
        UMSGroup g=groupdao.getGroupByGroupName(group.getGroupName());
        if(g!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0110);//这是运行时异常，Code_0110表示组织名已经存在
        }
        groupdao.save(group);
    }
    /**
     * 修改组织信息，会根据id来修改，修改的参数也封装在group里面
     * @param group
     * @see [类、类#方法、类#成员]
     */
    public void update(UMSGroup group){
        
        // 验证修改后的组织名是否重复
        UMSGroup g=groupdao.getGroupByGroupName(group.getGroupName(),group.getGroupId());
        if(g!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0110);//这是运行时异常，Code_0110表示组织名已经存在
        }
        UMSGroup gupdate=groupdao.get(group.getGroupId());
        CommonUtil.updateGroup(group, gupdate);
        gupdate.setUsergroups(null);//不用组织来修改用户与组织的关联关系
        groupdao.update(gupdate);
    }
    /**
     * 删除组织,如果有子组织不能删除
     * @param group
     * @see [类、类#方法、类#成员]
     */
    public void delete(UMSGroup group){
        if(groupdao.getGroupsByParentId(group.getGroupId())!=null&&groupdao.getGroupsByParentId(group.getGroupId()).size()>0){
            throw new AppRunTimeException(ExceptionConstants.Code_0111);//这是运行时异常，Code_0111表示组织存在子组织
        }
        String hql="from UMSGroup g where g.groupId= "+group.getGroupId();
        List<UMSGroup> groups=groupdao.find(hql);
        Set<UMSUserGroup> usergroup=null;
        if(groups!=null||groups.size()!=0){
        UMSGroup g=groups.get(0);
         usergroup=g.getUsergroups();//报错了
         Iterator<UMSUserGroup> it=usergroup.iterator();
         while(it.hasNext()){
             usergroupdao.delete(it.next());//删除用户与组织的中间表
         }
        }
        UMSGroup g=groupdao.get(group.getGroupId());
        groupdao.delete(g);// 删除组织
    }
    /**
     * 删除组织,如果有子组织不能删除,如果有用户绑定组织也不能删除
     * @param group
     * @see [类、类#方法、类#成员]
     */
    public void deleteGroup(UMSGroup group){
        if(groupdao.getGroupsByParentId(group.getGroupId())!=null&&groupdao.getGroupsByParentId(group.getGroupId()).size()>0){
            throw new AppRunTimeException(ExceptionConstants.Code_0111);//这是运行时异常，Code_0111表示组织存在子组织
        }
        String hql="from UMSGroup g where g.groupId= "+group.getGroupId();
        List<UMSGroup> groups=groupdao.find(hql);
        Set<UMSUserGroup> usergroup=null;
        if(groups!=null||groups.size()!=0){
        UMSGroup g=groups.get(0);
         usergroup=g.getUsergroups();//报错了
         if(usergroup!=null&&usergroup.size()>0){
             throw new AppRunTimeException(ExceptionConstants.Code_0112);//组织下存在用户，不能删除
         }
        }
        UMSGroup g=groupdao.get(group.getGroupId());
        groupdao.delete(g);// 删除组织
    }
    
    /**
     * 查询组织
     * @param firstResult 从哪里开始
     * @param maxResult   查询多少条
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSGroup> loadGroups(int firstResult,int maxResult){
        
        List <UMSGroup> groups=groupdao.findByCriteria(groupdao.createDetachedCriteria(UMSGroup.class), firstResult, maxResult);
        return groups;
    }
    
    /**
     * 获得总记录数
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getRowsCounts(){
        
      return  (Long)groupdao.getRowCount(groupdao.createDetachedCriteria(UMSGroup.class));
    }
    /**
     * 获取当前组织下的用户总数
     * @param groupId
     * @return
     * @see com.trsnj.ums.service.IGroupService#getUserCountsInNowGroupId(long)
     */
    public long getUserCountsInNowGroupId(long groupId){
        
        return groupdao.getUserCountsInNowGroupId(groupId);
    }
    /**
     * 获取不在当前组织下的用户总数
     * @param groupId
     * @return
     * @see com.trsnj.ums.service.IGroupService#getUserCountsNotInNowGroupId(long)
     */
    public long getUserCountsNotInNowGroupId(long groupId){
        return groupdao.getUserCountsNotInNowGroupId(groupId);
    }
    /**
     * 根据用户id来获取组织
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSGroup> getGroupsByUserId(long userId){
        List<UMSGroup> groups=null;
       groups= groupdao.getGroupsByUserId(userId);
        
        return groups;
    }
    /**
     * 根据prentId来获取组织
     * @param prentId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSGroup> getGroupsByPrentId(long parentId){
        List<UMSGroup> groups=null;
        groups= groupdao.getGroupsByParentId(parentId);
        return groups;
    }
    /**
     * 重写方法
     * @param groupId
     * @return
     * @see com.trsnj.ums.service.IGroupService#getGroupByGroupId(long)
     */
    @Override
    public UMSGroup getGroupByGroupId(long groupId)
    {
        return groupdao.get(groupId);
    }
    /**
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersByGroupId(long groupId,int page,int rows){
      
        return groupdao.getUsersByGroupId(groupId, page, rows);
    }
    /**
     * 分页获取不在当前组织下的用户
     * @param groupId
     * @param page
     * @param rows
     * @return
     * @see com.trsnj.ums.service.IGroupService#getUsersNotInNowGroupId(long, int, int)
     */
    public List<UMSUser> getUsersNotInNowGroupId(long groupId, int page, int rows){
        return groupdao.getUsersNotInNowGroupId(groupId,page,rows);
    }
    /**
     * 添加用户组到当前组织下
     * @param userIds
     * @param groupId
     * @see com.trsnj.ums.service.IGroupService#updateUserToGroupId(java.lang.String, java.lang.String)
     */
    public void updateUserToGroupId(String userIds, String groupId){
        String[] userId=userIds.split(",");
        for(String u:userId){
            groupdao.updateUserToGroupId(Long.parseLong(u),Long.parseLong(groupId));
        }
        
    }
    /**
     * 删除当前组织下的用户
     * @param userId
     * @param groupId
     * @see com.trsnj.ums.service.IGroupService#delUserInNowGroupId(java.lang.String, java.lang.String)
     */
    public void delUserInNowGroupId(String userId, String groupId){
        
        groupdao.delUserInNowGroupId(Long.parseLong(userId),Long.parseLong(groupId));
    }
    
    
}
