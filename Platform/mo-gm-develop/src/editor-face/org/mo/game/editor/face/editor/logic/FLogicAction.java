package org.mo.game.editor.face.editor.logic;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.logic.ELogicSource;
import org.mo.game.editor.core.logic.ILogicConsole;
import org.mo.game.editor.core.logic.common.XLogic;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.protocol.context.IWebContext;

public class FLogicAction
      extends FAbsXmlObjectAction<XLogic>
      implements
         ILogicAction
{
   private static ILogger _logger = RLogger.find(FLogicAction.class);

   @ALink
   protected ILogicConsole _logicConsole;

   public final static String PAGE_SOURCE = "Source";

   public final String SEL_COL = "sel_collection";

   public final static String PAGE_CATALOG = "Catalog";

   //============================================================
   @Override
   public String buildAllSource(IWebContext context,
                                FLogicPage page){
      String type = context.parameter("type");
      _logger.debug(this, "buildAllSource", "Build type source. (type={0})", type);
      // 生成代码
      if("all".equals(type)){
         _logicConsole.buildAll(ELogicSource.All);
      }else if("lua".equals(type)){
         _logicConsole.buildAll(ELogicSource.Lua);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FLogicPage page){
      return PAGE_CATALOG;
   }

   //============================================================
   @Override
   public String delete(IWebContext context,
                        FLogicPage page){
      return delete(_logicConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String insert(IWebContext context,
                        FLogicPage page){
      return insert(_logicConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String list(IWebContext context,
                      FLogicPage page){
      return list(_logicConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String sort(IWebContext context,
                      FLogicPage page){
      return sort(_logicConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   //============================================================
   @Override
   public String update(IWebContext context,
                        FLogicPage page){
      return update(_logicConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
