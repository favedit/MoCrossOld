package org.mo.web.protocol.context;

import org.mo.com.lang.RString;

//============================================================
// <T>页面项目。</T>
//============================================================
public class SPageItem
{
   // 名称
   public String name;

   // 对象
   public Object object;

   // 内容
   public Object value;

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @return 内容字符串
   //============================================================
   public String valueString(){
      return RString.toString(value);
   }
}
