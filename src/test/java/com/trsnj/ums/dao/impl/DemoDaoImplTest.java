package com.trsnj.ums.dao.impl;

import org.junit.Test;

import com.trsnj.ums.dao.IDemoDao;
import com.trsnj.ums.pojo.UMSDemo;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-19]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class DemoDaoImplTest
{
    private IDemoDao demodaoimpl=new DemoDaoImpl();
    @Test
    public void testAdd()
    {
        UMSDemo demo=new UMSDemo();
        demo.setDemoName("测试");
        demodaoimpl.add(demo);// c3p0总是报错是怎么回事
    }
}
