/*
 * @(#)FBaseJsTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建引入脚本标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseJsTag
      extends FAbstractHtmlTag
{

   // 类型
   protected String _type;

   // 源地址
   protected String _src;

   // 语言
   protected String _language;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果语言不空，则添加该属性
      if(null != _language){
         _writer.appendAttribute("language", _language, true);
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
      _type = null;
      _src = null;
      _language = null;
      super.release();
   }

   /**
    * <T>设置语言。</T>
    *
    * @param value 数据内容
    */
   public void setLanguage(String value){
      _language = value;
   }

   /**
    * <T>设置源地址。</T>
    *
    * @param value 数据内容
    */
   public void setSrc(String value){
      _src = value;
   }

   /**
    * <T>设置类型。</T>
    *
    * @param value 数据内容
    */
   public void setType(String value){
      _type = value;
   }

}
