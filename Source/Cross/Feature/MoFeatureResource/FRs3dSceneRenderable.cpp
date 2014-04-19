#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneRenderable, FInstance);

//============================================================
// <T>构造场景渲染资源。</T>
//============================================================
FRs3dSceneRenderable::FRs3dSceneRenderable(){
}

//============================================================
// <T>析构场景渲染资源。</T>
//============================================================
FRs3dSceneRenderable::~FRs3dSceneRenderable(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneRenderable::Unserialize(IDataInput* pInput){
   _name.UnserializeAutomatic(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
