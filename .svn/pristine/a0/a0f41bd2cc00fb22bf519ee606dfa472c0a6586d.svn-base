/*
 * 文 件 名:  UMSMessageDaoImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.dao.IUMSMessageDao;
import com.trsnj.ums.pojo.UMSCompanyInfo;
import com.trsnj.ums.pojo.UMSMessage;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.util.CommonUtil;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class UMSMessageDaoImpl extends BaseDao<UMSMessage> implements IUMSMessageDao
{
    
    /**
     * 重写方法
     * @return
     * @see com.trsnj.ums.basedao.imp.BaseHDao#getEntityClass()
     */
    @Override
    public Class<UMSMessage> getEntityClass()
    {
        return UMSMessage.class;
    }
    /**
     * 获取当前用户的message信息
     * @param userId
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getMessageByUserId(long)
     */
    public List<UMSMessage> getMessageByUserId(long userId){
        String sql="select * from umsmessage where reception ="+userId;
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        List<UMSMessage> messages=query.list();
        return messages;
    }
    /**
     * 分页获取当前用户的系统信息（mestype=0表示系统信息）
     * @param userId
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getMessageByUserId(long)
     */
    public List<UMSMessage> getMessageByUserId(long userId,int firstResult,int maxResult,int status){
        String sql="select * from umsmessage where reception ="+userId+" and messtatus="+status+" and mestype =0";
        if(status==-1){
            sql="select * from umsmessage where reception ="+userId+" and messtatus in"+" (0,1)"+" and mestype =0"; 
        }
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSMessage> messages=query.list();
        return messages;
    }
    /**
     * 获取当前用户求助信息
     * @param userId
     * @return 
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<UMSMessage> getHelpMessageByUserId(long userId,int firstResult, int maxResult,int status) {
    	String sql="select * from umsmessage where mesauthor ="+userId+" and messtatus="+status+" and mestype !=1";
        if(status==-1){
            sql="select * from umsmessage where mesauthor ="+userId+" and messtatus in"+" (0,1)"+" and mestype !=1"; 
    	}
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSMessage> messages=query.list();
        return messages;
    }
    /**
     * 分页获取向系统管理员求助的信息
     * @param userId
     * @param firstResult
     * @param maxResult
     * @param status
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getSysMessage(long, int, int, int)
     */
    public List<UMSMessage> getSysMessage(long userId, int firstResult, int maxResult, int status){
        String sql="select * from umsmessage where  messtatus="+status+" and mestype =2";//mestype=2表示向系统管理员的求助信息
        if(status==-1){
            sql="select * from umsmessage where  messtatus in"+" (0,1)"+" and mestype =2"; 
        }
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql).addEntity(getEntityClass());
        query.setMaxResults(maxResult);
        query.setFirstResult(firstResult);
        List<UMSMessage> messages=query.list();
        return messages;
        
    }
    /**
     * 获取当前用户信息的总数
     * @param userId
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public long getMessageCountByUserId(long userId,int status){
        String sql="select count(*) from umsmessage where reception ="+userId+" and messtatus="+status+" and mestype !=1";
        if(status==-1){
            sql="select count(*) from umsmessage where reception ="+userId+" and messtatus in"+" (0,1)"+" and mestype !=1"; 
        }
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        long r= (Integer)query.uniqueResult();
        return r;
    }
    /**
     * 获取向系统管理员求助的信息总数
     * @param userId
     * @param status
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getSysMessageCount(long, int)
     */
    public long getSysMessageCount(long userId, int status){
        String sql="select count(*) from umsmessage where  messtatus="+status+" and mestype =2";
        if(status==-1){
            sql="select count(*) from umsmessage where messtatus in"+" (0,1)"+" and mestype =2"; 
        }
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        long r= (Integer)query.uniqueResult();
        return r;
    }
    /**
     * 获得当前用户向系统管理员求助总数
     * @return -1 表示查找状态为0和1的信息
     * @see [类、类#方法、类#成员]
     */
    public long getHelpMessageCountByUserId(long userId,int status){
        String sql="select count(*) from umsmessage where mesauthor ="+userId+" and messtatus="+status+" and mestype !=1";
        if(status==-1){
            sql="select count(*) from umsmessage where mesauthor ="+userId+" and messtatus in"+" (0,1)"+" and mestype !=1"; 
        }
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        long r= (Integer)query.uniqueResult();
        return r;
    }
    
    /**
     * 删除当前用户选中的信息
     * @param userid,ids
     * @return 
     * @see com.trsnj.ums.dao.delMessageByUserIds(userid,ids)
     */
	@Override
	public String delMessageByUserIds(long userid, String ids) {
		String sql = "delete from umsmessage where reception ="+userid+" and umsmessageid in("+ids+")";
        Session session=this.getCurrentSession();
       // Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
       // ts.commit();
        return "删除成功！";
	}
	@Override
	public String delHelpMessageByUserIds(long userId, String ids) {
		String sql = "delete from umsmessage where mesauthor ="+userId+" and umsmessageid in("+ids+")";
        Session session=this.getCurrentSession();
      //  Transaction ts=session.beginTransaction();
        Query query=null;
        query=session.createSQLQuery(sql);
        query.executeUpdate();
      //  ts.commit();
        return "删除成功！";
	}
	/**
	 * 向某个用户发送系统信息
	 * @param mescon
	 * @param userId
	 * @param parseLong
	 * @see com.trsnj.ums.dao.IUMSMessageDao#addSystemMes(java.lang.String, long, long)
	 */
	 public void addSystemMes(String mestitle,String mescon, long userId, long author){
	     String sql = " insert into umsmessage(mesauthor,mescon,meslev,messtatus,mestime,mestitle,reception,mestype) values("+author+","+"'"+mescon+"',"+0+","+0+",'"+CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm")+"',"+"'"+mestitle+"',"+userId+",0)"; 
	        Session session=this.getCurrentSession();
	     //   Transaction ts=session.beginTransaction();
	        Query query=null;
	        query=session.createSQLQuery(sql);
	        query.executeUpdate();
	      //  ts.commit();
	 }
	 /**
	  * 回复向当前用户求助的信息
	  * @param replyCon 回复内容
	  * @param ids  messageids//这里ids就一个
	  * @param userId 当前用户
	  * @see com.trsnj.ums.dao.IUMSMessageDao#replyMes(java.lang.String, java.lang.String, long)
	  */
	 public void replyMes(String replyCon, String ids, long userId){
	     String sql = "update umsmessage set reception="+userId+",returnMes="+"'"+replyCon+"',replytime='"+CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm")+"',messtatus="+0+" where  UMSMessageid in("+ids+")";
         Session session=this.getCurrentSession();
        // Transaction ts=session.beginTransaction();
         Query query=null;
         query=session.createSQLQuery(sql);
         query.executeUpdate();
        // ts.commit();
	 }
	 /**
	  * 分页获取认证用户的信息
	  * @param userId
	  * @param firstResult
	  * @param maxResult
	  * @return
	  * @see com.trsnj.ums.dao.IUMSMessageDao#getAuthenticationUsers(java.lang.String, int, int)
	  */
	 public List<Map> getAuthenticationUsers(String roleId, int firstResult, int maxResult){
	      
	       String sql="select m.UMSMessageid messageid,m.mesauthor mesauthor,m.mescon mescon,m.mestime mestime,m.mestitle mestitle,m.replytime replytime,m.reception reception,u.userName username from  umsmessage m left join umsuser u on m.mesauthor=u.userid where m.receprole="+roleId+" and mestype=1 order by m.mestime desc";
	        Session session=getCurrentSession();
	        Query query=null;
	        query=session.createSQLQuery(sql)
	            .addScalar("messageid", Hibernate.STRING)
	            .addScalar("mesauthor", Hibernate.STRING)
	            .addScalar("mescon", Hibernate.STRING)
	            .addScalar("mestime", Hibernate.STRING)
	            .addScalar("mestitle", Hibernate.STRING)
	            .addScalar("replytime", Hibernate.STRING)
	            .addScalar("reception", Hibernate.STRING)
	            .addScalar("username", Hibernate.STRING);
	        query.setMaxResults(maxResult);
	        query.setFirstResult(firstResult);
	        List list=query.list();
	        List<Map> maps=new ArrayList<Map>();
	        if (list!=null&&list.size() > 0) {
	            for (int i = 0; i < list.size(); i++) {
	                Map map=new HashMap();
	                Object[] ob = (Object[]) list.get(i);
	                map.put("messageid",(String)ob[0]);
	                map.put("mesauthor", (String)ob[1]);
	                map.put("mescon", (String)ob[2]);
	                map.put("mestime", (String)ob[3]);
	                map.put("mestitle", (String)ob[4]);
	                if((String)ob[5]==null||"".equals((String)ob[5])){
	                    map.put("replytime", "未审批");
	                }else{
	                    map.put("replytime", (String)ob[5]);
	                }
	                map.put("reception", (String)ob[6]);
	                map.put("username", (String)ob[7]);
	                maps.add(map);
	            }
	        }
	        return maps;
	 }
	 /**
	  * 获得用户认证的数目
	  * @param userId
	  * @return
	  * @see com.trsnj.ums.dao.IUMSMessageDao#getAuthenticationUsersCounts(long)
	  */
	 public long getAuthenticationUsersCounts(long roleid){
	     
	     String sql=" select count(*) from  umsmessage m left join umsuser u on m.UMSMessageid=u.userid where m.receprole= "+roleid+" and mestype=1 ";
	        Session session=getCurrentSession();
	        Query query=null;
	        query=session.createSQLQuery(sql);
	        long r= (Integer)query.uniqueResult();
	        return r;
	 }
	 /**
	  * 批量修改信息状态，不包括认证mestype=1的信息
	  * @param messageids
	  * @see com.trsnj.ums.dao.IUMSMessageDao#readMessage(java.lang.String)
	  */
	 public void readMessage(String messageids){
	     String sql = "update umsmessage set messtatus="+1+" where mestype !=1 and UMSMessageid in("+messageids+")";
         Session session=this.getCurrentSession();
       //  Transaction ts=session.beginTransaction();
         Query query=null;
         query=session.createSQLQuery(sql);
         query.executeUpdate();
       //  ts.commit();
	 }
    /**
     * 重写方法
     * @param parseLong
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getMessageByCreuserid(long)
     */
    @Override
    public UMSMessage getMessageByCreuserid(long userid,long roleid)
    {
        String hql="from UMSMessage m where m.receprole="+roleid+" and m.mestype=1 and m.mesauthor="+userid;
        Session session=this.getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        List<UMSMessage> lists=query.list();
        if(lists!=null&&lists.size()>0){
            return lists.get(0);
        }
        return null;
    }
    /**
     * 重写方法
     * @param ids
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#batchDelAuthority(java.lang.String)
     */
    @Override
    public String batchDelAuthority(String ids)
    {
        String sql=" delete from umsmessage where umsmessageid in ("+ids+")";
        Session session=this.getCurrentSession();
          Query query=null;
          query=session.createSQLQuery(sql);
          query.executeUpdate();
        return "SUCCESS";
    }
    /**
     * 获得认证信息，但没回复
     * mestype=1,receprole=系统管理员，replytime is null
     * @param roleid
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getnewAuthoritynum(long)
     */
    @Override
    public long getnewAuthoritynum(long roleid)
    {
        String hql=" select count(*) from UMSMessage m where m.mestype=1 and m.receprole="+roleid+" and m.replytime is null";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        Number num=(Number)query.uniqueResult();
        long count=num.longValue();
        return count;
    }
    /**
     * 获取求助的信息，且没有回复的数目
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getnewHelpnum()
     */
    @Override
    public long getnewHelpnum()
    {
        String hql=" select count(*) from UMSMessage m where m.mestype=2 and m.messtatus in (0,1) and m.returnMes is null";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createQuery(hql);
        Number num=(Number)query.uniqueResult();
        long count=num.longValue();
        return count;
    }
    /**
     * 获取新的未读系统消息
     * @param userId
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getnewsysmesnum(long)
     */
    @Override
    public long getnewsysmesnum(long userId)
    {
        String sql="select count(*) from umsmessage where reception ="+userId+" and messtatus=0 and mestype =0";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        Number num=(Number)query.uniqueResult();
        return num.longValue();
    }
    /**
     * 获取当前用户的求助信息的回复信息未读的总数
     * @param userId
     * @return
     * @see com.trsnj.ums.dao.IUMSMessageDao#getnewreturnhelpnum(long)
     */
    @Override
    public long getnewreturnhelpnum(long userId)
    {
        String sql="select count(*) from umsmessage where mesauthor ="+userId+" and messtatus=0 and mestype =2 and returnMes is not null";
        Session session=getCurrentSession();
        Query query=null;
        query=session.createSQLQuery(sql);
        Number num=(Number)query.uniqueResult();
        return num.longValue();
    }
 
}
