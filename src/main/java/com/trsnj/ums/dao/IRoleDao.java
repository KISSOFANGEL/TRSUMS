/*
 * 文 件 名:  IRoleDao.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-21
 */
package com.trsnj.ums.dao;

import com.trsnj.ums.basedao.IBaseDao;
import com.trsnj.ums.pojo.UMSRole;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-21]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IRoleDao extends IBaseDao<UMSRole>
{
    public UMSRole getRoleByRoleName(String roleName);
    public UMSRole getRoleByRoleName(String roleName,long roleId);
    public long getUsersCountsByRoleId(long roleId);
    public long  getUsersConuntsNotInNowRoleId(long roleId);
    public void updateUserToRoleId(long userId,long roleId);
}
