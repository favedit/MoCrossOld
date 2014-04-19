package org.mo.logic.batch.process.version;

import java.io.File;
import org.mo.com.io.FByteFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.process.FBatchSqlCommand;
import org.mo.mime.zip.FZipEntry;
import org.mo.mime.zip.IZipOutput;
import org.mo.mime.zip.RZip;

public class FVersionExport
      extends FBatchSqlCommand
      implements
         IVersionExport
{
   private static ILogger _logger = RLogger.find(FVersionExport.class);

   // 文件路径（属性名称）
   private final static String FILE_PATH = "file_path";

   // 配置文件路径（属性名称）
   private final static String CONFIG_PATH = "config_path";

   // 配置文件名称（属性名称）
   private final static String CONFIG_NAME = "config_name";

   // zip包保存路径（属性名称）
   private final static String OUTPUT_ZIP = "output";

   // 配置文件包括属性名称（属性名称）
   private final static String INCLUDE = "Include";

   // 配置文件不包括属性名称（属性名称）
   private final static String EXCLUDE = "Exclude";

   // 路径关键字（属性名称）
   private final static String PATH = "Path";

   // 路径关键字（属性名称）
   private final static String FILTER = "Filter";

   // （属性名称）
   private final static String LOCATION = "location";

   // （属性名称）
   private String _zipName = "";

   // zip包保存目录名称（属性名称）
   private String _output = "D:/eUIS-Store/eUIS-{YYMMDD-HH24MISS}.zip";

   // 导出时的配置文件目录有默认值
   private String _configPath = "D:/Workspace/eUIS/config/batch";

   // 处理时间
   private long _processTime;

   /**
    * <T>判断是否可以打包</T>
    * 
    */
   protected boolean canExport(IAttributes excludes,
                               File file){
      if(null != excludes && !excludes.isEmpty()){
         String filePath = file.getAbsolutePath();
         FStrings excludePaths = new FStrings();
         excludePaths.unpack(excludes.get(PATH));
         if(!excludePaths.isEmpty()){
            for(String excludePath : excludePaths){
               if(filePath.startsWith(excludePath)){
                  return false;
               }
            }
         }
         String fileName = file.getName();
         FStrings excludeFilters = new FStrings();
         excludeFilters.unpack(excludes.get(FILTER));
         if(!excludeFilters.isEmpty()){
            for(String excludeFilter : excludeFilters){
               if(fileName.equals(excludeFilter)){
                  return false;
               }
            }
         }
      }
      return true;
   }

   /**
    * <T>往zip包打文件</T>
    * 
    */
   @SuppressWarnings("resource")
   public void exportFile(IZipOutput zipOutput,
                          IAttributes excludes,
                          String rootPath,
                          File root){
      if(!root.canRead()){
         return;
      }
      String zipName = _zipName;
      for(File file : root.listFiles()){
         String name = file.getName();
         // 判断不把自己打入自己包中
         if(zipName.equals(name)){
            continue;
         }
         if(file.isDirectory()){
            if(!".svn".equals(name)){
               exportFile(zipOutput, excludes, rootPath, file);
            }
         }else{
            // 判断不把自己打入自己包中
            if(zipName.equals(name)){
               continue;
            }
            if(!canExport(excludes, file)){
               continue;
            }
            String path = RString.replace(file.getAbsolutePath(), "\\", "/");
            // 读取文件
            FByteFile bytes = new FByteFile(file.getAbsolutePath());
            // 导出文件
            _logger.debug(this, "exportFile", "Export file: {0}", path);
            FZipEntry entry = zipOutput.openEntry(path.substring(rootPath.length()).substring(1));
            entry.setSize(bytes.length());
            entry.setTime(_processTime);
            entry.setComment(Integer.toString(bytes.length()));
            zipOutput.write(bytes.toArray());
            bytes.store();
            zipOutput.flush();
         }
      }
   }

   @Override
   public void exportVersion(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      // 设置.zip包输出路径
      setOutput(config.get(OUTPUT_ZIP));
      // 设置打包用的配置文件总路径不包括文件名称
      setConfigPath(config.get(CONFIG_PATH));
      if(null != config && config.hasNode("Item")){
         _processTime = System.currentTimeMillis();
         // 获得zip包对象
         String subDate = RString.mid(_output, "{", "}");
         String outputFile = RString.replace(_output, "{" + subDate + "}", RDateTime.format(subDate));
         IZipOutput zipOutput = null;
         try{
            RFile.makeFileDirectory(outputFile);
            zipOutput = RZip.writeFile(outputFile);
            setZipName(outputFile);
            for(FXmlNode node : config.nodes("Item")){
               exportVersion(node, zipOutput);
            }
         }catch(Exception e){
            System.out.println("========================================");
            System.out.println("导出[" + outputFile + "]包时出错！" + _processTime);
            System.out.println("========================================");
            System.out.println(e);
            System.out.println("========================================");
            return;
         }finally{
            // 关闭打包流
            if(null != zipOutput){
               zipOutput.finish();
               zipOutput.close();
            }
         }
      }
   }

   /**
    * <T>打zip包版本</T>
    * 
    */
   protected void exportVersion(FXmlNode config,
                                IZipOutput zipOutput){
      // 获得要打包的路径文件
      String exportFile = RFile.makeFilename(config.get(FILE_PATH));
      if(RString.isNotEmpty(exportFile)){
         String configName = config.get(CONFIG_NAME);
         FXmlNode exportConfig = loadConfig(configName);
         FStrings items = getInclude(exportConfig);
         IAttributes attributes = getExclude(exportConfig, exportFile);
         if(null != items && !items.isEmpty()){
            for(String item : items){
               exportFile(zipOutput, attributes, exportFile, new File(exportFile + item));
            }
         }else{
            exportFile(zipOutput, attributes, exportFile, new File(exportFile));
         }
      }
   }

   /**
    * <T>获得包括目录或文件名称列表</T>
    * 
    */
   protected IAttributes getExclude(FXmlNode exportConfig,
                                    String exprotRoot){
      IAttributes attributes = null;
      if(null != exportConfig){
         attributes = new FAttributes();
         FStrings path = new FStrings();
         FStrings filter = new FStrings();
         for(FXmlNode excludes : exportConfig.nodes(EXCLUDE)){
            for(FXmlNode exclude : excludes.nodes()){
               if(exclude.isName(PATH)){
                  String excluedPath = exclude.get(LOCATION);
                  excluedPath = exprotRoot + "/" + excluedPath;
                  excluedPath = RFile.makeFilename(excluedPath);
                  path.push(excluedPath);
               }else if(exclude.isName(FILTER)){
                  String excluedFilter = exclude.get(LOCATION);
                  filter.push(excluedFilter);
               }
            }
         }
         if(!path.isEmpty()){
            attributes.set(PATH, path.pack().toString());
         }
         if(!filter.isEmpty()){
            attributes.set(FILTER, filter.pack().toString());
         }
      }
      return attributes;
   }

   /**
    * <T>获得不包括目录或文件名称列表</T>
    * 
    */
   protected FStrings getInclude(FXmlNode exportConfig){
      FStrings item = null;
      if(null != exportConfig){
         item = new FStrings();
         for(FXmlNode includes : exportConfig.nodes(INCLUDE)){
            for(FXmlNode include : includes.nodes()){
               String location = include.get(LOCATION);
               if(RString.isNotEmpty(location)){
                  location = RFile.makeFilename(location);
                  item.push(location);
               }
            }
         }
      }
      return item;
   }

   /**
    * <T>导入配置文件递归实现</T>
    * 
    */
   protected void loadConfig(FXmlNode config,
                             String fileName){
      if(RString.isNotEmpty(fileName)){
         // 加载文件
         String fullName = RFile.makeFilename(_configPath, fileName + ".xml");
         _logger.debug(this, "loadConfig", "Load config file (file={0})", fullName);
         FXmlDocument xdoc = new FXmlDocument(fullName);
         // 处理文件内所有节点
         for(FXmlNode node : xdoc.root()){
            if(node.isName("Include_confg")){
               loadConfig(config, node.text());
            }
            config.push(node);
         }
      }
   }

   /**
    * <T>导入配置文件</T>
    * 
    */
   protected FXmlNode loadConfig(String fileName){
      if(RString.isNotEmpty(fileName)){
         FXmlNode config = new FXmlNode("ExportConfig");
         loadConfig(config, fileName);
         return config;
      }
      return null;
   }

   /**
    * <T>配置文件目录不包括文件名称</T>
    * 
    */
   protected void setConfigPath(String path){
      _configPath = path;
   }

   /**
    * <T>设置.zip输出目录</T>
    * 
    */
   protected void setOutput(String path){
      if(RString.isNotEmpty(path)){
         _output = path;
      }
   }

   /**
    * <T>设置zip包名称</T>
    * 
    */
   protected void setZipName(String zipPath){
      if(RString.isNotEmpty(zipPath)){
         String newZipPath = RString.replace(zipPath, "\\", "/");
         String spilt[] = newZipPath.split("/");
         int len = spilt.length;
         if(len > 0){
            _zipName = spilt[len - 1];
         }
      }
   }
}
