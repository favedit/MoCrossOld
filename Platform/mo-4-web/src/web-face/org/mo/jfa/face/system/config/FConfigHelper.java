package org.mo.jfa.face.system.config;

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
import org.mo.eng.config.IConfigConsole;
import org.mo.eng.help.FAbstractBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.template.ITemplateParser;

public class FConfigHelper
      extends FAbstractBuildHelper
      implements
         IConfigHelper
{

   private static ILogger _logger = RLogger.find(FConfigHelper.class);

   @ALink
   protected IConfigConsole _configConsole;

   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeConfigNode());
   }

   @Override
   public void buildConfigAll(XHelp xhelp,
                              XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildConfigAll", "Build config all (path={0})", path);
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xds : _configConsole.list()){

         String name = xds.innerGet("name");
         _logger.debug(null, "buildConfigAll", "Build config help (dataset={0})", name);
         name = RString.replace(name, '.', '_').toLowerCase();
         FXmlNode config = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         parser.reset();
         parser.define("path", xaction.getProcessPath());
         parser.define("config", config);
         FString source = parser.parse();
         RFile.saveToFile(path + "/" + name + ".html", source.toString(), encoding);
      }
   }

   @Override
   public void buildItemdAll(XHelp xhelp,
                             XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildItemAll", "Build config item all (path={0})", path);
      // 建立全部数据集字段对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xConfig : _configConsole.list()){
         String configName = xConfig.innerGet("name");
         FXmlNode configNode = RXmlObject.convertToNode(xConfig, EXmlConfig.Simple);
         for(IXmlObject xField : xConfig.children()){
            String itemName = xField.innerGet("name");
            _logger.debug(null, "main", "Build config item help (config={0}, item={1})", configName, itemName);
            String code = RString.replace(configName, '.', '_').toLowerCase() + "." + itemName;
            FXmlNode itemConfig = RXmlObject.convertToNode(xField, EXmlConfig.Simple);
            parser.reset();
            parser.define("path", xaction.getProcessPath());
            parser.define("config", configNode);
            parser.define("item", itemConfig);
            FString source = parser.parse();
            RFile.saveToFile(path + "/" + code + ".html", source.toString(), encoding);
         }
         //break;
      }
   }

   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeConfigNode());
   }

   @Override
   public void buildList(XHelp xhelp,
                         XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String itemName = xhelp.getOutputPath() + xaction.getOutputFile();
      _logger.debug(this, "buildList", "Build config item (item={0})", itemName);
      // 建立标题页
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      FXmlNode config = new FXmlNode();
      for(IXmlObject xConfig : _configConsole.list()){
         FXmlNode node = RXmlObject.convertToNode(xConfig, EXmlConfig.Simple);
         config.push(node);
      }
      parser.define("path", xaction.getProcessPath());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(itemName, source.toString(), encoding);
   }

   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeConfigNode());
   }

   protected FXmlNode makeConfigNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode();
      for(IXmlObject xds : _configConsole.list()){
         FXmlNode node = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         config.push(node);
      }
      return config;
   }

}
