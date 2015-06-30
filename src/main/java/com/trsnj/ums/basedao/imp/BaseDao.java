package com.trsnj.ums.basedao.imp;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.trsnj.ums.basedao.IBaseDao;


public abstract  class BaseDao<E extends Serializable>   implements  IBaseDao<E>  {

	/** 
     * 为E对应的实例类型 
     */  
    private Class<E> entityClass = getEntityClass(); 
    protected HibernateTemplate ht;
    
    public void setHt(HibernateTemplate ht) {//ht对象是set函数传过来的，并给ht属性赋值
		this.ht = ht;
	}
    
    
    /*
     * 自身特有的抽象方法
     * 给entityClass属性赋值
     * 
     */
	public abstract Class<E> getEntityClass();//抽象的方法，必须继承
   /*
    * 重写的方法
    * @id 根据id得到实体
    * (non-Javadoc)
    * @see com.ace.platform.dao.IBaseDao#get(java.lang.String)
    */
    public E get(long id) {  
        return (E) ht.get(this.entityClass, id);  
    }  
    /*
     *重写的加载方法
     * @id 根据id得到实体
     * (non-Javadoc)
     * @see com.ace.platform.dao.IBaseDao#load(java.lang.String)
     */
    public E load(String id) {  
        return (E) ht.load(this.entityClass, id);  
    }  
    @Override
    public Long getRowCount(DetachedCriteria criteria) {  
        criteria.setProjection(Projections.rowCount());  
        return (Long) this.findByCriteria(criteria, 0, 1).get(0);  
    } 
    /*
     * 得到所有的实体
     * (non-Javadoc)
     * @see com.ace.platform.dao.IBaseDao#loadAll()
     */
    public List<E> loadAll() {  
        return (List<E>) ht.loadAll(entityClass);  
    }  
  
  
    @Override
	public void save(E entity) {
		ht.save(entity);
		
	}
    @Override
	public void saveOrUpdate(E entity) {  
        ht.saveOrUpdate(entity);  
    }  
  
  
    @Override
	public void update(E entity) {

    	ht.update(entity);
	}
	public void delete(E entity){  
        ht.delete(entity);  
    }  
	@Override
    @SuppressWarnings("unchecked")  
    public List<E> find(String hql) {  
        return ht.find(hql);  
    }  
    @Override
    public Integer bulkUpdate(String hql) {  
        return ht.bulkUpdate(hql);  
    } 
    
    @Override
    public DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c) 
    {  
        return DetachedCriteria.forClass(c);  
    }  
    
    @SuppressWarnings("unchecked")  
    public List<E> findByCriteria(DetachedCriteria criteria) {  
        return ht.findByCriteria(criteria);  
    }  
  
    @SuppressWarnings("unchecked")  
    public List<E> findByCriteria(DetachedCriteria criteria,  
            Integer firstResult, Integer maxResults) {  
        return ht.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY),  
                firstResult, maxResults);  
    } 
    //这是通过spring 的事物管理获取得到的session
    public Session getCurrentSession(){
    	return ht.getSessionFactory().getCurrentSession();
    }

    public void flush(){  
        ht.flush();  
    }  

}
