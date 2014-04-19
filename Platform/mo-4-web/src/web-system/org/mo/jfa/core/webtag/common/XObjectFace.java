/*
 * @(#)XObjectFace.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.core.webtag.common;

import org.mo.com.xml.IXmlObject;

/**
 * <p>对象对象的XML节点基类</p>
 *
 * @author system
 */
public interface XObjectFace
      extends
         IXmlObject
{

   String NAME = "IObject";

   /**
    * 名称的名称定义
    */
   String PTY_NAME = "name";

   /**
    * 标签的名称定义
    */
   String PTY_LABEL = "label";

   /**
    * 注释的名称定义
    */
   String PTY_NOTE = "note";

   /**
    * 类型的名称定义
    */
   String PTY__TYPE = "_type";

   /**
    * 有效性的名称定义
    */
   String PTY_IS_VALID = "is_valid";

   /**
    * 获得名称的内容。
    *
    * @return 名称
    */
   String getName();

   /**
    * 设置名称的内容。
    *
    * @param value 名称
    */
   void setName(String value);

   /**
    * 获得标签的内容。
    *
    * @return 标签
    */
   public String getLabel();

   /**
    * 设置标签的内容。
    *
    * @param value 标签
    */
   void setLabel(String value);

   /**
    * 获得注释的内容。
    *
    * @return 注释
    */
   public String getNote();

   /**
    * 设置注释的内容。
    *
    * @param value 注释
    */
   void setNote(String value);

   /**
    * 获得类型的内容。
    *
    * @return 类型
    */
   String get_type();

   /**
    * 设置类型的内容。
    *
    * @param value 类型
    */
   void set_type(String value);

   /**
    * 获得有效性的内容。
    *
    * @return 有效性
    */
   String getIsValid();

   /**
    * 设置有效性的内容。
    *
    * @param value 有效性
    */
   void setIsValid(String value);

}
