#include "MoPoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPoRenderProgram, FRenderProgram);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPoRenderProgram::FPoRenderProgram(){
   _nativeCode = 0;
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPoRenderProgram::~FPoRenderProgram(){
}

//============================================================
// <T>根据代码查找属性索引。</T>
//
// @param pCode 代码
// @return 属性索引
//============================================================
TInt FPoRenderProgram::FindAttribute(TCharC* pCode){
   MO_ASSERT(pCode);
   GLint slot = glGetAttribLocation(_nativeCode, pCode);
   _pDevice->CheckError("glGetAttribLocation", "Find attribute location. (program_id=%d, code=%s)", _nativeCode, pCode);
   return slot;
}

//============================================================
// <T>根据代码查找定义索引。</T>
//
// @param pCode 代码
// @return 定义索引
//============================================================
TInt FPoRenderProgram::FindDefine(TCharC* pCode){
   MO_ASSERT(pCode);
   GLint slot = glGetUniformLocation(_nativeCode, pCode);
   _pDevice->CheckError("glGetUniformLocation", "Bind uniform location. (program_id=%d, code=%s)", _nativeCode, pCode);
   return slot;
}

//============================================================
// <T>根据代码绑定属性到指定插槽位置。</T>
//
// @param slot 插槽
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FPoRenderProgram::BindAttribute(TInt slot, TCharC* pCode){
   MO_ASSERT(slot >= 0);
   MO_ASSERT(pCode);
   MO_ASSERT(_nativeCode != 0);
   glBindAttribLocation(_nativeCode, slot, pCode);
   TResult resultCd = _pDevice->CheckError("glBindAttribLocation", "Bind attribute location. (program_id=%d, slot=%d, code=%s)", _nativeCode, slot, pCode);
   return resultCd;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderProgram::Setup(){
   _nativeCode = glCreateProgram();
   // 创建顶点渲染器
   FPoRenderVertexShader* pVertexShader = FPoRenderVertexShader::InstanceCreate();
   pVertexShader->SetDevice(_pDevice);
   pVertexShader->Setup();
   _vertexShader = pVertexShader;
   // 创建像素渲染器
   FPoRenderFragmentShader* pFragmentShader = FPoRenderFragmentShader::InstanceCreate();
   pFragmentShader->SetDevice(_pDevice);
   pFragmentShader->Setup();
   _fragmentShader = pFragmentShader;
   MO_INFO("Create program success. (program_id=%d, vertex_id=%d, fragment=%d)",
         _nativeCode, pVertexShader->NativeCode(), pFragmentShader->NativeCode());
   return ESuccess;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderProgram::Build(){
   TResult resultCd = ESuccess;
   // 设置顶点渲染器
   FPoRenderVertexShader* pVertexShader = _vertexShader->Convert<FPoRenderVertexShader>();
   GLuint vertexShaderId = pVertexShader->NativeCode();
   glAttachShader(_nativeCode, vertexShaderId);
   resultCd = _pDevice->CheckError("glAttachShader", "Attach shader failure. (program_id=%d, shader_id=%d)", _nativeCode, vertexShaderId);
   if(resultCd != ESuccess){
      return resultCd;
   }
   // 设置顶点渲染器
   FPoRenderFragmentShader* pFragmentShader = _fragmentShader->Convert<FPoRenderFragmentShader>();
   GLuint fragmentShaderId = pFragmentShader->NativeCode();
   glAttachShader(_nativeCode, fragmentShaderId);
   resultCd = _pDevice->CheckError("glAttachShader", "Attach shader failure. (program_id=%d, shader_id=%d)", _nativeCode, fragmentShaderId);
   if(resultCd != ESuccess){
      return resultCd;
   }
   // 设置属性集合
   if(!_attributes.IsEmpty()){
      TInt count = _attributes.Count();
      for(TInt n = 0; n < count; n++){
         FRenderProgramAttribute* pAttribute = _attributes.Get(n);
         TCharC* pName = pAttribute->Name();
         glBindAttribLocation(_nativeCode, n, pName);
         resultCd = _pDevice->CheckError("glBindAttribLocation", "Bind attribute location. (program_id=%d, slot=%d, name=%s)", _nativeCode, n, pName);
         if(resultCd != ESuccess){
            return resultCd;
         }
      }
   }
   return resultCd;
}

//============================================================
// <T>关联处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderProgram::Link(){
   TResult resultCd = ESuccess;
   // 关联处理
   glLinkProgram(_nativeCode);
   // 获得结果
   GLint linkStatus = GL_TRUE;
   glGetProgramiv(_nativeCode, GL_LINK_STATUS, &linkStatus);
   if(linkStatus == GL_FALSE){
      GLsizei length;
      glGetProgramiv(_nativeCode, GL_INFO_LOG_LENGTH, &length);
		GLchar* pReason = (GLchar*)MO_MEM_ALLOC(sizeof(GLchar) * length);
		glGetProgramInfoLog(_nativeCode, length, NULL, pReason);
      MO_FATAL("Link program failure. (status=%d, reason=%s)", linkStatus, pReason);
      MO_MEM_FREE(pReason);
      // 释放程序
      glDeleteProgram(_nativeCode);
      _nativeCode = 0;
   }
   //............................................................
   // 校验程序
   glValidateProgram(_nativeCode);
   // 获得结果
   GLint validateStatus = GL_TRUE;
   glGetProgramiv(_nativeCode, GL_VALIDATE_STATUS, &validateStatus);
   if(validateStatus == GL_FALSE){
      GLsizei length;
      glGetProgramiv(_nativeCode, GL_INFO_LOG_LENGTH, &length);
		GLchar* pReason = (GLchar*)MO_MEM_ALLOC(sizeof(GLchar) * length);
		glGetProgramInfoLog(_nativeCode, length, NULL, pReason);
      MO_FATAL("Validate program failure. (status=%d, reason=%s)", validateStatus, pReason);
      MO_MEM_FREE(pReason);
      // 释放程序
      glDeleteProgram(_nativeCode);
      _nativeCode = 0;
   }
   //............................................................
   // 结束处理
   glFinish();
   resultCd = _pDevice->CheckError("glFinish",
         "Finish program link faliure. (program_id=%d)", _nativeCode);
   if(resultCd != ESuccess){
      return resultCd;
   }
   //............................................................
   // 关联常量集合
   if(!_parameters.IsEmpty()){
      TInt count = _parameters.Count();
      for(TInt n = 0; n < count; n++){
         FRenderProgramParameter* pParameter = _parameters.Get(n);
         TCharC* pName = pParameter->Name();
         GLint slot = glGetUniformLocation(_nativeCode, pName);
         resultCd = _pDevice->CheckError("glGetUniformLocation", "Find parameter slot. (program_id=%d, name=%s, slot=%d)", _nativeCode, pName, slot);
         if(resultCd != ESuccess){
            return resultCd;
         }
         pParameter->SetSlot(slot);
         if(slot != -1){
            pParameter->SetStatusUsed(ETrue);
         }
      }
   }
   // 关联属性集合
   if(!_attributes.IsEmpty()){
      TInt count = _attributes.Count();
      for(TInt n = 0; n < count; n++){
         FRenderProgramAttribute* pAttribute = _attributes.Get(n);
         TCharC* pName = pAttribute->Name();
         GLint slot = glGetAttribLocation(_nativeCode, pName);
         resultCd = _pDevice->CheckError("glGetAttribLocation", "Find attribute slot. (program_id=%d, name=%s, slot=%d)", _nativeCode, pName, slot);
         if(resultCd != ESuccess){
            return resultCd;
         }
         pAttribute->SetSlot(slot);
         if(slot != -1){
            pAttribute->SetStatusUsed(ETrue);
         }
      }
   }
   // 关联取样器集合
   if(!_samplers.IsEmpty()){
      TInt count = _samplers.Count();
      for(TInt n = 0; n < count; n++){
         FRenderProgramSampler* pSampler = _samplers.Get(n);
         TCharC* pName = pSampler->Name();
         GLint slot = glGetUniformLocation(_nativeCode, pName);
         resultCd = _pDevice->CheckError("glGetUniformLocation", "Find sampler slot. (program_id=%d, name=%s, slot=%d)", _nativeCode, pName, slot);
         if(resultCd != ESuccess){
            return resultCd;
         }
         pSampler->SetSlot(slot);
         if(slot != -1){
            pSampler->SetStatusUsed(ETrue);
         }
      }
      TInt samplerIndex = 0;
      for(TInt n = 0; n < count; n++){
         FRenderProgramSampler* pSampler = _samplers.Get(n);
         if(pSampler->IsStatusUsed()){
            pSampler->SetIndex(samplerIndex++);
         }
      }
   }
   //............................................................
   MO_INFO("Link program success. (program_id=%d)", _nativeCode);
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderProgram::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderProgram::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderProgram::Dispose(){
   // 释放资源
   if(_nativeCode != 0){
      glDeleteProgram(_nativeCode);
      _nativeCode = 0;
   }
   return ESuccess;
}

MO_NAMESPACE_END
