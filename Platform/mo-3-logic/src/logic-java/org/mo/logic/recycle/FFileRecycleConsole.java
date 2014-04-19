package org.mo.logic.recycle;

import java.io.File;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.IMonitorConsole;

public class FFileRecycleConsole
      implements
         IFileRecycleConsole
{
   private final static ILogger _logger = RLogger.find(FFileRecycleConsole.class);

   protected FStrings _directories = new FStrings();

   @AProperty
   protected long _interval;

   protected FFileRecycleMonitor _monitor;

   @ALink
   protected IMonitorConsole _monitorConsole;

   @Override
   public void addDirectory(String directory){
      _directories.push(directory);
   }

   public void checkDirectory(File directory){
      long now = System.currentTimeMillis();
      // 处理所有文件
      for(File file : directory.listFiles()){
         if(file.isDirectory()){
            checkDirectory(file);
            // 删除空目录
            if(0 == directory.listFiles().length){
               directory.delete();
            }
         }
         // 删除大于一天的文件
         long modified = file.lastModified();
         if((now - modified) > RDateTime.TicksPerDay){
            if(_logger.debugAble()){
               _logger.debug(this, "checkDirectory", "Delete recycle file (date={0},filename={1})", RDateTime.format(modified), file.getAbsolutePath());
            }
            file.delete();
         }
      }
   }

   @Override
   public void initialize(){
      _monitor = new FFileRecycleMonitor(this);
      _monitor.setInterval(_interval);
      _monitorConsole.register(_monitor);
   }

   public void refresh(){
      for(String directory : _directories){
         if(!RString.isEmpty(directory)){
            File file = new File(directory);
            if(file.exists() && file.isDirectory()){
               _logger.debug(this, "refresh", "Check directory (directory={0})", directory);
               checkDirectory(new File(directory));
            }
         }
      }
   }
}
