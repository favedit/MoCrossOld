package org.mo.com.validator;

import org.mo.com.lang.FValidError;
import org.mo.com.lang.RString;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

public class RStringValidator
{
   private static IResource _resource = RResource.find(RStringValidator.class);

   public static void checkEmpty(String value,
                                 String display){
      if(RString.isBlank(value)){
         throw new FValidError(_resource.findDisplay("checkEmpty"), display);
      }
   }
}
