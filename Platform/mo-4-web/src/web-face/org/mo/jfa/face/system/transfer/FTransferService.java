package org.mo.jfa.face.system.transfer;

import org.mo.core.aop.face.ALink;
import org.mo.eng.transfer.ITransferConsole;
import org.mo.eng.transfer.common.XCsvImport;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FTransferService
      extends FAbsXmlObjectService<XCsvImport>
      implements
         ITransferService
{

   @ALink
   protected ITransferConsole _transferConsole;

   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_transferConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_transferConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_transferConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_transferConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_transferConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_transferConsole, context, input, output);
   }
}
