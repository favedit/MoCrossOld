package org.mo.data.synchronizer;

import org.mo.data.synchronizer.common.XSynchronizer;
import org.mo.eng.store.IXmlConfigConsole;

//============================================================
// <T>数据同步器控制台接口。</T>
//============================================================ 
public interface ISynchronizerConsole
      extends
         IXmlConfigConsole<XSynchronizer>
{
   //============================================================
   // <T>加载处理。</T>
   //============================================================
   void load();

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   void startup();
}
