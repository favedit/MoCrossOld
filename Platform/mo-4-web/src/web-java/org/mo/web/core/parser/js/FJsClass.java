package org.mo.web.core.parser.js;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

public class FJsClass
      extends FJsObject
{

   private FJsClasses _classes;

   private String _name;

   private String _space;

   private String _description;

   private String _author;

   private String _version;

   private String _scope;

   private String _fileName;

   private EJsClassType _type = EJsClassType.Unknown;

   private FJsMethods _methods;

   private FJsProperties _properties;

   private FJsAttributes _attributes;

   private FJsEmpties _empties;

   private FJsVirtuals _virtuals;

   private FJsEvents _events;

   private FJsStyles _styles;

   public FJsAttributes attributes(){
      if(null == _attributes){
         _attributes = new FJsAttributes();
      }
      return _attributes;
   }

   public String author(){
      return _author;
   }

   public FJsClasses classes(){
      if(null == _classes){
         _classes = new FJsClasses();
      }
      return _classes;
   }

   public String description(){
      return _description;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.appendLine();
      info.appendLine("name       =[", _name, "]");
      info.appendLine("type       =[", _type, "]");
      info.appendLine("scope       =[", _scope, "]");
      info.appendLine("author     =[", _author, "]");
      info.appendLine("version    =[", version(), "]");
      if(null != _methods){
         for(FJsMethod method : _methods.values()){
            info.appendLine();
            info.appendLine();
            info.appendLine(RDump.LINE_L2);
            info.append(method.dump());
         }
      }
      return info;
   }

   public FJsEmpties empties(){
      if(null == _empties){
         _empties = new FJsEmpties();
      }
      return _empties;
   }

   public FJsEvents events(){
      if(null == _events){
         _events = new FJsEvents();
      }
      return _events;
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

   public FXmlNode getXml(String fileUrl){
      FJsXmlParser parser = new FJsXmlParser();
      FJsXmlClass jsClass = parser.parse(fileUrl, "UTF-8");
      String name = jsClass.name();
      FXmlNode node = jsClass.makeNode();
      FXmlNode node2 = null;
      if(name.startsWith("F") || name.startsWith("M")){
         node2 = node;
      }
      return node2;
   }

   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Class");
      node.set("name", _name);
      node.set("type", _type.toString());
      node.set("scope", _scope);
      node.set("author", _author);
      node.set("version", _version);
      if(null != _classes){
         for(FJsClass classes : _classes.toObjects()){
            if(classes.name() != null){
               node.push(classes.makeNode());
            }
         }
      }
      // 继承关系
      FXmlNode fileNode = getXml(_fileName);
      if(null != fileNode){
         FXmlNode inheritNode = new FXmlNode("Inherits");
         inheritNode.push(fileNode);
         node.push(inheritNode);
         node.set("is_inherits", "Y");
      }else{
         node.set("is_inherits", "N");
      }
      // 类的详细说明
      if(null != _description && !"".equals(_description)){
         FXmlNode descriptionNode = new FXmlNode("Description");
         descriptionNode.setText(_description);
         node.push(descriptionNode);
         node.set("is_description", "Y");
      }else{
         node.set("is_description", "N");
      }
      // 属性
      if(null != _properties){
         FXmlNode propertiesNode = new FXmlNode("Properties");
         for(FJsProperty property : _properties.toObjects()){
            if(property.name() != null){
               propertiesNode.push(property.makeNode());
            }
         }
         node.push(propertiesNode);
         node.set("is_properties", "Y");
      }else{
         node.set("is_properties", "N");
      }
      // 样式
      if(null != _styles){
         FXmlNode stylesNode = new FXmlNode("Styles");
         for(FJsStyle style : _styles.toObjects()){
            if(style.name() != null){
               stylesNode.push(style.makeNode());
            }
         }
         node.push(stylesNode);
         node.set("is_styles", "Y");
      }else{
         node.set("is_styles", "N");
      }
      // 常量
      if(null != _attributes){
         FXmlNode attributesNode = new FXmlNode("Attributes");
         for(FJsAttribute attribute : _attributes.toObjects()){
            if(attribute.name() != null){
               attributesNode.push(attribute.makeNode());
            }
         }
         node.push(attributesNode);
         node.set("is_attributes", "Y");
      }else{
         node.set("is_attributes", "N");
      }
      // 函数
      if(null != _methods){
         FXmlNode methoesNode = new FXmlNode("Methods");
         for(FJsMethod method : _methods.toObjects()){
            if(method.name() != null){
               methoesNode.push(method.makeNode());
            }
         }
         if(null != _empties){
            for(FJsEmpty empty : _empties.toObjects()){
               methoesNode.push(empty.makeNode());
            }
         }
         if(null != _virtuals){
            for(FJsVirtual virtual : _virtuals.toObjects()){
               methoesNode.push(virtual.makeNode());
            }
         }
         //事件
         if(null != _events){
            for(FJsEvent event : _events.toObjects()){
               if(event.name() != null){
                  methoesNode.push(event.makeNode());
               }
            }
            node.set("is_events", "Y");
         }else{
            node.set("is_events", "N");
         }
         node.push(methoesNode);
         node.set("is_methods", "Y");
      }else{
         node.set("is_methods", "N");
      }

      return node;
   }

   public FJsMethods methods(){
      if(null == _methods){
         _methods = new FJsMethods();
      }
      return _methods;
   }

   public String name(){
      return _name;
   }

   public boolean parse(FStrings lines,
                        int start,
                        int end,
                        String fileName,
                        FStrings lines2){
      _fileName = fileName;
      for(int n = start; n < end; n++){
         String line = lines.get(n);
         if(line.contains(RJsTag.REFERENCE)){
            // 引用类的情况
            _type = EJsClassType.Reference;
            n = parseClass(lines, start, end, lines2);
         }else if(line.contains(RJsTag.CLASS)){
            // 普通类的情况
            _type = EJsClassType.Class;
            n = parseClass(lines, start, end, lines2);
         }else if(line.contains(RJsTag.ENUMERATE)){
            // 枚举类的情况
            _type = EJsClassType.Enumerate;
            n = parseClass(lines, start, end, lines2);
         }else if(line.contains(RJsTag.MANGER)){
            // 枚举类的情况
            _type = EJsClassType.Manager;
            n = parseClass(lines, start, end, lines2);
         }
      }
      return (EJsClassType.Unknown != _type);
   }

   protected int parseClass(FStrings lines,
                            int start,
                            int end,
                            FStrings lines2){
      boolean comment = false;
      boolean inFunction = false;
      boolean inParam = false;
      FString sub = new FString();
      FString subParam = new FString();
      for(int n = start; n < end; n++){
         String line = lines.get(n);
         // 判断是否是注释部分
         if(line.startsWith("/**")){
            comment = true;
            continue;
         }else if(line.endsWith("*/")){
            comment = false;
            continue;
         }
         // 分解注释信息
         if(comment){
            //
            if(line.startsWith("*")){
               line = line.substring(1).trim();
            }
            if(RJsTag.REFERENCE.equals(line)){
               _description = sub.toString().trim();
               sub.clear();
               continue;
            }else if(RJsTag.CLASS.equals(line.split(" ")[0])){
               _description = sub.toString().trim();
               sub.clear();
               continue;
            }else if(RJsTag.ENUMERATE.equals(line)){
               System.out.println(_description);
               _description = sub.toString().trim();
               sub.clear();
               continue;
            }else if(RJsTag.MANGER.equals(line)){
               _description = sub.toString().trim();
               sub.clear();
               continue;
            }else if(line.startsWith(RJsTag.SCOPE)){
               _scope = line.substring(RJsTag.SCOPE.length()).trim();
               continue;
            }else if(line.startsWith(RJsTag.AUTHOR)){
               _author = line.substring(RJsTag.AUTHOR.length()).trim();
               continue;
            }else if(line.startsWith(RJsTag.VERSION)){
               _version = line.substring(RJsTag.VERSION.length()).trim();
               continue;
            }
            // 增加新的行
            sub.appendLine(line);
         }else{
            // 判断是否是函数部分
            if(line.startsWith(RJsTag.CM_FUNCTION)){
               int find = line.indexOf('(');
               _name = line.substring(RJsTag.CM_FUNCTION.length(), find).trim();
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
               if(line.startsWith("///") && line.contains("@")){
                  inParam = true;
               }
               if(inParam){
                  if(line.startsWith("o.")){
                     inParam = false;
                  }
                  if(line.startsWith("///")){
                     line = line.substring(3).trim();
                  }
                  subParam.appendLine(line);
                  String subParamStr = subParam.toString().trim();
                  if(subParamStr.contains("o.") && subParamStr.contains("=")){
                     String[] subParams = subParam.toString().split("\n");
                     if(subParams[0].startsWith(RJsTag.PROPERTY)){
                        FJsProperty property = new FJsProperty();
                        property.parse(subParams);
                        if(RString.isEmpty(property.name())){
                           throw new FFatalError("Name is null. (line={0})", subParam.toString());
                        }
                        properties().push(property);
                        subParam.clear();
                        continue;
                     }
                     if(subParams[0].startsWith(RJsTag.STYLE)){
                        FJsStyle style = new FJsStyle();
                        style.parse(subParams);
                        if(RString.isEmpty(style.name())){
                           throw new FFatalError("Name is null. (line={0})", subParam.toString());
                        }
                        styles().push(style);
                        subParam.clear();
                        continue;
                     }else if(subParams[0].startsWith(RJsTag.ATTRIBUTE)){
                        FJsAttribute attribute = new FJsAttribute();
                        attribute.parse(subParams);
                        if(RString.isEmpty(attribute.name())){
                           throw new FFatalError("Name is null. (line={0})", subParam.toString());
                        }
                        attributes().push(attribute);
                        subParam.clear();
                        continue;
                     }else if(subParams[0].startsWith(RJsTag.EVENT)){
                        FJsEvent event = new FJsEvent();
                        event.parse(subParams);
                        if(RString.isEmpty(event.name())){
                           throw new FFatalError("Name is null. (line={0})", subParam.toString());
                        }
                        events().push(event);
                        subParam.clear();
                        continue;
                     }else if(subParams[0].startsWith(RJsTag.EMPTY)){
                        FJsEmpty empty = new FJsEmpty();
                        empty.parse(subParams);
                        if(RString.isEmpty(empty.name())){
                           throw new FFatalError("Name is null. (line={0})", subParam.toString());
                        }
                        empties().push(empty);
                        subParam.clear();
                        continue;
                     }else if(subParams[0].startsWith(RJsTag.VIRTUAL)){
                        FJsVirtual virtual = new FJsVirtual();
                        virtual.parse(subParams);
                        if(RString.isEmpty(virtual.name())){
                           throw new FFatalError("Name is null. (line={0})", subParam.toString());
                        }
                        virtuals().push(virtual);
                        subParam.clear();
                        continue;
                     }else if(subParams[0].startsWith(RJsTag.HTML)){
                        subParam.clear();
                        continue;
                     }
                  }
               }
               if(line.startsWith("o.") && line.contains("=") && line.contains("_")){
                  line = line.substring(2);
                  if(line.endsWith(";")){
                     line = line.substring(0, line.length() - 1);
                  }
                  String[] items = RString.splitTwo(line, "=", true);
                  // 建立方法
                  FJsMethod method = new FJsMethod();
                  method.setName(items[0]);
                  method.setFunction(items[1]);
                  methods().push(method);
                  if(RJsTag.CM_EMPTY.equals(method.function())){
                     // 空方法的情况
                     method.setType(EJsMethodType.Empty);
                  }else{
                     // 方法的情况
                     method.setType(EJsMethodType.Method);
                     int find = findFunction(lines, method.function());

                     if(-1 != find){
                        method.parse(lines, find, lines.count(), lines2);
                     }
                  }
               }
            }

         }
      }
      return end;
   }

   public FJsProperties properties(){
      if(null == _properties){
         _properties = new FJsProperties();
      }
      return _properties;
   }

   public void setSpace(String space){
      _space = space;
   }

   public String space(){
      return _space;
   }

   public FJsStyles styles(){
      if(null == _styles){
         _styles = new FJsStyles();
      }
      return _styles;
   }

   public EJsClassType type(){
      return _type;
   }

   public String version(){
      return _version;
   }

   public FJsVirtuals virtuals(){
      if(null == _virtuals){
         _virtuals = new FJsVirtuals();
      }
      return _virtuals;
   }
}
