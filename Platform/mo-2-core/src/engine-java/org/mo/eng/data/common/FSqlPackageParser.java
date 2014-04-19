/*
 * @(#)FSqlPackageParser.java
 *
 * Copyright 2008 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.eng.data.common;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlDatasetMeta;
import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlFunctions;
import org.mo.com.data.FSqlMethod;
import org.mo.com.data.FSqlParameter;
import org.mo.com.data.FSqlParameters;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.FSqlProcedures;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;

/**
 * 该类为存储数据的容器<br>
 * 根据包名查询数据库得出包中的内容，并解析包<br>
 * 生成解析后的包，包中包括function、procedure及参数
 * 
 * @author maocy
 * @version 1.00 - 2008/09/03
 */
public class FSqlPackageParser
      extends MSqlConnect
{
   private static IResource _resource = RResource.find(FSqlPackageParser.class);

   /**
    * 函数的分割符标志
    */
   // repeat方法是“-”字符串重复60次组成一个新的字符串
   public final static String FUNCTION_SPLITTER = RString.repeat("-", 60);

   public final static String PACKAGE_BEGIN = "-- Package define begin ------------------------------------";

   public final static String PACKAGE_END = "-- Package define End --------------------------------------";

   /**
    * 解析描述线标志
    */
   public final static String PARSE_DESCRIPTION_LINE = RString.repeat("-", 60);

   /**
    * 用户代码的标志
    */
   public final static String USER_DEFINE = "user.define";

   /**
    * 用户代码的开始标志
    */
   public final static String USER_DEFINE_BEGIN = "-------------------- Define Begin --------------------------";

   /**
    * 用户结束的结束标志
    */
   public final static String USER_DEFINE_END = "-------------------- Define End ----------------------------";

   // 描述信息
   private String _description = "";

   // function（方法）集合
   private final FSqlFunctions _functions = new FSqlFunctions();

   private FStrings _lines;

   // 被解析包的包名
   private String _name = null;

   // procedure（过程）的集合
   private final FSqlProcedures _procedures = new FSqlProcedures();

   // 属性集合
   private final FAttributes _typeMap = new FAttributes();

   /**
    * <p>用父类的构造函数创建PackageParser对象</p>
    * 
    * @param oConnection 连接数据库           
    * @param packageName 包的名字           
    * @ 
    */
   public FSqlPackageParser(ISqlConnect connect,
                            String packageName){
      super(connect);
      setName(packageName);
   }

   //获得包体的Sql语句，包体包括function和procedure的处理
   protected FString bodySql(){
      String source = _resource.findString("package.body");
      source = RString.parse(source, "name", _name);
      return new FSql(source);
   }

   /*实现把包中的类型转化成对应的数据类型，参数为包中的类型，如果包中的类型
     没有对应的数据类型则设置数据类为Unknown,ESqlDataType为枚举类型*/
   protected ESqlDataType databaseType(String paramType){
      if(null == paramType){
         return ESqlDataType.String;
      }
      if(paramType.equals("BOOLEAN")){
         return ESqlDataType.Boolean;
      }else if(paramType.equals("INTEGER")){
         return ESqlDataType.Integer;
      }else if(paramType.equals("NUMBER") || paramType.equals("FLOAT")){
         return ESqlDataType.Float;
      }else if(paramType.equals("DATETIME")){
         return ESqlDataType.DateTime;
      }else if(paramType.equals("DATE")){
         return ESqlDataType.Date;
      }else if(paramType.equals("CHAR") || paramType.equals("NCHAR") || paramType.equals("VARCHAR") || paramType.equals("VARCHAR2") || paramType.equals("LONG")){
         return ESqlDataType.String;
      }else if(paramType.indexOf("LONG RAW") > 0){
         return ESqlDataType.Unknown;
      }else if(paramType.indexOf("%ROWTYPE") > 0){
         return ESqlDataType.Unknown;
      }else if(paramType.indexOf("%TYPE") > 0){
         paramType = paramType.substring(0, paramType.length() - 5);
         if(_typeMap.contains(paramType)){
            return databaseType(_typeMap.get(paramType));
         }else{
            String[] arParamType = paramType.split("\\.");
            if(arParamType.length == 2){
               arParamType[1] = RString.mid(arParamType[1], "\"", "\"");
               FSqlDatasetMeta oDataSetMeta = activeConnection().fetchTableMeta(arParamType[0]);
               if(oDataSetMeta != null){
                  ESqlDataType dataType;
                  for(FSqlField oField : oDataSetMeta.fields()){
                     dataType = oField.typeCd();
                     if(dataType == ESqlDataType.Unknown){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "UNKNOWN");
                     }else if(dataType == ESqlDataType.Boolean){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "BOOLEAN");
                     }else if(dataType == ESqlDataType.DateTime){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "DATETIME");
                     }else if(dataType == ESqlDataType.Integer){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "INTEGER");
                     }else if(dataType == ESqlDataType.Float){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "FLOAT");
                     }else if(dataType == ESqlDataType.String){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "VARCHAR2");
                     }else if(dataType == ESqlDataType.Binary){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "LONG RAW");
                     }else if(dataType == ESqlDataType.Memo){
                        _typeMap.set(arParamType[0] + "." + oField.name(), "MEMO");
                     }
                  }
                  return databaseType(_typeMap.get(paramType));
               }
            }
         }
      }
      return ESqlDataType.Unknown;
   }

   /**
    * <p>获得描述信息</p>
    * 
    * @return描述信息（String类型）
    */
   public String description(){
      return _description;
   }

   //   @Override
   //   public TDumpInfo dump(TDumpInfo info){
   //      RDump.dump(info, this);
   //      info.appendLine();
   //      info.appendLine(RDump.LINE_L2);
   //      info.append(_functions.dump());
   //      info.appendLine(RDump.LINE_L2);
   //      info.append(_procedures.dump());
   //      return info;
   //   }
   /**
    * <p>获得包体，包括包中的function方法体和procedure的过程体</p>
    * 
    * @return 记录集合（FStrings类型)
    */
   public FStrings fetchBody(){
      FStrings lines = new FStrings();
      FDataset ds = activeConnection().fetchDataset(bodySql().toString());
      if(ds != null){
         for(FRow row : ds){
            String text = row.get("text");
            if(RString.endsWith(text, "\n")){
               if(!text.equalsIgnoreCase("\n")){
                  text = RString.trimRight(text) + "\n";
               }
            }
            lines.push(text);
         }
      }
      return lines;
   }

   public FString fetchBodyString(){
      FString source = new FString();
      activeConnection().setMaxLimit(false);
      FDataset ds = activeConnection().fetchDataset(bodySql().toString());
      activeConnection().setMaxLimit(true);
      if(ds != null){
         for(FRow row : ds){
            String text = row.get("text");
            if(RString.endsWith(text, "\n")){
               if(!text.equalsIgnoreCase("\n")){
                  text = RString.trimRight(text) + "\n";
               }
            }
            source.append(text);
         }
      }
      return source;
   }

   /**
    * <p>获得包头，包括包中的function方法定义和procedure过程定义</p>
    * 
    * @return 记录集合（FString类型）
    */
   public FStrings fetchHead(){
      FStrings lines = new FStrings();
      FDataset ds = activeConnection().fetchDataset(headSql().toString());
      if(ds != null){
         for(FRow row : ds){
            String text = row.get("text");
            if(RString.endsWith(text, "\n")){
               if(!text.equalsIgnoreCase("\n")){
                  text = RString.trimRight(text) + "\n";
               }
            }
            lines.push(text);
         }
      }
      return lines;
   }

   public FString fetchHeadString(){
      FString source = new FString();
      activeConnection().setMaxLimit(false);
      FDataset ds = activeConnection().fetchDataset(headSql().toString());
      activeConnection().setMaxLimit(true);
      if(ds != null){
         for(FRow row : ds){
            String text = row.get("text");
            if(RString.endsWith(text, "\n")){
               if(!text.equalsIgnoreCase("\n")){
                  text = RString.trimRight(text) + "\n";
               }
            }
            source.append(text);
         }
      }
      return source;
   }

   public FString fetchLines(){
      FString source = new FString();
      if(null != _lines){
         for(String line : _lines){
            source.append(line);
         }
      }
      return source;
   }

   /**
    * 获得用户定义代码。
    * 
    * @param type 代码类型
    * @return 用户定义代码
    */
   public FString fetchUserHeadDefine(){
      // 获得数据库数据包
      _lines = fetchHead();
      // 查找用户定义的部分
      int begin = 0, end = 0;
      int count = _lines.count();
      for(int i = 0; i < count; i++){
         String line = _lines.get(i);
         if(!RString.isEmpty(line)){
            line = line.trim();
            if(USER_DEFINE_BEGIN.equals(line)){
               begin = i;
            }else if(USER_DEFINE_END.equals(line)){
               end = i;
            }
         }
      }
      // 获取用户定义的部分
      FString source = new FString();
      for(int i = begin + 1; i < end; i++){
         source.append(_lines.get(i));
      }
      return source;
   }

   public FString fetchUserBodyDefine(){
      // 获得数据库数据包
      _lines = fetchBody();
      // 查找用户定义的部分
      int begin = 0, end = 0;
      int count = _lines.count();
      for(int i = 0; i < count; i++){
         String line = _lines.get(i);
         if(!RString.isEmpty(line)){
            line = line.trim();
            if(USER_DEFINE_BEGIN.equals(line)){
               begin = i;
            }else if(USER_DEFINE_END.equals(line)){
               end = i;
            }
         }
      }
      // 获取用户定义的部分
      FString source = new FString();
      for(int i = begin + 1; i < end; i++){
         source.append(_lines.get(i));
      }
      return source;
   }

   /**
    * <p>获得function方法集合</p>
    * 
    * @return function 方法集合
    */
   public FSqlFunctions functions(){
      return _functions;
   }

   // 获得包头的Sql语句（包头包括：function和procedure的定义声明）
   protected FString headSql(){
      String source = _resource.findString("package.head");
      source = RString.parse(source, "name", _name);
      return new FSql(source);
   }

   public String logicName(){
      String source = fetchHead().join();
      String find = RString.mid(source, "LG_NAME", "\n");
      return RString.mid(find, "'", "'");
   }

   /**
    * <p>生成XML节点该节点可以包括子节点<br>
    * 根结点为package包名<br>
    * 下一节点为function节点和<br>
    * procedure节点<br>
    * function和procedure节点的子节点为参数</p>
    * 
    * @return 根结点
    */
   public FXmlNode makeXmlNode(){
      FXmlNode packageNode = new FXmlNode("com.linekong.euis.data.");
      String packageName = name();
      if(packageName.endsWith("DI")){
         packageName = packageName.substring(0, packageName.length() - 2);
      }
      packageNode.set("name", packageName);
      packageNode.set("type", "package");
      boolean b = parseHead();
      if(b){
         for(FSqlFunction function : functions()){
            boolean isFunction = true;
            FXmlNode functionNode = new FXmlNode("Function");
            functionNode.set("name", function.name());
            functionNode.set("type", "function");
            FSqlParameters parameters = function.parameters();
            if(!parameters.isEmpty()){
               for(FSqlParameter parameter : parameters.values()){
                  FXmlNode parameterNode = new FXmlNode("Parameter");
                  if(ESqlDataType.Unknown == parameter.type()){
                     isFunction = false;
                     break;
                  }else{
                     isFunction = true;
                  }
                  FXmlNode paraDescNode = new FXmlNode("description");
                  paraDescNode.set("name", parameter.name());
                  paraDescNode.set("description", parameter.description());
                  paraDescNode.set("type", "description");
                  functionNode.set("desc_child", "Y");
                  functionNode.push(paraDescNode);
                  parameterNode.set("name", parameter.name());
                  if(ESqlDataType.Boolean == parameter.type()){
                     parameterNode.set("type", "Boolean");
                  }else if(ESqlDataType.Char == parameter.type()){
                     parameterNode.set("type", "String");
                  }else if(ESqlDataType.Date == parameter.type()){
                     parameterNode.set("type", "Date");
                  }else if(ESqlDataType.DateTime == parameter.type()){
                     parameterNode.set("type", "Date");
                  }else if(ESqlDataType.Float == parameter.type()){
                     parameterNode.set("type", "Float");
                  }else if(ESqlDataType.Integer == parameter.type()){
                     parameterNode.set("type", "Integer");
                  }else if(ESqlDataType.Long == parameter.type()){
                     parameterNode.set("type", "Long");
                  }else if(ESqlDataType.String == parameter.type()){
                     parameterNode.set("type", "String");
                  }else if(ESqlDataType.Time == parameter.type()){
                     parameterNode.set("type", "Date");
                  }
                  parameterNode.set("direction", parameter.direction().toString());
                  functionNode.push(parameterNode);
               }
               functionNode.set("child", "Y");
            }else{
               functionNode.set("child", "");
            }
            if(!isFunction){
               continue;
            }
            FSqlParameter retParameter = function.parameterReturn();
            if(ESqlDataType.Unknown == retParameter.type()){
               continue;
            }
            if("BOOLEAN".equals(retParameter.databaseType())){
               functionNode.set("re_type", "Boolean");
               functionNode.set("re_name", "true");
            }else if(ESqlDataType.Char == retParameter.type()){
               functionNode.set("re_type", "String");
            }else if(ESqlDataType.Date == retParameter.type()){
               functionNode.set("re_type", "Date");
            }else if(ESqlDataType.DateTime == retParameter.type()){
               functionNode.set("re_type", "Date");
            }else if(ESqlDataType.Float == retParameter.type()){
               functionNode.set("re_type", "Float");
            }else if(ESqlDataType.Integer == retParameter.type()){
               functionNode.set("re_type", "Integer");
            }else if(ESqlDataType.Long == retParameter.type()){
               functionNode.set("re_type", "Long");
            }else if(ESqlDataType.String == retParameter.type()){
               functionNode.set("re_type", "String");
            }else if(ESqlDataType.Time == retParameter.type()){
               functionNode.set("re_type", "Date");
            }
            if(retParameter.description() != null){
               functionNode.set("re_description", retParameter.description());
            }else{
               functionNode.set("re_description", "");
            }
            if(function.description() != null){
               functionNode.set("description", function.description());
            }else{
               functionNode.set("description", "");
            }
            packageNode.push(functionNode);
         }
         FSqlProcedures procedures = procedures();
         for(int i = 0; i < procedures.count(); i++){
            boolean isProcedure = true;
            FXmlNode procedureNode = new FXmlNode("Procedure");
            FSqlProcedure procedure = procedures.get(i);
            procedureNode.set("name", procedure.name());
            procedureNode.set("type", "procedure");
            FSqlParameters parameters = procedure.parameters();
            if(procedure.description() != null){
               procedureNode.set("description", procedure.description());
            }else{
               procedureNode.set("description", "");
            }
            if(!parameters.isEmpty()){
               for(FSqlParameter parameter : parameters.values()){
                  FXmlNode parameterNode = new FXmlNode("Parameter");
                  if(ESqlDataType.Unknown == parameter.type()){
                     isProcedure = false;
                     break;
                  }else{
                     isProcedure = true;
                  }
                  FXmlNode proDescNode = new FXmlNode("description");
                  proDescNode.set("name", parameter.name());
                  proDescNode.set("description", parameter.description());
                  proDescNode.set("type", "description");
                  procedureNode.set("child", "Y");
                  procedureNode.push(proDescNode);
                  parameterNode.set("name", parameter.name());
                  if(ESqlDataType.Boolean == parameter.type()){
                     parameterNode.set("type", "Boolean");
                  }else if(ESqlDataType.Char == parameter.type()){
                     parameterNode.set("type", "String");
                  }else if(ESqlDataType.Date == parameter.type()){
                     parameterNode.set("type", "Date");
                  }else if(ESqlDataType.DateTime == parameter.type()){
                     parameterNode.set("type", "Date");
                  }else if(ESqlDataType.Float == parameter.type()){
                     parameterNode.set("type", "Float");
                  }else if(ESqlDataType.Integer == parameter.type()){
                     parameterNode.set("type", "Integer");
                  }else if(ESqlDataType.Long == parameter.type()){
                     parameterNode.set("type", "Long");
                  }else if(ESqlDataType.String == parameter.type()){
                     parameterNode.set("type", "String");
                  }else if(ESqlDataType.Time == parameter.type()){
                     parameterNode.set("type", "Date");
                  }
                  parameterNode.set("direction", parameter.direction().toString());
                  procedureNode.push(parameterNode);
               }
               procedureNode.set("child", "Y");
            }else{
               procedureNode.set("child", "");
            }
            if(!isProcedure){
               continue;
            }
            procedureNode.set("re_type", "void");
            packageNode.push(procedureNode);
         }
      }
      return packageNode;
   }

   /**
    * <p> 获得包名</p>
    * 
    * @return 包名（String类型）
    */
   public String name(){
      return _name;
   }

   /**
    * <p>判断是否解析了包体，获得了包体</p>
    * 
    * @return TRUE? 已经解析获得包体<br>
    *          FALSE? 没有解析获得･
    */
   public boolean parseBody(){
      return parsePackage(fetchBody());
   }

   /**
    * <p> 判断是否解析获得了包头</p>
    * 
    * @return TRUE? 已经解析获得了包头<br>
    *          FALSE? 没有解析获得包头
    */
   public boolean parseHead(){
      return parsePackage(fetchHead());
   }

   /**
    * <p>判断是否解析包，获得了包中的内容</p>
    * 
    * @param lines 查询数据库获得的记录集合（包括包头和包体）          
    * @return TRUE? 解析包并获得了包中的内容<br>
    *          FALSE? 没有成功的解析包并获得包中的内容
    */
   protected boolean parsePackage(FStrings lines){
      String line = null;
      String lineDump = null;
      String name = "";
      FSqlFunction function = null;
      FSqlProcedure procedure = null;
      int count = lines.count();
      for(int n = 0; n < count; n++){
         line = lines.get(n).trim();
         if(RString.isEmpty(line)){
            continue;
         }
         lineDump = line.toUpperCase();
         if(lineDump.startsWith("--")){
            continue;
         }
         if(lineDump.startsWith("FUNCTION")){
            if(line.indexOf("(") >= 0){
               name = RString.left(line, "(").substring(8).trim();
            }else{
               name = RString.left(line, "RETURN").substring(8).trim();
            }
            if(name.indexOf(";") >= 0){
               name = RString.left(name, ";").trim();
            }
            function = new FSqlFunction(name);
            _functions.push(function);
            n = parseParams(function, lines, n, function.parameters(), function.parameterReturn());
         }else if(lineDump.indexOf("PROCEDURE") >= 0){
            if(line.indexOf("(") >= 0){
               name = RString.left(line, "(").substring(9).trim();
            }else{
               name = line.substring(9).trim();
            }
            if(name.indexOf(";") >= 0){
               name = RString.left(name, ";").trim();
            }
            procedure = new FSqlProcedure(name);
            //_procedures.set(procedure.name(), procedure);
            n = parseParams(procedure, lines, n, procedure.parameters(), null);
         }
      }
      return true;
   }

   /*根据传过来的参数解析从数据库得到的记录集合，获得function和procedure的参数
    *并把参数放到对应的function和procedure中*/
   protected int parseParams(FSqlMethod method,
                             FStrings sourceList,
                             int nLine,
                             FSqlParameters oParameters,
                             FSqlParameter oReturn){
      // 行标，记录第几行，目的是为了有序的，不重复的遍历从数据集合中读出的记录集
      int nLinePosition = nLine;
      String sLine = null;
      int nCount = sourceList.count();
      FString sParameter = new FString();
      for(int i = nLine; i < nCount; i++){
         sLine = sourceList.get(i).trim();
         if(sLine.startsWith("--")){
            continue;
         }
         if(!RString.isEmpty(sLine)){
            sParameter.append(sLine);
            if(sLine.indexOf(";") >= 0){
               nLinePosition = i;
               break;
            }
         }
      }
      String sMethodString = sParameter.toString();
      sMethodString = RString.removeChars(sMethodString, "\r\n");
      sMethodString = RString.replace(sMethodString, "\t", " ");
      // 解析记录创建参数
      boolean bHasParams = (sMethodString.indexOf("(") >= 0);
      String[] arParams = null;
      String[] arParam = null;
      String[] arParamInfo = null;
      FSqlParameter oParameter = null;
      if(bHasParams){
         String sParamString = RString.mid(sMethodString, "(", ")");
         arParams = sParamString.split(",");
         int nParamCount = arParams.length;
         for(int nParam = 0; nParam < nParamCount; nParam++){
            arParamInfo = RStrings.filterNotEmpty(arParams[nParam].split(" "));
            oParameter = new FSqlParameter(arParamInfo[0]);
            if(arParamInfo[1].equals("IN")){
               oParameter.addDirection(ESqlDataDirection.In);
               if(arParamInfo[2].equals("OUT")){
                  oParameter.addDirection(ESqlDataDirection.Out);
                  oParameter.setDatabaseType(arParamInfo[3]);
               }else{
                  oParameter.setDatabaseType(arParamInfo[2]);
               }
            }else if(arParamInfo[1].equals("OUT")){
               oParameter.addDirection(ESqlDataDirection.Out);
               if(arParamInfo[2].equals("IN")){
                  oParameter.addDirection(ESqlDataDirection.In);
                  oParameter.setDatabaseType(arParamInfo[3]);
               }else{
                  oParameter.setDatabaseType(arParamInfo[2]);
               }
            }else{
               oParameter.setDatabaseType(arParamInfo[1]);
            }
            oParameter.setType(databaseType(oParameter.databaseType()));
            oParameters.set(oParameter.name(), oParameter);
         }
      }
      // 创建返回参数
      if(oReturn != null){
         String sReturn = null;
         if(sMethodString.indexOf(")") >= 0){
            sMethodString = RString.right(sMethodString, ")").trim();
            if(sMethodString.startsWith("RETURN ")){
               sReturn = RString.mid(sMethodString, "RETURN ", ";").trim();
            }
         }else{
            sReturn = RString.mid(sMethodString, " RETURN ", ";").trim();
         }
         if(!RString.isEmpty(sReturn)){
            oReturn.setType(databaseType(sReturn));
            oReturn.setDatabaseType(sReturn);
         }
      }
      // 计算初出描述信息记录在记录集中的起始位置
      int nDescBegin = 0;
      for(int n = nLine - 1; n >= 0; n--){
         sLine = sourceList.get(n).trim();
         if(sLine.startsWith(FUNCTION_SPLITTER)){
            nDescBegin = n;
            break;
         }
         if(!sLine.startsWith("--")){
            nDescBegin = n;
            break;
         }
      }
      // 获得包中的描述信息
      String sMethodDescription = "";
      boolean bMethodDescriptionEnd = false;
      for(int i = nDescBegin + 1; i < nLine; i++){
         sLine = sourceList.get(i).trim();
         if(sLine.indexOf("--") >= 0 && sLine.length() > 2){
            sLine = sLine.substring(2).trim();
            if(sLine.indexOf("@param") >= 0){
               bMethodDescriptionEnd = true;
               arParam = RStrings.filterNotEmpty(sLine.split(" "));
               oParameter = oParameters.get(arParam[1]);
               if(oParameter != null){
                  if(arParam.length > 2){
                     oParameter.setDescription(arParam[2]);
                  }
               }
            }else if(sLine.indexOf("@return") >= 0){
               bMethodDescriptionEnd = true;
               arParam = RStrings.filterNotEmpty(sLine.split(" "));
               if(oReturn != null){
                  if(arParam.length > 1){
                     oReturn.setDescription(arParam[1]);
                  }
               }
            }
            if(!bMethodDescriptionEnd){
               sMethodDescription += sLine;
            }
         }
      }
      method.setDescription(sMethodDescription);
      return nLinePosition;
   }

   /**
    * <p>获得procedure过程集合</p>
    * 
    * @return procedure过程集合（FSqlProcedures类型）
    */
   public FSqlProcedures procedures(){
      return _procedures;
   }

   /**
    * <p>设置描述信息</p>
    * 
    * @param sValue 描述信息          
    * @return TRUE? 设置成功<br>
    *          FALSE? 设置失败
    */
   public boolean setDescription(String sValue){
      _description = sValue;
      return true;
   }

   /**
    * <p>设置包名</p>
    * 
    * @param packageName 新包名
    * @return TRUE?设置成功FALSE设置失败
    */
   public void setName(String packageName){
      _name = packageName;
   }
}
