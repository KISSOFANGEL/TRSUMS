/*
 * 文 件 名:  CompanyInfoServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-22
 */
package com.trsnj.ums.service.impl;

import com.trsnj.ums.dao.IUMSCompanyInfoDao;
import com.trsnj.ums.dao.impl.CompanyInfoDaoImpl;
import com.trsnj.ums.pojo.UMSCompanyInfo;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IUMSCompanyService;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CompanyInfoServiceImpl implements IUMSCompanyService
{
   // private IUMSCompanyInfoDao companydao=new CompanyInfoDaoImpl();
    private IUMSCompanyInfoDao companydao=null;
      /**
     * 获取 companydao
     * @return 返回 companydao
     */
    public IUMSCompanyInfoDao getCompanydao()
    {
        return companydao;
    }

    /**
     * 设置 companydao
     * @param 对companydao进行赋值
     */
    public void setCompanydao(IUMSCompanyInfoDao companydao)
    {
        this.companydao = companydao;
    }
    /**
     * 根据用户id获取公司信息
     * @param userId
     * @return
     * @see com.trsnj.ums.service.IUMSCompanyService#getComInfoByUserId(long)
     */
    public UMSCompanyInfo getComInfoByUserId(long userId){
          
          return companydao.getComInfoByUserId(userId);
      }
    /**
     * 修改公司信息
     * @param companyInfo
     * @return
     * @see com.trsnj.ums.service.IUMSCompanyService#updateCurrentCompanyInfo(com.trsnj.ums.pojo.UMSCompanyInfo)
     */
    public UMSCompanyInfo updateCurrentCompanyInfo(UMSCompanyInfo companyInfo,String userId){
        //查询userId所对应的公司信息是否存在，如果存在则修改，否则不修改
       UMSCompanyInfo companyinfo= companydao.getComInfoByUserId(Long.parseLong(userId));
       if(companyinfo!=null){
        String sql="update umscompanyinfo set companyName=?,companyNature=?,companyAddress=?,deptName=?,companytel=? where userid=?";
        UMSCompanyInfo comInfo=companydao.updateCurrentCompanyInfo(companyInfo,userId,sql);
        return comInfo;
       }else{
           UMSUser user=new UMSUser();
           user.setUserId(Long.parseLong(userId));
           companyInfo.setUser(user);
           companydao.save(companyInfo);
           return companyInfo;
       }
    }
    
    
}
