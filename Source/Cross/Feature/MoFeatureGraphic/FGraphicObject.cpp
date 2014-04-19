#include "MoFgCommon.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FGraphicObject, FInstance);

//============================================================
// <T>构造图形对象。</T>
//============================================================
FGraphicObject::FGraphicObject(){
   MO_CLEAR(_pOwner);
   _statusSetup = EFalse;
}

//============================================================
// <T>析构图形对象。</T>
//============================================================
FGraphicObject::~FGraphicObject(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGraphicObject::OnSetup(){
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGraphicObject::Setup(){
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
