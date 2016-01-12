#include "MoCrEnvironment.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造环境控制台。</T>
//============================================================
FEnvironmentConsole::FEnvironmentConsole(){
   _pEnvironments = MO_CREATE(FEnvironmentCollection);
}

//============================================================
// <T>析构环境控制台。</T>
//============================================================
FEnvironmentConsole::~FEnvironmentConsole(){
   Clear();
   MO_DELETE(_pEnvironments);
}

//============================================================
// <T>查找制定环境是否存在。</T>
//
// @param pName 名称
// @return 是否存在
//============================================================
TBool FEnvironmentConsole::Exists(TCharC* pName){
   FEnvironment* pEnvironment = FindEnvironment(pName);
   return (NULL != pEnvironment);
}

//============================================================
// <T>获得指定名称的环境。</T>
//
// @param pName 名称
// @return 环境
//============================================================
FEnvironment* FEnvironmentConsole::FindEnvironment(TCharC* pName){
   TInt count = _pEnvironments->Count();
   for(TInt n = 0; n < count; n++){
      FEnvironment* pEnvironment = _pEnvironments->Get(n);
      if(pEnvironment->IsName(pName)){
         return pEnvironment;
      }
   }
   return NULL;
}

//============================================================
// <T>获得指定名称的环境内容。</T>
//
// @param pName 名称
// @return 环境内容
//============================================================
TCharC* FEnvironmentConsole::FindValue(TCharC* pName){
   FEnvironment* pEnvironment = FindEnvironment(pName);
   if(NULL != pEnvironment){
      return pEnvironment->Value();
   }
   return NULL;
}

//============================================================
// <T>解析获得指定名称的环境内容。</T>
//
// @param pName 名称
// @return 环境内容
//============================================================
TCharC* FEnvironmentConsole::ParseValue(TCharC* pName){
   FEnvironment* pEnvironment = FindEnvironment(pName);
   TCharC* pData = NULL;
   if(pEnvironment != NULL){
      pData = pEnvironment->Data();
   }
   return pData;
}

//============================================================
// <T>注册一个环境。</T>
//
// @param pName 名称
// @param pValue 内容
//============================================================
void FEnvironmentConsole::Register(TCharC* pName, TCharC* pValue){
   // 查找环境
   FEnvironment* pEnvironment = FindEnvironment(pName);
   // 创建环境
   if(NULL == pEnvironment){
      pEnvironment = MO_CREATE(FEnvironment);
      pEnvironment->SetName(pName);
      _pEnvironments->Push(pEnvironment);
   }
   // 设置内容
   pEnvironment->SetValue(pValue);
   MO_DEBUG(TC("Register environment. (name=%s, value=%s)"), pName, pValue);
}

//============================================================
// <T>注销一个环境。</T>
//
// @param pName 名称
//============================================================
void FEnvironmentConsole::Unregister(TCharC* pName){
   // 查找环境
   FEnvironment* pEnvironment = FindEnvironment(pName);
   // 移除环境
   if(NULL != pEnvironment){
      _pEnvironments->Remove(pEnvironment);
      MO_DELETE(pEnvironment);
   }
}

//============================================================
// <T>清空环境。</T>
//============================================================
void FEnvironmentConsole::Clear(){
   TInt count = _pEnvironments->Count();
   for(TInt n = 0; n < count; n++){
      FEnvironment* pEnvironment = _pEnvironments->Get(n);
      MO_DELETE(pEnvironment);
   }
   _pEnvironments->Clear();
}

MO_NAMESPACE_END
