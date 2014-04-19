package org.mo.core.parser.java;

import org.mo.com.lang.FString;

public class FJavaParameter
{
   private String _type;

   private String _name;

   public FJavaParameter(){
   }

   public String name(){
      return _name;
   }

   public String type(){
      return _type;
   }

   public void setName(String name){
      _name = name;
   }

   public void setType(String type){
      _type = type;
   }

   public FString dump(){
      FString dump = new FString();
      dump.append(_type);
      dump.append(" ");
      dump.append(_name);
      return dump;
   }
}
