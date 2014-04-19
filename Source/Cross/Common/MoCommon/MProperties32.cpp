#include "MoCmString32.h"
#include "MoCmLanguage.h"
MO_NAMESPACE_BEGIN

//============================================================
MProperties32::MProperties32(){
   _count = 0;
   _pNames = MO_CREATE(FString32s);
   _pValues = MO_CREATE(FString32s);
}

//============================================================
MProperties32::~MProperties32(){
   MO_DELETE(_pNames);
   MO_DELETE(_pValues);
}

//============================================================
TChar32C* MProperties32::operator[](TChar32C* pName){
   return Get(pName);
}

//============================================================
TBool MProperties32::IsEmpty() const{
   return (0 == _count);
}

//============================================================
TInt MProperties32::Count() const{
   return _count;
}

//============================================================
TBool MProperties32::Contains(TChar32C* pName) const{
   MO_ASSERT(pName);
   TInt index = _pNames->IndexOf(pName);
   return (ENotFound != index);
}

//============================================================
TBool MProperties32::ContainsValue(TChar32C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(RChar32s::Equals(_pValues->Get(n), pValue)){
         return ETrue;
      }
   }
   return EFalse;
}
//============================================================
FString32s* MProperties32::Names(){
   return _pNames;
}

//============================================================
FString32s* MProperties32::Values(){
   return _pValues;
}

//============================================================
TChar32C* MProperties32::Name(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pNames->Get(index);
}

//============================================================
void MProperties32::SetName(TInt index, TChar32C* pName) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pNames->Set(index, pName);
}

//============================================================
TChar32C* MProperties32::Value(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pValues->Get(index);
}

//============================================================
void MProperties32::SetValue(TInt index, TChar32C* pValue) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pValues->Set(index, pValue);
}

//============================================================
TChar32C* MProperties32::Get(TChar32C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   return _pValues->Get(index);
}

//============================================================
TChar32C* MProperties32::Find(TChar32C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return NULL;
   }
   return _pValues->Get(index);
}

//============================================================
TChar32C* MProperties32::FindNvl(TChar32C* pName, TChar32C* pDefault) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return pDefault;
   }
   return _pValues->Get(index);
}

//============================================================
TChar32C* MProperties32::Search(TChar32C* pValue) const{
   TInt index = _pValues->IndexOf(pValue);
   if(ENotFound == index){
      return NULL;
   }
   return _pNames->Get(index);
}

//============================================================
void MProperties32::Join(FString32* pValue, TChar32 nameSpliter, TChar32 valueSpliter){
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
void MProperties32::Join(FString32* pValue, TChar32C* pNameSpliter, TChar32C* valueSpliter){
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
TInt MProperties32::Pack(TChar32* pPack, TInt length){
   TInt nameLength, valueLength, nameLenLen, valueLenLen;
   if(pPack){
      TChar32* writePos = pPack;
      TInt bytesWrite = 0;
      TChar32 lengthString[32];
      for(TInt n = 0; n < _count; n++){
         TFsDump32 onePair;
         TChar32C* pName = _pNames->Get(n);
         TChar32C* pValue = _pValues->Get(n);
         nameLength = RChar32s::Length(pName);
         valueLength = RChar32s::Length(pValue);
         nameLenLen = RInt::CountDigit(nameLength);
         valueLenLen = RInt::CountDigit(valueLength);
         // 名字长度的长度
         RNumber<TInt32>::ToUnsignString<TChar32>(lengthString, 16, nameLenLen);
         onePair.Append(lengthString);
         // 名字长度
         RNumber<TInt32>::ToUnsignString<TChar32>(lengthString, 16, nameLength);
         onePair.Append(lengthString);
         // 名字
         onePair.Append(pName);
         // 值长度的长度
         RNumber<TInt32>::ToUnsignString<TChar32>(lengthString, 16, valueLenLen);
         onePair.Append(lengthString);
         // 值长度
         RNumber<TInt32>::ToUnsignString<TChar32>(lengthString, 16, valueLength);
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
         nameLength = RChar32s::Length(_pNames->Get(n));
         nameLenLen = RInt::CountDigit(nameLength);
         valueLength = RChar32s::Length(_pValues->Get(n));
         valueLenLen = RInt::CountDigit(valueLength);
         byteCount += ( 1 + nameLenLen + nameLength + 1 + valueLenLen + valueLength);
      }
      return byteCount;
   }
}

//============================================================
TString32 MProperties32::Dump() const{
   TString32 temp;
   return temp;
}

//============================================================
void MProperties32::EnsureSize(TInt size){
   _pNames->EnsureSize(size);
   _pValues->EnsureSize(size);
}

//============================================================
void MProperties32::Assign(const MProperties32& properties){
   Clear();
   Append(properties);
}

//============================================================
void MProperties32::Append(const MProperties32& properties){
   TInt count = properties.Count();
   for(TInt n = 0; n < count; n++){
      _pNames->Push(properties.Name(n));
      _pValues->Push(properties.Value(n));
   }
   _count = count;
}

//============================================================
void MProperties32::AppendSplit(TChar32C* pValue, TChar32 nameSpliter, TChar32 valueSpliter){
   TString32Refer value(pValue);
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
void MProperties32::AppendSplit(TChar32C* pValue, TChar32C* pNameSpliter, TChar32C* valueSpliter){
   TString32Refer value(pValue);
   TInt nameSpliterLen = RChar32s::Length(pNameSpliter);
   TInt valueSpliterLen = RChar32s::Length(valueSpliter);
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
void MProperties32::Add(TChar32C* pName, TChar32C* pValue){
   _pNames->Push(pName);
   _pValues->Push(pValue);
   ++_count;
}

//============================================================
void MProperties32::Set(TChar32C* pName, TChar32C* pValue){
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   while(ENotFound != index){
      _pValues->Set(index, pValue);
      index = _pNames->IndexOf(pName);
   }
}

//============================================================
void MProperties32::Split(TChar32C* pValue, TChar32 nameSpliter, TChar32 valueSpliter){
   Clear();
   AppendSplit(pValue, nameSpliter, valueSpliter);
}

//============================================================
void MProperties32::Split(TChar32C* pValue, TChar32C* pNameSpliter, TChar32C* valueSpliter){
   Clear();
   AppendSplit(pValue, pNameSpliter, valueSpliter);
}

//============================================================
void MProperties32::Unpack(TChar32C* pPack){
   TString32Refer value(pPack);
   TInt count = value.Length();
   TInt offset = 0;
   while(offset < count){
      // name
      TFsName32 nameLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt nameLenLen = RNumber<TInt32>::ParseSign<TChar32>(nameLenLenStr);
      MO_ASSERT(nameLenLen >0 && nameLenLen <=9);
      TFsName32 nameLenStr = value.SubStrC(offset, offset + nameLenLen);
      offset += nameLenLen;
      TInt nameLen = RNumber<TInt32>::ParseSign<TChar32>(nameLenStr);
      _pNames->Push(value.SubStrC(offset, offset + nameLen));
      offset+= nameLen;
      // value
      TFsName32 valueLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt valueLenLen = RNumber<TInt32>::ParseSign<TChar32>(valueLenLenStr);
      MO_ASSERT((valueLenLen > 0) && (valueLenLen <= 9));
      TFsName32 valueLenStr = value.SubStrC(offset, offset + valueLenLen);
      offset += valueLenLen;
      TInt valueLen = RNumber<TInt32>::ParseSign<TChar32>(valueLenStr);
      _pNames->Push(value.SubStrC(offset, offset + valueLen));
      offset+= valueLen;
      ++_count;
   }
}

//============================================================
TString32 MProperties32::Remove(TChar32C* pName){
   TString32 value;
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
void MProperties32::Clear(){
   _pNames->Clear();
   _pValues->Clear();
   _count = 0;
}

MO_NAMESPACE_END
