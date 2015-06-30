/*
 * 文 件 名:  IUMSCommentDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao;

import java.util.List;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSComment;

/**
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IUMSCommentDao extends IBaseDao<UMSComment>
{
    public List<UMSComment> getCommentByUserId(long userId);
    public List<UMSComment> getCommentByUserId(long userId,int firstResult,int maxResult);
    public long getCommentCountByUserId(long userId);
    public List<UMSComment> getCommentByDocid(long docid);
    public List<UMSComment> getCommentByDocid(long docid,int firstResult,int maxResult);
    public long getCommentCountByDocid(long docid);
    public String delCommentByIds(long userId,String ids);
}
