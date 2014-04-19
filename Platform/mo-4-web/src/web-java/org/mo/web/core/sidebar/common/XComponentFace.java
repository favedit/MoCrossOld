/*
 * @(#)XComponentFace.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.sidebar.common;

import org.mo.com.xml.IXmlObject;

/**
 * <p>组件接口对象的XML节点基类</p>
 *
 * @author system
 */
public interface XComponentFace
      extends
         IXmlObject
{

   String NAME = "Component";

   /**
    * 名称的名称定义
    */
   String PTY_NAME = "name";

   /**
    * 标签的名称定义
    */
   String PTY_LABEL = "label";

   /**
    * 有效性的名称定义
    */
   String PTY_IS_VALID = "is_valid";

   /**
    * 注释的名称定义
    */
   String PTY_NOTE = "note";

   /**
    * 获得有效性的内容。
    *
    * @return 有效性
    */
   Boolean getIsValid();

   /**
    * 获得标签的内容。
    *
    * @return 标签
    */
   public String getLabel();

   /**
    * 获得名称的内容。
    *
    * @return 名称
    */
   String getName();

   /**
    * 获得注释的内容。
    *
    * @return 注释
    */
   public String getNote();

   /**
    * 设置有效性的内容。
    *
    * @param value 有效性
    */
   void setIsValid(Boolean value);

   /**
    * 设置标签的内容。
    *
    * @param value 标签
    */
   void setLabel(String value);

   /**
    * 设置名称的内容。
    *
    * @param value 名称
    */
   void setName(String value);

   /**
    * 设置注释的内容。
    *
    * @param value 注释
    */
   void setNote(String value);

}
