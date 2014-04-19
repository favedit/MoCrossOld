package org.mo.jfa.face.database.logicunit;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.logicunit.ILogicUnitConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FLogicUnitAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         ILogicUnitAction
{

   public final static String PAGE_CATALOG = "Catalog";

   @ALink
   protected ILogicUnitConsole _logicunitConsole;

   @Override
   public String catalog(IWebContext context,
                         FLogicUnitPage page){
      return PAGE_CATALOG;
   }

   @Override
   public String delete(IWebContext context,
                        FLogicUnitPage page){
      return delete(_logicunitConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FLogicUnitPage page){
      return insert(_logicunitConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FLogicUnitPage page){
      return list(_logicunitConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FLogicUnitPage page){
      return sort(_logicunitConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FLogicUnitPage page){
      return update(_logicunitConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

}
