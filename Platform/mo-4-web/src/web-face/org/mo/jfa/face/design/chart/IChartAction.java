package org.mo.jfa.face.design.chart;

import org.mo.web.core.container.AContainer;

import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IChartAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FChartPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FChartPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FChartPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FChartPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FChartPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FChartPage page);

}
