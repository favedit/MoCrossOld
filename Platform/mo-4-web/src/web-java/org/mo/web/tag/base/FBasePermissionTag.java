/*
 * @(#)FBasePermissionTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建判断用户权限基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBasePermissionTag
      extends FAbstractHtmlTag
{

   // 数据源
   protected String _source;

   // 角色名称
   protected String _role;

   // 模块名称
   protected String _module;

   // 命令内容
   protected String _action;

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
      _role = null;
      _module = null;
      _action = null;
      super.release();
   }

   /**
    * <T>设置命令内容。</T>
    *
    * @param value 数据内容
    */
   public void setAction(String value){
      _action = value;
   }

   /**
    * <T>设置模块名称。</T>
    *
    * @param value 数据内容
    */
   public void setModule(String value){
      _module = value;
   }

   /**
    * <T>设置角色名称。</T>
    *
    * @param value 数据内容
    */
   public void setRole(String value){
      _role = value;
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
