package org.mo.jfa.core.database;

import org.mo.com.lang.FError;
import org.mo.com.lang.FStrings;
import org.mo.web.protocol.context.IWebContext;

public class FTableProperty
{

   //   public void loadProperty(IWebContext iContext,
   //                            FStringList oParameters,
   //                            FStringList oValues)
   //         throws FException{
   //      String sTable = oParameters.value("table_name");
   //      //String sLogic = FWfcSqlUtil.buildLogicName(sTable);
   //      String sCatalog = FWfcSqlUtil.buildCatalogName(sTable);
   //      oValues.setValue("name", sTable.toLowerCase());
   //      oValues.setValue("data_name", sTable);
   //      if(FStringUtil.isEmpty(oValues.value("catalog"))){
   //         oValues.setValue("catalog", sCatalog);
   //      }
   //      if(FStringUtil.isEmpty(oValues.value("label"))){
   //         oValues.setValue("label", sTable.toUpperCase());
   //      }
   //   }

   public void saveProperty(IWebContext iContext,
                            FStrings oParameters,
                            FStrings oValues) throws FError{
   }

}
