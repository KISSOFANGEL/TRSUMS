/*
 * 文 件 名:  SystemMessageServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-3-12
 */
package com.trsnj.ums.service.impl;

import java.util.Date;
import java.util.List;

import com.trsnj.ums.dao.IUMSSystemMessageDao;
import com.trsnj.ums.pojo.UMSSystemMessage;
import com.trsnj.ums.service.ISystemMessageService;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-3-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class SystemMessageServiceImpl implements ISystemMessageService
{
    private IUMSSystemMessageDao systemmessagedao=null;

    /**
     * 获取 systemmessagedao
     * @return 返回 systemmessagedao
     */
    public IUMSSystemMessageDao getSystemmessagedao()
    {
        return systemmessagedao;
    }

    /**
     * 设置 systemmessagedao
     * @param 对systemmessagedao进行赋值
     */
    public void setSystemmessagedao(IUMSSystemMessageDao systemmessagedao)
    {
        this.systemmessagedao = systemmessagedao;
    }
    /**
     * 保存系统信息
     * @param mestitle
     * @param mescon
     * @param userId
     * @param userName
     * @param roleId （字符串拼接的角色id）
     * @see com.trsnj.ums.service.ISystemMessageService#saveSystemMes(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void saveSystemMes(String mestitle, String mescon, String userId, String userName, String roleId){
        UMSSystemMessage sm=new UMSSystemMessage();
        sm.setAuthor(Long.parseLong(userId));
        sm.setAuthorname(userName);
        sm.setCretime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        sm.setType(0);//默认是0，消息是文本类型
        sm.setMescontent(mescon);
        sm.setTitle(mestitle);
        sm.setRoleid(roleId);
        systemmessagedao.save(sm);
    }
    /**
     * 分页获取管理员发送的系统信息
     * @param page
     * @param rows
     * @return
     * @see com.trsnj.ums.service.ISystemMessageService#getSystemMessages(int, int)
     */
    public List<UMSSystemMessage> getSystemMessages(int page, int rows){
        int firstResult=(page-1)*rows;
        int maxResult=rows;
        return systemmessagedao.getSystemMessages(firstResult,maxResult);
    }
    /**
     * 获取所有管理员发送系统消息的总和
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getSystemMessagesCount(){
        
        return systemmessagedao.getSystemMessageCount();
    }
    /**
     * 批量删除
     * @param id
     * @see com.trsnj.ums.service.ISystemMessageService#batchdel(java.lang.String)
     */
    public void batchdel(String id){
         systemmessagedao.batchdel(id);
    }
}
