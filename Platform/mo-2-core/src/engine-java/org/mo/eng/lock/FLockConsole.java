/*
 * @(#)FLockConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>同步锁管理控制台</p>
 * <p>根据名称和引用类获得当前程序内唯一的同步对象</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/26
 */
public class FLockConsole
      implements
         ILockConsole
{
   // 共享锁缓冲
   private static Map<Object, Object> m_oLockMap = new HashMap<Object, Object>();

   /**
    * <p>根据锁标志获得当前程序内唯一的同步对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sLockFlag 锁标志
    * @return 同步对象
    */
   public static Object lockObject(String sLockFlag){
      if(!m_oLockMap.containsKey(sLockFlag)){
         m_oLockMap.put(sLockFlag, new Object());
      }
      return m_oLockMap.get(sLockFlag);
   }

   /**
    * <p>根据引用类和锁标志获得当前程序内唯一的同步对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oValue 引用类
    * @param sLockFlag 锁标志
    * @return 同步对象
    */
   public static Object lockObject(Object oValue,
                                   String sLockFlag){
      String sLockName = null;
      if(oValue instanceof Class){
         sLockName = ((Class<?>)oValue).getName();
      }else if(oValue instanceof String){
         sLockName = (String)oValue;
      }else{
         sLockName = oValue.getClass().getName();
      }
      sLockFlag = sLockName + "|" + sLockFlag;
      if(!m_oLockMap.containsKey(sLockFlag)){
         m_oLockMap.put(sLockFlag, new Object());
      }
      return m_oLockMap.get(sLockFlag);
   }
}
