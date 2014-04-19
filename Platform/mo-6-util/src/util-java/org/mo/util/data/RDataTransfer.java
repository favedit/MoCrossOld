/*
 * @(#)RDataTransfer.java
 *
 * Copyright 2009 microbject, All Rights Reserved.
 *
 */
package org.mo.util.data;

import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.RAop;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.data.dataset.common.XDataStore;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;

/**
 * <T>数据导入导出工具。</T>
 * 
 * @history 090602 MAOCY 创建
 */
public class RDataTransfer
{

   private static IDatasetConsole _datasetConsole;

   // 日志输出对象
   private final static ILogger _logger = RLogger.find(RDataTransfer.class);

   /**
    * <T>版本导入导出工具。</T>
    * 
    * @param args 输入参数
    *    <L value='type'>export/import:导出/导入</L>
    *    <L value='config'>设置文件的全路径</L>
    */
   public static void main(String[] args){
      // 读取参数
      String sourceDb = "EUISDP";
      String targetDb = "EUISTS";
      if(1 == args.length){
         targetDb = args[0];
      }else if(2 == args.length){
         sourceDb = args[0];
         targetDb = args[1];
      }else if(0 != args.length){
         throw new FFatalError("Parameters is invalid.");
      }
      FDataSourceItems items = new FDataSourceItems();
      try{
         // 加载设置
         String path = "D:/Workspace/eUIS/src/eUIS-config";
         RAop.configConsole().loadFile(path + "/application-dp-bj-external.xml");
         // 收集数据库链接
         IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
         ISqlConnection cnnFrom = databaseConsole.alloc(sourceDb);
         _logger.debug(null, "main", "Source connection is {0}", sourceDb);
         if(null == cnnFrom){
            throw new FFatalError("Source connection is not found.");
         }
         ISqlConnection cnnTo = databaseConsole.alloc(targetDb);
         _logger.debug(null, "main", "Target connection is {0}", targetDb);
         if(null == cnnTo){
            throw new FFatalError("Target connection is not found.");
         }
         ISqlConnection cnnWrite = databaseConsole.alloc(targetDb);
         // 获得数据集管理器
         _datasetConsole = RAop.find(IDatasetConsole.class);
         IXmlObject[] xdatasets = _datasetConsole.list();
         for(IXmlObject xdataset : xdatasets){
            if(RBoolean.parse(xdataset.innerGet(XDataStore.PTY_IS_VALID))){
               String dataLogic = xdataset.innerGet(XDataStore.PTY_DATA_LOGIC);
               if(RString.isNotBlank(dataLogic)){
                  FDataSourceItem item = new FDataSourceItem(dataLogic);
                  items.set(dataLogic, item);
               }
            }
         }
         // 增加所有枚举包
         FSqlQuery enumPackages = new FSqlQuery(cnnFrom, RDataTransfer.class, "enum.packages");
         for(FRow row : enumPackages.fetchDataset()){
            String dataLogic = row.get("PACKAGE_NAME");
            FDataSourceItem item = new FDataSourceItem(dataLogic);
            items.set(dataLogic, item);
         }
         // 同步所有系统包
         FSqlQuery commonPackages = new FSqlQuery(cnnFrom, RDataTransfer.class, "common.packages");
         for(FRow row : commonPackages.fetchDataset()){
            String dataLogic = row.get("PACKAGE_NAME");
            FDataSourceItem item = new FDataSourceItem(dataLogic);
            items.set(dataLogic, item);
         }
         // 启动同步线程
         new FDataSourceReadThread(items, cnnFrom).start();
         new FDataTargetReadThread(items, cnnTo).start();
         new FDataTargetWriteThread(items, cnnWrite).start();
      }catch(Exception e){
         _logger.error(null, "main", e);
         RAop.release();
      }
   }

}
