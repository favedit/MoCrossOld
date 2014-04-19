/*
 * @(#)FBaseMatchTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建判断内容是否与指定值匹配基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseMatchTag
      extends FAbstractHtmlTag
{

   // 数据源
   protected String _source;

   // 比较值
   protected String _value;

   // 开始位置
   protected String _begin;

   // 结束位置
   protected String _end;

   // 匹配位数
   protected String _length;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果匹配位数不空，则添加该属性
      if(null != _length){
         _writer.appendAttribute("length", _length, true);
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
      _begin = null;
      _end = null;
      _length = null;
      super.release();
   }

   /**
    * <T>设置开始位置。</T>
    *
    * @param value 数据内容
    */
   public void setBegin(String value){
      _begin = value;
   }

   /**
    * <T>设置结束位置。</T>
    *
    * @param value 数据内容
    */
   public void setEnd(String value){
      _end = value;
   }

   /**
    * <T>设置匹配位数。</T>
    *
    * @param value 数据内容
    */
   public void setLength(String value){
      _length = value;
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
