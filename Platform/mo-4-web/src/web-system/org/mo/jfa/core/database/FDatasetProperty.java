package org.mo.jfa.core.database;

public class FDatasetProperty
{

   //   public void loadProperty(IWebContext iContext,
   //                            FStringList oParameters,
   //                            FStringList oValues)
   //         throws FException{
   //      String sTableName = oParameters.value("table_name");
   //      if (sTableName.endsWith("_DS")) {
   //         sTableName = sTableName.substring(0, sTableName.length() - 3);
   //      }
   //      String sCatalog = sTableName.toLowerCase().replaceAll("_", ".");
   //      if (FStringUtil.isEmpty(oValues.value("catalog"))) {
   //         oValues.setValue("catalog", sCatalog);
   //      }
   //   }
   //
   //   public void saveProperty(IWebContext iContext,
   //                            FStringList oParameters,
   //                            FStringList oValues)
   //         throws FException{
   //      //      String sTableName = oParameters.value("table_name");
   //      //      ComDbiDatasetDi oComDbiDatasetDi = new ComDbiDatasetDi(iContext);
   //      //      FStringList oParams = new FStringList();
   //      //      oParams.setValue("dataset", sTableName);
   //      //      oParams.setValue("property", oValues.pack().toString());
   //      //      oComDbiDatasetDi.doSync(null, oParams);
   //   }

}
