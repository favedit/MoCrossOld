package org.mo.web.core.servlet.common;

import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;

public class FWebServlet
{

   private String _face;

   private FClass<?> _faceClass;

   private String _name;

   public boolean construct(XAopServlet xservlet){
      _name = xservlet.name();
      _face = xservlet.face();
      return true;
   }

   public String face(){
      return _face;
   }

   public FClass<?> faceClass(){
      if(null == _faceClass){
         _faceClass = RClass.find(_face);
      }
      return _faceClass;
   }

   public String name(){
      return _name;
   }

}
