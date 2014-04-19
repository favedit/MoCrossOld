package org.mo.com.lang.reflect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>类加载器。</T>
//============================================================
public class FClassLoader
      extends ClassLoader
{
   // 类路径集合
   protected List<String> _paths = new ArrayList<String>();

   //============================================================
   // <T>构造类加载器。</T>
   //============================================================
   public FClassLoader(){
   }

   //============================================================
   // <T>构造类加载器。</T>
   //
   // @param parent 父加载器
   //============================================================
   public FClassLoader(ClassLoader parent){
      super(parent);
   }

   //============================================================
   // <T>加载数据类数据。</T>
   //
   // @param className 类名
   // @return 字节数组
   //============================================================
   protected byte[] loadClassData(String className){
      byte[] data = null;
      for(String path : _paths){
         String fileName = path + File.separator + className.replace('.', File.separatorChar) + ".class";
         File file = new File(fileName);
         if(file.exists()){
            try(FileInputStream fileStream = new FileInputStream(file)){
               try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){
                  while(true){
                     int read = fileStream.read();
                     if(-1 == read){
                        break;
                     }
                     stream.write(read);
                  }
                  data = stream.toByteArray();
               }
            }catch(IOException e){
               throw new FFatalError(e);
            }
         }
      }
      return data;
   }

   //============================================================
   // <T>加载类对象。</T>
   //
   // @param className 类名
   //============================================================
   @Override
   @SuppressWarnings({"unchecked", "rawtypes"})
   public Class findClass(String name) throws ClassNotFoundException{
      byte[] data = loadClassData(name);
      if(null == data){
         return super.findClass(name);
      }
      return defineClass(name, data, 0, data.length);
   }
}
