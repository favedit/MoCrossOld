package org.mo.web.core.action.common;

public class FWebAction
{

   private String _face;

   private Class<?> _faceClass;

   @SuppressWarnings("unused")
   private XAopAction _config;

   private String _uri;

   public String face(){
      return _face;
   }

   public Class<?> faceClass(){
      return _faceClass;
   }

   public void setFace(String face){
      _face = face;
   }

   public void setFaceClass(Class<?> clazz){
      _faceClass = clazz;
   }

   public void setUri(String uri){
      _uri = uri;
   }

   public String uri(){
      return _uri;
   }

}
