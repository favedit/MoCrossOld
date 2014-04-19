package org.mo.logic.batch.process.version;

import java.io.File;
import org.mo.com.io.FByteFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FStringSet;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RByte;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.process.FBatchSqlCommand;
import org.mo.mime.zip.IZipEntry;
import org.mo.mime.zip.IZipInput;
import org.mo.mime.zip.RZip;

public class FVersionImport
      extends FBatchSqlCommand
      implements
         IVersionImport
{
   // 文件路径（属性名称）
   private final static String ZIP_PATH = "zip_path";

   // 配置文件路径（属性名称）
   private final static String CONFIG_PATH = "config_path";

   // 配置文件名称（属性名称）
   @SuppressWarnings("unused")
   private final static String CONFIG_NAME = "config_name";

   // zip包保存路径（属性名称）
   private final static String OUTPUT = "output";

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

   // 日志输出对象
   private final static ILogger _logger = RLogger.find(FVersionImport.class);

   private final byte[] _buffer = new byte[RInteger.SIZE_64K];

   // 排除路径集合 
   @SuppressWarnings("unused")
   private final FStringSet _excludes = new FStringSet();

   // 读取文件集合 
   private final FStringSet _files = new FStringSet();

   // 文件路径
   @SuppressWarnings("unused")
   private String _path;

   // 导出目录名称（属性名称）
   private String _outputPath = "D:/euis-test";

   // 导出时的配置文件目录有默认值
   private String _configPath = "D:/Workspace/eUIS/config/batch";

   // 处理时间
   @SuppressWarnings("unused")
   private long _processTime;

   /**
    * <T>判断是否可以同步</T>
    * 
    */
   protected boolean canImprot(IAttributes excludes,
                               File file){
      if(null != excludes && !excludes.isEmpty()){
         String filePath = file.getAbsolutePath();
         filePath = RString.replace(filePath, "\\", "/");
         FStrings excludePaths = new FStrings();
         excludePaths.unpack(excludes.get(PATH));
         if(!excludePaths.isEmpty()){
            for(String excludePath : excludePaths){
               if(filePath.startsWith(excludePath)){
                  return false;
               }
            }
         }
         FStrings excludeFilters = new FStrings();
         excludeFilters.unpack(excludes.get(FILTER));
         if(!excludeFilters.isEmpty()){
            for(String excludeFilter : excludeFilters){
               if(filePath.equals(excludeFilter)){
                  return false;
               }
            }
         }
      }
      return true;
   }

   /**
    * <T>清除目录</T>
    * 
    */
   public boolean clearDirectory(File root){
      int count = 0;
      for(File file : root.listFiles()){
         if(file.isDirectory()){
            if(clearDirectory(file)){
               continue;
            }
         }
         count++;
      }
      if(0 == count){
         String path = root.getAbsolutePath().substring(_outputPath.length());
         // 删除文件
         _logger.debug(null, "clearDirectory", "Clear directory: {0}", path);
         root.delete();
         return true;
      }
      return false;
   }

   /**
    * <T>清除文件</T>
    * 
    */
   public void clearFile(File root){
      for(File file : root.listFiles()){
         if(file.isDirectory()){
            clearFile(file);
         }else{
            // 查询是否含有文件
            String fileName = file.getAbsolutePath();
            fileName = RFile.makePathname(fileName);
            fileName = RString.replace(fileName, "\\", "/");
            if(_files.contains(fileName)){
               continue;
            }
            // 排除指定路径
            String path = fileName.substring(_outputPath.length());
            boolean excludeFlag = false;
            //            for(String exclude : _excludes){
            //               if(path.startsWith(exclude)){
            //                  excludeFlag = true;
            //                  break;
            //               }
            //            }
            if(excludeFlag){
               continue;
            }
            // 删除文件
            _logger.debug(null, "clearFile", "Clear file: {0}", path);
            file.delete();
         }
      }
   }

   /**
    * <T>获得包括目录或文件名称列表</T>
    * 
    */
   protected IAttributes getExclude(FXmlNode importConfig){
      IAttributes attributes = null;
      if(null != importConfig){
         attributes = new FAttributes();
         FStrings path = new FStrings();
         FStrings filter = new FStrings();
         FXmlNode configNode = new FXmlNode("import_config");
         loadConfig(configNode, importConfig.get("config_name"));
         if(configNode.hasNode(EXCLUDE)){
            for(FXmlNode excludes : configNode.nodes(EXCLUDE)){
               for(FXmlNode exclude : excludes.nodes()){
                  if(exclude.isName(PATH)){
                     String excluedPath = exclude.get(LOCATION);
                     excluedPath = _outputPath + excluedPath;
                     excluedPath = RString.replace(excluedPath, "\\", "/");
                     path.push(excluedPath);
                  }else if(exclude.isName(FILTER)){
                     String excluedFilter = exclude.get(LOCATION);
                     excluedFilter = RString.replace(excluedFilter, "\\", "/");
                     filter.push(_outputPath + excluedFilter);
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
      }
      return attributes;
   }

   /**
    * <T>获得不包括目录或文件名称列表</T>
    * 
    */
   protected FStrings getInclude(FXmlNode importConfig){
      FStrings item = null;
      if(null != importConfig){
         item = new FStrings();
         for(FXmlNode includes : importConfig.nodes(INCLUDE)){
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
    * <T>获得指定目录下的zip文件目录</T>
    * <P>如果没有指定zip文件，则查找最后版本的zip文件目录</p>
    * 
    */
   protected String getZipPath(String zipPath){
      File file = new File(zipPath);
      // 判断指定的目录是否为zip文件包
      if(file.isFile()){
         return zipPath;
      }
      // 查找最后一个导入文件
      String newZipPath = "";
      for(File zipFile : file.listFiles()){
         String path = zipFile.getAbsolutePath();
         if(path.endsWith(".zip")){
            if(path.compareTo(newZipPath) > 0){
               newZipPath = path;
            }
         }
      }
      return newZipPath;
   }

   /**
    * <T>读取文件并导出到指定文件</T>
    * 
    */
   @SuppressWarnings("resource")
   protected void importItem(FXmlNode importItem){
      //设置导出路径
      setOutput(importItem.get(OUTPUT));
      if(RString.isNotEmpty(_outputPath)){
         String zipPath = importItem.get(ZIP_PATH);
         // 获得导入的版本的目录名称
         zipPath = getZipPath(zipPath);
         if(RString.isNotEmpty(zipPath)){
            // 导入配置文件
            IAttributes excludes = getExclude(importItem);
            // 读取ZIP文件
            IZipInput zipInput = RZip.openFile(zipPath);
            IZipEntry entry = null;
            while(null != (entry = zipInput.nextEntry())){
               // 读取文件
               String outputFile = _outputPath + "/" + entry.name();
               outputFile = RFile.makeFilename(outputFile);
               outputFile = RString.replace(outputFile, "\\", "/");
               // 保存文件
               FByteFile file = new FByteFile(outputFile, false);
               while(true){
                  int read = zipInput.read(_buffer);
                  if(-1 == read){
                     break;
                  }
                  file.append(_buffer, 0, read);
               }
               if(canImprot(excludes, new File(outputFile))){
                  // 记录读取过的文档
                  _files.push(outputFile);
                  // 比较存在的文件
                  File existsFile = new File(outputFile);
                  if(existsFile.isFile()){
                     FByteFile testFile = new FByteFile(outputFile);
                     if(0 == RByte.compare(file.toArray(), testFile.toArray())){
                        // 如果文件一样，则不操作
                        continue;
                     }
                  }
                  file.store();
                  System.out.println("==========================================");
                  System.out.println("Update file [" + outputFile + "]");
                  System.out.println("==========================================");
                  _logger.debug(null, "process", "Import file: {0}", outputFile);
               }
            }
            zipInput.close();
            // 删除多余的文件
            //clearFile(new File(_outputPath));
            // 删除多余的空目录
            //clearDirectory(new File(_outputPath));
         }
      }
   }

   @Override
   public void importVersion(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      // 设置导出用的配置文件总路径不包括文件名称
      setConfigPath(config.get(CONFIG_PATH));
      if(null != config && config.hasNode("Item")){
         _processTime = System.currentTimeMillis();
         for(FXmlNode item : config.nodes("Item")){
            importItem(item);
         }
      }
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
      if(RString.isNotEmpty(path)){
         _configPath = path;
      }
   }

   /**
    * <T>设置导出目录</T>
    * 
    */
   protected void setOutput(String path){
      if(RString.isNotEmpty(path)){
         _outputPath = path;
      }
   }
}
