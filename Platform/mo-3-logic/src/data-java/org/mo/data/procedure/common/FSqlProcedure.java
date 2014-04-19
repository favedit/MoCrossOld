/*
 * @(#)FSqlProcedure.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

/**
 * <T>解析过程的的实体类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
/**
 * @author Administrator
 *
 */
public class FSqlProcedure
      extends FSqlFunction
{
   // 定义函数的参数集
   private FSqlParameters _parameters;

   public FSqlProcedure(FSqlParserContext context){
      super(context);
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.common.FSqlFunction#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append(",name=", _name);
      info.append(",type=", _type);
      info.append(",description=", _description, "]");
      return info;
   }

   /**
    * <T>创建过程节点并设置属性及子节点</T>
    * 
    * @return 过程节点的XML
    */
   @Override
   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Procedure");
      if(null == _nameId){
         _nameId = "";
      }
      node.set("name_id", _nameId);
      node.set("name", _name);
      node.set("type", _type);
      // 过程的详细说明
      if(null != _description && !"".equals(_description)){
         FXmlNode descriptionNode = new FXmlNode("Description");
         descriptionNode.setText(_description);
         node.push(descriptionNode);
         node.set("has_description", "Y");
      }else{
         node.set("has_description", "N");
      }
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
      // 过程体代码
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
    * <T>取得过程的名称</T>
    * 
    * @return 过程的名称
    */
   @Override
   public String name(){
      return _name;
   }

   /**
    * <T>参数集对象为空时创建</T>
    * 
    * @return 参数集对象
    */
   @Override
   public FSqlParameters parameters(){
      if(null == _parameters){
         _parameters = new FSqlParameters();
      }
      return _parameters;
   }

   /**
    * <T>解析过程体</T>
    * 
    * @param lines 包头的所有信息
    * @param start 函数开始的行数
    * @param bodyNode 包体的XML节点
    * @return 函数体结束的行数
    */
   @Override
   public int parse(FStrings lines,
                    int start,
                    FXmlNode bodyNode){
      String line = "";
      FString sub = new FString();
      FString subLines = new FString();
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
               }
               if(!inDescription){
                  sub.appendLine(line);
               }
            }
            if(line.startsWith(RSqlTag.PROCEDURE)){
               // 过程的类型
               _type = "procedure";
               if(line.indexOf("(") >= 0){
                  // 过程名称
                  _name = line.substring(line.indexOf(" "), line.indexOf("(")).trim();
               }else{
                  _name = line.substring(9, line.length() - 1).trim();
               }
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
               // 过程代码
               _code = bodyCode(bodyNode, param);
            }
            if(line.contains(";")){
               // 详细说明
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
               subParam.clear();
               subReturn.clear();
               subLines.clear();
               sub.clear();
               return n;
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse procedure error. (line={0})", line);
      }
      return count;
   }
}
