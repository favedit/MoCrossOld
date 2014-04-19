package org.mo.logic.recycle;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.logging.ILoggerConsole;
import org.mo.core.monitor.IMonitorConsole;

public class FLoggerRecycleConsole
      implements
         ILoggerRecycleConsole
{
   @SuppressWarnings("unused")
   private final static ILogger _logger = RLogger.find(FLoggerRecycleConsole.class);

   @AProperty
   protected long _interval;

   @ALink
   protected ILoggerConsole _loggerConsole;

   protected FLoggerRecycleMonitor _monitor;

   @ALink
   protected IMonitorConsole _monitorConsole;

   @Override
   public void initialize(){
      _monitor = new FLoggerRecycleMonitor(this);
      _monitor.setInterval(_interval);
      _monitorConsole.register(_monitor);
   }

   public void refresh(){
      //      FDictionary<IZipOutput> zipOutputs = new FDictionary<IZipOutput>(IZipOutput.class);
      //      String nowPart = RDate.format("YYYY-MM-DD");
      //      String storePath = _loggerConsole.storePath();
      //      for(String fileName : RFile.listAllFile(storePath)){
      //         if(fileName.endsWith(".log")){
      //            String name = RFile.name(fileName);
      //            String datePart = name.substring(0, 10);
      //            if(!nowPart.equals(datePart)){
      //               // 输出压缩文件
      //               IZipOutput zipOutput = zipOutputs.get(datePart);
      //               if(null == zipOutput){
      //                  // 检查文件存在性
      //                  String zipName = storePath + datePart + "." + RZip.EXTENSION;
      //                  if(RFile.isFile(zipName)){
      //                     // modify user zengd 20100930
      //                     _logger.debug(this, "FLoggerRecycleConsole.refresh", "Has zip file(" + zipName + ")");
      //                     //throw new FFatalError("Has zip file. (filename={0})", zipName);
      //                  }
      //                  // 打开输出流
      //                  zipOutput = RZip.writeFile(zipName);
      //                  zipOutputs.set(datePart, zipOutput);
      //               }
      //               // 输出数据
      //               zipOutput.openEntry(name);
      //               // 读取所有文本行
      //               FFileReader reader = new FFileReader(fileName);
      //               try{
      //                  String line = null;
      //                  while(null != (line = reader.readLine())){
      //                     zipOutput.write(line.getBytes());
      //                  }
      //               }finally{
      //                  reader.close();
      //               }
      //               // 删除旧文件
      //               RFile.delete(fileName);
      //            }
      //         }
      //      }
      //      // 关闭所有输出压缩流
      //      if(!zipOutputs.isEmpty()){
      //         for(IZipOutput zipOutput : zipOutputs.toObjects()){
      //            zipOutput.close();
      //         }
      //      }
   }
}
