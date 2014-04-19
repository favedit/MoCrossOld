/*
 * @(#)FBaseTreeTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建树目录基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseTreeTag
      extends FAbstractHtmlTag
{

   // 名称
   protected String _name;

   // 来源
   protected String _source;

   // 权限控制
   protected String _permission;

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
      _name = null;
      _source = null;
      _permission = null;
      super.release();
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
    * <T>设置权限控制。</T>
    *
    * @param value 数据内容
    */
   public void setPermission(String value){
      _permission = value;
   }

   /**
    * <T>设置来源。</T>
    *
    * @param value 数据内容
    */
   public void setSource(String value){
      _source = value;
   }

}
