/*
 * 文 件 名:  SubscriptionAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSSubscription;
import com.trsnj.ums.service.ISubscriptionService;
import com.trsnj.ums.service.impl.SubscriptionServiceImpl;
import com.trsnj.ums.util.BaseAction;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class SubscriptionAction extends BaseAction implements ModelDriven<UMSSubscription>
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4609619615800152142L;
   // private UMSSubscription subscription=new UMSSubscription();
   // private ISubscriptionService subscriptionService=new SubscriptionServiceImpl();
    private UMSSubscription subscription=null;
    private ISubscriptionService subscriptionService=null;
    
    /**
     * 获取 subscription
     * @return 返回 subscription
     */
    public UMSSubscription getSubscription()
    {
        return subscription;
    }
    /**
     * 设置 subscription
     * @param 对subscription进行赋值
     */
    public void setSubscription(UMSSubscription subscription)
    {
        this.subscription = subscription;
    }
    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSSubscription getModel()
    {
        return subscription;
    }
    /**
     * 获取 subscriptionService
     * @return 返回 subscriptionService
     */
    public ISubscriptionService getSubscriptionService()
    {
        return subscriptionService;
    }
    /**
     * 设置 subscriptionService
     * @param 对subscriptionService进行赋值
     */
    public void setSubscriptionService(ISubscriptionService subscriptionService)
    {
        this.subscriptionService = subscriptionService;
    }
    
    /**
     * 根据用户id分页获取订阅信息
     * @see [类、类#方法、类#成员]
     */
    public void getSubscriptionByUserId(){
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int perpage = Integer.parseInt(request.getParameter("perpage"));
        long userId=Long.parseLong(this.getUserId());
        List<UMSSubscription> subscriptions=subscriptionService.getSubscriptionsByUserId(userId, (currpage-1)*perpage, perpage);
        JSONArray jsonArray = new JSONArray();
        long total = subscriptionService.getSubscriptionCountByUserId(userId);
        //total = total%perpage>0?total/perpage+1:total/perpage;
        String ids="";
        int count=0;
        for(UMSSubscription subscription:subscriptions){
            ids+=subscription.getSubchnl();
            if(count!=subscriptions.size()-1){
                ids+=",";
            }
            count++;
        }
        JSONArray jsondocs=subscriptionService.getWcmChnlInfo(ids);
        for(UMSSubscription subscription:subscriptions){
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("total",total);
            jsonObject.accumulate("substatus", subscription.getSubstatus());
            jsonObject.accumulate("subid", subscription.getUMSSubscriptionid());
            jsonObject.accumulate("subtime", subscription.getSubtime());
            jsonObject.accumulate("remark", subscription.getRemark());
            //根据docid来请求wcm的jsp
            boolean flag=false;//判断当前docid是否存在
            //jsondocs !=null????????
            if(jsondocs!=null&&jsondocs.size()>0){
            for(int i=0;i<jsondocs.size();i++){
                JSONObject jo=jsondocs.getJSONObject(i);
                if(jo.getString("chnlid").equals(subscription.getSubchnl()+"")){
                    jsonObject.accumulate("chnlid", jo.getString("chnlid"));
                    jsonObject.accumulate("chnlname", jo.getString("chnlname")); 
                  //  jsonObject.accumulate("puburl", jo.getString("puburl"));
                    flag=true;
                    break;
                }
            }
          }   
            if(!flag){
                jsonObject.accumulate("chnlid", subscription.getSubchnl());
                jsonObject.accumulate("chnlname", subscription.getChnldesc()); 
                jsonObject.accumulate("puburl", "#");
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
    public void delSubscriptionByIds(){
        String userId=this.getUserId();
        String ids=request.getParameter("ids");
        subscriptionService.delSubscriptionByIds(Long.parseLong(userId), ids);
    }
    /**
     * 前台订阅简讯
     * 
     * 查询当前用户的订阅栏目信息，并根据栏目信息获取最新的文章信息
     * @see [类、类#方法、类#成员]
     */
    public void docInfoInSubscription(){
        long userId=Long.parseLong(this.getUserId());
        String count=request.getParameter("count")==null?"2":request.getParameter("count");
       JSONObject jsonObject= subscriptionService.docInfoInSubscription(userId,count);
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
     * 前台栏目订阅
     * @see [类、类#方法、类#成员]
     */
    public void chnlsub(){
        System.out.println("==============chnl");
       String chnlid=request.getParameter("chnlid"); 
       String chnldesc="";
    try
    {
        chnldesc = CommonUtil.deEncoding(request.getParameter("chnldesc"));
    }
    catch (UnsupportedEncodingException e1)
    {
        e1.printStackTrace();
    }
       String callbackFunName=request.getParameter("callbackparam");
       String userId=session.get("userId")+"";//userid="null" 
       JSONObject jsonObject=new JSONObject();
       response.setCharacterEncoding("UTF-8");
       PrintWriter out;
       try {
           out = response.getWriter();
           if(userId==null||"null".equals(userId)){
               jsonObject.accumulate("isSuccessOrfail", "FAIL");
               jsonObject.accumulate("message", "未登陆");
               out.print(callbackFunName+"("+jsonObject.toString()+")");
               return;
           }
          String result= subscriptionService.chnlsub(chnlid,chnldesc,userId);
           jsonObject.accumulate("isSuccessOrfail", "SUCCESS");
           jsonObject.accumulate("message", result);
           out.print(callbackFunName+"("+jsonObject.toString()+")");
           out.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
       
       
    }
    
    
}
