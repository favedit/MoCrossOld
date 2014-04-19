#include "MoEgDevice.h"
#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FComponent, FInstance);

//============================================================
// <T>构造组件对象。</T>
//============================================================
FComponent::FComponent(){
   _optionValid = ETrue;
   _objectCd = EComponent_Object | EComponent_Component;
   _flagCd = 0;
   MO_CLEAR(_pParent);
   //MO_CLEAR(_pChildren);
   MO_CLEAR(_pProperties);
   _statusConstruct = EFalse;
   _statusSetup = EFalse;
   _statusReady = EFalse;
   _lastUpdate = 0;
}

//============================================================
// <T>析构组件对象。</T>
//============================================================
FComponent::~FComponent(){
   //MO_DELETE(_pChildren);
   MO_DELETE(_pProperties);
}

//============================================================
// <T>获得最顶层组件。</T>
//
// @return 顶层组件
//============================================================
FComponent* FComponent::TopComponent(){
   FComponent* pComponent = this;
   while(pComponent != NULL){
      FComponent* pParent = pComponent->Parent();
      if(pParent == NULL){
         break;
      }
      pComponent = pParent;
   }
   return pComponent;
}

////============================================================
//// <T>从父对象中移除自己。</T>
////
//// @return 处理结果
////============================================================
//TResult FComponent::RemoveFromParent(){
//   if(_pParent != NULL){
//      _pParent->ChildRemove(this);
//      _pParent = NULL;
//   }
//   return ESuccess;
//}
//
////============================================================
//// <T>根据名称查找子节点。</T>
////
//// @param pName 名称
//// @return 子节点
////============================================================
//FComponent* FComponent::ChildGet(TCharC* pName){
//   FComponent* pComponent = ChildFind(pName);
//   MO_FATAL_CHECK(pComponent, return NULL,
//         "Child is not exists. (name=%s)", pName);
//   return pComponent;
//}
//
////============================================================
//// <T>根据名称查找子节点。</T>
////
//// @param pName 名称
//// @return 子节点
////============================================================
//FComponent* FComponent::ChildFind(TCharC* pName){
//   MO_CHECK(pName, return NULL);
//   // 查找所有子节点
//   TInt count = ChildCount();
//   for(TInt n = 0; n < count; n++){
//      FComponent* pComponent = _pChildren->Get(n);
//      if(pComponent->IsName(pName)){
//         return pComponent;
//      }
//   }
//   return NULL;
//}
//
////============================================================
//// <T>根据名称搜索子节点。</T>
////
//// @param pName 名称
//// @return 子节点
////============================================================
//FComponent* FComponent::ChildSearch(TCharC* pName){
//   MO_CHECK(pName, return NULL);
//   // 查找所有子节点
//   TInt count = ChildCount();
//   for(TInt n = 0; n < count; n++){
//      FComponent* pComponent = _pChildren->Get(n);
//      // 判定当前节点
//      if(pComponent->IsName(pName)){
//         return pComponent;
//      }
//      // 搜索子节点
//      FComponent* pSearchComponent = pComponent->ChildSearch(pName);
//      if(pSearchComponent != NULL){
//         return pSearchComponent;
//      }
//   }
//   return NULL;
//}
//
////============================================================
//// <T>增加一个子组件。</T>
////
//// @param pComponent 子组件
//// @return 处理结果
////============================================================
//TResult FComponent::ChildPush(FComponent* pComponent){
//   MO_ASSERT(pComponent);
//   // 如果子组件为空则创建
//   FComponentCollection* pChildren = Children();
//   // 检查是否已经存在
//   if(pChildren->Contains(pComponent)){
//      // 检查父组件是否为自己
//      FComponent* pParent = pComponent->Parent();
//      if(pParent != NULL){
//         MO_DEBUG_WARN("Child has already, but parent is null. (component=0x%08X)", pComponent);
//      }else if(pParent != this){
//         MO_DEBUG_WARN("Child has already, but parent is not this. (component=0x%08X, parent=0x%08X)", pComponent, pParent);
//      }else{
//         MO_DEBUG_WARN("Child has already. (component=0x%08X)", pComponent);
//      }
//      return EFailure;
//   }
//   // 从父组件中脱离
//   pComponent->RemoveFromParent();
//   pComponent->SetParent(this);
//   // 放入子组件集合
//   pChildren->Push(pComponent);
//   return ESuccess;
//}
//
////============================================================
//// <T>增加一个子组件。</T>
////
//// @param pComponent 子组件
//// @return 处理结果
////============================================================
//TResult FComponent::ChildPushDirect(FComponent* pComponent){
//   MO_ASSERT(pComponent);
//   pComponent->SetParent(this);
//   FComponentCollection* pChildren = Children();
//   pChildren->Push(pComponent);
//   return ESuccess;
//}
//
////============================================================
//// <T>移除一个子组件。</T>
////
//// @param pComponent 子组件
//// @return 处理结果
////============================================================
//TResult FComponent::ChildRemove(FComponent* pComponent){
//   MO_ASSERT(pComponent);
//   if(_pChildren == NULL){
//      MO_DEBUG_WARN("Children is empty, can't remove any child. (component=0x%08X)", pComponent);
//      return EFailure;
//   }
//#ifdef _MO_DEBUG
//   if(!_pChildren->Contains(pComponent)){
//      MO_DEBUG_WARN("Children not contains this child. (component=0x%08X)", pComponent);
//      return EFailure;
//   }
//#endif // _MO_DEBUG
//   _pChildren->Remove(pComponent);
//   return ESuccess;
//}
//
////============================================================
//// <T>交换两个子组件的位置。</T>
////
//// @param pSource 来源子组件
//// @param pTarget 目标子组件
//// @return 处理结果
////============================================================
//TResult FComponent::ChildSwap(FComponent* pSource, FComponent* pTarget){
//   return ESuccess;
//}
//
////============================================================
//// <T>清空子节点。</T>
////
//// @return 处理结果
////============================================================
//TResult FComponent::ChildClear(){
//   if(_pChildren != NULL){
//      _pChildren->Clear();
//   }
//   return ESuccess;
//}

//============================================================
// <T>构造处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::OnConstruct(){
   return ESuccess;
}

//============================================================
// <T>序列化数据内容到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FComponent::OnSerialize(IDataOutput* pOutput){
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FComponent::OnUnserialize(IDataInput* pInput){
   return ESuccess;
}

//============================================================
// <T>配置前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::OnSetupBefore(){
   return ESuccess;
}

//============================================================
// <T>配置后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::OnSetupAfter(){
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::OnDispose(){
   return ESuccess;
}

//============================================================
// <T>构造处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::Construct(){
   // 检查构造过
   if(_statusConstruct){
      return ESuccess;
   }
   // 构造处理
   OnConstruct();
   // 构造子组件集合
   //TInt childCount = ChildCount();
   //for(TInt n = 0; n < childCount; n++){
   //   FComponent* pComponent = _pChildren->Get(n);
   //   pComponent->Construct();
   //}
   _statusConstruct = ETrue;
   return ESuccess;
}

//============================================================
// <T>序列化数据内容到输出流。</T>
//
// @param pOutput 输出流
//============================================================
TResult FComponent::Serialize(IDataOutput* pOutput){
   // 序列化数据
   OnSerialize(pOutput);
   // 序列化子组件
   //TInt childCount = ChildCount();
   //pOutput->WriteInt16(childCount);
   //for(TInt n = 0; n < childCount; n++){
   //   FComponent* pComponent = _pChildren->Get(n);
   //   pComponent->Serialize(pOutput);
   //}
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FComponent::Unserialize(IDataInput* pInput){
   // 序列化数据
   OnUnserialize(pInput);
   // 序列化子组件
   TInt childCount = pInput->ReadInt16();
   for(TInt n = 0; n < childCount; n++){
      TInt componentCd = pInput->ReadInt8();
      // TODO: 临时处理
      FComponent* pComponent = MO_CREATE(FComponent);
      pComponent->Unserialize(pInput);
   }
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::Setup(){
   // 检查配置过
   if(_statusSetup){
      return ESuccess;
   }
   // 配置前置处理
   OnSetupBefore();
   // 配置子组件集合
   //TInt childCount = ChildCount();
   //for(TInt n = 0; n < childCount; n++){
   //   FComponent* pComponent = _pChildren->Get(n);
   //   pComponent->Setup();
   //}
   // 配置后置处理
   OnSetupAfter();
   _statusSetup = ETrue;
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::Update(){
   FTimerDevice* pTimerDevice = RDeviceManager::Instance().Find<FTimerDevice>();
   _lastUpdate = pTimerDevice->CurrentTick();
   return ESuccess;
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::ProcessBefore(SProcessContext* pContext){
   // 逻辑子组件集合
   //TInt childCount = ChildCount();
   //for(TInt n = 0; n < childCount; n++){
   //   FComponent* pComponent = _pChildren->Get(n);
   //   pComponent->Process();
   //}
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::ProcessAfter(SProcessContext* pContext){
   // 逻辑子组件集合
   //TInt childCount = ChildCount();
   //for(TInt n = 0; n < childCount; n++){
   //   FComponent* pComponent = _pChildren->Get(n);
   //   pComponent->Process();
   //}
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::Dispose(){
   // 释放子组件集合
   //TInt childCount = ChildCount();
   //for(TInt n = 0; n < childCount; n++){
   //   FComponent* pComponent = _pChildren->Get(n);
   //   pComponent->Dispose();
   //}
   // 释放处理
   return OnDispose();
}

MO_NAMESPACE_END
