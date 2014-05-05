#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param deviceCd 默认内容
// @return 枚举内容
//============================================================
ERenderDevice RRenderDevice::Parse(TCharC* pValue, ERenderDevice deviceCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("unknown")){
         return ERenderDevice_Unknown;
      }else if(code.Equals("directx9")){
         return ERenderDevice_DirectX9;
      }else if(code.Equals("directx10")){
         return ERenderDevice_DirectX10;
      }else if(code.Equals("directx11")){
         return ERenderDevice_DirectX11;
      }else if(code.Equals("opengl2")){
         return ERenderDevice_OpenGL2;
      }else if(code.Equals("opengl3")){
         return ERenderDevice_OpenGL3;
      }else if(code.Equals("opengl4")){
         return ERenderDevice_OpenGL4;
      }else if(code.Equals("opengles2")){
         return ERenderDevice_OpenGLEs2;
      }else if(code.Equals("opengles3")){
         return ERenderDevice_OpenGLEs3;
      }else{
         MO_STATIC_FATAL("Parse render device failure. (value=%s)", pValue);
      }
   }
   return deviceCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param deviceCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderDevice::Format(ERenderDevice deviceCd){
   switch(deviceCd){
      case ERenderDevice_DirectX9:
         return "DirectX9";
      case ERenderDevice_DirectX10:
         return "DirectX10";
      case ERenderDevice_DirectX11:
         return "DirectX11";
      case ERenderDevice_OpenGL2:
         return "OpenGL2";
      case ERenderDevice_OpenGL3:
         return "OpenGL3";
      case ERenderDevice_OpenGL4:
         return "OpenGL4";
      case ERenderDevice_OpenGLEs2:
         return "OpenGLEs2";
      case ERenderDevice_OpenGLEs3:
         return "OpenGLEs3";
   }
   return "Unknown";
}

MO_NAMESPACE_END
