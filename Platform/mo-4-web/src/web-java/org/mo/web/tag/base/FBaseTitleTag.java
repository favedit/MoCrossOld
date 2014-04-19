/*
 * @(#)FBaseTitleTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建标题标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseTitleTag
      extends FAbstractHtmlTag
{

   // 标题内容
   protected String _caption;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果样式不为空，则添加该样式
      if(null != _style){
         _writer.appendAttribute("style", _style, true);
      }
   }

   /**
    * <T>释放所有属性。</T>
    *
    */
   public void release(){
      _caption = null;
      super.release();
   }

   /**
    * <T>设置标题内容。</T>
    *
    * @param value 数据内容
    */
   public void setCaption(String value){
      _caption = value;
   }

}
