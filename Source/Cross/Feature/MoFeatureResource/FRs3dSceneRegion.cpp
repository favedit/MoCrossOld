#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneRegion, FInstance);

//============================================================
// <T>构造场景技术资源。</T>
//============================================================
FRs3dSceneRegion::FRs3dSceneRegion(){
   _camera = FRs3dSceneCamera::InstanceCreate();
   _light = FRs3dSceneLight::InstanceCreate();
}

//============================================================
// <T>析构场景技术资源。</T>
//============================================================
FRs3dSceneRegion::~FRs3dSceneRegion(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneRegion::Unserialize(IDataInput* pInput){
   // 读取颜色
   _color.Unserialize(pInput);
   // 读取颜色级别
   _colorLevel.Unserialize(pInput);
   // 读取雾化
   _fog.Unserialize(pInput);
   // 读取边界
   _edge.Unserialize(pInput);
   // 读取平面
   _face.Unserialize(pInput);
   // 读取相机
   _camera->Unserialize(pInput);
   // 读取光源
   _light->Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
