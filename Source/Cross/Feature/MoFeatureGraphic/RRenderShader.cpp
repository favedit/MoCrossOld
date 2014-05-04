#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param shaderCd 默认内容
// @return 枚举内容
//============================================================
ERenderShader RRenderShader::Parse(TCharC* pValue, ERenderShader shaderCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("unknown")){
         return ERenderShader_Unknown;
      }else if(code.Equals("vertex")){
         return ERenderShader_Vertex;
      }else if(code.Equals("fragment")){
         return ERenderShader_Fragment;
      }else{
         MO_STATIC_FATAL("Parse shader failure. (value=%s)", pValue);
      }
   }
   return shaderCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param shaderCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderShader::Format(ERenderShader shaderCd){
   switch(shaderCd){
      case ERenderShader_Vertex:
         return "Vertex";
      case ERenderShader_Fragment:
         return "Fragment";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
