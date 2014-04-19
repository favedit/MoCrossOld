package org.mo.web.core.webform.builder;

import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>网页控件建立器。</T>
//============================================================
public class FWebControlBuilder
      extends FWebBuilderObject
{
   // 标签宽度
   protected String _labelWidth;

   // 编辑宽度
   protected String _editWidth;

   // 数据名称
   protected String _dataName;

   // 数据名称
   protected boolean _nowrap;

   //============================================================
   // <T>构造网页控件建立器。</T>
   //
   // @param context 建立环境
   //============================================================
   public FWebControlBuilder(FWebBuilderContext context){
      super(context);
   }

   //============================================================
   // <T>获得数据名称。</T>
   //
   // @return 数据名称
   //============================================================
   public String dataName(){
      return _dataName;
   }

   //============================================================
   // <T>获得续行标志。</T>
   //
   // @return 续行标志
   //============================================================
   public boolean nowrap(){
      return _nowrap;
   }

   //============================================================
   // <T>解析配置信息。</T>
   //============================================================
   @Override
   public void parse(FXmlNode xconfig){
      _config = xconfig;
      _labelWidth = xconfig.get("label_width", "80");
      _editWidth = xconfig.get("edit_width", "200");
      _dataName = RString.toLower(xconfig.get("data_name"));
      _nowrap = xconfig.getBoolean("nowrap", false);
   }

   //============================================================
   // <T>建立头信息。</T>
   //============================================================
   public void buildLabel(){
      String label = _config.get("label");
      _context.appendHtml(label);
   }

   //============================================================
   // <T>建立头信息。</T>
   //============================================================
   public void buildEdit(){
      String value = _context.focusRow().get(_dataName);
      _context.appendHtml(value);
   }

   //============================================================
   // <T>建立内容。</T>
   //
   // @param xconfig 设置信息
   //============================================================
   @Override
   public void build(FXmlNode xconfig){
      _context.appendHtml("<TABLE border='0' cellpadding='3' cellspacing='0'>\n");
      _context.appendHtml("<TR>");
      //............................................................
      // 建立标签
      _context.appendHtml("<TD width=" + _labelWidth + ">");
      buildLabel();
      _context.appendHtml("</TD>");
      //............................................................
      // 建立内容
      _context.appendHtml("<TD width=" + _editWidth + ">");
      buildEdit();
      _context.appendHtml("</TD>");
      _context.appendHtml("</TR>");
      _context.appendHtml("</TABLE>\n");
   }
}
