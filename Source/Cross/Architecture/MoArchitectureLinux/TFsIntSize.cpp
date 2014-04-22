#include "MoMtFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsIntSize::TFsIntSize(){
}

//============================================================
TFsIntSize::TFsIntSize(const SIntSize2& value){
   _value = value;
}

//============================================================
void TFsIntSize::operator=(const SIntSize2& value){
   _value = value;
}

//============================================================
void TFsIntSize::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsIntSize::operator=(const TFsIntSize& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsIntSize::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SIntSize2 TFsIntSize::Value() const{
   return _value;
}

//============================================================
void TFsIntSize::SetValue(SIntSize2 value){
   _value = value;
}

//============================================================
TCharC* TFsIntSize::Format(){
   this->AssignFormat(TC("%d,%d"), _value.width, _value.height);
   return this->MemoryC();
}

MO_NAMESPACE_END
