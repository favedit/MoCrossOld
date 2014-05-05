#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param scriptCd 默认内容
// @return 枚举内容
//============================================================
ERenderScript RRenderScript::Parse(TCharC* pValue, ERenderScript scriptCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("unknown")){
         return ERenderScript_Unknown;
      }else if(code.Equals("hlsl")){
         return ERenderScript_Hlsl;
      }else if(code.Equals("glsl")){
         return ERenderScript_Glsl;
      }else{
         MO_STATIC_FATAL("Parse render device failure. (value=%s)", pValue);
      }
   }
   return scriptCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param scriptCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderScript::Format(ERenderScript scriptCd){
   switch(scriptCd){
      case ERenderScript_Hlsl:
         return "Hlsl";
      case ERenderScript_Glsl:
         return "Glsl";
   }
   return "Unknown";
}

MO_NAMESPACE_END
