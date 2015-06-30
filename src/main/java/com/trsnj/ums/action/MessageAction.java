/*
 * 文 件 名:  MessageAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-24
 */
package com.trsnj.ums.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.pojo.UMSMessage;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IMessageService;
import com.trsnj.ums.service.IRoleService;
import com.trsnj.ums.service.ISystemMessageService;
import com.trsnj.ums.service.IUserService;
import com.trsnj.ums.service.impl.MessageServiceImpl;
import com.trsnj.ums.service.impl.UserServiceImpl;
import com.trsnj.ums.util.BaseAction;

/**
 * <一句话功能简述>
 * 
 * @author dzy
 * @version [V1.00, 2014-12-24]
 * @see [相关类/方法]
 * @since V1.00
 */
public class MessageAction extends BaseAction implements
		ModelDriven<UMSMessage> {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -315897593600445587L;
    //private IMessageService messageService = new MessageServiceImpl();
	//private IUserService userService = new UserServiceImpl();
	//private UMSMessage message = new UMSMessage();
	private IMessageService messageService =null;
	private IUserService userService =null;
	private IRoleService roleService =null;
	private UMSMessage message =null;
	private ISystemMessageService systemmessageService=null;
	
	/**
     * 获取 systemmessageService
     * @return 返回 systemmessageService
     */
    public ISystemMessageService getSystemmessageService()
    {
        return systemmessageService;
    }

    /**
     * 设置 systemmessageService
     * @param 对systemmessageService进行赋值
     */
    public void setSystemmessageService(ISystemMessageService systemmessageService)
    {
        this.systemmessageService = systemmessageService;
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
     * 获取 message
     * @return 返回 message
     */
    public UMSMessage getMessage()
    {
        return message;
    }

    /**
     * 设置 message
     * @param 对message进行赋值
     */
    public void setMessage(UMSMessage message)
    {
        this.message = message;
    }

    /**
	 * 重写方法
	 * 
	 * @return
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public UMSMessage getModel() {
		return message;
	}

	/**
	 * 获取 userService
	 * 
	 * @return 返回 userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * 设置 userService
	 * 
	 * @param 对userService进行赋值
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * 获取 messageService
	 * 
	 * @return 返回 messageService
	 */
	public IMessageService getMessageService() {
		return messageService;
	}

	/**
	 * 设置 messageService
	 * 
	 * @param 对messageService进行赋值
	 */
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	/**
     * 添加求助信息
     * @see [类、类#方法、类#成员]
     */
	public void add(){
	    String mescon=request.getParameter("mescon");
	    String mestitle=request.getParameter("mestitle");
	    String userId=this.getUserId();
	    String roleName="系统管理员";// 后台必须要添加系统管理员角色
	   // List<UMSUser> users=userService.getUserByRoleName(roleName);//系统管理员下面的用户不能太多
	   // String adminid="1";//如果查不到系统管理员的角色就默认发给角色id为1的角色
	   // if(users!=null&&users.size()>0){// 系统管理员只有一个用户
	   //    adminid=""+users.get(0).getUserId();//??? 默认发给管理员的1号用户，到时候从角色中选一个用户
	   // }
	    //for(int i=0;i<users.size();i++){//向系统管理员角色下的每个用户都发送系统消息
	    //    adminid=""+users.get(i).getUserId();
	    //    messageService.add(mescon,mestitle,userId,adminid);
	    //}
	    UMSRole role=roleService.getRoleByRoleName(roleName);
	    if(role!=null){
	        messageService.addMesToRole(mescon, mestitle, userId, role.getRoleId());
	    }
	}
	/**
	 *向某个角色发送发送系统信息(后期这个改造，添加了管理员向角色发送的系统表)
	 * @see [类、类#方法、类#成员]
	 */
	public void addSystemMes(){
	   // 0 是所有用户，1 普通用户 2 认证用户 3 vip用户
	    String roleId=request.getParameter("roleid");
	    System.out.println(roleId+"===================roleId");
	    String mestitle=request.getParameter("mestitle");
	    String mescon=request.getParameter("mescon");
	    String userId=this.getUserId();
	    String result="FAIL";
	    //向系统信息表中添加数据(roleId是拼接的角色的id)
	    systemmessageService.saveSystemMes(mestitle,mescon,userId,this.getUserName(),roleId);
        /*if("0".equals(roleId)){
            for(int i=1;i<4;i++){//1 表示普通用户 2 表示认证用户 3 表示vip用户
                 result= messageService.addSystemMes(mestitle,mescon,userId,i+""); 
            }
           
        }else{
            result= messageService.addSystemMes(mestitle,mescon,userId,roleId);
        }*/
	        String[] roleids=roleId.split(",");
	        for(String rid:roleids){
	            if(rid==null||"".equals(rid))continue;
	            result= messageService.addSystemMes(mestitle,mescon,userId,rid);
	        }
	        JSONObject jsonObject=new JSONObject();
	        jsonObject.accumulate("isSuccessOrfail", result);
	        if("SUCCESS".equals(result)){
	            jsonObject.accumulate("message", "发送消息成功"); 
	        }else{
	            jsonObject.accumulate("message", "发送消息失败");
	        }
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
     * 回复求助的信息
     * @see [类、类#方法、类#成员]
     */
	public void replyMes(){
	    String ids=request.getParameter("ids");//messageids
	    String replyCon=request.getParameter("returnMes");
	    String userId=this.getUserId();
	    String result="FAIL";
	    result=messageService.replyMes(replyCon,ids,userId);
	    JSONObject jsonObject=new JSONObject();
        jsonObject.accumulate("isSuccessOrfail", result);
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
	 * 分页获取当前用户的系统信息
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void getMessageByUserId() {
	    int status=Integer.parseInt(request.getParameter("status"));
		int currpage = Integer.parseInt(request.getParameter("currpage"));
		int perpage = Integer.parseInt(request.getParameter("perpage"));
		List<UMSMessage> messages = messageService.getMessageByUserId(
				Long.parseLong(this.getUserId()), (currpage - 1) * perpage,
				perpage,status);
		JSONArray jsonArray = new JSONArray();
		long total = messageService.getMessageCountByUserId(Long.parseLong(this.getUserId()),status);
		for (UMSMessage message : messages) {
			long userId = message.getMesauthor();
			String userName = userService.getUserNameByUserId(userId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("messAuthor", userName);
			jsonObject.accumulate("messageid", message.getUMSMessageid());
			jsonObject.accumulate("mescon", message.getMescon());
			// jsonObject.accumulate("meslev", message.getMeslev());
			// jsonObject.accumulate("returnMes", message.getReturnMes());
			jsonObject.accumulate("mestitle", message.getMestitle()==null?"无标题":message.getMestitle());
			jsonObject.accumulate("mestime", message.getMestime());
			jsonObject.accumulate("messtatus", message.getMesstatus());
			jsonObject.accumulate("mestype", message.getMestype());
			jsonObject.accumulate("total",total);
			// jsonObject.accumulate("remark", message.getRemark());
			jsonArray.add(jsonObject);
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(jsonArray.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return "success";
	}

	public void delMessageByUserIds() {
		String ids = request.getParameter("mesids");
		String reurunmes = messageService.delMessageByUserIds(
				Long.parseLong(this.getUserId()), ids);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(reurunmes);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void delHelpMessageByUserIds() {
		String ids = request.getParameter("mesids");
		String reurunmes = messageService.delHelpMessageByUserIds(
				Long.parseLong(this.getUserId()), ids);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(reurunmes);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 分页获取当前用户的求助信息
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void getHelpMessageByUserId() {
        int status=Integer.parseInt(request.getParameter("status"));
		int currpage = Integer.parseInt(request.getParameter("currpage"));
		int perpage = Integer.parseInt(request.getParameter("perpage"));
		List<UMSMessage> messages = messageService.getHelpMessageByUserId(
				Long.parseLong(this.getUserId()), (currpage - 1) * perpage,
				perpage,status);
		JSONArray jsonArray = new JSONArray();
		long total = messageService.getHelpMessageCountByUserId(Long.parseLong(this.getUserId()),status);
		
		for (UMSMessage message : messages) {
			//long userId = message.getMesauthor();
			// String userName = userService.getUserNameByUserId(userId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("reception", message.getReturnMes()==null?"管理员尚未回复":message.getUser().getUserName());
			jsonObject.accumulate("messageid", message.getUMSMessageid());
			jsonObject.accumulate("mescon", message.getMescon());
			jsonObject.accumulate("returnmes", message.getReturnMes()==null?"消息尚未回复":message.getReturnMes());
			// jsonObject.accumulate("meslev", message.getMeslev());
			// jsonObject.accumulate("returnMes", message.getReturnMes());
			jsonObject.accumulate("mestitle", message.getMestitle()==null?"无标题":message.getMestitle());
			jsonObject.accumulate("mestime", message.getMestime()); 
			jsonObject.accumulate("replytime", message.getReplytime()==null?"消息尚未回复":message.getReplytime());
			jsonObject.accumulate("messtatus", message.getMesstatus());
            jsonObject.accumulate("mestype", message.getMestype());
			jsonObject.accumulate("total",total);
			// jsonObject.accumulate("remark", message.getRemark());
			jsonArray.add(jsonObject);
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(jsonArray.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
     * 分页获取当前用户的求助信息
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void getSysHelpMessageByUserId() {
        int status=-1;
        int currpage = this.getPage();
        int perpage = this.getRows();
        List<UMSMessage> messages = messageService.getMessageByUserId(
                Long.parseLong(this.getUserId()), (currpage - 1) * perpage,
                perpage,status);
        JSONArray jsonArray = new JSONArray();
        long total = messageService.getMessageCountByUserId(Long.parseLong(this.getUserId()),status);
       // total = total%perpage>0?total/perpage+1:total/perpage;
        for (UMSMessage message : messages) {
            //long userId = message.getMesauthor();
            // String userName = userService.getUserNameByUserId(userId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("reception", message.getReturnMes()==null?"管理员尚未回复":message.getUser().getUserName());
            jsonObject.accumulate("UMSMessageid", message.getUMSMessageid());
            jsonObject.accumulate("mescon", message.getMescon());
            jsonObject.accumulate("returnmes", message.getReturnMes()==null?"消息尚未回复":message.getReturnMes());
            // jsonObject.accumulate("meslev", message.getMeslev());
            jsonObject.accumulate("returnMes", message.getReturnMes()==null?"未回复":message.getReturnMes());
            jsonObject.accumulate("mesauthor", messageService.getUserNameByUserId(message.getMesauthor()));
            jsonObject.accumulate("mestime", message.getMestime());
            jsonObject.accumulate("mestitle", message.getMestitle());
            jsonObject.accumulate("replytime", message.getReplytime()==null?"消息待回复":message.getReplytime());
            jsonObject.accumulate("total",total);
            // jsonObject.accumulate("remark", message.getRemark());
            jsonArray.add(jsonObject);
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(jsonArray.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 分页获取向系统管理员求助的信息(后台管理员页面展示)
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void getSysHelpMessage() {
        int status=-1;
        int currpage = this.getPage();
        int perpage = this.getRows();
        List<UMSMessage> messages = messageService.getSysMessage(
                Long.parseLong(this.getUserId()), (currpage - 1) * perpage,
                perpage,status);
        JSONArray jsonArray = new JSONArray();
        long total = messageService.getSysMessageCount(Long.parseLong(this.getUserId()),status);
       // total = total%perpage>0?total/perpage+1:total/perpage;
        for (UMSMessage message : messages) {
            //long userId = message.getMesauthor();
            // String userName = userService.getUserNameByUserId(userId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("reception", message.getReturnMes()==null?"管理员尚未回复":message.getUser().getUserName());
            jsonObject.accumulate("UMSMessageid", message.getUMSMessageid());
            jsonObject.accumulate("mescon", message.getMescon());
            jsonObject.accumulate("returnmes", message.getReturnMes()==null?"消息尚未回复":message.getReturnMes());
            // jsonObject.accumulate("meslev", message.getMeslev());
            jsonObject.accumulate("returnMes", message.getReturnMes()==null?"未回复":message.getReturnMes());
            jsonObject.accumulate("mesauthor", messageService.getUserNameByUserId(message.getMesauthor()));
            jsonObject.accumulate("mestime", message.getMestime());
            jsonObject.accumulate("mestitle", message.getMestitle());
            jsonObject.accumulate("replytime", message.getReplytime()==null?"消息待回复":message.getReplytime());
            jsonObject.accumulate("total",total);
            // jsonObject.accumulate("remark", message.getRemark());
            jsonArray.add(jsonObject);
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(jsonArray.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据mestype=1分页获取待审批的认证用户
     * 
     * 注意，如果replytime不为null则已经审批认证过了
     * @see [类、类#方法、类#成员]
     */
    public void getAuthenticationUsers(){
        //String userId=this.getUserId();//当前登录用户的id
        String roleid=roleService.getRoleByRoleName("系统管理员").getRoleId()+"";
        int currpage = this.getPage();
        int perpage = this.getRows();
        List<Map> list=messageService.getAuthenticationUsers(roleid,(currpage-1)*perpage,perpage);
        JSONArray jsonArray = new JSONArray();
        long total = messageService.getAuthenticationUsersCounts(Long.parseLong(roleid));
        //total = total%perpage>0?total/perpage+1:total/perpage;
        for (Map map:list) { 
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("rows", total);
            for(Iterator it=map.keySet().iterator();it.hasNext();){
                String key=(String)it.next();
            // String userName = userService.getUserNameByUserId(userId);
            jsonObject.accumulate(key, map.get(key));
          
           }
            jsonArray.add(jsonObject);
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(jsonArray.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获得待认证，还没回复认证的消息数目
     * @see [类、类#方法、类#成员]
     */
    public void getnewAuthoritynum(){
        JSONObject jsonObject=new JSONObject();
        long total=messageService.getnewAuthoritynum();
        jsonObject.put("total", total);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            jsonObject.put("isSuccessOrfail", "SUCCESS");
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获得向系统管理员的求助信息且尚未回复。
     * <一句话功能简述>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void getnewHelpnum(){
        JSONObject jsonObject=new JSONObject();
        long total=messageService.getnewHelpnum();
        jsonObject.put("total", total);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            jsonObject.put("isSuccessOrfail", "SUCCESS");
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 修改信息的状态，标记为已读状态
     * @see [类、类#方法、类#成员]
     */
    public void readMessage(){  
        String messageids=request.getParameter("messageid");
        String result= messageService.readMessage(messageids);
        JSONObject jsonObject = new JSONObject();  
        jsonObject.accumulate("isSuccessOrfail", result);
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
     * 系统管理员，批量删除认证用户的信息
     * 
     * @see [类、类#方法、类#成员]
     */
    public void batchDelAuthority(){
        String ids=request.getParameter("ids");
        String result=messageService.batchDelAuthority(ids);
        JSONObject jsonObject = new JSONObject();  
        jsonObject.accumulate("isSuccessOrfail", result);
        jsonObject.accumulate("message", "删除成功");
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
     * 个人页面获取当前用户新的系统消息的数目
     * @see [类、类#方法、类#成员]
     */
    public void getnewsysmesnum(){
        JSONObject jsonObject=new JSONObject();
        String userid= this.getUserId();
        long total=messageService.getnewsysmesnum(userid);
        jsonObject.put("total", total);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            jsonObject.put("isSuccessOrfail", "SUCCESS");
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获得求助信息的新的回复信息
     * @see [类、类#方法、类#成员]
     */
    public void getnewreturnhelpnum(){
        JSONObject jsonObject=new JSONObject();
        String userid= this.getUserId();
        long total=messageService.getnewreturnhelpnum(userid);
        jsonObject.put("total", total);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            jsonObject.put("isSuccessOrfail", "SUCCESS");
            out.print(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
