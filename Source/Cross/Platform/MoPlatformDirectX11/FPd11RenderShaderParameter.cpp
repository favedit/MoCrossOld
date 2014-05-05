#include "MoPd11Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd11RenderShaderParameter, FRenderShaderParameter);

//============================================================
// <T>构造渲染器参数。</T>
//============================================================
FPd11RenderShaderParameter::FPd11RenderShaderParameter(){
   MO_CLEAR(_piVariable);
}

//============================================================
// <T>析构渲染器参数。</T>
//============================================================
FPd11RenderShaderParameter::~FPd11RenderShaderParameter(){
}

//============================================================
// <T>关联本地信息。</T>
//
// @param piVariable 参数接口
// @return 处理结果
//============================================================
TResult FPd11RenderShaderParameter::LinkNative(ID3D11ShaderReflectionVariable* piVariable){
   // 获得描述
   D3D11_SHADER_VARIABLE_DESC descriptor;
   HRESULT dxResult = piVariable->GetDesc(&descriptor);
   if(FAILED(dxResult)){
      MO_FATAL("Get variable description failure.");
      return EFailure;
   }
   // 设置名称
   _name = descriptor.Name;
   // 判断是否被是被使用
   if((descriptor.uFlags & D3D_SVF_USED) == D3D_SVF_USED){
      _statusUsed = ETrue;
   }
   // 设置属性
   _slot = descriptor.StartOffset;
   _size = descriptor.Size;
   return ESuccess;
}

MO_NAMESPACE_END
