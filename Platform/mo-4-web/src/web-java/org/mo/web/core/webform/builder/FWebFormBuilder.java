package org.mo.web.core.webform.builder;

import org.mo.com.collections.FRow;
import org.mo.com.lang.FObjects;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>表单建立器。</T>
//============================================================
public class FWebFormBuilder
      extends FWebContainerBuilder
{
   // 表单名称
   protected String _formName;

   // 列集合
   protected FObjects<FWebControlBuilder> _controls = new FObjects<FWebControlBuilder>(FWebControlBuilder.class);

   //============================================================
   // <T>构造表单建立器。</T>
   //============================================================
   public FWebFormBuilder(FWebBuilderContext context){
      super(context);
   }

   //============================================================
   // <T>解析配置。</T>
   //
   // @param xconfig 设置信息
   //============================================================
   @Override
   public void parse(FXmlNode xconfig){
      _formName = xconfig.get("name");
      for(FXmlNode xnode : xconfig.nodes()){
         String typeName = xnode.name();
         if("Number".equals(typeName)){
            FWebNumberBuilder builder = new FWebNumberBuilder(_context);
            builder.parse(xnode);
            _controls.push(builder);
         }else if("Edit".equals(typeName)){
            FWebEditBuilder builder = new FWebEditBuilder(_context);
            builder.parse(xnode);
            _controls.push(builder);
         }
      }
   }

   //============================================================
   // <T>建立内容。</T>
   //
   // @param xconfig 设置信息
   //============================================================
   @Override
   public void build(FXmlNode xconfig){
      // 解析对象
      parse(xconfig);
      // 获得数据集合
      FRow row = fetchRow(xconfig);
      //String pack = row.pack();
      _context.setFocusRow(row);
      //............................................................
      String label = xconfig.get("label");
      _context.appendHtml("<TABLE width='100%' border='0'><TR>");
      _context.appendHtml("<TD style='color:#114091'><B>");
      _context.appendHtml(label);
      _context.appendHtml("</B></TD>");
      _context.appendHtml("<TD align='right'>");
      _context.appendHtml("<A href=\"javascript:doAction('update')\">更新</A> | ");
      _context.appendHtml("<A href=\"javascript:doAction('delete')\">删除</A>");
      _context.appendHtml("</TD>");
      _context.appendHtml("</TR></TABLE>\n");
      //............................................................
      boolean newLine = true;
      for(FWebControlBuilder controlBuilder : _controls){
         if(newLine){
            _context.appendHtml("<TABLE border='0' cellpadding='3' cellspacing='0'>\n");
            _context.appendHtml("<TR>");
            newLine = false;
         }
         _context.appendHtml("<TD>");
         controlBuilder.build(xconfig);
         _context.appendHtml("</TD>");
         if(controlBuilder.nowrap()){
            _context.appendHtml("</TR>");
            _context.appendHtml("</TABLE>");
            newLine = true;
         }
      }
   }
}
