/*
 * 文 件 名:  CommonUtilTest.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-27
 */
package com.trsnj.ums.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-27]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CommonUtilTest
{
    @Test
    public void testGetTime(){
        try
        {
            Date date=CommonUtil.getTime("2014-11-27", "yyyy-MM-dd");
            System.out.println(date);// 这里解析出来的时间格式为Thu Nov 27 00:00:00 CST 2014
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetFormateStrTime(){// EEE 星期几
      String time=  CommonUtil.getFormateStrTime(new Date(), "EEE");
      System.out.println(time+"=====");
    }
    @Test
    public void testGetEncoding() throws UnsupportedEncodingException{
        String str="dzydzy";
       String s= CommonUtil.getEncoding(str, "UTF-8");
       System.out.println(s);
    }
    @Test
    public void testDeencoding() throws UnsupportedEncodingException{
        String str="dzy%E9%82%93dzy";
        String s=CommonUtil.deEncoding(str, "UTF-8");
        System.out.println(s);
    }
    @Test
    public void testDesc() throws Exception{
        String input = "602717968@qq.com";    
        
        String result = DESUtil.encryption(input); 
        System.out.println(result);
       result= CommonUtil.getEncoding(result, "UTF-8");
        System.out.println(result);  
          
        System.out.println(DESUtil.decryption(CommonUtil.deEncoding(result, "UTF-8"))); 
    }
    
}
