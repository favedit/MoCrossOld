package org.mo.jfa.face.monitor.objects;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.tracker.ITrackerConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FObjectsService
      implements
         IObjectsService
{

   private static Class<?>[] _filterClass = {Boolean.class, Integer.class, Long.class, Float.class, String.class};

   private static String[] _filterNames = {"boolean", "int", "long", "float"};

   private static ILogger _logger = RLogger.find(FObjectsService.class);

   @ALink
   protected ITrackerConsole _trackerConsole;

   private void buildArray(FObjectDictionary map,
                           IWebOutput output,
                           Object[] array){
      int count = array.length;
      for(int n = 0; n < count; n++){
         Object obj = array[n];
         FXmlNode node = new FXmlNode("Node");
         node.set("type", "method");
         node.set("caption", Integer.toString(n));
         node.set("child", "N");
         if(null != obj){
            Class<?> clazz = obj.getClass();
            node.set("class", clazz.getName());
            node.set("detail", obj.toString());
            if(!isFilter(clazz)){
               node.set("oid", push(map, obj));
               node.set("child", "Y");
            }
         }else{
            node.set("detail", "null");
         }
         output.config().nodes().push(node);
      }
   }

   private void buildObject(FObjectDictionary map,
                            IWebOutput output,
                            Object instance){
      @SuppressWarnings("unused") FClass<?> clazz = RClass.find(instance.getClass());
      try{
         //         for(Annotation anno : clazz.getAnnotations()){
         //            FXmlNode node = new FXmlNode("Node");
         //            node.set("type", "annotation");
         //            node.set("caption", RClass.shortName(anno.toString()));
         //            node.set("child", "N");
         //            node.set("class", anno.getClass().getName());
         //            node.set("detail", anno.toString());
         //            output.config().nodes().push(node);
         //         }
         //         for(FField field : clazz.declaredFields(false)){
         //            String type = "field";
         //            int mod = field.field().getModifiers();
         //            if(Modifier.isPrivate(mod)){
         //               type = "field-private";
         //            }else if(Modifier.isProtected(mod)){
         //               type = "field-protected";
         //            }
         //            FXmlNode node = new FXmlNode("Node");
         //            node.set("type", type);
         //            node.set("caption", field.field().getName());
         //            node.set("child", "N");
         //            node.set("class", field.field().getType().getName());
         //            Class<?> fldCls = field.field().getType();
         //            String fldClsName = fldCls.getName();
         //            if(fldCls.isArray()){
         //               node.set("class", fldCls.getComponentType().getName() + "[]");
         //            }else{
         //               node.set("class", fldClsName);
         //            }
         //            if(Modifier.isPublic(mod)){
         //               try{
         //                  Object value = field.field().get(instance);
         //                  if(null != value){
         //                     node.set("detail", value.toString());
         //                     if(!isFilter(fldCls)){
         //                        node.set("oid", push(map, value));
         //                        node.set("child", "Y");
         //                     }
         //                  }
         //               }catch(Throwable t){
         //                  node.set("detail", t.getMessage());
         //               }
         //            }
         //            output.config().nodes().push(node);
         //         }
         //         // Methods
         //         FMethod[] methods = clazz.allDeclaredMethods();
         //         for(Method method : methods){
         //            boolean able = false;
         //            String type = "method";
         //            String name = method.getName();
         //            int mod = method.getModifiers();
         //            if(Modifier.isPrivate(mod)){
         //               type = "method-private";
         //            }else if(Modifier.isProtected(mod)){
         //               type = "method-protected";
         //            }
         //            if(name.startsWith(FComponentDescriptor.FAopComponentDescriptor)
         //                  || name.startsWith(FComponentDescriptor.FAopComponentDescriptor)){
         //               type = "method-protected";
         //               able = true;
         //            }else if(name.startsWith(FComponentDescriptor.FAopComponentDescriptor)){
         //               type = "method-protected";
         //            }else if(name.startsWith(FComponentDescriptor.FAopComponentDescriptor)){
         //               type = "method-protected";
         //            }
         //            // Build node
         //            FXmlNode node = new FXmlNode("Node");
         //            node.set("type", type);
         //            node.set("caption", name);
         //            node.set("child", "N");
         //            node.set("detail", RMethod.shortName(method));
         //            node.set("oname", RMethod.detailName(method));
         //
         //            Class<?> returnClass = method.getReturnType();
         //            Class<?>[] types = method.getParameterTypes();
         //            if(able && types.length == 0 && !"void".equals(returnClass.getName())){
         //               try{
         //                  Object value = method.invoke(instance);
         //                  if(null != value){
         //                     node.set("value", value.toString());
         //                     if(!isFilter(returnClass)){
         //                        node.set("oid", push(map, value));
         //                        node.set("child", "Y");
         //                     }
         //                  }
         //               }catch(Throwable t){
         //                  node.set("value", t.getMessage());
         //               }
         //            }
         //            output.config().nodes().push(node);
         //         }
      }catch(Throwable t){
         throw new FFatalError(t);
      }
   }

   private boolean isFilter(Class<?> clazz){
      if(null != clazz){
         String name = clazz.getName();
         for(String cls : _filterNames){
            if(name == cls){
               return true;
            }
         }
         for(Class<?> cls : _filterClass){
            if(clazz == cls){
               return true;
            }
         }
      }
      return false;
   }

   public void process(IWebContext context,
                       FObjectsPage page,
                       IWebInput input,
                       IWebOutput output){
      // Find instance
      Object instance = null;
      FXmlNode search = input.config().node("Node");
      FObjectDictionary map = page.getObjects();
      if(null != search){
         String oid = search.get("oid");
         if(RString.isNotEmpty(oid)){
            instance = map.get(oid);
         }
      }
      if(null == instance){
         instance = page.getObject();
      }
      _logger.debug(this, "process", "Find monitor object {0}", instance);
      // Build nodes
      if(null != instance){
         Class<?> clazz = instance.getClass();
         if(clazz.isArray()){
            buildArray(map, output, (Object[])instance);
         }else{
            buildObject(map, output, instance);
            output.config().nodes().sortByAttribute("caption");
         }
      }
   }

   private String push(FObjectDictionary map,
                       Object instance){
      String hash = "";
      if(null != instance){
         hash = Integer.toHexString(instance.hashCode()).toLowerCase();
         map.set(hash, instance);
      }
      return hash;
   }

}
