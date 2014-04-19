package org.mo.web.core.webform.builder;

import org.mo.com.xml.FXmlNode;

//============================================================
// <T>网页建立对象。</T>
//============================================================
public class FWebBuilderObject
{
   // 建立环境
   protected FWebBuilderContext _context;

   // 配置节点
   protected FXmlNode _config;

   //============================================================
   // <T>构造网页建立对象。</T>
   //
   // @param context 建立环境
   //============================================================
   public FWebBuilderObject(FWebBuilderContext context){
      _context = context;
   }

   //============================================================
   // <T>获得配置节点。</T>
   //============================================================
   public FXmlNode config(){
      return _config;
   }

   //============================================================
   // <T>解析内容。</T>
   //
   // @param xconfig 设置信息
   //============================================================
   public void parse(FXmlNode xconfig){
      _config = xconfig;
   }

   //============================================================
   // <T>建立内容。</T>
   //
   // @param xconfig 设置信息
   //============================================================
   public void build(FXmlNode xconfig){
   }
}
