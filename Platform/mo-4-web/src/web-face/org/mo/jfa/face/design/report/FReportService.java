package org.mo.jfa.face.design.report;

import org.mo.core.aop.face.ALink;
import org.mo.eng.report.IReportConsole;
import org.mo.eng.report.common.XReport;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FReportService
      extends FAbsXmlObjectService<XReport>
      implements
         IReportService
{

   @ALink
   protected IReportConsole _reportConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_reportConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_reportConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_reportConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_reportConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_reportConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_reportConsole, context, input, output);
   }
}
