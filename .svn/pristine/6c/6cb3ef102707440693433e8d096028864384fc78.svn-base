/*
 * 文 件 名:  ShareServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-31
 */
package com.trsnj.ums.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.trsnj.ums.dao.IShareDao;
import com.trsnj.ums.dao.impl.UMSShareDaoImpl;
import com.trsnj.ums.pojo.UMSShare;
import com.trsnj.ums.pojo.UMSSubscription;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IShareService;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.CommunicateWithWCM;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-31]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class ShareServiceImpl implements IShareService
{
    //private IShareDao sharedao=new UMSShareDaoImpl();
    private IShareDao sharedao=null;
    
    /**
     * 获取 sharedao
     * @return 返回 sharedao
     */
    public IShareDao getSharedao()
    {
        return sharedao;
    }

    /**
     * 设置 sharedao
     * @param 对sharedao进行赋值
     */
    public void setSharedao(IShareDao sharedao)
    {
        this.sharedao = sharedao;
    }
    /**
     * 分页获取当前用户的分享信息
     * @param userId
     * @param firstResult
     * @param maxResult
     * @return
     * @see com.trsnj.ums.service.IShareService#getSharesByUserId(long, int, int)
     */
    public List<UMSShare> getSharesByUserId(long userId, int firstResult, int maxResult){
        
        return sharedao.getSharesByUserId(userId, firstResult, maxResult);
    }
    /**
     * 获得当前用户的总数
     * @param userId
     * @return
     * @see com.trsnj.ums.service.IShareService#getShareCountByUserId(long)
     */
    public long getShareCountByUserId(long userId){
        return sharedao.getShareCountByUserId(userId);
    }
    
    
    /**
     * 根据wcm的docid来获取wcm的文档信息
     * @param docid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JSONArray getWcmDocInfo(String docids){
        String url=CommonUtil.getValue("docurl");
        String str="";
        JSONArray jsonArray=null;
        try 
        {
          if("".equals(docids)){
              return jsonArray; 
          }
          str=CommunicateWithWCM.getdocsinfo(docids, url);
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
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delShareByIds(long userId,String ids){
        
        return sharedao.delShareByIds(userId, ids);
    }
    /**
     * 与wcm交互的首页热门分享
     * @param firstResult
     * @param maxResult
     * @return
     * @see com.trsnj.ums.service.IShareService#hotShareDoc(java.lang.String, java.lang.String)
     */
    public JSONArray hotShareDoc(String userId,String firstResult,String maxResult){
        int first=Integer.parseInt(firstResult);
        int max=Integer.parseInt(maxResult);
        List<Map> listdocid=sharedao.hotShareDoc(Long.parseLong(userId),first,max);
        int cou=0;
        String ids="";
        for(Map map:listdocid){
            ids+=map.get("docid");
            if(cou!=listdocid.size()-1){
                ids+=",";
            }
            cou++;
        }
        JSONArray jsonArray=this.getWcmDocInfo(ids);
        for(int i=0;jsonArray!=null&&i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            for(Map map:listdocid){
                String docid=(String)map.get("docid");
                if(docid.equals(jsonObject.getString("docid"))){
                    jsonObject.accumulate("sharecount", (String)map.get("sharecount"));
                }
            }
        }
        return jsonArray;
    }
    /**
     * 分享文章
     * @param sharedocid
     * @param sharedoctitle
     * @param where
     * @see com.trsnj.ums.service.IShareService#shareDoc(java.lang.String, java.lang.String, java.lang.String)
     */
    public void shareDoc(String sharedocid, String sharedoctitle, String where,String userId){
        UMSShare share=new UMSShare();
        share.setDocid(Long.parseLong(sharedocid));
        share.setDoctitle(sharedoctitle);
        share.setSharetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        share.setSwhere(where);
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userId));
        share.setUser(user);
        sharedao.save(share);
    }
}
