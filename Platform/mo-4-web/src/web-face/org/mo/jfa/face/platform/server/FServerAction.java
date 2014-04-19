package org.mo.jfa.face.platform.server;

import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.platform.server.IServerConsole;
import org.mo.platform.server.common.XServer;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>服务器命令。</T>
//============================================================
public class FServerAction
      extends FAbsXmlObjectAction<XServer>
      implements
         IServerAction
{
   // 页面定义
   public final static String PAGE_CATALOG = "Catalog";

   // 服务器控制台
   @ALink
   protected IServerConsole _listConsole;

   //============================================================
   // <T>列出目录处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FServerContainer page){
      return PAGE_CATALOG;
   }
   //============================================================
   // <T>列出项目处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String list(IWebContext context,
                      FServerContainer page){
      return list(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>新建处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String insert(IWebContext context,
                        FServerContainer page){
      return insert(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>更新处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String update(IWebContext context,
                        FServerContainer page){
      return update(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
   //============================================================
   // <T>删除处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String delete(IWebContext context,
                        FServerContainer page){
      return delete(_listConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>排序处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String sort(IWebContext context,
                      FServerContainer page){
      return sort(_listConsole, context, page, IPublicPage.XOBJECT_SORT);
   }
}
