package com.trsnj.ums.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.pojo.UMSChannel;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IRoleService;
import com.trsnj.ums.service.impl.RoleServiceImpl;
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
public class RoleAction extends BaseAction implements ModelDriven<UMSRole>
{
    //private IRoleService roleService=new RoleServiceImpl();
    //private UMSRole role =new UMSRole();
    private IRoleService roleService=null;
    private UMSRole role =null;
    
    /**
     * 获取 role
     * @return 返回 role
     */
    public UMSRole getRole()
    {
        return role;
    }

    /**
     * 设置 role
     * @param 对role进行赋值
     */
    public void setRole(UMSRole role)
    {
        this.role = role;
    }

    /**
     * 获取 roleService
     * @return 返回 roleService
     */
    public IRoleService getRoleService()
    {
        return roleService;
    }

    /**
     * 设置 roleService
     * @param 对roleService进行赋值
     */
    public void setRoleService(IRoleService roleService)
    {
        this.roleService = roleService;
    }

    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSRole getModel()
    {
        return role;
    }
    /**
     * 获取分页数据
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getRoles(){
        List<UMSRole> roles=roleService.loadRoles((page-1)*rows, rows);
        baseEntity.setRows(roles);
        baseEntity.setTotal(roleService.getRowsCounts());
        return "baseEntityResult";
    }
    /**
     * 根据角色的id获取角色信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getRoleByRoleId(){
        String roleId = request.getParameter("roleId");
        UMSRole role=roleService.getRoleByRoleId(Long.parseLong(roleId));
        baseEntity.setObj(role);
        return "baseEntityResult";
    }
    
    /**
     * 根据角色的id获取角色信息与模块信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void getRoleAndChannelByRoleId(){
        String roleId = request.getParameter("roleId");
       
        JSONObject jsonObject=roleService.getRoleAndChannelByRoleId(roleId);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 添加角色
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String addRole(){
        role.setCruser("admin");// 默认是谁创建的角色，可以改成session里面的用户名来创建
        role.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        roleService.add(role);
        return "baseEntityResult";
    }
    /**
     * 添加带有角色栏目权限的
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String addRoleChannel(){
        role.setCruser("admin");// 默认是谁创建的角色，可以改成session里面的用户名来创建
        role.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        String textSelect=request.getParameter("addTextSelect");
        roleService.add(role,textSelect);
        return "baseEntityResult";
    }
    /**
     * 修改角色
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public String updateRole(){
        String roleId=request.getParameter("id");
        
        role.setRoleId(Long.parseLong(roleId));
        roleService.update(role);
        return "baseEntityResult";
    }
    
    /**
     * 修改角色
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public String updateRoleAndChannel(){
        String roleId=request.getParameter("id");
        String editchannel=request.getParameter("editTextSelect");
        role.setRoleId(Long.parseLong(roleId));
       roleService.updateRoleAndChannel(role,editchannel);
        return "baseEntityResult";
    }
    /**
     * 删除角色
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delRole(){
        String roleId=request.getParameter("id");
        UMSRole r=new UMSRole();
        r.setRoleId(Long.parseLong(roleId));
        roleService.delete(r);
        return "baseEntityResult";
    }
    /**
     * 删除角色
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delRoleAndChannel(){
        String roleId=request.getParameter("id");
        UMSRole r=new UMSRole();
        r.setRoleId(Long.parseLong(roleId));
        roleService.deleteRoleAndChannel(r);
        return "baseEntityResult";
    }
    /**
     * 批量删除
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String batchDelRole(){
        String ids = request.getParameter("ids");
        String ids_yz = request.getParameter("ids_yz");
        String [] roleids=ids.split(",");
        for(String roleid:roleids){
            UMSRole r=new UMSRole();
            r.setRoleId(Long.parseLong(roleid));
            roleService.delete(r);
        }
        return "baseEntityResult";
    }
    
    /**
     * 批量删除角色以及其栏目权限
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String batchDelRoleAndChannel(){
        String ids = request.getParameter("ids");
        String ids_yz = request.getParameter("ids_yz");
        String [] roleids=ids.split(",");
        for(String roleid:roleids){
            UMSRole r=new UMSRole();
            r.setRoleId(Long.parseLong(roleid));
            roleService.deleteRoleAndChannel(r);
        }
        return "baseEntityResult";
    }
    /**
     * 根据角色id获得用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getUsersByRoleId(){
        String roleId=request.getParameter("id");
       List<UMSUser> users= roleService.getUsersByRoleId(Long.parseLong(roleId),page,rows);
       baseEntity.setRows(users);
       baseEntity.setTotal(roleService.getUsersCountsByRoleId(Long.parseLong(roleId)));
        return "baseEntityResult";
    }
    /**
     * 获取不在当前角色下的用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getUsersNotInNowRoleId(){
        
        String roleId=request.getParameter("id");
        List<UMSUser> users= roleService.getUsersNotInNowRoleId(Long.parseLong(roleId),page,rows);
        baseEntity.setRows(users);
        baseEntity.setTotal(roleService.getUsersConuntsNotInNowRoleId(Long.parseLong(roleId)));
        return "baseEntityResult";
    }
    /**
     * 修改用户为当前的角色
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateUserToRoleId(){
        String userIds=request.getParameter("ids");
        String roleId=request.getParameter("id");
        roleService.updateUserToRoleId(userIds, roleId);
        return "baseEntityResult";
    }
    /**
     * 删除当前角色下的用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delUserInNowRole(){
        String userId=request.getParameter("id");
        String roleId=request.getParameter("rid");//这个角色是当前角色下的用户
       // roleService.updateUserToRoleId(userId,null);
        roleService.updateUserToRoleId(userId);// 设置当前用户的角色为null
        return "baseEntityResult";
    }
    /**
     * 根据用户id获得当前用户的栏目权限
     * @see [类、类#方法、类#成员]
     */
    public void getchannelsbyuserid(){
       JSONObject jsonObject=new JSONObject();
       long roleid=(Long)session.get("roleId");//当前用户登陆后在session里面存的roleid
       List<UMSChannel> channels=roleService.getchannelsbyroleid(roleid);
       JSONArray jsonArray=JSONArray.fromObject(channels);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=null;
        try {
            out = response.getWriter();//这个抛异常就一定是null
            jsonObject.put("rows", jsonArray);
            jsonObject.put("isSuccessOrfail", "SUCCESS");
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
}
