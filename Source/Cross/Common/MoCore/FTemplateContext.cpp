#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateContext, FInstance);

//============================================================
// <T>构造模板环境。</T>
//============================================================
FTemplateContext::FTemplateContext(){
   _config = FXmlNode::InstanceCreate();
   _mergeConfig = FXmlNode::InstanceCreate();
   _pSource = MO_CREATE(FStringBuffer);
   _trimOnceFlag = EFalse;
   _trimBeginLine = ETrue;
   _trimEmptyLine = EFalse;
   _trimEndLine = EFalse;
   _trimCommentLine = EFalse;
}

//============================================================
// <T>析构模板环境。</T>
//============================================================
FTemplateContext::~FTemplateContext(){
   MO_DELETE(_pSource);
}

//============================================================
// <T>设置空白标志一次。</T>
//============================================================
void FTemplateContext::SetTrimOnceFlag(){
   _trimOnceFlag = ETrue;
}

//============================================================
// <T>空白最后一行。</T>
//============================================================
void FTemplateContext::TrimLastLine(){
   TInt length = _pSource->Length();
   if(length == 0){
      return;
   }
   TCharC* pSource = _pSource->MemoryC();
   if((length > 1) && (pSource[length - 2] == '\r') && (pSource[length - 1] == '\n')){
      _pSource->SetLength(length - 2);
   }else if((pSource[length - 1] == '\r') || (pSource[length - 1] == '\n')){
      _pSource->SetLength(length - 1);
   }
}

//============================================================
// <T>根据名称获得定义的布尔值。</T>
//
// @param pName 名称
// @param defaultValue 默认内容
// @return 布尔值
//============================================================
TBool FTemplateContext::GetDefineAsBool(TCharC* pName, TBool defaultValue){
   TBool value = _config->GetAsBool(pName, defaultValue);
   return value;
}

//============================================================
// <T>根据名称获得定义的整数。</T>
//
// @param pName 名称
// @param defaultValue 默认内容
// @return 整数
//============================================================
TInt FTemplateContext::GetDefineAsInt(TCharC* pName, TInt defaultValue){
   TInt value = _config->GetAsInt(pName, defaultValue);
   return value;
}

//============================================================
// <T>根据名称获得定义的字符串。</T>
//
// @param pName 名称
// @param defaultValue 默认内容
// @return 字符串
//============================================================
TCharC* FTemplateContext::GetDefineAsString(TCharC* pName, TCharC* pDefaultValue){
   TCharC* pValue = _config->Get(pName, pDefaultValue);
   return pValue;
}

//============================================================
// <T>定义一个布尔值。</T>
//
// @param pName 名称
// @param value 内容
//============================================================
void FTemplateContext::DefineBool(TCharC* pName, TBool value){
   MO_INFO(TC("Template define bool value. (name=%s, value=%d)"), pName, value);
   _config->SetBool(pName, value);
}

//============================================================
// <T>定义一个整数。</T>
//
// @param pName 名称
// @param value 内容
//============================================================
void FTemplateContext::DefineInt(TCharC* pName, TInt value){
   MO_INFO(TC("Template define int value. (name=%s, value=%d)"), pName, value);
   _config->SetInt(pName, value);
}

//============================================================
// <T>定义一个字符串。</T>
//
// @param pName 名称
// @param pValue 字符串
//============================================================
void FTemplateContext::DefineString(TCharC* pName, TCharC* pValue){
   MO_INFO(TC("Template define string value. (name=%s, value=%s)"), pName, pValue);
   _config->Set(pName, pValue);
}

//============================================================
// <T>获得代码内存。</T>
//
// @return 代码内存
//============================================================
TCharC* FTemplateContext::SourceMemory(){
   return _pSource->MemoryC();
}

//============================================================
// <T>代码重置。</T>
//============================================================
void FTemplateContext::SourceReset(){
   _pSource->Clear();
}

//============================================================
// <T>写入代码内容。</T>
//============================================================
void FTemplateContext::SourceWrite(TCharC* pSource){
   // 检查参数
   if(pSource == NULL){
      return;
   }
   TInt length = RString::Length(pSource);
   if(length == 0){
      return;
   }
   // 输出内容
   TCharC* pWrite = pSource;
   if(_trimOnceFlag){
      // 删除前空格
      if((length > 1) && (pWrite[0] == '\r') && (pWrite[1] == '\n')){
         pWrite += 2;
      }else if((pWrite[0] == '\r') || (pWrite[0] == '\n')){
         pWrite++;
      }
      _trimOnceFlag = EFalse;
   }
   _pSource->Append(pWrite);
}

//============================================================
// <T>代码格式化。</T>
//============================================================
void FTemplateContext::SourceFormat(){
   TChar* pMemory = _pSource->Memory();
   TInt length = _pSource->Length();
   TInt position = 0;
   TBool start = !_trimBeginLine;
   TBool line = ETrue;
   // 扫描字符串
   for(TInt n = 0; n < length; n++){
      TChar value = pMemory[n];
      // 扫描换行符
      if(value == '\r'){
         continue;
      }
      if(_trimEmptyLine && (value == '\n')){
         if(!start){
            continue;
         }
         if(line){
            line = EFalse;
         }else{
            continue;
         }
      }else{
         start = ETrue;
         line = ETrue;
      }
      // 扫描注释行(行内含有//则删除当前行)
      //if(_trimCommentLine && (value == '\n')){
      //   TCharC* pFind = pMemory + n + 1;
      //   TInt find = RChars::IndexOf(pFind, length - n - 1, '\n');
      //   if(find != -1){
      //      TInt findComment = RChars::Find(pFind, find, "//", 2);
      //      if(findComment != -1){
      //         n += find;
      //      }
      //   }
      //}
      // 追加内容
      pMemory[position++] = value;
   }
   // 截取尾空行
   if(_trimEndLine && (pMemory[position - 1] == '\n')){
      position--;
   }
   _pSource->SetLength(position);
}

MO_NAMESPACE_END
