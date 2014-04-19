#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneLight, FInstance);

//============================================================
// <T>构造场景光源资源。</T>
//============================================================
FRs3dSceneLight::FRs3dSceneLight(){
   _optionTrack = EFalse;
   _shadowAmbientMin = 0.0f;
   _shadowAmbientMax = 0.0f;
   _shadowAmbientThick = 0.0f;
   _shadowAmbientRange = 0.0f;
   _shadowMerge1Base = 0.0f;
   _shadowMerge1Rate = 0.0f;
   _shadowMerge2Base = 0.0f;
   _shadowMerge2Rate = 0.0f;
   _material = FRs3dSceneMaterial::InstanceCreate();
   _camera = FRs3dSceneCamera::InstanceCreate();
}

//============================================================
// <T>析构场景光源资源。</T>
//============================================================
FRs3dSceneLight::~FRs3dSceneLight(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneLight::Unserialize(IDataInput* pInput){
   // 读取属性
   _typeName.UnserializeAutomatic(pInput);
   // 读取配置
   _optionTrack = RGeomFlag::ToBoolean(pInput->ReadInt32());
   // 读取阴影
   _shadow1.Unserialize(pInput);
   _shadow2.Unserialize(pInput);
   _shadow3.Unserialize(pInput);
   // 读取阴影环境
   _shadowAmbientMin = pInput->ReadFloat();
   _shadowAmbientMax = pInput->ReadFloat();
   _shadowAmbientThick = pInput->ReadFloat();
   _shadowAmbientRange = pInput->ReadFloat();
   _shadowMerge1Base = pInput->ReadFloat();
   _shadowMerge1Rate = pInput->ReadFloat();
   _shadowMerge2Base = pInput->ReadFloat();
   _shadowMerge2Rate = pInput->ReadFloat();
   // 读取材质
   _material->Unserialize(pInput);
   // 读取相机
   _camera->Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
