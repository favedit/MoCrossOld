package org.mo.core.monitor.common;

import java.io.File;
import org.mo.com.io.RFile;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>文件列表监视器基类。</T>
// <P>1. 监视指定文件列表变动，进行相关处理。</P>
// <P>2. 只监视变动，不监视新增和删除文件的操作。</P>
//============================================================
public abstract class FFileListMonitor
      extends FAbstractMonitor
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FFileListMonitor.class);

   // 文件列表
   protected FFileInfos _files = new FFileInfos();

   // 存储间隔
   protected int _storeInterval;

   //============================================================
   // <T>构造文件列表监视器基类。</T>
   //============================================================
   public FFileListMonitor(){
   }

   //============================================================
   // <T>获得存储间隔。</T>
   //
   // @return 存储间隔
   //============================================================
   public int storeInterval(){
      return _storeInterval;
   }

   //============================================================
   // <T>设置存储间隔。</T>
   //
   // @param storeInterval 存储间隔
   //============================================================
   public void setStoreInterval(int storeInterval){
      _storeInterval = storeInterval;
   }

   //============================================================
   // <T>获得文件信息集合。</T>
   //
   // @return 文件信息集合
   //============================================================
   public FFileInfos files(){
      return _files;
   }

   //============================================================
   // <T>文档过滤器。</T>
   // <P>监视器只关心返回TRUE的文件。</P>
   //
   // @param info 文件信息
   // @return TRUE：成功<br>FALSE：失败
   //============================================================
   public boolean onFilter(FFileInfo info){
      return (null != info);
   }

   //============================================================
   // <T>响应初始化文档。</T>
   //
   // @param filename 文件名称
   //============================================================
   public void doInitializeFile(FFileInfo info){
      if(_logger.debugAble()){
         _logger.debug(this, "doInitializeFile", "File initialize (file={1})", info.fileName());
      }
   }

   //============================================================
   // <T>响应发生变动的文档。</T>
   //
   // @param info 文件信息
   //============================================================
   public void doFileChange(FFileInfo info){
      if(_logger.debugAble()){
         _logger.debug(this, "doFileChange", "File change (file={1})", info.fileName());
      }
   }

   //============================================================
   // <T>响应被保存的文档。</T>
   //
   // @param info 文件信息
   //============================================================
   public void doFileSave(FFileInfo info){
      if(_logger.debugAble()){
         _logger.debug(this, "doFileSave", "File save (file={1})", info.fileName());
      }
   }

   //============================================================
   // <T>响应被移除的文档。</T>
   //
   // @param info 文件信息
   //============================================================
   public void doFileRemove(FFileInfo info){
      if(_logger.debugAble()){
         _logger.debug(this, "doFileRemove", "File remove (file={1})", info.fileName());
      }
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean onExecute(){
      // 检查文件集合
      if(_files.isEmpty()){
         return false;
      }
      // 检查变更的文件
      FFileInfos removes = null;
      int count = _files.count();
      for(int n = 0; n < count; n++){
         FFileInfo info = _files.value(n);
         if(onFilter(info)){
            if(info.exists()){
               if(info.storeTest(_storeInterval)){
                  // 响应存储的文件
                  try{
                     doFileSave(info);
                  }catch(Exception e){
                     _logger.error(e, "File save event error (file={1})", info.fileName());
                  }
                  info.stored();
               }else if(info.isModified()){
                  // 响应变更的文件
                  try{
                     doFileChange(info);
                  }catch(Exception e){
                     _logger.error(e, "File change event error (file={1})", info.fileName());
                  }
                  info.refresh();
               }
            }else{
               // 响应被删除的文件
               try{
                  doFileRemove(info);
               }catch(Exception e){
                  _logger.error(e, "File remove event error (file={1})", info.fileName());
               }
               if(null == removes){
                  removes = new FFileInfos();
               }
               removes.push(info);
            }
         }
      }
      // 移除文件集合
      if(null != removes){
         for(FFileInfo info : removes.values()){
            _files.remove(info.fileName());
         }
      }
      return true;
   }

   //============================================================
   // <T>增加文件信息。</T>
   //
   // @param info 文件信息
   //============================================================
   public void push(FFileInfo info){
      if(null != info){
         _files.push(info);
      }
   }

   //============================================================
   // <T>向监视器中增加一个监视文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void push(String filename){
      File file = new File(filename);
      if(file.isFile()){
         _files.push(new FFileInfo(file));
      }else{
         _logger.warn(this, "push", "File is not exists (file={1})", filename);
      }
   }

   //============================================================
   // <T>存储文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public synchronized void store(String fileName){
      if(null != fileName){
         int count = _files.count();
         for(int n = 0; n < count; n++){
            if(fileName.equals(_files.value(n).fileName())){
               _files.value(n).store();
               return;
            }
         }
         if(!RFile.isFile(fileName)){
            RFile.saveToFile(fileName, RString.EMPTY);
         }
         FFileInfo info = new FFileInfo(fileName);
         _files.push(info);
         info.store();
      }
   }

   //============================================================
   // <T>已经存储文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public synchronized void stored(String fileName){
      if(null != fileName){
         int count = _files.count();
         for(int n = 0; n < count; n++){
            if(fileName.equals(_files.value(n).fileName())){
               _files.value(n).stored();
               break;
            }
         }
      }
   }

   //============================================================
   // <T>存储所有变更的文件。</T>
   //============================================================
   public synchronized void storeAll(){
      // 检查变更的文件
      if(!_files.isEmpty()){
         int count = _files.count();
         for(int n = 0; n < count; n++){
            FFileInfo info = _files.value(n);
            if(onFilter(info)){
               if(info.exists() && info.storeTest(0)){
                  // 响应存储的文件
                  try{
                     doFileSave(info);
                  }catch(Exception e){
                     _logger.error(e, "File save event error (file={1})", info.fileName());
                  }
                  info.stored();
               }
            }
         }
      }
   }

   //============================================================
   // <T>从监视器中移除一个监视文件。</T>
   //
   // @param info 文件信息 
   //============================================================
   public boolean remove(FFileInfo info){
      if(null != info){
         _files.remove(info.fileName());
         return true;
      }
      return false;
   }

   //============================================================
   // <T>从监视器中移除一个监视文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public boolean remove(String fileName){
      FFileInfo info = _files.get(fileName);
      if(null != info){
         _files.remove(fileName);
         return true;
      }
      return false;
   }

   //============================================================
   // <T>清除监视的文件列表。</T>
   //============================================================
   public synchronized void clear(){
      _files.clear();
   }
}
