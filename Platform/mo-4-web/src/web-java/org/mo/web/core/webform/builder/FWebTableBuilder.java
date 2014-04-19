package org.mo.web.core.webform.builder;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>表格建立器。</T>
//============================================================
public class FWebTableBuilder
      extends FWebContainerBuilder
{
   protected String _formName;

   // 列集合
   protected FObjects<FWebColumnBuilder> _columns = new FObjects<FWebColumnBuilder>(FWebColumnBuilder.class);

   //============================================================
   // <T>构造表格建立器。</T>
   //============================================================
   public FWebTableBuilder(FWebBuilderContext context){
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
         if("ColumnNumber".equals(typeName)){
            FWebColumnNumberBuilder builder = new FWebColumnNumberBuilder(_context);
            builder.parse(xnode);
            _columns.push(builder);
         }else if("ColumnEdit".equals(typeName)){
            FWebColumnEditBuilder builder = new FWebColumnEditBuilder(_context);
            builder.parse(xnode);
            _columns.push(builder);
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
      String formName = xconfig.get("form_name", null);
      if(RString.isEmpty(formName)){
         formName = null;
      }
      // 获得数据集合
      int pageSize = _context.pageSize();
      int page = _context.pageIndex();
      FDataset dataset = fetchDataset(xconfig, pageSize, page);
      int count = dataset.count();
      int total = dataset.total();
      int pageCount = dataset.pageCount();
      //............................................................
      String label = xconfig.get("label");
      _context.appendHtml("<TABLE width='100%' border='0'><TR>");
      _context.appendHtml("<TD style='color:#114091'><B>");
      _context.appendHtml(label);
      _context.appendHtml("</B></TD>");
      _context.appendHtml("<TD align='right'>");
      _context.appendHtml("<A href=\"javascript:doAction('insert')\">新建</A>");
      _context.appendHtml("</TD>");
      _context.appendHtml("</TR></TABLE>\n");
      //............................................................
      // 建立表格内容
      _context.appendHtml("<TABLE width='100%' border='0' bgcolor='#B1CBE2' cellpadding='3' cellspacing='1'>\n");
      // 建立头内容
      _context.appendHtml("<TR bgcolor='#D5E7F3'>\n");
      for(FWebColumnBuilder columnBuilder : _columns){
         columnBuilder.buildHead();
      }
      _context.appendHtml("<TD style='color:#114091'><B>命令</B></TD>");
      _context.appendHtml("</TR>\n");
      // 建立数据内容
      for(int n = 0; n < count; n++){
         FRow row = dataset.get(n);
         String pack = row.pack();
         _context.setFocusRow(row);
         _context.appendHtml("<TR");
         if(n % 2 == 1){
            _context.appendHtml(" bgcolor='#FAFAFA'");
         }else{
            _context.appendHtml(" bgcolor='#FFFFFF'");
         }
         _context.appendHtml(">\n");
         for(FWebColumnBuilder columnBuilder : _columns){
            columnBuilder.build(xconfig);
         }
         _context.appendHtml("<TD>");
         _context.appendHtml("<A href=\"javascript:doDetail('" + formName + "','" + pack + "')\">详细</A>");
         _context.appendHtml("</TD>\n");
         _context.appendHtml("</TR>\n");
      }
      _context.appendHtml("</TABLE>\n");
      //............................................................
      _context.appendHtml("<TABLE width='100%' border='0'><TR>");
      _context.appendHtml("<TD>");
      _context.appendHtml("共<FONT color='red'><B>" + pageCount + "</B></FONT>页<FONT color='red'><B>" + total + "</B></FONT>条记录，");
      _context.appendHtml("当前选中第<FONT color='red'><B>" + dataset.page() + "</B></FONT>页");
      _context.appendHtml("</TD>");
      _context.appendHtml("<TD align='right'>");
      if(dataset.page() == dataset.firstPage()){
         _context.appendHtml("首页");
      }else{
         _context.appendHtml("<A href=\"javascript:doPage(" + dataset.firstPage() + ")\">首页</A>");
      }
      _context.appendHtml(" | ");
      if(dataset.page() == dataset.priorPage()){
         _context.appendHtml("前一页");
      }else{
         _context.appendHtml("<A href=\"javascript:doPage(" + dataset.priorPage() + ")\">前一页</A>");
      }
      _context.appendHtml(" | ");
      if(dataset.page() == dataset.nextPage()){
         _context.appendHtml("后一页");
      }else{
         _context.appendHtml("<A href=\"javascript:doPage(" + dataset.nextPage() + ")\">后一页</A>");
      }
      _context.appendHtml(" | ");
      if(dataset.page() == dataset.lastPage()){
         _context.appendHtml("末页");
      }else{
         _context.appendHtml("<A href=\"javascript:doPage(" + dataset.lastPage() + ")\">末页</A>");
      }
      _context.appendHtml("</TD>");
      _context.appendHtml("</TR></TABLE>\n");
   }
}
