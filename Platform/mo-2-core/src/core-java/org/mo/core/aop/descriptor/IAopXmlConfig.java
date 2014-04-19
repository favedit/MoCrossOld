package org.mo.core.aop.descriptor;

import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP的XML设置接口。</T>
//============================================================
public interface IAopXmlConfig
{
   //============================================================
   // <T>从设置节点中加载设置信息。</T>
   //
   // @param xconfig 设置节点
   //============================================================
   void loadAopConfig(FXmlNode xconfig);
}
