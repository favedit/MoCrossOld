package org.mo.platform.server;

import org.mo.com.xml.FXmlNode;
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
   FXmlNode buildSimple(String name);
}
