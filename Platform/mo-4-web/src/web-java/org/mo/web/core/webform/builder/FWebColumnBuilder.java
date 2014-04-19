package org.mo.web.core.webform.builder;

import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>网页列建立对象。</T>
//============================================================
public class FWebColumnBuilder
      extends FWebBuilderObject
{
   // 配置节点
   protected FXmlNode _config;

   // 宽度
   protected String _width;

   // 数据名称
   protected String _dataName;

   //============================================================
   // <T>构造网页列建立对象。</T>
   //
   // @param context 建立环境
   //============================================================
   public FWebColumnBuilder(FWebBuilderContext context){
      super(context);
   }

   //============================================================
   // <T>获得配置节点。</T>
   //============================================================
   public FXmlNode config(){
      return _config;
   }

   //============================================================
   // <T>获得宽度。</T>
   //
   // @return 宽度
   //============================================================
   public String width(){
      return _width;
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
   // <T>解析配置信息。</T>
   //============================================================
   @Override
   public void parse(FXmlNode xconfig){
      _config = xconfig;
      _width = xconfig.get("width", "60");
      _dataName = RString.toLower(xconfig.get("data_name"));
   }

   //============================================================
   // <T>建立头信息。</T>
   //============================================================
   public void buildHead(){
      String label = _config.get("label");
      _context.appendHtml(label);
   }
}
