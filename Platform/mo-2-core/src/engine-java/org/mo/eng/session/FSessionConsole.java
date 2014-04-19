package org.mo.eng.session;

import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.IMonitorConsole;
import org.mo.eng.session.common.FSession;
import org.mo.eng.session.common.FSessionWorker;
import org.mo.eng.session.common.FSessionWorkers;
import org.mo.eng.session.common.ISession;

//============================================================
// <T>应用程序逻辑会话控制台。</T>
// <P>
//    提供逻辑的管理和自动存储功能。<B/>
//    在内存存储时，在内存超时后未使用的线程信息会被存储到硬盘中。<B/>
//    再次使用时，会重新从磁盘上加载。<B/>
//    当线程未使用时间超长时会自动释放。
// </P>
//============================================================
public class FSessionConsole
      implements
         ISessionConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSessionConsole.class);

   // 线程类对象名称
   @AProperty
   protected String _sessionClassName;

   // 检查间隔
   @AProperty
   protected long _interval;

   // 内存超时长短
   @AProperty
   protected long _memoryTimeout;

   // 线程超时
   @AProperty
   protected long _timeout;

   // 线程工作文件
   @AProperty
   protected String _workfile;

   // 工作目录
   @AProperty
   protected String _directory;

   // 监视器控制台接口
   @ALink
   protected IMonitorConsole _monitorConsole;

   // 线程刷新监视器
   protected FSessionMonitor _monitor;

   // 线程工作对象集合
   protected FSessionWorkers _workers = new FSessionWorkers();

   // 线程缓冲集合
   protected FSessionWorkers _buffers = new FSessionWorkers();

   // 要移除的线程集合
   protected FObjects<FSessionWorker> _removes = new FObjects<FSessionWorker>(FSessionWorker.class);

   //============================================================
   // <T>根据代码获得一个会话文件名称。</T>
   //
   // @param code 代码
   // @return 文件名称
   //============================================================
   protected String makeFilename(String code){
      return RFile.makeFilename(_directory, code) + ".ser";
   }

   //============================================================
   // <T>创建会话对象。</T>
   //
   // @param worker 会话工作器
   // @return 会话对象
   //============================================================
   protected FSession createSession(FSessionWorker worker){
      FSession session = RClass.newInstance(_sessionClassName);
      session.setId(worker.id());
      session.setStoreId(worker.storeId());
      return session;
   }

   //============================================================
   // <T>根据代码查找会话对象。</T>
   //
   // @param code 代码
   // @return 会话对象
   //============================================================
   @Override
   public <V extends ISession> V find(String code){
      // 查找会话对象
      FSessionWorker worker = null;
      synchronized(_workers){
         worker = _workers.find(code);
      }
      // 重新加载工作器
      if(null != worker){
         if(null == worker.session()){
            restore(worker);
         }
         worker.refresh();
         return worker.session();
      }
      return null;
   }

   //============================================================
   // <T>根据代码获得会话对象。</T>
   //
   // @param code 代码
   // @return 会话对象
   //============================================================
   @Override
   public <V extends ISession> V get(String code){
      V session = find(code);
      if(null == session){
         throw new FFatalError("Session is not exists.");
      }
      return session;
   }

   //============================================================
   // <T>根据代码同步会话对象。</T>
   //
   // @param code 代码
   // @return 会话对象
   //============================================================
   @Override
   public <V extends ISession> V sync(Class<V> clazz,
                                      String code){
      // 生成线程的名称
      String sessionId = code.toUpperCase() + "@" + RClass.shortName(clazz);
      FSessionWorker worker = null;
      synchronized(_workers){
         worker = _workers.find(sessionId);
      }
      // 当工作线程不存在时创建新的线程
      if(worker == null){
         String storeId = _workers.nextStoreId();
         // 创建线程工作对象
         worker = new FSessionWorker();
         worker.setId(sessionId);
         worker.setStoreId(storeId);
         worker.setTimeout(_timeout);
         // 创建线程对象
         FSession session = createSession(worker);
         worker.setSession(session);
         // 存储线程工作对象
         synchronized(_workers){
            _workers.set(sessionId, worker);
         }
      }else{
         // 如果工作线程存在，线程数据为空时，从文件缓冲中恢复线程数据
         if(worker.session() == null){
            restore(worker);
         }
      }
      // 更新线程对象的状态
      worker.refresh();
      // 获得线程信息
      return worker.session();
   }

   //============================================================
   // <T>将线程信息序列化存储到指定文件中。</T>
   //
   // @param worker 会话工作器
   //============================================================
   protected void store(FSessionWorker worker){
      // 只有激活状态下才允许被存储
      if(worker.isActive()){
         // 生成文件名称
         String filename = makeFilename(worker.storeId());
         if(_logger.debugAble()){
            _logger.debug(this, "store", "Store session {1} to {2}", worker, filename);
         }
         // 尝试序列化对象到文件中
         try{
            //FObjectFile file = new FObjectFile(filename);
            //            FXmlObjectFile file = new FXmlObjectFile(filename);
            //            file.writeObject(worker.session());
            //            //RXmlPersistent.saveFile(worker.session(), filename);
            // 释放线程信息对象
            worker.free();
         }catch(Throwable t){
            // 如果序列化失败，则删除错误的序列化文件
            RFile.delete(filename);
            // 输出失败原因
            _logger.error(this, "store", t);
         }
      }
   }

   //============================================================
   // <T>从指定的文件中读取已经序列化的线程信息。</T>
   //
   // @param worker 会话工作器
   //============================================================
   protected void restore(FSessionWorker worker){
      // 生成文件名称
      String fileName = makeFilename(worker.storeId());
      if(RFile.exists(fileName)){
         // 文件存在时，允许加载序列化对象
         try{
            //FObjectFile file = new FObjectFile(filename);
            //            FXmlObjectFile file = new FXmlObjectFile(filename);
            //            ISession session = file.readObject();
            //ISession session = (ISession) RXmlPersistent.loadFile(filename);
            //            worker.setSession(session);
            if(_logger.debugAble()){
               _logger.debug(this, "restore", "Restore session {1} from {2}", worker, fileName);
            }
         }catch(Throwable t){
            _logger.error(this, "restore", t);
         }
      }else{
         // 文件不存在时，输出警告信息
         _logger.warn(this, "restore", "Session file lose. (file_name={1})", fileName);
      }
      // 如果反序列化失败时，重新创建一个空的线程信息对象
      if(null == worker.session()){
         worker.setSession(createSession(worker));
      }
   }

   //============================================================
   // <T>根据代码移除会话对象。</T>
   //
   // @param code 代码
   //============================================================
   @Override
   public void remove(String code){
      // 从工作表中删除指定标识的线程工作对象
      FSessionWorker worker;
      synchronized(_workers){
         worker = _workers.remove(code);
      }
      // 释放线程工作对象
      if(null != worker){
         release(worker);
      }
   }

   //============================================================
   // <T>释放会话工作器。</T>
   //
   // @param worker 会话工作器
   //============================================================
   protected void release(FSessionWorker worker){
      // 是否含有当前标识
      boolean removed = _workers.contains(worker.id());
      // 移除当前线程对象
      if(removed){
         synchronized(_workers){
            _workers.remove(worker.id());
         }
      }
      // 删除关联文件
      String filename = makeFilename(worker.storeId());
      boolean removeFile = RFile.exists(filename);
      if(removeFile){
         RFile.delete(filename);
      }
      // 输出调试信息
      if(_logger.debugAble()){
         _logger.debug(this, "release", "Release session: {0} (id={1},file={2})", worker.dump(), removed, removeFile);
      }
   }

   //============================================================
   // <T>刷新线程信息,释放掉超时的线程对象。</T>
   //============================================================
   public void refresh(){
      FSessionWorker worker;
      _removes.clear();
      _buffers.clear();
      synchronized(_workers){
         int count = _workers.count();
         for(int n = 0; n < count; n++){
            worker = _workers.value(n);
            if(null != worker){
               if(worker.testTimeout(_interval)){
                  // 找出超时的线程信息，移除超时线程
                  _removes.push(worker);
                  //}else if(worker.isActive() && worker.testTimeout(_memoryTimeout)){
                  // 找出内存超时的线程信息
                  //   _buffers.set(_workers.name(n), worker);
               }
            }
         }
      }
      // 释放的超时的线程
      if(!_removes.isEmpty()){
         int count = _removes.count();
         for(int n = 0; n < count; n++){
            release(_removes.get(n));
         }
      }
      // 刷新内存，将内存超时的线程信息写入文件缓冲
      //      if(!_buffers.isEmpty()){
      //         int count = _buffers.count();
      //         for(int n = 0; n < count; n++){
      //            FSessionWorker work = _buffers.value(n);
      //            if(work.isActive() && !work.session().referTest()){
      //               store(work);
      //            }
      //         }
      //         _buffers.clear();
      //      }
   }

   //============================================================
   // <T>初始化控制台的监视器操作。</T>
   //============================================================
   public void initializeMonitor(){
      _monitor = new FSessionMonitor(this);
      _monitor.setInterval(_interval);
      _monitorConsole.register(_monitor);
   }

   //============================================================
   // <T>初始化控制台操作。</T>
   // <P>读取缓冲的设置信息，将缓冲的数据文件录入到内存缓冲中。</P>
   //============================================================
   public void initializeResume(){
      if(RFile.exists(_workfile)){
         // 继续前次暂停的服务，恢复所有可以缓冲的信息
         try{
            // 读取序列化文件
            //FObjectFile file = new FObjectFile(_workfile);
            //            FXmlObjectFile file = new FXmlObjectFile(_workfile);
            //            FSessionWorkers workers = file.readObject();
            //FSessionWorkers workers = (FSessionWorkers) RXmlPersistent.loadFile(_workfile);
            //            if(null == workers){
            //               _logger.error(this, "initializeResume", "Sessions is null.");
            //            }else{
            //               _logger.debug(this, "initializeResume", "Uerialize all session.");
            //               _workers = workers;
            //            }
         }catch(Throwable t){
            // 如果读取失败，则删除序列化文件
            _logger.error(this, "initializeResume", t, "Resume session failed.");
            RFile.delete(_workfile);
         }
      }
   }

   //============================================================
   // <T>关闭线程控制台前的处理。</T>
   // <P>存储当前缓冲中所有信息对象。</P>
   //============================================================
   public void releaseInterrupt(){
      long start = System.currentTimeMillis();
      if(_logger.debugAble()){
         _logger.debug(this, "releaseInterrupt", "Begin serialize all session.");
      }
      // 保存所有线程信息
      synchronized(_workers){
         //_workers.compress();
         int count = _workers.count();
         for(int n = 0; n < count; n++){
            store(_workers.value(n));
         }
      }
      // 保存线程列表
      try{
         //FObjectFile file = new FObjectFile(_workfile);
         //         FXmlObjectFile file = new FXmlObjectFile(_workfile);
         //         file.writeObject(_workers);
         //RXmlPersistent.saveFile(_workers, _workfile);
      }catch(Exception e){
         // 失败时尝试删除工作文件
         RFile.delete(_workfile);
         _logger.error(this, "releaseInterrupt", e);
      }
      if(_logger.debugAble()){
         long execute = System.currentTimeMillis() - start;
         _logger.debug(this, "releaseInterrupt", execute, "Serialize all session.");
      }
   }

}
