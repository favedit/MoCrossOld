/*
 * @(#)FBaseIncludeTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建包含基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseIncludeTag
      extends FAbstractHtmlTag
{

   // 包含地址
   protected String _uri;

   // 来源
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
      _uri = null;
      _source = null;
      super.release();
   }

   /**
    * <T>设置来源。</T>
    *
    * @param value 数据内容
    */
   public void setSource(String value){
      _source = value;
   }

   /**
    * <T>设置包含地址。</T>
    *
    * @param value 数据内容
    */
   public void setUri(String value){
      _uri = value;
   }

}
