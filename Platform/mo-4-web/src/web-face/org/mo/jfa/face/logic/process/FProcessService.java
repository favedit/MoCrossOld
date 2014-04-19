package org.mo.jfa.face.logic.process;

import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.logic.process.ILogicProcessConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FProcessService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IProcessService
{

   @ALink
   protected ILogicProcessConsole _processConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_processConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessService#delete(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_processConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessService#insert(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_processConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessService#list(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_processConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessService#sort(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_processConsole, context, input, output);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessService#update(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_processConsole, context, input, output);
   }

}
