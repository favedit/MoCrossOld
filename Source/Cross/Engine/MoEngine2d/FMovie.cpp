#include "MoE2Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造显示动画。</T>
//============================================================
FMovie::FMovie(){
   //_objectCd |= EComponent_Renderable;
   _typeCd = EDisplayType_Movie;
   _pMaterial = MO_CREATE(FMaterial);
   //_pMaterial->SetEffectCd(ERenderEffect_2dTexture);
   MO_CLEAR(_pResource);
   _rate = 1.0f;
   _startTick = 0;
   _lastTick = 0;
   MO_CLEAR(_pActiveClip);
   MO_CLEAR(_pActiveFrame);
   _hasActiveAction = EFalse;
}

//============================================================
// <T>析构显示动画。</T>
//============================================================
FMovie::~FMovie(){
   MO_DELETE(_pMaterial);
}

//============================================================
// <T>计算并输出渲染数据。</T>
//
// @param renderable 渲染数据
//============================================================
TResult FMovie::CalculateRenderable(SRenderable& renderable){
   FDisplay2d::CalculateRenderable(renderable);
   if(_pActiveFrame != NULL){
      SRenderableItem& item = renderable.items.Get(0);
      SIntPoint2& baryCenter = _pActiveFrame->ValidBarycenter();
      item.location.x -= baryCenter.x;
      item.location.y -= baryCenter.y;
   }
   return ESuccess;
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源
//============================================================
void FMovie::LoadResource(FAnimationResource* pResource){
   _pResource = pResource;
   //_pMaterial->TextureSet(pResource->Bitmap()->RenderTexture());
}

//============================================================
// <T>清空列表。</T>
//
// @return 处理结果
//============================================================
TResult FMovie::PlayClear(){
   _startTick = 0;
   MO_CLEAR(_pActiveClip);
   MO_CLEAR(_pActiveFrame);
   _hasActiveAction = EFalse;
   _actions.Clear();
   return ESuccess;
}

//============================================================
// <T>播放动画。</T>
//
// @param pAction 命令
// @return 处理结果
//============================================================
TResult FMovie::Play(SMovieAction* pAction){
   MO_ASSERT(pAction);
   _actions.Push(*pAction);
   return ESuccess;
}

//============================================================
// <T>播放处理。</T>
//
// @return 处理结果
//============================================================
TResult FMovie::ProcessPlay(){
   // 计算时间间隔
   //TTimeTick current = RDeviceManager::Instance().TimerDevice()->CurrentTick();
   //TInt span = (TInt)((current - _startTick) / 1000);
   //if(_rate != 1.0f){
   //   span = (TInt)((TFloat)span * _rate);
   //}
   // 获得激活剪辑
   _pActiveClip = _pResource->FindClipByDirection(_activeAction.directionCd);
   // 获得激活帧
   //TInt frameIndex = _pResource->CalculateFrameIndex(span);
   //_pActiveFrame = _pActiveClip->Frame(frameIndex);
   //_size.Set((TFloat)_pActiveFrame->ValidSize().width, (TFloat)_pActiveFrame->ValidSize().height);
   //_coord.Assign(_pActiveFrame->Coord());
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FMovie::Process(){
   // 检查资源
   if(_pResource == NULL){
      return EContinue;
   }
   // 获得激活的命令
   if(!_hasActiveAction){
      if(!_actions.IsEmpty()){
         _activeAction = _actions.Shift();
         _hasActiveAction = ETrue;
         //_startTick = RDeviceManager::Instance().TimerDevice()->CurrentTick();
      }
   }
   // 检查是否有激活命令
   if(!_hasActiveAction){
      return EContinue;
   }
   // 处理命令
   switch(_activeAction.actionCd){
      case EMovieAction_Play:
         ProcessPlay();
         break;
   }
   return ESuccess;
}

MO_NAMESPACE_END
