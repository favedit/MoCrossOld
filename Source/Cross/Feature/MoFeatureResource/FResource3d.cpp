#include "MoFrContent3dBase.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FResource3d, FContent);

//============================================================
// <T>构造资源。</T>
//============================================================
FResource3d::FResource3d(){
   _typeCd = EResource3d_Unknown;
   _code = 0;
   _timeout = 0;
   _statusOpen = EFalse;
   _statusReady = EFalse;
}

//============================================================
// <T>析构资源。</T>
//============================================================
FResource3d::~FResource3d(){
   MO_DEBUG_INFO(TC("Release resource. (code=%d)"), _code);
}

//============================================================
// <T>测试是否准备好。</T>
//
// @return 是否准备好。</T>
//============================================================
TBool FResource3d::TestReady(){
   return EFalse;
}

//============================================================
// <T>测试是否有效。</T>
//
// @return 是否有效
//============================================================
TBool FResource3d::TestValid(){
   return ETrue;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FResource3d::OnOpen(){
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FResource3d::OnClose(){
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FResource3d::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   _name.UnserializeAutomatic(pInput);
   // _code = pInput->ReadInt32();
   // _timeout = pInput->ReadInt32();
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FResource3d::Open(){
   TResult result = ESuccess;
   if(!_statusOpen){
      result = OnOpen();
      _statusOpen = ETrue;
   }
   return result;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FResource3d::Close(){
   TResult result = ESuccess;
   if(_statusOpen){
      result = OnClose();
      _statusOpen = EFalse;
   }
   return result;
}

MO_NAMESPACE_END
