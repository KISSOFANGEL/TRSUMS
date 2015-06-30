package com.trsnj.ums.action;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.entity.TreeAttribute;
import com.trsnj.ums.entity.TreeEntity;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.trsnj.ums.pojo.UMSCompanyInfo;
import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.pojo.UMSUserGroup;
import com.trsnj.ums.service.IGroupService;
import com.trsnj.ums.service.IMessageService;
import com.trsnj.ums.service.IRoleService;
import com.trsnj.ums.service.IUMSCompanyService;
import com.trsnj.ums.service.IUserService;
import com.trsnj.ums.service.impl.CompanyInfoServiceImpl;
import com.trsnj.ums.service.impl.GroupServiceImpl;
import com.trsnj.ums.service.impl.MessageServiceImpl;
import com.trsnj.ums.service.impl.RoleServiceImpl;
import com.trsnj.ums.service.impl.UserServiceImpl;
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
public class UserAction extends BaseAction implements ModelDriven<UMSUser>
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    //private IUserService userService =new UserServiceImpl();
    //private IGroupService groupService=new GroupServiceImpl();
    //private IRoleService roleService=new RoleServiceImpl();
    //private IUMSCompanyService companyService=new CompanyInfoServiceImpl();
   // private IMessageService messageService=new MessageServiceImpl();
    //private UMSUser user=new UMSUser();
    private IUserService userService =null;
    private IGroupService groupService=null;
    private IRoleService roleService=null;
    private IUMSCompanyService companyService=null;
    private IMessageService messageService=null;
    private UMSUser user=null;
    private String[] umsroles;//页面传过来的角色树形  是数组类型
    private String umsgroups;//页面传过来的组织树形
    private String id;
    
    // 模型驱动的重写方法
    public UMSUser getModel() {
        return this.user;
    }
    
    /**
     * 获取 messageService
     * @return 返回 messageService
     */
    public IMessageService getMessageService()
    {
        return messageService;
    }

    /**
     * 设置 messageService
     * @param 对messageService进行赋值
     */
    public void setMessageService(IMessageService messageService)
    {
        this.messageService = messageService;
    }

    /**
     * 获取 companyService
     * @return 返回 companyService
     */
    public IUMSCompanyService getCompanyService()
    {
        return companyService;
    }

    /**
     * 设置 companyService
     * @param 对companyService进行赋值
     */
    public void setCompanyService(IUMSCompanyService companyService)
    {
        this.companyService = companyService;
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
     * 获取 id
     * @return 返回 id
     */
    public String getId()
    {
        return id;
    }


    /**
     * 设置 id
     * @param 对id进行赋值
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * 获取 umsroles
     * @return 返回 umsroles
     */
    public String[] getUmsroles()
    {
        return umsroles;
    }

    /**
     * 设置 umsroles
     * @param 对umsroles进行赋值
     */
    public void setUmsroles(String[] umsroles)
    {
        this.umsroles = umsroles;
    }

    /**
     * 获取 umsgroups
     * @return 返回 umsgroups
     */
    public String getUmsgroups()
    {
        return umsgroups;
    }

    /**
     * 设置 umsgroups
     * @param 对umsgroups进行赋值
     */
    public void setUmsgroups(String umsgroups)
    {
        this.umsgroups = umsgroups;
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

    public UMSUser getUser() {
		return user;
	}

	public void setUser(UMSUser user) {
		this.user = user;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
    
    public void getAllUsers(){
    		StringBuffer jsonbuffer = new StringBuffer();
		List<UMSUser> user = userService.getAllUsers();;
		UMSUser u = null;
		jsonbuffer.append("{\"total\":"+user.size()+",\"rows\":[");
		for(int i=0;i<user.size();i++){
			u = user.get(i);
			jsonbuffer.append("{\"userid\":"+"\""+u.getUserId()+"\",");
			jsonbuffer.append("\"username\":"+"\""+u.getUserName()+"\",");
			jsonbuffer.append("\"email\":"+"\""+  u.getEmail()+"\",");
			jsonbuffer.append("\"qq\":"+"\""+u.getQq()+"\",");
			jsonbuffer.append("\"address\":"+"\""+u.getAddress()+"\"}");
			if(i+1<user.size())jsonbuffer.append(",");
		}
		jsonbuffer.append("]}");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(jsonbuffer.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    /**
     * 根据分页得到数据,返回的是json数据
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getUsers(){
        String searchparam=request.getParameter("searchparam");
        List<UMSUser> users=null;
        if(searchparam!=null&&!"".equals(searchparam)&&!searchparam.endsWith("@")){
            String name=searchparam.split("@")[0];
            String value = searchparam.split("@")[1];
            users=userService.loadUsers(name,value,(page-1)*rows, rows);
        }else{
            users=userService.loadUsers((page-1)*rows, rows);
        }
        /*for(UMSUser user:users){
            user.setUmsrole(null);
            user.setUmsusergroups(null);//就在转化的时候出错了，如果不加在转换成json的时候报错了
        }*/
        baseEntity.setRows(users);
        baseEntity.setTotal(userService.getRowsCounts());
       
        return "baseEntityResult";
    }
    
    public String findUserByName(){
    	
    	user=userService.getUserByName(user.getUserName());
    	
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(user==null)out.print("unexist");
			else{out.print("exist");}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * 根据用户id获得该用户组织名称的字符串拼接
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getStrGroupNameByUserId(){
       String userId = request.getParameter("userId");
       // String userId="4";
       List<UMSGroup> list= groupService.getGroupsByUserId(Long.parseLong(userId));
       String groupNames="";
       for(int i=0;i<list.size();i++)
       {
           groupNames=groupNames+list.get(i).getGroupName();
           if(list.size()-1!=i)
          groupNames=groupNames+",";
       }
       UMSGroup group=new UMSGroup();
       group.setGroupName(groupNames);
       this.baseEntity.setObj(group);
        return "baseEntityResult";
       //return groupNames;
    }
    /**
     * 更具用户id获得该用户组织id的字符串拼接
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getStrGroupIdByUserId(){
       String userId = request.getParameter("userId");
       // String userId="4";
       List<UMSGroup> list= groupService.getGroupsByUserId(Long.parseLong(userId));
       String groupIds="";
       for(int i=0;i<list.size();i++)
       {
           groupIds=groupIds+list.get(i).getGroupId();
           if(list.size()-1!=i)
               groupIds=groupIds+",";
       }
       UMSGroup group=new UMSGroup();
       group.setGroupName(groupIds);// 将id串放在组名的字段里
       this.baseEntity.setObj(group);
        return "baseEntityResult";
       //return groupNames;
    }
    
    /**
     * 根据用户的id获取用户信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getUserByUserId(){
        String userId = request.getParameter("userId");
        UMSUser user=userService.getUserByUserId(Long.parseLong(userId));
        baseEntity.setObj(user);
        return "baseEntityResult";
    }
    /**
     * 分页获取未被激活的用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getNotActivationUsers(){
        List<UMSUser> users=userService.getNotActivationUsers((page-1)*rows, rows);
        baseEntity.setRows(users);
        baseEntity.setTotal(userService.getCountsNotActivationUsers());
        return "baseEntityResult";
    }
    
    
    /**
     * 添加用户
     * 如果用户绑定组织，只需要给创建中间实体类，然后设置中间实体类的组织对象属性的值即可
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String addUser(){
        // 这里是后台直接添加用户，所以user的delnote设置为1，type设置为1
        user.setDelnote(1);
        user.setType(1);
        user.setAddUserType(2);//2表示后天注册添加的用户
        user.setCruser("admin");
        user.setCrutime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        // 角色是单选
        if(umsroles!=null&&!"".equals(umsroles)&&!"0".equals(umsroles[0])&&!"-1".equals(umsroles[0])){
            UMSRole role=new UMSRole();
            role.setRoleId(Long.parseLong(umsroles[0]));
            user.setUmsrole(role);
        }
        //组织是多选
        if(umsgroups.startsWith("0")&&umsgroups.length()>1){
            umsgroups=umsgroups.substring("0".length()+1);
        }
        if(umsgroups.contains("-1")&&umsgroups.length()>2){//大于2表示勾选的是无和其他的组织
            throw new AppRunTimeException(ExceptionConstants.Code_0113);//多选框勾选不正确
        }
        if(umsgroups!=null&&!"".equals(umsgroups)&&!"0".equals(umsgroups)&&!"-1".equals(umsgroups)){
            String [] groups=umsgroups.split(",");
            Set<UMSUserGroup> umsgus=new HashSet<UMSUserGroup>();
           for(String groupid:groups){
               UMSUserGroup umsgu=new UMSUserGroup();
               UMSGroup g=new UMSGroup();
               g.setGroupId(Long.parseLong(groupid));
               umsgu.setGroup(g);
               umsgus.add(umsgu);
           }
           user.setUmsusergroups(umsgus);
        }
        if(umsroles[0].equals("-1")){
            user.setUmsrole(null);
        }
        if(umsgroups.equals("-1")){
            user.setUmsusergroups(null);
        }
        userService.add(user);
        return "baseEntityResult";
    }
    /**
     * 修改用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateUser(){
        user.setUserId(Long.parseLong(id));
        // 传过来的userid要有
        if(umsroles!=null&&!"".equals(umsroles)&&!"0".equals(umsroles[0])&&!"-1".equals(umsroles[0])){
            UMSRole role=new UMSRole();
            role.setRoleId(Long.parseLong(umsroles[0]));
            user.setUmsrole(role);
        }
        if(umsgroups.startsWith("0")&&umsgroups.length()>1){
            umsgroups=umsgroups.substring("0".length()+1);
        }
        if(umsgroups.contains("-1")&&umsgroups.length()>2){
            throw new AppRunTimeException(ExceptionConstants.Code_0113);//多选框勾选不正确
        }
        if(umsgroups!=null&&!"".equals(umsgroups)&&!"0".equals(umsgroups)&&!"-1".equals(umsgroups)){
            String [] groups=umsgroups.split(",");
            Set<UMSUserGroup> umsgus=new HashSet<UMSUserGroup>();
           for(String groupid:groups){
               UMSUserGroup umsgu=new UMSUserGroup();
               UMSGroup g=new UMSGroup();
               g.setGroupId(Long.parseLong(groupid));
              // UMSUser u=new UMSUser();
              // u.setUserId(user.getUserId());
               umsgu.setGroup(g);
              // umsgu.setUser(u);
               umsgus.add(umsgu);
           }
           user.setUmsusergroups(umsgus);
        }
        if(umsroles[0].equals("-1")){
            user.setUmsrole(null);
        }
        if(umsgroups.equals("-1")){
            user.setUmsusergroups(null);//set null后，dao层要修改commonutil.update方法
        }
        userService.update(user);
        return "baseEntityResult";
    }
    /**
     * 删除用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String deluser(){
        UMSUser u=new UMSUser();
        u.setUserId(Long.parseLong(id));
        userService.delete(u);
        return "baseEntityResult";
    }
    /**
     * 批量删除用户数据
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String batchdeluser(){
        String ids = request.getParameter("ids");
        String ids_yz = request.getParameter("ids_yz");
        String [] userids=ids.split(",");
        for(String userid:userids){
            UMSUser u=new UMSUser();
            u.setUserId(Long.parseLong(userid));
            userService.delete(u);
        }
        return "baseEntityResult";
    }
    
    
    
    /**
     * 获取角色的树形，因为角色没有父角色的概念，所以不用递归来实现
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String initroletree(){
      List<UMSRole> roles=  roleService.loadAllRoles();
        // 将所有的数据放进树形实体中去
        List<TreeEntity> tree=new ArrayList<TreeEntity>();
        TreeEntity treeNone=new TreeEntity();
        treeNone.setId("-1");
        treeNone.setText("无");
        TreeEntity treeRoot=new TreeEntity();
        treeRoot.setId("0");
        treeRoot.setText("所有角色");
        List<TreeEntity> treeChildren=new ArrayList<TreeEntity>();
        for(UMSRole role:roles)
        {
            TreeEntity treeChild=new TreeEntity();
            treeChild.setId(role.getRoleId()+"");
            treeChild.setText(role.getRoleName());
            treeChildren.add(treeChild);
        }
        treeRoot.setChildren(treeChildren);
        tree.add(treeNone);
        tree.add(treeRoot);
        baseEntity.setTreeList(tree);// 设置角色的树形
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
        TreeEntity treeNone=new TreeEntity();
        treeNone.setId("-1");
        treeNone.setText("无");
        TreeEntity treeRoot=new TreeEntity();
        treeRoot.setId("0");
        treeRoot.setText("根组织");
        //调用递归方法
        recursive(treeRoot,0);
        tree.add(treeNone);
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
    ////////////////////////////////////////////////////////////
    //以下三个方法为main.jsp中树形加载的方法                                                  //
    ////////////////////////////////////////////////////////////
    /**
     * 获取角色的树形，因为角色没有父角色的概念，所以不用递归来实现
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String initroletreemain(){
      List<UMSRole> roles=  roleService.loadAllRoles();
        // 将所有的数据放进树形实体中去
        List<TreeEntity> tree=new ArrayList<TreeEntity>();
        TreeEntity treeRoot=new TreeEntity();
        treeRoot.setId("0");
        treeRoot.setText("所有角色");
        treeRoot.setState("open");
        List<TreeEntity> treeChildren=new ArrayList<TreeEntity>();
        for(UMSRole role:roles)
        {
            TreeEntity treeChild=new TreeEntity();
            treeChild.setId(role.getRoleId()+"");
            treeChild.setText(role.getRoleName());
            treeChild.setState("open");
           // treeChild.setAttributes(new TreeAttribute("javascript:addTab('tableid_"+role.getRoleId()+"','+"+role.getRoleName()+"角色管理','<%=root %>/role/roleusermanager')"));
            treeChildren.add(treeChild);
        }
        treeRoot.setChildren(treeChildren);
        tree.add(treeRoot);
        baseEntity.setTreeList(tree);// 设置角色的树形
        return "baseEntityResult";
    }
    /**
     * 获取组织的树形   根据递归的方法获取组织的树形
     * 在组织中如果是根组织，则parentId为0
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String initgrouptreemain(){
        List<TreeEntity> tree=new ArrayList<TreeEntity>();
        TreeEntity treeRoot=new TreeEntity();
        treeRoot.setId("0");
        treeRoot.setText("根组织");
        //调用递归方法
        recursivemain(treeRoot,0);
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
    public TreeEntity recursivemain(TreeEntity treeEntity,long groupid){
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
     * 后台激活用户(开通用户)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String activateUser(){
        String userId=request.getParameter("id");
        userService.activateUser(userId);
        return "baseEntityResult";
    }
    /**
     * 批量激活用户(批量开通用户)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String batchActivateUser(){
        String ids=request.getParameter("ids");
        userService.batchActivateUser(ids);
        return "baseEntityResult";
    }
    
    ///////////////////////////////////
    //前台个人中心页面的action请求               ///
    ///////////////////////////////////
    /**
     * 前台中心页面根据session里面的id来获取用户信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getCurrentUser(){
        // 获得session里面的id
        String userId=this.getUserId();
        UMSUser user=userService.getUserByUserId(Long.parseLong(userId));
        baseEntity.setObj(user);
        return "baseEntityResult";
    }
    /**
     * 获取当前用户的公司信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getCurrentCompanyInfo(){
        String userId=this.getUserId();
        UMSCompanyInfo companyInfo=companyService.getComInfoByUserId(Long.parseLong(userId));
        baseEntity.setObj(companyInfo);
        return "baseEntityResult";
    }
    /**
     * 修改当前用户
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateCurrentUser(){
        String userId=this.getUserId();
        String oldpassword=request.getParameter("oldPassWord");
        String repassword=request.getParameter("repassword");
        if(repassword!=null&&repassword!=""&&user.getPassWord()!=""){
            if(!repassword.equals(user.getPassWord())){
                baseEntity.setIsSuccessOrfail("FAIL");
                baseEntity.setMessage("新密码与确认密码不一致");
                return "baseEntityResult";
            }
        }
        user.setUserId(Long.parseLong(userId));
       UMSUser u= userService.updatCurrentUser(user,oldpassword);
        baseEntity.setObj(u);
        return "baseEntityResult";
    }
    /**
     * 修改密码
     * @see [类、类#方法、类#成员]
     */
    public void updatePassword(){
        String userId=this.getUserId();
        String oldpassword=request.getParameter("oldPassWord");
        String newpassword=request.getParameter("newPassword");
        String result=userService.updatePassword(userId,oldpassword,newpassword);
        JSONObject jsonObject=new JSONObject();
        jsonObject.accumulate("result", result);
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
     * 修改当前用户信息和公司信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updateCurrentUserAndCompanyInfo(){
        System.out.println(user.getEmail()+"||updateCurrentUserAndCompanyInfo=====================");
        String userId=this.getUserId();
        String oldpassword=request.getParameter("oldPassWord");
        String repassword=request.getParameter("repassword");
        if(repassword!=null&&repassword!=""&&user.getPassWord()!=""){
            if(!repassword.equals(user.getPassWord())){
               
            }
        }
        user.setUserId(Long.parseLong(userId));
       UMSUser u= userService.updatCurrentUser(user,oldpassword);
        String companyName=request.getParameter("companyName");
        String companyAddress=request.getParameter("companyAddress");
        String companyNature=request.getParameter("companyNature");
        String deptName=request.getParameter("deptName");
        String companytel=request.getParameter("companytel");
        UMSCompanyInfo companyInfo=new UMSCompanyInfo();
        companyInfo.setCompanyAddress(companyAddress);
        companyInfo.setCompanyName(companyName);
        companyInfo.setCompanyNature(companyNature);
        companyInfo.setDeptName(deptName);
        companyInfo.setCompanytel(companytel);
        UMSCompanyInfo cominfo= companyService.updateCurrentCompanyInfo(companyInfo,userId);
        // 修改成功后，以该用户作为发送者，向管理员发送认证信息，mestype=1
        messageService.addAuthentication(userId);//修改成发送给某一个角色了（这里是系统管理员角色）
        
        JSONObject jsonObject = new JSONObject();
        String result="suceess";
        if(u==null){
            result="fail";
        }else{
        jsonObject.accumulate("userId", u.getUserId());
        jsonObject.accumulate("address", u.getAddress());
        jsonObject.accumulate("email",u.getEmail());
        jsonObject.accumulate("mobile", u.getMobile());
        jsonObject.accumulate("qq", u.getQq());
        jsonObject.accumulate("tel", u.getTel());
        jsonObject.accumulate("relname", u.getRelname());
        }
        if(cominfo==null){
            result="fail";
        }else{
            jsonObject.accumulate("companyInfoId", cominfo.getCompanyInfoId());
            jsonObject.accumulate("companyAddress",cominfo.getCompanyAddress());
            jsonObject.accumulate("companyName", cominfo.getCompanyName());
            jsonObject.accumulate("companyNature", cominfo.getCompanyNature());
            jsonObject.accumulate("deptName", cominfo.getDeptName());
            jsonObject.accumulate("companytel", cominfo.getCompanytel());
        }
        jsonObject.accumulate("result", result);
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
     * 修改当前用户的公司信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateCurrentCompanyInfo(){
        String userId=this.getUserId();
        String companyName=request.getParameter("companyName");
        String companyAddress=request.getParameter("companyAddress");
        String companyNature=request.getParameter("companyNature");
        String deptName=request.getParameter("deptName");
        String companytel=request.getParameter("companytel");
        UMSCompanyInfo companyInfo=new UMSCompanyInfo();
        companyInfo.setCompanyAddress(companyAddress);
        companyInfo.setCompanyName(companyName);
        companyInfo.setCompanyNature(companyNature);
        companyInfo.setDeptName(deptName);
        companyInfo.setCompanytel(companytel);
        UMSCompanyInfo cominfo= companyService.updateCurrentCompanyInfo(companyInfo,userId);
        baseEntity.setObj(cominfo);
        return "baseEntityResult";
    }
    /**
     * 获得认证用户的详细信息
     * @see [类、类#方法、类#成员]
     */
    public void getAuthenticationUserInfo(){
        String userId=request.getParameter("mesauthor");
       Map<String,String> map= userService.getAuthenticationUserInfo(Long.parseLong(userId));
        JSONObject jsonObject=new JSONObject();
        jsonObject.accumulate("isSuccessOrfail", "SUCCESS");
        for(Iterator it=map.keySet().iterator();it.hasNext();){
            String key=(String)it.next();
            jsonObject.accumulate(key, map.get(key));
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();// 必须用这个流要不然打印不出去数据
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 通过认证请求
     * @see [类、类#方法、类#成员]
     */
    public void passAuthenticationUser(){
        String messageid=request.getParameter("messageid");//认证的信息请求
        String userid=request.getParameter("userid");//待认证用户
        String result=userService.passAuthenticationUser(Long.parseLong(messageid),Long.parseLong(userid));
        JSONObject jsonObject=new JSONObject();
        jsonObject.accumulate("isSuccessOrfail", result);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();// 必须用这个流要不然打印不出去数据
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据用户名获得用户
     * @param userName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void getUserByUserName(){
        String  param = request.getParameter("param");
        String name = request.getParameter("name");
        UMSUser tempuser ;
        if("userName".equals(name)){
            tempuser=userService.getUserByUserName(param);
          
                PrintWriter out;
                response.setCharacterEncoding("UTF-8");
                try {
                    out = response.getWriter();
                    if(tempuser!=null){
                    out.print("{\"info\":\"用户名已存在！\",\"status\":\"n\"}");
                    }else{
                        out.print("{\"info\":\"验证通过！\",\"status\":\"y\"}");
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            
        }
    }
    /**
     * 根据邮箱获得用户
     */
    public void getUserByEmail(){
        String  param = request.getParameter("param");
        String name = request.getParameter("name");
        UMSUser tempuser ;
        if("email".equals(name)){
            tempuser=userService.getUserByEmail(param);
          
                PrintWriter out;
                response.setCharacterEncoding("UTF-8");
                try {
                    out = response.getWriter();
                    if(tempuser!=null){
                        out.print("{\"info\":\"邮箱名已存在！\",\"status\":\"n\"}");
                    }else{
                        out.print("{\"info\":\"验证通过！\",\"status\":\"y\"}");
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            
        }
    }
    /**
     * 根据用户名验证密码是否匹配
     */
    public void checkPasswordByUserName(){
        String  param = request.getParameter("param");
        String name = request.getParameter("name");
        String username = this.getUserName();
        UMSUser tempuser ;
        if("oldPassWord".equals(name)){
            tempuser=userService.checkPasswordByUserName(username, param);
                PrintWriter out;
                response.setCharacterEncoding("UTF-8");
                try {
                    out = response.getWriter();
                    if(tempuser==null){
                        out.print("{\"info\":\"密码不正确！\",\"status\":\"n\"}");
                    }else{
                        out.print("{\"info\":\"验证通过！\",\"status\":\"y\"}");
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            
        }
    }
    /**
     * 找回密码的时候验证邮箱是否注册
     * @return
     */
    public void checkRegisterEmail(){
        String  param = request.getParameter("param");
        String name = request.getParameter("name");
        UMSUser tempuser ;
        if("registeremail".equals(name)){
            tempuser=userService.getUserByEmail(param);
                PrintWriter out;
                response.setCharacterEncoding("UTF-8");
                try {
                    out = response.getWriter();
                    if(tempuser==null){
                        out.print("{\"info\":\"该邮箱未被注册！\",\"status\":\"n\"}");
                    }else{
                        out.print("{\"info\":\"验证通过！\",\"status\":\"y\"}");
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
        }
    }
    /**
     * 管理员对注册用户的密码重置
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String resetsec(){
        String id=request.getParameter("id");
        userService.resetsec(id);
        return "baseEntityResult";
    }
    /**
     * 管理员对自身的一个密码修改（超级管理员可以修改其他管理员的密码，默认admin是超级管理员）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updatesec(){
        String id=request.getParameter("userId");//修改的管理员id
        String password=request.getParameter("password");
        String oldpassword=request.getParameter("oldpassword");
        String loginUser=getUserName();
        userService.updatesec(loginUser,id,password,oldpassword);
        return "baseEntityResult";
    }
    
    
}
