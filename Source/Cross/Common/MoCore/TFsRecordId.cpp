#include "MoCrCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsRecordId::TFsRecordId(){
}

//============================================================
TFsRecordId::TFsRecordId(TRecordId value){
   _value = value;
}

//============================================================
// <T>½«×Ö·û´®×ª»»Îª¼ÇÂ¼±àºÅ¡£</T>
//
// @param pValue ×Ö·û´®
// @param length ³¤¶È
// @return ¼ÇÂ¼±àºÅ
//============================================================
TRecordId TFsRecordId::Decode(TCharC* pValue, TInt length){
   return RInt64::ParseHex(pValue, length);
}

//============================================================
// <T>½«¼ÇÂ¼±àºÅ×ª»»Îª×Ö·û´®¡£</T>
//
// @param value ¼ÇÂ¼±àºÅ
// @param pBuffer ×Ö·û´®»º³å
// @param capacity ×Ö·û´®ÈÝÁ¿
// @return ×Ö·û´®
//============================================================
TCharC* TFsRecordId::Encode(TRecordId value, TChar* pBuffer, TInt capacity){
   RInt64::ToHexString(value, pBuffer, capacity);
   pBuffer[16] = 0;
   return pBuffer;
}

//============================================================
void TFsRecordId::operator=(TRecordId value){
   _value = value;
}

//============================================================
void TFsRecordId::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsRecordId::operator=(const TFsRecordId& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsRecordId::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
TRecordId TFsRecordId::Get() const{
   return _value;
}

//============================================================
void TFsRecordId::Set(TRecordId value){
   _value = value;
}

//============================================================
// <T>´ò°üÊý¾ÝÎª×Ö·û´®¡£</T>
//
// @return ×Ö·û´®
//============================================================
TCharC* TFsRecordId::Pack(){
   return Encode(_value, _pMemory, _capacity);
}

//============================================================
TCharC* TFsRecordId::Format(){
   AssignFormat(TC("%08X - %ld"), _value, _value);
   return MemoryC();
}

MO_NAMESPACE_END
