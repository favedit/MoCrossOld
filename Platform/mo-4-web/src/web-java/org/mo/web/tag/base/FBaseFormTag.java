/*
 * @(#)FBaseFormTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建表单标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseFormTag
      extends FAbstractHtmlTag
{

   // 名称
   protected String _name;

   // 窗口或框架的名称
   protected String _target;

   // 动作
   protected String _action;

   // 发送数据的方式
   protected String _method;

   // 编码
   protected String _enctype;

   // 上载格式
   protected String _multipart;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果名称不空，则添加该属性
      if(null != _name){
         _writer.appendAttribute("name", _name, true);
      }
      /// 如果窗口或框架的名称不空，则添加该属性
      if(null != _target){
         _writer.appendAttribute("target", _target, true);
      }
      /// 如果动作不空，则添加该属性
      if(null != _action){
         _writer.appendAttribute("action", _action, true);
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
      _name = null;
      _target = null;
      _action = null;
      _method = null;
      _enctype = null;
      _multipart = null;
      super.release();
   }

   /**
    * <T>设置动作。</T>
    *
    * @param value 数据内容
    */
   public void setAction(String value){
      _action = value;
   }

   /**
    * <T>设置编码。</T>
    *
    * @param value 数据内容
    */
   public void setEnctype(String value){
      _enctype = value;
   }

   /**
    * <T>设置发送数据的方式。</T>
    *
    * @param value 数据内容
    */
   public void setMethod(String value){
      _method = value;
   }

   /**
    * <T>设置上载格式。</T>
    *
    * @param value 数据内容
    */
   public void setMultipart(String value){
      _multipart = value;
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
    * <T>设置窗口或框架的名称。</T>
    *
    * @param value 数据内容
    */
   public void setTarget(String value){
      _target = value;
   }

}
