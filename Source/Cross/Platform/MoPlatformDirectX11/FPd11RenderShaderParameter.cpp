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
   return ESuccess;
}

//============================================================
// <T>获得数据内容。</T>
//
// @param pData 数据
// @param capacity 容量
// @return 处理结果
//============================================================
TResult FPd11RenderShaderParameter::Get(TAny* pData, TInt capacity){
   return ESuccess;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FPd11RenderShaderParameter::Set(TAny* pData, TInt length){
   ID3D11ShaderReflectionConstantBuffer* piBuffer = _piVariable->GetBuffer();
   return ESuccess;
}

MO_NAMESPACE_END
