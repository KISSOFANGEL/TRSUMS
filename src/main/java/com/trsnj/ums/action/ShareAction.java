/*
 * 文 件 名:  ShareAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-31
 */
package com.trsnj.ums.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSShare;
import com.trsnj.ums.service.IShareService;
import com.trsnj.ums.service.impl.ShareServiceImpl;
import com.trsnj.ums.util.BaseAction;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-31]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class ShareAction extends BaseAction implements ModelDriven<UMSShare>
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5893615818154381192L;
    //private IShareService shareService=new ShareServiceImpl();
    //private UMSShare share=new UMSShare();
    private IShareService shareService=null;
    private UMSShare share=null;
    
    /**
     * 获取 share
     * @return 返回 share
     */
    public UMSShare getShare()
    {
        return share;
    }
    /**
     * 设置 share
     * @param 对share进行赋值
     */
    public void setShare(UMSShare share)
    {
        this.share = share;
    }
    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSShare getModel()
    {
        return share;
    }
    /**
     * 获取 shareService
     * @return 返回 shareService
     */
    public IShareService getShareService()
    {
        return shareService;
    }
    /**
     * 设置 shareService
     * @param 对shareService进行赋值
     */
    public void setShareService(IShareService shareService)
    {
        this.shareService = shareService;
    }
    
    /**
     * 根据用户id分页获取分享信息
     * @see [类、类#方法、类#成员]
     */
    public void getShareByUserId(){
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int perpage = Integer.parseInt(request.getParameter("perpage"));
        long userId=Long.parseLong(this.getUserId());
        List<UMSShare> shares=shareService.getSharesByUserId(userId, (currpage-1)*perpage, perpage);
        JSONArray jsonArray = new JSONArray();
        long total = shareService.getShareCountByUserId(userId);
        //total = total%perpage>0?total/perpage+1:total/perpage;
        String ids="";
        int count=0;
        for(UMSShare share:shares){
            ids+=share.getDocid();
            if(count!=shares.size()-1){
                ids+=",";
            }
            count++;
        } 
        JSONArray jsondocs=shareService.getWcmDocInfo(ids);
        for(UMSShare share:shares){
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("total",total);
            jsonObject.accumulate("surl", share.getSurl());
            jsonObject.accumulate("swhere", share.getSwhere());
            jsonObject.accumulate("shareid", share.getUMSShareid());
            jsonObject.accumulate("sharetime", share.getSharetime());
            jsonObject.accumulate("remark", share.getRemark());
            //根据docid来请求wcm的jsp
            boolean flag=false;//判断当前docid是否存在
            //jsondocs !=null????????
            if(jsondocs!=null&&jsondocs.size()>0){
            for(int i=0;i<jsondocs.size();i++){
                JSONObject jo=jsondocs.getJSONObject(i);
                if(jo.getString("docid").equals(share.getDocid()+"")){
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
                jsonObject.accumulate("docid", share.getDocid());
                jsonObject.accumulate("doctitle", share.getDoctitle()); 
                jsonObject.accumulate("puburl", "#");
                jsonObject.accumulate("chnlname", "改文档已删除");
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
    public void delShareByIds(){
        String userId=this.getUserId();
        String ids=request.getParameter("ids");
        shareService.delShareByIds(Long.parseLong(userId), ids);
    }
    /**
     * 首页热门分享
     * @see [类、类#方法、类#成员]
     */
    public void hotShareDoc(){
        String userId=this.getUserId();
        String firstResult=request.getParameter("firstResult")==null?"0":request.getParameter("firstResult");
        String maxResult=request.getParameter("maxResult")==null?"5":request.getParameter("maxResult");
        //select docid from umsshare group by(docid) order by count(docid) desc
        JSONArray jsonArray=shareService.hotShareDoc(userId,firstResult,maxResult);
       if(jsonArray==null||jsonArray.size()==0){
    	   jsonArray = new JSONArray();
           JSONObject jsonObject=new JSONObject();
           jsonObject.accumulate("isSuccessOrfail", "FAIL");
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
     * 前台分享
     * @see [类、类#方法、类#成员]
     */
    public void shareDoc(){
        String callbackFunName=request.getParameter("callbackparam");
        String sharedocid=request.getParameter("sharedocid");
        String sharedoctitle="";
        String where="";
        try
        {
            sharedoctitle = CommonUtil.deEncoding(request.getParameter("sharedoctitle"));
            where=CommonUtil.deEncoding(request.getParameter("where"));
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        String userId=session.get("userId")+"";
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
            shareService.shareDoc(sharedocid,sharedoctitle,where,userId);
            jsonObject.accumulate("isSuccessOrfail", "SUCCESS");
            jsonObject.accumulate("message", "分享成功");
            out.print(callbackFunName+"("+jsonObject.toString()+")");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
