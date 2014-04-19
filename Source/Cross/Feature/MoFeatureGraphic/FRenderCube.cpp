#include "MoFgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderCube, FRenderable);

//============================================================
// <T>构造实体3D几何体。</T>
//============================================================
FRenderCube::FRenderCube(){
}

//============================================================
// <T>析构实体3D几何体。</T>
//============================================================
FRenderCube::~FRenderCube(){
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FRenderCube::Setup(){
   return ESuccess;
}

MO_NAMESPACE_END
