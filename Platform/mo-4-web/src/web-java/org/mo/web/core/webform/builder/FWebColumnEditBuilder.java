package org.mo.web.core.webform.builder;

import org.mo.com.xml.FXmlNode;

//============================================================
// <T>网页编辑列建立对象。</T>
//============================================================
public class FWebColumnEditBuilder
      extends FWebColumnBuilder
{
   //============================================================
   // <T>构造网页编辑列建立对象。</T>
   //
   // @param context 建立环境
   //============================================================
   public FWebColumnEditBuilder(FWebBuilderContext context){
      super(context);
   }

   //============================================================
   // <T>建立头信息。</T>
   //============================================================
   @Override
   public void buildHead(){
      String label = _config.get("label");
      _context.appendHtml("<TD width=");
      _context.appendHtml(_width);
      _context.appendHtml(" style='color:#114091'><B>");
      _context.appendHtml(label);
      _context.appendHtml("</B></TD>\n");
   }

   //============================================================
   // <T>建立内容。</T>
   //
   // @param xconfig 设置信息
   //============================================================
   @Override
   public void build(FXmlNode xconfig){
      String value = _context.focusRow().get(_dataName);
      _context.appendHtml("<TD>");
      _context.appendHtml(value);
      _context.appendHtml("</TD>\n");
   }
}
