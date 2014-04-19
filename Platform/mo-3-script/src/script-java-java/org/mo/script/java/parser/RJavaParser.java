package org.mo.script.java.parser;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;

public class RJavaParser
{
   public static final String Splitters = " \r\n";

   public static final String CommentLine = "//" + RString.repeat('=', 60);

   public static final boolean isSplitter(char value){
      return (-1 != Splitters.indexOf(value));
   }

   public static final boolean isComment(String line){
      return line.startsWith("//");
   }

   public static final boolean isCommentLine(String line){
      return CommentLine.equals(line);
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
      switch(name){
         case "void":
            return "void";
         case "boolean":
            return "Boolean";
         case "int":
            return "int";
         case "double":
            return "Number";
         case "Date":
            return "Object";
         case "String":
            return "String";
         case "Object":
            return "Object";
         case "Array":
            return "Object";
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
      return null;
   }
}
