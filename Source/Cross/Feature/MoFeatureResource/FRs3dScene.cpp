#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dScene, FResource3d);

//============================================================
// <T>构造资源3D模板。</T>
//============================================================
FRs3dScene::FRs3dScene(){
   // 技术
   _technique = FRs3dSceneTechnique::InstanceCreate();
   // 区域
   _region = FRs3dSceneRegion::InstanceCreate();
   // 天空
   _sky = FRs3dSceneSky::InstanceCreate();
   // 地图
   _map = FRs3dSceneMap::InstanceCreate();
   // 空间
   _space = FRs3dSceneSpace::InstanceCreate();
}

//============================================================
// <T>析构资源3D模板。</T>
//============================================================
FRs3dScene::~FRs3dScene(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dScene::Unserialize(IDataInput* pInput){
   FResource3d::Unserialize(pInput);
   // 读取属性
   _themeCode.UnserializeAutomatic(pInput);
   //_theme = RGeResource.themeConsole.load(themeCode);
   // 读取对象
   _technique->Unserialize(pInput);
   _region->Unserialize(pInput);
   _sky->Unserialize(pInput);
   _map->Unserialize(pInput);
   _space->Unserialize(pInput);
   // dataReady = true;
   return ESuccess;
}

MO_NAMESPACE_END
