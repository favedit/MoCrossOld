#include "MoMtFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsIntPoint3::TFsIntPoint3(){
}

//============================================================
TFsIntPoint3::TFsIntPoint3(const SIntPoint3& value){
   _value = value;
}

//============================================================
void TFsIntPoint3::operator=(const SIntPoint3& value){
   _value = value;
}

//============================================================
void TFsIntPoint3::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsIntPoint3::operator=(const TFsIntPoint3& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsIntPoint3::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SIntPoint3 TFsIntPoint3::Value() const{
   return _value;
}

//============================================================
void TFsIntPoint3::SetValue(SIntPoint3 value){
   _value = value;
}

//============================================================
TCharC* TFsIntPoint3::Format(){
   this->AssignFormat(TC("%d,%d,%d"), _value.x, _value.y, _value.z);
   return this->MemoryC();
}

MO_NAMESPACE_END
