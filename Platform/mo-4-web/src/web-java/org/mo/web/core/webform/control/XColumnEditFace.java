/*
 * @(#)XColumnEditFace.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.webform.control;

/**
 * <p>数据列接口对象的XML节点基类</p>
 *
 * @author system
 */
public interface XColumnEditFace
      extends
         XDataEditFace
{

   String NAME = "IColumnEdit";

   /**
    * 标题的名称定义
    */
   String PTY_CAPTION = "caption";

   /**
    * 显示方式的名称定义
    */
   String PTY_DISP_TYPE = "disp_type";

   /**
    * 图标的名称定义
    */
   String PTY_ICON = "icon";

   /**
    * 获得标题的内容。
    *
    * @return 标题
    */
   String getCaption();

   /**
    * 获得显示方式的内容。
    *
    * @return 显示方式
    */
   String getDispType();

   /**
    * 获得图标的内容。
    *
    * @return 图标
    */
   String getIcon();

   /**
    * 设置标题的内容。
    *
    * @param value 标题
    */
   void setCaption(String value);

   /**
    * 设置显示方式的内容。
    *
    * @param value 显示方式
    */
   void setDispType(String value);

   /**
    * 设置图标的内容。
    *
    * @param value 图标
    */
   void setIcon(String value);

}
