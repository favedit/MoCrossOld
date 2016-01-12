#ifndef __MO_CR_NET_COLLECTION_H__
#define __MO_CR_NET_COLLECTION_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>网络消息数据类型。</T>
//wenti 
// @history 100318 MAOCY 创建
//============================================================
template <typename T, TInt S>
class TFsNetTypes : public TStackTypes<T, S>{
public:
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count(){
      return this->_length;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前容量。</T>
   MO_INLINE TSize Capacity(){
      TSize capacity = sizeof(TUint16);
      capacity += sizeof(T) * this->_length;
      return capacity;
   }
   //------------------------------------------------------------
   // <T>序列化内部数据到数据区。</T>
   TInt Serialize(TAny* pMemory){
      TByte* pPtr = (TByte*)pMemory;
      *(TUint16*)pPtr = (TUint16)this->_length;
      TInt total = sizeof(T) * this->_length;
      MO_LIB_MEMORY_COPY(pPtr + sizeof(TUint16), total, this->_memory, total);
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>反序列化数据区到内部数据。</T>
   TInt Unserialize(TAnyC* pMemory){
      TByteC* pPtr = (TByteC*)pMemory;
      // 检查长度
      TInt length = *(TUint16*)pPtr;
      if(this->_length > S){
         MO_ERROR(TC("Unserialize net types not enough memory. (size=%d, length=%d)"), S, this->_length);
         return -1;
      }
      this->_length = length;
      // 复制数据
      TInt total = sizeof(T) * length;
      if(total > 0){
         MO_LIB_MEMORY_COPY(this->_memory, sizeof(T) * S, pPtr + sizeof(TUint16), total);
      }
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>打包数据到字符串。</T>
   TBool Pack(MPack* pPack){
      MO_FATAL_UNSUPPORT();
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>解包字符串到内部数据。</T>
   TBool Unpack(MPack* pPack){
      MO_FATAL_UNSUPPORT();
      return EFalse;
   }
public:
   //------------------------------------------------------------
   // <T>跟踪内部数据。</T>
   TCharC* Track(MString* pTrack){
      MO_ASSERT(pTrack);
      pTrack->AppendFormat(TC("[length=%d]"), this->_length);
      return pTrack->MemoryC();
   }
};

//============================================================
// <T>网络消息字符串。</T>
//
// @history 100902 MAOCY 创建
//============================================================
template <TInt S>
class TFsNetBytes : public TFsNetTypes<TByte, S>{
public:
   //------------------------------------------------------------
   // <T>构造网络字符串。</T>
   TFsNetBytes(){
   }
public:
   //------------------------------------------------------------
   // <T>跟踪内部数据。</T>
   TCharC* Track(MString* pTrack, TInt level){
      MO_ASSERT(pTrack);
      TInt trackLength = MO_MIN(this->_length, 32);
      pTrack->AppendFormat("[length=%d] %s",
            this->_length,
            RByte::FormatDisplay(this->_memory, trackLength, (TChar*)TFsText(), TFsText::Size()));
      return pTrack->MemoryC();
   }
};

//============================================================
// <T>网络消息字符串。</T>
//
// @history 100902 MAOCY 创建
//============================================================
template <TInt S>
class TFsNetString : public TFsNetTypes<TChar, S>{
public:
   //------------------------------------------------------------
   // <T>构造网络字符串。</T>
   TFsNetString(){
      Set(NULL);
   }
   //------------------------------------------------------------
   // <T>构造网络字符串。</T>
   TFsNetString(TCharC* pValue){
      Set(pValue);
   }
public:
   //------------------------------------------------------------
   MO_INLINE void operator=(TCharC* pValue){
      Set(pValue);
   }
public:
   //------------------------------------------------------------
   // <T>获得当前容积，保留一个尾结束字符。</T>
   MO_INLINE TSize Capacity(){
      TSize capacity = sizeof(TUint16) + 1;
      capacity += sizeof(TChar) * this->_length;
      return capacity;
   }
   //------------------------------------------------------------
   // <T>是否相等。</T>
   MO_INLINE TBool Equals(TCharC* pMemory, TInt length = -1){
      MO_ASSERT(pMemory);
      if(length <= 0){
         length = RString::Length(pMemory);
      }
      if(this->_length != length){
         return EFalse;
      }
      return RTypes<TCharC>::Equals((TCharC*)this->_memory, pMemory, length);
   }
   //------------------------------------------------------------
   // <T>复制信息到自己内部。</T>
   MO_INLINE void Assign(const TFsNetString<S>& value){
      Set((TCharC*)value._memory);
      this->_length = value._length;
   }
   // ------------------------------------------------------------
   // 追加一个格式字符串
   void AssignFormat(TCharC* pFormat, va_list& params){
      this->Clear();
#ifdef _MO_WINDOWS
#else
      TChar buffer[MO_FS_SPRINT_LENGTH];
      TInt length = vsnprintf(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
      buffer[length] = 0;
      Append(buffer);
#endif
   }
   //------------------------------------------------------------
   // <T>追加一个字符串到当前字符串对象的末尾。</T>
   // <P>如果长度小于0，则自动计算指针的数据长度。</P>
   void Append(TCharC* pValue, TInt length = -1){
      // 检查指针
      if(NULL == pValue){
         return;
      }
      // 获取长度
      if(length <= 0){
         length = RTypes<TChar>::IndexOf(pValue, 0);
      }
      // 检查长度
      if(this->_length + length >= S){
         MO_FATAL(TC("Append string length invalid. (length=%d, size=%d, value=%d:%s)"), this->_length, S, length, pValue);
         length = S - this->_length - 1;
      }
      // 复制数据
      if(length > 0){
         TChar* pMemory = (TChar*)this->_memory;
         MO_LIB_MEMORY_COPY(pMemory + this->_length, S - this->_length, pValue, sizeof(TChar) * length);
         this->_length += length;
         pMemory[this->_length] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个格式化字符串到对象尾部。</T>
   void AppendFormat(TCharC* pFormat, ...){
      // 格式化可变参数字符串信息
      va_list params;
      va_start(params, pFormat);
      // 输出日志信息
   #ifdef _MO_WINDOWS
      TInt length = MO_LIB_STRING_FORMAT_LENGTH(pFormat, params);
      MO_ASSERT_RANGE(this->_length + length + 1, 0, S);
      length = MO_LIB_STRING_FORMAT((TChar*)_memory + _length, length + 1, pFormat, params);
      this->_length += length;
   #else
      TChar buffer[MO_FS_SPRINT_LENGTH];
      TInt length = vsnprintf(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
      buffer[length] = 0;
      Append(buffer);
   #endif // _MO_WINDOWS
      va_end(params);
   }
   // ------------------------------------------------------------
   // 追加一个格式字符串
   void AppendFormat(TCharC* pFormat, va_list& params){
#ifdef _MO_WINDOWS
      TInt length = MO_LIB_STRING_FORMAT_LENGTH(pFormat, params);
      MO_ASSERT_RANGE(this->_length + length + 1, 0, S);
      length = MO_LIB_STRING_FORMAT((TChar*)_memory + _length, length + 1, pFormat, params);
      this->_length += length;
#else
      TChar buffer[MO_FS_SPRINT_LENGTH];
      TInt length = vsnprintf(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
      buffer[length] = 0;
      Append(buffer);
#endif // _MO_WINDOWS
   }
   //------------------------------------------------------------
   // <T>设置内部数据。</T>
   MO_INLINE void Set(TCharC* pValue){
      TInt length = 0;
      if(NULL != pValue){
         length = RTypes<TChar>::IndexOf(pValue, 0);
         if(length >= S){
            MO_FATAL(TC("Set string length invalid. (size=%d, value=%d:%s)"), S, length, pValue);
            length = S - 1;
         }
         if(length > 0){
            MO_LIB_MEMORY_COPY(this->_memory, sizeof(TChar) * S, pValue, sizeof(TChar) * length);
         }
      }
      this->_length = length;
      this->_memory[this->_length] = 0;
   }
   //------------------------------------------------------------
   // <T>序列化内部数据到数据区。</T>
   // <P>多保存一个结尾空字符。</P>
   MO_INLINE TInt Serialize(TAny* pMemory){
      TByte* pPtr = (TByte*)pMemory;
      *(TUint16*)pPtr = (TUint16)this->_length;
      TInt total = sizeof(TChar) * this->_length;
      MO_LIB_MEMORY_COPY(pPtr + sizeof(TUint16), total, this->_memory, total);
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>反序列化数据区到内部数据。</T>
   MO_INLINE TInt Unserialize(TAnyC* pMemory){
      TByteC* pPtr = (TByteC*)pMemory;
      TInt length = *(TUint16*)pPtr;
      // 检查长度
      if(length >= S){
         MO_ERROR(TC("Unserialize net string not enough memory. (size=%d, length=%d)"), S, length);
         return -1;
      }
      this->_length = length;
      // 复制内存数据
      TInt total = sizeof(TChar) * length;
      if(total > 0){
         MO_LIB_MEMORY_COPY(this->_memory, sizeof(TChar) * S, pPtr + sizeof(TUint16), total);
      }
      this->_memory[length] = 0;
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>输出内部数据到外部。</T>
   MO_INLINE TCharC* ToDisplay(TChar* pValue, TInt capacity){
      MO_ASSERT(pValue);
      MO_ASSERT(capacity > this->_length + 1);
      TStringRefer refer(pValue, capacity);
      if(this->_length > 0){
         refer.AppendFormat(TC("%d:\"%s\""), this->_length, this->_memory);
      }else{
         refer.Append(TC("0:\"\""));
      }
      return pValue;
   }
   //------------------------------------------------------------
   // <T>输出内部数据到外部。</T>
   MO_INLINE TCharC* ToString(TChar* pValue, TInt capacity){
      MO_ASSERT(pValue);
      MO_ASSERT(this->_length + 1 < capacity);
      memcpy(pValue, this->_memory, sizeof(TChar) * (this->_length + 1));
      pValue[this->_length] = 0;
      return pValue;
   }
};

//============================================================
// <T>网络消息对象类型</T>
//
// @history 100318 MAOCY 创建
//============================================================
template <typename T, TInt S>
class TFsNetObjects : public TStackObjects<T, S>{
public:
   //------------------------------------------------------------
   // <T>获得当前容积。</T>
   TSize Capacity(){
      TInt offset = sizeof(TUint16);
      T* pItems = (T*)this->_items;
      for(TInt n = 0; n < this->_count; n++){
         offset += pItems[n].Capacity();
      }
      return offset;
   }
   //------------------------------------------------------------
   // <T>序列化内部数据到数据区。</T>
   TInt Serialize(TAny* pMemory){
      TByte* pPtr = (TByte*)pMemory;
      // 写入长度
      *(TUint16*)pMemory = (TUint16)this->_count;
      TInt offset = sizeof(TUint16);
      // 写入所有对象
      T* pItems = (T*)this->_items;
      for(TInt n = 0; n < this->_count; n++){
         offset += pItems[n].Serialize(pPtr + offset);
      }
      return offset;
   }
   //------------------------------------------------------------
   // <T>反序列化数据区到内部数据。</T>
   TInt Unserialize(TAnyC* pMemory){
      TByteC* pPtr = (TByteC*)pMemory;
      // 读取长度
      TInt count = *(TUint16*)pPtr;
      if(count > S){
         MO_FATAL("Unserialize net objects not enough memory. (size=%d, count=%d)", S, count);
         return -1;
      }
      this->_count = count;
      // 读取数据
      TInt offset = sizeof(TUint16);
      T* pItems = (T*)this->_items;
      // 清空内存
      MO_LIB_MEMORY_CLEAR(pItems, sizeof(T) * count);
      for(TInt n = 0; n < count; n++){
         TInt result = pItems[n].Unserialize(pPtr + offset);
         if(-1 == result){
            return result;
         }
         offset += result;
      }
      return offset;
   }
   //------------------------------------------------------------
   // <T>打包数据到字符串。</T>
   TBool Pack(MPack* pPack){
      MO_ASSERT(pPack);
      // 写入长度
      TBool result = pPack->WriteCollectionBegin(this->_count);
      // 写入所有对象
      T* pItems = (T*)this->_items;
      if(result){
         for(TInt n = 0; n < this->_count; n++){
            pItems[n].Pack(pPack);
         }
         result = pPack->WriteCollectionEnd();
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>解包字符串到内部数据。</T>
   TBool Unpack(MPack* pPack){
      MO_ASSERT(pPack);
      TInt count = 0;
      TBool result = pPack->ReadCollectionBegin(&count);
      if(result){
         // 检查长度
         if(count > S){
            MO_FATAL("Unserialize net objects not enough memory. (size=%d, count=%d)", S, count);
            return EFalse;
         }
         this->_count = count;
         // 清空内存
         T* pItems = (T*)this->_items;
         MO_LIB_MEMORY_CLEAR(pItems, sizeof(T) * count);
         // 读取所有对象
         for(TInt n = 0; n < count; n++){
            TBool result = pItems[n].Unpack(pPack);
            if(!result){
               return result;
            }
         }
         result = pPack->ReadCollectionEnd();
      }
      return result;
   }
public:
   //------------------------------------------------------------
   // <T>跟踪内部数据。</T>
   TCharC* Track(TChar* pTrack, TSize length){
      T* pItems = (T*)this->_items;
      TStringRefer track(pTrack, length);
      track.AppendFormat("[count=%d]", this->_count);
      for(TInt n=0; n<this->_count; n++){
         TChar buffer[MO_FS_TRACK_LENGTH];
         pItems[n].Track(buffer, MO_FS_TRACK_LENGTH);
         track.AppendFormat("\n   - %s", buffer);
      }
      return pTrack;
   }
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_COLLECTION_H__
