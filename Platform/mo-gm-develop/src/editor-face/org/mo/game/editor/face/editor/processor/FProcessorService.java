package org.mo.game.editor.face.editor.processor;

import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.processor.IProcessorConsole;
import org.mo.game.editor.core.processor.common.XProject;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;


public class FProcessorService
      extends FAbsXmlObjectService<XProject>
      implements
         IProcessorService{

   @ALink
   protected IProcessorConsole _processorConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_processorConsole, context, input, output);
      // 设置排序
      //orderNode(output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_processorConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_processorConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_processorConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_processorConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_processorConsole, context, input, output);
   }
}
