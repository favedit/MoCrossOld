package org.mo.web.protocol.common;

import java.io.Writer;
import javax.servlet.http.HttpServletResponse;
import org.mo.com.lang.FError;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;

public class RWebResponse
{

   public static void sendString(HttpServletResponse oResponse,
                                 FString sValue) throws FError{
      try{
         Writer oWriter = oResponse.getWriter();
         oWriter.write(sValue.memory(), 0, sValue.length());
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
