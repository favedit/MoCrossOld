package org.mo.jfa.face.database.procedure;

import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FProcedureAction
      extends FAbstractCommon
      implements
         IProcedureAction
{

   @ALink
   protected IDataInfoConsole _dataInfoConsole;

   @Override
   public String catalog(IWebContext context,
                         FProcedurePage page){
      return "Catalog";
   }

   public String update(IWebContext context,
                        ISqlContext sqlContext,
                        FProcedurePage page){

      setEnvironment(context, page);
      // Attribute
      //      String pageName = page.getSelectPackage();
      //      FSqlPackageParser parser = new FSqlPackageParser(sqlContext, pageName);
      //      if(parser.parseHead()){
      //         FSqlProcedures procedures = parser.procedures();
      //         for(int i = 0; i < procedures.count(); i++){
      //            FSqlProcedure procedure = procedures.value(i);
      //            if(page.getSelectProcedure().equals(procedure.name())){
      //               FXmlNode node = new FXmlNode();
      //               node.set("attribute", procedures.name(i));
      //               page.setFormValue(node.xml());
      //               break;
      //            }
      //         }
      //      }
      return "Procedure";
   }
}
