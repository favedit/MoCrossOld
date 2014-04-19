#include "MoCm{string}.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//==================================================
M{string}s::M{string}s(){
   _count = 0;
   _pStrings = MO_CREATE(F{string}Vector);
}

//==================================================
M{string}s::~M{string}s(){
   TInt stringCount = _pStrings->Count();
   F{string}* pString = NULL;
   for(TInt n = 0; n < stringCount; n++){
      pString = _pStrings->Get(n);
      MO_DELETE(pString);
   }
   MO_DELETE(_pStrings);
}

//==================================================
{char}C* M{string}s::operator[](TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   F{string}* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//============================================================
void M{string}s::operator +=({char}C* pValue){
   Push(pValue);
}

//============================================================
void M{string}s::operator +=(const M{string}s& rhs){
   TInt count = rhs.Count();
   for(TInt i = 0; i < count; i++){
      Push(rhs[i]);
   }
}

//============================================================
void M{string}s::operator -=({char}C* pValue){
   Remove(pValue);
}

//============================================================
void M{string}s::operator -=(const M{string}s& rhs){
   Remove(rhs);
}

//==================================================
TBool M{string}s::IsEmpty() const{
   return 0 == _count;
}

//==================================================
TInt M{string}s::Count() const{
   return _count;
}

//==================================================
T{string}sIteratorC M{string}s::IteratorC() const{
   //return T{string}sIteratorC(_pStrings->MemoryC(), _count);
   return T{string}sIteratorC();
}

//==================================================
{char}C* M{string}s::First() const{
   if( 0 == _count){
      return NULL;
   }
   F{string}* pString = _pStrings->Get(0);
   return pString->MemoryC();
}

//==================================================
{char}C* M{string}s::Last() const{
   if(0 == _count){
      return NULL;
   }
   F{string}* pString = _pStrings->Get(_count - 1);
   return pString->MemoryC();
}

//==================================================
{char}C* M{string}s::Get(TInt index) const{
   MO_ASSERT_RANGE(index, 0, _count);
   F{string}* pString = _pStrings->Get(index);
   return pString->MemoryC();
}

//==================================================
TInt M{string}s::IndexOf({char}C* pValue) const{
   for(TInt n = 0; n < _count; n++){
      if(_pStrings->Get(n)->Equals(pValue)){
         return n;
      }
   }
   return ENotFound;
}

//==================================================
T{string} M{string}s::Join({char} splitter){
   T{string} result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
T{string} M{string}s::Join({char}C* splitter){
   T{string} result;
   for(TInt n = 0; n < _count; n++){
      if(n){
         result.Append(splitter);
      }
      result.Append(_pStrings->Get(n)->MemoryC());
   }
   return result;
}

//==================================================
TInt M{string}s::Pack({char}* pPack, TInt length){
   TInt valueLen, valueLenLen, byteCount = 0;
   F{string}* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      if(pPack){
         {char} buffer[16];
         RInt::To{string}(valueLenLen, buffer, sizeof(buffer));
         pPack[byteCount++] = buffer[0];
         RInt::To{string}(valueLen, buffer, sizeof(buffer));
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
T{string} M{string}s::Pack(){
   T{string} result;
   TInt valueLen, valueLenLen;
   F{string}* pString;
   for(TInt n = 0; n < _count; n++){
      pString = _pStrings->Get(n);
      valueLen = pString->Length();
      valueLenLen = RInt::CountDigit(valueLen);
      {char} buffer[16];
      result.Append(RInt::To{string}(valueLenLen, buffer, 16));
      result.Append(RInt::To{string}(valueLen, buffer, 16));
      result.Append(pString->MemoryC());
   }
   return result;
}

//============================================================
 void M{string}s::EnsureSize(TInt size){
   _pStrings->EnsureSize(size);
 }
 //============================================================
 void M{string}s::Set(TInt index, {char}C* pValue){
   MO_ASSERT_RANGE(index, 0, _count);
   _pStrings->Get(index)->Assign(pValue);
 }

//============================================================
void M{string}s::Assign(const M{string}s& strings){
   _pStrings->Clear();
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      F{string}* pString = MO_CREATE(F{string});
      pString->Assign(strings[i]);
      _pStrings->Push(pString);
   }
   _count = count;
}

//============================================================
void M{string}s::Append(const M{string}s& strings){
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
void M{string}s::AppendSplit({char}C* pValue, {char} splitter){
   T{string}Refer value(pValue);
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
void M{string}s::AppendSplit({char}C* pValue, {char}C* pSplitter){
   TInt length = RChar{type}s::Length(pSplitter);
   T{string}Refer value(pValue);
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
void M{string}s::Push({char}C* pValue){
   F{string}* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(pValue);
   }else{
      pString = MO_CREATE(F{string}, pValue);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void M{string}s::Push(const T{string}PtrC& value){
   F{string}* pString = NULL;
   if(_count < _pStrings->Count()){
      pString = _pStrings->Get(_count);
   }
   if(pString){
      pString->Assign(value);
   }else{
      pString = MO_CREATE(F{string},  value);
      _pStrings->Push(pString);
   }
   ++_count;
}

//============================================================
void M{string}s::Remove({char}C* pValue){
   for(TInt n = 0; n < _count; n++){
      F{string}* pString = _pStrings->Get(n);
      if(pString->Equals(pValue)){
         _pStrings->Delete(n);
         --_count;
         _pStrings->Push(pString);
      }
   }
}
//============================================================
void M{string}s::Remove(const M{string}s& strings){
   TInt count = strings.Count();
   for(TInt i = 0; i < count; i++){
      Remove(strings[i]);
   }
}

//============================================================
void M{string}s::Delete(TInt index){
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
void M{string}s::Split({char}C* pValue, {char} splitter){
   Clear();
   AppendSplit(pValue, splitter);
}

//============================================================
// <T>将一个字符串按指定字符分割成多个字符串，并存入清空后的容器。</T>
//
// @param pValue被分割串
// @param splitter 分割字符串
//============================================================
void M{string}s::Split({char}C* pValue, {char}C* pSplitter){
   Clear();
   AppendSplit(pValue, pSplitter);
}

//============================================================
void M{string}s::Unpack({char}C* pPack){
   TInt count = RChar{type}s::Length(pPack);
   TInt offset = 0;
   while(offset < count){
      TInt valueLenLen = pPack[offset++] - 4{type};
      MO_ASSERT(valueLenLen > 0 && valueLenLen <= 9);
      {char} buffer[16];
      MO_LIB_MEMORY_COPY(buffer, 16, pPack + offset, valueLenLen);
      offset += valueLenLen;
      buffer[valueLenLen] = 0;
      TInt valueLen = RNumber<TInt32>::ParseUnsign<{char}>(buffer);
      //F{string}* pString = MO_CREATE(F{string},  pPack + offset, valueLen);
      offset += valueLen;
   }
}

//============================================================
void M{string}s::Clear(){
   _pStrings->Clear();
   _count = 0;
}

MO_NAMESPACE_END
