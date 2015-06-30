package com.trsnj.ums.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="UMSUserGroup")
public class UMSUserGroup implements java.io.Serializable {
	
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6460120791400182225L;

    @GenericGenerator(name = "gugenerator", strategy = "native")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "gugenerator")// 引用这个生成器
    @Column(unique = true, nullable = false)
    private long userGroupId;
	
	@ManyToOne
	@JoinColumn(name="group_id")
    private UMSGroup group;
	
	@ManyToOne
	@JoinColumn(name="user_id")
    private UMSUser user;
	
	private String gumark;
	private String crutime; //这写字段在保存用户的时候，修改用户的时候设置字段的值
	private String cruser;
	
	
	
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
    /**
     * 获取 cruser
     * @return 返回 cruser
     */
    public String getCruser()
    {
        return cruser;
    }
    /**
     * 设置 cruser
     * @param 对cruser进行赋值
     */
    public void setCruser(String cruser)
    {
        this.cruser = cruser;
    }
    public String getGumark() {
		return gumark;
	}
	public void setGumark(String gumark) {
		this.gumark = gumark;
	}
	public long getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(long userGroupId) {
		this.userGroupId = userGroupId;
	}
	public UMSGroup getGroup() {
		return group;
	}
	public void setGroup(UMSGroup group) {
		this.group = group;
	}
	public UMSUser getUser() {
		return user;
	}
	public void setUser(UMSUser user) {
		this.user = user;
	}
    
}
