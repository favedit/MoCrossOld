/*
 * @(#)FBaseContextTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建环境标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseContextTag
      extends FAbstractHtmlTag
{

   // 环境
   protected String _path;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果环境不空，则添加该属性
      if(null != _path){
         _writer.appendAttribute("path", _path, true);
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
      _path = null;
      super.release();
   }

   /**
    * <T>设置环境。</T>
    *
    * @param value 数据内容
    */
   public void setPath(String value){
      _path = value;
   }

}
