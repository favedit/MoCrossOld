#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造应用程序参数集合。</T>
//============================================================
FApplicationParameters::FApplicationParameters(){
   _pParameters = MO_CREATE(FApplicationParameterCollection);
}

//============================================================
// <T>析构应用程序参数集合。</T>
//============================================================
FApplicationParameters::~FApplicationParameters(){
   MO_DELETE(_pParameters);
}

//============================================================
// <T>设置命令行。</T>
//
// @param pCommandLine 命令行
// @return 处理结果
//============================================================
TResult FApplicationParameters::Setup(TCharC* pCommandLine){
   // 检查参数

   if(pCommandLine == NULL){
      return ENull;
   }
   // 生成参数集合
   TInt position = 0;
   TInt length = RString::Length(pCommandLine);
   while(position < length){
      TCharC* pReader = pCommandLine + position;
      TInt find = RChars::IndexOf(pReader, length - position, TC(' '));
      // 创建参数
      FApplicationParameter* pParameter = MO_CREATE(FApplicationParameter);
      pParameter->Parse(pReader, find);
      _pParameters->Push(pParameter);
      // 调整位置
      if(find != ENotFound){
         position += find + 1;
      }else{
         break;
      }
   }
   return ESuccess;
}

//============================================================
// <T>设置参数集合。</T>
//
// @param ppArguments 参数集合
// @param count 参数个数
// @return 处理结果
//============================================================
TResult FApplicationParameters::Setup(TCharC** ppArguments, TInt count){
   // 检查参数
   if(ppArguments == NULL){
      return ENull;
   }
   if(count <= 0){
      return EFailure;
   }
   // 生成参数几何
   for(TInt n = 0; n < count; n++){
      FApplicationParameter* pParameter = MO_CREATE(FApplicationParameter);
      TCharC* pSource = ppArguments[n];
      pParameter->Parse(pSource);
      _pParameters->Push(pParameter);
   }
   return ESuccess;
}

//============================================================
// <T>获得参数个数。</T>
//
// @return 参数个数
//============================================================
TInt FApplicationParameters::Count(){
   return _pParameters->Count();
}

//============================================================
// <T>根据索引获得参数。</T>
//
// @param index 索引
// @return 参数
//============================================================
FApplicationParameter* FApplicationParameters::GetParameter(TInt index){
   return _pParameters->Get(index);
}

//============================================================
// <T>根据名称查找参数。</T>
//
// @param pName 名称
// @return 参数
//============================================================
FApplicationParameter* FApplicationParameters::FindParameter(TCharC* pName){
   TInt count = _pParameters->Count();
   for(TInt n = 0; n < count; n++){
      FApplicationParameter* pParameter = _pParameters->Get(n);
      if(pParameter->IsName(pName)){
         return pParameter;
      }
   }
   return NULL;
}

//============================================================
// <T>判断是否含有指定内容。</T>
//
// @param pValue 内容
// @return 参数
//============================================================
TBool FApplicationParameters::ConstainsValue(TCharC* pValue){
   TInt count = _pParameters->Count();
   for(TInt n = 0; n < count; n++){
      FApplicationParameter* pParameter = _pParameters->Get(n);
      if(pParameter->IsValue(pValue)){
         return ETrue;
      }
   }
   return EFalse;
}

//============================================================
// <T>根据索引获得参数内容。</T>
//
// @param index 索引
// @return 参数内容
//============================================================
TCharC* FApplicationParameters::GetValue(TInt index){
   FApplicationParameter* pParameter = GetParameter(index);
   MO_ASSERT(pParameter);
   TCharC* pValue = pParameter->Value();
   return pValue;
}

//============================================================
// <T>根据名称查找参数内容。</T>
//
// @param pName 名称
// @return 参数内容
//============================================================
TCharC* FApplicationParameters::FindValue(TCharC* pName){
   TCharC* pValue = NULL;
   FApplicationParameter* pParameter = FindParameter(pName);
   if(pParameter != NULL){
      pValue = pParameter->Value();
   }
   return pValue;
}

MO_NAMESPACE_END
