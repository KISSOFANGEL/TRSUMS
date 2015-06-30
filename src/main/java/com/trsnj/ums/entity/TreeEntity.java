package com.trsnj.ums.entity;

import java.util.List;
/**
 * 树的基类
 * @author dzy
 *
 */
public class TreeEntity
{
	private String id;
	
	private String text;
	private boolean checked = false;//是否被选中
	private List<TreeEntity> children;
    // 以下两个属性在tree中用到
	private String state="open";//默认节点是展开的
	private TreeAttribute attributes;//节点上的属性
	public TreeEntity()
	{
		super();
	}
    
	/**
     * 获取 state
     * @return 返回 state
     */
    public String getState()
    {
        return state;
    }

    /**
     * 设置 state
     * @param 对state进行赋值
     */
    public void setState(String state)
    {
        this.state = state;
    }


    /**
     * 获取 attributes
     * @return 返回 attributes
     */
    public TreeAttribute getAttributes()
    {
        return attributes;
    }

    /**
     * 设置 attributes
     * @param 对attributes进行赋值
     */
    public void setAttributes(TreeAttribute attributes)
    {
        this.attributes = attributes;
    }

    public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public List<TreeEntity> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeEntity> children)
	{
		this.children = children;
	}

	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public TreeEntity(String id, String text, List<TreeEntity> children) {
		super();
		this.id = id;
		this.text = text;
		this.children = children;
	}
}
