#include "MoCmLanguage.h"
#include "MoCmFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsDateTime::TFsDateTime(){
   _value = RDateTime::Current();
}

//============================================================
TFsDateTime::TFsDateTime(TDateTime value){
   _value = value;
}

//============================================================
TFsDateTime::TFsDateTime(const TFsDateTime& value) :
      TFixString<MO_FS_DATETIME_LENGTH>(value){
   _value = RDateTime::Current();
}

//============================================================
TFsDateTime::TFsDateTime(TCharC* pValue, TInt length) :
      TFixString<MO_FS_DATETIME_LENGTH>(pValue, length){
   _value = RDateTime::Current();
}

//============================================================
TFsDateTime::TFsDateTime(const TStringPtrC& value) :
      TFixString<MO_FS_DATETIME_LENGTH>(value){
   _value = RDateTime::Current();
}

//============================================================
// <T>½«×Ö·û´®×ª»»Îª¶ÔÏó±àºÅ¡£</T>
//
// @param pValue ×Ö·û´®
// @param length ³¤¶È
// @return ¶ÔÏó±àºÅ
//============================================================
TDateTime TFsDateTime::Decode(TCharC* pValue, TInt length){
   return RUint64::ParseHex(pValue, length);
}

//============================================================
// <T>½«¶ÔÏó±àºÅ×ª»»Îª×Ö·û´®¡£</T>
//
// @param value ¶ÔÏó±àºÅ
// @param pBuffer ×Ö·û´®»º³å
// @param capacity ×Ö·û´®ÈÝÁ¿
// @return ×Ö·û´®
//============================================================
TCharC* TFsDateTime::Encode(TDateTime value, TChar* pBuffer, TInt capacity){
   RUint64::ToHexString(value, pBuffer, capacity);
   pBuffer[16] = 0;
   return pBuffer;
}

//============================================================
void TFsDateTime::operator=(TDateTime value){
   _value = value;
}

//============================================================
void TFsDateTime::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsDateTime::operator=(const TFsDateTime& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsDateTime::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
TDateTime TFsDateTime::Value(){
   return _value;
}

//============================================================
void TFsDateTime::SetValue(TDateTime value){
   _value = value;
}

//============================================================
TCharC* TFsDateTime::Format(){
   return RDateTime::ToString(_pMemory, MO_FS_DATETIME_LENGTH, _value, RDateTime::DefaultFormat);
}

//============================================================
TCharC* TFsDateTime::Format(TCharC* pFormat){
   return RDateTime::ToString(_pMemory, MO_FS_DATETIME_LENGTH, _value, pFormat);
}

//============================================================
TBool TFsDateTime::Parse(TCharC* pValue, TCharC* pFormat){
   _value = RDateTime::Parse(pValue, pFormat);
   return ETrue;
}

//============================================================
TCharC* TFsDateTime::Pack(){
   return RUint64::ToHexString(_value, _memory, _capacity);
}

MO_NAMESPACE_END
