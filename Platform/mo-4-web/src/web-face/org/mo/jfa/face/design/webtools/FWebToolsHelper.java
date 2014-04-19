package org.mo.jfa.face.design.webtools;

import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.help.FAbstractBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.template.ITemplateParser;
import org.mo.web.core.webtools.IWebToolsConsole;

public class FWebToolsHelper
      extends FAbstractBuildHelper
      implements
         IWebToolsHelper
{

   private static ILogger _logger = RLogger.find(FWebToolsHelper.class);

   @ALink
   protected IWebToolsConsole _webtoolsconsole;

   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeWebtoolsNode());
   }

   @Override
   public void buildWebToolsAll(XHelp xhelp,
                                XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildWebtoolsAll", "Build webtools all (path={0})", path);
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xds : _webtoolsconsole.list()){

         String name = xds.innerGet("name");
         _logger.debug(null, "buildWebtoolsAll", "Build webtools help (webtools={0})", name);
         name = RString.replace(name, '.', '_').toLowerCase();
         FXmlNode config = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         parser.reset();
         parser.define("path", xaction.getProcessPath());
         parser.define("webtools", config);
         FString source = parser.parse();
         RFile.saveToFile(path + "/" + name + ".html", source.toString(), encoding);
      }
   }

   @Override
   public void buildItemdAll(XHelp xhelp,
                             XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildItemAll", "Build webtools item all (path={0})", path);
      // 建立全部数据集字段对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xWebtools : _webtoolsconsole.list()){
         String webtoolsName = xWebtools.innerGet("name");
         FXmlNode webtoolsNode = RXmlObject.convertToNode(xWebtools, EXmlConfig.Simple);
         for(IXmlObject xfield : xWebtools.children()){
            String itemName = xfield.innerGet("name");
            _logger.debug(null, "main", "Build webtools item help (webtools={0}, item={1})", webtoolsName, itemName);
            String code = RString.replace(webtoolsName, '.', '_').toLowerCase() + "." + itemName;
            FXmlNode fieldConfig = RXmlObject.convertToNode(xfield, EXmlConfig.Simple);
            parser.reset();
            parser.define("path", xaction.getProcessPath());
            parser.define("webtools", webtoolsNode);
            parser.define("item", fieldConfig);
            FString source = parser.parse();
            RFile.saveToFile(path + "/" + code + ".html", source.toString(), encoding);
         }
         //break;
      }
   }

   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeWebtoolsNode());
   }

   @Override
   public void buildList(XHelp xhelp,
                         XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String itemName = xhelp.getOutputPath() + xaction.getOutputFile();
      _logger.debug(this, "buildList", "Build webtools list (file={0})", itemName);
      // 建立标题页
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      FXmlNode config = new FXmlNode();
      for(IXmlObject xWebtools : _webtoolsconsole.list()){
         FXmlNode node = RXmlObject.convertToNode(xWebtools, EXmlConfig.Simple);
         config.push(node);
      }
      parser.define("path", xaction.getProcessPath());
      parser.define("webtools", config);
      FString source = parser.parse();
      RFile.saveToFile(itemName, source.toString(), encoding);
   }

   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeWebtoolsNode());
   }

   protected FXmlNode makeWebtoolsNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode();
      for(IXmlObject xds : _webtoolsconsole.list()){
         FXmlNode node = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         config.push(node);
      }
      return config;
   }

}
