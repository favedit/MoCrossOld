/*
 * @(#)FBaseFormTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建表单基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseFormTag
      extends FAbstractHtmlTag
{

   protected String _name;

   protected String _source;

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
      _name = null;
      _source = null;
      super.release();
   }

   public void setName(String value){
      _name = value;
   }

   public void setSource(String value){
      _source = value;
   }

}
