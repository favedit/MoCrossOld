/*
 * @(#)FSqlFunction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

/**
 * <T>解析包头中所有函数的类</T>
 * 
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlFunction
      extends FSqlObject
{
   // 定义函数的代码变量
   protected String _code;

   // 定义函数的数据类型
   protected String _dataType;

   // 定义函数的详细说明
   protected String _description = "";

   // 定义函数的名称
   protected String _name;

   // 定义函数的名称ID
   protected String _nameId;

   // 定义函数的参数集
   private FSqlParameters _parameters;

   // 定义函数的返回值变量
   private FSqlReturn _return;

   // 定义函数的调用
   private FSqlSee _see;

   // 定义函数的类型
   protected String _type;

   public FSqlFunction(FSqlParserContext context){
      super(context);
   }

   /**
    * <T>以包头当前函数去包体中查找相同的函数</T>
    * 
    * @param bodyNode 包体的XML节点
    * @param param 当前函数的所有参数
    * @return 方法体的代码 
    */
   public String bodyCode(FXmlNode bodyNode,
                          String param){
      String code = "";
      param = RString.mid(param, "(", ")").trim();
      if(param.contains(" ")){
         param = param.replaceAll(" ", "");
      }
      if(param.contains(",")){
         param = param.replaceAll(",", "");
      }
      if(param.contains("\t")){
         param = param.replaceAll("\t", "");
      }
      if(param.contains("DEFAULT")){
         param = param.replaceAll("DEFAULT", "");
      }
      int nameId = 0;
      for(FXmlNode rootNode : bodyNode.toObjects()){
         String bodyName = rootNode.get("name");
         String bodyType = rootNode.get("type");
         if(_name.equals(bodyName) && _type.equals(bodyType)){
            FXmlNode parameters = rootNode.findNode("Parameters");
            String paramString = "";
            for(FXmlNode paramterNode : parameters.toObjects()){
               String bodyParamName = paramterNode.get("name");
               String bodyParamType = paramterNode.get("type");
               String bodyParamDataType = paramterNode.get("data_type");
               String paramValue = paramterNode.get("value");
               paramString += bodyParamName + bodyParamType + bodyParamDataType + paramValue;
            }
            paramString = paramString.replaceAll(" ", "");
            if(paramString.contains("DEFAULT")){
               paramString = paramString.replaceAll("DEFAULT", "");
            }
            if(param.equals(paramString.trim())){
               code = rootNode.findNode("Code").text();
               String[] arrString = code.split("\n");
               FString lines = new FString();
               for(int i = 0; i < arrString.length; i++){
                  lines.appendLine(arrString[i]);
               }
               if(1 == nameId){
                  _nameId = "" + nameId;
               }
               code = RSqlTag.formatPlSQLScript(lines);
               break;
            }
         }
      }
      return code;
   }

   /**
    * <T>取得详细说明内容</T>
    * 
    * @return 详细说明内容
    */
   public String description(){
      return _description;
   }

   /* (non-Javadoc)
    * @see org.mo.com.lang.IDump#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",type=", _type);
      info.append(",description=", _description, "]");
      return info;
   }

   /**
    * <T>创建函数节点并设置属性及子节点</T>
    * 
    * @return 函数节点的XML
    */
   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Function");
      if(null == _nameId){
         _nameId = "";
      }
      node.set("name_id", _nameId);
      node.set("name", _name);
      node.set("type", _type);
      // 函数的详细说明
      if(null != _description && !"".equals(_description)){
         FXmlNode descriptionNode = new FXmlNode("Description");
         descriptionNode.setText(_description);
         node.push(descriptionNode);
         node.set("has_description", "Y");
      }else{
         node.set("has_description", "N");
      }
      // 参数
      if(null != _parameters){
         FXmlNode parametersNode = new FXmlNode("Parameters");
         for(FSqlParameter parameter : _parameters.toObjects()){
            if(null != parameter._name){
               parametersNode.push(parameter.makeNode());
            }
         }
         node.push(parametersNode);
         node.set("has_parameters", "Y");
      }else{
         node.set("has_parameters", "N");
      }
      // 调用
      if(null != _see){
         node.push(_see.makeSeeNode());
         node.set("has_see", "Y");
      }else{
         node.set("has_see", "N");
      }
      // 返回
      if(null != _return){
         node.push(_return.makeReturnNode());
         node.set("has_return", "Y");
      }else{
         node.set("has_return", "N");
      }
      // 函数体代码
      if(null != _code){
         FXmlNode codeNode = new FXmlNode("Code");
         codeNode.setText(_code);
         node.push(codeNode);
         node.set("has_code", "Y");
      }else{
         node.set("has_code", "N");
      }
      return node;
   }

   /**
    * <T>取得函数的名称</T>
    * 
    * @return 函数的名称
    */
   public String name(){
      return _name;
   }

   /**
    * <T>参数集对象为空时创建</T>
    * 
    * @return 参数集对象
    */
   public FSqlParameters parameters(){
      if(null == _parameters){
         _parameters = new FSqlParameters();
      }
      return _parameters;
   }

   /**
    * <T>解析函数体</T>
    * 
    * @param lines 包头的所有信息
    * @param start 函数开始的行数
    * @param bodyNode 包体的XML节点
    * @return 函数体结束的行数
    */
   public int parse(FStrings lines,
                    int start,
                    FXmlNode bodyNode){
      String line = "";
      FString sub = new FString();
      FString subParam = new FString();
      FString paramString = new FString();
      FString subReturn = new FString();
      int count = lines.count();
      boolean inDescription = false;
      try{
         for(int n = start; n < count; n++){
            line = lines.get(n);
            if(line.startsWith("--") && line.length() > 2){
               line = line.substring(2).trim();
               // 判断行是否包含@param
               if(line.contains(RSqlTag.PARAM)){
                  inDescription = true;
                  subParam.appendLine(line);
                  continue;
                  // 判断行是否包含@see
               }else if(line.contains(RSqlTag.SEE)){
                  inDescription = true;
                  see().parseSee(line);
                  continue;
                  // 判断行是否包含@return
               }else if(line.contains(RSqlTag.RETURN)){
                  inDescription = true;
                  subReturn.appendLine(line);
                  continue;
               }
               if(!inDescription){
                  sub.appendLine(line);
               }
            }
            if(line.startsWith(RSqlTag.FUNCTION)){
               // 函数的类型
               _type = "function";
               if(line.indexOf("(") >= 0){
                  _name = line.substring(line.indexOf(" "), line.indexOf("(")).trim();
               }else{
                  if(!line.contains("RETURN")){
                     _name = line.substring(line.indexOf(" "), line.length()).trim();
                  }else{
                     _name = line.substring(line.indexOf(" "), line.indexOf("RETURN")).trim();
                  }
               }
               // 获取函数注释的参数
               for(int i = n; i < count; i++){
                  paramString.append(lines.get(i));
                  if(lines.get(i).contains(";")){
                     break;
                  }
               }
               String param = paramString.toString();
               boolean bHasParams = (param.indexOf("(") >= 0);
               if(bHasParams){
                  setParameter(param, subParam);
               }
               // 查找函数体
               _code = bodyCode(bodyNode, param);
            }
            if(line.contains(";")){
               String des[] = sub.toString().trim().split("\n");
               int noteLine = 0;
               int length = 0;
               if(null != des){
                  length = des.length;
                  for(int i = length - 1; i >= 0; i--){
                     if(des[i].contains("-------")){
                        noteLine = i;
                        break;
                     }
                  }
                  for(int i = noteLine; i < length; i++){
                     if(!des[i].contains("-------")){
                        String enter = "";
                        if(i == length - 1){
                           enter = "";
                        }else{
                           enter = "\n";
                        }
                        // 函数详细说明
                        _description += des[i].trim() + enter;
                     }
                  }
               }
               // 返回参数
               returns().parse(subReturn.toString().trim(), line);
               subParam.clear();
               subReturn.clear();
               sub.clear();
               return n;
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse Functions error. (line={0})", line);
      }
      return count;
   }

   /**
    * <T>返回对象为空时创建</T>
    * 
    * @return 返回对象
    */
   public FSqlReturn returns(){
      if(null == _return){
         _return = new FSqlReturn(_context);
      }
      return _return;
   }

   /**
    * <T>调用对象为空时创建</T>
    * 
    * @return 调用对象
    */
   public FSqlSee see(){
      if(null == _see){
         _see = new FSqlSee(_context);
      }
      return _see;
   }

   /**
    * <T>解析函数中的所有参数</T>
    *
    * @param param 当前函数体中所有参数
    * @param subParam 注释当前函数参数的所有行
    */
   public void setParameter(String param,
                            FString subParam){
      String[] arParams = null;
      String[] subParams = null;
      arParams = RString.mid(param, "(", ")").split(",");
      subParams = subParam.toString().split("\n");
      for(int i = 0; i < arParams.length; i++){
         String paramString = arParams[i];
         String paramName = paramString.substring(0, paramString.indexOf(" ")).trim();
         String subArParam = paramString.substring(paramString.indexOf(" ")).trim();
         String type = "";
         String dataType = "";
         String description = "";
         if(subArParam.contains("IN") && subArParam.contains("OUT")){
            if(subArParam.indexOf("IN") > subArParam.indexOf("OUT")){
               type = "OUT IN";
               dataType = subArParam.substring(subArParam.indexOf("IN") + 2).trim();
            }else{
               type = "IN OUT";
               dataType = subArParam.substring(subArParam.indexOf("OUT") + 3).trim();
            }
         }else if(subArParam.contains("IN") && !subArParam.contains("OUT")){
            type = "IN";
            dataType = subArParam.substring(subArParam.indexOf("IN") + 2).trim();
         }else if(subArParam.contains("OUT") && !subArParam.contains("IN")){
            type = "OUT";
            dataType = subArParam.substring(subArParam.indexOf("OUT") + 3).trim();
         }
         int subCount = subParams.length;
         int subLength = subParam.length();
         if(0 < subLength && 0 < subCount){
            for(int j = 0; j < subParams.length; j++){
               String subItem = subParams[j].substring(RSqlTag.PARAM.length()).trim();
               if(null != subItem && !"".equals(subItem)){
                  String[] subItems = null;
                  subItems = RString.splitTwo(subItem, " ", true);
                  if(null != subItems){
                     if(subItems.length > 1){
                        if(paramName.equals(subItems[0])){
                           // 获取注释中参数的详细说明
                           description = subItems[1].trim();
                        }
                     }
                  }
               }
            }
         }
         FSqlParameter parameter = new FSqlParameter(_context);
         parameter.parse(description, paramName, type, dataType);
         if(RString.isEmpty(parameter.name())){
            throw new FFatalError("parameter Name is null. (line={0})", paramString);
         }
         parameters().push(parameter);
      }
   }
}
