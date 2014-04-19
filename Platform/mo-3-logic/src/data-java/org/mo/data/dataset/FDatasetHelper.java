package org.mo.data.dataset;

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
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.help.FAbstractBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.template.ITemplateParser;

public class FDatasetHelper
      extends FAbstractBuildHelper
      implements
         IDatasetHelper
{
   private static ILogger _logger = RLogger.find(FDatasetHelper.class);

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @ALink
   protected IDatasetConsole _datasetConsole;

   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeDatsetsNode());
   }

   @Override
   public void buildDatasetAll(XHelp xhelp,
                               XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildDatasetAll", "Build dataset all (path={0})", path);
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xds : _datasetConsole.list()){
         String name = xds.innerGet("name");
         _logger.debug(null, "buildDatasetAll", "Build dataset help (dataset={0})", name);
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
   public void buildFieldAll(XHelp xhelp,
                             XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildFieldAll", "Build dataset field all (path={0})", path);
      // 建立全部数据集字段对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xdataset : _datasetConsole.list()){
         String datasetName = xdataset.innerGet("name");
         FXmlNode datasetNode = RXmlObject.convertToNode(xdataset, EXmlConfig.Simple);
         for(IXmlObject xfield : xdataset.children()){
            String fieldName = xfield.innerGet("name");
            _logger.debug(null, "main", "Build dataset field help (dataset={0}, field={1})", datasetName, fieldName);
            String code = RString.replace(datasetName, '.', '_').toLowerCase() + "." + fieldName;
            FXmlNode fieldConfig = RXmlObject.convertToNode(xfield, EXmlConfig.Simple);
            parser.reset();
            parser.define("path", xaction.getProcessPath());
            parser.define("dataset", datasetNode);
            parser.define("field", fieldConfig);
            FString source = parser.parse();
            RFile.saveToFile(path + "/" + code + ".html", source.toString(), encoding);
         }
         //break;
      }
   }

   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeDatsetsNode());
   }

   @Override
   public void buildList(XHelp xhelp,
                         XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String fileName = xhelp.getOutputPath() + xaction.getOutputFile();
      _logger.debug(this, "buildList", "Build dataset list (file={0})", fileName);
      // 建立标题页
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      FXmlNode config = new FXmlNode();
      for(IXmlObject xdataset : _datasetConsole.list()){
         FXmlNode node = RXmlObject.convertToNode(xdataset, EXmlConfig.Simple);
         config.push(node);
      }
      parser.define("path", xaction.getProcessPath());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(fileName, source.toString(), encoding);
   }

   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeDatsetsNode());
   }

   protected FXmlNode makeDatsetsNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode();
      for(IXmlObject xds : _datasetConsole.list()){
         FXmlNode node = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         config.push(node);
      }
      return config;
   }
}
