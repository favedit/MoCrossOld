#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造空间体。</T>
//============================================================
FSpatial::FSpatial(){
   _objectCd |= EComponent_Spatial;
   MO_CLEAR(_pDisplays);
}

//============================================================
// <T>析构空间体。</T>
//============================================================
FSpatial::~FSpatial(){
   MO_DELETE(_pDisplays);
}

//============================================================
// <T>增加一个子显示对象。</T>
//
// @param pDisplay 子显示对象
// @return 处理结果
//============================================================
TResult FSpatial::DisplayPush(FDisplay* pDisplay){
   MO_ASSERT(pDisplay);
   // 获得显示集合
   if(_pDisplays == NULL){
      _pDisplays = MO_CREATE(FDisplayCollection);
   }
   // 检查是否已经存在
   if(_pDisplays->Contains(pDisplay)){
      // 检查父组件是否为自己
      FComponent* pParent = pDisplay->Parent();
      if(pParent != NULL){
         MO_DEBUG_WARN("Child has already, but parent is null. (display=0x%08X)", pDisplay);
      }else if(pParent != this){
         MO_DEBUG_WARN("Child has already, but parent is not this. (display=0x%08X, parent=0x%08X)", pDisplay, pParent);
      }else{
         MO_DEBUG_WARN("Child has already. (display=0x%08X)", pDisplay);
      }
      return EFailure;
   }
   // 从父组件中脱离
   // pDisplay->RemoveFromParent();
   // 放入子组件集合
   pDisplay->SetParent(this);
   _pDisplays->Push(pDisplay);
   return ESuccess;
}

//============================================================
// <T>移除一个子显示对象。</T>
//
// @param pDisplay 子显示对象
// @return 处理结果
//============================================================
TResult FSpatial::DisplayRemove(FDisplay* pDisplay){
   MO_ASSERT(pDisplay);
   if(_pDisplays == NULL){
      MO_DEBUG_WARN("Children is empty, can't remove any child. (display=0x%08X)", pDisplay);
      return EFailure;
   }
#ifdef _MO_DEBUG
   if(!_pDisplays->Contains(pDisplay)){
      MO_DEBUG_WARN("Children not contains this child. (display=0x%08X)", pDisplay);
      return EFailure;
   }
#endif // _MO_DEBUG
   _pDisplays->Remove(pDisplay);
   return ESuccess;
}

//============================================================
// <T>清空子显示对象。</T>
//
// @return 处理结果
//============================================================
TResult FSpatial::DisplayClear(){
   if(_pDisplays != NULL){
      _pDisplays->Clear();
   }
   return ESuccess;
}

//============================================================
// <T>过滤渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FSpatial::FilterRegion(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   // 检查可见状态
   if(!_statusVisible){
      return EFalse;
   }
   // 判断是否可以被绘制
   //if(TestDrawable()){
   //   FRenderable* pRenderable = (FRenderable*)Convert(EComponent_Renderable);
   //   if(pRenderable != NULL){
   //      if(pRenderable->Material() != NULL){
   //         pRenderables->Push(pRenderable);
   //      }
   //   }
   //}
   // 处理所有子节点
   if(_pDisplays != NULL){
      TInt count = _pDisplays->Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _pDisplays->Get(n);
         pDisplay->FilterRegion(pRegion);
      }
   }
   return ESuccess;
}

//============================================================
// <T>更新当前所有变换矩阵。</T>
//============================================================
void FSpatial::UpdateAllMatrix(SDrawableContext* pContext){
   // 更新自矩阵
   UpdateSelftMatrix(pContext);
   // 更新子显示对象
   if(_pDisplays != NULL){
      TInt count = _pDisplays->Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _pDisplays->Get(n);
         pDisplay->UpdateAllMatrix(pContext);
      }
   }
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FSpatial::ProcessBefore(SProcessContext* pContext){
   // 逻辑前置处理
   FDrawable::ProcessBefore(pContext);
   // 处理所有子节点
   if(_pDisplays != NULL){
      TInt count = _pDisplays->Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _pDisplays->Get(n);
         pDisplay->ProcessBefore(pContext);
      }
   }
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FSpatial::ProcessAfter(SProcessContext* pContext){
   // 逻辑前置处理
   FDrawable::ProcessAfter(pContext);
   // 处理所有子节点
   if(_pDisplays != NULL){
      TInt count = _pDisplays->Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _pDisplays->Get(n);
         pDisplay->ProcessAfter(pContext);
      }
   }
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//============================================================
TResult FSpatial::Free(){
   // 脱离父组件
   // RemoveFromParent();
   return ESuccess;
}

MO_NAMESPACE_END
