/*
 * 文 件 名:  CreateTable.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CreateTable
{
    Session session = null;
    // 在注解中必须是AnnotationConfiguration
    AnnotationConfiguration config =null;
   
    @Before
   public  void init() throws InterruptedException{
       config = new AnnotationConfiguration();
       config.configure("hibernate.cfg.xml");
       SessionFactory factory = config.buildSessionFactory();
       session=factory.openSession();
   }
   @After
   public  void destory() {
       if (session != null)
           session.close();
   }
   @Test
   public void genTables() throws InterruptedException
   {
       // 创建表
       SchemaExport se=new SchemaExport(config);
       se.create(true, true);
   }
}
