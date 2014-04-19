package org.mo.web.core.servlet.common;

import javax.servlet.http.HttpServletResponse;
import org.mo.com.lang.FFatalError;

public class FWebServletResponse
      implements
         IWebServletResponse
{

   private final HttpServletResponse _response;

   public FWebServletResponse(HttpServletResponse response){
      _response = response;
   }

   public void addHeader(String name,
                         String value){
      _response.addHeader(name, value);
   }

   public void setCharacterEncoding(String arg0){
      _response.setCharacterEncoding(arg0);
   }

   @Override
   public void setContentLength(int length){
      _response.setContentLength(length);
   }

   public void setContentType(String contentType){
      _response.setContentType(contentType);
   }

   public void write(byte[] data){
      try{
         _response.getOutputStream().write(data);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

}
