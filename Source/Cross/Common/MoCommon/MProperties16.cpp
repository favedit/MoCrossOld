#include "MoCmString16.h"
#include "MoCmLanguage.h"
MO_NAMESPACE_BEGIN

//============================================================
MProperties16::MProperties16(){
   _count = 0;
   _pNames = MO_CREATE(FString16s);
   _pValues = MO_CREATE(FString16s);
}

//============================================================
MProperties16::~MProperties16(){
   MO_DELETE(_pNames);
   MO_DELETE(_pValues);
}

//============================================================
TChar16C* MProperties16::operator[](TChar16C* pName){
   return Get(pName);
}

//============================================================
TBool MProperties16::IsEmpty() const{
   return (0 == _count);
}

//============================================================
TInt MProperties16::Count() const{
   return _count;
}

//============================================================
TBool MProperties16::Contains(TChar16C* pName) const{
   MO_ASSERT(pName);
   TInt index = _pNames->IndexOf(pName);
   return (ENotFound != index);
}

//============================================================
TBool MProperties16::ContainsValue(TChar16C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(RChar16s::Equals(_pValues->Get(n), pValue)){
         return ETrue;
      }
   }
   return EFalse;
}
//============================================================
FString16s* MProperties16::Names(){
   return _pNames;
}

//============================================================
FString16s* MProperties16::Values(){
   return _pValues;
}

//============================================================
TChar16C* MProperties16::Name(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pNames->Get(index);
}

//============================================================
void MProperties16::SetName(TInt index, TChar16C* pName) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pNames->Set(index, pName);
}

//============================================================
TChar16C* MProperties16::Value(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pValues->Get(index);
}

//============================================================
void MProperties16::SetValue(TInt index, TChar16C* pValue) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pValues->Set(index, pValue);
}

//============================================================
TChar16C* MProperties16::Get(TChar16C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   return _pValues->Get(index);
}

//============================================================
TChar16C* MProperties16::Find(TChar16C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return NULL;
   }
   return _pValues->Get(index);
}

//============================================================
TChar16C* MProperties16::FindNvl(TChar16C* pName, TChar16C* pDefault) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return pDefault;
   }
   return _pValues->Get(index);
}

//============================================================
TChar16C* MProperties16::Search(TChar16C* pValue) const{
   TInt index = _pValues->IndexOf(pValue);
   if(ENotFound == index){
      return NULL;
   }
   return _pNames->Get(index);
}

//============================================================
void MProperties16::Join(FString16* pValue, TChar16 nameSpliter, TChar16 valueSpliter){
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
void MProperties16::Join(FString16* pValue, TChar16C* pNameSpliter, TChar16C* valueSpliter){
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
TInt MProperties16::Pack(TChar16* pPack, TInt length){
   TInt nameLength, valueLength, nameLenLen, valueLenLen;
   if(pPack){
      TChar16* writePos = pPack;
      TInt bytesWrite = 0;
      TChar16 lengthString[32];
      for(TInt n = 0; n < _count; n++){
         TFsDump16 onePair;
         TChar16C* pName = _pNames->Get(n);
         TChar16C* pValue = _pValues->Get(n);
         nameLength = RChar16s::Length(pName);
         valueLength = RChar16s::Length(pValue);
         nameLenLen = RInt::CountDigit(nameLength);
         valueLenLen = RInt::CountDigit(valueLength);
         // 名字长度的长度
         RNumber<TInt32>::ToUnsignString<TChar16>(lengthString, 16, nameLenLen);
         onePair.Append(lengthString);
         // 名字长度
         RNumber<TInt32>::ToUnsignString<TChar16>(lengthString, 16, nameLength);
         onePair.Append(lengthString);
         // 名字
         onePair.Append(pName);
         // 值长度的长度
         RNumber<TInt32>::ToUnsignString<TChar16>(lengthString, 16, valueLenLen);
         onePair.Append(lengthString);
         // 值长度
         RNumber<TInt32>::ToUnsignString<TChar16>(lengthString, 16, valueLength);
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
         nameLength = RChar16s::Length(_pNames->Get(n));
         nameLenLen = RInt::CountDigit(nameLength);
         valueLength = RChar16s::Length(_pValues->Get(n));
         valueLenLen = RInt::CountDigit(valueLength);
         byteCount += ( 1 + nameLenLen + nameLength + 1 + valueLenLen + valueLength);
      }
      return byteCount;
   }
}

//============================================================
TString16 MProperties16::Dump() const{
   TString16 temp;
   return temp;
}

//============================================================
void MProperties16::EnsureSize(TInt size){
   _pNames->EnsureSize(size);
   _pValues->EnsureSize(size);
}

//============================================================
void MProperties16::Assign(const MProperties16& properties){
   Clear();
   Append(properties);
}

//============================================================
void MProperties16::Append(const MProperties16& properties){
   TInt count = properties.Count();
   for(TInt n = 0; n < count; n++){
      _pNames->Push(properties.Name(n));
      _pValues->Push(properties.Value(n));
   }
   _count = count;
}

//============================================================
void MProperties16::AppendSplit(TChar16C* pValue, TChar16 nameSpliter, TChar16 valueSpliter){
   TString16Refer value(pValue);
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
void MProperties16::AppendSplit(TChar16C* pValue, TChar16C* pNameSpliter, TChar16C* valueSpliter){
   TString16Refer value(pValue);
   TInt nameSpliterLen = RChar16s::Length(pNameSpliter);
   TInt valueSpliterLen = RChar16s::Length(valueSpliter);
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
void MProperties16::Add(TChar16C* pName, TChar16C* pValue){
   _pNames->Push(pName);
   _pValues->Push(pValue);
   ++_count;
}

//============================================================
void MProperties16::Set(TChar16C* pName, TChar16C* pValue){
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   while(ENotFound != index){
      _pValues->Set(index, pValue);
      index = _pNames->IndexOf(pName);
   }
}

//============================================================
void MProperties16::Split(TChar16C* pValue, TChar16 nameSpliter, TChar16 valueSpliter){
   Clear();
   AppendSplit(pValue, nameSpliter, valueSpliter);
}

//============================================================
void MProperties16::Split(TChar16C* pValue, TChar16C* pNameSpliter, TChar16C* valueSpliter){
   Clear();
   AppendSplit(pValue, pNameSpliter, valueSpliter);
}

//============================================================
void MProperties16::Unpack(TChar16C* pPack){
   TString16Refer value(pPack);
   TInt count = value.Length();
   TInt offset = 0;
   while(offset < count){
      // name
      TFsName16 nameLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt nameLenLen = RNumber<TInt32>::ParseSign<TChar16>(nameLenLenStr);
      MO_ASSERT(nameLenLen >0 && nameLenLen <=9);
      TFsName16 nameLenStr = value.SubStrC(offset, offset + nameLenLen);
      offset += nameLenLen;
      TInt nameLen = RNumber<TInt32>::ParseSign<TChar16>(nameLenStr);
      _pNames->Push(value.SubStrC(offset, offset + nameLen));
      offset+= nameLen;
      // value
      TFsName16 valueLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt valueLenLen = RNumber<TInt32>::ParseSign<TChar16>(valueLenLenStr);
      MO_ASSERT((valueLenLen > 0) && (valueLenLen <= 9));
      TFsName16 valueLenStr = value.SubStrC(offset, offset + valueLenLen);
      offset += valueLenLen;
      TInt valueLen = RNumber<TInt32>::ParseSign<TChar16>(valueLenStr);
      _pNames->Push(value.SubStrC(offset, offset + valueLen));
      offset+= valueLen;
      ++_count;
   }
}

//============================================================
TString16 MProperties16::Remove(TChar16C* pName){
   TString16 value;
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
void MProperties16::Clear(){
   _pNames->Clear();
   _pValues->Clear();
   _count = 0;
}

MO_NAMESPACE_END
