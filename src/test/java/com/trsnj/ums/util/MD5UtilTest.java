/*
 * 文 件 名:  MD5UtilTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-12-12
 */
package com.trsnj.ums.util;

import org.junit.Test;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-12-12]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class MD5UtilTest
{
    @Test
    public void testGetSecretStr(){
       String s= MD5Util.getSecretStr("123");
       String s1= MD5Util.getSecretStr("123");
       System.out.println(s.equals(s1));
    }
}
