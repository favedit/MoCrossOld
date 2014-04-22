#include "MoEgDevice.h"
#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FComponent, FInstance);

//============================================================
// <T>构造组件对象。</T>
//============================================================
FComponent::FComponent(){
   _optionFlag = 0;
   _statusFlag = 0;
   MO_CLEAR(_pParent);
   _lastUpdate = 0;
}

//============================================================
// <T>析构组件对象。</T>
//============================================================
FComponent::~FComponent(){
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
   // 检查状态
   if(!IsStatus(EComponentStatus_Construct)){
      // 构造处理
      OnConstruct();
      // 设置状态
      SetStatus(EComponentStatus_Construct, ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>序列化数据内容到输出流。</T>
//
// @param pOutput 输出流
//============================================================
TResult FComponent::Serialize(IDataOutput* pOutput){
   TResult resultCd = OnSerialize(pOutput);
   return resultCd;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FComponent::Unserialize(IDataInput* pInput){
   TResult resultCd = OnUnserialize(pInput);
   return resultCd;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::Setup(){
   // 检查状态
   if(!IsStatus(EComponentStatus_Setup)){
      // 配置前置处理
      OnSetupBefore();
      // 配置后置处理
      OnSetupAfter();
      // 设置状态
      SetStatus(EComponentStatus_Setup, ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
// <P>只要是舞台内对象全部参与处理，无论是否有效，是否被显示。</P>
//
// @param pContext 处理环境
// @return 处理结果
//============================================================
TResult FComponent::Update(SProcessContext* pContext){
   MO_CHECK(pContext, return ENull);
   _lastUpdate = pContext->currentTick;
   return ESuccess;
}

//============================================================
// <T>功能前置处理。</T>
//
// @param pContext 处理环境
// @return 处理结果
//============================================================
TResult FComponent::ProcessBefore(SProcessContext* pContext){
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @param pContext 处理环境
// @return 处理结果
//============================================================
TResult FComponent::ProcessAfter(SProcessContext* pContext){
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FComponent::Dispose(){
   TResult resultCd = OnDispose();
   return resultCd;
}

MO_NAMESPACE_END
