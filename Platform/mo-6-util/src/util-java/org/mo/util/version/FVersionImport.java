package org.mo.util.version;

import java.io.File;
import org.mo.com.io.FByteFile;
import org.mo.com.lang.FStringSet;
import org.mo.com.lang.RByte;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.mime.zip.IZipEntry;
import org.mo.mime.zip.IZipInput;
import org.mo.mime.zip.RZip;

public class FVersionImport
{

   // 日志输出对象
   private final static ILogger _logger = RLogger.find(FVersionImport.class);

   private byte[] _buffer = new byte[RInteger.SIZE_64K];

   // 排除路径集合 
   private FStringSet _excludes = new FStringSet();

   // 读取文件集合 
   private FStringSet _files = new FStringSet();

   // 导出路径
   private String _outputPath;

   // 文件路径
   private String _path;

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

   public void clearFile(File root){
      for(File file : root.listFiles()){
         if(file.isDirectory()){
            clearFile(file);
         }else{
            // 查询是否含有文件
            String fileName = file.getAbsolutePath();
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

   public void loadConfig(FXmlNode config){
      // 读取文件路径
      _path = config.get("path");
      _logger.debug(null, "loadConfig", "Import path: {0}", _outputPath);
      // 读取设置路径
      _outputPath = config.get("output");
      _logger.debug(null, "loadConfig", "Output path: {0}", _outputPath);
      // 读取排除路径集合
      FXmlNode excludeNode = config.findNode("Exclude");
      if(null != excludeNode && excludeNode.hasNode()){
         for(FXmlNode node : excludeNode){
            if(node.isName("Path")){
               _excludes.push(node.get("location"));
            }
         }
      }
   }

   public void process(){
      // 查找最后一个导入文件
      String zipPath = "";
      for(File zipFile : new File(_path).listFiles()){
         String path = zipFile.getAbsolutePath();
         if(path.compareTo(zipPath) > 0){
            zipPath = path;
         }
      }
      if(RString.isEmpty(zipPath)){
         _logger.debug(null, "process", "Import file is not founded.");
         return;
      }
      _logger.debug(null, "process", "Import file {0}.", zipPath);
      // 读取ZIP文件
      IZipInput zipInput = RZip.openFile(zipPath);
      IZipEntry entry = null;
      while(null != (entry = zipInput.nextEntry())){
         // 读取文件
         String outputFile = _outputPath + "/" + entry.name();
         outputFile = RString.replace(outputFile, "\\", "/");
         // 记录读取过的文档
         _files.push(outputFile);
         // 保存文件
         FByteFile file = new FByteFile(outputFile, false);
         while(true){
            int read = zipInput.read(_buffer);
            if(-1 == read){
               break;
            }
            file.append(_buffer, 0, read);
         }
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
         _logger.debug(null, "process", "Import file: {0}", outputFile);
      }
      zipInput.close();
      // 删除多余的文件
      clearFile(new File(_outputPath));
      // 删除多余的空目录
      clearDirectory(new File(_outputPath));
   }
}
