/*
 * @(#)FPropertyTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.common;

import org.mo.com.lang.FError;
import org.mo.com.lang.RString;

/**
 * <p>从指定内容通过set属性函数设置给自己的父标签对象</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FPropertyTag
      extends FAbstractTag
{

   // 指定内容
   private String m_sItem = null;

   // 属性名称
   private String m_sProperty = null;

   /**
    * <p>获得指定内容</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 指定内容
    */
   public String item(){
      return m_sItem;
   }

   @Override
   public int onEnd(){
      // TODO Auto-generated method stub
      return 0;
   }

   /**
    * <p>开始标签的逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   public int onStart() throws FError{
      FAbstractTag oTag = (FAbstractTag)getParent();
      if(oTag != null){
         if(!RString.isEmpty(property())){
            //String sMethod = "set" + property();
            //Object[] oValue = new Object[] { oContext.source(item()) };
            //FClassUtil.invokeMethod(oTag, sMethod, false, oValue);
         }
      }
      return EVAL_PAGE;
   }

   /**
    * <p>获得属性名称</p>
    * <p>create date:2005/02/18</p>
    *
    * @retrun 属性名称
    */
   public String property(){
      return m_sProperty;
   }

   /**
    * <p>释放标签所使用的资源</p>
    * <p>create date:2005/02/18</p>
    *
    */
   public void release(){
      m_sItem = null;
      m_sProperty = null;
      super.release();
   }

   /**
    * <p>设置指定内容</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sItem 指定内容
    */
   public void setItem(String sItem){
      m_sItem = sItem;
   }

   /**
    * <p>设置属性名称</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sItem 属性名称
    */
   public void setProperty(String sProperty){
      m_sProperty = sProperty;
   }
}
