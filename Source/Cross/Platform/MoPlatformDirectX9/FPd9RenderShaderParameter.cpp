#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderShaderParameter, FRenderShaderParameter);

//============================================================
// <T>构造渲染器参数。</T>
//============================================================
FPd9RenderShaderParameter::FPd9RenderShaderParameter(){
   //MO_CLEAR(_piVariable);
}

//============================================================
// <T>析构渲染器参数。</T>
//============================================================
FPd9RenderShaderParameter::~FPd9RenderShaderParameter(){
}

//============================================================
// <T>关联本地信息。</T>
//
// @param piVariable 参数接口
// @return 处理结果
//============================================================
//TResult FPd9RenderShaderParameter::LinkNative(ID3D9ShaderReflectionVariable* piVariable){
//   // 获得描述
//   D3D9_SHADER_VARIABLE_DESC descriptor;
//   HRESULT dxResult = piVariable->GetDesc(&descriptor);
//   if(FAILED(dxResult)){
//      MO_FATAL("Get variable description failure.");
//      return EFailure;
//   }
//   // 设置名称
//   _name = descriptor.Name;
//   // 判断是否被是被使用
//   if((descriptor.uFlags & D3D_SVF_USED) == D3D_SVF_USED){
//      _statusUsed = ETrue;
//   }
//   // 设置属性
//   _slot = descriptor.StartOffset;
//   _size = descriptor.Size;
//   return ESuccess;
//}

MO_NAMESPACE_END
