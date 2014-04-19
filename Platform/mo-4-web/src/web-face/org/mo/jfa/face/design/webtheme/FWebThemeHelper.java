package org.mo.jfa.face.design.webtheme;

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
import org.mo.web.core.webtheme.IWebThemeConsole;

public class FWebThemeHelper
      extends FAbstractBuildHelper
      implements
         IWebThemeHelper
{

   private static ILogger _logger = RLogger.find(FWebThemeHelper.class);

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @ALink
   protected IWebThemeConsole _themeConsole;

   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeWebThemeNode());
   }

   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeWebThemeNode());
   }

   @Override
   public void list(XHelp xhelp,
                    XAction xaction){
      FXmlNode config = makeWebThemeNode();
      String encoding = xhelp.getProcessEncoding();
      String attributeName = xhelp.getOutputPath() + xaction.getOutputFile();
      _logger.debug(this, "list", "Build theme list (attributeName={0})", attributeName);
      // 建立标题页
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      parser.define("path", xaction.getProcessPath());
      parser.define("config", config);
      FString source = parser.parse();
      RFile.saveToFile(attributeName, source.toString(), encoding);
   }

   @Override
   public void bulid(XHelp xhelp,
                     XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      _logger.debug(this, "bulidTheme", "Build theme all (path={0})", path);
      // 建立全部数据集对象
      ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
      for(IXmlObject xds : _themeConsole.list()){
         String themeName = xds.innerGet("name");
         _logger.debug(null, "bulidAllList", "Build form help (bulidAllList={0})", themeName);
         themeName = RString.replace(themeName, '.', '_').toLowerCase();
         FXmlNode config = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         config.set("_type", xds.name());
         if(xds.hasChild()){
            config.set("child", "Y");
         }else{
            config.set("child", "N");
         }
         parser.reset();
         parser.define("path", xaction.getProcessPath());
         parser.define("config", config);
         FString source = parser.parse();
         RFile.saveToFile(path + "/" + themeName + ".html", source.toString(), encoding);
         if(xds.hasChild()){
            bulidChlid(xds, config, themeName, parser, xhelp, xaction);
         }
      }
   }

   //生成表单和表格属性函数，实现方法为递归
   public void bulidChlid(IXmlObject formAttribue,
                          FXmlNode theme,
                          String themeName,
                          ITemplateParser parser,
                          XHelp xhelp,
                          XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath() + xaction.getOutputPath();
      for(IXmlObject xAttribute : formAttribue.children()){
         String name = xAttribute.innerGet("name");
         _logger.debug(null, "main", "Build theme control all help (form={0}, attribute name={1})", themeName, name);
         String code = RString.replace(themeName, '.', '_').toLowerCase() + "." + name;
         FXmlNode fieldConfig = RXmlObject.convertDeepToNode(xAttribute, EXmlConfig.Simple);
         String type = xAttribute.name();
         if(RString.contains(type, "Filter")){
            type = "Filter";
         }
         fieldConfig.set("_type", type);
         if(xAttribute.hasChild()){
            fieldConfig.set("child", "Y");
         }else{
            fieldConfig.set("child", "N");
         }
         parser.reset();
         parser.define("path", xaction.getProcessPath());
         parser.define("theme", theme);
         parser.define("config", fieldConfig);
         FString source = parser.parse();
         RFile.saveToFile(path + "/" + code + ".html", source.toString(), encoding);
         if(xAttribute.hasChild()){
            bulidChlid(xAttribute, theme, themeName, parser, xhelp, xaction);
         }
      }

   }

   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeWebThemeNode());
   }

   protected FXmlNode makeWebThemeNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode();
      for(IXmlObject xds : _themeConsole.list()){
         FXmlNode node = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
         if(xds.hasChild()){
            node.set("chlid", "Y");
         }else{
            node.set("chlid", "N");
         }
         config.push(node);
      }
      return config;
   }
}
