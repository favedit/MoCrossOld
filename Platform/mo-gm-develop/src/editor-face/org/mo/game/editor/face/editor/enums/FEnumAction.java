package org.mo.game.editor.face.editor.enums;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.enums.EEnumSource;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.enums.common.XEnumGroup;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.protocol.context.IWebContext;

public class FEnumAction
      extends FAbsXmlObjectAction<XEnumGroup>
      implements
         IEnumAction
{

   private static ILogger _logger = RLogger.find(FEnumAction.class);

   @ALink
   protected IEnumConsole _enumConsole;

   public final static String PAGE_SOURCE = "Source";

   public final String SEL_COL = "sel_collection";

   public final static String PAGE_CATALOG = "Catalog";

   @ALink
   protected IFormatConsole _formatConsole;

   //============================================================
   @Override
   public String buildAllSource(IWebContext context,
                                FEnumPage page){
      String type = context.parameter("type");
      _logger.debug(this, "buildAllSource", "Build type source. (type={0})", type);
      // 生成代码
      if("all".equals(type)){
         _enumConsole.buildAll(EEnumSource.All);
      }else if("cpp".equals(type)){
         _enumConsole.buildAll(EEnumSource.AllCpp);
      }else if("as".equals(type)){
         _enumConsole.buildAll(EEnumSource.AllAs);
      }else if("cs".equals(type)){
         _enumConsole.buildAll(EEnumSource.AllCs);
      }else if("java".equals(type)){
         _enumConsole.buildAll(EEnumSource.AllJava);
      }else if("lua".equals(type)){
         _enumConsole.buildAll(EEnumSource.AllLua);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FEnumPage page){
      return PAGE_CATALOG;
   }

   //============================================================
   @Override
   public String delete(IWebContext context,
                        FEnumPage page){
      return delete(_enumConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String fetch(IWebContext context,
                       FEnumPage page){
      // 获取定义列表
      _enumConsole.fetchDefineCodeList();
      _enumConsole.fetchDefineMessage();
      _enumConsole.fetchDefineProperty();
      _enumConsole.fetchDefineMail();
      // 保存当前设置
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String insert(IWebContext context,
                        FEnumPage page){
      return insert(_enumConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String list(IWebContext context,
                      FEnumPage page){
      return list(_enumConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String sort(IWebContext context,
                      FEnumPage page){
      return sort(_enumConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   //============================================================
   @Override
   public String update(IWebContext context,
                        FEnumPage page){
      return update(_enumConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
