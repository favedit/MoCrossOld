#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDisplay, FDrawable);

//============================================================
// <T>构造显示对象。</T>
//============================================================
FDisplay::FDisplay(){
}

//============================================================
// <T>析构显示对象。</T>
//============================================================
FDisplay::~FDisplay(){
}

//============================================================
// <T>增加一个子渲染对象。</T>
//
// @param pRenderable 子渲染对象
// @return 处理结果
//============================================================
TResult FDisplay::RenderablePush(FRenderable* pRenderable){
   MO_ASSERT(pRenderable);
   // 检查是否已经存在
#ifdef _MO_DEBUG
   if(_renderables.Contains(pRenderable)){
      MO_FATAL("Child renderable has already. (renderable=0x%08X)", pRenderable);
      return EFailure;
   }
#endif // _MO_DEBUG
   // 从父组件中脱离
   // pDisplay->RemoveFromParent();
   // 放入子组件集合
   //pRenderable->SetParent(this);
   _renderables.Push(pRenderable);
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
#ifdef _MO_DEBUG
   if(!_renderables.Contains(pRenderable)){
      MO_DEBUG_WARN("Children not contains this child. (renderable=0x%08X)", pRenderable);
      return EFailure;
   }
#endif // _MO_DEBUG
   _renderables.Remove(pRenderable);
   return ESuccess;
}

//============================================================
// <T>清空子渲染对象。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::RenderableClear(){
   _renderables.Clear();
   return ESuccess;
}

//============================================================
// <T>增加一个子显示对象。</T>
//
// @param pDisplay 子显示对象
// @return 处理结果
//============================================================
TResult FDisplay::DisplayPush(FDisplay* pDisplay){
   MO_ASSERT(pDisplay);
   // 检查是否已经存在
#ifdef _MO_DEBUG
   if(_displays.Contains(pDisplay)){
      MO_FATAL("Child display has already. (renderable=0x%08X)", pDisplay);
      return EFailure;
   }
#endif // _MO_DEBUG
   // 从父组件中脱离
   // pDisplay->RemoveFromParent();
   // 放入子组件集合
   pDisplay->SetParent(this);
   _displays.Push(pDisplay);
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
#ifdef _MO_DEBUG
   if(!_displays.Contains(pDisplay)){
      MO_DEBUG_WARN("Children not contains this child. (display=0x%08X)", pDisplay);
      return EFailure;
   }
#endif // _MO_DEBUG
   _displays.Remove(pDisplay);
   return ESuccess;
}

//============================================================
// <T>清空子显示对象。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::DisplayClear(){
   _displays.Clear();
   return ESuccess;
}

//============================================================
// <T>添加一个脚本对象。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::ScriptablePush(FScriptable* pScriptable){
   MO_CHECK(pScriptable, return ENull);
   // 检查是否已经存在
#ifdef _MO_DEBUG
   if(_scriptables.Contains(pScriptable)){
      MO_FATAL("Child scriptable has already. (renderable=0x%08X)", pScriptable);
      return EFailure;
   }
#endif // _MO_DEBUG
   _scriptables.Push(pScriptable);
   return ESuccess;
}

//============================================================
// <T>移除一个脚本对象。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::ScriptableRemove(FScriptable* pScriptable){
   MO_CHECK(pScriptable, return ENull);
   _scriptables.Remove(pScriptable);
   return ESuccess;
}

//============================================================
// <T>清空脚本对象集合。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::ScriptableClear(){
   _scriptables.Clear();
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
   // 检查准备好
   if(!TestReady()){
      return EContinue;
   }
   // 检查可见状态
   if(!_statusVisible){
      return EContinue;
   }
   //............................................................
   // 处理所有子节点
   if(!_renderables.IsEmpty()){
      TInt count = _renderables.Count();
      FRenderableCollection* pRenderables = pRegion->Renderables();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = _renderables.Get(n);
         pRenderables->Push(pRenderable);
      }
   }
   //............................................................
   // 处理所有子显示对象
   if(!_displays.IsEmpty()){
      TInt count = _displays.Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _displays.Get(n);
         pDisplay->FilterRegion(pRegion);
      }
   }
   return ESuccess;
}

//============================================================
// <T>更新当前所有变换矩阵。</T>
//============================================================
TResult FDisplay::UpdateAllMatrix(SDrawableContext* pContext){
   // 更新自矩阵
   UpdateSelftMatrix(pContext);
   // 更新子显示对象
   if(!_renderables.IsEmpty()){
      TInt count = _renderables.Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = _renderables.Get(n);
         pRenderable->Matrix().Assign(_matrix);
      }
   }
   // 更新子显示对象
   if(!_displays.IsEmpty()){
      TInt count = _displays.Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _displays.Get(n);
         pDisplay->UpdateAllMatrix(pContext);
      }
   }
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay::Update(SProcessContext* pContext){
   // 逻辑前置处理
   TResult resultCd = FDrawable::Update(pContext);
   // 处理所有子节点
   if(!_renderables.IsEmpty()){
      TInt count = _renderables.Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = _renderables.Get(n);
         pRenderable->Update(pContext);
      }
   }
   // 处理所有子节点
   if(!_displays.IsEmpty()){
      TInt count = _displays.Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _displays.Get(n);
         pDisplay->Update(pContext);
      }
   }
   return resultCd;
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
   if(!_renderables.IsEmpty()){
      TInt count = _renderables.Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = _renderables.Get(n);
         pRenderable->ProcessBefore(pContext);
      }
   }
   // 处理所有子节点
   if(!_displays.IsEmpty()){
      TInt count = _displays.Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _displays.Get(n);
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
   if(!_renderables.IsEmpty()){
      TInt count = _renderables.Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = _renderables.Get(n);
         pRenderable->ProcessAfter(pContext);
      }
   }
   // 处理所有子节点
   if(!_displays.IsEmpty()){
      TInt count = _displays.Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _displays.Get(n);
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
   if(!_displays.IsEmpty()){
      TInt count = _displays.Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _displays.Get(n);
         pDisplay->Suspend();
      }
   }
   // 处理所有子节点
   if(!_renderables.IsEmpty()){
      TInt count = _renderables.Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = _renderables.Get(n);
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
   if(!_displays.IsEmpty()){
      TInt count = _displays.Count();
      for(TInt n = 0; n < count; n++){
         FDisplay* pDisplay = _displays.Get(n);
         pDisplay->Resume();
      }
   }
   // 处理所有子节点
   if(!_renderables.IsEmpty()){
      TInt count = _renderables.Count();
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = _renderables.Get(n);
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
