package org.mo.jfa.face.design.webtools;

import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtools.IWebToolsConsole;
import org.mo.web.core.webtools.common.XToolBar;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebToolsService
      extends FAbsXmlObjectService<XToolBar>
      implements
         IWebToolsService
{

   @ALink
   protected IWebToolsConsole _toolsConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_toolsConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_toolsConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_toolsConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_toolsConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_toolsConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_toolsConsole, context, input, output);
   }
}
