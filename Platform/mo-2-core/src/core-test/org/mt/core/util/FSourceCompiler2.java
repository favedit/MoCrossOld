package org.mt.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.mo.com.io.FByteFile;
import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RLong;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.text.RWord;

//JavaCompiler a;
public class FSourceCompiler2
{
   private Class<?> _class = null;

   private final long _lastModified = 0;

   //   private FLoaderConsole _console;
   //
   //   public FSourceCompiler(FLoaderConsole console, Class clazz, long lastModified) {
   //      _console = console;
   //      _class = clazz;
   //      _lastModified = lastModified;
   //   }
   public FSourceCompiler2(String className){
      try{
         _class = Class.forName(className);
      }catch(ClassNotFoundException e){
         throw new FFatalError(e);
      }
   }

   private String _proxyName = null;

   private String _shortName = null;

   private String _packageName = null;

   public String proxyName(){
      return _proxyName;
   }

   @SuppressWarnings("resource")
   public byte[] compile(){
      String classname = _class.getName();
      String version = RLong.toHexNumber(_lastModified, 8);
      _shortName = RClass.shortName(classname) + "_" + version;
      _packageName = RClass.packageName(classname);
      _proxyName = _packageName + "." + _shortName;
      //String path = _console.workpath();
      String path = null;
      String name = RString.replaceChars(classname, '.', '/') + "_" + version;
      String jname = RFile.makeFilename(path, name + ".java");
      String cname = RFile.makeFilename(path, name + ".class");
      if(RFile.exists(cname)){
         return new FByteFile(cname).toArray();
      }
      FStringFile source = new FStringFile(jname, false);
      if(!buildClass(source)){
         return null;
      }
      source.store();
      //String[] params = new String[] { "-classpath", _console.classpath(), "-d", path, jname };
      String[] params = new String[]{"-classpath", "", "-d", path, jname};
      //int status = Main.compile(params);
      int status = params.length;
      if(status != 0){
         throw new FFatalError("Complie error:{1}", status);
      }
      return new FByteFile(cname).toArray();
   }

   public boolean buildClass(FString source){
      boolean result = false;
      source.append("package ");
      source.append(_packageName);
      source.append(";");
      source.appendLine();
      source.append("public class ");
      source.append(_shortName);
      source.append(" extends ");
      source.append(RClass.shortName(_class));
      source.append(" {");
      source.appendLine();
      Field[] fields = _class.getFields();
      for(Field field : fields){
         if(Modifier.isProtected(field.getModifiers())){
            String fieldName = field.getName();
            String fieldType = field.getType().getName();
            String methodName = RWord.formatFirstUpper(field.getName(), "link");
            source.append("public void " + methodName + "(" + fieldType + " value){");
            source.append("this." + fieldName + "=value;");
            source.append("}");
            source.appendLine();
            result = true;
         }
      }
      source.appendLine("}");
      source.appendLine();
      return result;
   }
}
