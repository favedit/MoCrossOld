package org.mo.web.core.parser.js;

import org.mo.com.io.RDirectory;
import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

public class FJsXmlClass
      extends FJsObject
{

   private String _directory = "D:/Workspace/eUIS/webroot/ajs";

   private String _encoding = "UTF-8";

   private FJsXmlClasses _classes;

   private String _name;

   private String _space;

   private String _label;

   private String _note;

   private EJsClassType _type = EJsClassType.Unknown;

   public FJsXmlClasses classes(){
      if(null == _classes){
         _classes = new FJsXmlClasses();
      }
      return _classes;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.appendLine();
      info.appendLine("name       =[", _name, "]");
      info.appendLine("label       =[", _label, "]");
      info.appendLine("note       =[", _note, "]");
      info.appendLine("type       =[", _type, "]");
      return info;
   }

   protected int findFunction(FStrings lines,
                              String function){
      int count = lines.count();
      for(int n = 0; n < count; n++){
         String line = lines.get(n);
         if(line.startsWith("function ") && line.contains(function)){
            for(int i = n; i >= 0; i--){
               line = lines.get(i);
               if("}".equals(line)){
                  return i + 1;
               }
            }
            return n;
         }
      }
      return -1;
   }

   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Node");
      node.set("name", _name);
      node.set("label", _label);
      node.set("note", _note);
      node.set("type", _type.toString());
      if(null != _classes){
         for(FJsXmlClass classes : _classes.toObjects()){
            if(classes.name() != null){
               node.push(classes.makeNode());
            }
         }
      }
      return node;
   }

   public String name(){
      return _name;
   }

   public boolean parse(FStrings lines,
                        int start,
                        int end){
      for(int n = start; n < end; n++){
         String line = lines.get(n);
         if(line.contains(RJsTag.REFERENCE)){
            // 引用类的情况
            _type = EJsClassType.Reference;
            n = parseClass(lines, start, end);
         }else if(line.contains(RJsTag.CLASS)){
            // 普通类的情况
            _type = EJsClassType.Class;
            n = parseClass(lines, start, end);
         }else if(line.contains(RJsTag.ENUMERATE)){
            // 枚举类的情况
            _type = EJsClassType.Enumerate;
            n = parseClass(lines, start, end);
         }else if(line.contains(RJsTag.MANGER)){
            // 枚举类的情况
            _type = EJsClassType.Manager;
            n = parseClass(lines, start, end);
         }
      }
      return (EJsClassType.Unknown != _type);
   }

   protected int parseClass(FStrings lines,
                            int start,
                            int end){
      boolean comment = false;
      boolean inFunction = false;
      FString sub = new FString();

      for(int n = start; n < end; n++){
         String line = lines.get(n);
         /// 判断是否是注释部分
         if(line.startsWith("/**")){
            /// 如果是/**开头，则设置注释标识为真
            comment = true;
            continue;
         }else if(line.endsWith("*/")){
            /// 如果是*/结尾，则设置注释标识为假
            comment = false;
            continue;
         }
         /// 分解注释信息
         if(comment){
            //
            if(line.startsWith("*")){
               line = line.substring(1).trim();
            }
            if(RJsTag.REFERENCE.equals(line)){
               _note = sub.toString().trim();
               sub.clear();
               continue;
            }else if(RJsTag.CLASS.equals(line.split(" ")[0])){
               String[] subline = line.split(" ");
               if(subline.length == 2){
                  if(subline[1].trim().startsWith("F")){
                     space(subline[1].trim());
                  }
               }
               _note = sub.toString().trim();
               sub.clear();
               continue;
            }else if(RJsTag.ENUMERATE.equals(line)){
               _note = sub.toString().trim();
               sub.clear();
               continue;
            }else if(RJsTag.MANGER.equals(line)){
               _note = sub.toString().trim();
               sub.clear();
               continue;
            }

            if(line.startsWith(RJsTag.FACE)){
               line = line.substring(RJsTag.FACE.length()).trim();
               String[] items = line.split(",");
               for(int i = 0; i < items.length; i++){
                  if(items[i].trim().startsWith("M")){
                     FJsXmlClass clazz = new FJsXmlClass();
                     clazz._name = items[i].trim();
                     clazz._label = items[i].trim();
                     clazz._type = EJsClassType.Manager;
                     classes().push(clazz);
                  }
               }
            }
            // 增加新的行
            sub.appendLine(line);
         }else{
            // 判断是否是函数部分
            if(line.startsWith(RJsTag.CM_FUNCTION)){
               int find = line.indexOf('(');
               _name = line.substring(RJsTag.CM_FUNCTION.length(), find).trim();
               _label = _name;
               if(EJsClassType.Enumerate == _type || EJsClassType.Reference == _type){
                  if(_name.endsWith("Face")){
                     _name = _name.substring(0, _name.length() - 4);
                  }
               }
               inFunction = true;
               continue;
            }else if("}".equals(line)){
               return n;
            }
            // 分解函数信息
            if(inFunction){

            }
         }
      }
      return end;
   }

   public void parseSpace(String space,
                          String directory,
                          String className){
      FJsXmlParser parser = new FJsXmlParser();
      FStrings files = RFile.listFiles(directory);
      for(String file : files){
         if(file.endsWith(className + ".js")){
            FJsXmlClass clazz = parser.parse(file, _encoding);
            if(null != clazz){
               clazz.setSpace(space);
               classes().push(clazz);
            }
         }
      }
   }

   public void setSpace(String space){
      _space = space;
   }

   public String space(){
      return _space;
   }

   public void space(String className){
      FStrings dirs = RDirectory.listDirectory(_directory, true);
      for(String dir : dirs){
         if(dir.contains("6-help")){
            continue;
         }
         int find = dir.lastIndexOf('-');
         if(-1 != find){
            String space = dir.substring(find + 1);
            parseSpace(space, dir, className);
         }
      }
   }

   public EJsClassType type(){
      return _type;
   }
}
