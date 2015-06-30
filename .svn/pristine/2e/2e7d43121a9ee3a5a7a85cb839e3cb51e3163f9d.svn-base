/*
 * 文 件 名:  CollectServiceImpl.java
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

import com.trsnj.ums.dao.IUMSCollectDao;
import com.trsnj.ums.dao.impl.UMSCollectDaoImpl;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.ICollectService;
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
public class CollectServiceImpl implements ICollectService
{
   // private IUMSCollectDao collectdao=new UMSCollectDaoImpl();
    private IUMSCollectDao collectdao=null;
    /**
     * 获取 collectdao
     * @return 返回 collectdao
     */
    public IUMSCollectDao getCollectdao()
    {
        return collectdao;
    }

    /**
     * 设置 collectdao
     * @param 对collectdao进行赋值
     */
    public void setCollectdao(IUMSCollectDao collectdao)
    {
        this.collectdao = collectdao;
    }
    /**
     * 分页获取用户的收藏
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSCollect> getCollectsByUserId(long userId,int firstResult,int maxResult){
        
        return collectdao.getCollectByUserId(userId, firstResult, maxResult);
    }
    /**
     * 获取当前用户收藏的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getCollectCountByUserId(long userId){
        
        return collectdao.getCollectCountByUserId(userId);
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
          System.out.println(docids+"||docids===||"+url+"||====向wcm请求前，没有str返回");
          str=CommunicateWithWCM.getdocsinfo(docids, url);
          System.out.println(docids+"||docids===||"+url+"||====||"+str+"==========收藏取的数据");
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
    public String delCollectByIds(long userId,String ids){
        
        return collectdao.delCollectByIds(userId, ids);
    }
    /**
     * 获得热门推荐
     * @param userId
     * @param firstResult
     * @param maxResult
     * @return
     * @see com.trsnj.ums.service.ICollectService#hotRecommendDoc(java.lang.String, java.lang.String, java.lang.String)
     */
    public JSONArray hotRecommendDoc(String userId, String firstResult, String maxResult){
        int first=Integer.parseInt(firstResult);
        int max=Integer.parseInt(maxResult);
        long userid=Long.parseLong(userId);
        List<String> listdocid=collectdao.hotRecommendDoc(userid,first,max);
        int cou=0;
        String ids="";
        for(String id:listdocid){
            ids+=id;
            if(cou!=listdocid.size()-1){
                ids+=",";
            }
            cou++;
        }
        JSONArray jsonArray=this.getWcmDocInfo(ids);
        return jsonArray;
    }
    /**
     * wcm 前台收藏文档
     * @param doctitle
     * @param docid
     * @param userId
     * @return
     * @see com.trsnj.ums.service.ICollectService#saveCollectFront(java.lang.String, java.lang.String, java.lang.String)
     */
    public String saveCollectFront(String doctitle, String docid, String userId){
        //如果文章已收藏，不重复收藏
        UMSCollect c=collectdao.getCollectByUserIdAndDocid(docid,userId);
        if(c!=null){
            return "改文章已收藏";
        }
        UMSCollect collect=new UMSCollect();
        collect.setColstatus(1);
        collect.setColtime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        collect.setDocid(Long.parseLong(docid));
        collect.setDoctitle(doctitle);
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userId));
        collect.setUser(user);
        collectdao.save(collect);
        return "收藏成功";
    }
}
