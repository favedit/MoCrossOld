/*
 * @(#)FNamingValue.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.naming;

/**
 * <p>名称节点内容组件</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/26
 */
public class FNamingValue
{
   // 内容变更标志
   private boolean m_bIsChanged = true;

   // 内容对象
   private Object m_oValue = null;

   /**
    * <p>创建组件的实例</p>
    * <p>create date:2005/02/18</p>
    *
    */
   public FNamingValue(){
   }

   /**
    * <p>获得内容变更标志</p>
    * <p>create date:2005/02/18</p>
    *
    * @return 内容变更标志
    */
   public boolean isChanged(){
      return m_bIsChanged;
   }

   /**
    * <p>获得内容对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @return 内容对象
    */
   public Object object(){
      return m_oValue;
   }

   /**
    * <p>设置内容对象</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oValue 内容对象
    * @return TRUE：成功<br>FALSE：失败
    */
   public boolean setObject(Object oValue){
      m_bIsChanged = true;
      m_oValue = oValue;
      return true;
   }
}
