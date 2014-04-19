/*
 * @(#)FSqlMethod.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

/**
 * <T>解析所有方法的实体类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlMethod
      extends FSqlObject
{
   // 包体集
   private FSqlBodies _bodies;

   // 方法中的函数集
   private FSqlFunctions _functions;

   // 方法的名称
   private String _name;

   // 方法中的过程集
   private FSqlProcedures _procedures;

   public FSqlMethod(FSqlParserContext context){
      super(context);
   }

   /**
    * <T>包体集为空时创建该对象<T>
    * 
    * @return 包体集
    */
   public FSqlBodies bodies(){
      if(null == _bodies){
         _bodies = new FSqlBodies();
      }
      return _bodies;
   }

   /* (non-Javadoc)
    * @see org.mo.com.lang.IDump#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.appendLine();
      if(null != _functions){
         for(FSqlFunction function : _functions){
            info.appendLine();
            info.appendLine();
            info.appendLine(RDump.LINE_L2);
            info.append(function.dump());
         }
      }
      return info;
   }

   /**
    * <T>在包头查找方法注释的开始行数</T>
    * 
    * @param lines 包头的所有行的信息
    * @param start 方法开始的行数
    * @return 方法开始注释的行数
    */
   protected int findMehod(FStrings lines,
                           int start){
      int count = lines.count();
      for(int n = start; n < count; n++){
         String line = lines.get(n).trim();
         if(line.startsWith(RSqlTag.FUNCTION) || line.startsWith(RSqlTag.PROCEDURE)){
            for(int i = n - 1; i >= 0; i--){
               line = lines.get(i);
               // 如果该行最后以冒号为结束符，则下行是注释开始的行数
               if(line.endsWith(";")){
                  return i + 1;
               }
            }
            return n;
         }
      }
      return -1;
   }

   /**
    * <T>函数集为空是创建该对象</T>
    * 
    * @return 函数集
    */
   public FSqlFunctions functions(){
      if(null == _functions){
         _functions = new FSqlFunctions();
      }
      return _functions;
   }

   /**
    * <T>创建方法节点并设置节点中的属性及子节点</T>
    * 
    * @return 方法节点的XML
    */
   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Methods");
      if(null != _functions){
         for(FSqlFunction function : _functions.toObjects()){
            if(null != function._name){
               node.push(function.makeNode());
            }
         }
         // 如果对象为不空，则创建属性，Y表示对象不空
         node.set("has_functions", "Y");
      }else{
         // 如果对象为空，则创建属性，N表示对象为空
         node.set("has_functions", "N");
      }
      if(null != _procedures){
         for(FSqlProcedure procedure : _procedures.toObjects()){
            if(null != procedure._name){
               node.push(procedure.makeNode());
            }
         }
         node.set("has_procedures", "Y");
      }else{
         node.set("has_procedures", "N");
      }
      return node;
   }

   /**
    * <T>取得方法的名称</T>
    * 
    * @return 方法的名称
    */
   public String name(){
      return _name;
   }

   /**
    * <T>解析包头中的方法及包体中的方法</T>
    * 
    * @param lines 包头的所有行的信息
    * @param linesBody 包体的所有行的信息
    * @param start 包头的方法开始行数
    * @param end 包头总行数
    */
   protected void parse(FStrings lines,
                        FStrings linesBody,
                        int start,
                        int end){
      String line = null;
      String lineDump = null;
      // 解析包体
      parserBody(linesBody);
      // 创建包体根节点
      FXmlNode node = new FXmlNode();
      if(null != _bodies){
         for(FSqlBody body : _bodies.toObjects()){
            if(null != body._name){
               node.push(body.makeNode());
            }
         }
         node.set("has_body", "Y");
      }else{
         node.set("has_body", "N");
      }
      // 分解函数和过程
      for(int n = start; n < end; n++){
         line = lines.get(n).trim();
         lineDump = line.toUpperCase();
         if(lineDump.startsWith("--")){
            continue;
         }
         if(lineDump.startsWith(RSqlTag.FUNCTION)){
            int find = findMehod(lines, n);
            FSqlFunction function = new FSqlFunction(_context);
            // 分解函数
            n = function.parse(lines, find, node);
            if(RString.isEmpty(function.name())){
               throw new FFatalError("Name is null. (line={0})", line);
            }
            // 增加函数对象到函数集
            functions().push(function);
         }else if(lineDump.startsWith(RSqlTag.PROCEDURE)){
            int find = findMehod(lines, n);
            FSqlProcedure precedure = new FSqlProcedure(_context);
            // 分解过程
            n = precedure.parse(lines, find, node);
            if(RString.isEmpty(precedure.name())){
               throw new FFatalError("Procedure Name is null. (line={0})", line);
            }
            // 增加过程对象到过程集
            procedures().push(precedure);
         }
      }
   }

   /**
    * <T>解析包体的所有方法获取方法体的代码</T>
    * 
    * @param lines 包体中的所有行的信息
    */
   public void parserBody(FStrings lines){
      int count = lines.count();
      boolean inMethod = false;
      for(int n = 0; n < count; n++){
         String line = lines.get(n);
         // 判断是否是方法，如果是则进行解析
         if(line.startsWith(RSqlTag.FUNCTION) || line.startsWith(RSqlTag.PROCEDURE)){
            inMethod = true;
         }
         if(inMethod){
            FSqlBody body = new FSqlBody(_context);
            // 分解方法
            n = body.parser(lines, n);
            if(RString.isEmpty(body.name())){
               throw new FFatalError("Body Name is null. (line={0})", line);
            }
            // 增加包体里函数或过程代码到包体集
            bodies().push(body);
            inMethod = false;
         }
      }
   }

   /**
    * <T>过程集为空是创建该对象</T>
    * 
    * @return 过程集
    */
   public FSqlProcedures procedures(){
      if(null == _procedures){
         _procedures = new FSqlProcedures();
      }
      return _procedures;
   }

   /**
    * <T>设置方法的名称</T>
    * 
    * @param name 方法的名称
    */
   public void setName(String name){
      _name = name;
   }
}
