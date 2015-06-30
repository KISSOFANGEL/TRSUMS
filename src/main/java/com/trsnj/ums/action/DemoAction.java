package com.trsnj.ums.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.trsnj.ums.entity.BaseEntity;
import com.trsnj.ums.exception.AppRunTimeException;
import com.trsnj.ums.exception.ExceptionConstants;
import com.trsnj.ums.pojo.UMSDemo;
import com.trsnj.ums.service.IDemoService;
import com.trsnj.ums.service.impl.DemoServiceImpl;
import com.trsnj.ums.util.BaseAction;


/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-19]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class DemoAction extends BaseAction
{
    // 用来将实体转换成json字符串的
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    //private IDemoService demoservice=new DemoServiceImpl();
    //通过spring的IOC来注入
    private IDemoService demoservice=null;
    public String add(){
        UMSDemo demo=new UMSDemo();
        demo.setDemoName("测试action");
        demoservice.addDemo(demo);
        return super.SUCCESS;
    }
    
    public String testLoadalldemo(){
        
        List list=new ArrayList();
        UMSDemo demo=new UMSDemo();
        demo.setDemoId(1);
        demo.setDemoName("test1");
        UMSDemo demo2=new UMSDemo();
        demo2.setDemoId(2);
        demo2.setDemoName("test2");
        list.add(demo);
        list.add(demo2);
        baseEntity.setTotal(3);
        baseEntity.setObj(list);
       
        if(true){
            // 异常测试demo
        throw new AppRunTimeException(ExceptionConstants.Demo_00);
        }
        return "baseEntityResult";
    }
    
    /**
     * 获取 demoservice
     * @return 返回 demoservice
     */
    public IDemoService getDemoservice()
    {
        return demoservice;
    }
    /**
     * 设置 demoservice
     * @param 对demoservice进行赋值
     */
    public void setDemoservice(IDemoService demoservice)
    {
        this.demoservice = demoservice;
    }
    
    
    
    
    
}
