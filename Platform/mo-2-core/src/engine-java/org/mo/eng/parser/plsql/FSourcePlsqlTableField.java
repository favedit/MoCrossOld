package org.mo.eng.parser.plsql;

import org.mo.com.lang.IAttributes;

public class FSourcePlsqlTableField
{
   private String _id;

   private String _name;

   private String _isNull;

   private String _dataType;

   private String _dataLength;

   private String _dataScale;

   public String dataLength(){
      return _dataLength;
   }

   public String dataScale(){
      return _dataScale;
   }

   public String dataType(){
      return _dataType;
   }

   public String id(){
      return _id;
   }

   public String isNull(){
      return _isNull;
   }

   public void loadConfig(IAttributes attributes){
      _name = attributes.get("table_name");
   }

   public String name(){
      return _name;
   }

   public void setDataLength(String dataLength){
      _dataLength = dataLength;
   }

   public void setDataScale(String dataScale){
      _dataScale = dataScale;
   }

   public void setDataType(String dataType){
      _dataType = dataType;
   }

   public void setId(String id){
      _id = id;
   }

   public void setIsNull(String isNull){
      _isNull = isNull;
   }

   public void setName(String name){
      _name = name;
   }
}
