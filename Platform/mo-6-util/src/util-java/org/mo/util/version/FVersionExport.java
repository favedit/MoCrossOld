package org.mo.util.version;

import java.io.File;
import org.mo.com.io.FByteFile;
import org.mo.com.lang.FStringSet;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.mime.zip.FZipEntry;
import org.mo.mime.zip.IZipOutput;
import org.mo.mime.zip.RZip;

public class FVersionExport
{

   // 日志输出对象
   private final static ILogger _logger = RLogger.find(FVersionExport.class);

   // 排除路径集合 
   private FStringSet _excludeFilters = new FStringSet();

   // 排除路径集合 
   private FStringSet _excludePaths = new FStringSet();

   protected boolean _exportAll = false;

   // 导出路径
   private String _exportPath;

   // 包含路径集合 
   private FStringSet _includes = new FStringSet();

   // 输出路径
   private String _outputFile;

   private long _processTime;

   public void exportFile(IZipOutput zipOutput,
                          File root){
      for(File file : root.listFiles()){
         String name = file.getName();
         if(file.isDirectory()){
            if(!".svn".equals(name)){
               exportFile(zipOutput, file);
            }
         }else{
            // 排除指定过滤器
            if(!_exportAll && _excludeFilters.contains(name.toLowerCase())){
               continue;
            }
            // 排除指定路径
            String path = file.getAbsolutePath().substring(_exportPath.length());
            path = RString.replace(path, "\\", "/");
            boolean excludeFlag = false;
            //            for(String exclude : _excludePaths){
            //               if(path.startsWith(exclude)){
            //                  excludeFlag = true;
            //                  break;
            //               }
            //            }
            if(!_exportAll && excludeFlag){
               continue;
            }
            // 读取文件
            FByteFile bytes = new FByteFile(file.getAbsolutePath());
            // 导出文件
            _logger.debug(this, "exportFile", "Export file: {0}", path);
            FZipEntry entry = zipOutput.openEntry(path.substring(1));
            entry.setSize(bytes.length());
            entry.setTime(_processTime);
            entry.setComment(Integer.toString(bytes.length()));
            zipOutput.write(bytes.toArray());
            zipOutput.flush();
         }
      }
   }

   public void loadConfig(FXmlNode config){
      // 读取设置路径
      _exportPath = config.get("path");
      _logger.debug(this, "loadConfig", "Export root path: {0}", _exportPath);
      // 读取输出路径
      String outputFile = config.get("output");
      if(_exportAll){
         outputFile = config.get("output_all");
      }
      String subDate = RString.mid(outputFile, "{", "}");
      _outputFile = RString.replace(outputFile, "{" + subDate + "}", RDateTime.format(subDate));
      _logger.debug(this, "loadConfig", "Export output path: {0}", _outputFile);
      // 读取包含路径集合
      FXmlNode includeNode = config.findNode("Include");
      if(null != includeNode && includeNode.hasNode()){
         for(FXmlNode node : includeNode){
            if(node.isName("Path")){
               _includes.push(node.get("location"));
            }
         }
      }
      // 读取排除路径集合
      FXmlNode excludeNode = config.findNode("Exclude");
      if(null != excludeNode && excludeNode.hasNode()){
         for(FXmlNode node : excludeNode){
            if(node.isName("Path")){
               _excludePaths.push(node.get("location"));
            }else if(node.isName("Filter")){
               _excludeFilters.push(node.get("name").toLowerCase());
            }
         }
      }
   }

   public void process(){
      _processTime = System.currentTimeMillis();
      IZipOutput zipOutput = RZip.writeFile(_outputFile);
      if(_exportAll){
         exportFile(zipOutput, new File(_exportPath));
      }else{
         //         for(String include : _includes){
         //            exportFile(zipOutput, new File(_exportPath + include));
         //         }
      }
      zipOutput.close();
   }

}
