/*
 * 文 件 名:  DemoServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-19
 */
package com.trsnj.ums.service.impl;

import com.trsnj.ums.dao.IDemoDao;
import com.trsnj.ums.dao.impl.DemoDaoImpl;
import com.trsnj.ums.pojo.UMSDemo;
import com.trsnj.ums.service.IDemoService;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-19]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class DemoServiceImpl implements IDemoService
{
   private IDemoDao demodao=null;;
    public void addDemo(UMSDemo demo)
    {
        demodao.add(demo);
    }
    /**
     * 获取 demodao
     * @return 返回 demodao
     */
    public IDemoDao getDemodao()
    {
        return demodao;
    }
    /**
     * 设置 demodao
     * @param 对demodao进行赋值
     */
    public void setDemodao(IDemoDao demodao)
    {
        this.demodao = demodao;
    }
    
}
