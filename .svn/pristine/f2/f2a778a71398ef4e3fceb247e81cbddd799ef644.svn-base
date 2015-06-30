/*
 * 文 件 名:  RoleServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.trsnj.ums.dao.IRoleDao;
import com.trsnj.ums.dao.IUMSChannelDao;
import com.trsnj.ums.dao.IUserDao;
import com.trsnj.ums.dao.impl.RoleDaoImpl;
import com.trsnj.ums.dao.impl.UMSChannelDaoImpl;
import com.trsnj.ums.dao.impl.UserDaoImpl;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.trsnj.ums.pojo.UMSChannel;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IRoleService;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class RoleServiceImpl implements IRoleService
{  
    //添加set方法，使得在spring的时候能够用到。
    //private IRoleDao roledao=new RoleDaoImpl();
    //private IUserDao userdao=new UserDaoImpl();
    //private IUMSChannelDao channeldao=new UMSChannelDaoImpl();
    private IRoleDao roledao=null;
    private IUserDao userdao=null; 
    private IUMSChannelDao channeldao=null;
    
    /**
     * 获取 channeldao
     * @return 返回 channeldao
     */
    public IUMSChannelDao getChanneldao()
    {
        return channeldao;
    }

    /**
     * 设置 channeldao
     * @param 对channeldao进行赋值
     */
    public void setChanneldao(IUMSChannelDao channeldao)
    {
        this.channeldao = channeldao;
    }

    /**
     * 获取 roledao
     * @return 返回 roledao
     */
    public IRoleDao getRoledao()
    {
        return roledao;
    }

    /**
     * 获取 userdao
     * @return 返回 userdao
     */
    public IUserDao getUserdao()
    {
        return userdao;
    }

    /**
     * 设置 roledao
     * @param 对roledao进行赋值
     */
    public void setRoledao(IRoleDao roledao)
    {
        this.roledao = roledao;
    }
    
    /**
     * 设置 userdao
     * @param 对userdao进行赋值
     */
    public void setUserdao(IUserDao userdao)
    {
        this.userdao = userdao;
    }

    /**
     * 
     * 添加角色
     * @param role
     * @see [类、类#方法、类#成员]
     */
    public void add(UMSRole role){
        // 验证角色明是否重复
        UMSRole r=roledao.getRoleByRoleName(role.getRoleName());
        if(r!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0070);//这是运行时异常，code0070表示角色名已经存在
        }
        roledao.save(role);
    }
    
    /**
     * 
     * 添加角色,并添加栏目权限
     * @param role
     * @see [类、类#方法、类#成员]
     */
    public void add(UMSRole role,String textSelect){
        // 验证角色明是否重复
        UMSRole r=roledao.getRoleByRoleName(role.getRoleName());
        if(r!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0070);//这是运行时异常，code0070表示角色名已经存在
        }
        roledao.save(role);
        //添加角色的栏目权限
        if(textSelect==null||"".equals(textSelect)){
            return ;
        }
        String [] idtextattributes=textSelect.split(",");
        for(String str:idtextattributes){
            String [] idtextattribute=str.split("@");
            UMSChannel channel=new UMSChannel();
            channel.setChnldesc(idtextattribute[1]);
            channel.setChnlid(Integer.parseInt(idtextattribute[0]));
            channel.setPath(idtextattribute[2]);
            channel.setRoleid((int)role.getRoleId());
            channeldao.save(channel);
        }
        
    }
    /**
     * 修改角色信息，根据id来修改，修改的参数也封装在role的参数里面
     * @param role
     * @see [类、类#方法、类#成员]
     */
    public void update(UMSRole role){
        
        // 验证修改后的角色明是否重复
        UMSRole r=roledao.getRoleByRoleName(role.getRoleName(),role.getRoleId());
        if(r!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0070);//这是运行时异常，code0070表示角色名已经存在
        }
        UMSRole rupdate=roledao.get(role.getRoleId());
        //角色里面修改时，不修改角色的用户
        CommonUtil.updateRole(role, rupdate);// role里面有问题
        roledao.update(rupdate);
    }
    /**
     * 修改角色的栏目权限
     * @param role
     * @param editchannel
     * @see com.trsnj.ums.service.IRoleService#updateRoleAndChannel(com.trsnj.ums.pojo.UMSRole, java.lang.String)
     */
    public void updateRoleAndChannel(UMSRole role, String editchannel){
        // 验证修改后的角色明是否重复
        UMSRole r=roledao.getRoleByRoleName(role.getRoleName(),role.getRoleId());
        if(r!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0070);//这是运行时异常，code0070表示角色名已经存在
        }
        UMSRole rupdate=roledao.get(role.getRoleId());
        //角色里面修改时，不修改角色的用户
        CommonUtil.updateRole(role, rupdate);// role里面有问题
        roledao.update(rupdate);
        // 修改channel表里的数据
        String[] idtextattributes=editchannel.split(",");
        List<UMSChannel> channels=channeldao.getChannelsByRoleId(""+role.getRoleId());
       // 这个循环是添加库里没有的栏目
        for(int i=0;i<idtextattributes.length;i++){
            String [] idtextattribute=idtextattributes[i].split("@");
            int flag=0;
            for(UMSChannel channel:channels){//当前角色拥有的栏目权限进行遍历
                if(idtextattribute[0].equals(channel.getChnlid()+"")){
                    // 选中的id与数据库中现有的id相同   同步本地库里面的数据
                    if(!idtextattribute[1].equals(channel.getChnldesc())||!idtextattribute[2].equals(channel.getPath())){
                        channel.setChnldesc(idtextattribute[1]);
                        channel.setPath(idtextattribute[2]);
                        channeldao.saveOrUpdate(channel);
                    }
                    flag=-1;
                    // 
                    break;
                }else{
                    flag++;
                    continue;
                }
            }
            if(flag!=-1){
                // 保存channel
                UMSChannel ch=new UMSChannel();
                ch.setChnldesc(idtextattribute[1]);
                ch.setChnlid(Integer.parseInt(idtextattribute[0]));
                ch.setPath(idtextattribute[2]);
                ch.setRoleid((int)role.getRoleId());
                channeldao.save(ch);
            }
        }
       // 这个循环是删除库里有的
        for(UMSChannel chan:channels){
            String channelid=chan.getChnlid()+"";
            int flag=-1;
            for(int i=0;i<idtextattributes.length;i++){
                String [] idtextattribute=idtextattributes[i].split("@");
                if(idtextattribute[0].equals(channelid)){
                    flag++;
                    continue;
                }
            }
            //从库里删除当前channel
            if(flag==-1){
                channeldao.delete(chan);
            }
        }
    }
    /**
     * 查询角色
     * @param firstResult 从哪里开始
     * @param maxResult   查询多少条
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSRole> loadRoles(int firstResult,int maxResult){
        
        List <UMSRole> roles=roledao.findByCriteria(roledao.createDetachedCriteria(UMSRole.class), firstResult, maxResult);
        return roles;
    }
    /**
     * 获得总记录数
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getRowsCounts(){
        
      return  (Long)roledao.getRowCount(roledao.createDetachedCriteria(UMSRole.class));
    }
    /**
     * 根据角色id获取总的用户数
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getUsersCountsByRoleId(long roleId){
        
        return roledao.getUsersCountsByRoleId(roleId);
    }
    /**
     * 获取不在当前角色下的用户总数
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getUsersConuntsNotInNowRoleId(long roleId){
        return roledao.getUsersConuntsNotInNowRoleId(roleId);
    }
   
    /**
     * 删除角色,这里删除角色会附带的将用户表中使用该角色的用户的roleid变成默认的角色1
     * @param role
     * @see [类、类#方法、类#成员]
     */
    public void delete(UMSRole role){
        UMSRole umsrole=roledao.get(role.getRoleId());
        // 删除角色的时候将用户的roleid字段修改成默认的角色
        List<UMSUser> users=userdao.getUsersByRoleId(role.getRoleId(),1,10);
        if(users!=null&&users.size()>0){
            //存在角色正在被用户使用,先修改用户角色为默认角色,在删除角色
            throw new AppRunTimeException(ExceptionConstants.Code_0071);//角色下存在用户
           // UMSRole baserole=new UMSRole();
           // baserole.setRoleId(1);//基本角色在创建表的时候就创建好了
           /* for(UMSUser user:users){
                user.setUmsrole(null);// 将角色变为null也可以
                userdao.saveOrUpdate(user);
            }*/
        }
        // 直接删除角色
        roledao.delete(umsrole);//删除的就是持久化实体
    }
    
    
    /**
     * 删除角色,这里删除角色会附带的将用户表中使用该角色的用户的roleid变成默认的角色1
     * @param role
     * @see [类、类#方法、类#成员]
     */
    public void deleteRoleAndChannel(UMSRole role){
        UMSRole umsrole=roledao.get(role.getRoleId());
        // 删除角色的时候将用户的roleid字段修改成默认的角色
        List<UMSUser> users=userdao.getUsersByRoleId(role.getRoleId(),1,10);
        if(users!=null&&users.size()>0){
            //存在角色正在被用户使用,先修改用户角色为默认角色,在删除角色
            throw new AppRunTimeException(ExceptionConstants.Code_0071);//角色下存在用户
           // UMSRole baserole=new UMSRole();
           // baserole.setRoleId(1);//基本角色在创建表的时候就创建好了
           /* for(UMSUser user:users){
                user.setUmsrole(null);// 将角色变为null也可以
                userdao.saveOrUpdate(user);
            }*/
        }
        // 直接删除角色
        roledao.delete(umsrole);//删除的就是持久化实体
        // 删除角色下的栏目权限
        List<UMSChannel> channels=channeldao.getChannelsByRoleId(role.getRoleId()+"");
        for(UMSChannel channel:channels){
            channeldao.delete(channel);
        }
    }
    /**
     * 获取所有的角色
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSRole> loadAllRoles(){
      return  roledao.loadAll();
    }
    
    /**
     * 重写方法
     * @param roleId
     * @return
     * @see com.trsnj.ums.service.IRoleService#getRoleByRoleId(long)
     */
    @Override
    public UMSRole getRoleByRoleId(long roleId)
    {
        return  roledao.get(roleId);
    }
    /**
     * 根据角色id获取角色以及权限信息
     * @param roleId
     * @return
     * @see com.trsnj.ums.service.IRoleService#getRoleAndChannelByRoleId(java.lang.String)
     */
    public JSONObject getRoleAndChannelByRoleId(String roleId){
        UMSRole role=roledao.get(Long.parseLong(roleId));
        List<UMSChannel> channels=channeldao.getChannelsByRoleId(roleId);
        String chnlids="";
        for(UMSChannel channel:channels){
            String chnlid=channel.getChnlid()+"";
            if(chnlids!="")
                chnlids+=",";
            chnlids+=chnlid;
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.accumulate("roleId", role.getRoleId());
        jsonObject.accumulate("roleLevel", role.getRoleLevel());
        jsonObject.accumulate("roleOrder", role.getRoleOrder());
        jsonObject.accumulate("roleType", role.getRoleType());
        jsonObject.accumulate("cruser", role.getCruser());
        jsonObject.accumulate("crutime", role.getCrutime());
        jsonObject.accumulate("roleDesc", role.getRoleDesc());
        jsonObject.accumulate("roleName", role.getRoleName());
        jsonObject.accumulate("chnlids", chnlids);
        jsonObject.accumulate("isSuccessOrfail", "SUCCESS");
        jsonObject.accumulate("message", "成功");
        return jsonObject;
    }
    /**
     * 根据角色名获得角色
     * @param roleName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSRole getRoleByRoleName(String roleName){
        return  roledao.getRoleByRoleName(roleName);
    }
    /**
     * 根据角色id获取用户
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersByRoleId(long roleId,int page,int rows){
        
        return userdao.getUsersByRoleId(roleId,page,rows);//null
    }
    /**
     * 分页获取不在当前角色下的用户
     * @param roleId
     * @param page
     * @param rows
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSUser> getUsersNotInNowRoleId(long roleId,int page,int rows){
        
        return userdao.getUsersNotInNowRoleId(roleId,page,rows);
    }
    /**
     * 批量修改用户为当前角色
     * @param userIds
     * @param roleId null
     * @see [类、类#方法、类#成员]
     */
    public void updateUserToRoleId(String userIds,String roleId){
        String [] userId=userIds.split(",");
        for(String uid:userId){
            // 修改
            roledao.updateUserToRoleId(Long.parseLong(uid), Long.parseLong(roleId));
        }
    }
    /**
     * 修改当前的用户的角色为null
     * @param userId
     * @see com.trsnj.ums.service.IRoleService#updateUserToRoleId(java.lang.String)
     */
    public void updateUserToRoleId(String userId){
       UMSUser user= userdao.get(Long.parseLong(userId));
       user.setUmsrole(null);
       userdao.saveOrUpdate(user);
    }

    /**
     * 根据角色id获得当前角色的栏目权限
     * @param roleid
     * @return
     * @see com.trsnj.ums.service.IRoleService#getchannelsbyroleid(long)
     */
    @Override
    public List<UMSChannel> getchannelsbyroleid(long roleid)
    {
        String roleId=roleid+"";
        if("".equals(roleId)||"null".equals(roleId)){
            return new ArrayList<UMSChannel>();
        }
        return channeldao.getChannelsByRoleId(roleId);
    }
}
