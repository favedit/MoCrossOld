package org.mo.core.aop.config;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;

//============================================================
// <T>AOP定义集合。</T>
//============================================================
public class XAopDefineCollection
      extends XAopNodeCollection<XAopDefine>
{
   //============================================================
   // <T>构造AOP定义集合。</T>
   //============================================================
   public XAopDefineCollection(){
      super(XAopDefine.class);
   }

   //============================================================
   // <T>格式化显示内容。</T>
   //============================================================
   public String parse(String value){
      // 检查参数
      if(RString.isEmpty(value)){
         return value;
      }
      // 替换内容
      int start = 0;
      while((start = value.indexOf("${")) != -1){
         int end = value.indexOf("}", start);
         if(-1 != end){
            String refer = value.substring(start + 2, end);
            XAopDefine define = findByKey(refer.toLowerCase());
            if(define == null){
               throw new FFatalError("Refer is not defined. (value={1}, refer={2})", value, refer);
            }
            String parse = define.value();
            if(parse == null){
               parse = "";
            }
            value = value.substring(0, start) + parse + value.substring(end + 1);
         }
      }
      return value;
   }
}
