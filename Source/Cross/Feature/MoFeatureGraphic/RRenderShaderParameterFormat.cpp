#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param formatCd 默认内容
// @return 枚举内容
//============================================================
ERenderShaderParameterFormat RRenderShaderParameterFormat::Parse(TCharC* pValue, ERenderShaderParameterFormat formatCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("unknown")){
         return ERenderShaderParameterFormat_Unknown;
      }else if(code.Equals("float1")){
         return ERenderShaderParameterFormat_Float1;
      }else if(code.Equals("float2")){
         return ERenderShaderParameterFormat_Float2;
      }else if(code.Equals("float3")){
         return ERenderShaderParameterFormat_Float3;
      }else if(code.Equals("float4")){
         return ERenderShaderParameterFormat_Float4;
      }else if(code.Equals("float3x3")){
         return ERenderShaderParameterFormat_Float3x3;
      }else if(code.Equals("float4x3")){
         return ERenderShaderParameterFormat_Float4x3;
      }else if(code.Equals("float4x4")){
         return ERenderShaderParameterFormat_Float4x4;
      }else{
         MO_STATIC_FATAL("Parse shader parameter format failure. (value=%s)", pValue);
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
TCharC* RRenderShaderParameterFormat::Format(ERenderShaderParameterFormat formatCd){
   switch(formatCd){
      case ERenderShaderParameterFormat_Float1:
         return "Float1";
      case ERenderShaderParameterFormat_Float2:
         return "Float2";
      case ERenderShaderParameterFormat_Float3:
         return "Float3";
      case ERenderShaderParameterFormat_Float4:
         return "Float4";
      case ERenderShaderParameterFormat_Float3x3:
         return "Float3x3";
      case ERenderShaderParameterFormat_Float4x3:
         return "Float4x3";
      case ERenderShaderParameterFormat_Float4x4:
         return "Float4x4";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
