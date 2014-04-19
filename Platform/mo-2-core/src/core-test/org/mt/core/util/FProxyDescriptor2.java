package org.mt.core.util;

import org.mo.com.lang.reflect.FClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.descriptor.MAopDescriptor;

public class FProxyDescriptor2
      extends MAopDescriptor
{
   public class FComponentLoader
         extends ClassLoader
   {
      //private static ILogger _logger = RLogger.find(FComponentLoader.class);
      private Class<?> _class;

      //private IDescriptor _descriptor;
      //private FComponentDescriptor _descriptor = new FComponentDescriptor();
      //private FSetterDescriptor _setterDescriptor = new FSetterDescriptor();
      public FComponentLoader(Object parent){
         super(parent.getClass().getClassLoader());
      }

      public Class<?> buildClass(String name,
                                 byte[] data){
         if(null == _class){
            _class = defineClass(name, data, 0, data.length);
         }
         return _class;
      }
      //   public Class<?> clazz(){
      //      return _class;
      //   }
      //
      //   public FComponentDescriptor descriptor(){
      //      return _descriptor;
      //   }
      //
      //   public boolean initialize(FComponentConfig config){
      //      String className = config.className();
      //      if(RString.isEmpty(className)){
      //         throw new FFatalException("Class name is null.\n{1}", config.dump());
      //      }
      //      if(RClass.hasClass(className)){
      //         try{
      //            //if(_config != null && _config.hasNode("Proxy")){
      //            //buildProxyClass(name);
      //            //}else{
      //            buildClass(config);
      //            //_setterDescriptor = new FSetterDescriptor(_class);
      //            //}
      //            return true;
      //         }catch(Exception e){
      //            throw new FFatalException(e, "build class error. {1}", config.dump());
      //         }
      //      }
      //      return false;
      //   }
      //
      //   public FSetterDescriptor setterDescriptor(){
      //      return _setterDescriptor;
      //   }
   }

   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FProxyDescriptor2.class);

   @SuppressWarnings("unused")
   private static String FIELD_CLASS = "__class";

   //   protected void buildProxyClass(String name){
   //      String face = _config.attribute("face");
   //      FClass faceClass = new FClass(face);
   //
   //      FClass clazz = new FClass(name, true);
   //      clazz.addInterface(faceClass);
   //
   //      // Add field [ Class __class ] 
   //      FField thisClass = new FField(clazz, Class.class, FIELD_CLASS);
   //      thisClass.setModifiers(Modifier.PROTECTED);
   //      clazz.addField(thisClass);
   //
   //      // Add Constructor
   //      FConstructor cons = new FConstructor(clazz, null);
   //      cons.setBody(FIELD_CLASS + "=" + face + ".class;");
   //      clazz.addConstructor(cons);
   //
   //      // Filter method
   //      FMethod[] faceMethods = faceClass.allMethods();
   //      FObjects<FMethod> filtersMethods = new FObjects<FMethod>(FMethod.class);
   //      for(FMethod method : faceMethods){
   //         if(!method.className().startsWith("java")){
   //            filtersMethods.push(method);
   //         }
   //      }
   //
   //      FObjects<FMethod> proxyMethods = new FObjects<FMethod>(FMethod.class);
   //      FNameMap<FMethod> proxyedMethods = new FNameMap<FMethod>(FMethod.class);
   //      for(FXmlNode proxyNode : _config.nodes("Proxy")){
   //         String proxyField = FFieldUtil.format("__", face);
   //         String path = _configConsole.parse(proxyNode.attribute("source"));
   //         String filename = FClassUtil.classFilename(path, face);
   //         FJavaClass javaClass = null;
   //         if(FFileUtil.exists(filename)){
   //            FJavaParserWorker parser = new FJavaParserWorker(filename);
   //            parser.parse();
   //            javaClass = parser.result();
   //         }
   //
   //         // Add proxy field/method
   //         String proxyFace = proxyNode.attribute("face");
   //         _descriptor.push(buildProxyField(clazz, proxyFace, proxyField));
   //         String ptyMethod = buildProxyProperty(clazz, proxyFace, proxyNode.nodes("Property"));
   //
   //         // Proxy config
   //         String proxyType = null;
   //         FXmlNode proxyConfig = _configConsole.node("Proxy", proxyFace);
   //         if(proxyConfig != null){
   //            proxyType = proxyConfig.attribute("type");
   //         }
   //         proxyMethods.clear();
   //         if("class".equals(proxyType)){
   //            proxyMethods.append(filtersMethods);
   //         }else{
   //            for(FXmlNode methodNode : proxyNode.nodes("Method")){
   //               String filter = methodNode.attribute("proxy");
   //               String match = FStringUtil.replace(filter, "*", "\\w*");
   //               for(FMethod method : filtersMethods){
   //                  if(method.name().matches(match)){
   //                     proxyMethods.push(method);
   //                  }
   //               }
   //            }
   //         }
   //
   //         // Add proxy methods
   //         String methodId;
   //         for(FMethod method : proxyMethods){
   //            methodId = Integer.toString(method.hashCode());
   //            if(!proxyedMethods.containsName(methodId)){
   //               buildProxyMethod(clazz, javaClass, method, face, proxyField, ptyMethod);
   //               proxyedMethods.setValue(methodId, method);
   //            }
   //         }
   //      }
   //
   //      // Build class
   //      byte[] data = clazz.toBytecode();
   //      _class = defineClass(name, data, 0, data.length);
   //
   //      _descriptor.linkClass(_class);
   //   }
   //
   //   protected FNativeLink buildProxyField(FClass clazz,
   //                                         String face,
   //                                         String fieldName){
   //      // Add field [Type _{proxyName}] 
   //      FField field = new FField(clazz, face, fieldName);
   //      field.setModifiers(Modifier.PROTECTED);
   //      clazz.addField(field);
   //
   //      // Add link method
   //      return buildMethodLink(clazz, field);
   //   }
   //protected void buildProxyMethod(FClass<?> clazz, FJavaClass javaClass, FMethod method, String face, String proxyField, String ptyMethod){
   //      String methodName = method.name();
   //      String returnType = method.returnClassname();
   //      FClass<?>[] types = method.parameterTypes();
   //      int count = types.length;
   //      // Add method field
   //      String fieldName = "__" + methodName + "Method";
   //      FField field = new FField(clazz, Method.class, fieldName);
   //      field.setModifiers(Modifier.PROTECTED);
   //      clazz.addField(field);
   //      // Build type source
   //      FString typeStr = new FString();
   //      if(count > 0){
   //         typeStr.append("Class[] types = new Class[]{");
   //         for(int tp = 0; tp < count; tp++){
   //            if(tp != 0){
   //               typeStr.append(",");
   //            }
   //            typeStr.append(types[tp].name());
   //            typeStr.append(".class");
   //         }
   //         typeStr.append("};\n");
   //      }else{
   //         typeStr.append("Class[] types = new Class[0];\n");
   //      }
   //
   //      // Build param names
   //      FString namesStr = new FString();
   //      namesStr.append("String[] names = null;\n");
   //      if(javaClass != null){
   //         FJavaMethod javaMethod = javaClass.methods().get(methodName);
   //         if(javaMethod != null){
   //            namesStr.clear();
   //            namesStr.append("String[] names = new String[]{");
   //            int ncount = javaMethod.parameters().count();
   //            for(int n = 0; n < ncount; n++){
   //               if(n != 0){
   //                  namesStr.append(", ");
   //               }
   //               namesStr.append('\"');
   //               namesStr.append(javaMethod.parameters().value(n).name());
   //               namesStr.append('\"');
   //            }
   //            namesStr.append("};\n");
   //         }
   //      }
   //
   //      // Build param values
   //      FString valuesStr = new FString();
   //      if(count > 0){
   //         valuesStr.append("Object[] values = new Object[]{");
   //         for(int tp = 0; tp < count; tp++){
   //            if(tp != 0){
   //               valuesStr.append(", ");
   //            }
   //            valuesStr.append("p" + tp);
   //         }
   //         valuesStr.append("};\n");
   //      }else{
   //         valuesStr.append("Object[] values = new Object[0];\n");
   //      }
   //
   //      // Build source
   //      FString source = new FString();
   //      source.append("public ");
   //      source.append(returnType);
   //      source.append(" ");
   //      source.append(methodName);
   //      source.append("(");
   //      if(count > 0){
   //         for(int tp = 0; tp < count; tp++){
   //            if(tp != 0){
   //               source.append(",");
   //            }
   //            source.append(types[tp].name());
   //            source.append(" p");
   //            source.appendInt(tp);
   //         }
   //      }
   //      source.append("){\n");
   //      source.append("   if(" + fieldName + " == null){\n");
   //      source.append("      " + typeStr);
   //      source.append("      " + fieldName + " = " + face + ".class.getMethod(\"" + methodName + "\", types);\n");
   //      source.append("   }\n");
   //      source.append("   " + namesStr);
   //      source.append("   " + valuesStr);
   //      if(!returnType.equals("void")){
   //         source.append("   return (");
   //         source.append(returnType);
   //         source.append(")");
   //      }
   //      source.append("this.");
   //      source.append(proxyField);
   //      source.append(".proxy(" + FIELD_CLASS + ", " + fieldName + ", " + ptyMethod + "(), names, values);");
   //      source.append("\n}");
   //
   //      // Add method
   //      clazz.addMethod(source);
   //      if(_logger.debugAble()){
   //         _logger.debug(this, "buildProxyMethod", "Build proxy method: ({1}).{2}", clazz.name(), methodName);
   //      }
   //   }
   protected String buildProxyProperty(FClass<?> clazz,
                                       String face,
                                       FXmlNodes ptyNodes){
      //      String fieldName = RField.format("__", face) + "_pty";
      //      String attrClass = FAttributes.class.getName();
      //      String attrMethod = RField.format("__", face) + "_get";
      //      // Add attr field [Type _{proxyName}Pty] 
      //      FField field = new FField(clazz, FAttributes.class, fieldName);
      //      field.setModifiers(Modifier.PROTECTED);
      //      clazz.addField(field);
      //      // Add attr method [Type _{proxyName}Getter]
      //      FString source = new FString();
      //      source.append("protected " + attrClass + " " + attrMethod + "(){\n");
      //      source.append("   if(" + fieldName + " == null){\n");
      //      source.append("      " + fieldName + " = new " + attrClass + "();\n");
      //      for(FXmlNode ptyNode : ptyNodes){
      //         source.append("      " + fieldName + ".setValue(\"");
      //         source.append(ptyNode.get("name") + "\",\"" + ptyNode.text());
      //         source.append("\");\n");
      //      }
      //      source.append("   }\n   return ");
      //      source.append(fieldName);
      //      source.append(";\n}");
      //
      //      clazz.addMethod(source);
      //      return attrMethod;
      return null;
   }

   @Override
   public <V> V newInstance(){
      // TODO Auto-generated method stub
      return null;
   }
}
