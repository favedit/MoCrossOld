package org.mo.core.persistent.loader;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;

public class FPersistentLoader
      extends ClassLoader
{
   //private static ILogger _logger = new FLogger(FPersistentLoader.class);
   @SuppressWarnings("rawtypes")
   private final FDictionary<Class> _classes = new FDictionary<Class>(Class.class);

   private final FDictionary<FXmlNode> _configs = new FDictionary<FXmlNode>(FXmlNode.class);

   private final FDictionary<FPersistentDescriptor> _descriptors = new FDictionary<FPersistentDescriptor>(FPersistentDescriptor.class);

   public FPersistentLoader(ClassLoader parent){
      super(parent);
   }

   private void buildInnerFlag(FPersistentDescriptor dsp){
      //      FClass<?> clazz = dsp.clazz();
      //      // Add field
      //      clazz.addField(boolean.class, "__linked");
      //      // Add method
      //      FString source = new FString();
      //      source.appendLine("public void __link(){");
      //      source.appendLine("   if(!__linked){");
      //      source.appendLine("      __linked = true;");
      //      source.appendLine("   }");
      //      source.appendLine("}");
      //      clazz.addMethod(source);
   }

   private void buildInnerMethod(FPersistentDescriptor dsp){
      //      FClass<?> clazz = dsp.clazz();
      //      FDictionary<FPersistentMethod> methods = dsp.methods();
      //      // Build pack
      //      {
      //         FString source = new FString();
      //         source.append("public org.mo.com.lang.FObjectPacker __pack(){\n");
      //         source.append("   org.mo.com.lang.FObjectPacker packer = new org.mo.com.lang.FObjectPacker();\n");
      //         FPersistentMethod method;
      //         int count = methods.count();
      //         for(int n = 0; n < count; n++){
      //            method = methods.value(n);
      //            EType type = method.type();
      //            if(type == EType.Id || type == EType.Foreign || type == EType.Name || type == EType.Field){
      //               source.append("   packer.setValue(\"");
      //               source.append(method.alias());
      //               source.append("\", ");
      //               source.append(method.fieldName());
      //               source.append(");\n");
      //            }
      //         }
      //         source.append("   return packer;\n");
      //         source.append("}\n");
      //         clazz.addMethod(source);
      //      }
      //
      //      // Unpack
      //      {
      //         FString source = new FString();
      //         source.append("public void __unpack(org.mo.com.lang.FObjectPacker packer){\n");
      //         FPersistentMethod method;
      //         int count = methods.count();
      //         for(int n = 0; n < count; n++){
      //            method = methods.value(n);
      //            EType type = method.type();
      //            if(type == EType.Id || type == EType.Foreign || type == EType.Name || type == EType.Field){
      //               source.append("   ");
      //               source.append(method.fieldName());
      //               source.append(" = (");
      //               source.append(method.className());
      //               source.append(")packer.value(\"");
      //               source.append(method.alias());
      //               source.append("\");\n");
      //            }
      //         }
      //         source.append("   return packer;\n");
      //         source.append("}\n");
      //         clazz.addMethod(source);
      //      }
   }

   private void buildModifyMethod(FPersistentDescriptor dsp,
                                  FPersistentMethod method){
      //      FClass<?> clazz = dsp.clazz();
      //      FMethod getter = method.getter();
      //      if(getter != null){
      //         String nwName = getter.name() + "$$Proxy";
      //         FMethod nwMethod = getter.copy(nwName);
      //         clazz.addMethod(nwMethod);
      //
      //         // Modify method
      //         FString source = new FString();
      //         source.append("{return " + nwName + "($$);}");
      //         getter.setBody(source);
      //      } 
   }

   private void buildModifyMethods(FPersistentDescriptor dsp){
      FDictionary<FPersistentMethod> methods = dsp.methods();
      int count = methods.count();
      for(int n = 0; n < count; n++){
         buildModifyMethod(dsp, methods.value(n));
      }
   }

   public FDictionary<FXmlNode> configs(){
      return _configs;
   }

   public FPersistentDescriptor descriptor(Class<?> clazz){
      return _descriptors.get(clazz.getName());
   }

   public FPersistentDescriptor descriptor(String name){
      return _descriptors.get(name);
   }

   @Override
   protected synchronized Class<?> loadClass(String name,
                                             boolean resolve) throws ClassNotFoundException{
      if(_configs.contains(name)){
         Class<?> clazz = _classes.get(name);
         if(clazz == null){
            try{
               FClass<?> cls = RClass.find(name);
               FPersistentDescriptor dsp = new FPersistentDescriptor(cls);
               _descriptors.set(name, dsp);
               buildInnerFlag(dsp);
               buildInnerMethod(dsp);
               buildModifyMethods(dsp);
               clazz = cls.toClass();
               dsp.linkClass(clazz);
            }catch(Exception e){
               throw new FFatalError(e);
            }
         }
         return clazz;
      }
      return super.loadClass(name, resolve);
   }
}
