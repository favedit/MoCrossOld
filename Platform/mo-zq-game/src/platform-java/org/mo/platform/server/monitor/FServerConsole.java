package org.mo.platform.server.monitor;

import org.mo.com.lang.FObjects;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.platform.server.common.XServer;

//============================================================
// <T>构造服务器控制台接口。</T>
//============================================================
public class FServerConsole
      extends FXmlConfigConsole<XServer>
    implements   IServerConsole
{   
   // 监视器集合
   protected FServerMonitor monitor = new FServerMonitor();
   
   //============================================================
   // <T>创建集合。</T>
   //
   // @return 集合
   //============================================================
   @Override
   protected FObjects<XServer> createCollection() {
      return new FObjects<XServer>(XServer.class);
   }
  
   //============================================================
   // <T>启动处理。</T>
   //
   //============================================================
   @Override
   public void startup() {
      monitor.startup();
   }
}
