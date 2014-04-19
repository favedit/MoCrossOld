package org.mo.platform.server;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.persistence.common.FXmlPersistence;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.platform.server.common.XServer;

//============================================================
// <T>构造服务器控制台接口。</T>
//============================================================
public class FServerConsole
      extends FXmlConfigConsole<XServer>
      implements
         IServerConsole
{
   //============================================================
   // <T>创建集合。</T>
   //
   // @return 集合
   //============================================================
   @Override
   protected FObjects<XServer> createCollection(){
      return new FObjects<XServer>(XServer.class);
   }

   //============================================================
   // <T>建立简单信息。</T>
   //
   // @param name 名称
   // @return 配置
   //============================================================
   @Override
   public FXmlNode buildSimple(String name){
      XServer xtools = get(name);
      FXmlPersistence persistence = _persistenceConsole.find(FXmlPersistence.class, _persistence);
      FXmlNode config = persistence.saveObject(xtools, EXmlConfig.Simple);
      for(FXmlNode node : config.allNodes()){
         if(node.isName(XServer.NAME)){
            String attributes = node.get("attributes");
            if(RString.isNotEmpty(attributes)){
               node.removeAttribute("attributes");
               node.attributes().split(attributes, "=", "\n", true);
            }
         }
      }
      return config;
   }
}
