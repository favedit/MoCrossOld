package org.mo.core.parser.java;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;

public class FJavaParserWorker
{
   private final FString _source;

   private final FJavaClass _class = new FJavaClass();

   private FJavaMethod _method;

   private FJavaParameter _parameter;

   private String[] _lines;

   private int _count;

   private int _position;

   private String nextLine(){
      String line;
      while(_position < _count){
         line = _lines[_position++];
         line = RString.replaceChars(line, '\n', ' ', '\r', ' ', '\t', ' ').trim();
         if(line.length() > 0){
            return line;
         }
      }
      return null;
   }

   public FJavaParserWorker(String filename){
      _source = new FStringFile(filename);
   }

   public FJavaParserWorker(FString source){
      _source = source;
   }

   public void parse(){
      _lines = RString.splitChars(_source.toString(), true, '{', '}', ';');
      _count = _lines.length;
      _position = 0;
      String line;
      while((line = nextLine()) != null){
         //System.out.println(line);
         if(line.startsWith("package ")){
            line = RString.mid(line, "package ", ";").trim();
            _class.setNamespace(line);
         }else if(line.startsWith("public class")){
            line = RString.mid(line, "public class", "{").trim();
            String[] data = line.split(" ");
            _class.setInterface(false);
            _class.setShortName(data[0]);
         }else if(line.startsWith("public interface")){
            line = RString.mid(line, "public interface", "{").trim();
            String[] data = line.split(" ");
            _class.setInterface(true);
            _class.setShortName(data[0]);
         }
         if(_class.isInterface()){
            if(line.indexOf('(') != -1){
               String[] items = RString.splitTwo(line, '(', true);
               String[] lefts = RString.trimNoEmpty(items[0].split(" "));
               if(lefts.length == 3){
                  // TODO
               }else if(lefts.length == 2){
                  _method = new FJavaMethod();
                  _method.setName(lefts[1]);
                  _method.setReturnType(lefts[0]);
                  _class.methods().set(lefts[1], _method);
               }
               String paramStr = RString.mid(line, "(", ")");
               String[] params = RString.trimNoEmpty(paramStr.split(","));
               for(String param : params){
                  String[] pms = RString.trimNoEmpty(param.split(" "));
                  if(pms.length == 2){
                     _parameter = new FJavaParameter();
                     _parameter.setName(pms[1]);
                     _parameter.setType(pms[0]);
                     _method.parameters().set(pms[1], _parameter);
                  }
               }
            }
         }else{
            if(line.startsWith("public ")){
               parseClassMethod(line);
            }
         }
      }
   }

   protected void parseClassMethod(String line){
      line = RString.mid(line, "public ", null);
      if(line.indexOf('(') != -1){
         String[] items = RString.splitTwo(line, '(', true);
         String[] lefts = RString.trimNoEmpty(items[0].split(" "));
         if(lefts.length == 3){
            _method = new FJavaMethod();
            _method.setName(lefts[2]);
            _method.setReturnType(lefts[1]);
            //_class.methods().push(_method);
         }else if(lefts.length == 2){
            _method = new FJavaMethod();
            _method.setName(lefts[1]);
            _method.setReturnType(lefts[0]);
            //_class.methods().push(_method);
         }else{
            _method = null;
         }
         if(_method != null){
            String paramStr = RString.mid(line, "(", ")");
            String[] params = RString.trimNoEmpty(paramStr.split(","));
            for(String param : params){
               String[] pms = RString.trimNoEmpty(param.split(" "));
               if(pms.length == 2){
                  _parameter = new FJavaParameter();
                  _parameter.setName(pms[1]);
                  _parameter.setType(pms[0]);
                  //_method.parameters().push(_parameter);
               }
            }
         }
      }
   }

   public FJavaClass result(){
      return _class;
   }
}
