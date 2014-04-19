/*
 * @(#)FBaseFrameTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建框架标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseFrameTag
      extends FAbstractHtmlTag
{

   // 编号
   protected String _id;

   // 名称
   protected String _name;

   // 源地址
   protected String _src;

   // 框架滚动条
   protected String _scrolling;

   // 框架边框
   protected String _frameborder;

   // 框架缩放
   protected String _noresize;

   // 宽度
   protected String _width;

   // 高度
   protected String _height;

   // 上下边距高度
   protected String _marginheight;

   // 上下边距宽度
   protected String _marginwidth;

   // 样式引入
   protected String _style;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果编号不空，则添加该属性
      if(null != _id){
         _writer.appendAttribute("id", _id, true);
      }
      /// 如果名称不空，则添加该属性
      if(null != _name){
         _writer.appendAttribute("name", _name, true);
      }
      /// 如果框架滚动条不空，则添加该属性
      if(null != _scrolling){
         _writer.appendAttribute("scrolling", _scrolling, true);
      }
      /// 如果框架边框不空，则添加该属性
      if(null != _frameborder){
         _writer.appendAttribute("frameborder", _frameborder, true);
      }
      /// 如果框架缩放不空，则添加该属性
      if(null != _noresize){
         _writer.appendAttribute("noresize", _noresize, true);
      }
      /// 如果宽度不空，则添加该属性
      if(null != _width){
         _writer.appendAttribute("width", _width, true);
      }
      /// 如果高度不空，则添加该属性
      if(null != _height){
         _writer.appendAttribute("height", _height, true);
      }
      /// 如果上下边距高度不空，则添加该属性
      if(null != _marginheight){
         _writer.appendAttribute("marginheight", _marginheight, true);
      }
      /// 如果上下边距宽度不空，则添加该属性
      if(null != _marginwidth){
         _writer.appendAttribute("marginwidth", _marginwidth, true);
      }
      /// 如果样式引入不空，则添加该属性
      if(null != _style){
         _writer.appendAttribute("style", _style, true);
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
      _src = null;
      _scrolling = null;
      _frameborder = null;
      _noresize = null;
      _width = null;
      _height = null;
      _marginheight = null;
      _marginwidth = null;
      _style = null;
      super.release();
   }

   /**
    * <T>设置框架边框。</T>
    *
    * @param value 数据内容
    */
   public void setFrameborder(String value){
      _frameborder = value;
   }

   /**
    * <T>设置高度。</T>
    *
    * @param value 数据内容
    */
   public void setHeight(String value){
      _height = value;
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
    * <T>设置上下边距高度。</T>
    *
    * @param value 数据内容
    */
   public void setMarginheight(String value){
      _marginheight = value;
   }

   /**
    * <T>设置上下边距宽度。</T>
    *
    * @param value 数据内容
    */
   public void setMarginwidth(String value){
      _marginwidth = value;
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
    * <T>设置框架缩放。</T>
    *
    * @param value 数据内容
    */
   public void setNoresize(String value){
      _noresize = value;
   }

   /**
    * <T>设置框架滚动条。</T>
    *
    * @param value 数据内容
    */
   public void setScrolling(String value){
      _scrolling = value;
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
    * <T>设置样式引入。</T>
    *
    * @param value 数据内容
    */
   public void setStyle(String value){
      _style = value;
   }

   /**
    * <T>设置宽度。</T>
    *
    * @param value 数据内容
    */
   public void setWidth(String value){
      _width = value;
   }

}
