package org.mo.web.core.service.common;

import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;

//============================================================
// <T>服务对象。</T>
//============================================================
public class FWebService
{
   private String _face;

   private FClass<?> _faceClass;

   private String _name;

   public boolean construct(XAopService xservice){
      _name = xservice.name();
      _face = xservice.face();
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
