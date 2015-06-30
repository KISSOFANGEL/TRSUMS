/*
 * 文 件 名:  LoginService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-12
 */
package com.trsnj.ums.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.trsnj.ums.dao.IGroupDao;
import com.trsnj.ums.dao.IUMSChannelDao;
import com.trsnj.ums.dao.IUserDao;
import com.trsnj.ums.dao.IUserGroupDao;
import com.trsnj.ums.dao.impl.GroupDaoImpl;
import com.trsnj.ums.dao.impl.UserDaoImpl;
import com.trsnj.ums.dao.impl.UserGroupDaoImpl;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.trsnj.ums.pojo.UMSChannel;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.pojo.UMSUserGroup;
import com.trsnj.ums.service.ILoginService;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.DESUtil;
import com.trsnj.ums.util.MD5Util;
import com.trsnj.ums.util.TRSMailUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class LoginService implements ILoginService
{
    //private IUserDao userdao=new UserDaoImpl();
   // private IGroupDao groupdao=new GroupDaoImpl();
   // private IUserGroupDao usergroupdao=new UserGroupDaoImpl();
    private IUserDao userdao=null;
    private IGroupDao groupdao=null;
    private IUserGroupDao usergroupdao=null;
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
     * 获取 groupdao
     * @return 返回 groupdao
     */
    public IGroupDao getGroupdao()
    {
        return groupdao;
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
     * 注册新用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void register(UMSUser user){
        // 验证用户名是否重复
        UMSUser u=userdao.getUserByUserName(user.getUserName());
        if(u!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0090);//这是运行时异常，Code_0090表示用户名已经存在
        }
        // 验证邮箱是否被注册了
        UMSUser ue=userdao.getUserByEmail(user.getEmail());
        if(ue!=null){
            throw new AppRunTimeException(ExceptionConstants.Code_0091);//改邮箱已经被注册
        }
        // 给用户名的密码加密
        String password=user.getPassWord();
        password=MD5Util.getSecretStr(password);
        user.setPassWord(password);
        Set<UMSUserGroup> usergroups=user.getUmsusergroups();      
        if(usergroups==null||usergroups.size()==0) {
            userdao.save(user);
        }
        else{
            user.setUmsusergroups(null);// 防止关联保存中间表数据
            userdao.save(user);//这个在保存的时候也关联了
            Iterator<UMSUserGroup> it=usergroups.iterator();
            while(it.hasNext()){
                UMSUserGroup usergroup=it.next();
              UMSGroup group= groupdao.get(usergroup.getGroup().getGroupId());
              usergroup.setGroup(group);
              usergroup.setUser(user);
              // 保存创建时间，创建用户，可以从session里面去取出来
              usergroup.setCruser("admin");// 从session里面去拿
              usergroup.setCrutime("crutime");
              usergroupdao.save(usergroup);
            }
        }
        // 向用户发送email  这里要加事物控制，防止用户插入了，邮箱没发送成功
        String timeStamp=user.getCrutime();
        Date time;
        try
        {
            time = CommonUtil.getTime(timeStamp, "yyyy-MM-dd HH:mm");
            timeStamp=time.getTime()+"";//获得注册的时间撮
            StringBuffer sb=new StringBuffer();
            // des进行加密
            String marked = DESUtil.encryption(user.getUserName());
            marked=marked.replace("+", "*");
           // System.out.println(marked+"=============des加密的用户名===");
            marked=CommonUtil.getEncoding(marked, "UTF-8");//加密后在进行url转换，防止特许字符在url上被过滤掉
           // System.out.println(marked+"=============des加密的用户名在经过url转换的===");
            String ip=CommonUtil.getValue("ip");
            String port=CommonUtil.getValue("port");
            sb.append("亲爱的" + user.getUserName() + "您好：<br/><br/>");
            sb.append("点击以下链接激活当前用户。<br/><br/>");
            sb.append("<a href='http://"+ip+":"+port+"/web/registerActivate?timeStamp="+timeStamp+"&marked="+marked+"' >");
            sb.append("http://"+ip+":"+port+"/web/registerActivate?timeStamp="+timeStamp+"&marked="+marked+"</a><br/><br/>");
            sb.append("(如果无法点击该URL链接地址，请将它复制并粘帖到浏览器的地址输入框，然后单击回车即可。)<br/><br/>");
            sb.append("注意:请您在收到邮件后尽快激活。<br/><br/>");
             sb.append("我们将一如既往、热忱的为您服务！<br/><br/>");
            TRSMailUtil.send(user.getEmail(), sb.toString());
        }
        catch (Exception e)
        {
            throw new AppRunTimeException(ExceptionConstants.Code_9999);
        }
    }
    /**
     * 根据用户名和创建时间来获得待激活的用户
     * @param userName
     * @param crutime
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UMSUser getActivateUserByUserName(String userName,String crutime){
        UMSUser user=userdao.getActivateUserByUserName(userName,crutime);
        return user;
    }
    /**
     * 激活用户
     * @param u
     * @see com.trsnj.ums.service.ILoginService#activateUser(com.trsnj.ums.pojo.UMSUser)
     */
    public void activateUser(UMSUser u){
        
        u.setType(1);//激活用户
        userdao.saveOrUpdate(u);
    }
    /**
     * 找回密码，向邮箱发送链接的一步
     * @param email
     * @see com.trsnj.ums.service.ILoginService#findpwd(java.lang.String)
     */
    public void findpwd(String email){
        
       UMSUser u=userdao.getUserByEmail(email);
       long expiretime=new Date().getTime()+24*60*60*1000;//当前时间的毫秒数+24小时的毫秒数
        String expiretimestr=""+expiretime;//超过24小时就过期
        String updatepwdkey=CommonUtil.genStr(20);
        String userName=u.getUserName();
        String ip=CommonUtil.getValue("ip");
        String port=CommonUtil.getValue("port");
        try
        {
            String desusername=DESUtil.encryption(userName);
            updatepwdkey=DESUtil.encryption(updatepwdkey);//加密
            updatepwdkey=CommonUtil.getEncoding(updatepwdkey, "UTF-8");//必须要encoding防止因为加密产生的特许字符在传输的时候丢失（例如：+）
            u.setUpdatepwdtime(expiretimestr);//过期时间
            u.setUpdatepwdkey(updatepwdkey);//加密的key
            userdao.update(u);
            StringBuilder sb = new StringBuilder();
             sb.append("亲爱的" + userName + "您好：<br/><br/>");
           sb.append("点击以下链接设置新密码。<br/><br/>");
             sb.append("<a href =\"http://"+ip+":"+port+"/ums/web/findpwdcheck?key=" + updatepwdkey + "&username=" + desusername + "\">http://"+ip+":"+port+"/ums/web/findpwdcheck?key=" + updatepwdkey + "&username=" + desusername + " </a><br/><br/>");
            sb.append("(如果无法点击该URL链接地址，请将它复制并粘帖到浏览器的地址输入框，然后单击回车即可。)<br/><br/>");
            sb.append("注意:请您在收到邮件24小时内使用，否则该链接将会失效且该链接只能点击一次，如若失效，请重新按照找回密码步骤操作。<br/><br/>");
             sb.append("我们将一如既往、热忱的为您服务！<br/><br/>");
             String content = sb.toString();
            TRSMailUtil.sendFindpwd(email, content,"UMS找回密码");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 找回密码验证
     * @param username
     * @param key
     * @return
     * @see com.trsnj.ums.service.ILoginService#findpwdcheck(java.lang.String, java.lang.String)
     */
    public String findpwdcheck(String username, String key){
        try
        {
            if(username.equals("null")||username.equals("")||username==null){
                return "FAIL";
            }
            String name=CommonUtil.deEncoding(username, "UTF-8");
            name=DESUtil.decryption(username);
           UMSUser user= userdao.getUserByUserName(name);
           if(user.getUpdatepwdkey()==null||user.getUpdatepwdkey().equals("")){
               return "FAIL";
           }
            long time=new Date().getTime();
            long expiretime=Long.parseLong(user.getUpdatepwdtime());
            if(time>expiretime||!key.equals(CommonUtil.deEncoding(user.getUpdatepwdkey(), "UTF-8"))){
               
                return "FAIL";
            }else{
                user.setUpdatepwdkey(null);
                userdao.update(user);
                return "SUCCESS";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return "FAIL";
    }
    /**
     * 
     * @param userName
     * @param passWord
     * @see com.trsnj.ums.service.ILoginService#updatepwd(java.lang.String, java.lang.String)
     */
    public String updatepwd(String userName, String passWord){
        UMSUser u=userdao.getUserByUserName(userName);
        if(u!=null){
            u.setPassWord(MD5Util.getSecretStr(passWord));
            userdao.update(u);
            return "SUCCESS";
        }else{
            return "FAIL";
        }
        
    }
    /**
     * 判断当前角色,是否有url路径的权限
     * @param roleId
     * @param url  wcm前台文档所在栏目的路径
     * @return
     * @see com.trsnj.ums.service.ILoginService#isValidAuthority(java.lang.String, java.lang.String)
     */
    public boolean isValidAuthority(String roleId, String url,String chnlid){
       //这里占时只判断chnlid
        boolean flag=false;
        List<UMSChannel> lists=channeldao.getChannelsByRoleId(roleId);
        if(lists==null||lists.size()==0){
            return flag;
        }else if(lists!=null&&lists.size()>0){
            for(UMSChannel channel:lists){
                String cid=channel.getChnlid()+"";
               if(cid.equals(chnlid)){
                   flag=true;
                   return flag;
               }
            }
        }
        return flag;
    }
    /**
     * 判断当前路径在当前的角色权限下是否授可
     * @param roleId
     * @param path
     * @return
     * @see com.trsnj.ums.service.ILoginService#isValidAuthority(java.lang.String, java.lang.String)
     */
    public boolean isValidAuthority(String roleId, String path){
        boolean flag=false;
        List<UMSChannel> lists=channeldao.getChannelsByRoleId(roleId);
        if(lists==null||lists.size()==0){
            return flag;
        }else if(lists!=null&&lists.size()>0){
            for(UMSChannel channel:lists){
                String cid=channel.getPath();//获得栏目的路径
               if(cid.equals(path)){
                   flag=true;
                   return flag;
               }
            }
        }
        return flag;
    }
}
