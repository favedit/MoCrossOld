/*
 * @(#)FBaseModTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建取模基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseModTag
      extends FAbstractHtmlTag
{

   // 数据源
   protected String _source;

   // 比较值
   protected String _value;

   // 是否相等
   protected String _equals;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果是否相等不空，则添加该属性
      if(null != _equals){
         _writer.appendAttribute("equals", _equals, true);
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
      _source = null;
      _value = null;
      _equals = null;
      super.release();
   }

   /**
    * <T>设置是否相等。</T>
    *
    * @param value 数据内容
    */
   public void setEquals(String value){
      _equals = value;
   }

   /**
    * <T>设置数据源。</T>
    *
    * @param value 数据内容
    */
   public void setSource(String value){
      _source = value;
   }

   /**
    * <T>设置比较值。</T>
    *
    * @param value 数据内容
    */
   public void setValue(String value){
      _value = value;
   }

}
