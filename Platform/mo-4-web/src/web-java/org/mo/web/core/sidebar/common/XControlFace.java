/*
 * @(#)XControlFace.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.sidebar.common;

/**
 * <p>控件接口对象的XML节点基类</p>
 *
 * @author system
 */
public interface XControlFace
      extends
         XComponentFace
{

   String NAME = "Control";

   /**
    * 颜色的名称定义
    */
   String PTY_COLOR = "color";

   /**
    * 状态栏提示的名称定义
    */
   String PTY_HINT = "hint";

   /**
    * 控件样式的名称定义
    */
   String PTY_STYLE_REFER = "style_refer";

   /**
    * 标签样式的名称定义
    */
   String PTY_STYLE_LABEL = "style_label";

   /**
    * 编辑样式的名称定义
    */
   String PTY_STYLE_EDIT = "style_edit";

   /**
    * 高度的名称定义
    */
   String PTY_HEIGH = "heigh";

   /**
    * 宽度的名称定义
    */
   String PTY_WIDTH = "width";

   /**
    * 图片的名称定义
    */
   String PTY_ICON = "icon";

   /**
    * 获得颜色的内容。
    *
    * @return 颜色
    */
   String getColor();

   /**
    * 获得高度的内容。
    *
    * @return 高度
    */
   String getHeigh();

   /**
    * 获得状态栏提示的内容。
    *
    * @return 状态栏提示
    */
   String getHint();

   /**
    * 获得图片的内容。
    *
    * @return 图片
    */
   String getIcon();

   /**
    * 获得编辑样式的内容。
    *
    * @return 编辑样式
    */
   String getStyleEdit();

   /**
    * 获得标签样式的内容。
    *
    * @return 标签样式
    */
   String getStyleLabel();

   /**
    * 获得控件样式的内容。
    *
    * @return 控件样式
    */
   String getStyleRefer();

   /**
    * 获得宽度的内容。
    *
    * @return 宽度
    */
   String getWidth();

   /**
    * 设置颜色的内容。
    *
    * @param value 颜色
    */
   void setColor(String value);

   /**
    * 设置高度的内容。
    *
    * @param value 高度
    */
   void setHeigh(String value);

   /**
    * 设置状态栏提示的内容。
    *
    * @param value 状态栏提示
    */
   void setHint(String value);

   /**
    * 设置图片的内容。
    *
    * @param value 图片
    */
   void setIcon(String value);

   /**
    * 设置编辑样式的内容。
    *
    * @param value 编辑样式
    */
   void setStyleEdit(String value);

   /**
    * 设置标签样式的内容。
    *
    * @param value 标签样式
    */
   void setStyleLabel(String value);

   /**
    * 设置控件样式的内容。
    *
    * @param value 控件样式
    */
   void setStyleRefer(String value);

   /**
    * 设置宽度的内容。
    *
    * @param value 宽度
    */
   void setWidth(String value);

}
