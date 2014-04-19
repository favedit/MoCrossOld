package org.mo.eng.store;

import org.mo.com.xml.RXml;
import org.mo.core.monitor.common.FAbsDirectoryMonitor;
import org.mo.core.monitor.common.FFileInfo;

//============================================================
// <T>配置文件监视器。</T>
//============================================================
public class FXmlConfigMonitor
      extends FAbsDirectoryMonitor
{
   // 配置文件控制台
   public FAbstractXmlConfigConsole _console;

   //============================================================
   // <T>构造配置文件监视器。</T>
   //
   // @param console 配置文件控制台
   // @param directory 路径
   //============================================================
   public FXmlConfigMonitor(FAbstractXmlConfigConsole console,
                            String directory){
      super(directory);
      _console = console;
   }

   //============================================================
   // <T>文档过滤器。</T>
   // <P>监视器只关心返回TRUE的文件。</P>
   //
   // @param info 文件信息
   // @return TRUE：成功<br>FALSE：失败
   //============================================================
   @Override
   public boolean onFilter(FFileInfo info){
      return info.fileName().endsWith(RXml.EXTENSION);
   }

   //============================================================
   // <T>响应发生变动的文档。</T>
   //
   // @param info 文件信息
   //============================================================
   @Override
   public void doFileChange(FFileInfo info){
      _console.freeConfig(info.fileName());
   }

   //============================================================
   // <T>响应被保存的文档。</T>
   //
   // @param info 文件信息
   //============================================================
   @Override
   public void doFileSave(FFileInfo info){
      _console.saveFile(info.fileName());
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //============================================================
   @Override
   public void execute(){
      super.execute();
      _console.refreshMonitor();
   }
}
