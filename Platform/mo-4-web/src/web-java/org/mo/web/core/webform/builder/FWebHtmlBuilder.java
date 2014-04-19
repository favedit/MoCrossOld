package org.mo.web.core.webform.builder;

import org.mo.com.lang.FObject;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>页面建立器。</T>
//============================================================
public class FWebHtmlBuilder
      extends FObject
{
   // 建立环境
   protected FWebBuilderContext _context = new FWebBuilderContext();

   //============================================================
   // <T>构造页面建立器。</T>
   //============================================================
   public FWebHtmlBuilder(){
   }

   //============================================================
   // <T>获得建立环境。</T>
   //
   // @return 建立环境
   //============================================================
   public FWebBuilderContext context(){
      return _context;
   }

   //============================================================
   // <T>获得建立环境。</T>
   //
   // @return 建立环境
   //============================================================
   public String build(FXmlNode xconfig){
      // 解析信息
      String typeName = xconfig.name();
      if("WebForm".equals(typeName)){
         FWebFormBuilder formBuilder = new FWebFormBuilder(_context);
         formBuilder.build(xconfig);
      }else if("WebTable".equals(typeName)){
         FWebTableBuilder tableBuilder = new FWebTableBuilder(_context);
         tableBuilder.build(xconfig);
      }
      // 返回内容
      return _context.html().toString();
   }
}
