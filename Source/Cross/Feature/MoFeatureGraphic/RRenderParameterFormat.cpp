#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param formatCd 默认内容
// @return 枚举内容
//============================================================
ERenderParameterFormat RRenderParameterFormat::Parse(TCharC* pValue, ERenderParameterFormat formatCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("unknown")){
         return ERenderParameterFormat_Unknown;
      }else if(code.Equals("float1")){
         return ERenderParameterFormat_Float1;
      }else if(code.Equals("float2")){
         return ERenderParameterFormat_Float2;
      }else if(code.Equals("float3")){
         return ERenderParameterFormat_Float3;
      }else if(code.Equals("float4")){
         return ERenderParameterFormat_Float4;
      }else if(code.Equals("float3x3")){
         return ERenderParameterFormat_Float3x3;
      }else if(code.Equals("float4x3")){
         return ERenderParameterFormat_Float4x3;
      }else if(code.Equals("float4x4")){
         return ERenderParameterFormat_Float4x4;
      }else{
         MO_STATIC_FATAL("Parse parameter format failure. (value=%s)", pValue);
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
TCharC* RRenderParameterFormat::Format(ERenderParameterFormat formatCd){
   switch(formatCd){
      case ERenderParameterFormat_Float1:
         return "Float1";
      case ERenderParameterFormat_Float2:
         return "Float2";
      case ERenderParameterFormat_Float3:
         return "Float3";
      case ERenderParameterFormat_Float4:
         return "Float4";
      case ERenderParameterFormat_Float3x3:
         return "Float3x3";
      case ERenderParameterFormat_Float4x3:
         return "Float4x3";
      case ERenderParameterFormat_Float4x4:
         return "Float4x4";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
