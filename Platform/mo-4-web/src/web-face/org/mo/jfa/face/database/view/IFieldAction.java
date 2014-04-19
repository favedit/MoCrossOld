package org.mo.jfa.face.database.view;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IFieldAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FViewPage page);

   String update(IWebContext context,
                 ISqlContext sqlContext,
                 @AContainer(name = "page") FViewPage page);

}
