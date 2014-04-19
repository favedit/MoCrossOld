package org.mo.eng.store;

import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;

//============================================================
// <T>支持配置节点和配置对象转换的接口。</T>
//
// @author maocy
// @version 1.00 - 2008/12/24
//============================================================
public interface IXmlConfigConvert
{
   //============================================================
   // <T>根据配置节点创建一个配置对象。</T>
   //
   // @param config 配置节点
   // @param type 设置类型
   // @return 配置对象
   //============================================================
   IXmlObject createElement(FXmlNode config,
                            EXmlConfig typeCd);

   //============================================================
   // <T>将一个配置节点转换为配置对象。</T>
   //
   // @param instance 配置对象
   // @return 配置节点
   //============================================================
   FXmlNode convertInstanceToNode(Object instance);

   //============================================================
   // <T>将一个配置节点转换为配置对象。</T>
   //
   // @param instance 配置对象
   // @param typeCd 类型
   // @return 配置节点
   //============================================================
   FXmlNode convertInstanceToNode(Object instance,
                                  EXmlConfig typeCd);

   //============================================================
   // <T>将一个配置节点转换为配置对象。</T>
   //
   // @param xconfig 配置节点
   // @return 配置对象
   //============================================================
   Object convertNodeToInstance(FXmlNode xconfig);

   //============================================================
   // <T>将一个配置设置节点转换为配置对象。</T>
   //
   // @param xconfig 配置设置节点
   // @return 配置对象
   //============================================================
   Object convertConfigToInstance(FXmlConfig xconfig);
}
