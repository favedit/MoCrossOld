/*
 * @(#)FHasDefineTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.common;

import org.mo.com.lang.FError;

/**
 * <p>检查是否定义了当前别称标签</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FHasDefineTag
      extends FAbstractTag
{

   // 指定内容
   private String m_sItem = null;

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
      return EVAL_PAGE;
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
      //return oContext.hasDefine(item()) ? EVAL_PAGE : SKIP_BODY;
      return SKIP_BODY;
   }

   /**
    * <p>释放标签所使用的资源</p>
    * <p>create date:2005/02/18</p>
    *
    */
   public void release(){
      m_sItem = null;
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
}
