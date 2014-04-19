package org.mo.platform.server.monitor;

import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.store.IXmlConfigConvert;
import org.mo.platform.server.common.XServer;


//============================================================
// <T>服务器控制台接口。</T>
//============================================================
public interface IServerConsole
      extends
         IXmlConfigConsole<XServer>,
         IXmlConfigConvert
{
   //============================================================
   // <T>启动处理。</T>
   //
   //============================================================
   void startup();
}
