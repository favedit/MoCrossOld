#include "MoFgCommon.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FGraphicInstance, FInstance);

//============================================================
// <T>构造图形实例。</T>
//============================================================
FGraphicInstance::FGraphicInstance(){
   MO_CLEAR(_pOwner);
   _statusSetup = EFalse;
}

//============================================================
// <T>析构图形实例。</T>
//============================================================
FGraphicInstance::~FGraphicInstance(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGraphicInstance::OnSetup(){
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGraphicInstance::Setup(){
   TResult result = EContinue;
   if(!_statusSetup){
      result = OnSetup();
      if(result){
         MO_WARN("Setup result is not success. (result=%d)", result);
      }
      _statusSetup = ETrue;
   }
   return result;
}

MO_NAMESPACE_END
