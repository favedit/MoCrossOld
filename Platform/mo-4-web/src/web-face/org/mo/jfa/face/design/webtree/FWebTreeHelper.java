package org.mo.jfa.face.design.webtree;

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
import org.mo.web.core.webtree.IWebTreeConsole;

public class FWebTreeHelper
      extends FAbstractBuildHelper
      implements
         IWebTreeHelper
{

   private static ILogger _logger = RLogger.find(FWebTreeHelper.class);

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @ALink
   protected IWebTreeConsole _webTreeConsole;

   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeTreeNode());
   }

   @Override
   public void buildTreeViewAll(XHelp xhelp,
                                XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildTreeViewAll", "Build TreeView all (path={0})", path);
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xds : _webTreeConsole.list()){
         String name = xds.innerGet("name");
         _logger.debug(null, "buildTreeViewAll", "Build TreeView help (TreeView={0})", name);
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
   public void buildTreeNodeAll(XHelp xhelp,
                                XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildFieldAll", "Build tree node all (path={0})", path);
      // 建立全部数据集字段对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xdataset : _webTreeConsole.list()){
         String treeViewName = xdataset.innerGet("name");
         FXmlNode treeViewNode = RXmlObject.convertToNode(xdataset, EXmlConfig.Simple);
         for(IXmlObject xfield : xdataset.children()){
            String treeNodeName = xfield.innerGet("name");
            _logger.debug(null, "main", "Build tree node help (treeViewName={0}, treeNodeName={1})", treeViewName, treeNodeName);
            String code = RString.replace(treeViewName, '.', '_').toLowerCase() + "." + treeNodeName;
            FXmlNode treeNodeNode = RXmlObject.convertToNode(xfield, EXmlConfig.Simple);
            parser.reset();
            parser.define("path", xaction.getProcessPath());
            parser.define("treeView", treeViewNode);
            parser.define("treeNode", treeNodeNode);
            FString source = parser.parse();
            RFile.saveToFile(path + "/" + code + ".html", source.toString(), encoding);
         }
         //break;
      }
   }

   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeTreeNode());
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
      for(IXmlObject xdataset : _webTreeConsole.list()){
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
      buildProject(xhelp, makeTreeNode());
   }

   protected FXmlNode makeTreeNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode();
      for(IXmlObject xds : _webTreeConsole.list()){
         FXmlNode node = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         config.push(node);
      }
      return config;
   }

}
