#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param cullModeCd 默认内容
// @return 枚举内容
//============================================================
ERenderCullMode RRenderCullMode::Parse(TCharC* pValue, ERenderCullMode cullModeCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("none")){
         return ERenderCullMode_None;
      }else if(code.Equals("front")){
         return ERenderCullMode_Front;
      }else if(code.Equals("back")){
         return ERenderCullMode_Back;
      }else if(code.Equals("blth")){
         return ERenderCullMode_Both;
      }else{
         MO_STATIC_FATAL("Parse cull mode failure. (value=%s)", pValue);
      }
   }
   return cullModeCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param cullModeCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderCullMode::Format(ERenderCullMode cullModeCd){
   switch(cullModeCd){
      case ERenderCullMode_None:
         return "None";
      case ERenderCullMode_Front:
         return "Front";
      case ERenderCullMode_Back:
         return "Back";
      case ERenderCullMode_Both:
         return "Both";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
