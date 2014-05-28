#include "MoPoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPoRenderFragmentShader, FRenderVertexShader);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPoRenderFragmentShader::FPoRenderFragmentShader(){
   _nativeCode = 0;
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPoRenderFragmentShader::~FPoRenderFragmentShader(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderFragmentShader::Setup(){
   _nativeCode = glCreateShader(GL_FRAGMENT_SHADER);
   TResult resultCd = _pDevice->CheckError("glCreateShader", "Create fragment shader failure. (shader_id=%d)", _nativeCode);
   return resultCd;
}

//============================================================
// <T>编译处理。</T>
//
// @param pSource 代码字符串
// @return 处理结果
//============================================================
TResult FPoRenderFragmentShader::Compile(TCharC* pSource){
   // 上传代码
   const GLchar* source[1];
   source[0] = (const GLchar*)pSource;
   glShaderSource(_nativeCode, 1, source, NULL);
   // 编译处理
   glCompileShader(_nativeCode);
   // 测试编译结果
   GLint status;
   glGetShaderiv(_nativeCode, GL_COMPILE_STATUS, &status);
   if(!status){
      // 获得代码
      GLsizei sourceLength;
      glGetShaderiv(_nativeCode, GL_SHADER_SOURCE_LENGTH, &sourceLength);
		GLchar* pShaderSource = MO_TYPES_ALLOC(GLchar, sourceLength);
		glGetShaderSource(_nativeCode, sourceLength, NULL, pShaderSource);
      // 获得原因
      GLsizei reasonLength = 0;
      glGetShaderiv(_nativeCode, GL_INFO_LOG_LENGTH, &reasonLength);
      GLchar* pReason = MO_TYPES_ALLOC(GLchar, reasonLength);
      glGetShaderInfoLog(_nativeCode, reasonLength, NULL, pReason);  
      MO_FATAL("Create fragment shader failure. (status=%d)\n%s\n%s", status, pReason, pShaderSource);
      // 释放资源
      MO_DELETE(pShaderSource);
      MO_DELETE(pReason);
      glDeleteShader(_nativeCode); 
      _nativeCode = 0;
   }else{
      MO_INFO("Create fragment shader success. (status=%d)\n%s", status, pSource);
   }
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderFragmentShader::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderFragmentShader::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderFragmentShader::Dispose(){
   TResult resultCd = ESuccess;
   if(_nativeCode != 0){
      glDeleteShader(_nativeCode);
      resultCd = _pDevice->CheckError("glCreateShader", "Delete fragment shader failure. (shader_id=%d)", _nativeCode);
      _nativeCode = 0;
   }
   return resultCd;
}

MO_NAMESPACE_END
