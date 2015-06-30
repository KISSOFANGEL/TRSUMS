package com.trsnj.ums.dao.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.trsnj.ums.basedao.imp.BaseDao;
import com.trsnj.ums.basedao.imp.BaseHDao;
import com.trsnj.ums.pojo.UMSUser;

public class UMSUserDaoImpl extends BaseDao<UMSUser>{

	@Override
	public Class<UMSUser> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UMSUser> getAllUsers() {
		return super.loadAll();
	}

	public void addUser(UMSUser user) {
		super.save(user);
		
	}

}
