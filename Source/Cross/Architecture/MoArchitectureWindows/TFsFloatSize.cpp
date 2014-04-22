#include "MoMtFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsFloatSize::TFsFloatSize(){
}

//============================================================
TFsFloatSize::TFsFloatSize(const SFloatSize2& value){
   _value = value;
}

//============================================================
void TFsFloatSize::operator=(const SFloatSize2& value){
   _value = value;
}

//============================================================
void TFsFloatSize::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsFloatSize::operator=(const TFsFloatSize& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsFloatSize::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SFloatSize2 TFsFloatSize::Value() const{
   return _value;
}

//============================================================
void TFsFloatSize::SetValue(SFloatSize2 value){
   _value = value;
}

//============================================================
TCharC* TFsFloatSize::Format(){
   this->AssignFormat(TC("%f,%f"), _value.width, _value.height);
   return this->MemoryC();
}

MO_NAMESPACE_END
