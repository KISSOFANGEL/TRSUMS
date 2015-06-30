package com.trsnj.ums.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExceptionConstants {
    // 测试demo
    public static final String Demo_00="00";
    public static final String Msg_demo00="demo异常测试";
   // 所有的错误代码都要在这里来注册使用
    public static final String Code_0000 = "0000";  
    public static final String Msg_0000 = "输入参数不合法!";  
    
    public static final String Code_0001 = "0001";  
    public static final String Msg_0001 = "数据被引用,无法删除!"; 
    
    public static final String Code_0002 = "0002";  
    public static final String Msg_0002 = "当前会话中，不存在此用户信息!"; 
    
    public static final String Code_9999 = "9999";  
    public static final String Msg_9999 = "系统错误,请联系管理员!";  
    
    
    
    //角色管理
    public static final String Code_0070 = "0070";  
    public static final String Msg_0070 = "角色名称存在同名!";
    public static final String Code_0071 = "0071";  
    public static final String Msg_0071 = "角色正在被用户使用，请先删除该角色下的用户!";
  
    //用户管理
    public static final String Code_0090 = "0090";  
    public static final String Msg_0090 = "用户名称存在同名!";
    public static final String Code_0091 = "0091";  
    public static final String Msg_0091 = "对不起,该邮箱已经被注册!";
    public static final String Code_0092 = "0092";  // 不用了
    public static final String Msg_0092 = "用户角色或组织不能取消，请选择默认角色!";
    //组织管理
    public static final String Code_0110 = "0110";  
    public static final String Msg_0110 = "组织名称存在同名!";
    public static final String Code_0111 = "0111";  
    public static final String Msg_0111 = "存在子组织,无法删除!";
    public static final String Code_0112 = "0112";  
    public static final String Msg_0112 = "组织下存在用户,无法删除!";
    public static final String Code_0113 = "0113";  
    public static final String Msg_0113 = "组织多选框勾选不正确!";
    // 前台个人用户管理
    public static final String Code_0801 = "0801";  
    public static final String Msg_0801 = "原密码输入不正确!";
    // 后台管理用户密码修改（对管理员密码的修改）
    public static final String Code_0802 = "0802";  
    public static final String Msg_0802 = "请输入原密码后进行修改!";
    private static Map<String, String> returnCodeMap = new ConcurrentHashMap<String, String>();  
	  
    public static Map<String, String> getReturnCodeMap() {  
        if (returnCodeMap.isEmpty()) {  
            // 测试demo注册
            returnCodeMap.put(Demo_00, Msg_demo00);  
            
            returnCodeMap.put(Code_0000, Msg_0000);  
            returnCodeMap.put(Code_0001, Msg_0001);
            
            returnCodeMap.put(Code_0070, Msg_0070);  
            returnCodeMap.put(Code_0071, Msg_0071);
            
            returnCodeMap.put(Code_0090, Msg_0090);
            returnCodeMap.put(Code_0091, Msg_0091);
            returnCodeMap.put(Code_0092, Msg_0092);
            
            returnCodeMap.put(Code_0110, Msg_0110); 
            returnCodeMap.put(Code_0111, Msg_0111); 
            returnCodeMap.put(Code_0112, Msg_0112); 
            returnCodeMap.put(Code_0113, Msg_0113);
            
            returnCodeMap.put(Code_9999, Msg_9999);  
            // 前台个人用户管理
            returnCodeMap.put(Code_0801, Msg_0801);
            returnCodeMap.put(Code_0802, Msg_0802);
        }  
        return returnCodeMap;  
    }
}
