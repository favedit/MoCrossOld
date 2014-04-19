#include "MoE3Scene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScene3dFrame, FStageFrame);

//============================================================
// <T>构造实体3D场景帧。</T>
//============================================================
FScene3dFrame::FScene3dFrame(){
   _name = "scene.frame";
}

//============================================================
// <T>析构实体3D场景帧。</T>
//============================================================
FScene3dFrame::~FScene3dFrame(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3dFrame::Setup(){
   TResult resultCd = FStageFrame::Setup();
   // 构件天空层
   _skyLayer = FDisplayLayer::InstanceCreate();
   _skyLayer->SetName("sky.layer");
   _skyLayer->Setup();
   _pLayers->Push(_skyLayer);
   // 构件地表层
   _mapLayer = FDisplayLayer::InstanceCreate();
   _mapLayer->SetName("map.layer");
   _mapLayer->Setup();
   _pLayers->Push(_mapLayer);
   // 构件空间层
   _spaceLayer = FDisplayLayer::InstanceCreate();
   _spaceLayer->SetName("space.layer");
   _spaceLayer->Setup();
   _pLayers->Push(_spaceLayer);
   // 构件界面层
   _faceLayer = FDisplayLayer::InstanceCreate();
   _faceLayer->SetName("face.layer");
   _faceLayer->Setup();
   _pLayers->Push(_faceLayer);
   return resultCd;
}

MO_NAMESPACE_END
