#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDisplay, FDrawable);

//============================================================
// <T>构造显示对象。</T>
//============================================================
FDisplay::FDisplay(){
   MO_CLEAR(_pDisplays);
   MO_CLEAR(_pRenderables);
}

//============================================================
// <T>析构显示对象。</T>
//============================================================
FDisplay::~FDisplay(){
}

//============================================================
// <T>增加一个子显示对象。</T>
//
// @param pDisplay 子显示对象
// @return 处理结果
//============================================================
TResult FDisplay::DisplayPush(FDisplay* pDisplay){
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
TResult FDisplay::DisplayRemove(FDisplay* pDisplay){
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
TResult FDisplay::DisplayClear(){
   if(_pDisplays != NULL){
      _pDisplays->Clear();
   }
   return ESuccess;
}

//============================================================
// <T>增加一个子渲染对象。</T>
//
// @param pRenderable 子渲染对象
// @return 处理结果
//============================================================
TResult FDisplay::RenderablePush(FRenderable* pRenderable){
   MO_ASSERT(pRenderable);
   // 获得显示集合
   if(_pRenderables == NULL){
      _pRenderables = MO_CREATE(FRenderableCollection);
   }
   // 检查是否已经存在
   if(_pRenderables->Contains(pRenderable)){
      // 检查父组件是否为自己
      //FComponent* pParent = pRenderable->Parent();
      //if(pParent != NULL){
      //   MO_DEBUG_WARN("Child has already, but parent is null. (renderable=0x%08X)", pRenderable);
      //}else if(pParent != this){
      //   MO_DEBUG_WARN("Child has already, but parent is not this. (renderable=0x%08X, parent=0x%08X)", pRenderable, pParent);
      //}else{
      //   MO_DEBUG_WARN("Child has already. (renderable=0x%08X)", pRenderable);
      //}
      return EFailure;
   }
   // 从父组件中脱离
   // pDisplay->RemoveFromParent();
   // 放入子组件集合
   //pRenderable->SetParent(this);
   _pRenderables->Push(pRenderable);
   return ESuccess;
}

//============================================================
// <T>移除一个子渲染对象。</T>
//
// @param pRenderable 子渲染对象
// @return 处理结果
//============================================================
TResult FDisplay::RenderableRemove(FRenderable* pRenderable){
   MO_ASSERT(pRenderable);
   if(_pRenderables == NULL){
      MO_DEBUG_WARN("Children is empty, can't remove any child. (renderable=0x%08X)", pRenderable);
      return EFailure;
   }
#ifdef _MO_DEBUG
   if(!_pRenderables->Contains(pRenderable)){
      MO_DEBUG_WARN("Children not contains this child. (renderable=0x%08X)", pRenderable);
      return EFailure;
   }
#endif // _MO_DEBUG
   _pRenderables->Remove(pRenderable);
   return ESuccess;
}

//============================================================
// <T>清空子渲染对象。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::RenderableClear(){
   if(_pRenderables != NULL){
      _pRenderables->Clear();
   }
   return ESuccess;
}

//============================================================
// <T>过滤渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FDisplay::FilterRegion(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   // 检查可见状态
   if(!_statusVisible){
      return EFalse;
   }
   //............................................................
   // 处理所有子节点
   if(_pRenderables != NULL){
      TInt count = _pRenderables->Count();
      if(count > 0){
         FRenderableCollection* pRenderables = pRegion->Renderables();
         for(TInt n = 0; n < count; n++){
            FRenderable* pRenderable = _pRenderables->Get(n);
            pRenderables->Push(pRenderable);
         }
      }
   }
   //............................................................
   // 处理所有子显示对象
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
void FDisplay::UpdateAllMatrix(SDrawableContext* pContext){
   // 更新自矩阵
   UpdateSelftMatrix(pContext);
   // 更新子显示对象
   if(_pRenderables != NULL){
      TInt count = _pRenderables->Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = (FRenderable*)_pRenderables->Get(n);
         pRenderable->Matrix().Assign(_matrix);
      }
   }
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
TResult FDisplay::ProcessBefore(SProcessContext* pContext){
   // 逻辑前置处理
   FDrawable::ProcessBefore(pContext);
   // 处理所有子节点
   if(_pRenderables != NULL){
      TInt count = _pRenderables->Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = (FRenderable*)_pRenderables->Get(n);
         pRenderable->ProcessBefore(pContext);
      }
   }
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
TResult FDisplay::ProcessAfter(SProcessContext* pContext){
   // 逻辑前置处理
   FDrawable::ProcessAfter(pContext);
   // 处理所有子节点
   if(_pRenderables != NULL){
      TInt count = _pRenderables->Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = (FRenderable*)_pRenderables->Get(n);
         pRenderable->ProcessAfter(pContext);
      }
   }
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
TResult FDisplay::Free(){
   // 脱离父组件
   // RemoveFromParent();
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::Suspend(){
   // 处理所有子节点
   if(_pDisplays != NULL){
      TInt count = _pDisplays->Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _pDisplays->Get(n);
         pDisplay->Suspend();
      }
   }
   // 处理所有子节点
   if(_pRenderables != NULL){
      TInt count = _pRenderables->Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = (FRenderable*)_pRenderables->Get(n);
         pRenderable->Suspend();
      }
   }
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::Resume(){
   // 处理所有子节点
   if(_pDisplays != NULL){
      TInt count = _pDisplays->Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _pDisplays->Get(n);
         pDisplay->Resume();
      }
   }
   // 处理所有子节点
   if(_pRenderables != NULL){
      TInt count = _pRenderables->Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = (FRenderable*)_pRenderables->Get(n);
         pRenderable->Resume();
      }
   }
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
