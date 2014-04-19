/*
 * @(#)FLogicTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.reflect.RClass;

/**
 * <p>逻辑操作标签</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FLogicTag
      extends FAbstractTag
{

   // 逻辑实例
   private FLogicTag m_oInstance = null;

   // 逻辑类名称
   private String m_sInstance = null;

   // 参数信息
   private String m_sParameter = null;

   /**
    * <p>获得逻辑类名称</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 逻辑类名称
    */
   public String instance(){
      return m_sInstance;
   }

   /**
    * <p>结束标签的逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   public int onEnd(){
      return m_oInstance.onEnd();
   }

   /**
    * <p>开始标签的逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   public int onStart(){
      m_oInstance = RClass.newInstance(_context.parseString(instance()));
      m_oInstance.setParameter(parameter());
      return m_oInstance.onStart();
   }

   /**
    * <p>获得参数信息</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 参数信息
    */
   public String parameter(){
      return m_sParameter;
   }

   /**
    * <p>释放标签所使用的资源</p>
    * <p>create date:2005/02/18</p>
    *
    */
   public void release(){
      m_oInstance = null;
      m_sInstance = null;
      m_sParameter = null;
      super.release();
   }

   /**
    * <p>设置逻辑类名称</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sItem 逻辑类名称
    */
   public void setInstance(String sValue){
      m_sInstance = sValue;
   }

   /**
    * <p>设置参数信息</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sItem 参数信息
    */
   public void setParameter(String sValue){
      m_sParameter = sValue;
   }
}
