package org.mo.game.editor.common.source;

import org.mo.com.lang.FFatalError;

public class REditorCppSource{

   //============================================================
   public static String convertDataDisplay(String type,
                                           String typeName){
      if("Bool".equals(type)){
         return "Bool";
      }else if("Type".equals(type)){
         return typeName;
      }else if("Enum".equals(type)){
         return typeName;
      }else if("Set".equals(type)){
         return typeName;
      }else if("Int8".equals(type)){
         return "Int8";
      }else if("Int16".equals(type)){
         return "Int16";
      }else if("Int32".equals(type)){
         return "Int32";
      }else if("Int64".equals(type)){
         return "Int64";
      }else if("Uint8".equals(type)){
         return "Uint8";
      }else if("Uint16".equals(type)){
         return "Uint16";
      }else if("Uint32".equals(type)){
         return "Uint32";
      }else if("Uint64".equals(type)){
         return "Uint64";
      }else if("Float".equals(type)){
         return "Float";
      }else if("Double".equals(type)){
         return "Double";
      }else if("DateTime".equals(type)){
         return "DateTime";
      }else if("TimeSpan".equals(type)){
         return "TimeSpan";
      }else if("String".equals(type)){
         return "String";
      }else if("Struct".equals(type)){
         return typeName;
      }else if("Object".equals(type)){
         return typeName;
      }
      throw new FFatalError("Unknown type name. (type={0})", type);
   }

   //============================================================
   public static String convertDataType(String type,
                                        String typeName){
      if("Bool".equals(type)){
         return "Bool";
      }else if("Type".equals(type)){
         return typeName;
      }else if("Enum".equals(type)){
         return typeName;
      }else if("Set".equals(type)){
         return typeName;
      }else if("Int8".equals(type)){
         return "sbyte";
      }else if("Int16".equals(type)){
         return "short";
      }else if("Int32".equals(type)){
         return "int";
      }else if("Int64".equals(type)){
         return "long";
      }else if("Uint8".equals(type)){
         return "byte";
      }else if("Uint16".equals(type)){
         return "ushort";
      }else if("Uint32".equals(type)){
         return "uint";
      }else if("Uint64".equals(type)){
         return "ulong";
      }else if("Float".equals(type)){
         return "float";
      }else if("Double".equals(type)){
         return "double";
      }else if("DateTime".equals(type)){
         return "ulong";
      }else if("TimeSpan".equals(type)){
         return "ulong";
      }else if("String".equals(type)){
         return "TNetFix";
      }else if("Struct".equals(type)){
         return typeName;
      }else if("Object".equals(type)){
         return typeName;
      }
      throw new FFatalError("Unknown type name. (type={0})", type);
   }
}
