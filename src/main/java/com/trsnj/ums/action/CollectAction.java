/*
 * 文 件 名:  CollectAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.ICollectService;
import com.trsnj.ums.service.IUserService;
import com.trsnj.ums.service.impl.CollectServiceImpl;
import com.trsnj.ums.util.BaseAction;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.CommunicateWithWCM;
import com.trsnj.ums.util.MD5Util;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CollectAction extends BaseAction implements ModelDriven<UMSCollect>
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7156059444224608648L;
    //private ICollectService collectService=new CollectServiceImpl();
   // private UMSCollect collect=new UMSCollect();
    private ICollectService collectService=null;
    private IUserService userService =null;
    private UMSCollect collect=null;
    
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
     * 获取 collect
     * @return 返回 collect
     */
    public UMSCollect getCollect()
    {
        return collect;
    }
    /**
     * 设置 collect
     * @param 对collect进行赋值
     */
    public void setCollect(UMSCollect collect)
    {
        this.collect = collect;
    }
    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSCollect getModel()
    {
        return collect;
    }
    /**
     * 获取 collectService
     * @return 返回 collectService
     */
    public ICollectService getCollectService()
    {
        return collectService;
    }
    /**
     * 设置 collectService
     * @param 对collectService进行赋值
     */
    public void setCollectService(ICollectService collectService)
    {
        this.collectService = collectService;
    }
    /**
     * 根据用户id分页获取搜藏信息
     * @see [类、类#方法、类#成员]
     */
    public void getCollectByUserId(){
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int perpage = Integer.parseInt(request.getParameter("perpage"));
        long userId=Long.parseLong(this.getUserId());
        List<UMSCollect> collects=collectService.getCollectsByUserId(userId, (currpage-1)*perpage, perpage);
        JSONArray jsonArray = new JSONArray();
        long total = collectService.getCollectCountByUserId(userId);
       // total = total%perpage>0?total/perpage+1:total/perpage;
        String ids="";
        int count=0;
        for(UMSCollect collect:collects){
            ids+=collect.getDocid();
            if(count!=collects.size()-1){
                ids+=",";
            }
            count++;
        }
        System.out.println(ids+"==========collectaction============ids");
        JSONArray jsondocs=collectService.getWcmDocInfo(ids);
        System.out.println("================================");
        System.out.println(jsondocs==null);
        for(UMSCollect collect:collects){
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("total",total);
            jsonObject.accumulate("colstatus", collect.getColstatus());
            jsonObject.accumulate("collectid", collect.getUMSCollectid());
            jsonObject.accumulate("coltime", collect.getColtime());
            jsonObject.accumulate("remark", collect.getRemark());
            //根据docid来请求wcm的jsp
            boolean flag=false;//判断当前docid是否存在
            //jsondocs !=null????????
            if(jsondocs!=null&&jsondocs.size()>0){
            for(int i=0;i<jsondocs.size();i++){
                JSONObject jo=jsondocs.getJSONObject(i);
                if(jo.getString("docid").equals(collect.getDocid()+"")){
                    jsonObject.accumulate("docid", jo.getString("docid"));
                    jsonObject.accumulate("doctitle", jo.getString("doctitle")); 
                    jsonObject.accumulate("puburl", jo.getString("puburl"));
                    jsonObject.accumulate("chnlname", jo.getString("chnlname"));
                    flag=true;
                    break;
                }
            }
          }   
            if(!flag){
                jsonObject.accumulate("docid", collect.getDocid());
                jsonObject.accumulate("doctitle", collect.getDoctitle()); 
                jsonObject.accumulate("puburl", "#");
                jsonObject.accumulate("chnlname", "该文档已删除");
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
     * 批量删除
     * @see [类、类#方法、类#成员]
     */
    public void delCollectByIds(){
        String userId=this.getUserId();
        String ids=request.getParameter("ids");
        collectService.delCollectByIds(Long.parseLong(userId), ids);
    }
    /**
     * 热门推荐文档，不包括自己收藏的文章
     * @see [类、类#方法、类#成员]
     */
    public void hotRecommendDoc(){
        //select docid ,count(docid) from umscollect where docid not in (select docid from umscollect where userid =3)  group by(docid) order by count(docid) desc
       String userId=this.getUserId();
       String firstResult=request.getParameter("firstResult")==null?"0":request.getParameter("firstResult");
       String maxResult=request.getParameter("maxResult")==null?"5":request.getParameter("maxResult");
       JSONArray jsonArray=collectService.hotRecommendDoc(userId,firstResult,maxResult);
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
     * 针对wcm的方法
     * wcm 文章页面判断是否登陆的action，和收藏
     * @see [类、类#方法、类#成员]
     */
    public void loginfront(){
        String callbackFunName=request.getParameter("callbackparam");
        System.out.println(callbackFunName+"==========callbackfunname");
        String doctitle="";
        try
        {
            doctitle = CommonUtil.deEncoding(request.getParameter("doctitle"));//一个参数解码成UTF-8
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }//encodeURI解码
        String docid=request.getParameter("docid");
        String userName=(String)session.get("userName");
        String userId=(Long)session.get("userId")+"";
        System.out.println(doctitle+docid+"进入=============loginfront"+userName+userId);
        JSONObject jsonObject=new JSONObject();
        if(userName!=null){
            //  保存收藏文档
           String result= collectService.saveCollectFront(doctitle,docid,userId);
            jsonObject.accumulate("isSuccessOrfail", "SUCCESS");
            jsonObject.accumulate("message", "已登陆");
            jsonObject.accumulate("result", result);
        }else{
            jsonObject.accumulate("isSuccessOrfail", "FAIL");
            jsonObject.accumulate("message", "尚未登陆");
            jsonObject.accumulate("result", "fail");
        }
        PrintWriter out;
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
           // System.out.println(jsonObject.toString());
            out.print(callbackFunName+"("+jsonObject.toString()+")");
           // System.out.println("end============");
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 针对wcm的方法
     * wcm 文章页面登陆的action
     * @see [类、类#方法、类#成员]
     */
    public void logindaofront(){
        String callbackFunName=request.getParameter("callbackparam");
        String username="";
        try
        {
            username = CommonUtil.deEncoding(request.getParameter("username"));
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        String password=request.getParameter("password");
        // 根据用户名查询查用户
        UMSUser u=userService.getUserByName(username);
        PrintWriter out;
        JSONObject jsonObject=new JSONObject();
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            if(u==null){
                //用户名不存在
                jsonObject.accumulate("isSuccessOrfail", "FAIL");
                jsonObject.accumulate("message", "用户名或密码不正确");
                out.print(callbackFunName+"("+jsonObject.toString()+")");
                return;
            }else{ 
                String pw=password;
                pw=MD5Util.getSecretStr(pw); 
                if(!pw.equals(u.getPassWord())){
                    jsonObject.accumulate("isSuccessOrfail", "FAIL");
                    jsonObject.accumulate("message", "用户名或密码不正确");
                    out.print(callbackFunName+"("+jsonObject.toString()+")");
                    return;
                }else{
                    if(u.getType()==0){
                        jsonObject.accumulate("isSuccessOrfail", "FAIL");
                        jsonObject.accumulate("message", "用户尚未激活");
                        out.print(callbackFunName+"("+jsonObject.toString()+")");
                       return;
                    }
                }
            }
            javax.servlet.http.Cookie[] cookies = request.getCookies();
            String sessionid="";
            for(javax.servlet.http.Cookie cookie:cookies){
                System.out.println(cookie.getName()+"------->"+cookie.getValue());
                if(cookie.getName().equals("JSESSIONID")){
                    sessionid=cookie.getValue();
                }
            }
            session.put("userName",u.getUserName());//前台通过session.getValue(key);来获取session的值
            session.put("userId",u.getUserId());
            session.put("roleId", u.getUmsrole().getRoleId());
            jsonObject.accumulate("isSuccessOrfail", "SUCCESS");
            jsonObject.accumulate("userName", u.getUserName());
            jsonObject.accumulate("userId", u.getUserId());
            jsonObject.accumulate("sessionid", sessionid);
            jsonObject.accumulate("message", "登陆成功");
            out.print(callbackFunName+"("+jsonObject.toString()+")");
            System.out.println("jsonobject============"+jsonObject.toString());
            System.out.println("end============");
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
