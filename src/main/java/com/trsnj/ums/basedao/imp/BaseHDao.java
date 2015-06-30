package com.trsnj.ums.basedao.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.trsnj.ums.basedao.IBaseDao; 
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.util.HibernateFactory;
public abstract class BaseHDao<E extends Serializable> implements IBaseDao<E> {

	/**
	 * 为E对应的实例类型 
	 */
	private Class<E> entityClass = getEntityClass();
    // 获得当前的class类
	public abstract Class<E> getEntityClass();

	public E get(long id) {//只有所有的主键id都为long的时候才能统一
		Session s = null;
		Object e;
		try {
			s = HibernateFactory.openSession();
			e = s.get(this.entityClass,id);
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
			
		}
		return (E) e;
	}

	public E load(String id) {
		
		Session s = null;
		Object e;
		try {
			s = HibernateFactory.openSession();
			e = s.load(this.entityClass, id);
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
		return (E) e;
	}
//?
	public Long getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<E> list = this.findByCriteria(criteria,0,1);
		if(list!=null&&list.size()>0)
		return (Long) list.get(0);
		return 0l;
	}
    /**
     * 
     * @return
     * @see com.trsnj.ums.basedao.IBaseDao#loadAll()
     */
	public List<E> loadAll() {
		Session s = null;
		Query query = null;
		List<E> list = new ArrayList<E>();
		try {
			s = HibernateFactory.openSession();
			//entityClass.getName();
			query = s.createQuery(" from "+entityClass.getName());
			list = query.list();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
		return list;
	}


	public void save(E entity) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateFactory.openSession();
			tx = s.beginTransaction();
			s.save(entity);
			tx.commit();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}

	}

	public void saveOrUpdate(E entity) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateFactory.openSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(entity);
			tx.commit();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
	}


	public void update(E entity) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateFactory.openSession();
			tx = s.beginTransaction();
			s.update(entity);
			tx.commit();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
	}

	public void delete(E entity) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateFactory.openSession();
			tx = s.beginTransaction();
			s.delete(entity);
			tx.commit();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String hql) {
		Session s = null;
		Query query = null;
		List<E> list = new ArrayList<E>();
		try {
			s = HibernateFactory.openSession();
			query = s.createQuery(hql);
			list = query.list();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
		return list;
	}

	public Integer bulkUpdate(String hql) {
		Session s = null;
		Transaction tx = null;
		Query query = null;
		Integer i;
		try {
			s = HibernateFactory.openSession();
			tx = s.beginTransaction();
			query = s.createQuery(hql);
			i = query.executeUpdate();
			tx.commit();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
		return i;
	}

	public DetachedCriteria createDetachedCriteria(
			Class<? extends Serializable> c) {// 必须继承Serializable接口
		return DetachedCriteria.forClass(c);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(DetachedCriteria criteria) {
		Session s = null;
		Transaction tx = null;
		Query query = null;
		List<E> list = new ArrayList<E>();
		try {
			s = HibernateFactory.openSession();
			tx = s.beginTransaction();
			list = s.createCriteria(this.getEntityClass()).list();
			//  list = criteria.add().list();
			tx.commit();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
		return list;
	}
	// 方法不能用，只是对结果集进行过滤，分页时候不准确
	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(final DetachedCriteria criteria,
		 final Integer firstResult,final Integer maxResults) {
		Session s = null;
		Transaction tx = null;
		Query query = null;
		List<E> list = new ArrayList<E>();
		//List<E> list =null;
		try {
			s = HibernateFactory.openSession();
			tx = s.beginTransaction();
			//分页   一般只是对持久化的类分页查询
			list = criteria.getExecutableCriteria(s).setFirstResult(firstResult).setMaxResults(maxResults)
			    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			///////////////////////////////////////
			 //hibernate模版提供的方法
			/*list=(List)getHibernateTemplate().execute(new HibernateCallback() { 
                public Object doInHibernate(Session session) throws HibernateException { 
                        Criteria dcriteria = criteria.getExecutableCriteria(session); 
                        dcriteria.setProjection(null); 
                       List<UMSUser> item = dcriteria.setFirstResult(firstResult).setMaxResults(maxResults).list(); 
                        return item;
                } 
        }, true);*/ 
			//////////////////////////////////////
			//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); //避免数据重复，但是只是对查询结果集进行过滤，对查询总数不边，造成分页不对
			//list=s.createCriteria(this.getEntityClass()).setFirstResult(firstResult).setMaxResults(maxResults).list();	
			tx.commit();
		} finally {
			if (s != null)
				HibernateFactory.closeSession();
		}
		return list;
	}
   // 这里是通过单独的Hibernate获取得到的
	public Session getCurrentSession() {
		return HibernateFactory.openSession();
	}

	public void closeCurrentSession() {
		 HibernateFactory.closeSession();
	}

	public void flush() {
		HibernateFactory.openSession().flush();
	}

}
