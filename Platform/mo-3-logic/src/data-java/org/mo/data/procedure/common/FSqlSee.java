/**
 * @(#)FSqlSee.java
 * 
 * Copyright 2008 linekong Corporation. All Rights Reserved.
 * 
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

/**
 * <T>方法中调用其他类的方法</T>
 * 
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlSee
      extends FSqlParameter
{
   // 调用的方法名称
   private String _method;

   public FSqlSee(FSqlParserContext context){
      super(context);
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.common.FSqlParameter#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",type=", _type);
      info.append(",data_type=", "See");
      info.append(",method=", _method, "]");
      return info;
   }

   /**
    * <T>创建调用节点并设置节点属性</T>
    * 
    * @return 调用节点的XML
    */
   public FXmlNode makeSeeNode(){
      FXmlNode node = new FXmlNode("See");
      node.set("name", _name);
      node.set("type", _type.toString());
      node.set("data_type", "See");
      node.set("method", _method);
      return node;
   }

   /**
    * <T>解析调用的方法</T>
    * 
    * @param line 注释调用的信息行
    */
   public void parseSee(String line){
      try{
         line = line.substring(RSqlTag.SEE.length()).trim();
         String[] items = RString.splitTwo(line, ".", true);
         if(null == items){
            // 调用的类型
            _type = line;
            _name = "see";
         }else if(items.length == 2){
            _type = items[0];
            // 调用的方法
            _method = items[1];
            _name = "see";
         }else{
            throw new FFatalError("Parse see error. (line={0})", line);
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse see error. (line={0})", line);
      }
   }
}
