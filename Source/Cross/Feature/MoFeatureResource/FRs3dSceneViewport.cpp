#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneViewport, FInstance);

//============================================================
// <T>构造场景视角资源。</T>
//============================================================
FRs3dSceneViewport::FRs3dSceneViewport(){
   _angle = 0.0f;
   _near = 0.0f;
   _far = 0.0f;
}

//============================================================
// <T>析构场景视角资源。</T>
//============================================================
FRs3dSceneViewport::~FRs3dSceneViewport(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneViewport::Unserialize(IDataInput* pInput){
   _angle = pInput->ReadFloat();
   _near = pInput->ReadFloat();
   _far = pInput->ReadFloat() * 2.0f;
   return ESuccess;
}

MO_NAMESPACE_END
