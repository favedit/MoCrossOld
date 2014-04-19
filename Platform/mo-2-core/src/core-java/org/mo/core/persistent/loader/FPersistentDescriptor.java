package org.mo.core.persistent.loader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.face.AName;
import org.mo.com.lang.reflect.FAnnotation;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.FField;
import org.mo.com.lang.reflect.FMethod;
import org.mo.core.persistent.face.AType;
import org.mo.core.persistent.face.EType;

public class FPersistentDescriptor
{
   private final FClass<?> _class;

   private final FDictionary<FPersistentMethod> _methods = new FDictionary<FPersistentMethod>(FPersistentMethod.class);

   private Method _nameGetMethod;

   private Method _nameSetMethod;

   private Method _packMethod;

   private Method _unpackMethod;

   public FPersistentDescriptor(FClass<?> clazz){
      _class = clazz;
      try{
         buildMethods();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   protected void buildMethods() throws Exception{
      // Build Fields
      String fieldName;
      FField[] fields = _class.allDeclaredFields();
      FDictionary<FField> fieldMap = new FDictionary<FField>(FField.class);
      for(FField field : fields){
         fieldName = field.name().toLowerCase();
         if(fieldName.startsWith("_")){
            fieldName = fieldName.substring(1);
         }
         fieldMap.set(fieldName.toLowerCase(), field);
      }
      // Build Methods
      String methodName;
      FClass<?>[] parameterTypes;
      FMethod[] methods = _class.allMethods();
      FDictionary<FMethod> methodMap = new FDictionary<FMethod>(FMethod.class);
      for(FMethod method : methods){
         methodName = method.name().toLowerCase();
         parameterTypes = method.parameterTypes();
         if(methodName.startsWith("set") && parameterTypes.length == 1){
            methodMap.set(methodName, method);
         }else if(parameterTypes.length == 0){
            methodMap.set(methodName, method);
         }
      }
      // Build Property
      FField field;
      FMethod getter;
      FMethod setter;
      FPersistentMethod pstMethod;
      EType type;
      EFieldType dataType;
      String alias;
      for(INamePair<FField> pair : fieldMap){
         fieldName = pair.name();
         field = pair.value();
         type = null;
         dataType = null;
         alias = null;
         for(FAnnotation annotation : field.annotations()){
            Annotation anno = annotation.annotation();
            if(anno instanceof AType){
               type = ((AType)anno).value();
               //}else if(anno instanceof ADataType){
               //   dataType = ((ADataType) anno).value();
            }else if(anno instanceof AName){
               alias = ((AName)anno).value();
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
            // Create property method
            pstMethod = new FPersistentMethod();
            pstMethod.setName(fieldName);
            pstMethod.setType(type);
            pstMethod.setDatatype(dataType);
            pstMethod.setAlias(alias);
            pstMethod.setField(field);
            pstMethod.setClassName(field.nativeObject().getType().getName());
            pstMethod.setFieldName(field.name());
            pstMethod.setGetter(getter);
            pstMethod.setSetter(setter);
            _methods.set(fieldName, pstMethod);
         }
      }
   }

   public FClass<?> clazz(){
      return _class;
   }

   public void linkClass(Class<?> clazz){
      String methodName;
      for(Method method : clazz.getMethods()){
         methodName = method.getName();
         if(methodName.equals("__pack")){
            _packMethod = method;
         }else if(methodName.equals("__unpack")){
            _unpackMethod = method;
         }
      }
   }

   public FDictionary<FPersistentMethod> methods(){
      return _methods;
   }

   public String name(Object item){
      try{
         return (String)_nameGetMethod.invoke(item);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //   public FObjectPacker pack(Object item) {
   //      try {
   //         return (FObjectPacker) _packMethod.invoke(item);
   //      } catch (Exception e) {
   //         throw new FFatalException(e);
   //      }
   //   }
   //   public void unpack(Object item, FObjectPacker packer) {
   //      try {
   //         _unpackMethod.invoke(item, packer);
   //      } catch (Exception e) {
   //         throw new FFatalException(e);
   //      }
   //   }
   public Method nameGetMethod(){
      return _nameGetMethod;
   }

   public Method nameSetMethod(){
      return _nameSetMethod;
   }

   public Method packMethod(){
      return _packMethod;
   }

   public void setName(Object item,
                       String name){
      try{
         _nameSetMethod.invoke(item, name);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public Method unpackMethod(){
      return _unpackMethod;
   }
}
