/*
 * 文 件 名:  CommonUtil.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-24
 */
package com.trsnj.ums.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.trsnj.ums.pojo.UMSGroup;
import com.trsnj.ums.pojo.UMSRole;
import com.trsnj.ums.pojo.UMSUser;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-24]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class CommonUtil
{
     public static UMSUser updateUser(UMSUser olduser,UMSUser newuser){
         if(olduser.getCruser()!=null&&olduser.getCruser()!=""){
             newuser.setCruser(olduser.getCruser());
         }
         if(olduser.getCrutime()!=null&&olduser.getCrutime()!=""){
             newuser.setCrutime(olduser.getCrutime());
         }
         if(olduser.getAddress()!=""&&olduser.getAddress()!=null){
         newuser.setAddress(olduser.getAddress());
         }
         if(olduser.getEmail()!=""&&olduser.getEmail()!=null){
             newuser.setEmail(olduser.getEmail());
         }
         if(olduser.getMobile()!=""&&olduser.getMobile()!=null){
         newuser.setMobile(olduser.getMobile());
         }
         if(olduser.getPassWord()!=""&&olduser.getPassWord()!=null){
         newuser.setPassWord(MD5Util.getSecretStr(olduser.getPassWord()));
         }
         if(olduser.getQq()!=""&&olduser.getQq()!=null){
         newuser.setQq(olduser.getQq());
         }
         // 用户状态不用0来 表示
         if(olduser.getStatus()!=0){
         newuser.setStatus(olduser.getStatus());
         }
         if(olduser.getTel()!=""&&olduser.getTel()!=null){
         newuser.setTel(olduser.getTel());
         }
         if(olduser.getUmsrole()!=null){
         newuser.setUmsrole(olduser.getUmsrole());
         }
         if(olduser.getUserName()!=""&&olduser.getUserName()!=null){
         newuser.setUserName(olduser.getUserName());
         }
         // userType 不用0来标示
         if(olduser.getUserType()!=0){
         newuser.setUserType(olduser.getUserType());
         }
         /*if(olduser.getUmsusergroups().size()>0&&olduser.getUmsusergroups()!=null){
         newuser.setUmsusergroups(olduser.getUmsusergroups());
         }*/
         return newuser;
     }
     
     public static UMSRole updateRole(UMSRole oldrole,UMSRole newrole){
         if(oldrole.getCruser()!=null&&oldrole.getCruser()!=""){
             newrole.setCruser(oldrole.getCruser());
         }
         if(oldrole.getCrutime()!=null&&oldrole.getCrutime()!=""){
             newrole.setCrutime(oldrole.getCrutime());
         }
         if(oldrole.getRoleDesc()!=null&&oldrole.getRoleDesc()!=""){
         newrole.setRoleDesc(oldrole.getRoleDesc());
         }
         if(oldrole.getRoleLevel()!=0){
         newrole.setRoleLevel(oldrole.getRoleLevel());
         }
         if(oldrole.getRoleName()!=null&&oldrole.getRoleName()!=""){
         newrole.setRoleName(oldrole.getRoleName());
         }
         //数字型的值都不用0来表示其特性
         if(oldrole.getRoleOrder()!=0){
         newrole.setRoleOrder(oldrole.getRoleOrder());
         }
         if(oldrole.getRoleType()!=0){
         newrole.setRoleType(oldrole.getRoleType());
         }
         return newrole;
     }
     public static UMSGroup updateGroup(UMSGroup oldgroup,UMSGroup newgroup){
        if(oldgroup.getCruser()!=null&&oldgroup.getCruser()!=""){
            newgroup.setCruser(oldgroup.getCruser());
        }
        if(oldgroup.getCrutime()!=null&&oldgroup.getCrutime()!=""){
            newgroup.setCrutime(oldgroup.getCrutime());
        }
        if(oldgroup.getGroupDesc()!=null&&oldgroup.getGroupDesc()!=""){
        newgroup.setGroupDesc(oldgroup.getGroupDesc());
        }
        if(oldgroup.getGroupName()!=null&&oldgroup.getGroupName()!=""){
         newgroup.setGroupName(oldgroup.getGroupName());
        }
        if(oldgroup.getGroupOrder()!=0){
         newgroup.setGroupOrder(oldgroup.getGroupOrder());
        }
        if(oldgroup.getParentId()!=0){
         newgroup.setParentId(oldgroup.getParentId());
        }
        //不用组织来关联保存用户
        /*if(oldgroup.getUsergroups().size()>0&&oldgroup.getUsergroups()!=null){
         newgroup.setUsergroups(oldgroup.getUsergroups());
        }*/
         return newgroup;
     }
     /**
      * 根据指定的时间，格式返回字符串类型的时间
      * @param date
      * @param formate
      * @return
      * @see [类、类#方法、类#成员]
      * 
      * 日期和时间模式  结果  
        "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT  
        "EEE, MMM d, ''yy"  Wed, Jul 4, '01  
        "h:mm a"  12:08 PM  
        "hh 'o''clock' a, zzzz"  12 o'clock PM, Pacific Daylight Time  
        "K:mm a, z"  0:08 PM, PDT  
        "yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM  
        "EEE, d MMM yyyy HH:mm:ss Z"  Wed, 4 Jul 2001 12:08:56 -0700  
        "yyMMddHHmmssZ"  010704120856-0700  
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ"  2001-07-04T12:08:56.235-0700  
      * 
      */
     /**
      * 将指定的时间转换为指定的格式
      * @param date
      * @param formate
      * @return String
      * @see [类、类#方法、类#成员]
      */
     public static String getFormateStrTime(Date date,String formate){
         SimpleDateFormat sdf=new SimpleDateFormat(formate);
         return  sdf.format(date);
     }
     /**
      * 根据指定的格式将对应的格式时间转化为时间格式
      * @param time
      * @param formate
      * @return Date
      * @throws ParseException
      * @see [类、类#方法、类#成员]
      */
     public static Date getTime(String time,String formate) throws ParseException{
         SimpleDateFormat sdf=new SimpleDateFormat(formate);
         return sdf.parse(time);
     }
    public static String getEncoding(String data,String encode) throws UnsupportedEncodingException{
        return java.net.URLEncoder.encode(data,encode);
    }
    
    public static String deEncoding(String data,String encode) throws UnsupportedEncodingException{
        
        return java.net.URLDecoder.decode(data,encode);
    }
    public static String getEncoding(String data) throws UnsupportedEncodingException{
        return java.net.URLEncoder.encode(data,"UTF-8");
    }
    
    public static String deEncoding(String data) throws UnsupportedEncodingException{
        
        return java.net.URLDecoder.decode(data,"UTF-8");
    }
    public static String getStrTimeByTimeStamp(String date,String formate){
        long time=Long.parseLong(date);//会抛出运行时异常
        Date d=new Date(time);
        SimpleDateFormat sdf=new SimpleDateFormat(formate);
        return sdf.format(d);
    }
    
    public static String getValue(String key){
        Properties prop = new Properties();   
        InputStream in = TRSMailUtil.class.getResourceAsStream("/TRSMailSend.properties");   
        String value="";
        try {   
            prop.load(in); 
            value = prop.getProperty(key).trim();  
            return value;
        } catch (IOException e) { 
            e.printStackTrace();   
        } 
        return value;
    }
    /**
     * 随机生成一组包含大小写字符和数字的字符串
     * @param length
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String genStr(int length) {
        char[] ss = new char[length];
        int i=0;
       while(i<length) {
           int f = (int) (Math.random()*3);
           if(f==0)  
            ss[i] = (char) ('A'+Math.random()*26);
           else if(f==1)  
            ss[i] = (char) ('a'+Math.random()*26);
           else 
            ss[i] = (char) ('0'+Math.random()*10);    
           i++;
        }
       String is=new String(ss);
        return is;
       }
}
