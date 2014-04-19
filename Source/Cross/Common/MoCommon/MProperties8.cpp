#include "MoCmString8.h"
#include "MoCmLanguage.h"
MO_NAMESPACE_BEGIN

//============================================================
MProperties8::MProperties8(){
   _count = 0;
   _pNames = MO_CREATE(FString8s);
   _pValues = MO_CREATE(FString8s);
}

//============================================================
MProperties8::~MProperties8(){
   MO_DELETE(_pNames);
   MO_DELETE(_pValues);
}

//============================================================
TChar8C* MProperties8::operator[](TChar8C* pName){
   return Get(pName);
}

//============================================================
TBool MProperties8::IsEmpty() const{
   return (0 == _count);
}

//============================================================
TInt MProperties8::Count() const{
   return _count;
}

//============================================================
TBool MProperties8::Contains(TChar8C* pName) const{
   MO_ASSERT(pName);
   TInt index = _pNames->IndexOf(pName);
   return (ENotFound != index);
}

//============================================================
TBool MProperties8::ContainsValue(TChar8C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(RChar8s::Equals(_pValues->Get(n), pValue)){
         return ETrue;
      }
   }
   return EFalse;
}
//============================================================
FString8s* MProperties8::Names(){
   return _pNames;
}

//============================================================
FString8s* MProperties8::Values(){
   return _pValues;
}

//============================================================
TChar8C* MProperties8::Name(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pNames->Get(index);
}

//============================================================
void MProperties8::SetName(TInt index, TChar8C* pName) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pNames->Set(index, pName);
}

//============================================================
TChar8C* MProperties8::Value(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pValues->Get(index);
}

//============================================================
void MProperties8::SetValue(TInt index, TChar8C* pValue) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pValues->Set(index, pValue);
}

//============================================================
TChar8C* MProperties8::Get(TChar8C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   return _pValues->Get(index);
}

//============================================================
TChar8C* MProperties8::Find(TChar8C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return NULL;
   }
   return _pValues->Get(index);
}

//============================================================
TChar8C* MProperties8::FindNvl(TChar8C* pName, TChar8C* pDefault) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return pDefault;
   }
   return _pValues->Get(index);
}

//============================================================
TChar8C* MProperties8::Search(TChar8C* pValue) const{
   TInt index = _pValues->IndexOf(pValue);
   if(ENotFound == index){
      return NULL;
   }
   return _pNames->Get(index);
}

//============================================================
void MProperties8::Join(FString8* pValue, TChar8 nameSpliter, TChar8 valueSpliter){
   for(TInt n = 0; n < _count; n++){
      if(n){
         pValue->Append(valueSpliter);
      }
      pValue->Append(_pNames->Get(n));
      pValue->Append(nameSpliter);
      pValue->Append(_pValues->Get(n));
   }
}

//============================================================
void MProperties8::Join(FString8* pValue, TChar8C* pNameSpliter, TChar8C* valueSpliter){
   for(TInt n = 0; n < _count; n++){
      if(n){
         pValue->Append(valueSpliter);
      }
      pValue->Append(_pNames->Get(n));
      pValue->Append(pNameSpliter);
      pValue->Append(_pValues->Get(n));
   }
}

//============================================================
TInt MProperties8::Pack(TChar8* pPack, TInt length){
   TInt nameLength, valueLength, nameLenLen, valueLenLen;
   if(pPack){
      TChar8* writePos = pPack;
      TInt bytesWrite = 0;
      TChar8 lengthString[32];
      for(TInt n = 0; n < _count; n++){
         TFsDump8 onePair;
         TChar8C* pName = _pNames->Get(n);
         TChar8C* pValue = _pValues->Get(n);
         nameLength = RChar8s::Length(pName);
         valueLength = RChar8s::Length(pValue);
         nameLenLen = RInt::CountDigit(nameLength);
         valueLenLen = RInt::CountDigit(valueLength);
         // 名字长度的长度
         RNumber<TInt32>::ToUnsignString<TChar8>(lengthString, 16, nameLenLen);
         onePair.Append(lengthString);
         // 名字长度
         RNumber<TInt32>::ToUnsignString<TChar8>(lengthString, 16, nameLength);
         onePair.Append(lengthString);
         // 名字
         onePair.Append(pName);
         // 值长度的长度
         RNumber<TInt32>::ToUnsignString<TChar8>(lengthString, 16, valueLenLen);
         onePair.Append(lengthString);
         // 值长度
         RNumber<TInt32>::ToUnsignString<TChar8>(lengthString, 16, valueLength);
         onePair.Append(lengthString);
         // 值
         onePair.Append(pValue);
         TInt pairLen = onePair.Length();
         MO_LIB_MEMORY_COPY(writePos, length - bytesWrite, onePair.MemoryC(), pairLen);
         bytesWrite += pairLen;
         writePos+= pairLen;
      }
      return bytesWrite;
   }else{
      TInt byteCount = 0;
      for(TInt n = 0; n < _count; n++){
         nameLength = RChar8s::Length(_pNames->Get(n));
         nameLenLen = RInt::CountDigit(nameLength);
         valueLength = RChar8s::Length(_pValues->Get(n));
         valueLenLen = RInt::CountDigit(valueLength);
         byteCount += ( 1 + nameLenLen + nameLength + 1 + valueLenLen + valueLength);
      }
      return byteCount;
   }
}

//============================================================
TString8 MProperties8::Dump() const{
   TString8 temp;
   return temp;
}

//============================================================
void MProperties8::EnsureSize(TInt size){
   _pNames->EnsureSize(size);
   _pValues->EnsureSize(size);
}

//============================================================
void MProperties8::Assign(const MProperties8& properties){
   Clear();
   Append(properties);
}

//============================================================
void MProperties8::Append(const MProperties8& properties){
   TInt count = properties.Count();
   for(TInt n = 0; n < count; n++){
      _pNames->Push(properties.Name(n));
      _pValues->Push(properties.Value(n));
   }
   _count = count;
}

//============================================================
void MProperties8::AppendSplit(TChar8C* pValue, TChar8 nameSpliter, TChar8 valueSpliter){
   TString8Refer value(pValue);
   TInt offset = 0;
   TInt nameEnd = value.IndexOf(nameSpliter,  offset);
   while(ENotFound != nameEnd){
      _pNames->Push(value.SubStrC(offset, nameEnd));
      TInt valueEnd = value.IndexOf(valueSpliter, offset);
      _pValues->Push(value.SubStrC(nameEnd + 1, valueEnd));
      offset += (valueEnd + 1);
      nameEnd = value.IndexOf(nameSpliter,  offset);
      ++_count;
   }
}

//============================================================
void MProperties8::AppendSplit(TChar8C* pValue, TChar8C* pNameSpliter, TChar8C* valueSpliter){
   TString8Refer value(pValue);
   TInt nameSpliterLen = RChar8s::Length(pNameSpliter);
   TInt valueSpliterLen = RChar8s::Length(valueSpliter);
   TInt offset = 0;
   TInt nameEnd = value.Find(pNameSpliter,  offset);
   while(ENotFound != nameEnd){
      _pNames->Push(value.SubStrC(offset, nameEnd));
      TInt valueEnd = value.Find(valueSpliter, offset);
      _pValues->Push(value.SubStrC(nameEnd + nameSpliterLen + 1, valueEnd));
      offset += (valueEnd + valueSpliterLen + 1);
      nameEnd = value.Find(pNameSpliter,  offset);
      ++_count;
   }
}

//============================================================
void MProperties8::Add(TChar8C* pName, TChar8C* pValue){
   _pNames->Push(pName);
   _pValues->Push(pValue);
   ++_count;
}

//============================================================
void MProperties8::Set(TChar8C* pName, TChar8C* pValue){
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   while(ENotFound != index){
      _pValues->Set(index, pValue);
      index = _pNames->IndexOf(pName);
   }
}

//============================================================
void MProperties8::Split(TChar8C* pValue, TChar8 nameSpliter, TChar8 valueSpliter){
   Clear();
   AppendSplit(pValue, nameSpliter, valueSpliter);
}

//============================================================
void MProperties8::Split(TChar8C* pValue, TChar8C* pNameSpliter, TChar8C* valueSpliter){
   Clear();
   AppendSplit(pValue, pNameSpliter, valueSpliter);
}

//============================================================
void MProperties8::Unpack(TChar8C* pPack){
   TString8Refer value(pPack);
   TInt count = value.Length();
   TInt offset = 0;
   while(offset < count){
      // name
      TFsName8 nameLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt nameLenLen = RNumber<TInt32>::ParseSign<TChar8>(nameLenLenStr);
      MO_ASSERT(nameLenLen >0 && nameLenLen <=9);
      TFsName8 nameLenStr = value.SubStrC(offset, offset + nameLenLen);
      offset += nameLenLen;
      TInt nameLen = RNumber<TInt32>::ParseSign<TChar8>(nameLenStr);
      _pNames->Push(value.SubStrC(offset, offset + nameLen));
      offset+= nameLen;
      // value
      TFsName8 valueLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt valueLenLen = RNumber<TInt32>::ParseSign<TChar8>(valueLenLenStr);
      MO_ASSERT((valueLenLen > 0) && (valueLenLen <= 9));
      TFsName8 valueLenStr = value.SubStrC(offset, offset + valueLenLen);
      offset += valueLenLen;
      TInt valueLen = RNumber<TInt32>::ParseSign<TChar8>(valueLenStr);
      _pNames->Push(value.SubStrC(offset, offset + valueLen));
      offset+= valueLen;
      ++_count;
   }
}

//============================================================
TString8 MProperties8::Remove(TChar8C* pName){
   TString8 value;
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   value.Append(_pNames->Get(index));
   value.Append(_pValues->Get(index));
   _pNames->Delete(index);
   _pValues->Delete(index);
   --_count;
   return value;
}

//============================================================
void MProperties8::Clear(){
   _pNames->Clear();
   _pValues->Clear();
   _count = 0;
}

MO_NAMESPACE_END
