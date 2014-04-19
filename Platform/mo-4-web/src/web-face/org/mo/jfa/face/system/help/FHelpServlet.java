package org.mo.jfa.face.system.help;

import org.mo.com.io.FByteFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.help.IHelpConsole;
import org.mo.eng.help.common.XHelp;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.protocol.context.IWebContext;

public class FHelpServlet
      implements
         IHelpServlet
{

   private static ILogger _logger = RLogger.find(FHelpServlet.class);

   @ALink
   protected IHelpConsole _helpConsole;

   @ALink
   protected IEnvironmentConsole _envConsole;

   @Override
   public void download(IWebContext context,
                        ISqlContext sqlContext,
                        IWebServletResponse response){
      // 获得帮助定义
      String helpName = context.parameter("sel_collection");
      XHelp xhelp = _helpConsole.get(helpName);
      String name = xhelp.getName() + ".chm";
      try{
         // 设置传输信息
         response.setContentType("application/mshelp");
         response.addHeader("Content-Disposition", new String(("attachment; filename=" + name).getBytes("GBK"), "ISO-8859-1"));
      }catch(Exception e){
         throw new FFatalError(e);
      }

      String root = _envConsole.parse(xhelp.getOutputRoot());
      String fileName = RFile.makeFilename(root, name);
      _logger.debug(this, "build", "Load help file. (file={0})", fileName);
      FByteFile file = new FByteFile();
      file.loadFile(fileName);
      _logger.debug(this, "build", "Translate bytes. (size={0})", file.length());
      response.write(file.toArray());
   }
}
