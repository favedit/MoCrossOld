package org.mo.core.aop.config;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP节点。</T>
//============================================================
public abstract class XAbsAopNode
      extends FObject
      implements
         IAopNode
{
   // 设置节点
   protected FXmlNode _config;

   //============================================================
   // <T>构造AOP节点。</T>
   //============================================================
   public XAbsAopNode(){
   }

   //============================================================
   // <T>获得设置节点。</T>
   //
   // @return 设置节点
   //============================================================
   public FXmlNode config(){
      return _config;
   }

   //============================================================
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode xconfig){
      _config = xconfig;
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" [");
      info.append(" key=");
      info.append(key());
      info.append(" ]");
      return info;
   }
}
