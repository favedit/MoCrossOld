/*
 * @(#)FSqlParameter.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;

/**
 * <T>解析参数的的实体类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlParameter
      extends FSqlObject
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FSqlParameter.class);

   @ALink
   protected IDatabaseConsole _databaseConsole;

   // 参数的数据类型
   protected String _dataType;

   // 参数的详细说明
   protected String _description;

   // 参数的方向
   protected String _direction;

   // 参数的JAVA类型
   protected String _javaType;

   // 参数的名称
   protected String _name;

   // 参数的类型
   protected String _type;

   // 参数的默认值
   protected String _value;

   public FSqlParameter(FSqlParserContext context){
      super(context);
   }

   /* (non-Javadoc)
    * @see org.mo.com.lang.IDump#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",direction=", _direction);
      info.append(",data_type=", _dataType);
      info.append(",java_type=", _javaType);
      info.append(",value=", _value);
      info.append(",description=", _description, "]");
      return info;
   }

   /**
    * <T>创建参数节点并设置节点属性及子节点</T>
    * 
    * @return 参数节点的XML
    */
   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Parameter");
      node.set("name", _name);
      node.set("direction", _direction);
      node.set("data_type", _dataType);
      node.set("java_type", _javaType);
      node.set("value", _value);
      node.set("description", _description);
      return node;
   }

   /**
    * <T>获取名称</T>
    * 
    * @return 名称
    */
   public String name(){
      return _name;
   }

   /**
    * <T>解析参数的所有信息</T>
    * 
    * @param line 详细信息行
    * @param name 参数的名称
    * @param type 参数的方向
    * @param dataType 参数的数据类型
    */
   public void parse(String line,
                     String name,
                     String direction,
                     String dataType){
      try{
         _name = name;
         _direction = direction;
         _description = line;
         if(null != dataType && !"".equals(dataType)){
            if(dataType.contains("DEFAULT")){
               String[] items = dataType.split(" ");
               if(items.length > 2){
                  _dataType = items[0];
                  _value = items[2];
                  // 获取JAVA数据类型
                  _javaType = _context.findDataType(items[0]);
               }
            }else{
               _dataType = dataType;
               _value = "";
               _javaType = _context.findDataType(dataType);
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse parameter error. (line={0})", "");
      }
   }
}
