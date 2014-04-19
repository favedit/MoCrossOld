package org.mo.jfa.face.database.synchronizer;

import org.mo.core.aop.face.ALink;
import org.mo.data.synchronizer.ISynchronizerConsole;
import org.mo.data.synchronizer.common.XSynchronizer;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.protocol.context.IWebContext;

public class FSynchronizerAction
      extends FAbsXmlObjectAction<XSynchronizer>
      implements
         ISynchronizerAction
{
   public final static String PAGE_CATALOG = "Catalog";

   @ALink
   protected ISynchronizerConsole _synchronizerConsole;

   @Override
   public String catalog(IWebContext context,
                         FSynchronizerPage page){
      return catalog(_synchronizerConsole, context, page, PAGE_CATALOG);
   }

   @Override
   public String delete(IWebContext context,
                        FSynchronizerPage page){
      return delete(_synchronizerConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String insert(IWebContext context,
                        FSynchronizerPage page){
      return insert(_synchronizerConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FSynchronizerPage page){
      return list(_synchronizerConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String sort(IWebContext context,
                      FSynchronizerPage page){
      return sort(_synchronizerConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FSynchronizerPage page){
      return update(_synchronizerConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
