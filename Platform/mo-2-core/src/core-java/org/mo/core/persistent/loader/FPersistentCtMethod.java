package org.mo.core.persistent.loader;

import org.mo.com.lang.reflect.FField;
import org.mo.com.lang.reflect.FMethod;
import org.mo.core.persistent.face.EType;

public class FPersistentCtMethod
{
   private String _alias;

   private EType _type;

   private EFieldType _datatype;

   private FField _field;

   private FMethod _getter;

   private String _fieldName;

   private String _name;

   private FMethod _setter;

   public EType type(){
      return _type;
   }

   public void setType(EType type){
      _type = type;
   }

   public FField field(){
      return _field;
   }

   public FMethod getter(){
      return _getter;
   }

   public String name(){
      return _name;
   }

   public void setField(FField field){
      this._field = field;
   }

   public void setGetter(FMethod getter){
      this._getter = getter;
   }

   public void setName(String name){
      this._name = name;
   }

   public void setSetter(FMethod setter){
      this._setter = setter;
   }

   public FMethod setter(){
      return _setter;
   }

   public EFieldType datatype(){
      return _datatype;
   }

   public void setDatatype(EFieldType datatype){
      _datatype = datatype;
   }

   public String alias(){
      return _alias;
   }

   public void setAlias(String alias){
      _alias = alias;
   }

   public String fieldName(){
      return _fieldName;
   }

   public void setFieldName(String name){
      _fieldName = name;
   }
}
