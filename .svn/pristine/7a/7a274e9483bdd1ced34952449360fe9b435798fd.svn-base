/*
 * 文 件 名:  SubscriptionServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.trsnj.ums.dao.IUMSSubscriptionDao;
import com.trsnj.ums.dao.impl.UMSSubscriptionDaoImpl;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSSubscription;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.ISubscriptionService;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.CommunicateWithWCM;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class SubscriptionServiceImpl implements ISubscriptionService
{
   // private IUMSSubscriptionDao subscriptiondao=new UMSSubscriptionDaoImpl();
    private IUMSSubscriptionDao subscriptiondao=null;
    /**
     * 获取 subscriptiondao
     * @return 返回 subscriptiondao
     */
    public IUMSSubscriptionDao getSubscriptiondao()
    {
        return subscriptiondao;
    }

    /**
     * 设置 subscriptiondao
     * @param 对subscriptiondao进行赋值
     */
    public void setSubscriptiondao(IUMSSubscriptionDao subscriptiondao)
    {
        this.subscriptiondao = subscriptiondao;
    }
    /**
     * 分页获取用户的订阅
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSSubscription> getSubscriptionsByUserId(long userId,int firstResult,int maxResult){
        
        return subscriptiondao.getSubscriptionByUserId(userId, firstResult, maxResult);
    }
    /**
     * 获取当前用户订阅的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getSubscriptionCountByUserId(long userId){
        
        return subscriptiondao.getSubscriptionCountByUserId(userId);
    }
    /**
     * 根据wcm的chnlid来获取栏目信息
     * @param docid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JSONArray getWcmChnlInfo(String chnlids){
        String url=CommonUtil.getValue("chnlurl");
        String str="";
        JSONArray jsonArray=null;
        try 
        {
          if("".equals(chnlids)){
              return jsonArray; 
          }
          str=CommunicateWithWCM.getchnlsinfo(chnlids, url);
          if("".equals(str)||str==null){
              return jsonArray;
          }
          jsonArray=JSONArray.fromObject(str);
          return jsonArray;
          
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
        return jsonArray;
    }
    /**
     * 批量删除
     * @param userId
     * @param ids
     * @see com.trsnj.ums.service.ISubscriptionService#delSubscriptionByIds(long, java.lang.String)
     */
    public String delSubscriptionByIds(long userId, String ids){
        return subscriptiondao.delSubscriptionByIds(userId, ids);
    }
    /**
     * 获取当前用户订阅的前三个栏目，并取三个栏目下面的文档信息
     * @param userId
     * @param count 获取栏目下文档的数目，默认是2
     * @return
     * @see com.trsnj.ums.service.ISubscriptionService#docInfoInSubscription(long)
     */
    public JSONObject docInfoInSubscription(long userId,String count){
        String url=CommonUtil.getValue("docinfochnlurl");
        JSONObject jsonArray=new JSONObject();
        int c =Integer.parseInt(count);// 
        // 获得用户订阅的前三个栏目
        List<UMSSubscription> lists=subscriptiondao.getSubscriptionByUserId(userId, 0, 3);
        int cou=0;
        String ids="";
        for(UMSSubscription subscription:lists){
            ids+=subscription.getSubchnl();
            if(cou!=lists.size()-1){
                ids+=",";
            }
            cou++;
        }
        if(cou==1){
           count="6"; 
        }else if(cou==2){
            count="3";
        }else if(cou==3){
            count="2";
        }
       try
    { 
       String str= CommunicateWithWCM.getdocsinfoinchnl(ids, url, Integer.parseInt(count));
       if(str==null||"".equals(str)||"chnlids is null".equals(str)){
           String result="FAIL";
           str="{'isSuccessOrfail':'"+result+"'}";
       }
       jsonArray=jsonArray.fromObject(str);
    }
    catch (NumberFormatException e)
    {
        e.printStackTrace();
    }
    catch (UnsupportedEncodingException e)
    {
        e.printStackTrace();
    }
       
        return jsonArray;
    }
    /**
     * 栏目订阅
     * @param chnlid
     * @param chnldesc
     * @param userId
     * @see com.trsnj.ums.service.ISubscriptionService#chnlsub(java.lang.String, java.lang.String, java.lang.String)
     */
    public String chnlsub(String chnlid, String chnldesc, String userId){
        //验证是否重复订阅
        UMSSubscription s=subscriptiondao.getSubscriptionByChnlid(userId,chnlid);
        if(s!=null){
            return "已订阅";
        }
        UMSSubscription subscription=new UMSSubscription();
        subscription.setChnldesc(chnldesc);
        subscription.setSubchnl(Integer.parseInt(chnlid));
        subscription.setSubstatus(1);
        subscription.setSubtime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userId));
        subscription.setUser(user);
        subscriptiondao.save(subscription);
        return "订阅成功";
    }
}
