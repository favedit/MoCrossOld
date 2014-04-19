/*
 * @(#)FSqlReturn.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

/**
 * <T>解析函数返回值的类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlReturn
      extends FSqlParameter
{
   public FSqlReturn(FSqlParserContext context){
      super(context);
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.common.FSqlParameter#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[type=", _type);
      info.append(",description=", _description, "]");
      return info;
   }

   /**
    * <T>创建返回值节点并设置节点属性</T>
    * 
    * @return 返回值节点的XML
    */
   public FXmlNode makeReturnNode(){
      FXmlNode node = new FXmlNode("Return");
      node.set("type", _type);
      node.set("java_type", _javaType);
      node.set("description", _description);
      return node;
   }

   /**
    * <T>解析返回值的信息</T>
    * 
    * @param lineReturn 返回值注释的说明
    * @param line 返回值在代码中的数据类型
    */
   public void parse(String lineReturn,
                     String line){
      try{
         if(null != lineReturn && !"".equals(lineReturn)){
            _description = lineReturn.substring(RSqlTag.RETURN.length()).trim();
         }
         int length = line.indexOf("RETURN");
         _type = line.substring(length + 6, line.length() - 1).trim();
         // 获取JAVA类型
         _javaType = _context.findDataType(_type);
         _name = "return";
      }catch(Exception e){
         throw new FFatalError(e, "Parse return error. (line={0})", lineReturn + "," + line);
      }
   }
}
