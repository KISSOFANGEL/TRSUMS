package com.trsnj.ums.util;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;

public class HibernateFactory{
 private static final SessionFactory sessionFactory;
 private static final ThreadLocal thread_var = new ThreadLocal();

 static{
  try{
   //创建SessionFactory
   sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
  }catch(HibernateException e){
   throw new HibernateException("Build SessionFactory:",e);
  }
 }

 public static Session openSession(){// 这个是从线程池里面取线程的session  否则就创建session
  //取得Session对象
  Session s = (Session)thread_var.get();
  //如果这个线程为空,打开一个新的Session
  if(s == null){
   s = sessionFactory.openSession();
   //把Hibernate的Session放入thread_var变量中保存
   thread_var.set(s);
  }
  return s;
 }

 public static void closeSession(){
  Session s = (Session)thread_var.get();
  if(s != null){
   s.close(); 
  }
  thread_var.set(null);
 }
 public static SessionFactory getSessionFactory() {   
     return sessionFactory;   
 }   
}