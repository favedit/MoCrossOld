/*
 * @(#)FSqlProperty.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

/**
 * <T>解析属性的实体类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlProperty
      extends FSqlObject
{
   // 属性的详细说明
   private String _description;

   // 属性的名称
   private String _name;

   // 属性的类型
   private String _type;

   //属性的默认值
   private String _value;

   public FSqlProperty(FSqlParserContext context){
      super(context);
   }

   /* (non-Javadoc)
    * @see org.mo.com.lang.IDump#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.append("[name=", _name);
      info.append(",type=", _type);
      info.append(",value=", _value);
      info.append(",description=", _description, "]");
      return info;
   }

   /**
    * <T>创建属性节点并设置节点属性及子节点</T>
    * 
    * @return 属性节点的XML
    */
   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Property");
      node.set("name", _name);
      node.set("type", _type);
      node.set("value", _value);
      node.set("description", _description);
      return node;
   }

   /**
    * <T>获取属性的名称</T>
    * 
    * @return 属性的名称
    */
   public String name(){
      return _name;
   }

   /**
    * <T>分解属性</T>
    * 
    * @param items 关于该属性的所有信息
    */
   public void parse(String[] items){
      String subItem = "";
      String line = "";
      try{
         for(int i = 0; i < items.length; i++){
            // 判断是否是注释信息行
            if(!items[i].contains(":=") && !items[i].contains(";")){
               subItem += items[i].toString().trim();
            }else{
               line += items[i].toString().trim();
            }
         }
         // 获取属性的说明信息
         if(null != subItem && !"".equals(subItem)){
            subItem = subItem.substring(RSqlTag.PROPERTY.length());
            if(null != subItem && !"".equals(subItem)){
               String[] subItems = RString.splitTwo(subItem, " ", true);
               int length = subItems.length;
               if(length > 0){
                  _description = subItems[0];
               }
            }
         }
         if(null != line){
            _name = line.substring(0, line.indexOf(" ")).trim();
            // 判断属性是否赋值
            if(line.contains(":=")){
               _type = line.substring(_name.length(), line.indexOf(":=")).trim();
               _value = line.substring(line.indexOf(":=") + 2, line.indexOf(";")).trim();
            }else{
               _type = line.substring(_name.length(), line.indexOf(";")).trim();
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse property error. (itemsLength={0})", items.length);
      }
   }
}
