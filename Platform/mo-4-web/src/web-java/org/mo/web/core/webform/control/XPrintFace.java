/*
 * @(#)XPrintFace.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.webform.control;

import org.mo.com.xml.IXmlObject;

/**
 * <p>印刷接口对象的XML节点基类</p>
 *
 * @author system
 */
public interface XPrintFace
      extends
         IXmlObject
{

   String NAME = "IPrint";

   /**
    * 印刷颜色的名称定义
    */
   String PTY_PRINT_COLOR = "print_color";

   /**
    * 打印宽度的名称定义
    */
   String PTY_PRINT_WIDTH = "print_width";

   /**
    * 获得印刷颜色的内容。
    *
    * @return 印刷颜色
    */
   String getPrintColor();

   /**
    * 获得打印宽度的内容。
    *
    * @return 打印宽度
    */
   String getPrintWidth();

   /**
    * 设置印刷颜色的内容。
    *
    * @param value 印刷颜色
    */
   void setPrintColor(String value);

   /**
    * 设置打印宽度的内容。
    *
    * @param value 打印宽度
    */
   void setPrintWidth(String value);

}
