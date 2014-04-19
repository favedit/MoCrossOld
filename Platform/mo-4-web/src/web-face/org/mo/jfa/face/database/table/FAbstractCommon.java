package org.mo.jfa.face.database.table;

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
                              FTablePage page){
      String table = context.parameter("sel_table");
      String field = context.parameter("sel_field");
      // Page
      page.setSelectTable(table);
      page.setSelectField(field);
      // Env
      page.setEnv("sel_table", table);
      page.setEnv("sel_field", field);
   }

}
