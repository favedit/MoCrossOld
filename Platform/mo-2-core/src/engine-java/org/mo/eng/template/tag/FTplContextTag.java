package org.mo.eng.template.tag;

import org.mo.com.lang.generic.MString;
import org.mo.core.aop.RAop;
import org.mo.eng.environment.IEnvironmentConsole;

public class FTplContextTag
      extends FAbstractTplTag
{
   public static String NAME = "Context";

   protected String _path;

   @Override
   public void onDump(MString dump){
      dump.append(NAME);
      dump.append("[ path=", _path);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      IEnvironmentConsole environmentConsole = RAop.find(IEnvironmentConsole.class);
      String server = environmentConsole.findRegister("server_web");
      String url = environmentConsole.parseServer(server, "${system.context|server.http.url}");
      _context.append(url);
      _context.append(_path);
      return STOP;
   }

   public void setSource(String path){
      _path = path;
   }
}
