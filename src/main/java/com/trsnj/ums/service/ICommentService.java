/*
 * 文 件 名:  ICommentService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-24
 */
package com.trsnj.ums.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.trsnj.ums.pojo.UMSComment;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface ICommentService
{
    public List<UMSComment> getCommentByUserId(long userid,int firstResult,int maxResult);
    public long getCommentCountByUserId(long userId);
    public JSONArray getWcmDocInfo(String docids);
    public String delCommentByIds(long userId,String ids);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param docid
     * @param commentcon
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String saveCommentFront(String userId, String docid,String doctitle, String commentcon);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param docid
     * @param pagecount
     * @param pagenum
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSComment> getCommentByDocId(String docid, String pagecount, String pagenum);
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param docid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getCommentCountsByDocId(String docid);
}
