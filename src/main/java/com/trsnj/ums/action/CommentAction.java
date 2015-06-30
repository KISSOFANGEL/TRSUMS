/*
 * 文 件 名:  CommentAction.java
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
import com.trsnj.ums.pojo.UMSComment;
import com.trsnj.ums.pojo.UMSShare;
import com.trsnj.ums.service.ICommentService;
import com.trsnj.ums.service.impl.CommentService;
import com.trsnj.ums.util.BaseAction;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.MD5Util;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CommentAction extends BaseAction implements ModelDriven<UMSComment>
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1638296610064704956L;
    //后面都要spring注入的
   // private ICommentService commentService=new CommentService();
   // private UMSComment comment=new UMSComment();
    private ICommentService commentService=null;
    private UMSComment comment=null;
    
    /**
     * 获取 comment
     * @return 返回 comment
     */
    public UMSComment getComment()
    {
        return comment;
    }

    /**
     * 设置 comment
     * @param 对comment进行赋值
     */
    public void setComment(UMSComment comment)
    {
        this.comment = comment;
    }

    /**
     * 获取 commentService
     * @return 返回 commentService
     */
    public ICommentService getCommentService()
    {
        return commentService;
    }

    /**
     * 设置 commentService
     * @param 对commentService进行赋值
     */
    public void setCommentService(ICommentService commentService)
    {
        this.commentService = commentService;
    }

    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSComment getModel()
    {
        return comment;
    }
    
    /**
     * 根据用户id分页获取品论信息
     * @see [类、类#方法、类#成员]
     */
    public void getCommentByUserId(){
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int perpage = Integer.parseInt(request.getParameter("perpage"));
        long userId=Long.parseLong(this.getUserId());
        List<UMSComment> comments=commentService.getCommentByUserId(userId, (currpage-1)*perpage, perpage);
        JSONArray jsonArray = new JSONArray();
        long total = commentService.getCommentCountByUserId(userId);
       // total = total%perpage>0?total/perpage+1:total/perpage;
        String ids="";
        int count=0;
        for(UMSComment comment:comments){
            ids+=comment.getDocid();
            if(count!=comments.size()-1){
                ids+=",";
            }
            count++;
        } 
        JSONArray jsondocs=commentService.getWcmDocInfo(ids);
        for(UMSComment comment:comments){
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("total",total);
            jsonObject.accumulate("content", comment.getContent());
            jsonObject.accumulate("comtime", comment.getComtime());
            jsonObject.accumulate("commentid", comment.getUMSCommentid());
            jsonObject.accumulate("comstatus", comment.getComstatus());
            jsonObject.accumulate("remark", comment.getRemark());
            //根据docid来请求wcm的jsp
            boolean flag=false;//判断当前docid是否存在
            //jsondocs !=null????????
            if(jsondocs!=null&&jsondocs.size()>0){
            for(int i=0;i<jsondocs.size();i++){
                JSONObject jo=jsondocs.getJSONObject(i);
                if(jo.getString("docid").equals(comment.getDocid()+"")){
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
                jsonObject.accumulate("docid", comment.getDocid());
                jsonObject.accumulate("doctitle", comment.getDoctitile()); 
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
     * 分页获得当前文章的评论
     * @see [类、类#方法、类#成员]
     */
    public void getCommentByDocId(){
        String callbackFunName=request.getParameter("callbackparam");
        String docid=request.getParameter("docid");
        String pagecount=request.getParameter("pagecount");
        String pagenum=request.getParameter("pagenum");
        List<UMSComment> comments=commentService.getCommentByDocId(docid,pagecount,pagenum);
        long count=commentService.getCommentCountsByDocId(docid);
        JSONObject jo=new JSONObject();
       
        jo.accumulate("count", count);
        if(comments!=null&&comments.size()>0){
            jo.accumulate("isSuccessOrfail", "SUCCESS");
            JSONArray jsonArray=new JSONArray();
            for(UMSComment comment:comments)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.accumulate("comstatus", comment.getComstatus());
                jsonObject.accumulate("UMSCommentid", comment.getUMSCommentid());
                jsonObject.accumulate("comtime", comment.getComtime());
                jsonObject.accumulate("content", comment.getContent());
                jsonObject.accumulate("remark", comment.getRemark());
                jsonObject.accumulate("userName", comment.getUser().getUserName());//修改了原来的懒加载为不懒加载
                jsonArray.add(jsonObject);
            }
            jo.accumulate("rows", jsonArray);
            jo.accumulate("pagecount", comments.size());
        }else{
            jo.accumulate("isSuccessOrfail", "FAIL");//一个都没查询到品论数
           
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(callbackFunName+"("+jo.toString()+")");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 批量删除
     * @see [类、类#方法、类#成员]
     */
    public void delCommentByIds(){
        String userId=this.getUserId();
        String ids=request.getParameter("ids");
        commentService.delCommentByIds(Long.parseLong(userId), ids);
    }
    /**
     * 页面提交评论
     * @see [类、类#方法、类#成员]
     */
    public void commentSubmit(){
        System.out.println("=============commentsubmit");
        String commentcon="";
        String doctitle="";
        try
        {
            commentcon = CommonUtil.deEncoding(request.getParameter("commentcon"));
            doctitle=CommonUtil.deEncoding(request.getParameter("doctitle"));
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        String docid=request.getParameter("docid");
        String callbackFunName=request.getParameter("callbackparam");
        String userId=session.get("userId")+"";
        String result="";
        JSONObject jsonObject=new JSONObject();
        PrintWriter out;
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            if(userId==null||"null".equals(userId)){
                jsonObject.accumulate("isSuccessOrfail", "FAIL"); 
                jsonObject.accumulate("message", "未登陆");
                out.print(callbackFunName+"("+jsonObject.toString()+")");
                return;
             }else{
                result=commentService.saveCommentFront(userId,docid,doctitle,commentcon);
                jsonObject.accumulate("isSuccessOrfail", "SUCCESS"); 
                jsonObject.accumulate("message", result);
                out.print(callbackFunName+"("+jsonObject.toString()+")");
                return;
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
