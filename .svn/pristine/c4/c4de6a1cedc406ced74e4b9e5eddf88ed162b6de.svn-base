package com.trsnj.ums.entity;

import java.util.List;

import com.trsnj.ums.entity.TreeEntity;
import com.trsnj.ums.entity.CONST;

public class BaseEntity {

	//返回信息
	private String message=CONST.MESSAGE_SUCCESS;//操作成功 or 操作失败
	//返回成功失败标识  //默认是成功的
	private String isSuccessOrfail=CONST.SUCCESS;//success or fail
	//返回datagrid总条数参数
	private long total;
	//返回datafrid行参数
	private List rows;
	
	//返回一个对象
	private Object obj;
	
	//返回一个TreeEntity的list的对象
	private List<TreeEntity> treeList;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getIsSuccessOrfail() {
		return isSuccessOrfail;
	}
	public void setIsSuccessOrfail(String isSuccessOrfail) {
		this.isSuccessOrfail = isSuccessOrfail;
	}
	
	/**
     * 获取 total
     * @return 返回 total
     */
    public long getTotal()
    {
        return total;
    }
    /**
     * 设置 total
     * @param 对total进行赋值
     */
    public void setTotal(long total)
    {
        this.total = total;
    }
    public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public List<TreeEntity> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<TreeEntity> treeList) {
		this.treeList = treeList;
	}
	
	
	
}
