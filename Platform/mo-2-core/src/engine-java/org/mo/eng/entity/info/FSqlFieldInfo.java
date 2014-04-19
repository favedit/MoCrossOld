package org.mo.eng.entity.info;

import org.mo.com.lang.FString;
import org.mo.eng.entity.face.EFieldKey;
import org.mo.eng.entity.face.EFieldType;

public class FSqlFieldInfo
      implements
         ISqlFieldInfo
{
   private static String VALUE_NULL = "NULL";

   private boolean _auto = false;

   private boolean _delete = true;

   private boolean _empty = false;

   private boolean _insert = true;;

   private EFieldKey _key = EFieldKey.None;;

   private String _name;;

   private EFieldType _type = EFieldType.String;

   private boolean _update = true;

   public FSqlFieldInfo(){
   }

   @Override
   public boolean auto(){
      return _auto;
   }

   public boolean delete(){
      return _delete;
   }

   public FString dump(){
      FString dump = new FString();
      dump.append("FieldInfo ");
      dump.append("[ name=", _name);
      dump.append(", key=", _key);
      dump.append(", type=", _type);
      dump.append(", empty=", _empty);
      dump.append(", auto=", _auto);
      dump.append(", insert=", _insert);
      dump.append(", update=", _update);
      dump.append(", delete=", _delete);
      dump.append(" ]");
      return dump;
   }

   public boolean empty(){
      return _empty;
   }

   @Override
   public String format(String value){
      if(null == value){
         return VALUE_NULL;
      }
      if(0 == value.length()){
         return VALUE_NULL;
      }
      if(EFieldType.Char == _type){
         return "'" + value + "'";
      }else if(EFieldType.String == _type){
         return "'" + value + "'";
      }else if(EFieldType.Date == _type){
         return "'" + value + "'";
      }else if(EFieldType.Integer == _type){
         return value;
      }
      return value;
   }

   public boolean insert(){
      return _insert;
   }

   public EFieldKey key(){
      return _key;
   }

   @Override
   public String name(){
      return _name;
   }

   public void setAuto(boolean auto){
      _auto = auto;
   }

   public void setDelete(boolean value){
      _delete = value;
   }

   public void setEmpty(boolean empty){
      _empty = empty;
   }

   public void setInsert(boolean value){
      _insert = value;
   }

   public void setKey(EFieldKey key){
      _key = key;
   }

   public void setName(String name){
      _name = name;
   }

   public void setType(EFieldType type){
      _type = type;
   }

   public void setUpdate(boolean value){
      _update = value;
   }

   @Override
   public String toString(){
      return _name;
   }

   @Override
   public EFieldType type(){
      return _type;
   }

   public boolean update(){
      return _update;
   }
}
