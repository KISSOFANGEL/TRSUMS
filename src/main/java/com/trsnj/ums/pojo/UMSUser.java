package com.trsnj.ums.pojo;
//标准注解
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;










// 增加的注解
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "UMSUser")// 表名可以省略
public class UMSUser implements java.io.Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9045845428057173766L;
// 建议实现这个接口
	@GenericGenerator(name = "usergenerator", strategy = "native")//自动增长生成器    一个生成器
	@Id
	@GeneratedValue(generator = "usergenerator")// 引用这个生成器
	@Column(name = "userid", unique = true, nullable = false)
    private long userId;
	@Column(unique=true,length=50,nullable = false)
    private String userName;//用户名唯一
	@Column(length=50,nullable = false)
    private String passWord;
	@Column(length=50)
	private String relname;
    private int userType;
    private int status;
    @Column(unique=true,length=50,nullable = false)
    private String email;
    private String mobile;
    private String cruser;
    private String crutime;
    private String qq;
    private String address;
    private String tel;
    @Column(length=50)
    private String updatepwdtime;
    @Column(length=50)
    private String updatepwdkey;
    private int type;// 用户类型：0表示前台注册用户未开通，1表示前台注册用户并开通(也表示后台直接开通的用户)
    private int delnote;// 假删除标记：0表示已经删除用户，1表示未删除用户
    private int addUserType;//用户添加类型：1表示前台注册添加，2表示后台注册添加的用户
    @ManyToOne(fetch = FetchType.EAGER)//可以根据用户关联查询到角色,在登陆的时候关联了
    @JoinColumn(name = "role_id")
    private UMSRole umsrole=null;
  
    
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<UMSUserGroup> umsusergroups=null;
    
    public UMSUser()//实现一个无参构造
    {
    	
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
   
    /**
     * 获取 relname
     * @return 返回 relname
     */
    public String getRelname()
    {
        return relname;
    }

    /**
     * 设置 relname
     * @param 对relname进行赋值
     */
    public void setRelname(String relname)
    {
        this.relname = relname;
    }

    /**
     * 获取 updatepwdtime
     * @return 返回 updatepwdtime
     */
    public String getUpdatepwdtime()
    {
        return updatepwdtime;
    }

    /**
     * 设置 updatepwdtime
     * @param 对updatepwdtime进行赋值
     */
    public void setUpdatepwdtime(String updatepwdtime)
    {
        this.updatepwdtime = updatepwdtime;
    }

    /**
     * 获取 updatepwdkey
     * @return 返回 updatepwdkey
     */
    public String getUpdatepwdkey()
    {
        return updatepwdkey;
    }

    /**
     * 设置 updatepwdkey
     * @param 对updatepwdkey进行赋值
     */
    public void setUpdatepwdkey(String updatepwdkey)
    {
        this.updatepwdkey = updatepwdkey;
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
    //@JSON(serialize=false)
    public Set<UMSUserGroup> getUmsusergroups() {
		return umsusergroups;
	}

	public void setUmsusergroups(Set<UMSUserGroup> umsusergroups) {
		this.umsusergroups = umsusergroups;
	}
	//@JSON(serialize=false)
	public UMSRole getUmsrole() {
		return umsrole;
	}

	public void setUmsrole(UMSRole umsrole) {
		this.umsrole = umsrole;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
    
    /**
     * 获取 type
     * @return 返回 type
     */
    public int getType()
    {
        return type;
    }

    /**
     * 设置 type
     * @param 对type进行赋值
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * 获取 delnote
     * @return 返回 delnote
     */
    public int getDelnote()
    {
        return delnote;
    }

    /**
     * 设置 delnote
     * @param 对delnote进行赋值
     */
    public void setDelnote(int delnote)
    {
        this.delnote = delnote;
    }

    /**
     * 获取 addUserType
     * @return 返回 addUserType
     */
    public int getAddUserType()
    {
        return addUserType;
    }

    /**
     * 设置 addUserType
     * @param 对addUserType进行赋值
     */
    public void setAddUserType(int addUserType)
    {
        this.addUserType = addUserType;
    }

    /**
     * 重写方法
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "UMSUser [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", userType=" + userType + ", status=" + status + ", email=" + email + ", mobile=" + mobile
            + ", cruser=" + cruser + ", crutime=" + crutime + ", qq=" + qq + ", address=" + address + ", tel=" + tel + ", umsrole=" + umsrole + ", umsusergroups=" + umsusergroups + "]";
    }
    
    
}
