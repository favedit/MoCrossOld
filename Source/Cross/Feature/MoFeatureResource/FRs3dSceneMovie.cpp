#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneMovie, FInstance);

//============================================================
// <T>构造资源场景动画。</T>
//============================================================
FRs3dSceneMovie::FRs3dSceneMovie(){
   _interval = 0;
}

//============================================================
// <T>析构资源场景动画。</T>
//============================================================
FRs3dSceneMovie::~FRs3dSceneMovie(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneMovie::Unserialize(IDataInput* pInput){
   _typeName.UnserializeAutomatic(pInput);
   _interval = pInput->ReadInt32();
   _rotation.Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
