/*
 * @(#)FSqlPackage.java
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
 * <T>解析包的实体类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlPackage
      extends FSqlObject
{
   // 定义包的作者
   private String _author;

   // 定义包的详细
   private String _description;

   // 定义包的方法集
   private FSqlMethods _methods;

   // 定义包的名称
   private String _name;

   // 定义包的属性集
   private FSqlProperties _properties;

   // 定义包的类型
   private String _type;

   // 定义包的版本
   private String _version;

   public FSqlPackage(FSqlParserContext context){
      super(context);
   }

   /* (non-Javadoc)
    * @see org.mo.com.lang.IDump#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.appendLine();
      info.appendLine("name       =[", _name, "]");
      info.appendLine("type       =[", _type, "]");
      info.appendLine("author     =[", _author, "]");
      info.appendLine("version    =[", version(), "]");
      return info;
   }

   /**
    * <T>判断该行是否是属性</T>
    * @param line
    * @return
    */
   public boolean isProperty(String line){
      boolean isProperty = false;
      if(!line.contains(":=")
            && (line.contains(" BOOLEAN") || line.contains(" INTEGER") || line.contains(" NUMBER") || line.contains(" FLOAT") || line.contains(" DATETIME") || line.contains(" DATE") || line.contains(" CHAR") || line.contains(" NCHAR")
                  || line.contains(" VARCHAR") || line.contains(" VARCHAR2") || line.contains(" LONG")) && line.contains(";")){
         isProperty = true;
      }
      return isProperty;
   }

   /**
    * <T>创建包节点并设置节点中的属性及子节点</T>
    * 
    * @return 返回包节点的XML
    */
   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Package");
      node.set("name", _name);
      node.set("type", "package");
      node.set("author", _author);
      node.set("version", _version);
      // 包的详细说明
      if(null != _description && !"".equals(_description)){
         FXmlNode descriptionNode = new FXmlNode("Description");
         descriptionNode.setText(_description);
         node.push(descriptionNode);
         node.set("has_description", "Y");
      }else{
         node.set("has_description", "N");
      }
      //  包的子节点属性集
      if(null != _properties){
         FXmlNode propertiesNode = new FXmlNode("Properties");
         for(FSqlProperty property : _properties.toObjects()){
            if(property.name() != null){
               propertiesNode.push(property.makeNode());
            }
         }
         node.push(propertiesNode);
         node.set("has_properties", "Y");
      }else{
         node.set("has_properties", "N");
      }
      //  包的子节点方法集
      if(null != _methods){
         for(FSqlMethod method : _methods.toObjects()){
            if(method.name() != null){
               node.push(method.makeNode());
            }
         }
         node.set("has_methods", "Y");
      }else{
         node.set("has_methods", "N");
      }
      return node;
   }

   /**
    * <T>当方法集为空时创建该对象</T>
    * 
    * @return 方法集
    */
   public FSqlMethods methods(){
      if(null == _methods){
         _methods = new FSqlMethods();
      }
      return _methods;
   }

   /**
    * <T>获取包的名称</T>
    * 
    * @return 包的名称
    */
   public String name(){
      return _name;
   }

   /**
    * <T>解析包中的属性及打开解析方法的通道</T>
    * 
    * @param packageName 包名称
    * @param linesBody 包体中的所有信息    
    * @param lines 包头的所有行数
    * @param start 包头的开始行数
    * @param end 包头的总行数
    */
   public void parse(String packageName,
                     FStrings linesBody,
                     FStrings lines,
                     int start,
                     int end){
      _name = packageName;
      // 建立方法
      FSqlMethod method = new FSqlMethod(_context);
      method.setName(_name);
      methods().push(method);
      boolean inFunction = false;
      boolean inParam = false;
      FString subParam = new FString();
      for(int n = start; n < end; n++){
         String line = lines.get(n);
         int k = 0;
         if(!line.startsWith(RSqlTag.FUNCTION) || !line.startsWith(RSqlTag.PROCEDURE)){
            // 如果该行中包含:=或CONSTANT，说明是属性部分
            if((line.contains(":=") && line.contains(";"))){
               inParam = true;
               k = n;
               // 如果属性的值定义在下一行的情况
            }else if(line.contains(":=")){
               if(n + 1 < end){
                  if(lines.get(n + 1).contains(";")){
                     inParam = true;
                     k = n + 1;
                  }
               }
               // 属性没有值的情况
            }else if(isProperty(line)){
               inParam = true;
               k = n;
            }
         }
         int noteCount = 0;
         // 分解属性部分
         if(inParam){
            for(int i = n; n > 0; n--){
               String noteLine = lines.get(i);
               // 如果该行开头不是"--- "，表示该属性没有注释，则跳出循环返回该行的行号
               if(!noteLine.startsWith("--- ")){
                  noteCount = n;
                  break;
                  // 如果该行开头"---"及包含"@"，表示该行是属性注释，则跳出循环返回该行的行号
               }else if(noteLine.startsWith("---") && noteLine.contains("@")){
                  noteCount = i;
                  break;
               }
            }
            // 对属性注释及属性行截取
            for(int i = noteCount; i <= k; i++){
               String noteLine = lines.get(i);
               if(noteLine.startsWith("---")){
                  noteLine = noteLine.substring(3).trim();
               }
               subParam.appendLine(noteLine);
            }
            String[] subParams = subParam.toString().split("\n");
            FSqlProperty property = new FSqlProperty(_context);
            // 对截取的属性注释部分进行分解
            property.parse(subParams);
            if(RString.isEmpty(property.name())){
               throw new FFatalError("Name is null. (line={0})", subParam.toString());
            }
            properties().push(property);
            inParam = false;
            subParam.clear();
            continue;
         }
         // 如果是函数或过程部分，则打开解析通道
         if(line.startsWith(RSqlTag.FUNCTION) || line.startsWith(RSqlTag.PROCEDURE)){
            inFunction = true;
         }
         // 分解函数信息
         if(inFunction){
            method.parse(lines, linesBody, n, lines.count());
            break;
         }
      }
   }

   /**
    * <T>当属性集为空时创建该对象</T>
    * 
    * @return 属性集
    */
   public FSqlProperties properties(){
      if(null == _properties){
         _properties = new FSqlProperties();
      }
      return _properties;
   }

   /**
    * <T>说明包的版本</T>
    * 
    * @return 版本
    */
   public String version(){
      return _version;
   }
}
