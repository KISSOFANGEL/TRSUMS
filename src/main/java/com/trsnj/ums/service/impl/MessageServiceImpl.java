/*
 * 文 件 名:  MessageServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-24
 */
package com.trsnj.ums.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.trsnj.ums.dao.IRoleDao;
import com.trsnj.ums.dao.IUMSMessageDao;
import com.trsnj.ums.dao.IUserDao;
import com.trsnj.ums.dao.impl.RoleDaoImpl;
import com.trsnj.ums.dao.impl.UMSMessageDaoImpl;
import com.trsnj.ums.dao.impl.UserDaoImpl;
import com.trsnj.ums.pojo.UMSMessage;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IMessageService;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class MessageServiceImpl implements IMessageService
{
   // private IUMSMessageDao messagedao=new UMSMessageDaoImpl();
   // private IRoleDao roledao=new RoleDaoImpl();
   // private IUserDao userdao=new UserDaoImpl();
    private IUMSMessageDao messagedao=null;
    private IRoleDao roledao=null;
    private IUserDao userdao=null;
    
    /**
     * 获取 messagedao
     * @return 返回 messagedao
     */
    public IUMSMessageDao getMessagedao()
    {
        return messagedao;
    }
    /**
     * 设置 messagedao
     * @param 对messagedao进行赋值
     */
    public void setMessagedao(IUMSMessageDao messagedao)
    {
        this.messagedao = messagedao;
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
     * 设置 roledao
     * @param 对roledao进行赋值
     */
    public void setRoledao(IRoleDao roledao)
    {
        this.roledao = roledao;
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
     * 设置 userdao
     * @param 对userdao进行赋值
     */
    public void setUserdao(IUserDao userdao)
    {
        this.userdao = userdao;
    }
    /**
     * 分页获取当前用户的系统信息
     * @param userId
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSMessage> getMessageByUserId(long userId,int firstResult,int maxResult,int status){
        
        return messagedao.getMessageByUserId(userId, firstResult, maxResult,status);
    }
    /**
     * 分页获取向系统管理员求助的信息
     * @param userId
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSMessage> getSysMessage(long userId,int firstResult,int maxResult,int status){
        
        return messagedao.getSysMessage(userId, firstResult, maxResult,status);
    }
	@Override
	public String delMessageByUserIds(long userId, String ids) {
		// TODO Auto-generated method stub
		return messagedao.delMessageByUserIds( userId,  ids);
	}
	@Override
	public String delHelpMessageByUserIds(long userId, String ids) {
		// TODO Auto-generated method stub
		return messagedao.delHelpMessageByUserIds( userId,  ids);
	}
	
	@Override
	public List<UMSMessage> getHelpMessageByUserId(long userId,int firstResult,int maxResult,int status) {
		
		return messagedao.getHelpMessageByUserId(userId, firstResult, maxResult,status);
	}
	/**
	 * 获得当前用户的信息总数
	 * @param userId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    public long getMessageCountByUserId(long userId,int status){
        
        return messagedao.getMessageCountByUserId(userId,status);
    }
    /**
     * 获取向系统管理员求助的信息总数
     * @param userId
     * @param status
     * @return
     * @see com.trsnj.ums.service.IMessageService#getSysMessageCount(long, int)
     */
    public long getSysMessageCount(long userId, int status){
        
        return messagedao.getSysMessageCount(userId,status);
    }
	/**
	 * 获得当前用户求助信息的总数
	 * @param userId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    public long getHelpMessageCountByUserId(long userId,int status){
        return messagedao.getHelpMessageCountByUserId(userId,status);
    }
    /**
     * 添加用户求助信息
     * @param mescon
     * @param userId
     * @param adminid
     * @see com.trsnj.ums.service.IMessageService#add(java.lang.String, java.lang.String, java.lang.String)
     */
    public void add(String mescon,String mestitle, String userId, String adminid){
        UMSMessage message=new UMSMessage();
        message.setMesauthor(Long.parseLong(userId));
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(adminid));
        message.setUser(user);
        message.setMescon(mescon);
        message.setMestitle(mestitle);
        message.setMesstatus(1);//1表示前台已读显示
        message.setMeslev(0);
        message.setMestime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
        message.setMestype(0);
        messagedao.save(message);
    }
    /**
     * 向管理员角色添加求助信息
     * @param mescon
     * @param userId
     * @param adminid
     * @see com.trsnj.ums.service.IMessageService#add(java.lang.String, java.lang.String, java.lang.String)
     */
    public void addMesToRole(String mescon,String mestitle, String userId, long adminid){
        UMSMessage message=new UMSMessage();
        message.setMesauthor(Long.parseLong(userId));
        /*UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(adminid));
        message.setUser(user);*///信息接受者不赋值
        message.setReceprole(adminid);
        message.setMestype(2);//信息类型为2表示求助信息
        message.setMescon(mescon);
        message.setMestitle(mestitle);
        message.setMesstatus(1);//1表示前台已读显示
        message.setMeslev(0);
        message.setMestime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd"));
        messagedao.save(message);
    }
    /**
     * 当前系统管理员向某个角色发送系统信息
     * @param mescon
     * @param roleId
     * @return 0 是所有注册用户 1 是普通用户 2 是认证用户 3 是vip用户
     * @see com.trsnj.ums.service.IMessageService#addSystemMes(java.lang.String, java.lang.String)
     */
    public String addSystemMes(String mestitle,String mescon,String userId, String roleId){
        // 根据roleId查询所有的用户总数，在根据用户总数来近些年分页插入数据
        int count=10;
       long userCounts= roledao.getUsersCountsByRoleId(Long.parseLong(roleId));
       int pagecounts=(int)(userCounts%count!=0?userCounts/count+1:userCounts/count);
       for(int i=1;i<pagecounts+1;i++){
           List<UMSUser> users=userdao.getUsersByRoleId(Long.parseLong(roleId), i, count);
           // 向mes信息表中出入信息
           for(UMSUser user:users){
               // 第一个userid是发给谁，第二个是创建者
               try{
                   messagedao.addSystemMes(mestitle,mescon,user.getUserId(),Long.parseLong(userId));
               }catch (Exception e){
                   //日志里添加操作的信息，向user.getUserName发送系统信息失败？？？
                   continue;
               }
           }
       }
       
       return "SUCCESS";
    }
    /**
     * 根据用户id获得用户名称
     * @param userId
     * @return
     * @see com.trsnj.ums.service.IMessageService#getUserNameByUserId(long)
     */
    public String getUserNameByUserId(long userId){
        UMSUser user=userdao.get(userId);
        return user.getUserName();
    }
    
    /**
     * 回复求助信息
     * @param replyCon
     * @param ids
     * @param userId  //求助信息的接受者的用户id
     * @return
     * @see com.trsnj.ums.service.IMessageService#replyMes(java.lang.String, java.lang.String, java.lang.String)
     */
    public String replyMes(String replyCon, String ids, String userId){
        
        messagedao.replyMes(replyCon,ids,Long.parseLong(userId));
        return "SUCCESS";
    }
    /**
     * 用户完成个人信息后，向管理员发送的认证请求信息（与用户向管理员发送求助信息类似）
     * @param userId  个人用户
     * @see com.trsnj.ums.service.IMessageService#addAuthentication(java.lang.String)
     */
    public void addAuthentication(String userId){
        //必须根据当前用户id and mestype=1 去查信息是否存在
        UMSUser user=userdao.get(Long.parseLong(userId));
        if(!"普通用户".equals(user.getUmsrole().getRoleName())){//普通用户才有认证的可能
            return ;
        }
        //根据角色名‘系统管理员’来查询其roleid
        UMSRole role= roledao.getRoleByRoleName("系统管理员");
        //根据当前用户，查询认证信息里是否有自己的认证信息。
       UMSMessage mesauthority= messagedao.getMessageByCreuserid(Long.parseLong(userId),role.getRoleId());
       if(mesauthority!=null&&(mesauthority.getReplytime()==null||"".equals(mesauthority.getReplytime())))
       {
           return ;
       }
       UMSMessage message=new UMSMessage();
       message.setMesauthor(Long.parseLong(userId));
       message.setMescon("请求认证");
       message.setMeslev(0);
       message.setMesstatus(0);
       message.setMestype(1);//这里必须是1
       message.setReceprole(role.getRoleId());//发送给某一个角色的id，没有接受者用户的id了
       message.setMestime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
       messagedao.save(message);
       /* // 获得‘系统管理员’角色下的用户
        List<UMSUser> users=userdao.getUserByRoleName("系统管理员");
        for(UMSUser u:users){
            UMSMessage message=new UMSMessage();
              message.setMesauthor(Long.parseLong(userId));
              message.setMescon("请求认证");
              message.setMeslev(0);
              message.setMesstatus(0);
              message.setMestype(1);//这里必须是1
              message.setMestime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
              message.setUser(u);
              messagedao.save(message);
        }*/
        
    }
    /**
     * 分页得到认证管理
     * @param userId
     * @return
     * @see com.trsnj.ums.service.IMessageService#getAuthenticationUsers(java.lang.String)
     */
    public List<Map> getAuthenticationUsers(String roleId,int firstResult,int maxResult){
         
       List<Map> map= messagedao.getAuthenticationUsers(roleId,firstResult,maxResult);
        
        return map;
    }
    /**
     * 获得请求认证用户的总数
     * @param userId
     * @return
     * @see com.trsnj.ums.service.IMessageService#getAuthenticationUsersCounts(long)
     */
    public long getAuthenticationUsersCounts(long roleid){
        
        return messagedao.getAuthenticationUsersCounts(roleid);
    }
    /**
     * 标记已读信息
     * @param messageids
     * @return
     * @see com.trsnj.ums.service.IMessageService#readMessage(java.lang.String)
     */
    public String readMessage(String messageids){
        messagedao.readMessage(messageids);
        return "SUCCESS";  
    }
    /**
     * 重写方法
     * @param ids
     * @return
     * @see com.trsnj.ums.service.IMessageService#batchDelAuthority(java.lang.String)
     */
    @Override
    public String batchDelAuthority(String ids)
    {
        return messagedao.batchDelAuthority(ids);
    }
    /**
     * 获得系统管理员收到的认证信息且没回复的数目。
     * @return
     * @see com.trsnj.ums.service.IMessageService#getnewAuthoritynum()
     */
    @Override
    public long getnewAuthoritynum()
    {
        UMSRole role=roledao.getRoleByRoleName("系统管理员");
        long roleid=role.getRoleId();
        
        return messagedao.getnewAuthoritynum(roleid);
    }
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.service.IMessageService#getnewHelpnum()
     */
    @Override
    public long getnewHelpnum()
    {
        return messagedao.getnewHelpnum();
    }
    /**
     * 获得系统消息未读的
     * @param userid
     * @return
     * @see com.trsnj.ums.service.IMessageService#getnewsysmesnum(java.lang.String)
     */
    @Override
    public long getnewsysmesnum(String userid)
    {
        long userId=Long.parseLong(userid);
        return messagedao.getnewsysmesnum(userId);
    }
    /**
     * 获得求组信息新回复的数目
     * @param userid
     * @return
     * @see com.trsnj.ums.service.IMessageService#getnewreturnhelpnum(java.lang.String)
     */
    @Override
    public long getnewreturnhelpnum(String userid)
    {
        long userId=Long.parseLong(userid);
        return messagedao.getnewreturnhelpnum(userId);
    }
}
