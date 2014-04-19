package org.mo.jfa.face.database.procedure;

import org.mo.com.lang.RString;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.data.logicunit.common.XDataset;
import org.mo.eng.validator.IValidatorConsole;
import org.mo.web.protocol.context.IWebContext;

public class FAbstractCommon
{

   @ALink
   protected IDatasetConsole _datasetConsole;

   @ALink
   protected IValidatorConsole _validatorConsole;

   public XDataset findDataset(String dataset){
      if(RString.isNotEmpty(dataset)){
         //return _datasetConsole.find(dataset);
      }
      return null;
   }

   //   public XDatasetField findDatasetField(String dataset,
   //                                         String field){
   //      XDataset xdataset = findDataset(dataset);
   //      if(null != xdataset){
   //         if(RString.isNotEmpty(field)){
   //            //return (XDatasetField) xdataset.children().get(field);
   //         }
   //      }
   //      return null;
   //   }

   public void setEnvironment(IWebContext context,
                              FProcedurePage page){
      String sPackage = context.parameter("sel_Package");
      String function = context.parameter("sel_Function");
      String procedure = context.parameter("sel_Procedure");
      // Page
      page.setSelectPackage(sPackage);
      page.setSelectFunction(function);
      page.setSelectProcedure(procedure);
      // Env
      page.setEnv("sel_package", sPackage);
      page.setEnv("sel_function", function);
      page.setEnv("sel_procedure", procedure);
   }

}
