/*
 * @(#)XDataFace.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.webform.control;

/**
 * <p>数据接口对象的XML节点基类</p>
 *
 * @author system
 */
public interface XDataFace
      extends
         XControlFace,
         XPrintFace
{

   String NAME = "IData";

   /**
    * 缺省内容的名称定义
    */
   String PTY_DATA_DEFAULT = "data_default";

   /**
    * 数据名称的名称定义
    */
   String PTY_DATA_NAME = "data_name";

   /**
    * 数据必须的名称定义
    */
   String PTY_DATA_REQUIRE = "data_require";

   /**
    * 数据内容的名称定义
    */
   String PTY_DATA_VALUE = "data_value";

   /**
    * 对齐方式的名称定义
    */
   String PTY_LABEL_ALIGN = "label_align";

   /**
    * 标签颜色的名称定义
    */
   String PTY_LABEL_COLOR = "label_color";

   /**
    * 标签高度的名称定义
    */
   String PTY_LABEL_HEIGHT = "label_height";

   /**
    * 标签图标的名称定义
    */
   String PTY_LABEL_ICON = "label_icon";

   /**
    * 标签位置的名称定义
    */
   String PTY_LABEL_POSITION = "label_position";

   /**
    * 显示方式的名称定义
    */
   String PTY_LABEL_TYPE = "label_type";

   /**
    * 标签宽度的名称定义
    */
   String PTY_LABEL_WIDTH = "label_width";

   /**
    * 选取父的名称定义
    */
   String PTY_PICKER_PARENT = "picker_parent";

   /**
    * 选取宽度的名称定义
    */
   String PTY_PICKER_WIDTH = "picker_width";

   /**
    * 获得缺省内容的内容。
    *
    * @return 缺省内容
    */
   String getDataDefault();

   /**
    * 获得数据名称的内容。
    *
    * @return 数据名称
    */
   String getDataName();

   /**
    * 获得数据必须的内容。
    *
    * @return 数据必须
    */
   Boolean getDataRequire();

   /**
    * 获得数据内容的内容。
    *
    * @return 数据内容
    */
   String getDataValue();

   /**
    * 获得对齐方式的内容。
    *
    * @return 对齐方式
    */
   String getLabelAlign();

   /**
    * 获得标签颜色的内容。
    *
    * @return 标签颜色
    */
   String getLabelColor();

   /**
    * 获得标签高度的内容。
    *
    * @return 标签高度
    */
   String getLabelHeight();

   /**
    * 获得标签图标的内容。
    *
    * @return 标签图标
    */
   String getLabelIcon();

   /**
    * 获得标签位置的内容。
    *
    * @return 标签位置
    */
   String getLabelPosition();

   /**
    * 获得显示方式的内容。
    *
    * @return 显示方式
    */
   String getLabelType();

   /**
    * 获得标签宽度的内容。
    *
    * @return 标签宽度
    */
   String getLabelWidth();

   /**
    * 获得选取父的内容。
    *
    * @return 选取父
    */
   String getPickerParent();

   /**
    * 获得选取宽度的内容。
    *
    * @return 选取宽度
    */
   String getPickerWidth();

   /**
    * 设置缺省内容的内容。
    *
    * @param value 缺省内容
    */
   void setDataDefault(String value);

   /**
    * 设置数据名称的内容。
    *
    * @param value 数据名称
    */
   void setDataName(String value);

   /**
    * 设置数据必须的内容。
    *
    * @param value 数据必须
    */
   void setDataRequire(Boolean value);

   /**
    * 设置数据内容的内容。
    *
    * @param value 数据内容
    */
   void setDataValue(String value);

   /**
    * 设置对齐方式的内容。
    *
    * @param value 对齐方式
    */
   void setLabelAlign(String value);

   /**
    * 设置标签颜色的内容。
    *
    * @param value 标签颜色
    */
   void setLabelColor(String value);

   /**
    * 设置标签高度的内容。
    *
    * @param value 标签高度
    */
   void setLabelHeight(String value);

   /**
    * 设置标签图标的内容。
    *
    * @param value 标签图标
    */
   void setLabelIcon(String value);

   /**
    * 设置标签位置的内容。
    *
    * @param value 标签位置
    */
   void setLabelPosition(String value);

   /**
    * 设置显示方式的内容。
    *
    * @param value 显示方式
    */
   void setLabelType(String value);

   /**
    * 设置标签宽度的内容。
    *
    * @param value 标签宽度
    */
   void setLabelWidth(String value);

   /**
    * 设置选取父的内容。
    *
    * @param value 选取父
    */
   void setPickerParent(String value);

   /**
    * 设置选取宽度的内容。
    *
    * @param value 选取宽度
    */
   void setPickerWidth(String value);

}
