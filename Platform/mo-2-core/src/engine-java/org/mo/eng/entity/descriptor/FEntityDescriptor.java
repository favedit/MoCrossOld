package org.mo.eng.entity.descriptor;

import java.lang.reflect.Method;
import java.util.Date;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.face.AName;
import org.mo.com.lang.reflect.FAnnotation;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.FField;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.eng.entity.IEntity;

public class FEntityDescriptor
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FEntityDescriptor.class);

   private static String PACK_METHOD = "__pack";

   private static String UNPACK_METHOD = "__unpack";

   private FClass<?> _class;

   @SuppressWarnings("unused")
   private FEntityLoader _loader;

   private Class<?> _nativeClass;

   private Method _packMethod;

   private Method _unpackMethod;

   public FEntityDescriptor(FEntityLoader loader,
                            String className){
      _loader = loader;
      loadClass(className);
   }

   protected void buildPack(FClass<?> clsEntity,
                            FClass<?> clsInfo){
      FString source = new FString();
      source.append("public void ", PACK_METHOD, "(", FRow.class.getName(), " row){\n");
      for(FField field : clsInfo.allDeclaredFields()){
         FAnnotation anno = field.findAnnotation(AName.class);
         if(null != anno){
            AName aname = (AName)anno.annotation();
            String name = aname.value().toLowerCase();
            String typeName = field.type().name();
            if(String.class.getName().equals(typeName)){
               source.append("row.setString(\"", name, "\",");
            }else if(Date.class.getName().equals(typeName)){
               source.append("row.setDate(\"", name, "\",");
            }else if(Integer.class.getName().equals(typeName)){
               source.append("row.setInteger(\"", name, "\",");
            }
            source.append(field.name(), ");\n");
         }
      }
      source.append("}");
      //      clsEntity.addMethod(source);
      //System.out.println(source);
   }

   protected void buildToString(FClass<?> clsEntity,
                                FClass<?> clsInfo){
      FString source = new FString();
      source.append("public String toString(){\n");
      source.append(FRow.class.getName(), " row = new ", FRow.class.getName(), "();\n");
      source.append("__pack(row);\n");
      source.append("return getClass().getName() + \" [\" + row + \"]\";\n");
      source.append("}");
      //      clsEntity.addMethod(source);
      //System.out.println(source);
   }

   protected void buildUnpack(FClass<?> clsEntity,
                              FClass<?> clsInfo){
      FString source = new FString();
      source.append("public void ", UNPACK_METHOD, "(", FRow.class.getName(), " row){\n");
      for(FField field : clsInfo.allDeclaredFields()){
         FAnnotation anno = field.findAnnotation(AName.class);
         if(null != anno){
            AName aname = (AName)anno.annotation();
            String name = aname.value().toLowerCase();
            source.append(field.name(), "=");
            String typeName = field.type().name();
            if(String.class.getName().equals(typeName)){
               source.append("row.getString(\"", name, "\");\n");
            }else if(Date.class.getName().equals(typeName)){
               source.append("row.getDate(\"", name, "\");\n");
            }else if(Integer.class.getName().equals(typeName)){
               source.append("row.getInteger(\"", name, "\");\n");
            }
         }
      }
      source.append("}");
      //      clsEntity.addMethod(source);
      //System.out.println(source);
   }

   public FClass<?> clazz(){
      return _class;
   }

   protected void linkClass(Class<?> clazz){
      for(Method method : clazz.getMethods()){
         String name = method.getName();
         if(PACK_METHOD.equals(name)){
            _packMethod = method;
         }else if(UNPACK_METHOD.equals(name)){
            _unpackMethod = method;
         }
      }
   }

   protected void loadClass(String className){
      //      String clsName = className + "$$Entity";
      //      FClass<?> clsInfo = RClass.find(className);
      //      FClass<?> clsEntity = RClass.createClass(clsName, clsInfo);
      //      //      clsEntity.addInterface(IEntity.class);
      //      buildPack(clsEntity, clsInfo);
      //      buildUnpack(clsEntity, clsInfo);
      //      buildToString(clsEntity, clsInfo);
      //
      //      // Build class
      //      //      byte[] data = clsEntity.toBytecode();
      //      //      _nativeClass = _loader.buildClass(clsName, data);
      //
      //      _logger.debug(this, "loadClass", "Build entity class (name={0})", _nativeClass.getName());
      //      linkClass(_nativeClass);
   }

   public Class<?> nativeClass(){
      return _nativeClass;
   }

   public IEntity newInstance(){
      try{
         return (IEntity)_nativeClass.newInstance();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void pack(Object entity,
                    FRow row){
      try{
         _packMethod.invoke(entity, row);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void setLoader(FEntityLoader loader){
      _loader = loader;
   }

   public void unpack(Object entity,
                      FRow row){
      try{
         _unpackMethod.invoke(entity, row);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
