#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dScenePass, FInstance);

//============================================================
// <T>构造场景技术过程。</T>
//============================================================
FRs3dScenePass::FRs3dScenePass(){
}

//============================================================
// <T>析构场景技术过程。</T>
//============================================================
FRs3dScenePass::~FRs3dScenePass(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dScenePass::Unserialize(IDataInput* pInput){
   _name.UnserializeAutomatic(pInput);
   _renderTargetSize.Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
