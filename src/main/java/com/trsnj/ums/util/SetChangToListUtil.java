/*
 * 文 件 名:  SetChangToListUtil.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  dzy
 * 创建时间:  2014-11-22
 */
package com.trsnj.ums.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <一句话功能简述>
 *  
 * @author  dzy
 * @version  [V1.00, 2014-11-22]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class SetChangToListUtil
{

    public static  <E> List setToList(Set<E> set){
        List<E> list=new ArrayList<E>();
        Iterator<E> it=set.iterator();
         while(it.hasNext()){
             list.add(it.next());
         }
         
         return list;
     }
}
