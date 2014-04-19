package org.mo.game.editor.common.source;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class REditorAsSource
{

   //============================================================
   public static String convertBaseType(String type){
      if("DateTime".equals(type)){
         return "Uint64";
      }else if("TimeSpan".equals(type)){
         return "Uint64";
      }
      return type;
   }

   //============================================================
   public static int convertDataSize(String type){
      if("Enum".equals(type)){
         return 4;
      }else if("Set".equals(type)){
         return 4;
      }else if("Type".equals(type)){
         return 0;
      }else if("Bool".equals(type)){
         return 4;
      }else if("Int8".equals(type)){
         return 1;
      }else if(type.equals("Int16")){
         return 2;
      }else if(type.equals("Int32")){
         return 4;
      }else if(type.equals("Int64")){
         return 8;
      }else if(type.equals("Uint8")){
         return 1;
      }else if(type.equals("Uint16")){
         return 2;
      }else if(type.equals("Uint32")){
         return 4;
      }else if(type.equals("Uint64")){
         return 8;
      }else if(type.equals("Float")){
         return 4;
      }else if(type.equals("Double")){
         return 8;
      }else if(type.equals("DateTime")){
         return 8;
      }else if(type.equals("TimeSpan")){
         return 8;
      }else if(type.equals("String")){
         return 0;
      }else if(type.equals("Struct")){
         return 0;
      }else if(type.equals("Object")){
         return 0;
      }
      throw new FFatalError("Unknown type name. (type={0})", type);
   }

   //============================================================
   public static String convertDataType(String type,
                                        String typeName){
      if("Enum".equals(type)){
         return "uint";
      }else if("Set".equals(type)){
         return "uint";
      }else if("Type".equals(type)){
         return typeName;
      }else if("Bool".equals(type)){
         return "int";
      }else if("Int8".equals(type)){
         return "int";
      }else if(type.equals("Int16")){
         return "int";
      }else if(type.equals("Int32")){
         return "int";
      }else if(type.equals("Int64")){
         return "int";
      }else if(type.equals("Uint8")){
         return "uint";
      }else if(type.equals("Uint16")){
         return "uint";
      }else if(type.equals("Uint32")){
         return "uint";
      }else if(type.equals("Uint64")){
         return "uint";
      }else if(type.equals("Float")){
         return "Number";
      }else if(type.equals("Double")){
         return "Number";
      }else if(type.equals("DateTime")){
         return "Uint64";
      }else if(type.equals("TimeSpan")){
         return "Uint64";
      }else if(type.equals("String")){
         return "String";
      }else if(type.equals("Struct")){
         return typeName;
      }else if(type.equals("Object")){
         return typeName;
      }
      throw new FFatalError("Unknown type name. (type={0})", type);
   }

   //============================================================
   public static void convertNodeType(FXmlNode config){
      // 读取类型信息
      String type = config.get("type", null);
      String typeName = config.get("type_name", null);
      // 读取基本信息
      String langType = convertDataType(type, typeName);
      int langSize = convertDataSize(type);
      String baseType = convertBaseType(type);
      // 检查是否设置过AS类型
      String typeAs = config.get("type_as", null);
      if(!RString.isEmpty(typeAs)){
         langType = typeAs;
      }
      config.set("type", type);
      config.set("lang_type", langType);
      config.set("lang_size", langSize);
      config.set("base_size", baseType);
   }
}
