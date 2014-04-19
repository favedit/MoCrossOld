/*
 * @(#)FBaseDefineTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建定义标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseDefineTag
      extends FAbstractHtmlTag
{

   // 数据源
   protected String _source;

   // 别称
   protected String _alias;

   // 索引
   protected String _index;

   // 默认值
   protected String _default;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果别称不空，则添加该属性
      if(null != _alias){
         _writer.appendAttribute("alias", _alias, true);
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
      _alias = null;
      _index = null;
      _default = null;
      super.release();
   }

   /**
    * <T>设置别称。</T>
    *
    * @param value 数据内容
    */
   public void setAlias(String value){
      _alias = value;
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
    * <T>设置索引。</T>
    *
    * @param value 数据内容
    */
   public void setIndex(String value){
      _index = value;
   }

   /**
    * <T>设置数据源。</T>
    *
    * @param value 数据内容
    */
   public void setSource(String value){
      _source = value;
   }

}
