/*
 * 文 件 名:  MD5Util.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-12
 */
package com.trsnj.ums.util;

import cryptix.jce.provider.MD5;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class MD5Util
{
    /**
     * 获取MD5加密的字符串
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getSecretStr(String str){
        
        MD5 md5 = new MD5();
        String s= md5.toMD5(str);
        return s;
    }
}
