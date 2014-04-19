package org.mo.jfa.face.database.procedure;

import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.IDataInfoConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FFunctionAction
      extends FAbstractCommon
      implements
         IFunctionAction
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
      // Environment
      setEnvironment(context, page);
      // Attribute
      //      String pageName = page.getSelectPackage();
      //      FSqlPackageParser parser = new FSqlPackageParser(sqlContext, pageName);
      //      if(parser.parseHead()){
      //         FSqlFunctions funName = parser.functions();
      //         for(int i = 0; i < funName.count(); i++){
      //            if(page.getSelectFunction().equals(funName.name(i))){
      //               FXmlNode node = new FXmlNode();
      //               node.set("attribute", funName.name(i));
      //               page.setFormValue(node.xml());
      //            }
      //         }
      //      }
      return "Function";
   }

}
