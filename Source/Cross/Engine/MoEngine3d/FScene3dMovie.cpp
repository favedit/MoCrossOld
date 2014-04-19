#include "MoE3Scene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScene3dMovie, FInstance);

//============================================================
// <T>构造动画。</T>
//============================================================
FScene3dMovie::FScene3dMovie(){
   MO_CLEAR(_pResource);
   _interval = 0;
}

//============================================================
// <T>析构动画。</T>
//============================================================
FScene3dMovie::~FScene3dMovie(){
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3dMovie::LoadResource(FRs3dSceneMovie* pResource){
   MO_CHECK(pResource, return ENull);
   _pResource = pResource;
   _interval = pResource->Interval() * 1000;
   return ESuccess;
}

//============================================================
// <T>处理动画。</T>
//
// @param matrix 矩阵
// @return 处理结果
//============================================================
TResult FScene3dMovie::Process(SFloatMatrix3d& matrix){
   return ESuccess;
}

MO_NAMESPACE_END
