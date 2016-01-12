#include "MoCrCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsTemplateId::TFsTemplateId(){
}

//============================================================
TFsTemplateId::TFsTemplateId(TTemplateId value){
   _value = value;
}

//============================================================
// <T>½«×Ö·û´®×ª»»ÎªÄ£°å±àºÅ¡£</T>
//
// @param pValue ×Ö·û´®
// @param length ³¤¶È
// @return Ä£°å±àºÅ
//============================================================
TTemplateId TFsTemplateId::Decode(TCharC* pValue, TInt length){
   return RUint32::ParseHex(pValue, length);
}

//============================================================
// <T>½«Ä£°å±àºÅ×ª»»Îª×Ö·û´®¡£</T>
//
// @param value Ä£°å±àºÅ
// @param pBuffer ×Ö·û´®»º³å
// @param capacity ×Ö·û´®ÈÝÁ¿
// @return ×Ö·û´®
//============================================================
TCharC* TFsTemplateId::Encode(TTemplateId value, TChar* pBuffer, TInt capacity){
   RUint32::ToHexString(value, pBuffer, capacity);
   pBuffer[8] = 0;
   return pBuffer;
}

//============================================================
void TFsTemplateId::operator=(TTemplateId value){
   _value = value;
}

//============================================================
void TFsTemplateId::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsTemplateId::operator=(const TFsTemplateId& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsTemplateId::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
TTemplateId TFsTemplateId::Value() const{
   return _value;
}

//============================================================
void TFsTemplateId::SetValue(TTemplateId value){
   _value = value;
}

//============================================================
// <T>´ò°üÊý¾ÝÎª×Ö·û´®¡£</T>
//
// @return ×Ö·û´®
//============================================================
TCharC* TFsTemplateId::Pack(){
   return Encode(_value, _pMemory, _capacity);
}

//============================================================
TCharC* TFsTemplateId::Format(){
   this->AssignFormat(TC("%02X-%06X(%d)"),
         (_value >> 24) % 0x000000FF, (_value % 0x00FFFFFF), _value);
   return this->MemoryC();
}

MO_NAMESPACE_END
