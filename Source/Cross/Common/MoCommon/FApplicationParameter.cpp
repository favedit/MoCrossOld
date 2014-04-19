#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造应用程序参数。</T>
//============================================================
FApplicationParameter::FApplicationParameter(){
}

//============================================================
// <T>析构应用程序参数。</T>
//============================================================
FApplicationParameter::~FApplicationParameter(){
}

//============================================================
// <T>解析来源。</T>
//
// @param pSource 来源
// @param length 长度
// @return 处理结果
//============================================================
TResult FApplicationParameter::Parse(TCharC* pSource, TInt length){
   // 检查参数
   MO_ASSERT(pSource);
   // 获得长度
   if(length == -1){
      length = RString::Length(pSource);
   }
   // 查找切分
   TInt find = RChars::IndexOf(pSource, length, '=');
   if(find == ENotFound){
      _value = TStringPtrC(pSource, length);
   }else{
      _name = TStringPtrC(pSource, find);
      _value = TStringPtrC(pSource + find + 1, length - find - 1);
   }
   return ESuccess;
}

MO_NAMESPACE_END
