package org.mo.jfa.face.apl.form;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RInteger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IConnectionConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.web.core.container.AContainer;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.builder.FWebHtmlBuilder;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>显示命令。</T>
//
// @author MAOCY
// @history 130701 MAOCY 创建
//============================================================
public class FShowAction
      extends FObject
      implements
         IShowAction
{
   // 数据库控制台
   @ALink
   protected IDatabaseConsole _databaseConsole;

   // 网页表单控制台
   @ALink
   protected IWebFormConsole _webFormConsole;

   //============================================================
   // <T>构建处理。</T>
   //
   // @param context 网页环境
   //============================================================
   @Override
   public String construct(IWebContext context,
                           @AContainer(name = "page") FFormContainer page){
      // 获得参数
      String formName = page.form();
      String databaseName = page.database();
      String pageIndex = page.page();
      String pack = page.pack();
      // 获得表单定义
      FXmlNode xform = _webFormConsole.build(formName, EXmlConfig.Value);
      System.out.println(xform.xml());
      IConnectionConsole connectionConsole = _databaseConsole.connectionConsole(databaseName);
      // 建立显示内容
      FWebHtmlBuilder builder = new FWebHtmlBuilder();
      builder.context().setConnectionConsole(connectionConsole);
      builder.context().parameters().unpack(pack);
      builder.context().setPageIndex(RInteger.parse(pageIndex));
      String formHtml = builder.build(xform);
      page.setHtml(formHtml);
      return null;
   }

   @Override
   public String delete(IWebContext context){
      return "#/apl/page/process/EndDelete.jsp";
   }

   @Override
   public String insert(IWebContext context){
      return "#/apl/page/process/EndInsert.jsp";
   }

   @Override
   public String update(IWebContext context){
      return "#/apl/page/process/EndUpdate.jsp";
   }
}
