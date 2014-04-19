package org.mo.jfa.face.system.help;

import org.mo.core.aop.face.ALink;
import org.mo.eng.help.IHelpConsole;
import org.mo.eng.help.common.XHelp;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FHelpService
      extends FAbsXmlObjectService<XHelp>
      implements
         IHelpService
{

   @ALink
   protected IHelpConsole _helpConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_helpConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_helpConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_helpConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_helpConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_helpConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_helpConsole, context, input, output);
   }

}
