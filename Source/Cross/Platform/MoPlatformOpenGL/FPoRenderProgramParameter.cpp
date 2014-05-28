#include "MoPoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPoRenderProgramParameter, FRenderProgramParameter);

//============================================================
// <T>构造渲染器参数。</T>
//============================================================
FPoRenderProgramParameter::FPoRenderProgramParameter(){
}

//============================================================
// <T>析构渲染器参数。</T>
//============================================================
FPoRenderProgramParameter::~FPoRenderProgramParameter(){
}

//============================================================
// <T>获得数据内容。</T>
//
// @param pData 数据
// @param capacity 容量
// @return 处理结果
//============================================================
TResult FPoRenderProgramParameter::Get(TAny* pData, TInt capacity){
   MO_FATAL_UNSUPPORT();
   return EUnsupport;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FPoRenderProgramParameter::Set(TAny* pData, TInt length){
   _pDevice->BindConst(_shaderCd, _slot, _formatCd, pData, length);
   return EUnsupport;
}

MO_NAMESPACE_END
