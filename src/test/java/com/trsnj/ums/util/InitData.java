package com.trsnj.ums.util;

import java.util.List;

import javax.management.relation.Role;

import org.junit.Test;

import com.trsnj.ums.dao.impl.RoleDaoImpl;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;
import com.trsnj.ums.service.IRoleService;
import com.trsnj.ums.service.impl.RoleServiceImpl;

public class InitData {
	 IRoleService roleservice=new RoleServiceImpl();
	 @Test
	public void initRole(){
		UMSRole role=new UMSRole();
        role.setRoleName("系统管理员");
        role.setRoleOrder(1);
        role.setCruser("you");
       
		
		 
	}
	 @Test
	 public void initUser(){
		  	IRoleService ros = new RoleServiceImpl();
		  	UMSRole role =ros.getRoleByRoleName("系统管理员");
	        UMSUser user=new UMSUser();
	        user.setAddress("中环大厦");
	        user.setEmail("475683936@qq.com");// email 必填
	        user.setMobile("15651937319");
	        user.setPassWord("trsadmin");
	        user.setStatus(1);
	        user.setTel("025-83176521");
	        user.setUserName("王江");
	        user.setUserType(1);
	       user.setUmsrole(role);
		 
	 }

}
