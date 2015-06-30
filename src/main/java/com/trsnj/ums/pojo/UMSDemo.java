package com.trsnj.ums.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-19]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Entity
@Table(name="UMSDemo")
public class UMSDemo implements java.io.Serializable
{
    private long demoId;
    private String demoName;
    /**
     * 获取 demoId
     * @return 返回 demoId
     */
    @GenericGenerator(name = "demogenerator", strategy = "identity")//自动增长生成器    一个生成器
    @Id
    @GeneratedValue(generator = "demogenerator")// 引用这个生成器
    public long getDemoId()
    {
        return demoId;
    }
    /**
     * 设置 demoId
     * @param 对demoId进行赋值
     */
    public void setDemoId(long demoId)
    {
        this.demoId = demoId;
    }
    /**
     * 获取 demoName
     * @return 返回 demoName
     */
    @Column(name="DemoName",length=50)
    public String getDemoName()
    {
        return demoName;
    }
    /**
     * 设置 demoName
     * @param 对demoName进行赋值
     */
    public void setDemoName(String demoName)
    {
        this.demoName = demoName;
    }
    
}
