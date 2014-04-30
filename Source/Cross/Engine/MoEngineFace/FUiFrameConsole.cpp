#include "MoEfDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造控件对象控制台。</T>
//============================================================
FUiFrameConsole::FUiFrameConsole(){
   _pPools = MO_CREATE(FUiControlPoolCollection);
   _pFrames = MO_CREATE(FUiFrameSet);
}

//============================================================
// <T>析构显示对象控制台。</T>
//============================================================
FUiFrameConsole::~FUiFrameConsole(){
   MO_DELETE(_pPools);
   MO_DELETE(_pFrames);
}

//============================================================
// <T>配置处理</T>
//============================================================
TResult FUiFrameConsole::Setup(){
   //// 注册精灵缓冲池
   //FSpritePool* pSpritePool = MO_CREATE(FSpritePool);
   //PoolRegister(pSpritePool);
   return ESuccess;
}

//============================================================
// <T>配置处理</T>
//
// @return 处理结果
//============================================================
TResult FUiFrameConsole::Open(){
   // 打开数据流
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStreamFormat("/db/frame.db");
   MO_ERROR_CHECK(pStream, return EFailure, "Open theme failure.");
   // 打开帧处理
   TFsName className;
   TInt count = pStream->ReadInt32();
   for(TInt n = 0; n < count; n++){
      EControlType controlCd = (EControlType)pStream->ReadInt16();
      className.Unserialize(pStream);
      FUiFrame* pFrame = (FUiFrame*)RFaceManager::Instance().ControlConsole()->Alloc(controlCd, (TCharC*)className);
      pFrame->Unserialize(pStream);
      _pFrames->Set(pFrame->Code(), pFrame);
   }
   RAssetManager::Instance().CloseAssetStream(pStream);
   return ESuccess;
}

//============================================================
// <T>根据编号查找一个页面。</T>
//
// @return 页面
//============================================================
FUiFrame* FUiFrameConsole::Find(TControlId frameId){
   FUiFrame* pFrame = _pFrames->Find(frameId);
   if(pFrame != NULL){
      pFrame->Setup();
   }
   return pFrame;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiFrameConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
