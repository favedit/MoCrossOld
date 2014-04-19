package org.mo.core.persistent.loader;

import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.face.AName;
import org.mo.core.persistent.face.AType;
import org.mo.core.persistent.face.EType;

public class FPersistentCtDescriptor
{
   private final CtClass _class;

   private final FDictionary<FPersistentCtMethod> _methods = new FDictionary<FPersistentCtMethod>(FPersistentCtMethod.class);

   public FPersistentCtDescriptor(CtClass clazz){
      _class = clazz;
      try{
         buildMethods();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   protected void buildMethods() throws Exception{
      String fieldName;
      CtField[] fields = _class.getDeclaredFields();
      FDictionary<CtField> fieldMap = new FDictionary<CtField>(CtField.class);
      for(CtField field : fields){
         fieldName = field.getName().toLowerCase();
         if(fieldName.startsWith("_")){
            fieldName = fieldName.substring(1);
         }
         fieldMap.set(fieldName.toLowerCase(), field);
      }
      String methodName;
      CtClass[] parameterTypes;
      CtMethod[] methods = _class.getMethods();
      FDictionary<CtMethod> methodMap = new FDictionary<CtMethod>(CtMethod.class);
      for(CtMethod method : methods){
         methodName = method.getName().toLowerCase();
         parameterTypes = method.getParameterTypes();
         if(methodName.startsWith("set") && parameterTypes.length == 1){
            methodMap.set(methodName, method);
         }else if(parameterTypes.length == 0){
            methodMap.set(methodName, method);
         }
      }
      CtField field;
      CtMethod getter;
      @SuppressWarnings("unused") CtMethod setter;
      FPersistentCtMethod pstMethod;
      EType type;
      EFieldType dataType;
      String alias;
      for(INamePair<CtField> pair : fieldMap){
         fieldName = pair.name();
         field = pair.value();
         type = null;
         dataType = null;
         alias = null;
         for(Object annotation : field.getAnnotations()){
            if(annotation instanceof AType){
               type = ((AType)annotation).value();
               //}else if(annotation instanceof ADataType){
               //dataType = ((ADataType) annotation).value();
            }else if(annotation instanceof AName){
               alias = ((AName)annotation).value();
            }
         }
         if(type != null){
            getter = methodMap.get("get" + fieldName);
            if(getter == null){
               getter = methodMap.get(fieldName);
            }
            if(getter == null){
               continue;
            }
            setter = methodMap.get("set" + fieldName);
            if(alias == null){
               alias = fieldName;
            }
            // Create property method
            pstMethod = new FPersistentCtMethod();
            pstMethod.setName(fieldName);
            pstMethod.setType(type);
            pstMethod.setDatatype(dataType);
            pstMethod.setAlias(alias);
            //pstMethod.setField(field);
            pstMethod.setFieldName(field.getName());
            //pstMethod.setGetter(getter);
            //pstMethod.setSetter(setter);
            _methods.set(fieldName, pstMethod);
         }
      }
   }

   public FDictionary<FPersistentCtMethod> methods(){
      return _methods;
   }
}
