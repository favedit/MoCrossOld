#include "MoCmData.h"
#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
FField::FField(){
   _type = ESqlType_String;
}

//============================================================
FField::~FField(){
}

//============================================================
TCharC* FField::Name(){
   return _name;
}

//============================================================
ESqlType FField::Type(){
   return _type;
}

//============================================================
void FField::SetName(TCharC* pName){
   _name = pName;
}

//============================================================
void FField::SetType(ESqlType type){
   _type = type;
}

MO_NAMESPACE_END
