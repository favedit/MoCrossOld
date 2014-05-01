#include "MoPd11Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd11RenderProgram, FRenderProgram);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPd11RenderProgram::FPd11RenderProgram(){
   //_programId = 0;
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPd11RenderProgram::~FPd11RenderProgram(){
}

//============================================================
// <T>根据代码查找属性索引。</T>
//
// @param pCode 代码
// @return 属性索引
//============================================================
TInt FPd11RenderProgram::FindAttribute(TCharC* pCode){
   MO_ASSERT(pCode);
   //GLint slot = glGetAttribLocation(_programId, pCode);
   //_pDevice->CheckError("glGetAttribLocation", "Find attribute location. (program_id=%d, code=%s)", _programId, pCode);
   //return slot;
   return 0;
}

//============================================================
// <T>根据代码查找定义索引。</T>
//
// @param pCode 代码
// @return 定义索引
//============================================================
TInt FPd11RenderProgram::FindDefine(TCharC* pCode){
   MO_ASSERT(pCode);
   //GLint slot = glGetUniformLocation(_programId, pCode);
   //_pDevice->CheckError("glGetUniformLocation", "Bind uniform location. (program_id=%d, code=%s)", _programId, pCode);
   //return slot;
   return 0;
}

//============================================================
// <T>根据代码绑定属性到指定插槽位置。</T>
//
// @param slot 插槽
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::BindAttribute(TInt slot, TCharC* pCode){
   MO_ASSERT(slot >= 0);
   MO_ASSERT(pCode);
   //MO_ASSERT(_programId != 0);
   //glBindAttribLocation(_programId, slot, pCode);
   //TResult resultCd = _pDevice->CheckError("glBindAttribLocation", "Bind attribute location. (program_id=%d, slot=%d, code=%s)", _programId, slot, pCode);
   //return resultCd;
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Setup(){
   MO_CHECK(_pDevice, return ENull);
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   // 创建顶点渲染器
   FPd11RenderVertexShader* pVertexShader = FPd11RenderVertexShader::InstanceCreate();
   pVertexShader->SetDevice(_pDevice);
   pVertexShader->SetProgram(this);
   pVertexShader->Setup();
   _pVertexShader = pVertexShader;
   // 创建像素渲染器
   FPd11RenderFragmentShader* pFragmentShader = FPd11RenderFragmentShader::InstanceCreate();
   pFragmentShader->SetDevice(_pDevice);
   pFragmentShader->SetProgram(this);
   pFragmentShader->Setup();
   _pFragmentShader = pFragmentShader;
   MO_INFO("Create program success.");
   return ESuccess;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Build(){
   MO_CHECK(_pDevice, return ENull);
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   //pRenderDevice->NativeDevice()->VSSetShader();
   //pRenderDevice->NativeDevice()->PSSetShader();

   FPd11RenderVertexShader* pVertexShader = _pVertexShader->Convert<FPd11RenderVertexShader>();
   TAny* pVertexData = pVertexShader->NativeData()->GetBufferPointer();
   TInt vertexDataLength = pVertexShader->NativeData()->GetBufferSize();

   D3D11_INPUT_ELEMENT_DESC layout[] = {
      {"POSITION",  0, DXGI_FORMAT_R32G32B32_FLOAT, 0, 0, D3D11_INPUT_PER_VERTEX_DATA, 0},
      {"TEXCOORD0", 1, DXGI_FORMAT_R32G32_FLOAT,    0, 0, D3D11_INPUT_PER_VERTEX_DATA, 0},
   };
   UINT numElements = ARRAYSIZE(layout);
   ID3D11InputLayout* _pInputLayout;
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateInputLayout(layout, numElements, pVertexData, vertexDataLength, &_pInputLayout);
   if(FAILED(dxResult)){
      MO_FATAL("Create input layout failure.");
      return EFailure;
   }

   TResult resultCd = ESuccess;
   // 设置顶点渲染器

   //GLuint vertexShaderId = _pVertexShader->RenderId().uint32;
   //glAttachShader(_programId, vertexShaderId);
   //resultCd = _pDevice->CheckError("glAttachShader", "Attach shader failure. (program_id=%d, shader_id=%d)", _programId, vertexShaderId);
   //if(resultCd != ESuccess){
   //   return resultCd;
   //}
   //// 设置顶点渲染器
   //FPd11RenderFragmentShader* pFragmentShader = (FPd11RenderFragmentShader*)_pFragmentShader;
   //GLuint fragmentShaderId = pFragmentShader->RenderId().uint32;
   //glAttachShader(_programId, fragmentShaderId);
   //resultCd = _pDevice->CheckError("glAttachShader", "Attach shader failure. (program_id=%d, shader_id=%d)", _programId, fragmentShaderId);
   //if(resultCd != ESuccess){
   //   return resultCd;
   //}
   return resultCd;
}

//============================================================
// <T>关联处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Link(){
   TResult resultCd = ESuccess;
  // // 关联处理
  // glLinkProgram(_programId);
  // // 获得结果
  // GLint linkStatus = GL_TRUE;
  // glGetProgramiv(_programId, GL_LINK_STATUS, &linkStatus);
  // if(linkStatus == GL_FALSE){
  //    GLsizei length;
  //    glGetProgramiv(_programId, GL_INFO_LOG_LENGTH, &length);
		//GLchar* pReason = (GLchar*)MO_MEM_ALLOC(sizeof(GLchar) * length);
		//glGetProgramInfoLog(_programId, length, NULL, pReason);
  //    MO_FATAL("Link program failure. (status=%d, reason=%s)", linkStatus, pReason);
  //    MO_MEM_FREE(pReason);
  //    // 释放程序
  //    glDeleteProgram(_programId);
  //    _programId = 0;
  // }
  // //............................................................
  // // 校验程序
  // glValidateProgram(_programId);
  // // 获得结果
  // GLint validateStatus = GL_TRUE;
  // glGetProgramiv(_programId, GL_VALIDATE_STATUS, &validateStatus);
  // if(validateStatus == GL_FALSE){
  //    GLsizei length;
  //    glGetProgramiv(_programId, GL_INFO_LOG_LENGTH, &length);
		//GLchar* pReason = (GLchar*)MO_MEM_ALLOC(sizeof(GLchar) * length);
		//glGetProgramInfoLog(_programId, length, NULL, pReason);
  //    MO_FATAL("Validate program failure. (status=%d, reason=%s)", validateStatus, pReason);
  //    MO_MEM_FREE(pReason);
  //    // 释放程序
  //    glDeleteProgram(_programId);
  //    _programId = 0;
  // }
  // //............................................................
  // glFinish();
  // resultCd = _pDevice->CheckError("glFinish",
  //       "Finish program link faliure. (program_id=%d)", _programId);
  // if(resultCd != ESuccess){
  //    return resultCd;
  // }
  // //............................................................
  // MO_INFO("Link program success. (program_id=%d)", _programId);
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Dispose(){
   //// 释放资源
   //if(_programId != 0){
   //   glDeleteProgram(_programId);
   //   _programId = 0;
   //}
   //// 释放程序
   //MO_DELETE(_pVertexShader);
   //MO_DELETE(_pFragmentShader);
   return ESuccess;
}

MO_NAMESPACE_END
