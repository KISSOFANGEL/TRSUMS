/*
 * 文 件 名:  BehaviorServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.service.impl;

import java.util.Date;
import java.util.List;

import com.trsnj.ums.dao.IUMSBehaviorLogsDao;
import com.trsnj.ums.dao.impl.UMSBehaviorLogsDaoImpl;
import com.trsnj.ums.pojo.UMSBehaviorLogs;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IBehaviorService;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class BehaviorServiceImpl implements IBehaviorService
{
    
    //private IUMSBehaviorLogsDao behaviordao=new UMSBehaviorLogsDaoImpl();
    private IUMSBehaviorLogsDao behaviordao=null;
    /**
     * 获取 behaviordao
     * @return 返回 behaviordao
     */
    public IUMSBehaviorLogsDao getBehaviordao()
    {
        return behaviordao;
    }

    /**
     * 设置 behaviordao
     * @param 对behaviordao进行赋值
     */
    public void setBehaviordao(IUMSBehaviorLogsDao behaviordao)
    {
        this.behaviordao = behaviordao;
    }
    /**
     * 添加用户被删除的操作日志
     * @param deluserId
     * @param parseLong
     * @see com.trsnj.ums.service.IBehaviorService#saveDelUserLog(long, long)
     */
    public void saveDelUserLog(long deluserId, long userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("删除用户操作");
        ublog.setObjid(deluserId);
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        UMSUser user=new UMSUser();
        user.setUserId(userid);
        ublog.setUser(user);
        behaviordao.save(ublog);
    }
    /**
     * 添加角色被删除的操作日志
     * @param deluserId
     * @param parseLong
     * @see com.trsnj.ums.service.IBehaviorService#saveDelUserLog(long, long)
     */
    public void saveDelRoleLog(long delroleid, long userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("删除角色操作");
        ublog.setObjid(delroleid);
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        UMSUser user=new UMSUser();
        user.setUserId(userid);
        ublog.setUser(user);
        behaviordao.save(ublog);
    }
    /**
     * 添加组织被删除的操作日志
     * @param deluserId
     * @param parseLong
     * @see com.trsnj.ums.service.IBehaviorService#saveDelUserLog(long, long)
     */
    public void saveDelGroupLog(long delgroupid, long userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("删除组织操作");
        ublog.setObjid(delgroupid);
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        UMSUser user=new UMSUser();
        user.setUserId(userid);
        ublog.setUser(user);
        behaviordao.save(ublog); 
    }
    /**
     * 收藏的日志保存
     * @param doctitle
     * @param docid
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveCollectLog(java.lang.String, java.lang.String, java.lang.String)
     */
    public void saveCollectLog(String doctitle, String docid, String userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("收藏文档");
        ublog.setObjid(Long.parseLong(docid));
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setObjname(doctitle);//保存收藏文档的标题
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userid));
        ublog.setUser(user);
        behaviordao.save(ublog); 
    }
    /**
     * 分享文档的日志记录
     * @param doctitle
     * @param docid
     * @param sharewhere
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveShareDocLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void saveShareDocLog(String doctitle, String docid, String sharewhere, String userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("分享文档");
        ublog.setObjid(Long.parseLong(docid));
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setObjname(doctitle);//保存分享文档的标题
        ublog.setRemark(sharewhere);//保存了分享去哪儿了
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userid));
        ublog.setUser(user);
        behaviordao.save(ublog); 
    }
    /**
     * 保存品论的日志
     * @param doctitle
     * @param docid
     * @param commentcon
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveCommentDocLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void saveCommentDocLog(String doctitle, String docid, String commentcon, String userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("评论文章");
        ublog.setObjid(Long.parseLong(docid));
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setObjname(doctitle);//保存分享文档的标题
        ublog.setRemark(commentcon);//保存了分享去哪儿了
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userid));
        ublog.setUser(user);
        behaviordao.save(ublog);
        
    }
    /**
     * 
     * @param chnlid
     * @param chnldesc
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveChnlSubLog(java.lang.String, java.lang.String, java.lang.String)
     */
    public void saveChnlSubLog(String chnlid, String chnldesc, String userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("栏目订阅");
        ublog.setObjid(Long.parseLong(chnlid));
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setObjname(chnldesc);//保存分享文档的标题
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userid));
        ublog.setUser(user);
        behaviordao.save(ublog);
    }
    /**
     * 删除订阅的日志记录
     * @param ids
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveDelChnlSubLog(java.lang.String, long)
     */
    public void saveDelChnlSubLog(String ids, long userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("删除栏目订阅");
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setRemark(ids);
        UMSUser user=new UMSUser();
        user.setUserId(userid);
        ublog.setUser(user);
        behaviordao.save(ublog);
    }
    /**
     * 删除评论的日志记录
     * @param ids
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveDelCommentDocLog(java.lang.String, java.lang.String)
     */
    public void saveDelCommentDocLog(String ids, String userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("删除文章评论");
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setRemark(ids);
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userid));
        ublog.setUser(user);
        behaviordao.save(ublog);
    }
    /**
     * 删除分享的日志记录
     * @param ids
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveDelShareDocLog(java.lang.String, java.lang.String)
     */
    public void saveDelShareDocLog(String ids, String userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("删除分享");
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setRemark(ids);
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userid));
        ublog.setUser(user);
        behaviordao.save(ublog);
    }
    /**
     * 删除收藏的日志记录
     * @param ids
     * @param userid
     * @see com.trsnj.ums.service.IBehaviorService#saveDelCollectDocLog(java.lang.String, long)
     */
    public void saveDelCollectDocLog(String ids, long userid){
        UMSBehaviorLogs ublog=new UMSBehaviorLogs();
        ublog.setAction("删除收藏");
        ublog.setOperatetime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm sss"));
        ublog.setRemark(ids);
        UMSUser user=new UMSUser();
        user.setUserId(userid);
        ublog.setUser(user);
        behaviordao.save(ublog);
    }
    /**
     * 分页获取当前用户的动态信息
     * @param currpage
     * @param perpage
     * @param userId
     * @return
     * @see com.trsnj.ums.service.IBehaviorService#getMyDynamic(int, int, long)
     */
    public List<UMSBehaviorLogs> getMyDynamic(int currpage, int perpage, long userId){
        
        return behaviordao.getMyDynamic(currpage,perpage,userId);
    }
    /**
     * 获取当前用户的动态总数
     * @param userId
     * @return
     * @see com.trsnj.ums.service.IBehaviorService#getMyDynamicCount(long)
     */
    public long getMyDynamicCount(long userId){
        
        return behaviordao.getMyDynamicCount(userId);
    }
    /**
     * 批量删除当前用户的动态
     * @param userId
     * @param ids
     * @see com.trsnj.ums.service.IBehaviorService#delMyDynamic(java.lang.String, java.lang.String)
     */
    public void delMyDynamic(String userId, String ids){
        behaviordao.delMyDynamic(userId,ids);
    }

	
}
