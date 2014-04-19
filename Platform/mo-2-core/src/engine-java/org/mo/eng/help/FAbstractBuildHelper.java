package org.mo.eng.help;

import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public abstract class FAbstractBuildHelper
      implements
         IBuildHelper
{
   @ALink
   protected ITemplateConsole _templateConsole;

   public void buildCatalog(XHelp xhelp,
                            FXmlNode config){
      String encoding = xhelp.getProcessEncoding();
      String name = xhelp.getName();
      String path = xhelp.getOutputPath();
      // 建立帮助索引文件
      ITemplateParser parser = _templateConsole.findParser(xhelp.getTemplateCatalog());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(path + "/" + name + ".hhc", source.toString(), encoding);
   }

   public void buildIndex(XHelp xhelp,
                          FXmlNode config){
      String encoding = xhelp.getProcessEncoding();
      String name = xhelp.getName();
      String path = xhelp.getOutputPath();
      // 建立帮助索引文件
      ITemplateParser parser = _templateConsole.findParser(xhelp.getTemplateIndex());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(path + "/" + name + ".hhk", source.toString(), encoding);
   }

   public void buildProject(XHelp xhelp,
                            FXmlNode config){
      String encoding = xhelp.getProcessEncoding();
      String name = xhelp.getName();
      String path = xhelp.getOutputRoot();
      // 建立帮助索引文件
      ITemplateParser parser = _templateConsole.findParser(xhelp.getTemplateProject());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(path + "/" + name + ".hhp", source.toString(), encoding);
   }
}
