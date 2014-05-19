#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param formatCd 默认内容
// @return 枚举内容
//============================================================
ERenderAttributeFormat RRenderAttributeFormat::Parse(TCharC* pValue, ERenderAttributeFormat formatCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("unknown")){
         return ERenderAttributeFormat_Unknown;
      }else if(code.Equals("float1")){
         return ERenderAttributeFormat_Float1;
      }else if(code.Equals("float2")){
         return ERenderAttributeFormat_Float2;
      }else if(code.Equals("float3")){
         return ERenderAttributeFormat_Float3;
      }else if(code.Equals("float4")){
         return ERenderAttributeFormat_Float4;
      }else if(code.Equals("byte4")){
         return ERenderAttributeFormat_Byte4;
      }else if(code.Equals("byte4normal")){
         return ERenderAttributeFormat_Byte4Normal;
      }else{
         MO_STATIC_FATAL("Parse shader attribute format failure. (value=%s)", pValue);
      }
   }
   return formatCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param formatCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderAttributeFormat::Format(ERenderAttributeFormat formatCd){
   switch(formatCd){
      case ERenderAttributeFormat_Float1:
         return "Float1";
      case ERenderAttributeFormat_Float2:
         return "Float2";
      case ERenderAttributeFormat_Float3:
         return "Float3";
      case ERenderAttributeFormat_Float4:
         return "Float4";
      case ERenderAttributeFormat_Byte4:
         return "Byte4";
      case ERenderAttributeFormat_Byte4Normal:
         return "Byte4Normal";
      default:
         break;
   }
   return "Unknown";
}

//============================================================
// <T>计算格式的字节大小。</T>
//
// @param formatCd 枚举内容
// @return 字节大小
//============================================================
TInt RRenderAttributeFormat::CalculateSize(ERenderAttributeFormat formatCd){
   switch(formatCd){
      case ERenderAttributeFormat_Float1:
         return sizeof(TFloat) * 1;
      case ERenderAttributeFormat_Float2:
         return sizeof(TFloat) * 2;
      case ERenderAttributeFormat_Float3:
         return sizeof(TFloat) * 3;
      case ERenderAttributeFormat_Float4:
         return sizeof(TFloat) * 4;
      case ERenderAttributeFormat_Byte4:
         return sizeof(TByte) * 4;
      case ERenderAttributeFormat_Byte4Normal:
         return sizeof(TByte) * 4;
   }
   return 0;
}

MO_NAMESPACE_END
