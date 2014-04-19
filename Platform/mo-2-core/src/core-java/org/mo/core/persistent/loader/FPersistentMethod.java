package org.mo.core.persistent.loader;

import org.mo.com.lang.reflect.FField;
import org.mo.com.lang.reflect.FMethod;
import org.mo.core.persistent.face.EType;

public class FPersistentMethod
{
   private String _alias;

   private EType _type;

   private EFieldType _datatype;

   private FField _field;

   private String _className;

   private FMethod _getter;

   private String _name;

   private String _fieldName;

   private FMethod _setter;

   public EType type(){
      return _type;
   }

   public void setType(EType type){
      _type = type;
   }

   public FMethod getter(){
      return _getter;
   }

   public String name(){
      return _name;
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

   public FField field(){
      return _field;
   }

   public void setField(FField field){
      _field = field;
   }

   public String fieldName(){
      return _fieldName;
   }

   public void setFieldName(String name){
      _fieldName = name;
   }

   public Class<?> fieldClass(){
      //      try{
      //         return _field.nativeField().getType().toClass();
      //      }catch(Exception e){
      //         throw new FFatalError(e);
      //      }
      return null;
   }

   public String className(){
      return _className;
   }

   public void setClassName(String name){
      _className = name;
   }
}
