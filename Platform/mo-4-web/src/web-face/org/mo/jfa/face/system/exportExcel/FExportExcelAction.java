/*
 * @(#)FTemplateAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.system.exportExcel;

import org.mo.com.lang.FFatalError;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.export.IExportExcelConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FExportExcelAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IExportExcelAction
{
   @ALink
   protected IExportExcelConsole _exportExcelConsole;

   public final static String PAGE_CATALOG = "Catalog";

   public final static String PAGE_SOURCE = "Source";

   @Override
   public String buildSql(IWebContext context,
                          FExportExcelPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      String collection = page.getSelectCollection();
      IXmlObject xcollection = _exportExcelConsole.get(collection);
      String component = page.getSelectComponent();
      IXmlObject xcomponent = xcollection.children().search(component);
      page.setComponent(xcomponent);
      if(null == xcomponent){
         throw new FFatalError("Xml view is not found. (collection={0},component={1})", collection);
      }
      // 返回数据
      page.setSqlSource(xcomponent.innerText());
      return "SqlSource";
   }

   @Override
   public String catalog(IWebContext context,
                         FExportExcelPage page){
      return catalog(_exportExcelConsole, context, page, PAGE_CATALOG);
   }

   @Override
   public String delete(IWebContext context,
                        FExportExcelPage page){
      return delete(_exportExcelConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FExportExcelPage page){
      return insert(_exportExcelConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FExportExcelPage page){
      return list(_exportExcelConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FExportExcelPage page){
      return sort(_exportExcelConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String sqlSourceSave(IWebContext context,
                               FExportExcelPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      IXmlObject xcomponent = page.getComponent();
      IXmlObject xcollection = page.getCollection();
      if(null == xcomponent){
         throw new FFatalError("Xml component is not found. (collection={0},component={1})", xcollection, xcomponent);
      }
      // 返回数据
      String SqlSource = page.getSqlSource();
      xcomponent.setInnerText(SqlSource);
      _exportExcelConsole.store(xcollection);
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String update(IWebContext context,
                        FExportExcelPage page){
      return update(_exportExcelConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}
