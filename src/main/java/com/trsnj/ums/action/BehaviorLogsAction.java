/*
 * 文 件 名:  BehaviorLogsAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2015-2-26
 */
package com.trsnj.ums.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.trsnj.ums.pojo.UMSBehaviorLogs;
import com.trsnj.ums.service.IBehaviorService;
import com.trsnj.ums.util.BaseAction;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2015-2-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class BehaviorLogsAction  extends BaseAction implements ModelDriven<UMSBehaviorLogs>
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5919561688546966667L;
    private IBehaviorService behaviorService=null;
    private UMSBehaviorLogs behaviorlogs=null;
    /**
     * 重写方法
     * @return
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public UMSBehaviorLogs getModel()
    {
        return behaviorlogs;
    }

    /**
     * 获取 behaviorService
     * @return 返回 behaviorService
     */
    public IBehaviorService getBehaviorService()
    {
        return behaviorService;
    }

    /**
     * 设置 behaviorService
     * @param 对behaviorService进行赋值
     */
    public void setBehaviorService(IBehaviorService behaviorService)
    {
        this.behaviorService = behaviorService;
    }

    /**
     * 获取 behaviorlogs
     * @return 返回 behaviorlogs
     */
    public UMSBehaviorLogs getBehaviorlogs()
    {
        return behaviorlogs;
    }
    /**
     * 设置 behaviorlogs
     * @param 对behaviorlogs进行赋值
     */
    public void setBehaviorlogs(UMSBehaviorLogs behaviorlogs)
    {
        this.behaviorlogs = behaviorlogs;
    }
    /**
     * 分页获取我的动态
     * @see [类、类#方法、类#成员]
     */
    public void getMyDynamic(){
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int perpage = Integer.parseInt(request.getParameter("perpage"));
        long userId=Long.parseLong(this.getUserId());
        List<UMSBehaviorLogs> behaviors=behaviorService.getMyDynamic(currpage,perpage,userId);
        long total=behaviorService.getMyDynamicCount(userId);
        JSONArray jsonArray=new JSONArray();
        for(UMSBehaviorLogs behavior:behaviors)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.accumulate("total", total);
            jsonObject.accumulate("dynamicid", behavior.getUMSBehaviorLogsid());
            jsonObject.accumulate("action", behavior.getAction());
            jsonObject.accumulate("objname", behavior.getObjname());
            jsonObject.accumulate("operatetime", behavior.getOperatetime());
            jsonArray.add(jsonObject);
        }
        PrintWriter out;
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            out.print(jsonArray.toString());
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 批量删除我的动态
     * @see [类、类#方法、类#成员]
     */
    public void delMyDynamic(){
        String userId=this.getUserId();
        String ids=request.getParameter("ids");
        behaviorService.delMyDynamic(userId,ids);
    }
    
}
