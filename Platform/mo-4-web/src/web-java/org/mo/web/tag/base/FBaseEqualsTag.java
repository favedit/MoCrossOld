/*
 * @(#)FBaseEqualsTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建判断内容是否相等基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseEqualsTag
      extends FAbstractHtmlTag
{

   // 数据源
   protected String _source;

   // 忽略大小写数据值
   protected String _value;

   // 不忽略大小写数据值
   protected String _casevalue;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果忽略大小写数据值不空，则添加该属性
      if(null != _value){
         _writer.appendAttribute("value", _value, true);
      }
      /// 如果不忽略大小写数据值不空，则添加该属性
      if(null != _casevalue){
         _writer.appendAttribute("casevalue", _casevalue, true);
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
      _casevalue = null;
      super.release();
   }

   /**
    * <T>设置不忽略大小写数据值。</T>
    *
    * @param value 数据内容
    */
   public void setCasevalue(String value){
      _casevalue = value;
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
    * <T>设置忽略大小写数据值。</T>
    *
    * @param value 数据内容
    */
   public void setValue(String value){
      _value = value;
   }

}
