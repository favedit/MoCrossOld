package org.mo.script.as.parser;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;

public class RAsParser
{
   public static final String Splitters = " \r\n";

   public static final String[] Operator2 = new String[] { " == ", " != ", " += ", " -= ", " *= ", " /= ", " %= " };

   public static final boolean isSplitter(char value){
      return (-1 != Splitters.indexOf(value));
   }

   public static final boolean isComment(String line){
      return line.startsWith("//");
   }

   public static boolean isOperator2(String source){
      for(String item : Operator2){
         if(RString.contains(source, item)){
            return true;
         }
      }
      return false;
   }

   public static String skipStrings(String source){
      while(RString.contains(source, '"')){
         String find = RString.midMatch(source, '"');
         if(null == find){
            throw new FFatalError("Find string is null.");
         }
         source = RString.replace(source, "\"" + find + "\"", "");
      }
      return source;
   }

   public static String makeClassName(String name){
      if(RString.startsWith(name, "E")){
         return "EAs" + name.substring(1);
      }else if(RString.startsWith(name, "S")){
         return "SAs" + name.substring(1);
      }else if(RString.startsWith(name, "I")){
         return "IAs" + name.substring(1);
      }else if(RString.startsWith(name, "F")){
         return "FAs" + name.substring(1);
      }else if(RString.startsWith(name, "R")){
         return "RAs" + name.substring(1);
      }
      throw new FFatalError("Make class name.");
   }

   public static String makeTypeName(String name){
      if(!RString.isEmpty(name)){
         name = name.trim();
         switch(name){
            case "void":
               return "void";
            case "Boolean":
               return "boolean";
            case "int":
               return "int";
            case "uint":
               return "int";
            case "Number":
               return "double";
            case "String":
               return "String";
            case "*":
               return "Object";
            case "Object":
               return "Object";
            case "Array":
               return "Object";
            case "...":
               return "Object...";
            case "Vector.<int>":
               return "Vector.<int>";
            case "Vector.<uint>":
               return "Vector.<uint>";
            case "Vector.<Number>":
               return "Vector.<Number>";
            case "XML":
               return "Object";
            case "Function":
               return "Object";
            case "Class":
               return "Object";
         }
         if(name.startsWith("Vector.<")){
            return name;
         }
      }
      return null;
   }
}
