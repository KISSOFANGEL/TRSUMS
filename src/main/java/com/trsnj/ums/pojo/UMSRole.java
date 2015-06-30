package com.trsnj.ums.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "UMSRole")
public class UMSRole implements java.io.Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3305196512569493483L;
    @GenericGenerator(name = "rolegenerator", strategy = "native")//自动增长生成器    一个生成器
	@Id
	@GeneratedValue(generator = "rolegenerator")// 引用这个生成器
	@Column(name = "roleId", unique = true, nullable = false)
     private long roleId;
     private String roleName;
     private String roleDesc;
     private String cruser;
     private String crutime;
     private int roleType;
     private int roleOrder;
     private int roleLevel;
     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "umsrole") 
     private Set<UMSUser> users=null;
     public UMSRole()
     {
     }

	/**
     * 获取 crutime
     * @return 返回 crutime
     */
    public String getCrutime()
    {
        return crutime;
    }

    /**
     * 设置 crutime
     * @param 对crutime进行赋值
     */
    public void setCrutime(String crutime)
    {
        this.crutime = crutime;
    }
    @JSON(serialize=false)
    public Set<UMSUser> getUsers() {
		return users;
	}
	public void setUsers(Set<UMSUser> users) {
		this.users = users;
	}


	public int getRoleLevel() {
		return roleLevel;
	}


	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

	/**
     * 获取 roleId
     * @return 返回 roleId
     */
    public long getRoleId()
    {
        return roleId;
    }


    /**
     * 设置 roleId
     * @param 对roleId进行赋值
     */
    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }


    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public int getRoleOrder() {
		return roleOrder;
	}

	public void setRoleOrder(int roleOrder) {
		this.roleOrder = roleOrder;
	}
     
     
     
}
