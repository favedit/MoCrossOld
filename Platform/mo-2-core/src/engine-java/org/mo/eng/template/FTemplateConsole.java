package org.mo.eng.template;

import java.io.File;
import org.mo.com.console.FConsole;
import org.mo.com.io.RFile;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.template.tag.FTplTag;
import org.mo.eng.template.tag.ITplTag;

//============================================================
// <T>模板控制台。</T>
//============================================================
public class FTemplateConsole
      extends FConsole
      implements
         ITemplateConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FTemplateConsole.class);

   // 类型
   @AProperty
   protected String _type;

   // 设置
   @AProperty
   protected String _config;

   // 工作路径
   @AProperty
   protected String _workpath;

   // 标签设置字典
   protected FDictionary<FTplTagConfig> _tagConfigs = new FDictionary<FTplTagConfig>(FTplTagConfig.class);

   //============================================================
   // <T>根据名称创建标签。</T>
   //
   // @param name 名称
   // @return 标签
   //============================================================
   @Override
   public ITplTag createTag(String name){
      name = RString.toLower(name);
      FTplTagConfig config = _tagConfigs.find(name);
      if(config != null){
         return config.newInstance();
      }
      return new FTplTag();
   }

   //============================================================
   // <T>根据来源查找模板。</T>
   //
   // @param source 来源
   // @return 模板
   //============================================================
   @Override
   public FXmlNode findTemplate(String source){
      // 检查文件存在
      String fileName = RFile.makeFilename(_workpath, RString.replace(source, '.', '/') + "." + _type);
      File file = new File(fileName);
      if(!file.isFile()){
         _logger.warn(this, "findTemplate", "Template file is not exists. (file_name={1})", fileName);
         return null;
      }
      // 加载配置文档
      FXmlDocument doc = new FXmlDocument();
      doc.setTextTrimFlag(false);
      doc.loadFile(file.getAbsolutePath());
      return doc.root();
   }

   //============================================================
   // <T>根据路径和名称查找模板。</T>
   //
   // @param path 路径
   // @param name 名称
   // @return 模板
   //============================================================
   @Override
   public FXmlNode findTemplate(String path,
                                String name){
      // 检查文件存在
      String pathName = RFile.makePathname(_workpath, path);
      File dir = new File(pathName);
      if(!dir.isDirectory()){
         _logger.warn(this, "findTemplate", "Directory is not exists. ({path_name={1})", pathName);
         return null;
      }
      String fileName = RFile.makeFilename(pathName, name) + "." + _type;
      File file = new File(fileName);
      if(!file.isFile()){
         _logger.warn(this, "findTemplate", "File is not exists. (file_name={1})", fileName);
         return null;
      }
      // 加载配置文档
      FXmlDocument doc = new FXmlDocument();
      doc.setTextTrimFlag(false);
      doc.loadFile(file.getAbsolutePath());
      return doc.root();
   }

   //============================================================
   // <T>根据来源查找解析器。</T>
   //
   // @param source 来源
   // @return 解析器
   //============================================================
   @Override
   public ITemplateParser findParser(String source){
      // 获得模板名称
      String templateName = source;
      if(source.contains("@")){
         templateName = source.substring(source.indexOf('@') + 1);
      }
      // 获得模板设置
      FXmlNode config = findTemplate(templateName);
      if(null != config){
         FTemplateParser parser = new FTemplateParser(config);
         parser.setTemplateName(source);
         return parser;
      }
      return null;
   }

   //============================================================
   // <T>根据路径和名称查找解析器。</T>
   //
   // @param path 路径
   // @param name 名称
   // @return 解析器
   //============================================================
   @Override
   public ITemplateParser findParser(String path,
                                     String name){
      FXmlNode config = findTemplate(path, name);
      if(null != config){
         FTemplateParser parser = new FTemplateParser(config);
         parser.setTemplateName(path + "|" + name);
         return parser;
      }
      return null;
   }

   //============================================================
   // <T>根据来源获得解析器。</T>
   //
   // @param source 来源
   // @return 解析器
   //============================================================
   @Override
   public ITemplateParser getParser(String source){
      ITemplateParser parser = findParser(source);
      if(null == parser){
         throw new FFatalError("Can't find parser. (source={1})", source);
      }
      return parser;
   }

   //============================================================
   // <T>根据路径和名称获得解析器。</T>
   //
   // @param path 路径
   // @param name 名称
   // @return 解析器
   //============================================================
   @Override
   public ITemplateParser getParser(String path,
                                    String name){
      ITemplateParser parser = findParser(path, name);
      if(null == parser){
         throw new FFatalError("Can't find parser. (path={1}, name={2})", path, name);
      }
      return parser;
   }

   //============================================================
   // <T>初始化设置。</T>
   //============================================================
   public void initializeConfig(){
      FXmlDocument doc = new FXmlDocument(_config);
      for(FXmlNode node : doc.root().nodes()){
         if(node.isName("Tag")){
            FTplTagConfig config = new FTplTagConfig(node);
            String name = RString.toLower(node.get("name"));
            _tagConfigs.set(name, config);
         }
      }
   }
}
