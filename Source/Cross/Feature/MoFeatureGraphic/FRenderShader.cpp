#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderShader, FRenderInstance);

//============================================================
// <T>构造渲染器。</T>
//============================================================
FRenderShader::FRenderShader(){
   _shaderCd = ERenderShader_Unknown;
   MO_CLEAR(_pProgram);
   _pSource = MO_CREATE(FRenderSource);
   _pCompileSource = MO_CREATE(FRenderSource);
}

//============================================================
// <T>析构渲染器。</T>
//============================================================
FRenderShader::~FRenderShader(){
   MO_DELETE(_pSource);
   MO_DELETE(_pCompileSource);
}

//============================================================
// <T>建立代码。</T>
//
// @param pSource 来源
// @return 处理结果
//============================================================
TResult FRenderShader::Build(TCharC* pSource){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   // 设置内容
   _pSource->Assign(pSource);
   // 变换脚本
   FRenderShaderTransformer* pTransformer = _pDevice->ShaderTransformer();
   if(pTransformer != NULL){
      pTransformer->Convert(_pCompileSource, _pSource);
   }
   // 优化脚本
   FRenderShaderOptimizer* pOptimizer = _pDevice->ShaderOptimizer();
   if(pOptimizer != NULL){
      pOptimizer->Convert(_pCompileSource, _pCompileSource);
   }
   // 编译脚本
   if(_pCompileSource->IsEmpty()){
      Compile(_pSource->MemoryC());
   }else{
      Compile(_pCompileSource->MemoryC());
   }
   return ESuccess;
}

MO_NAMESPACE_END
