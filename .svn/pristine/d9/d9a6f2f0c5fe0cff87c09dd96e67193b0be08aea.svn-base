/*
 * 文 件 名:  SystemMessageAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-3-12
 */
package com.trsnj.ums.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.pojo.UMSSystemMessage;
import com.trsnj.ums.service.ISystemMessageService;
import com.trsnj.ums.util.BaseAction;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-3-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class SystemMessageAction extends BaseAction implements ModelDriven<UMSSystemMessage>
{
    private UMSSystemMessage systemMessage=null;
    private ISystemMessageService systemmessageService=null;
    
    /**
     * 获取 systemmessageService
     * @return 返回 systemmessageService
     */
    public ISystemMessageService getSystemmessageService()
    {
        return systemmessageService;
    }

    /**
     * 设置 systemmessageService
     * @param 对systemmessageService进行赋值
     */
    public void setSystemmessageService(ISystemMessageService systemmessageService)
    {
        this.systemmessageService = systemmessageService;
    }

    /**
     * 获取 systemMessage
     * @return 返回 systemMessage
     */
    public UMSSystemMessage getSystemMessage()
    {
        return systemMessage;
    }

    /**
     * 设置 systemMessage
     * @param 对systemMessage进行赋值
     */
    public void setSystemMessage(UMSSystemMessage systemMessage)
    {
        this.systemMessage = systemMessage;
    }

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8807740395479896816L;

    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    public UMSSystemMessage getModel()
    {
        return this.systemMessage;
    }
    /**
     * 分页获取管理员发送的系统信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getSystemMessages(){
        
        List<UMSSystemMessage> lists=systemmessageService.getSystemMessages(this.page,this.rows);
        if(lists!=null&&lists.size()>0){
            baseEntity.setRows(lists);
        }
        long total=systemmessageService.getSystemMessagesCount();//获得总数
            baseEntity.setTotal(total);
            return "baseEntityResult";
    }
    
    public String delMes(){
       String id= request.getParameter("id");
        systemmessageService.batchdel(id);
        return "baseEntityResult";
    }
    
}
