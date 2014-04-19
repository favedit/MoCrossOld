/*
 * @(#)FBaseHiddenTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建隐藏域标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseHiddenTag
      extends FAbstractHtmlTag
{

   // 编号
   protected String _id;

   // 名称
   protected String _name;

   // 数据源
   protected String _source;

   // 设置
   protected String _config;

   // 值
   protected String _value;

   // 状态
   protected String _status;

   // 默认值
   protected String _default;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果编号不空，则添加该属性
      if(null != _id){
         _writer.appendAttribute("id", _id, true);
      }
      /// 如果值不空，则添加该属性
      if(null != _value){
         _writer.appendAttribute("value", _value, true);
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
      _id = null;
      _name = null;
      _source = null;
      _config = null;
      _value = null;
      _status = null;
      _default = null;
      super.release();
   }

   /**
    * <T>设置设置。</T>
    *
    * @param value 数据内容
    */
   public void setConfig(String value){
      _config = value;
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
    * <T>设置编号。</T>
    *
    * @param value 数据内容
    */
   public void setId(String value){
      _id = value;
   }

   /**
    * <T>设置名称。</T>
    *
    * @param value 数据内容
    */
   public void setName(String value){
      _name = value;
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
    * <T>设置状态。</T>
    *
    * @param value 数据内容
    */
   public void setStatus(String value){
      _status = value;
   }

   /**
    * <T>设置值。</T>
    *
    * @param value 数据内容
    */
   public void setValue(String value){
      _value = value;
   }

}
