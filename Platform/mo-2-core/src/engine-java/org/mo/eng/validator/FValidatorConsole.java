package org.mo.eng.validator;

import org.mo.com.console.FConsole;
import org.mo.com.lang.reflect.RClass;

//============================================================
// <T>校验控制台。</T>
//============================================================
public class FValidatorConsole
      extends FConsole
      implements
         IValidatorConsole
{
   // 校验器集合
   protected FValidators _validators = new FValidators();

   //============================================================
   // <T>根据类对象获得一个校验器。</T>
   //
   // @param clazz 类对象
   // @return 校验器
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public <V extends IValidator> V find(Class<V> clazz){
      String name = clazz.getName();
      IValidator validator = _validators.find(name);
      if(null == validator){
         validator = RClass.newInstance(clazz);
         _validators.set(name, validator);
      }
      return (V)validator;
   }
}
