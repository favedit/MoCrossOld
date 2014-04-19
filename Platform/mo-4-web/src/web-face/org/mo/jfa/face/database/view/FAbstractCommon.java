package org.mo.jfa.face.database.view;

import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.validator.IValidatorConsole;
import org.mo.web.protocol.context.IWebContext;

public class FAbstractCommon
{

   @ALink
   protected IDatasetConsole _datasetConsole;

   @ALink
   protected IValidatorConsole _validatorConsole;

   public void setEnvironment(IWebContext context,
                              FViewPage page){
      String view = context.parameter("sel_view");
      String field = context.parameter("sel_field");
      // Page
      page.setSelectView(view);
      page.setSelectField(field);
      // Env
      page.setEnv("sel_view", view);
      page.setEnv("sel_field", field);
   }

}
