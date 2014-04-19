/*
 * @(#)FSqlBody.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

/**
 * <T>解析包体的类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlBody
      extends FSqlObject
{
   // 定义包体方法代码的名称
   private String _code;

   /**
    * <T>包体方法的名称定义</T>
    */
   public String _name;

   // 定义包体方法参数的名称
   private FSqlParameters _parameters;

   // 定义包体方法类型的名称
   private String _type;

   public FSqlBody(FSqlParserContext context){
      super(context);
   }

   /* (non-Javadoc)
    * @see org.mo.com.lang.IDump#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * <T>获取方法对应的代码</T>
    * 
    * @param start 方法的开始行数
    * @param end 包体的总行数
    * @return 返回当前方法结束的行数
    */
   public int getCode(int start,
                      int end,
                      FStrings lines){
      FString subLines = new FString();
      for(int i = start; i < end; i++){
         String lineBody = lines.get(i).toString();
         String bodyName = "";
         if(lineBody.startsWith(RSqlTag.FUNCTION) || lineBody.startsWith(RSqlTag.PROCEDURE)){
            // 判断方法的类型
            if(lineBody.startsWith(RSqlTag.FUNCTION)){
               _type = "function";
            }else{
               _type = "procedure";
            }
            /// 判断当前行是否存在'('
            if(lineBody.indexOf("(") >= 0){
               /// 有则获取'('前面的方法名称
               _name = lineBody.substring(lineBody.indexOf(" "), lineBody.indexOf("(")).trim();
            }else{
               /// 无则表明方法没有参数直接获取空格前面的方法名称
               _name = lineBody.substring(lineBody.indexOf(" ")).trim();
            }
         }
         subLines.appendLine(lineBody);
         /// 判断方法体是否到了最后
         if(lineBody.startsWith("END") && lineBody.contains(bodyName)){
            _code = subLines.toString();
            subLines.clear();
            return i;
         }
      }
      return end;
   }

   /**
    * <T>创建包体节点并设置节点中的属性及子节点</T>
    * 
    * @return 返回包体节点的XML
    */
   public FXmlNode makeNode(){
      /// 创建Body节点
      FXmlNode node = new FXmlNode("Boby");
      /// 在Body节点中设置name属性
      node.set("name", _name);
      /// 在Body节点中设置type属性
      node.set("type", _type);
      if(null != _parameters){
         /// 创建Parameters节点
         FXmlNode parametersNode = new FXmlNode("Parameters");
         for(FSqlParameter parameter : _parameters.toObjects()){
            if(null != parameter._name){
               parametersNode.push(parameter.makeNode());
            }
         }
         /// 将Parameters节点增加到Body节点中
         node.push(parametersNode);
         node.set("has_parameters", "Y");
      }else{
         /// 则在Body节点中设置has_parameters属性,值为'N'，表明_parameters为空。
         node.set("has_parameters", "N");
      }
      /// 函数体代码
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
    * <T>获得方法的名词</T>
    *
    * @return 名称
    */
   public String name(){
      return _name;
   }

   /**
    * <T>参数对象为空时创建</T>
    * 
    * @return 参数对象集
    */
   public FSqlParameters parameters(){
      if(null == _parameters){
         _parameters = new FSqlParameters();
      }
      return _parameters;
   }

   /**
    * <T>获取包体方法的代码及参数</T>
    * 
    * @param lines 包体的所有代码
    * @param n 方法开始的行数
    * @return 返回当前方法结束的行数
    */
   public int parser(FStrings lines,
                     int n){
      int count = lines.count();
      FString paramString = new FString();
      int j = getCode(n, count, lines);
      /// 获取方法体中的所有参数，并拼成字符串
      for(int i = n; i < count; i++){
         paramString.append(lines.get(i).trim());
         /// 如果当前行以IS开头，说明参数声明结束
         if(lines.get(i).startsWith("IS")){
            break;
         }
      }
      if(!"".equals(paramString.toString().trim())){
         /// 调用解析参数的方法
         parserParameter(paramString.toString());
      }
      return j;
   }

   /**
    * <T>解析方法中的参数</T>
    *
    * @param param 方法中所有参数拼成的字符串
    */
   public void parserParameter(String param){
      String[] arParams = null;
      /// 将获取字符串param在'('与')'中间部分，用','划分成数组
      arParams = RString.mid(param, "(", ")").split(",");
      for(int i = 0; i < arParams.length; i++){
         String paramString = arParams[i];
         /// 获取参数的名称
         String paramName = paramString.substring(0, paramString.indexOf(" ")).trim();
         String subArParam = paramString.substring(paramString.indexOf(" ")).trim();
         String type = "";
         String dataType = "";
         /// 判断参数里是否包含IN类型与OUT类型
         if(subArParam.contains("IN") && subArParam.contains("OUT")){
            if(subArParam.indexOf("IN") > subArParam.indexOf("OUT")){
               /// 获取参数的类型
               type = "OUT IN";
               /// 获取参数的数据类型
               dataType = subArParam.substring(subArParam.indexOf("IN") + 2).trim();
            }else{
               type = "IN OUT";
               dataType = subArParam.substring(subArParam.indexOf("OUT") + 3).trim();
            }
            /// 判断参数里是否包含IN类型，不包含OUT类型
         }else if(subArParam.contains("IN") && !subArParam.contains("OUT")){
            type = "IN";
            dataType = subArParam.substring(subArParam.indexOf("IN") + 2).trim();
            /// 判断参数里是否包含OUT类型，不包含IN类型
         }else if(subArParam.contains("OUT") && !subArParam.contains("IN")){
            type = "OUT";
            dataType = subArParam.substring(subArParam.indexOf("OUT") + 3).trim();
         }
         /// 新建一个参数对象
         FSqlParameter parameter = new FSqlParameter(_context);
         /// 对获取到的参数进行解析，最终建立节点将参数设置到节点的属性中
         parameter.parse("", paramName, type, dataType);
         if(RString.isEmpty(parameter.name())){
            throw new FFatalError("parameter Name is null. (line={0})", paramString);
         }
         parameters().push(parameter);
      }
   }
}
