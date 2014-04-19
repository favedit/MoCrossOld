package org.mo.eng.global;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.io.FObjectFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>全局管理器。</T>
//============================================================
public class FGlobalConsole
      implements
         IGlobalConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FGlobalConsole.class);

   // 工作路径
   @AProperty
   protected String _workpath;

   // 对象集合
   protected final FObjectDictionary _objects = new FObjectDictionary();

   //============================================================
   // <T>构造全局管理器。</T>
   //============================================================
   public FGlobalConsole(){
   }

   //============================================================
   // <T>根据名称查找一个全局对象。</T>
   //
   // @param name 名称
   // @return 全局对象
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public <V> V find(String name){
      return (V)_objects.find(name);
   }

   //============================================================
   // <T>根据名称查找一个全局对象。</T>
   //
   // @param clazz 类对象
   // @return 全局对象
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public <V> V find(Class<?> clazz){
      String name = clazz.getName();
      Object instance = _objects.find(name);
      if(instance == null){
         instance = RClass.newInstance(clazz);
         _objects.set(name, instance);
      }
      return (V)instance;
   }

   //============================================================
   // <T>根据名称查找一个全局对象。</T>
   //
   // @param name 名称
   // @param clazz 类对象
   // @return 全局对象
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public <V> V find(String name,
                     Class<?> clazz){
      Object instance = _objects.find(name);
      if(instance == null){
         instance = RClass.newInstance(clazz);
         _objects.set(name, instance);
      }
      return (V)instance;
   }

   //============================================================
   // <T>根据名称移除一个全局对象。</T>
   //
   // @param name 名称
   //============================================================
   @Override
   public void remove(String name){
      _objects.remove(name);
   }

   //============================================================
   // <T>根据名称移除一个全局对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   @Override
   public void remove(Class<?> clazz){
      _objects.remove(clazz.getName());
   }

   //============================================================
   // <T>清空所有内容。</T>
   //============================================================
   @Override
   public void clear(){
      _objects.clear();
   }

   //============================================================
   // <T>初始化继续内容。</T>
   //============================================================
   @SuppressWarnings("resource")
   public void initializeResume(){
      FStrings files = RFile.listFiles(_workpath);
      for(String fileName : files){
         try{
            String name = fileName.substring(_workpath.length());
            FObjectFile file = new FObjectFile(fileName);
            Object instance = file.readObject();
            _logger.debug(this, "initializeResume", "Restore {0}({1}) from {2}", name, instance, instance);
            _objects.set(name, instance);
         }catch(Throwable t){
            _logger.error(this, "initializeResume", t);
         }
      }
   }

   //============================================================
   // <T>释放中断内容。</T>
   //============================================================
   @SuppressWarnings("resource")
   public void releaseInterrupt(){
      int count = _objects.count();
      for(int n = 0; n < count; n++){
         String name = _objects.name(n);
         Object instance = _objects.value(n);
         String filename = RFile.makeFilename(_workpath, name);
         try{
            _logger.debug(this, "releaseInterrupt", "Store {0}({1}) to {2}", name, instance, instance);
            FObjectFile file = new FObjectFile(filename);
            file.writeObject(instance);
         }catch(Throwable t){
            _logger.error(this, "releaseInterrupt", t);
         }
      }
   }
}
