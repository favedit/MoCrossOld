#include "MoEoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEoRenderProgram, FRenderProgram);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FEoRenderProgram::FEoRenderProgram(){
   _programId = 0;
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FEoRenderProgram::~FEoRenderProgram(){
}

//============================================================
// <T>根据代码查找属性索引。</T>
//
// @param pCode 代码
// @return 属性索引
//============================================================
TInt FEoRenderProgram::FindAttribute(TCharC* pCode){
   MO_ASSERT(pCode);
   GLint slot = glGetAttribLocation(_programId, pCode);
   _pDevice->CheckError("glGetAttribLocation", "Find attribute location. (program_id=%d, code=%s)", _programId, pCode);
   return slot;
}

//============================================================
// <T>根据代码查找定义索引。</T>
//
// @param pCode 代码
// @return 定义索引
//============================================================
TInt FEoRenderProgram::FindDefine(TCharC* pCode){
   MO_ASSERT(pCode);
   GLint slot = glGetUniformLocation(_programId, pCode);
   _pDevice->CheckError("glGetUniformLocation", "Bind uniform location. (program_id=%d, code=%s)", _programId, pCode);
   return slot;
}

//============================================================
// <T>根据代码绑定属性到指定插槽位置。</T>
//
// @param slot 插槽
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FEoRenderProgram::BindAttribute(TInt slot, TCharC* pCode){
   MO_ASSERT(slot >= 0);
   MO_ASSERT(pCode);
   MO_ASSERT(_programId != 0);
   glBindAttribLocation(_programId, slot, pCode);
   TResult resultCd = _pDevice->CheckError("glBindAttribLocation", "Bind attribute location. (program_id=%d, slot=%d, code=%s)", _programId, slot, pCode);
   return resultCd;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderProgram::Setup(){
   _programId = glCreateProgram();
   // 创建顶点渲染器
   FEoRenderVertexShader* pVertexShader = FEoRenderVertexShader::InstanceCreate();
   pVertexShader->SetDevice(_pDevice);
   pVertexShader->Setup();
   _pVertexShader = pVertexShader;
   // 创建像素渲染器
   FEoRenderFragmentShader* pFragmentShader = FEoRenderFragmentShader::InstanceCreate();
   pFragmentShader->SetDevice(_pDevice);
   pFragmentShader->Setup();
   _pFragmentShader = pFragmentShader;
   MO_INFO("Create program success. (program_id=%d, vertex_id=%d, fragment=%d)",
         _programId, pVertexShader->RenderId().uint32, pFragmentShader->RenderId().uint32);
   return ESuccess;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderProgram::Build(){
   TResult resultCd = ESuccess;
   // 设置顶点渲染器
   GLuint vertexShaderId = _pVertexShader->RenderId().uint32;
   glAttachShader(_programId, vertexShaderId);
   resultCd = _pDevice->CheckError("glAttachShader", "Attach shader failure. (program_id=%d, shader_id=%d)", _programId, vertexShaderId);
   if(resultCd != ESuccess){
      return resultCd;
   }
   // 设置顶点渲染器
   FEoRenderFragmentShader* pFragmentShader = (FEoRenderFragmentShader*)_pFragmentShader;
   GLuint fragmentShaderId = pFragmentShader->RenderId().uint32;
   glAttachShader(_programId, fragmentShaderId);
   resultCd = _pDevice->CheckError("glAttachShader", "Attach shader failure. (program_id=%d, shader_id=%d)", _programId, fragmentShaderId);
   if(resultCd != ESuccess){
      return resultCd;
   }
   return resultCd;
}

//============================================================
// <T>关联处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderProgram::Link(){
   TResult resultCd = ESuccess;
   // 关联处理
   glLinkProgram(_programId);
   // 获得结果
   GLint linkStatus = GL_TRUE;
   glGetProgramiv(_programId, GL_LINK_STATUS, &linkStatus);
   if(linkStatus == GL_FALSE){
      GLsizei length;
      glGetProgramiv(_programId, GL_INFO_LOG_LENGTH, &length);
		GLchar* pReason = (GLchar*)MO_MEM_ALLOC(sizeof(GLchar) * length);
		glGetProgramInfoLog(_programId, length, NULL, pReason);
      MO_FATAL("Link program failure. (status=%d, reason=%s)", linkStatus, pReason);
      MO_MEM_FREE(pReason);
      // 释放程序
      glDeleteProgram(_programId);
      _programId = 0;
   }
   //............................................................
   // 校验程序
   glValidateProgram(_programId);
   // 获得结果
   GLint validateStatus = GL_TRUE;
   glGetProgramiv(_programId, GL_VALIDATE_STATUS, &validateStatus);
   if(validateStatus == GL_FALSE){
      GLsizei length;
      glGetProgramiv(_programId, GL_INFO_LOG_LENGTH, &length);
		GLchar* pReason = (GLchar*)MO_MEM_ALLOC(sizeof(GLchar) * length);
		glGetProgramInfoLog(_programId, length, NULL, pReason);
      MO_FATAL("Validate program failure. (status=%d, reason=%s)", validateStatus, pReason);
      MO_MEM_FREE(pReason);
      // 释放程序
      glDeleteProgram(_programId);
      _programId = 0;
   }
   //............................................................
   glFinish();
   resultCd = _pDevice->CheckError("glFinish",
         "Finish program link faliure. (program_id=%d)", _programId);
   if(resultCd != ESuccess){
      return resultCd;
   }
   //............................................................
   MO_INFO("Link program success. (program_id=%d)", _programId);
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderProgram::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderProgram::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderProgram::Dispose(){
   // 释放资源
   if(_programId != 0){
      glDeleteProgram(_programId);
      _programId = 0;
   }
   // 释放程序
   MO_DELETE(_pVertexShader);
   MO_DELETE(_pFragmentShader);
   return ESuccess;
}

MO_NAMESPACE_END
