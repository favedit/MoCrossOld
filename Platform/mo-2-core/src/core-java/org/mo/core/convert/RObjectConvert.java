package org.mo.core.convert;

import org.mo.com.lang.FString;
import org.mo.com.lang.IObjects;
import org.mo.com.lang.reflect.FPropertyDescriptor;
import org.mo.com.lang.reflect.RDescriptor;

public class RObjectConvert
{

   //private static com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();

   public static <T> T toClass(Object item,
                               Class<?> target){
      FString source = new FString();
      source.append("package org.mo.com.face;");
      source.append("public class FTest{}");
      // File file = File.createTempFile("RunTime",".java",new File(System.getProperty("user.dir"))); 
      //String[] arg = new String[] { "-d", System.getProperty("user.dir"), "" };
      //javac.compile();
      return null;
   }

   @SuppressWarnings("rawtypes")
   public static Object[] toObjects(Object item){
      Object[] objects = null;
      if(null != item){
         if(item.getClass().isArray()){
            objects = (Object[])item;
         }else if(item instanceof IObjects){
            objects = ((IObjects)item).toObjects();
         }else{
            FPropertyDescriptor ptyDsp = RDescriptor.findProperty(item);
            if(null != ptyDsp){
               objects = ptyDsp.gets(item);
            }
         }
      }
      return objects;
   }

}
