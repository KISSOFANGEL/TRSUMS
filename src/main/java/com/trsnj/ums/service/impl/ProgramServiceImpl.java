/*
 * 文 件 名:  CollectServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-26
 */
package com.trsnj.ums.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.trsnj.ums.dao.IUMSCollectDao;
import com.trsnj.ums.dao.IUMSProgramDao;
import com.trsnj.ums.dao.impl.UMSCollectDaoImpl;
import com.trsnj.ums.pojo.UMSCollect;
import com.trsnj.ums.pojo.UMSProgram;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.ICollectService;
import com.trsnj.ums.service.IProgramService;
import com.trsnj.ums.util.CommonUtil;
import com.trsnj.ums.util.CommunicateWithWCM;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-26]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class ProgramServiceImpl implements IProgramService
{
   // private IUMSCollectDao collectdao=new UMSCollectDaoImpl();
    private IUMSProgramDao programdao=null;
    /**
     * 获取 collectdao
     * @return 返回 collectdao
     */
    public IUMSProgramDao getProgramdao()
    {
        return programdao;
    }

    /**
     * 设置 collectdao
     * @param 对collectdao进行赋值
     */
    public void setProgramdao(IUMSProgramDao programdao)
    {
        this.programdao = programdao;
    }
    /**
     * 分页获取用户的收藏
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UMSProgram> getProgramsByUserId(long userId,int firstResult,int maxResult){
        
        return programdao.getProgramByUserId(userId, firstResult, maxResult);
    }
    /**
     * 获取当前用户收藏的总数
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public long getProgramCountByUserId(long userId){
        
        return programdao.getProgramCountByUserId(userId);
    }
    /**
     * 根据wcm的docid来获取wcm的文档信息
     * @param docid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JSONArray getWcmDocInfo(String docids){
        String url=CommonUtil.getValue("docurl");
        String str="";
        JSONArray jsonArray=null;
        try 
        {
          if("".equals(docids)){
              return jsonArray; 
          }
          System.out.println(docids+"||docids===||"+url+"||====向wcm请求前，没有str返回");
          str=CommunicateWithWCM.getdocsinfo(docids, url);
          System.out.println(docids+"||docids===||"+url+"||====||"+str+"==========收藏取的数据");
          if("".equals(str)||str==null){
              return jsonArray;
          }
          jsonArray=JSONArray.fromObject(str);
          return jsonArray;
          
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
        return jsonArray;
    }
    /**
     * 批量删除
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String delProgramByIds(long userId,String ids){
        
        return programdao.delProgramByIds(userId, ids);
    }
    /**
     * 获得热门推荐
     * @param userId
     * @param firstResult
     * @param maxResult
     * @return
     * @see com.trsnj.ums.service.ICollectService#hotRecommendDoc(java.lang.String, java.lang.String, java.lang.String)
     */
    public JSONArray hotRecommendDoc(String userId, String firstResult, String maxResult){
        int first=Integer.parseInt(firstResult);
        int max=Integer.parseInt(maxResult);
        long userid=Long.parseLong(userId);
        List<String> listdocid=programdao.hotRecommendDoc(userid,first,max);
        int cou=0;
        String ids="";
        for(String id:listdocid){
            ids+=id;
            if(cou!=listdocid.size()-1){
                ids+=",";
            }
            cou++;
        }
        JSONArray jsonArray=this.getWcmDocInfo(ids);
        return jsonArray;
    }
    /**
     * wcm 前台收藏文档
     * @param doctitle
     * @param docid
     * @param userId
     * @return
     * @see com.trsnj.ums.service.ICollectService#saveCollectFront(java.lang.String, java.lang.String, java.lang.String)
     */
    public String saveProgramFront(String doctitle, String docid, String userId){
        //如果文章已收藏，不重复收藏
        UMSProgram c=programdao.getProgramByUserIdAndDocid(docid,userId);
        if(c!=null){
            return "该项目已关注";
        }
        UMSProgram program=new UMSProgram();
        program.setProgramstatus(1);
        program.setRemarktime(CommonUtil.getFormateStrTime(new Date(), "yyyy-MM-dd HH:mm"));
        program.setDocid(Long.parseLong(docid));
        program.setDoctitle(doctitle);
        UMSUser user=new UMSUser();
        user.setUserId(Long.parseLong(userId));
        program.setUser(user);
        programdao.save(program);
        return "收藏成功";
    }
}
