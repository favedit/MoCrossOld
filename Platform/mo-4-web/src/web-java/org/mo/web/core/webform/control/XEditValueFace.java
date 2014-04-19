package org.mo.web.core.webform.control;

import org.mo.com.xml.IXmlObject;

//============================================================
// <T>数据内容接口对象的XML节点基类。</T>
//============================================================
public interface XEditValueFace
      extends IXmlObject
{
   // 名称定义
   String NAME = "MEditValue";

   // 数据内容的名称定义
   String PTY_DATA_VALUE = "data_value";

   //============================================================
   // <T>获得数据内容的内容。</T>
   //
   // @return 数据内容
   //============================================================
   String getDataValue();

   //============================================================
   // <T>设置数据内容的内容。</T>
   //
   // @param value 数据内容
   //============================================================
   void setDataValue(String value);
}