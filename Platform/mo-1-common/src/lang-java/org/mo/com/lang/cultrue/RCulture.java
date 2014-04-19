package org.mo.com.lang.cultrue;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;

//============================================================
// <T>文化定义。</T>
//============================================================
public class RCulture
{
   // 默认代码
   public static String DEFAULT_CODE = "zh";

   // 默认语言
   public static String DEFAULT_LANGUAGE = "cn";

   // 默认编码
   public static String DEFAULT_ENCODING = "UTF-8";

   // 文化定义
   public static FCulture _culture = new FCulture();

   // 文化集合
   protected static FObjects<FCulture> _cultures = new FObjects<FCulture>(FCulture.class);

   //============================================================
   // <T>获得文化定义。</T>
   //
   // @return 文化定义
   //============================================================
   public static FCulture culture(){
      return _culture;
   }

   //============================================================
   // <T>根据线程编号获得文化定义。</T>
   //
   // @param threadId 线程编号
   // @return 文化定义
   //============================================================
   public static FCulture findThreadCulture(long threadId){
      int count = _cultures.count();
      for(int n = 0; n < count; n++){
         FCulture culture = _cultures.get(n);
         if(culture.threadId() == threadId){
            return culture;
         }
      }
      return null;
   }

   //============================================================
   // <T>获得线程关联文化定义。</T>
   //
   // @return 文化定义
   //============================================================
   public static FCulture currentCulture(){
      long threadId = Thread.currentThread().getId();
      return findThreadCulture(threadId);
   }

   //============================================================
   // <T>根据当前线程的语言。</T>
   //
   // @return 语言
   //============================================================
   public static String currentLanguage(){
      FCulture culture = currentCulture();
      if(null == culture){
         culture = _culture;
      }
      return culture.country().language();
   }

   //============================================================
   // <T>关联当前线程的文化定义。</T>
   //
   // @param culture 文化定义
   //============================================================
   public static void link(FCulture culture){
      // 检查参数
      if(null == culture){
         throw new FFatalError("Culture is null.");
      }
      // 检查存在性
      long threadId = Thread.currentThread().getId();
      FCulture findCulture = findThreadCulture(threadId);
      if(null != findCulture){
         throw new FFatalError("Culture is already exists.");
      }
      // 放入集合
      culture.setThreadId(threadId);
      _cultures.push(culture);
   }

   //============================================================
   // <T>断开当前线程的文化定义。</T>
   //
   // @return 文化定义
   //============================================================
   public static FCulture unlink(){
      // 检查存在性
      long threadId = Thread.currentThread().getId();
      FCulture culture = findThreadCulture(threadId);
      if(null == culture){
         throw new FFatalError("Culture is not exists. (thread_id={1})", threadId);
      }
      culture.setThreadId(0);
      _cultures.remove(culture);
      return culture;
   }

   //============================================================
   // <T>断开当前线程的文化定义。</T>
   //
   // @param culture 文化定义
   //============================================================
   public static void unlink(FCulture culture){
      // 检查参数
      if(null == culture){
         throw new FFatalError("Culture is null.");
      }
      culture.setThreadId(0);
      _cultures.remove(culture);
   }

   //============================================================
   // <T>释放所有信息。</T>
   //============================================================
   public static void release(){
      _cultures.clear();
   }
}
