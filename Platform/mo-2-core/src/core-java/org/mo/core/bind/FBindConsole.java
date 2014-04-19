package org.mo.core.bind;

import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>绑定控制台。</T>
//============================================================
public class FBindConsole
      implements
         IBindConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FBindConsole.class);

   // 绑定信息集合
   private final FBindInfos _infos = new FBindInfos();

   //============================================================
   // <T>构造绑定控制台。</T>
   //============================================================
   public FBindConsole(){
   }

   //============================================================
   // <T>获得当前代码。</T>
   //
   // @return 代码
   //============================================================
   protected String currentCode(){
      return Long.toString(Thread.currentThread().getId());
   }

   //============================================================
   // <T>是否存在关联的绑定信息。</T>
   //
   // @return 是否存在
   //============================================================
   @Override
   public boolean exists(){
      return _infos.contains(currentCode());
   }

   //============================================================
   // <T>是否含有指定类关联的信息。</T>
   //
   // @param clazz 类对象
   // @return 是否含有
   //============================================================
   @Override
   public boolean contains(Class<?> clazz){
      FBindInfo info = _infos.get(currentCode());
      return (info != null) ? info.contains(clazz.getName()) : false;
   }

   //============================================================
   // <T>查找类对应的内容对象。</T>
   //
   // @param clazz 类对象
   // @return 内容对象
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public <V> V find(Class<V> clazz){
      if(_logger.debugAble()){
         _logger.debug(this, "find", "Find bind value (class={1})", clazz);
      }
      V value = null;
      String code = currentCode();
      FBindInfo info = _infos.get(code);
      if(null != info){
         value = (V)info.get(clazz.getName());
      }
      return value;
   }

   //============================================================
   // <T>获得类对应的内容对象。</T>
   //
   // @param clazz 类对象
   // @return 内容对象
   //============================================================
   @Override
   public <V> V get(Class<V> clazz){
      V instance = find(clazz);
      if(null == instance){
         throw new FFatalError("Can't get bind object (class={1})", clazz);
      }
      return instance;
   }

   //============================================================
   // <T>绑定类对应的内容对象。</T>
   //
   // @param clazz 类对象
   // @param item 内容对象
   //============================================================
   @Override
   public void bind(Class<?> clazz,
                    Object object){
      if(_logger.debugAble()){
         _logger.debug(this, "bind", "Bind value. ({1}={2})", clazz, object);
      }
      String code = currentCode();
      FBindInfo info = _infos.sync(code);
      info.set(clazz.getName(), object);
   }

   //============================================================
   // <T>解除绑定类对应的内容对象。</T>
   //
   // @param clazz 类对象
   // @return 内容对象
   //============================================================
   @Override
   public <V> void unbind(Class<V> clazz){
      String code = currentCode();
      FBindInfo info = _infos.get(code);
      if(null != info){
         info.remove(clazz.getName());
      }
   }

   //============================================================
   // <T>移除关联的所有内容对象。</T>
   //============================================================
   @Override
   public void remove(){
      _infos.remove(currentCode());
   }

   //============================================================
   // <T>清空所有内容对象。</T>
   //============================================================
   @Override
   public void clear(){
      String id = currentCode();
      FBindInfo info = _infos.get(id);
      if(null != info){
         _logger.debug(this, "clear", "Clear bind info (id={1})", id);
         info.release();
      }
   }
}
