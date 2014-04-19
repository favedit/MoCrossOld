package org.mo.eng.environment;

import org.mo.com.lang.IAttributes;
import org.mo.eng.environment.common.XEnvironment;
import org.mo.eng.store.IXmlConfigConsole;

public interface IEnvironmentConsole
      extends
         IXmlConfigConsole<XEnvironment>
{
   String findDefine(String source);

   String findHomePath();

   String findRegister(String name);

   String findServerDefine(String server,
                           String source);

   void loadDirectory(String directory);

   String parse(String source);

   String parseServer(String server,
                      String source);

   void register(String name,
                 String value);

   IAttributes registers();

   void reset();
}
