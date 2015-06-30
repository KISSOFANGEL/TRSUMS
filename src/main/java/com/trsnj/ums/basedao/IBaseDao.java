package com.trsnj.ums.basedao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

/**
 * dao的接口封装
 * @Description: 主要实现CURD接口
 * @author dzy 
 * @param <E>
 * @param <String>
 * @date 2013-1-5 上午10:25:29
 */
public interface IBaseDao<E> {
  
    /** 
     *  
     * @param id  根据主键查询一个实体 
     * @return 一个实体对象 
     */  
    E get(long id);  
  
    /** 
     *  
     * @param id  根据主键加裁一个实体对象 
     * @return 一个实体对象 
     */  
    E load(String id);  
    
  
    /** 
     * @return 加裁所有对象 
     */  
    List<E> loadAll();  
  
  
    /** 
     *  
     * @param entity 删除一个实体 
     * @throws HibernateException  抛出异常 
     */  
    void delete(E entity);  
  
  
    /** 
     *  
     * @param entity  当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体 
     */  
    void save(E entity);  
    
    /** 
     *  
     * @param entity  当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体 
     */  
    void saveOrUpdate(E entity);  
    
    void update(E entity);
    
    public Long getRowCount(DetachedCriteria criteria);
  
    /*---------------------------利用hql,sql对数据库进行操作--------------------------------*/  
  
    /** 
     *  
     * @param hql 使用hql语句进行数据库增，删，改操作 
     * @return 受影响行的记录数 
     */  
    Integer bulkUpdate(String hql);  
  
    /** 
     *  
     * @param hql    使用hql语句,检索数据 
     * @return 一个list集合 
     */  
    List<E> find(String hql);  
  
  
    /** 
     *  
     * @param criteria   使用指定的检索标准来检索数 
     * @return 一个list集合 
     */  
    List<E> findByCriteria(DetachedCriteria criteria);  
  
    /** 
     *  
     * @param criteria  使用指定的检索标准来分页检索数据 
     * @param firstResult   开始条数 
     * @param maxResults  返回记录数 
     * @return 一个list集合 
     */  
    List<E> findByCriteria(DetachedCriteria criteria, Integer firstResult,  
            Integer maxResults);  
  
  
    /**注意，Hibernate中，事物不提交，查询与更新都要必须提交事物，否则提交不了？
     * 强制立即更新到数据库,否则需要事务提交后,才会提交到数据库 
     */  
    void flush();  
  
  
    /** 
     *  
     * @param c   为一个实体类型 
     * @return 根据指定的类型创建一个与会话无关的检索对象 
     */  
    DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c);  
  
    Session getCurrentSession();
   // public void closeCurrentSession();
}
