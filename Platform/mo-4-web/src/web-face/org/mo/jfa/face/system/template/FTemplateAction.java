/*
 * @(#)FTemplateAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.system.template;

import org.mo.com.lang.FFatalError;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.template.ITemplateConfigConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FTemplateAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         ITemplateAction
{

   @ALink
   protected ITemplateConfigConsole _templateConfigConsole;

   public final static String PAGE_CATALOG = "Catalog";

   public final static String PAGE_SOURCE = "Source";

   @Override
   public String catalog(IWebContext context,
                         FTemplatePage page){
      return catalog(_templateConfigConsole, context, page, PAGE_CATALOG);
   }

   @Override
   public String delete(IWebContext context,
                        FTemplatePage page){
      return delete(_templateConfigConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FTemplatePage page){
      return insert(_templateConfigConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FTemplatePage page){
      return list(_templateConfigConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FTemplatePage page){
      return sort(_templateConfigConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String source(IWebContext context,
                        FTemplatePage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      String collection = page.getSelectCollection();
      IXmlObject xcollection = _templateConfigConsole.get(collection);
      page.setCollection(xcollection);
      String component = page.getSelectComponent();
      IXmlObject xcomponent = xcollection.children().search(component);
      if(null == xcomponent){
         throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
      }
      // 返回数据
      page.setSource(xcomponent.innerText());
      return PAGE_SOURCE;
   }

   @Override
   public String sourceSave(IWebContext context,
                            FTemplatePage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的XML集合对象和XML对象
      String collection = page.getSelectCollection();
      IXmlObject xcollection = _templateConfigConsole.get(collection);
      page.setCollection(xcollection);
      String component = page.getSelectComponent();
      IXmlObject xcomponent = xcollection.children().search(component);
      if(null == xcomponent){
         throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
      }
      // 返回数据
      String source = context.parameter("source");
      xcomponent.setInnerText(source);
      _templateConfigConsole.store(xcollection);
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String update(IWebContext context,
                        FTemplatePage page){
      return update(_templateConfigConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}
