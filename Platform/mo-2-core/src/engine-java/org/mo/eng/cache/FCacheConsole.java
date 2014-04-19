package org.mo.eng.cache;

import java.io.File;
import org.mo.com.lang.FError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.IMonitorConsole;
// List为一个缓冲堆栈。
// 索引越小的越旧，使用remove(0)移除，索引越大的越新，使用add增加。

public class FCacheConsole
      implements
         ICacheConsole
{
   private static ILogger _logger = RLogger.find(FCacheConsole.class);

   @AProperty
   private final int _diskFileCount = 0;

   @AProperty
   protected int _interval;

   @AProperty
   private final int _memoryFileCount = 0;

   private final FCacheMap _memoryMap = new FCacheMap();

   protected FCacheMonitor _monitor;

   @ALink
   protected IMonitorConsole _monitorConsole;

   private long _requireCount = 0;

   private long _validCount = 0;

   //   public T cache(Object oOwner,
   //                  String sCacheId){
   //      String sCacheName = makeCacheName(oOwner, sCacheId);
   //      _requireCount++;
   //      synchronized(this){
   //         FCache oCache = null;
   //         // 从内存缓冲中获得内容，从内存缓冲列表中移除
   //         if(_memoryMap.contains(sCacheName)){
   //            oCache = _memoryMap.get(sCacheName);
   //            _memoryList.remove(_memoryMap.remove(sCacheName).name());
   //         }
   //         // 从文件缓冲中获得内容，从文件缓冲列表中移除
   //         if(_cacheList.contains(sCacheName)){
   //            //oCache = readCache(sCacheName);
   //            oCache = _cacheList.get(sCacheName);
   //            _cacheList.remove(sCacheName);
   //         }
   //         if(oCache != null && !oCache.testOvertime()){
   //            // 将新的放入内存缓冲池
   //            _memoryMap.set(sCacheName, oCache);
   //            _memoryList.push(oCache);
   //            _cacheList.push(oCache);
   //            // 如果内存缓冲池已满，则从内存缓冲池中移除最旧一个，放到文件缓冲池中
   //            if(_memoryList.count() > _memoryFileCount){
   //               _memoryMap.remove(_memoryList.remove(0).name());
   //            }
   //            _validCount++;
   //            //return (T) oCache;
   //            return null;
   //         }
   //         return null;
   //      }
   //   }
   @AProperty
   private String _workFile;

   @AProperty
   private String _workPath;

   public void deleteCache(String sCacheName){
      //      String sFileName = m_sWorkPath + sCacheName;
      //      File oFile = new File(sFileName);
      //      if (oFile.isFile()) {
      //         oFile.delete();
      //      }
   }

   @Override
   public ICache find(Class<?> clazz,
                      String name){
      _requireCount++;
      String cacheName = name + "@" + clazz.getName();
      long now = System.currentTimeMillis();
      ICache cache = null;
      synchronized(_memoryMap){
         cache = _memoryMap.find(cacheName);
         if(null != cache){
            if(cache.testTimeout(now)){
               cache = null;
               _memoryMap.remove(cacheName);
            }else{
               _validCount++;
               cache.refresh();
            }
         }
      }
      if(_logger.debugAble()){
         _logger.debug(this, "find", "Find cache (class={1}, name={2}, cache={3})", clazz.getName(), name, cache);
      }
      return cache;
   }

   /**
    * <p>初始化前操作</p>
    * <p>设置初始化的参数</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public void initializeConfig(){
      new File(_workPath).mkdirs();
   }

   /**
    * <p>初始化后操作</p>
    * <p>建立数据容器监视器</p>
    * <p>将数据容器监视器配置到监视器控制台中</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public void initializeMonitor(){
      _monitor = new FCacheMonitor(this);
      _monitor.setInterval(_interval);
      _monitorConsole.register(_monitor);
      // 继续前次暂停的服务，恢复所有可以缓冲的信息
      //      if(RFile.exists(_workFile)){
      //         try{
      //            m_oFileList = (List)FFileUtil.readObject(m_sWorkFile);
      //            if(_logger.debugAble()){
      //               _logger.debug(this, "initializeMonitor", "Unserialize all cache info");
      //            }
      //         }catch(Exception e){
      //            _logger.error(this, "initializeMonitor", e);
      //            (new File(m_sWorkFile)).delete();
      //         }
      //      }
   }

   public void refresh(){
      long now = System.currentTimeMillis();
      // 删除过期的缓冲信息
      synchronized(_memoryMap){
         int count = _memoryMap.count();
         for(int n = 0; n < count; n++){
            ICache cache = _memoryMap.value(n);
            if(null != cache){
               if(now - cache.lastUsed() > cache.timeout()){
                  _memoryMap.erase(n);
               }
            }
         }
      }
   }

   //   protected String makeCacheName(Object oOwner,
   //                                  String sFileId){
   //      String sOwnerName = null;
   //      if(oOwner instanceof Class){
   //         sOwnerName = ((Class<?>) oOwner).getName();
   //      }else if(oOwner instanceof String){
   //         sOwnerName = (String) oOwner;
   //      }else{
   //         sOwnerName = oOwner.getClass().getName();
   //      }
   //      String sCacheName = sOwnerName + "_" + sFileId;
   //      sCacheName = sCacheName.replaceAll("\\.", "_");
   //      sCacheName = sCacheName.replaceAll("\\|", "_");
   //      return sCacheName = sCacheName.toLowerCase();
   //   }
   //   public void push(Object oOwner,
   //                    String sCacheId,
   //                    T oCache){
   //      String sCacheName = makeCacheName(oOwner, sCacheId);
   //      oCache.setName(sCacheName);
   //      synchronized(this){
   //         // 增加缓冲内容到文件缓冲中
   //         if(_cacheList.contains(sCacheName)){
   //            _cacheList.remove(sCacheName);
   //         }
   //         _cacheList.push(oCache);
   //         writeCache(oCache);
   //         // 如果文件缓冲池已满，则从文件缓冲池中移除最旧一个
   //         if(_cacheList.count() > _diskFileCount){
   //            deleteCache(_cacheList.remove(0).name());
   //         }
   //      }
   //   }
   //   public FCache readCache(String sCacheName){
   //      //String sFileName = m_sWorkPath + sCacheName;
   //      //return (FCache)FFile.readObject(sFileName);
   //      return null;
   //   }
   @Override
   public void register(Class<?> clazz,
                        String name,
                        ICache cache){
      String cacheName = name + "@" + clazz.getName();
      synchronized(_memoryMap){
         _memoryMap.set(cacheName, cache);
      }
   }

   /**
    * <p>释放前操作</p>
    * <p>关闭控制台，记录当前缓冲信息</p>
    * <p>create date:2005/02/18</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    */
   public void release(){
      if(_logger.debugAble()){
         _logger.debug(this, "release", "Serialize all cache info");
      }
      //FFileUtil.writeObject(m_sWorkFile, m_oFileList);
   }

   public long requireCount(){
      return _requireCount;
   }

   public long validCount(){
      return _validCount;
   }

   public float validPercent(){
      int nPercent = (int)(((float)_validCount / (float)_requireCount) * 10000);
      return (float)nPercent / 100;
   }

   public String workPath(){
      return _workPath;
   }

   public void writeCache(FCache oCache){
      //String sFileName = m_sWorkPath + oCache.getCacheName();
      //return FFile.writeObject(sFileName, oCache);
   }
}
