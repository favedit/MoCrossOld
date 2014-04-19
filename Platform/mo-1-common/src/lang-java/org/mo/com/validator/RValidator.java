package org.mo.com.validator;

import org.mo.com.lang.FValidError;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

public class RValidator
{
   private static IResource _resource = RResource.find(RValidator.class);

   public static void checkNull(Object object,
                                String display){
      if(null == object){
         throw new FValidError(_resource.findDisplay("checkNull"), display);
      }
   }
}
