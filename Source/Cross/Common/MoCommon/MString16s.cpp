#include "MoCmString16.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//==================================================
MString16s::MString16s(){
   _count = 0;
   _pStrings = MO_CREATE(FString16Vector);
}

//==================================================
MString16s::~MString16s(){
   TInt stringCount = _pStrings->Count();
   FString16* pString = NULL;
   for(TInt n = 0; n < stringCount; n++){
      pString = _pStrings->Get(n);
      MO_DELETE(pString);
   }
   MO_DELETE(_pStrings);
}

//==================================================
TChar16C* MString16s::operator[](TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   FString16* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//============================================================
void MString16s::operator +=(TChar16C* pValue){
   Push(pValue);
}

//============================================================
void MString16s::operator +=(const MString16s& rhs){
   TInt count = rhs.Count();
   for(TInt i = 0; i < count; i++){
      Push(rhs[i]);
   }
}

//============================================================
void MString16s::operator -=(TChar16C* pValue){
   Remove(pValue);
}

//============================================================
void MString16s::operator -=(const MString16s& rhs){
   Remove(rhs);
}

//==================================================
TBool MString16s::IsEmpty() const{
   return 0 == _count;
}

//==================================================
TInt MString16s::Count() const{
   return _count;
}

//==================================================
TString16sIteratorC MString16s::IteratorC() const{
   //return TString16sIteratorC(_pStrings->MemoryC(), _count);
   return TString16sIteratorC();
}

//==================================================
TChar16C* MString16s::First() const{
   if( 0 == _count){
      return NULL;
   }
   FString16* pString = _pStrings->Get(0);
   return pString->MemoryC();
}

//==================================================
TChar16C* MString16s::Last() const{
   if(0 == _count){
      return NULL;
   }
   FString16* pString = _pStrings->Get(_count - 1);
   return pString->MemoryC();
}

//==================================================
TChar16C* MString16s::Get(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   FString16* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//==================================================
TInt MString16s::IndexOf(TChar16C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(_pStrings->Get(n)->Equals(pValue)){
         return n;
      }
   }
   return ENotFound;
}

//==================================================
TString16 MString16s::Join(TChar16 splitter){
   TString16 result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
TString16 MString16s::Join(TChar16C* splitter){
   TString16 result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
TInt MString16s::Pack(TChar16* pPack, TInt length){
   TInt valueLen, valueLenLen, byteCount = 0;
   FString16* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      if(pPack){
         TChar16 buffer[16];
         RInt::ToString16(valueLenLen, buffer, sizeof(buffer));
         pPack[byteCount++] = buffer[0];
         RInt::ToString16(valueLen, buffer, sizeof(buffer));
         MO_LIB_MEMORY_COPY(&pPack[byteCount], length - byteCount, buffer, valueLenLen);
         byteCount += valueLenLen;
         MO_LIB_MEMORY_COPY(&pPack[byteCount], length - byteCount, pString->MemoryC(), valueLen);
         byteCount += valueLen;
      }else{
         byteCount += (1 + valueLenLen + valueLen);
      }
   }
   if(pPack){
      pPack[byteCount] = 0;
   }
   return byteCount;
}

//==================================================
TString16 MString16s::Pack(){
   TString16 result;
   TInt valueLen, valueLenLen;
   FString16* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      TChar16 buffer[16];
      result.Append(RInt::ToString16(valueLenLen, buffer, 16));
      result.Append(RInt::ToString16(valueLen, buffer, 16));
      result.Append(pString->MemoryC());
   }
   return result;
}

//============================================================
 void MString16s::EnsureSize(TInt size){
   _pStrings->EnsureSize(size);
 }
 //============================================================
 void MString16s::Set(TInt index, TChar16C* pValue){
   MO_ASSERT_RANGE(index, 0, _count);
   _pStrings->Get(index)->Assign(pValue);
 }

//============================================================
void MString16s::Assign(const MString16s& strings){
   _pStrings->Clear();
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      FString16* pString = MO_CREATE(FString16);
      pString->Assign(strings[i]);
      _pStrings->Push(pString);
   }
   _count = count;
}

//============================================================
void MString16s::Append(const MString16s& strings){
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      Push(strings[i]);
   }
}

//============================================================
// <T>将一个字符串按指定字符分割成多个字符串，并存入容器。</T>
//
// @param pValue被分割串
// @param splitter 分割字符
//============================================================
void MString16s::AppendSplit(TChar16C* pValue, TChar16 splitter){
   TString16Refer value(pValue);
   TInt begin = 0;
   TInt index = value.IndexOf(splitter, begin);
   while(ENotFound != index){
      Push(value.SubStrC(begin, index));
      begin = index + 1;
      index = value.IndexOf(splitter, begin);
   }
}

//============================================================
// <T>将一个字符串用指定字符串分割成多个字符串，并存入容器。</T>
//
// @param pValue被分割串
// @param pSplitter 分割串
//============================================================
void MString16s::AppendSplit(TChar16C* pValue, TChar16C* pSplitter){
   TInt length = RChar16s::Length(pSplitter);
   TString16Refer value(pValue);
   // 上一个结束位置
   TInt begin = 0 ;
   TInt index = value.Find(pSplitter);
   while(ENotFound != index){
      Push(value.SubStrC(begin, index));
      begin = index + length;
      index = value.Find(pSplitter, begin);
   }
}

//============================================================
void MString16s::Push(TChar16C* pValue){
   FString16* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(pValue);
   }else{
      pString = MO_CREATE(FString16, pValue);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void MString16s::Push(const TString16PtrC& value){
   FString16* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(value);
   }else{
      pString = MO_CREATE(FString16,  value);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void MString16s::Remove(TChar16C* pValue){
   for(TInt n = 0; n < _count; n++){
      FString16* pString = _pStrings->Get(n);
      if(pString->Equals(pValue)){
         _pStrings->Delete(n);
         --_count;
         _pStrings->Push(pString);
      }
   }
}
//============================================================
void MString16s::Remove(const MString16s& strings){
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      Remove(strings[i]);
   }
}

//============================================================
void MString16s::Delete(TInt index){
   MO_ASSERT_RANGE(index, 0, _count);
   _pStrings->Delete(index);
   --_count;
}

//============================================================
// <T>将一个字符串按指定字符分割成多个字符串，并存入清空后的容器。</T>
//
// @param pValue被分割串
// @param splitter 分割字符
//============================================================
void MString16s::Split(TChar16C* pValue, TChar16 splitter){
   Clear();
   AppendSplit(pValue, splitter);
}

//============================================================
// <T>将一个字符串按指定字符分割成多个字符串，并存入清空后的容器。</T>
//
// @param pValue被分割串
// @param splitter 分割字符串
//============================================================
void MString16s::Split(TChar16C* pValue, TChar16C* pSplitter){
   Clear();
   AppendSplit(pValue, pSplitter);
}

//============================================================
void MString16s::Unpack(TChar16C* pPack){
   TInt count = RChar16s::Length(pPack);
   TInt offset = 0;
   while(offset < count){
      TInt valueLenLen = pPack[offset++] - 416;
      MO_ASSERT(valueLenLen > 0 && valueLenLen <= 9);
      TChar16 buffer[16];
      MO_LIB_MEMORY_COPY(buffer, 16, pPack + offset, valueLenLen);
      offset += valueLenLen;
      buffer[valueLenLen] = 0;
      TInt valueLen = RNumber<TInt32>::ParseUnsign<TChar16>(buffer);
      //FString16* pString = MO_CREATE(FString16,  pPack + offset, valueLen);
      offset += valueLen;
   }
}

//============================================================
void MString16s::Clear(){
   _pStrings->Clear();
   _count = 0;
}

MO_NAMESPACE_END
