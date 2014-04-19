#include "MoCmString32.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//==================================================
MString32s::MString32s(){
   _count = 0;
   _pStrings = MO_CREATE(FString32Vector);
}

//==================================================
MString32s::~MString32s(){
   TInt stringCount = _pStrings->Count();
   FString32* pString = NULL;
   for(TInt n = 0; n < stringCount; n++){
      pString = _pStrings->Get(n);
      MO_DELETE(pString);
   }
   MO_DELETE(_pStrings);
}

//==================================================
TChar32C* MString32s::operator[](TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   FString32* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//============================================================
void MString32s::operator +=(TChar32C* pValue){
   Push(pValue);
}

//============================================================
void MString32s::operator +=(const MString32s& rhs){
   TInt count = rhs.Count();
   for(TInt i = 0; i < count; i++){
      Push(rhs[i]);
   }
}

//============================================================
void MString32s::operator -=(TChar32C* pValue){
   Remove(pValue);
}

//============================================================
void MString32s::operator -=(const MString32s& rhs){
   Remove(rhs);
}

//==================================================
TBool MString32s::IsEmpty() const{
   return 0 == _count;
}

//==================================================
TInt MString32s::Count() const{
   return _count;
}

//==================================================
TString32sIteratorC MString32s::IteratorC() const{
   //return TString32sIteratorC(_pStrings->MemoryC(), _count);
   return TString32sIteratorC();
}

//==================================================
TChar32C* MString32s::First() const{
   if( 0 == _count){
      return NULL;
   }
   FString32* pString = _pStrings->Get(0);
   return pString->MemoryC();
}

//==================================================
TChar32C* MString32s::Last() const{
   if(0 == _count){
      return NULL;
   }
   FString32* pString = _pStrings->Get(_count - 1);
   return pString->MemoryC();
}

//==================================================
TChar32C* MString32s::Get(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   FString32* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//==================================================
TInt MString32s::IndexOf(TChar32C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(_pStrings->Get(n)->Equals(pValue)){
         return n;
      }
   }
   return ENotFound;
}

//==================================================
TString32 MString32s::Join(TChar32 splitter){
   TString32 result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
TString32 MString32s::Join(TChar32C* splitter){
   TString32 result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
TInt MString32s::Pack(TChar32* pPack, TInt length){
   TInt valueLen, valueLenLen, byteCount = 0;
   FString32* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      if(pPack){
         TChar32 buffer[16];
         RInt::ToString32(valueLenLen, buffer, sizeof(buffer));
         pPack[byteCount++] = buffer[0];
         RInt::ToString32(valueLen, buffer, sizeof(buffer));
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
TString32 MString32s::Pack(){
   TString32 result;
   TInt valueLen, valueLenLen;
   FString32* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      TChar32 buffer[16];
      result.Append(RInt::ToString32(valueLenLen, buffer, 16));
      result.Append(RInt::ToString32(valueLen, buffer, 16));
      result.Append(pString->MemoryC());
   }
   return result;
}

//============================================================
 void MString32s::EnsureSize(TInt size){
   _pStrings->EnsureSize(size);
 }
 //============================================================
 void MString32s::Set(TInt index, TChar32C* pValue){
   MO_ASSERT_RANGE(index, 0, _count);
   _pStrings->Get(index)->Assign(pValue);
 }

//============================================================
void MString32s::Assign(const MString32s& strings){
   _pStrings->Clear();
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      FString32* pString = MO_CREATE(FString32);
      pString->Assign(strings[i]);
      _pStrings->Push(pString);
   }
   _count = count;
}

//============================================================
void MString32s::Append(const MString32s& strings){
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
void MString32s::AppendSplit(TChar32C* pValue, TChar32 splitter){
   TString32Refer value(pValue);
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
void MString32s::AppendSplit(TChar32C* pValue, TChar32C* pSplitter){
   TInt length = RChar32s::Length(pSplitter);
   TString32Refer value(pValue);
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
void MString32s::Push(TChar32C* pValue){
   FString32* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(pValue);
   }else{
      pString = MO_CREATE(FString32, pValue);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void MString32s::Push(const TString32PtrC& value){
   FString32* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(value);
   }else{
      pString = MO_CREATE(FString32,  value);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void MString32s::Remove(TChar32C* pValue){
   for(TInt n = 0; n < _count; n++){
      FString32* pString = _pStrings->Get(n);
      if(pString->Equals(pValue)){
         _pStrings->Delete(n);
         --_count;
         _pStrings->Push(pString);
      }
   }
}
//============================================================
void MString32s::Remove(const MString32s& strings){
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      Remove(strings[i]);
   }
}

//============================================================
void MString32s::Delete(TInt index){
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
void MString32s::Split(TChar32C* pValue, TChar32 splitter){
   Clear();
   AppendSplit(pValue, splitter);
}

//============================================================
// <T>将一个字符串按指定字符分割成多个字符串，并存入清空后的容器。</T>
//
// @param pValue被分割串
// @param splitter 分割字符串
//============================================================
void MString32s::Split(TChar32C* pValue, TChar32C* pSplitter){
   Clear();
   AppendSplit(pValue, pSplitter);
}

//============================================================
void MString32s::Unpack(TChar32C* pPack){
   TInt count = RChar32s::Length(pPack);
   TInt offset = 0;
   while(offset < count){
      TInt valueLenLen = pPack[offset++] - 432;
      MO_ASSERT(valueLenLen > 0 && valueLenLen <= 9);
      TChar32 buffer[16];
      MO_LIB_MEMORY_COPY(buffer, 16, pPack + offset, valueLenLen);
      offset += valueLenLen;
      buffer[valueLenLen] = 0;
      TInt valueLen = RNumber<TInt32>::ParseUnsign<TChar32>(buffer);
      //FString32* pString = MO_CREATE(FString32,  pPack + offset, valueLen);
      offset += valueLen;
   }
}

//============================================================
void MString32s::Clear(){
   _pStrings->Clear();
   _count = 0;
}

MO_NAMESPACE_END
