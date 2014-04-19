/*
 * @(#)FBaseAllowTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建允许基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseAllowTag
      extends FAbstractHtmlTag
{

   // 用户角色
   protected String _roles;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果用户角色不空，则添加该属性
      if(null != _roles){
         _writer.appendAttribute("roles", _roles, true);
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
      _roles = null;
      super.release();
   }

   /**
    * <T>设置用户角色。</T>
    *
    * @param value 数据内容
    */
   public void setRoles(String value){
      _roles = value;
   }

}
