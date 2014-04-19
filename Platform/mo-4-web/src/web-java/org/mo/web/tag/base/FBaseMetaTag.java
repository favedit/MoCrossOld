/*
 * @(#)FBaseMetaTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建元基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseMetaTag
      extends FAbstractHtmlTag
{

   // 名称
   protected String _name;

   // 内容
   protected String _content;

   // 字符集
   protected String _charset;

   // http响应头的信息
   protected String _httpequiv;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果名称不空，则添加该属性
      if(null != _name){
         _writer.appendAttribute("name", _name, true);
      }
      /// 如果字符集不空，则添加该属性
      if(null != _charset){
         _writer.appendAttribute("charset", _charset, true);
      }
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
      _content = null;
      _charset = null;
      _httpequiv = null;
      super.release();
   }

   /**
    * <T>设置字符集。</T>
    *
    * @param value 数据内容
    */
   public void setCharset(String value){
      _charset = value;
   }

   /**
    * <T>设置内容。</T>
    *
    * @param value 数据内容
    */
   public void setContent(String value){
      _content = value;
   }

   /**
    * <T>设置http响应头的信息。</T>
    *
    * @param value 数据内容
    */
   public void setHttpequiv(String value){
      _httpequiv = value;
   }

   /**
    * <T>设置名称。</T>
    *
    * @param value 数据内容
    */
   public void setName(String value){
      _name = value;
   }

}
