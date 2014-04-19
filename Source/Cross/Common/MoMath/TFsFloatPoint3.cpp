#include "MoMtFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsFloatPoint3::TFsFloatPoint3(){
}

//============================================================
TFsFloatPoint3::TFsFloatPoint3(const SFloatPoint3& value){
   _value = value;
}

//============================================================
void TFsFloatPoint3::operator=(const SFloatPoint3& value){
   _value = value;
}

//============================================================
void TFsFloatPoint3::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsFloatPoint3::operator=(const TFsFloatPoint3& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsFloatPoint3::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SFloatPoint3 TFsFloatPoint3::Value() const{
   return _value;
}

//============================================================
void TFsFloatPoint3::SetValue(SFloatPoint3 value){
   _value = value;
}

//============================================================
TCharC* TFsFloatPoint3::Format(){
   this->AssignFormat(TC("%f,%f,%f"), _value.x, _value.y, _value.z);
   return this->MemoryC();
}

MO_NAMESPACE_END
