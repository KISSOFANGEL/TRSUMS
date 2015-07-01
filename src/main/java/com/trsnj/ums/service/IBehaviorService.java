/*
 * 文 件 名:  IBehaviorService.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.service;

import java.util.List;

import com.trsnj.ums.pojo.UMSBehaviorLogs;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IBehaviorService
{

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param deluserId
     * @param parseLong
     * @see [类、类#方法、类#成员]
     */
  public void saveDelUserLog(long deluserId, long userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param delroleid
     * @param parseLong
     * @see [类、类#方法、类#成员]
     */
    public void saveDelRoleLog(long delroleid, long userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param delgroupid
     * @param parseLong
     * @see [类、类#方法、类#成员]
     */
    public void saveDelGroupLog(long delgroupid, long userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param doctitle
     * @param docid
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveCollectLog(String doctitle, String docid, String userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param doctitle
     * @param docid
     * @param sharewhere
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveShareDocLog(String doctitle, String docid, String sharewhere, String userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param doctitle
     * @param docid
     * @param commentcon
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveCommentDocLog(String doctitle, String docid, String commentcon, String userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param chnlid
     * @param chnldesc
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveChnlSubLog(String chnlid, String chnldesc, String userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param ids
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveDelChnlSubLog(String ids, long userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param ids
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveDelCommentDocLog(String ids, String userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param ids
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveDelShareDocLog(String ids, String userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param ids
     * @param userid
     * @see [类、类#方法、类#成员]
     */
    public void saveDelCollectDocLog(String ids, long userid);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param currpage
     * @param perpage
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSBehaviorLogs> getMyDynamic(int currpage, int perpage, long userId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getMyDynamicCount(long userId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @param ids
     * @see [类、类#方法、类#成员]
     */
    public void delMyDynamic(String userId, String ids);

/*	public List<UMSBehaviorLogs> getMyprogram(int currpage, int perpage,
			long userId);

	public long getgetMyprogramCount(long userId);*/
    
}
