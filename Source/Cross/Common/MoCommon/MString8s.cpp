#include "MoCmString8.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//==================================================
MString8s::MString8s(){
   _count = 0;
   _pStrings = MO_CREATE(FString8Vector);
}

//==================================================
MString8s::~MString8s(){
   TInt stringCount = _pStrings->Count();
   FString8* pString = NULL;
   for(TInt n = 0; n < stringCount; n++){
      pString = _pStrings->Get(n);
      MO_DELETE(pString);
   }
   MO_DELETE(_pStrings);
}

//==================================================
TChar8C* MString8s::operator[](TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   FString8* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//============================================================
void MString8s::operator +=(TChar8C* pValue){
   Push(pValue);
}

//============================================================
void MString8s::operator +=(const MString8s& rhs){
   TInt count = rhs.Count();
   for(TInt i = 0; i < count; i++){
      Push(rhs[i]);
   }
}

//============================================================
void MString8s::operator -=(TChar8C* pValue){
   Remove(pValue);
}

//============================================================
void MString8s::operator -=(const MString8s& rhs){
   Remove(rhs);
}

//==================================================
TBool MString8s::IsEmpty() const{
   return 0 == _count;
}

//==================================================
TInt MString8s::Count() const{
   return _count;
}

//==================================================
TString8sIteratorC MString8s::IteratorC() const{
   //return TString8sIteratorC(_pStrings->MemoryC(), _count);
   return TString8sIteratorC();
}

//==================================================
TChar8C* MString8s::First() const{
   if( 0 == _count){
      return NULL;
   }
   FString8* pString = _pStrings->Get(0);
   return pString->MemoryC();
}

//==================================================
TChar8C* MString8s::Last() const{
   if(0 == _count){
      return NULL;
   }
   FString8* pString = _pStrings->Get(_count - 1);
   return pString->MemoryC();
}

//==================================================
TChar8C* MString8s::Get(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   FString8* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//==================================================
TInt MString8s::IndexOf(TChar8C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(_pStrings->Get(n)->Equals(pValue)){
         return n;
      }
   }
   return ENotFound;
}

//==================================================
TString8 MString8s::Join(TChar8 splitter){
   TString8 result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
TString8 MString8s::Join(TChar8C* splitter){
   TString8 result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
TInt MString8s::Pack(TChar8* pPack, TInt length){
   TInt valueLen, valueLenLen, byteCount = 0;
   FString8* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      if(pPack){
         TChar8 buffer[16];
         RInt::ToString8(valueLenLen, buffer, sizeof(buffer));
         pPack[byteCount++] = buffer[0];
         RInt::ToString8(valueLen, buffer, sizeof(buffer));
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
TString8 MString8s::Pack(){
   TString8 result;
   TInt valueLen, valueLenLen;
   FString8* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      TChar8 buffer[16];
      result.Append(RInt::ToString8(valueLenLen, buffer, 16));
      result.Append(RInt::ToString8(valueLen, buffer, 16));
      result.Append(pString->MemoryC());
   }
   return result;
}

//============================================================
 void MString8s::EnsureSize(TInt size){
   _pStrings->EnsureSize(size);
 }
 //============================================================
 void MString8s::Set(TInt index, TChar8C* pValue){
   MO_ASSERT_RANGE(index, 0, _count);
   _pStrings->Get(index)->Assign(pValue);
 }

//============================================================
void MString8s::Assign(const MString8s& strings){
   _pStrings->Clear();
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      FString8* pString = MO_CREATE(FString8);
      pString->Assign(strings[i]);
      _pStrings->Push(pString);
   }
   _count = count;
}

//============================================================
void MString8s::Append(const MString8s& strings){
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
void MString8s::AppendSplit(TChar8C* pValue, TChar8 splitter){
   TString8Refer value(pValue);
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
void MString8s::AppendSplit(TChar8C* pValue, TChar8C* pSplitter){
   TInt length = RChar8s::Length(pSplitter);
   TString8Refer value(pValue);
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
void MString8s::Push(TChar8C* pValue){
   FString8* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(pValue);
   }else{
      pString = MO_CREATE(FString8, pValue);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void MString8s::Push(const TString8PtrC& value){
   FString8* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(value);
   }else{
      pString = MO_CREATE(FString8,  value);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void MString8s::Remove(TChar8C* pValue){
   for(TInt n = 0; n < _count; n++){
      FString8* pString = _pStrings->Get(n);
      if(pString->Equals(pValue)){
         _pStrings->Delete(n);
         --_count;
         _pStrings->Push(pString);
      }
   }
}
//============================================================
void MString8s::Remove(const MString8s& strings){
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      Remove(strings[i]);
   }
}

//============================================================
void MString8s::Delete(TInt index){
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
void MString8s::Split(TChar8C* pValue, TChar8 splitter){
   Clear();
   AppendSplit(pValue, splitter);
}

//============================================================
// <T>将一个字符串按指定字符分割成多个字符串，并存入清空后的容器。</T>
//
// @param pValue被分割串
// @param splitter 分割字符串
//============================================================
void MString8s::Split(TChar8C* pValue, TChar8C* pSplitter){
   Clear();
   AppendSplit(pValue, pSplitter);
}

//============================================================
void MString8s::Unpack(TChar8C* pPack){
   TInt count = RChar8s::Length(pPack);
   TInt offset = 0;
   while(offset < count){
      TInt valueLenLen = pPack[offset++] - 48;
      MO_ASSERT(valueLenLen > 0 && valueLenLen <= 9);
      TChar8 buffer[16];
      MO_LIB_MEMORY_COPY(buffer, 16, pPack + offset, valueLenLen);
      offset += valueLenLen;
      buffer[valueLenLen] = 0;
      TInt valueLen = RNumber<TInt32>::ParseUnsign<TChar8>(buffer);
      //FString8* pString = MO_CREATE(FString8,  pPack + offset, valueLen);
      offset += valueLen;
   }
}

//============================================================
void MString8s::Clear(){
   _pStrings->Clear();
   _count = 0;
}

MO_NAMESPACE_END
