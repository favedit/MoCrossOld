package org.mo.com.lang.temp;

import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;

public class RMap
{
   public static void split(IAttributes map,
                            String value,
                            String splitName,
                            String splitValue){
      if(!RString.isEmpty(value)){
         String[] values = value.split(splitValue);
         if(!RString.isEmpty(splitName)){
            String[] items = null;
            for(int n = 0; n < values.length; n++){
               items = values[n].split(splitName, 2);
               if(items.length == 2){
                  map.set(items[0], items[1]);
               }
            }
         }
      }
   }
}
