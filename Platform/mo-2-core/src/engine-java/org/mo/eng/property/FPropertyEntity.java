package org.mo.eng.property;

import org.mo.com.lang.face.AName;

public class FPropertyEntity
{
   @AName("id")
   private String _id;

   @AName("name")
   private String _name;

   private String _value;

   public FPropertyEntity(){
   }

   public String id(){
      return _id;
   }

   public String name(){
      return _name;
   }

   public void setId(String _id){
      this._id = _id;
   }

   public void setName(String _name){
      this._name = _name;
   }

   public void setValue(String _value){
      this._value = _value;
   }

   public String value(){
      return _value;
   }
}
