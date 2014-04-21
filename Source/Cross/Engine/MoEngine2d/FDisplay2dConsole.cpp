#include "MoE2Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造显示对象控制台。</T>
//============================================================
FDisplay2dConsole::FDisplay2dConsole(){
}

//============================================================
// <T>析构显示对象控制台。</T>
//============================================================
FDisplay2dConsole::~FDisplay2dConsole(){
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
   _classFactory->Register("Picture", FPicture::Class());
   _classFactory->Register("Shape", FShape::Class());
   _classFactory->Register("Sprite", FSprite::Class());
   _classFactory->Register("Movie", FMovie::Class());
}

//============================================================
// <T>获得缓冲池</T>
//
// @param typecCd 类型
// @return 缓冲池
//============================================================
FDisplayPool* FDisplay2dConsole::PoolFind(TDisplayType typeCd){
   //TInt count = _pPools->Count();
   //for(TInt n = 0; n < count; n++){
   //   FDisplayPool* pPool = _pPools->Get(n);
   //   if(pPool->TypeCd() == typeCd){
   //      return pPool;
   //   }
   //}
   return NULL;
}

//============================================================
// <T>释放处理。</T>
//============================================================
TResult FDisplay2dConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
