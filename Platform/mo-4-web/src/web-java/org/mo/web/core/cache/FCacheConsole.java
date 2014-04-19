package org.mo.web.core.cache;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>缓冲控制台。</T>
//============================================================
public class FCacheConsole
      implements
         ICacheConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FCacheConsole.class);

   // 工作路径
   @AProperty
   protected String _workPath;

   // 缓冲信息字典
   protected FDictionary<FCacheInfo> _caches = new FDictionary<FCacheInfo>(FCacheInfo.class);

   //============================================================
   // <T>根据网页地址获得缓冲字符串。</T>
   //
   // @param uri 网页地址
   // @return 缓冲字符串
   //============================================================
   @Override
   public FString find(String uri){
      FCacheInfo info = _caches.find(uri);
      if(info == null){
         String fileName = RFile.makeFilename(_workPath, uri);
         if(RFile.isFile(fileName)){
            _logger.debug(this, "find", "Load cache file. (file_name={1})", fileName);
            FStringFile file = new FStringFile(fileName, true);
            info = new FCacheInfo();
            info.setBuffer(file);
            _caches.set(uri, info);
         }else{
            _logger.warn(this, "find", "Can't find cache file. (file_name={1})", fileName);
         }
      }
      return info.buffer();
   }

}
