#include "MoMtFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsIntPoint2::TFsIntPoint2(){
}

//============================================================
TFsIntPoint2::TFsIntPoint2(const SIntPoint2& value){
   _value = value;
}

//============================================================
void TFsIntPoint2::operator=(const SIntPoint2& value){
   _value = value;
}

//============================================================
void TFsIntPoint2::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsIntPoint2::operator=(const TFsIntPoint2& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsIntPoint2::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SIntPoint2 TFsIntPoint2::Value() const{
   return _value;
}

//============================================================
void TFsIntPoint2::SetValue(SIntPoint2 value){
   _value = value;
}

//============================================================
TCharC* TFsIntPoint2::Format(){
   this->AssignFormat(TC("%d,%d"), _value.x, _value.y);
   return this->MemoryC();
}

MO_NAMESPACE_END
