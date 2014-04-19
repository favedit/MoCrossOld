#include "MoCm{string}.h"
#include "MoCmLanguage.h"
MO_NAMESPACE_BEGIN

//============================================================
MProperties{type}::MProperties{type}(){
   _count = 0;
   _pNames = MO_CREATE(F{string}s);
   _pValues = MO_CREATE(F{string}s);
}

//============================================================
MProperties{type}::~MProperties{type}(){
   MO_DELETE(_pNames);
   MO_DELETE(_pValues);
}

//============================================================
{char}C* MProperties{type}::operator[]({char}C* pName){
   return Get(pName);
}

//============================================================
TBool MProperties{type}::IsEmpty() const{
   return (0 == _count);
}

//============================================================
TInt MProperties{type}::Count() const{
   return _count;
}

//============================================================
TBool MProperties{type}::Contains({char}C* pName) const{
   MO_ASSERT(pName);
   TInt index = _pNames->IndexOf(pName);
   return (ENotFound != index);
}

//============================================================
TBool MProperties{type}::ContainsValue({char}C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(RChar{type}s::Equals(_pValues->Get(n), pValue)){
         return ETrue;
      }
   }
   return EFalse;
}
//============================================================
F{string}s* MProperties{type}::Names(){
   return _pNames;
}

//============================================================
F{string}s* MProperties{type}::Values(){
   return _pValues;
}

//============================================================
{char}C* MProperties{type}::Name(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pNames->Get(index);
}

//============================================================
void MProperties{type}::SetName(TInt index, {char}C* pName) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pNames->Set(index, pName);
}

//============================================================
{char}C* MProperties{type}::Value(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   return _pValues->Get(index);
}

//============================================================
void MProperties{type}::SetValue(TInt index, {char}C* pValue) const{
   MO_ASSERT_RANGE(index, 0, _count);
   _pValues->Set(index, pValue);
}

//============================================================
{char}C* MProperties{type}::Get({char}C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   return _pValues->Get(index);
}

//============================================================
{char}C* MProperties{type}::Find({char}C* pName) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return NULL;
   }
   return _pValues->Get(index);
}

//============================================================
{char}C* MProperties{type}::FindNvl({char}C* pName, {char}C* pDefault) const{
   TInt index = _pNames->IndexOf(pName);
   if(ENotFound == index){
      return pDefault;
   }
   return _pValues->Get(index);
}

//============================================================
{char}C* MProperties{type}::Search({char}C* pValue) const{
   TInt index = _pValues->IndexOf(pValue);
   if(ENotFound == index){
      return NULL;
   }
   return _pNames->Get(index);
}

//============================================================
void MProperties{type}::Join(F{string}* pValue, {char} nameSpliter, {char} valueSpliter){
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
void MProperties{type}::Join(F{string}* pValue, {char}C* pNameSpliter, {char}C* valueSpliter){
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
TInt MProperties{type}::Pack({char}* pPack, TInt length){
   TInt nameLength, valueLength, nameLenLen, valueLenLen;
   if(pPack){
      {char}* writePos = pPack;
      TInt bytesWrite = 0;
      {char} lengthString[32];
      for(TInt n = 0; n < _count; n++){
         TFsDump{type} onePair;
         {char}C* pName = _pNames->Get(n);
         {char}C* pValue = _pValues->Get(n);
         nameLength = RChar{type}s::Length(pName);
         valueLength = RChar{type}s::Length(pValue);
         nameLenLen = RInt::CountDigit(nameLength);
         valueLenLen = RInt::CountDigit(valueLength);
         // 名字长度的长度
         RNumber<TInt32>::ToUnsignString<{char}>(lengthString, 16, nameLenLen);
         onePair.Append(lengthString);
         // 名字长度
         RNumber<TInt32>::ToUnsignString<{char}>(lengthString, 16, nameLength);
         onePair.Append(lengthString);
         // 名字
         onePair.Append(pName);
         // 值长度的长度
         RNumber<TInt32>::ToUnsignString<{char}>(lengthString, 16, valueLenLen);
         onePair.Append(lengthString);
         // 值长度
         RNumber<TInt32>::ToUnsignString<{char}>(lengthString, 16, valueLength);
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
         nameLength = RChar{type}s::Length(_pNames->Get(n));
         nameLenLen = RInt::CountDigit(nameLength);
         valueLength = RChar{type}s::Length(_pValues->Get(n));
         valueLenLen = RInt::CountDigit(valueLength);
         byteCount += ( 1 + nameLenLen + nameLength + 1 + valueLenLen + valueLength);
      }
      return byteCount;
   }
}

//============================================================
T{string} MProperties{type}::Dump() const{
   T{string} temp;
   return temp;
}

//============================================================
void MProperties{type}::EnsureSize(TInt size){
   _pNames->EnsureSize(size);
   _pValues->EnsureSize(size);
}

//============================================================
void MProperties{type}::Assign(const MProperties{type}& properties){
   Clear();
   Append(properties);
}

//============================================================
void MProperties{type}::Append(const MProperties{type}& properties){
   TInt count = properties.Count();
   for(TInt n = 0; n < count; n++){
      _pNames->Push(properties.Name(n));
      _pValues->Push(properties.Value(n));
   }
   _count = count;
}

//============================================================
void MProperties{type}::AppendSplit({char}C* pValue, {char} nameSpliter, {char} valueSpliter){
   T{string}Refer value(pValue);
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
void MProperties{type}::AppendSplit({char}C* pValue, {char}C* pNameSpliter, {char}C* valueSpliter){
   T{string}Refer value(pValue);
   TInt nameSpliterLen = RChar{type}s::Length(pNameSpliter);
   TInt valueSpliterLen = RChar{type}s::Length(valueSpliter);
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
void MProperties{type}::Add({char}C* pName, {char}C* pValue){
   _pNames->Push(pName);
   _pValues->Push(pValue);
   ++_count;
}

//============================================================
void MProperties{type}::Set({char}C* pName, {char}C* pValue){
   TInt index = _pNames->IndexOf(pName);
   MO_ASSERT(ENotFound != index);
   while(ENotFound != index){
      _pValues->Set(index, pValue);
      index = _pNames->IndexOf(pName);
   }
}

//============================================================
void MProperties{type}::Split({char}C* pValue, {char} nameSpliter, {char} valueSpliter){
   Clear();
   AppendSplit(pValue, nameSpliter, valueSpliter);
}

//============================================================
void MProperties{type}::Split({char}C* pValue, {char}C* pNameSpliter, {char}C* valueSpliter){
   Clear();
   AppendSplit(pValue, pNameSpliter, valueSpliter);
}

//============================================================
void MProperties{type}::Unpack({char}C* pPack){
   T{string}Refer value(pPack);
   TInt count = value.Length();
   TInt offset = 0;
   while(offset < count){
      // name
      TFsName{type} nameLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt nameLenLen = RNumber<TInt32>::ParseSign<{char}>(nameLenLenStr);
      MO_ASSERT(nameLenLen >0 && nameLenLen <=9);
      TFsName{type} nameLenStr = value.SubStrC(offset, offset + nameLenLen);
      offset += nameLenLen;
      TInt nameLen = RNumber<TInt32>::ParseSign<{char}>(nameLenStr);
      _pNames->Push(value.SubStrC(offset, offset + nameLen));
      offset+= nameLen;
      // value
      TFsName{type} valueLenLenStr = value.SubStrC(offset, offset + 1);
      offset++;
      TInt valueLenLen = RNumber<TInt32>::ParseSign<{char}>(valueLenLenStr);
      MO_ASSERT((valueLenLen > 0) && (valueLenLen <= 9));
      TFsName{type} valueLenStr = value.SubStrC(offset, offset + valueLenLen);
      offset += valueLenLen;
      TInt valueLen = RNumber<TInt32>::ParseSign<{char}>(valueLenStr);
      _pNames->Push(value.SubStrC(offset, offset + valueLen));
      offset+= valueLen;
      ++_count;
   }
}

//============================================================
T{string} MProperties{type}::Remove({char}C* pName){
   T{string} value;
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
void MProperties{type}::Clear(){
   _pNames->Clear();
   _pValues->Clear();
   _count = 0;
}

MO_NAMESPACE_END
