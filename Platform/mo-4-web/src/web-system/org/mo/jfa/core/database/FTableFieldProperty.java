package org.mo.jfa.core.database;

import org.mo.com.lang.FError;
import org.mo.com.lang.FStrings;
import org.mo.web.protocol.context.IWebContext;

public class FTableFieldProperty
{

   //   public void loadProperty(IWebContext iContext,
   //                            FStringList oParameters,
   //                            FStringList oValues)
   //         throws FException{
   //      String sField = oParameters.value("field_name");
   //      oValues.setValue("name", sField.toLowerCase());
   //      oValues.setValue("data_name", sField.toUpperCase());
   //      if(FStringUtil.isEmpty(oValues.value("label"))){
   //         oValues.setValue("label", sField.toUpperCase());
   //      }
   //   }

   public void saveProperty(IWebContext iContext,
                            FStrings oParameters,
                            FStrings oValues) throws FError{
   }

}
