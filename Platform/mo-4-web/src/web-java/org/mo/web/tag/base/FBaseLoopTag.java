/*
 * @(#)FBaseLoopTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建循环标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseLoopTag
      extends FAbstractHtmlTag
{

   // 项目名称
   protected String _source;

   // 数据别称
   protected String _alias;

   // 循环别称
   protected String _looper;

   // 过滤器
   protected String _filter;

   // 开始位置
   protected String _offset;

   // 循环次数
   protected String _count;

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
      _source = null;
      _alias = null;
      _looper = null;
      _filter = null;
      _offset = null;
      _count = null;
      super.release();
   }

   /**
    * <T>设置数据别称。</T>
    *
    * @param value 数据内容
    */
   public void setAlias(String value){
      _alias = value;
   }

   /**
    * <T>设置循环次数。</T>
    *
    * @param value 数据内容
    */
   public void setCount(String value){
      _count = value;
   }

   /**
    * <T>设置过滤器。</T>
    *
    * @param value 数据内容
    */
   public void setFilter(String value){
      _filter = value;
   }

   /**
    * <T>设置循环别称。</T>
    *
    * @param value 数据内容
    */
   public void setLooper(String value){
      _looper = value;
   }

   /**
    * <T>设置开始位置。</T>
    *
    * @param value 数据内容
    */
   public void setOffset(String value){
      _offset = value;
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
