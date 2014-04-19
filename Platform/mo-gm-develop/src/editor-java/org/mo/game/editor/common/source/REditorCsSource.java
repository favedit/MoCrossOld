package org.mo.game.editor.common.source;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class REditorCsSource{

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
   public static String convertDataType(String type,
                                        String typeName){
      if("Bool".equals(type)){
         return "bool";
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
         return "string";
      }else if("Struct".equals(type)){
         return typeName;
      }else if("Object".equals(type)){
         return typeName;
      }else if("Bytes".equals(type)){
         return "FNetBytes";
      }
      throw new FFatalError("Unknown type name. (type={0}, type_name={1})", type, typeName);
   }

   //============================================================
   public static void convertNodeType(FXmlNode config){
      // 读取类型信息
      String type = config.get("type");
      String typeName = config.get("type_name");
      // 读取基本信息
      String baseType = convertBaseType(type);
      String langType = convertDataType(type, typeName);
      // 检查是否设置过CS类型
      String typeCs = config.get("type_cs");
      if(!RString.isEmpty(typeCs)){
         langType = typeCs;
      }
      config.set("type", type);
      config.set("base_type", baseType);
      config.set("lang_type", langType);
   }
}
