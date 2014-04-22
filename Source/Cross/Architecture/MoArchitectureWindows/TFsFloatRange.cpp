#include "MoMtFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsFloatRange::TFsFloatRange(){
}

//============================================================
TFsFloatRange::TFsFloatRange(const SFloatRange& value){
   _value = value;
}

//============================================================
void TFsFloatRange::operator=(const SFloatRange& value){
   _value = value;
}

//============================================================
void TFsFloatRange::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsFloatRange::operator=(const TFsFloatRange& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsFloatRange::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SFloatRange TFsFloatRange::Value() const{
   return _value;
}

//============================================================
void TFsFloatRange::SetValue(SFloatRange value){
   _value = value;
}

//============================================================
TCharC* TFsFloatRange::Format(){
   this->AssignFormat(TC("%f-%f"), _value.min, _value.max);
   return this->MemoryC();
}

MO_NAMESPACE_END
