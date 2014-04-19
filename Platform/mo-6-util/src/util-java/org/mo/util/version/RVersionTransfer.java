/*
 * @(#)RVersionTransfer.java
 *
 * Copyright 2009 microbject, All Rights Reserved.
 *
 */
package org.mo.util.version;

import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;

/**
 * <T>文件导入导出工具。</T>
 * 
 * @history 090601 MAOCY 创建
 */
public class RVersionTransfer{

   // 日志输出对象
   private final static ILogger _logger = RLogger.find(RVersionTransfer.class);

   private final static IResource _resource = RResource.find(RVersionTransfer.class);

   /**
    * <T>版本导入导出工具。</T>
    * 
    * @param args 输入参数
    *    <L value='type'>export/import:导出/导入</L>
    *    <L value='config'>设置文件的全路径</L>
    */
   public static void main(String[] args){
      if(true){
         //args = new String[] { "import", "D:/eUIS-090601-152921.zip" };
      }
      // 读取参数
      if(0 == args.length){
         throw new FFatalError("Parameters error.");
      }
      String mode = args[0].toLowerCase();
      boolean all = false;
      if(2 == args.length){
         if("all".equalsIgnoreCase(args[1])){
            all = true;
         }
      }
      //
      _logger.debug(null, "main", "Process start. (mode={0} all={1})", mode, all);
      // 导入导出操作
      if("export".equals(mode)){
         FVersionExport versionExport = new FVersionExport();
         versionExport._exportAll = all;
         FXmlNode exportNode = _resource.config().findNode("Export");
         versionExport.loadConfig(exportNode);
         versionExport.process();
      }else if("import".equals(mode)){
         // 导入数据
         FVersionImport versionImport = new FVersionImport();
         FXmlNode importNode = _resource.config().findNode("Import");
         versionImport.loadConfig(importNode);
         versionImport.process();
      }
   }
}
