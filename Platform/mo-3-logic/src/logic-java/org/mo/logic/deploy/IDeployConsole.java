package org.mo.logic.deploy;

import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;

public interface IDeployConsole
      extends
         IXmlConfigConsole<IXmlObject>
{
   void install(EDeploySource type);

   void installAll(EDeploySource type);

   void uninstall(EDeploySource type);

   void uninstallAll(EDeploySource type);
}
