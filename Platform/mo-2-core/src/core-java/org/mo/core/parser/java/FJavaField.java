package org.mo.core.parser.java;

public class FJavaField
{
   private String _type;

   private String _name;

   public FJavaField(){
   }

   public String getName(){
      return _name;
   }

   public String getType(){
      return _type;
   }

   public void setName(String name){
      _name = name;
   }

   public void setType(String type){
      _type = type;
   }
}
