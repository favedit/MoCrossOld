#include "MoE2Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造显示对象控制台。</T>
//============================================================
FDisplay2dConsole::FDisplay2dConsole(){
   _pPools = MO_CREATE(FDisplayPoolCollection);
}

//============================================================
// <T>析构显示对象控制台。</T>
//============================================================
FDisplay2dConsole::~FDisplay2dConsole(){
   MO_DELETE(_pPools);
}

//============================================================
// <T>收集一个指定类型的显示对象。</T>
//
// @param typeCd 类型
// @return 显示对象
//============================================================
FDisplay2d* FDisplay2dConsole::DisplayAlloc(TDisplayType typeCd){
   FDisplayPool* pPool = PoolFind(typeCd);
   MO_ASSERT(pPool);
   return (FDisplay2d*)pPool->Alloc();
}

//============================================================
// <T>释放一个指定类型的显示对象。</T>
//
// @return 显示对象
//============================================================
void FDisplay2dConsole::DisplayFree(FDisplay2d* pDisplay){
   TDisplayType typeCd = pDisplay->TypeCd();
   FDisplayPool* pPool = PoolFind(typeCd);
   MO_ASSERT(pPool);
   //return pPool->Free(pDisplay);
}

//============================================================
// <T>配置处理</T>
//============================================================
void FDisplay2dConsole::Setup(){
   // 注册图片缓冲池
   FPicturePool* pPicturePool = MO_CREATE(FPicturePool);
   PoolRegister(pPicturePool);
   // 注册形状缓冲池
   FShapePool* pShapePool = MO_CREATE(FShapePool);
   PoolRegister(pShapePool);
   // 注册精灵缓冲池
   FSpritePool* pSpritePool = MO_CREATE(FSpritePool);
   PoolRegister(pSpritePool);
   // 注册动画缓冲池
   FMoviePool* pMoviePool = MO_CREATE(FMoviePool);
   PoolRegister(pMoviePool);
}

//============================================================
// <T>获得缓冲池</T>
//
// @param typecCd 类型
// @return 缓冲池
//============================================================
FDisplayPool* FDisplay2dConsole::PoolFind(TDisplayType typeCd){
   TInt count = _pPools->Count();
   for(TInt n = 0; n < count; n++){
      FDisplayPool* pPool = _pPools->Get(n);
      if(pPool->TypeCd() == typeCd){
         return pPool;
      }
   }
   return NULL;
}

//============================================================
// <T>注册缓冲池。</T>
//
// @param pPool 缓冲池
//============================================================
void FDisplay2dConsole::PoolRegister(FDisplayPool* pPool){
   _pPools->Push(pPool);
}

//============================================================
// <T>注销缓冲池。</T>
//
// @param pPool 缓冲池
//============================================================
void FDisplay2dConsole::PoolUnregister(FDisplayPool* pPool){
   _pPools->Remove(pPool);
}

//============================================================
// <T>释放处理。</T>
//============================================================
TResult FDisplay2dConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
