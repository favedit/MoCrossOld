package org.mo.web.core.container;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>页面容器工具类。</T>
//============================================================
public class RWebContainer
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(RWebContainer.class);

   // 前缀字符串
   public static final String Head = "_";

   // 关联字符串
   public static final String Link = "_";

   //============================================================
   // <T>生成名称。</T>
   //
   // @param objectId 对象编号
   // @param name 名称
   // @return 名称
   //============================================================
   public static final String makeName(String objectId,
                                       String name){
      return Head + objectId + Link + name;
   }

   //============================================================
   // <T>分解名称。</T>
   //
   // @param name 名称
   // @return 内容数组
   //============================================================
   public static final String[] parseName(String name){
      if(name == null){
         return null;
      }
      String[] result = null;
      if(name.startsWith(Head)){
         int index = name.indexOf(Link, 1);
         if(index != -1){
            result = new String[2];
            result[0] = name.substring(1, index);
            result[1] = name.substring(index + 1);
         }else{
            _logger.warn(null, "parseName", "Parse source name failure. (name={1})", name);
         }
      }else{
         result = new String[2];
         result[0] = null;
         result[1] = name;
      }
      return result;
   }
}
