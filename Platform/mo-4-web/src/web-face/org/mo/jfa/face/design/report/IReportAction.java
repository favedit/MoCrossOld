package org.mo.jfa.face.design.report;

import org.mo.web.core.container.AContainer;

import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IReportAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FReportPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FReportPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FReportPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FReportPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FReportPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FReportPage page);

}
