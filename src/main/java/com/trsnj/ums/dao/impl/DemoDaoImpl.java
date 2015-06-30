package com.trsnj.ums.dao.impl;

import org.hibernate.Session;

import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IDemoDao;
import com.trsnj.ums.pojo.UMSDemo;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-19]
 * @see  [相关类/方法]
 * @since V1.00
 * BaseHDao 是hibernate的基础到，没有添加spring支持，BaseDao是添加了spring的支持
 */
public class DemoDaoImpl extends BaseHDao<UMSDemo> implements IDemoDao
{

    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSDemo> getEntityClass()
    {
        return UMSDemo.class;
    }
     
    public void add(UMSDemo demo)
    {
        super.save(demo);
    }
    public static void main(String[] args)
    {
        UMSDemo demo=new UMSDemo();
        demo.setDemoName("测试");
        // 测试通过
        DemoDaoImpl d=new DemoDaoImpl();
        d.add(demo);
    } 
    
}
