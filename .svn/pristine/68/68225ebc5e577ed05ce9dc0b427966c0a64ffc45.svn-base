/*
 * 文 件 名:  GroupAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.entity.TreeEntity;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IGroupService;
import com.trsnj.ums.service.impl.GroupServiceImpl;
import com.trsnj.ums.util.BaseAction;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class GroupAction extends BaseAction implements ModelDriven<UMSGroup>
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7801505209432087163L;
   // private IGroupService groupService=new GroupServiceImpl();
   // private UMSGroup group=new UMSGroup();
    private IGroupService groupService=null;
    private UMSGroup group=null;
    
    /**
     * 获取 group
     * @return 返回 group
     */
    public UMSGroup getGroup()
    {
        return group;
    }

    /**
     * 设置 group
     * @param 对group进行赋值
     */
    public void setGroup(UMSGroup group)
    {
        this.group = group;
    }

    /**
     * 获取 groupService
     * @return 返回 groupService
     */
    public IGroupService getGroupService()
    {
        return groupService;
    }

    /**
     * 设置 groupService
     * @param 对groupService进行赋值
     */
    public void setGroupService(IGroupService groupService)
    {
        this.groupService = groupService;
    }

    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSGroup getModel()
    {
        return group;
    }
    /**
     * 分页查询
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getGroups(){
        
        List<UMSGroup> groups=groupService.loadGroups((page-1)*rows, rows);
        baseEntity.setRows(groups);
        baseEntity.setTotal(groupService.getRowsCounts());
        return "baseEntityResult";
    }
    /**
     * 根据组织id查询组织
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getGroupByGroupId(){
        String groupId=request.getParameter("groupId");
        UMSGroup group=groupService.getGroupByGroupId(Long.parseLong(groupId));
        baseEntity.setObj(group);
        return "baseEntityResult";
    }
    /**
     * 添加组织
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String addGroup(){
        // 添加组织的创建者和创建时间
        group.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        group.setCruser("admin");//要更改的，改成登陆的用户
        groupService.add(group);
        return "baseEntityResult";
    }
    /**
     * 修改组织
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateGroup(){
    
        String groupId=request.getParameter("id");
        group.setGroupId(Long.parseLong(groupId));
        groupService.update(group);
        return "baseEntityResult";
    }
    /**
     * 删除组织
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delGroup(){
        String groupId=request.getParameter("id");
        UMSGroup g=new UMSGroup();
        g.setGroupId(Long.parseLong(groupId));
        groupService.deleteGroup(g);
        return "baseEntityResult";
    }
    
    /**
     * 获取组织的树形   根据递归的方法获取组织的树形
     * 在组织中如果是根组织，则parentId为0
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String initgrouptree(){
        List<TreeEntity> tree=new ArrayList<TreeEntity>();
        TreeEntity treeRoot=new TreeEntity();
        treeRoot.setId("0");
        treeRoot.setText("根组织");
        //调用递归方法
        recursive(treeRoot,0);
        tree.add(treeRoot);
        baseEntity.setTreeList(tree);
        return "baseEntityResult";
    }
    /**
     * 
     * 递归方法
     * @paream treeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public TreeEntity recursive(TreeEntity treeEntity,long groupid){
       List<UMSGroup> groups= groupService.getGroupsByPrentId(groupid);//一开始groupid为0
       List<TreeEntity> treeChildren=new ArrayList<TreeEntity>();
       for(UMSGroup group:groups){
           long gid=group.getGroupId();//获得当前对象的groupid
           TreeEntity treeChild=new TreeEntity();
           treeChild.setId(""+group.getGroupId());
           treeChild.setText(group.getGroupName());
           treeChildren.add(treeChild);
           List<UMSGroup> gs= groupService.getGroupsByPrentId(gid);
           if(gs.size()>0&&gs!=null){
               recursive(treeChild,gid);//进行递归
           }
       }
        treeEntity.setChildren(treeChildren);
        return treeEntity;
    }
    /**
     * 分页获取当前组织的用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getUsersByGroupId(){
        String groupId=request.getParameter("id");
       List<UMSUser> users=groupService.getUsersByGroupId(Long.parseLong(groupId), page, rows);
       baseEntity.setRows(users);
       baseEntity.setTotal(groupService.getUserCountsInNowGroupId(Long.parseLong(groupId)));
        return "baseEntityResult";
    }
    /**
     * 分页获取不在当前组织下的用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getUsersNotInNowGroupId(){
        String groupId=request.getParameter("id");
        List<UMSUser> users=groupService.getUsersNotInNowGroupId(Long.parseLong(groupId),page,rows);
        baseEntity.setRows(users);
        baseEntity.setTotal(groupService.getUserCountsNotInNowGroupId(Long.parseLong(groupId)));
        return "baseEntityResult";
    }
    /**
     * 将用户组添加到当前组织下
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateUserToGroupId(){
        String userIds=request.getParameter("ids");
        String groupId=request.getParameter("id");
        groupService.updateUserToGroupId(userIds,groupId);
        return "baseEntityResult";
    }
    /**
     * 删除当前组织下的用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delUserInNowGroupId(){
        String userId=request.getParameter("id");
        String groupId=request.getParameter("gid");
        groupService.delUserInNowGroupId(userId,groupId);
        
        return "baseEntityResult";
    }
    
    
}
