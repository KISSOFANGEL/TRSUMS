/*
 * 文 件 名:  LoginAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-12
 */
package com.trsnj.ums.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.ILoginService;
import com.trsnj.ums.service.IRoleService;
import com.trsnj.ums.service.IUserService;
import com.trsnj.ums.service.impl.LoginService;
import com.trsnj.ums.service.impl.RoleServiceImpl;
import com.trsnj.ums.service.impl.UserServiceImpl;
import com.trsnj.ums.util.BaseAction;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.DESUtil;
import com.trsnj.ums.util.MD5Util;

/**
 * <一句话功能简述>
 *  两个action用一个umsuser的ModelDriven在请求的时候，参数会同事封装进来。
 * @author  dzy
 * @version  [V1.00, 2014-12-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class LoginAction extends BaseAction implements ModelDriven<UMSUser>
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4104497756414084778L;
    private IUserService userService =null;
    private IRoleService roleService =null;
    private ILoginService loginService =null;
    private UMSUser user=null;
    
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
     * 获取 loginService
     * @return 返回 loginService
     */
    public ILoginService getLoginService()
    {
        return loginService;
    }

    /**
     * 设置 loginService
     * @param 对loginService进行赋值
     */
    public void setLoginService(ILoginService loginService)
    {
        this.loginService = loginService;
    }

    /**
     * 获取 user
     * @return 返回 user
     */
    public UMSUser getUser()
    {
        return user;
    }

    /**
     * 设置 user
     * @param 对user进行赋值
     */
    public void setUser(UMSUser user)
    {
        this.user = user;
    }

    /**
     * 获取 userService
     * @return 返回 userService
     */
    public IUserService getUserService()
    {
        return userService;
    }

    /**
     * 设置 userService
     * @param 对userService进行赋值
     */
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSUser getModel()
    {
        return this.user;
    }
    
    /**
     * 检查用户名是否存在
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String checkUserName(){
        String userName=user.getUserName();// 前台参数传过来是传到userName
        userService.getUserByName(userName);//检查用户名是否存在
        return "baseEntityResult";
    }
    
    /**
     * 检查邮箱是否被注册
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String checkEmail(){
        String eamil=user.getEmail();// 前台参数传过来是传到userName
        userService.getEmail(eamil);//检查用户名是否存在
        return "baseEntityResult";
    }
    /**
     * 用户注册
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String register(){
        //
        UMSRole umsrole=roleService.getRoleByRoleName("普通用户");
        if(umsrole!=null){
            user.setUmsrole(umsrole);
        }
        user.setCruser(user.getUserName());
        user.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        user.setDelnote(1);//未被删除的用户
        user.setType(0);// 未被激活的用户
        user.setAddUserType(1);//表示前台注册的用户
        
        // 直接调用方法保存
            loginService.register(user);// 此时角色与组织都为null，这里user对象保存后变成持久化对象
            //将email加密在url上传递
            UMSUser entityUser=new UMSUser();;
            try
            {
                String email=DESUtil.encryption(user.getEmail());// 这是在跳转到mail.jsp的页面的时候给其进行加密
                email=CommonUtil.getEncoding(email, "UTF-8");
                
                //user.setEmail(email);//（不能改变持久化类，会修改数据的！！！，这里改变了user对象的email，结果保存到库里啦）
                entityUser.setEmail(email);//给他urlencoding，在转换成json的时候就不会乱码了，
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            baseEntity.setObj(entityUser);
            return "baseEntityResult";//baseEntityResult返回json，异常抛出也返回json
       
    }
    /**
     * 前台登陆请求的action(用户名与密码都能登陆)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String logindao(){
        // 根据用户名查询查用户，这个用户名可能是邮箱
       UMSUser u=userService.getUserByName(user.getUserName());
       UMSUser uemail=userService.getUserByEmail(user.getUserName());
        if(u==null&&uemail==null){
            //用户名不存在
            request.setAttribute("info", "用户不存在");
            request.setAttribute("status", "n");
            return "logindaoerror";
        }else{ 
            String pw=user.getPassWord();
            pw=MD5Util.getSecretStr(pw); 
          if(u!=null){
              if(!pw.equals(u.getPassWord())){
                  request.setAttribute("info", "密码不正确");
                  request.setAttribute("status", "n");
                  return "logindaoerror"; 
              }else{
                  if(u.getType()==0){
                      request.setAttribute("info", "该用户尚未被激活，请去注册邮箱进行激活");
                      request.setAttribute("status", "n");
                      return "logindaoerror";  //登陆错误后，转跳转的
                  }
              } 
              session.put("userName",u.getUserName());//前台通过session.getValue(key);来获取session的值
              session.put("userId",u.getUserId());
              session.put("addUserType",u.getAddUserType());
              session.put("roleId", u.getUmsrole().getRoleId());//存取角色的id
          }else{
              if(!pw.equals(uemail.getPassWord())){
                  request.setAttribute("info", "密码不正确");
                  request.setAttribute("status", "n");
                  return "logindaoerror"; 
              }else{
                  if(uemail.getType()==0){
                      request.setAttribute("info", "该用户尚未被激活，请去注册邮箱进行激活");
                      request.setAttribute("status", "n");
                      return "logindaoerror";  //登陆错误后，转跳转的
                  }
              } 
              session.put("userName",uemail.getUserName());//前台通过session.getValue(key);来获取session的值
              session.put("userId",uemail.getUserId());
              session.put("addUserType",uemail.getAddUserType());
              session.put("roleId", uemail.getUmsrole().getRoleId());//存取角色的id  
          }  
            
        }
        return "loginsuccess";// 登陆成功的是重定向的跳转,在加一个后台登陆还是前台登陆(????)(loginsuccess是前台，adminloginsuccess是后台)
    }
    
    /**
     * 管理员请求的action
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String loginmanagerdao(){
        // 根据用户名查询查用户
       UMSUser u=userService.getUserByName(user.getUserName());
     if(u==null){
            //用户名不存在
            request.setAttribute("info", "用户不存在");
            request.setAttribute("status", "n");
            return "loginmanagerdaoerror";
        }else{ 
            
            String pw=user.getPassWord();
            pw=MD5Util.getSecretStr(pw); 
            if(!pw.equals(u.getPassWord())){
                request.setAttribute("info", "密码不正确");
                request.setAttribute("status", "n");
                return "loginmanagerdaoerror"; 
            }else{
                if(u.getAddUserType()!=2||!"系统管理员".equals(u.getUmsrole().getRoleName())){
                    request.setAttribute("info", "用户权限不够");
                    request.setAttribute("status", "n");
                    return "loginmanagerdaoerror";  //登陆错误后，转跳转的
                }
            }
        }
        session.put("userName",u.getUserName());//前台通过session.getValue(key);来获取session的值
        session.put("userId",u.getUserId());
        session.put("addUserType",u.getAddUserType());
        if(u.getUmsrole()!=null)session.put("roleId", u.getUmsrole().getRoleId());//存取角色的id
        
        return "adminloginsuccess";// 登陆成功的是重定向的跳转,在加一个后台登陆还是前台登陆(????)(loginsuccess是前台，adminloginsuccess是后台)
    }
    /**
     * 前台注册用户激活
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String registerActivate(){
        
        String timeStamp=request.getParameter("timeStamp");
        System.out.println(timeStamp+"=======激活获得时间戳参数值======");
        System.out.println(request.getParameter("marked")+"=======激活获得用户名参数值======");
        try
        {
            System.out.println(CommonUtil.deEncoding(request.getParameter("marked").replace("*", "+"), "UTF-8")+"=======激活获得用户名url转码参数值======");
            //des解密
            //String userName=DESUtil.decryption(CommonUtil.deEncoding(request.getParameter("marked").replace("*", "+"), "UTF-8"));
            String userName=DESUtil.decryption(request.getParameter("marked").replace("*", "+"));
            String crutime=CommonUtil.getStrTimeByTimeStamp(timeStamp, "yyyy-MM-dd HH:mm");
            System.out.println(crutime+"=======获得formate的时间格式======");
            System.out.println(userName+"=======激活获得已经解密的用户名参数值======");
            // 根据用户名，创建时间来查询用户
           UMSUser u= loginService.getActivateUserByUserName(userName,crutime);
           if(u==null){
               request.setAttribute("userStatus", "激活失效，请重新注册");
               return "registerActivate"; 
           }
           if(u.getType()==1){
               request.setAttribute("userStatus", "该用户已经被激活，请不要重复操作");
               return "registerActivate";
           }
           if(u.getType()==0){
               //激活用户
               loginService.activateUser(u);
               request.setAttribute("userStatus", "激活成功，请登录!");
           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return "registerActivate";//激活用户
    }
    /**
     * 退出个人中心系统
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String quitSystem(){
        this.session.clear();
        return "urlerror";//urlerror为login.jsp页面了  urlerror页面还是跳转到前台登陆页面
    }
    
    /**
     * 退出管理系统
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String quitManagerSystem(){
        this.session.clear();
        return "quitmanagersystem";//loginerror改成nologin.html页面了  urlerror页面还是跳转到前台登陆页面
    }
    /**
     * 找回密码，向邮箱发布找回密码的连接
     * @see [类、类#方法、类#成员]
     */
    public String findpwd(){
        
        String email=request.getParameter("registeremail");
        loginService.findpwd(email);
        try
        {
            email=DESUtil.encryption(email);
            
            request.setAttribute("ml", email);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "success";
    }
    /**
     * 验证key
     * @see [类、类#方法、类#成员]
     */
    public String findpwdcheck(){
        
        String username=request.getParameter("username");
        String key=request.getParameter("key");
       String result= loginService.findpwdcheck(username,key);
       if(result.equals("FAIL")){
           request.setAttribute("info", "找回密码链接已失效");
           request.setAttribute("status", "n");
       }else{
           request.setAttribute("key", key);
           request.setAttribute("desusername", username);
       }
       return result;
    }
    /**
     * 重置密码
     * @see [类、类#方法、类#成员]
     */
    public String updatepwd(){
        String userName=request.getParameter("userName");//这里获得用户名是des加密的用户名
        String passWord=request.getParameter("passWord");
        String result="FAIl";
        try
        {
            userName=DESUtil.decryption(userName);
           result=loginService.updatepwd(userName,passWord);
           if("SUCCESS".equals(result)){
               request.setAttribute("info", "密码重置成功");
               request.setAttribute("status", "n");
           }else{
               request.setAttribute("info", "重置密码失败");
               request.setAttribute("status", "n");
           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * wcm 前台页面权限检查(根据用户的栏目权限,没登陆的就是游客权限)
     * @see [类、类#方法、类#成员]
     * 跨域调用
     */
    public void loginAuthority(){
        System.out.println("===========qingqiujinlai");
        String callbackFunName=request.getParameter("callbackparam");
        String chnlurl=request.getParameter("chnlurl");
        String chnlid=request.getParameter("chnlid");
        PrintWriter out=null;
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonObject=new JSONObject();
        try
        {
            out=response.getWriter();
            if(chnlid==null||"".equals(chnlid)){
                jsonObject.put("result", false);
                out.print(callbackFunName+"("+jsonObject.toString()+")");
                return;
            }else {//包括预览的时候也是pub
              //  String[] strurl=chnlurl.split("pub/");
              //  String url=strurl[1];
                //从session里面获得角色id
                String roleId=session.get("roleId")+"";
                if(roleId==null||"".equals(roleId)||"null".equals(roleId)){
                    jsonObject.put("online", false);
                   // roleId="5";//5表示游客角色的权限，默认的不能删除
                    // 根据角色名称来获取roleId
                    UMSRole role=roleService.getRoleByRoleName("游客");
                     if(role!=null){
                         roleId=role.getRoleId()+"";
                     }else{
                         roleId="5";
                     }
                }else{
                    
                    jsonObject.put("online", true);
                }
                // 根据角色id获取栏目的权限
                boolean flag=loginService.isValidAuthority(roleId,"",chnlid);
                jsonObject.put("result", flag);
               // System.out.println("jsonOjbect====||"+jsonObject.toString());
                out.print(callbackFunName+"("+jsonObject.toString()+")");
                return;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**丢弃（占时没用）
     * wcm的过滤器发送过来的请求，判断ums系统是否登陆状态，如果登陆，补充判断当前页面是否可以访问
     * @see [类、类#方法、类#成员]
     */
    public void isOnline(){
        String path=request.getParameter("relativepath");//获得传递过来的访问路径（wcm页面的路径）
       //  /pub/wcmdemo2/news/technology/200905/t20090518_177.html
        // 剪切成这样来匹配wcmdemo2/news/technology/
        System.out.println(path+"||path========================");
        String splitrule="pub\\/";
        String rule=CommonUtil.getValue("rule");
        if(rule!=null&&!"".equals(rule)){
            splitrule=rule;
        }
        System.out.println(path+"||path=============pub||"+path.split(splitrule)[1]);
        path=path.split(splitrule)[1].split("[0-9]{6}/")[0];//?????????????路径没split好
        if(path.endsWith("index.html")){
            System.out.println(path+"=================概览");
            path=path.substring(0,path.lastIndexOf("/")+1);
        }
        System.out.println(path+"====================经过正则剪切的字符串");
        boolean valid= false;
        JSONObject jsonObject=new JSONObject();
        String userName=(String)session.get("userName");
        String roleId="5";//初始化数据的时候游客角色id为5
        if(userName==null||"".equals(userName)){
            jsonObject.put("isOnline", false);
            UMSRole role=roleService.getRoleByRoleName("游客");
            if(role!=null){
                roleId=role.getRoleId()+"";
            }
            valid=  loginService.isValidAuthority(roleId, path);
        }else{
            jsonObject.put("isOnline", true);
            //判断当前权限是否可以访问当前path的页面
            roleId=session.get("roleId")+"";
            valid=  loginService.isValidAuthority(roleId, path);
        }
        jsonObject.put("valid", valid);
        PrintWriter out=null;
        try
        {
            System.out.println(jsonObject.toString()+"||=====================jsonObject");
            out=response.getWriter();
            out.print(jsonObject.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
