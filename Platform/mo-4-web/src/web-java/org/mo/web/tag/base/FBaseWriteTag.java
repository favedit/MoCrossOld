/*
 * @(#)FBaseWriteTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建输出标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseWriteTag
      extends FAbstractHtmlTag
{

   // 项目名称
   protected String _source;

   // 输出格式
   protected String _format;

   // 默认值
   protected String _default;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果输出格式不空，则添加该属性
      if(null != _format){
         _writer.appendAttribute("format", _format, true);
      }
      /// 如果默认值不空，则添加该属性
      if(null != _default){
         _writer.appendAttribute("default", _default, true);
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
      _format = null;
      _default = null;
      super.release();
   }

   /**
    * <T>设置默认值。</T>
    *
    * @param value 数据内容
    */
   public void setDefault(String value){
      _default = value;
   }

   /**
    * <T>设置输出格式。</T>
    *
    * @param value 数据内容
    */
   public void setFormat(String value){
      _format = value;
   }

   /**
    * <T>设置项目名称。</T>
    *
    * @param value 数据内容
    */
   public void setSource(String value){
      _source = value;
   }

}
