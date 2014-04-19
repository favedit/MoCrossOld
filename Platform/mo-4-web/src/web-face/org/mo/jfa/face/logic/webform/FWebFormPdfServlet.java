package org.mo.jfa.face.logic.webform;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.validator.RStringValidator;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.core.webform.FWebFormDatasetArgs;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.protocol.context.IWebContext;

public class FWebFormPdfServlet
      implements
         IWebFormPdfServlet
{

   private static ILogger _logger = RLogger.find(FWebFormPdfServlet.class);

   public static byte[] creatPdf(FXmlNode dsNode,
                                 IXmlObject formNode){
      // 创建一个Document对象
      Rectangle rectPageSize = new Rectangle(PageSize.A4);// 定义A4页面大小
      rectPageSize = rectPageSize.rotate();// 实现A4页面的横置
      Document document = new Document(rectPageSize, 50, 30, 30, 30);// 4个参数，
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      // 设置了页面的4个边距
      try{
         // 生成名为 HelloWorld.pdf 的文档
         PdfWriter.getInstance(document, buffer);

         BaseFont bfChinese;
         try{
            bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
         }catch(IOException e){
            throw new FFatalError(e);
         }
         Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.red);
         document.open();
         // 插入一个段落
         //获得模块名称
         Paragraph par = new Paragraph(formNode.innerGet("label"), fontChinese);
         document.add(par);

         int tableColumns = formNode.children().count();
         // 定义表格填充内容
         PdfPTable datatable = new PdfPTable(tableColumns); // 创建新表.
         // int headerwidths[] = { 9, 4, 8, 10, 8, 7 }; // percentage 定义表格头宽度
         // datatable.setWidths(headerwidths);
         datatable.setWidthPercentage(100);
         // 设置表头的高度
         datatable.getDefaultCell().setPadding(2);
         // 设置表头的粗细线条
         datatable.getDefaultCell().setBorderWidth(1);
         datatable.getDefaultCell().setGrayFill(0.8f);
         datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
         // 以下是填充表头
         for(int i = 0; i < tableColumns; i++){
            datatable.addCell(new Phrase(formNode.children().get(i).innerGet("label"), fontChinese));
         }
         datatable.setHeaderRows(1);
         // 结束表格的头部
         // 设置页码
         HeaderFooter footer = new HeaderFooter(new Phrase("页码：", fontChinese), true);
         footer.setBorder(Rectangle.NO_BORDER);
         document.setFooter(footer);
         // 结束页码的设置
         //设置表格颜色参数
         int i = 1;
         datatable.getDefaultCell().setBorderWidth(1);
         for(FXmlNode row : dsNode.nodes()){
            if(i % 2 == 1){
               // 设置表格颜色
               datatable.getDefaultCell().setGrayFill(1.0f);
            }
            //根据数据列项，依次取出该列所对应的值
            for(int x = 0; x < tableColumns; x++){
               String columnName = formNode.children().get(x).innerGet("data_name");

               datatable.addCell(new Phrase(row.get(columnName), fontChinese));

            }
            if(i % 2 == 1){
               // 设置表格颜色
               datatable.getDefaultCell().setGrayFill(0.9f);
            }
            i++;
         }
         document.add(datatable);// 加载新表
      }catch(DocumentException de){
         de.printStackTrace();
         System.err.println("document: " + de.getMessage());
      }finally{
         document.close();
      }
      return buffer.toByteArray();
   }

   @ALink
   protected IWebFormConsole _webformConsole;

   @ALink
   IWebFormDatasetConsole _webFormDataConsole;

   @Override
   public void build(IWebContext context,
                     ISqlContext sqlContext,
                     IWebServletResponse response){
      String formName = context.parameter("form_name");
      RStringValidator.checkEmpty(formName, "form_name");
      IXmlObject xform = findForm(formName);
      // 查找数据集
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setPageSize(-1);
      xform.children().get(0).innerGet("label");
      args.setForm(xform);
      FXmlNode dsNode = _webFormDataConsole.fetchNode(args);
      // 生成PDF文件爱女
      byte[] bytes = creatPdf(dsNode, xform);
      _logger.debug(this, "build", "Make form pdf file. (form={0}, pdf size={1})", xform.name(), bytes.length);
      response.write(bytes);
   }

   public IXmlObject findForm(String formName){
      IXmlObject xform = null;
      if(RString.isNotEmpty(formName)){
         xform = _webformConsole.find(formName);
         if(null == xform){
            throw new FFatalError("Show form is null. (name={0})", formName);
         }
      }
      return xform;
   }
}
