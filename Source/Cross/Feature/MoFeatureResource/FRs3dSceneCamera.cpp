#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneCamera, FInstance);

//============================================================
// <T>构造场景相机资源。</T>
//============================================================
FRs3dSceneCamera::FRs3dSceneCamera(){
   _centerFront = 0.0f;
   _centerBack = 0.0f;
   _focalNear = 0.0f;
   _focalFar = 0.0f;
   _viewport = FRs3dSceneViewport::InstanceCreate();
}

//============================================================
// <T>析构场景相机资源。</T>
//============================================================
FRs3dSceneCamera::~FRs3dSceneCamera(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneCamera::Unserialize(IDataInput* pInput){
   // 读取属性
   _typeName.UnserializeAutomatic(pInput);
   // 读取中心
   _centerFront = pInput->ReadFloat();
   _centerBack = pInput->ReadFloat();
   // 读取位置
   _position.Unserialize(pInput);
   // 读取方向
   _direction.Unserialize(pInput);
   // 读取焦平面
   _focalNear = pInput->ReadFloat();
   _focalFar = pInput->ReadFloat();
   // 读取视角
   _viewport->Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
