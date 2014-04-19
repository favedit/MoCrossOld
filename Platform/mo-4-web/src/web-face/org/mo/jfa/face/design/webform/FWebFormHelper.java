package org.mo.jfa.face.design.webform;

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
import org.mo.web.core.webform.IWebFormConsole;

public class FWebFormHelper
      extends FAbstractBuildHelper
      implements
         IWebFormHelper
{

   private static ILogger _logger = RLogger.find(FWebFormHelper.class);

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeWebFormNode());
   }

   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeWebFormNode());
   }

   @Override
   public void bulidAllList(XHelp xhelp,
                            XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "bulidAllList", "Build form all (path={0})", path);
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xds : _formConsole.list()){
         String formName = xds.name();
         String name = xds.innerGet("name");
         _logger.debug(null, "bulidAllList", "Build form help (bulidAllList={0})", name);
         name = RString.replace(name, '.', '_').toLowerCase();
         if(("WebForm".equals(formName)) || ("WebTable".equals(formName))){

            FXmlNode config = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
            parser.reset();
            parser.define("path", xaction.getProcessPath());
            parser.define("config", config);
            FString source = parser.parse();
            RFile.saveToFile(path + "/" + name + ".html", source.toString(), encoding);
         }
      }
   }

   public void buildWebFormList(XHelp xhelp,
                                XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String fileName = xhelp.getOutputPath() + xaction.getOutputFile();
      _logger.debug(this, "buildWebFormList", "Build dataset list (buildWebFormList={0})", fileName);
      // 建立标题页
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      FXmlNode config = new FXmlNode();
      for(IXmlObject xdataset : _formConsole.list()){
         FXmlNode node = RXmlObject.convertToNode(xdataset, EXmlConfig.Simple);
         if("WebForm".equals(node.name())){
            config.push(node);
         }
      }
      parser.define("path", xaction.getProcessPath());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(fileName, source.toString(), encoding);
   }

   @Override
   public void buildWebTableList(XHelp xhelp,
                                 XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String fileName = xhelp.getOutputPath() + xaction.getOutputFile();
      _logger.debug(this, "buildWebTableList", "Build dataset list (file={0})", fileName);
      // 建立标题页
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      FXmlNode config = new FXmlNode();
      for(IXmlObject xdataset : _formConsole.list()){
         FXmlNode node = RXmlObject.convertToNode(xdataset, EXmlConfig.Simple);
         if("WebTable".equals(node.name())){
            config.push(node);
         }
      }
      parser.define("path", xaction.getProcessPath());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(fileName, source.toString(), encoding);
   }

   @Override
   public void buildAttribute(XHelp xhelp,
                              XAction xaction){
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "buildAttribute", "Build form control all (buildAttribute={0})", path);
      // 建立全部数据集字段对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xform : _formConsole.list()){
         String name = xform.name();
         if(("WebForm".equals(name)) || ("WebTable".equals(name))){
            String formName = xform.innerGet("name");
            FXmlNode formNode = RXmlObject.convertToNode(xform, EXmlConfig.Simple);
            if(xform.hasChild()){
               bulidAttributesChlid(xform, formNode, formName, parser, xhelp, xaction);
            }
         }
         //break;
      }
   }

   //生成表单和表格属性函数，实现方法为递归
   public void bulidAttributesChlid(IXmlObject formAttribue,
                                    FXmlNode form,
                                    String formName,
                                    ITemplateParser parser,
                                    XHelp xhelp,
                                    XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      for(IXmlObject xAttribute : formAttribue.children()){
         String name = xAttribute.innerGet("name");
         _logger.debug(null, "main", "Build form control all help (form={0}, attribute name={1})", formName, name);
         String code = RString.replace(formName, '.', '_').toLowerCase() + "." + name;
         FXmlNode fieldConfig = RXmlObject.convertToNode(xAttribute, EXmlConfig.Simple);
         parser.reset();
         parser.define("path", xaction.getProcessPath());
         parser.define("form", form);
         parser.define("control", fieldConfig);
         FString source = parser.parse();
         RFile.saveToFile(path + "/" + code + ".html", source.toString(), encoding);
         if(xAttribute.hasChild()){
            bulidAttributesChlid(xAttribute, form, formName, parser, xhelp, xaction);
         }
      }

   }

   @Override
   public void list(XHelp xhelp,
                    XAction xaction){
      FXmlNode config = makeWebFormNode();
      String encoding = xhelp.getProcessEncoding();
      String fileName = xhelp.getOutputPath() + xaction.getOutputFile();
      _logger.debug(this, "buildWebTableList", "Build dataset list (file={0})", fileName);
      // 建立标题页
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      parser.define("path", xaction.getProcessPath());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(fileName, source.toString(), encoding);
   }

   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeWebFormNode());
   }

   protected FXmlNode makeWebFormNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode();
      for(IXmlObject xds : _formConsole.list()){
         FXmlNode node = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         if(("WebForm".equals(node.name())) || ("WebTable".equals(node.name()))){
            node.set("_type", node.name());
            config.push(node);
         }
      }
      return config;
   }

}
