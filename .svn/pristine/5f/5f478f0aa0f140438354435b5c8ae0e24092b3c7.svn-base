/*
 * 文 件 名:  CommentService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-24
 */
package com.trsnj.ums.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.trsnj.ums.dao.IUMSCommentDao;
import com.trsnj.ums.dao.impl.UMSCommentDaoImpl;
import com.trsnj.ums.pojo.UMSComment;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.ICommentService;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.CommunicateWithWCM;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CommentService implements ICommentService
{
   // private IUMSCommentDao commentdao=new UMSCommentDaoImpl();
    private IUMSCommentDao commentdao=null;
    
    
    /**
     * 获取 commentdao
     * @return 返回 commentdao
     */
    public IUMSCommentDao getCommentdao()
    {
        return commentdao;
    }
    /**
     * 设置 commentdao
     * @param 对commentdao进行赋值
     */
    public void setCommentdao(IUMSCommentDao commentdao)
    {
        this.commentdao = commentdao;
    }
    /**
     * 分页获取当前的用户的评论信息
     * @param userid
     * @param firstResult
     * @param maxResult
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSComment> getCommentByUserId(long userid,int firstResult,int maxResult){
        
        return commentdao.getCommentByUserId(userid, firstResult, maxResult);
    }
    /**
     * 获取当前用户品论的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getCommentCountByUserId(long userId){
        
        return commentdao.getCommentCountByUserId(userId);
    }
    
    /**
     * 批量删除
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delCommentByIds(long userId,String ids){
        
        return commentdao.delCommentByIds(userId, ids);
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
     * 保存用户品论的文章
     * @param userId
     * @param docid
     * @param commentcon
     * @return
     * @see com.trsnj.ums.service.ICommentService#saveCommentFront(java.lang.String, java.lang.String, java.lang.String)
     */
    public String saveCommentFront(String userId, String docid,String doctitle, String commentcon){
        UMSComment comment =new UMSComment();
        comment.setComstatus(1);
        comment.setComtime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        comment.setContent(commentcon);
        comment.setDocid(Long.parseLong(docid));
        comment.setDoctitile(doctitle);
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userId));
        comment.setUser(user);
        commentdao.save(comment);
        return "评论成功";
    }
    /**
     * 分页获取文档的评论
     * @param docid
     * @param pagecount
     * @param pagenum
     * @return
     * @see com.trsnj.ums.service.ICommentService#getCommentByDocId(java.lang.String, java.lang.String, java.lang.String)
     */
    public List<UMSComment> getCommentByDocId(String docid, String pagecount, String pagenum){
        List<UMSComment> comments=commentdao.getCommentByDocid(Long.parseLong(docid), (Integer.parseInt(pagenum)-1)*Integer.parseInt(pagecount), Integer.parseInt(pagecount));
        return comments;
    }
    public long getCommentCountsByDocId(String docid){
        
        return commentdao.getCommentCountByDocid(Long.parseLong(docid));
    }
}
