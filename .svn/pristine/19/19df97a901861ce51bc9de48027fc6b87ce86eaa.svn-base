package com.trsnj.ums.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "UMSGroup")
public class UMSGroup implements java.io.Serializable{
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7782067183344088302L;
    @GenericGenerator(name = "groupgenerator", strategy = "native")//自动增长生成器    一个生成器
	@Id
	@GeneratedValue(generator = "groupgenerator")// 引用这个生成器
	@Column(name = "groupId", unique = true, nullable = false)
   private long groupId;
   @Column(unique=true,length=50,nullable = false)
   private String groupName;
   private String groupDesc;
   private long parentId;
   private String cruser;
   private String crutime;
   private int groupOrder;
   // 这个是后加的
   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy="group") 
   private Set<UMSUserGroup> usergroups=null;
   /*@ManyToMany(mappedBy = "umsgroups", fetch = FetchType.LAZY) 
   private Set<UMSUser> users=null;*/
   public UMSGroup()
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

/**
 * 获取 usergroups
 * @return 返回 usergroups
 */
@JSON(serialize=false)
public Set<UMSUserGroup> getUsergroups()
{
    return usergroups;
}

/**
 * 设置 usergroups
 * @param 对usergroups进行赋值
 */
public void setUsergroups(Set<UMSUserGroup> usergroups)
{
    this.usergroups = usergroups;
}



public String getGroupName() {
	return groupName;
}

/**
 * 获取 groupId
 * @return 返回 groupId
 */
public long getGroupId()
{
    return groupId;
}

/**
 * 设置 groupId
 * @param 对groupId进行赋值
 */
public void setGroupId(long groupId)
{
    this.groupId = groupId;
}

public void setGroupName(String groupName) {
	this.groupName = groupName;
}

public String getGroupDesc() {
	return groupDesc;
}

public void setGroupDesc(String groupDesc) {
	this.groupDesc = groupDesc;
}


/**
 * 获取 parentId
 * @return 返回 parentId
 */
public long getParentId()
{
    return parentId;
}

/**
 * 设置 parentId
 * @param 对parentId进行赋值
 */
public void setParentId(long parentId)
{
    this.parentId = parentId;
}

public String getCruser() {
	return cruser;
}

public void setCruser(String cruser) {
	this.cruser = cruser;
}

public int getGroupOrder() {
	return groupOrder;
}

public void setGroupOrder(int groupOrder) {
	this.groupOrder = groupOrder;
}
   
}
