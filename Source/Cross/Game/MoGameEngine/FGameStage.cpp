#include "MoGeDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FGameStage, FStageLayer);

//============================================================
// <T>构造简单舞台对象。</T>
//============================================================
FGameStage::FGameStage(){
   MO_CLEAR(_pSpriteLayer);
   // 清空舞台帧
   MO_CLEAR(_pGroundFrame);
   MO_CLEAR(_pSceneFrame);
   MO_CLEAR(_pFaceFrame);
}

//============================================================
// <T>析构简单舞台对象。</T>
//============================================================
FGameStage::~FGameStage(){
   MO_DELETE(_pGroundFrame);
   MO_DELETE(_pSceneFrame);
   MO_DELETE(_pFaceFrame);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGameStage::Setup(){
   TResult result = FStage::Setup();
   // 获得屏幕大小
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   SIntSize2& screenSize = pScreenDevice->Size();
   //............................................................
   //// 创建渲染目标
   //FRenderTexture* pGroundTexture = pRenderDevice->CreateFlatTexture();
   //pGroundTexture->Size().Assign(_size);
   //pGroundTexture->Setup();
   //FRenderTarget* pGroundTarget = pRenderDevice->CreateRenderTarget();
   //pGroundTarget->SetOptionDepth(ETrue);
   //pGroundTarget->Size().Assign(_size);
   //pGroundTarget->Textures()->Push(pGroundTexture);
   //pGroundTarget->Setup();
   //// 创建地面层
   //FDisplayLayer* pGroundLayer = MO_CREATE(FDisplayLayer);
   //pGroundLayer->SetLayerCd(EDisplayLayer_Ground);
   //pGroundLayer->Setup();
   //// 创建背景帧
   //_pGroundFrame = MO_CREATE(FStageLayer);
   //_pGroundFrame->SetFrameCd(EStageFrame_Ground);
   //_pGroundFrame->LayerPush(pGroundLayer);
   //_pGroundFrame->SetRenderTarget(pGroundTarget);
   //_pGroundFrame->Setup();
   //FramePush(_pGroundFrame);
   //............................................................
   // 创建渲染目标
   //FRenderTexture* pSceneTexture = pRenderDevice->CreateFlatTexture();
   //pSceneTexture->Size().Assign(_size);
   //pSceneTexture->Setup();
   //FRenderTarget* pSceneTarget = pRenderDevice->CreateRenderTarget();
   //pSceneTarget->SetOptionDepth(ETrue);
   //pSceneTarget->Size().Assign(_size);
   //pSceneTarget->Textures()->Push(pSceneTexture);
   //pSceneTarget->Setup();
   // 创建地形层
   //FDisplayLayer* pTerrainLayer = MO_CREATE(FDisplayLayer);
   //pTerrainLayer->SetLayerCd(EDisplayLayer_Terrain);
   //pTerrainLayer->Setup();
   // 创建精灵层
   _pSpriteLayer = FDisplayLayer::InstanceCreate();
   _pSpriteLayer->SetName("Sprite");
   _pSpriteLayer->Setup();
   // 创建精灵层
   //FDisplayLayer* pParticleLayer = MO_CREATE(FDisplayLayer);
   //pParticleLayer->SetLayerCd(EDisplayLayer_Particle);
   //pParticleLayer->Setup();
   // 创建场景帧
   _pSceneFrame = FStageLayer::InstanceCreate();
   _pSceneFrame->SetName("Scene");
   //_pSceneFrame->LayerPush(pTerrainLayer);
   _pSceneFrame->LayerPush(_pSpriteLayer);
   //_pSceneFrame->LayerPush(pParticleLayer);
   //_pSceneFrame->SetRenderTarget(pSceneTarget);
   _pSceneFrame->Setup();
   LayerPush(_pSceneFrame);
   //............................................................
   //// 创建渲染目标
   //FRenderTexture* pFaceTexture = pRenderDevice->CreateFlatTexture();
   //pFaceTexture->Size().Assign(_size);
   //pFaceTexture->Setup();
   //FRenderTarget* pFaceTarget = pRenderDevice->CreateRenderTarget();
   //pFaceTarget->Size().Assign(_size);
   //pFaceTarget->Textures()->Push(pFaceTexture);
   //pFaceTarget->Setup();
   //// 创建界面层
   //FDisplayLayer* pFaceLayer = MO_CREATE(FDisplayLayer);
   //pFaceLayer->SetLayerCd(EDisplayLayer_Face);
   //pFaceLayer->Setup();
   //// 创建界面帧
   //_pFaceFrame = MO_CREATE(FStageLayer);
   //_pFaceFrame->SetFrameCd(EStageFrame_Face);
   //_pFaceFrame->LayerPush(pFaceLayer);
   //_pFaceFrame->SetRenderTarget(pFaceTarget);
   //_pFaceFrame->Setup();
   //FramePush(_pFaceFrame);
   return result;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGameStage::ProcessInput(){
   //FStage::ProcessInput();
   return ESuccess;
}

MO_NAMESPACE_END
