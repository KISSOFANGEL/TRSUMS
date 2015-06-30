/*
 * 文 件 名:  DefaultAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-16
 */
package com.trsnj.ums.action;

import java.io.IOException;
import java.util.Map;

import com.trsnj.ums.util.BaseAction;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-16]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class URLDefaultAction extends BaseAction
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8615720244527532069L;

    public void urldefaultAction(){
        
        String url=request.getRequestURI();
        
        return;//没找到配置的action  不配置跳转，还在当前页
    }
    
}
