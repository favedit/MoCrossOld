package org.mo.web.tag.common;

import org.mo.com.lang.FString;
import org.mo.web.protocol.context.FWebContext;

public class RTag
{

   public static void makeUri(FString source,
                              FWebContext context,
                              String src){
      if(null != src && src.charAt(0) == '/'){
         source.append(context.contextPath());
         source.append(context.parseString(src));
      }else{
         source.append(context.parseString(src));
      }
   }

}
