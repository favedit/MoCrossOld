package org.mo.eng.config.common;

import org.mo.com.xml.IXmlObject;

//============================================================
// <T>控件接口对象的XML节点基类。</T>
//============================================================
public interface XComponentFace
      extends IXmlObject
{
   // 名称定义
   String NAME = "IComponent";

   // 接口名称的名称定义
   String PTY_FACE = "face";

   // 实体名称的名称定义
   String PTY_INSTANCE = "instance";

   // 有效范围的名称定义
   String PTY_SCOPE = "scope";

   //============================================================
   // <T>获得接口名称的内容。</T>
   //
   // @return 接口名称
   //============================================================
   String getFace();

   //============================================================
   // <T>设置接口名称的内容。</T>
   //
   // @param value 接口名称
   //============================================================
   void setFace(String value);

   //============================================================
   // <T>获得实体名称的内容。</T>
   //
   // @return 实体名称
   //============================================================
   String getInstance();

   //============================================================
   // <T>设置实体名称的内容。</T>
   //
   // @param value 实体名称
   //============================================================
   void setInstance(String value);

   //============================================================
   // <T>获得有效范围的内容。</T>
   //
   // @return 有效范围
   //============================================================
   String getScope();

   //============================================================
   // <T>设置有效范围的内容。</T>
   //
   // @param value 有效范围
   //============================================================
   void setScope(String value);
}