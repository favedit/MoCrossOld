#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字符数据流。</T>
//============================================================
FByteString::FByteString(){
   _position = 0;
}

//============================================================
// <T>析构字符数据流。</T>
//============================================================
FByteString::~FByteString(){
}

//============================================================
// <T>获得位置。</T>
//
// @return 位置
//============================================================
TInt FByteString::Position(){
   return _position;
}

//============================================================
// <T>设置位置。</T>
//
// @param position 位置
//============================================================
void FByteString::SetPosition(TInt position){
   MO_ASSERT(position < _length);
   _position = position;
}

//============================================================
// <T>获得位置内存。</T>
//
// @return 位置内存
//============================================================
TByte* FByteString::PositionMemory(){
   return _pMemory + _position;
}

//============================================================
// <T>跳过指定长度的内存。</T>
//
// @param length 长度
//============================================================
void FByteString::Skip(TInt length){
   MO_ASSERT(_position + length <= _length);
   _position += length;
}

//============================================================
// <T>从字节流中读取一个指定长度的数据。</T>
//
// @param pData 字节数据
// @param length 数据长度
// @return 读取长度
//============================================================
TInt FByteString::Read(TChar* pData, TInt capacity){
   MO_ASSERT(_position + capacity <= _length);
   TChar* pMemory = (TChar*)(_pMemory + _position);
   MO_LIB_MEMORY_COPY(pData, sizeof(TChar) * capacity, pMemory, sizeof(TChar) * capacity);
   _position += capacity;
   return capacity;
}

//============================================================
// <T>从字节流中读取一个布尔值。</T>
// <P>占用一个字节，为1表示真，为0表示假。</P>
//
// @return 布尔值
//============================================================
TBool FByteString::ReadBool(){
   MO_ASSERT(_position + (TInt)sizeof(TByte) <= _length);
   TByte value = *(TByte*)(_pMemory + _position);
   _position += sizeof(TByte);
   return (value > 0);
}

//============================================================
// <T>从字节流中读取一个有符号整数。</T>
//
// @return 有符号整数
//============================================================
TInt FByteString::ReadInt(){
   MO_ASSERT(_position + (TInt)sizeof(TInt) <= _length);
   TInt value = *(TInt*)(_pMemory + _position);
   _position += sizeof(TInt);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号8位整数。</T>
//
// @return 有符号8位整数
//============================================================
TInt8 FByteString::ReadInt8(){
   MO_ASSERT(_position + (TInt)sizeof(TInt8) <= _length);
   TInt8 value = *(TInt8*)(_pMemory + _position);
   _position += sizeof(TInt8);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号16位整数。</T>
//
// @return 有符号16位整数
//============================================================
TInt16 FByteString::ReadInt16(){
   MO_ASSERT(_position + (TInt)sizeof(TInt16) <= _length);
   TInt16 value = *(TInt16*)(_pMemory + _position);
   _position += sizeof(TInt16);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号32位整数。</T>
//
// @return 有符号32位整数
//============================================================
TInt32 FByteString::ReadInt32(){
   MO_ASSERT(_position + (TInt)sizeof(TInt32) <= _length);
   TInt32 value = *(TInt32*)(_pMemory + _position);
   _position += sizeof(TInt32);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号64位整数。</T>
//
// @return 有符号64位整数
//============================================================
TInt64 FByteString::ReadInt64(){
   MO_ASSERT(_position + (TInt)sizeof(TInt64) <= _length);
   TInt64 value = *(TInt64*)(_pMemory + _position);
   _position += sizeof(TInt64);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号整数。</T>
//
// @return 无符号整数
//============================================================
TUint FByteString::ReadUint(){
   MO_ASSERT(_position + (TInt)sizeof(TUint) <= _length);
   TUint value = *(TUint*)(_pMemory + _position);
   _position += sizeof(TUint);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号8位整数。</T>
//
// @return 无符号8位整数
//============================================================
TUint8 FByteString::ReadUint8(){
   MO_ASSERT(_position + (TInt)sizeof(TUint8) <= _length);
   TUint8 value = *(TUint8*)(_pMemory + _position);
   _position += sizeof(TUint8);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号16位整数。</T>
//
// @return 无符号16位整数
//============================================================
TUint16 FByteString::ReadUint16(){
   MO_ASSERT(_position + (TInt)sizeof(TUint16) <= _length);
   TUint16 value = *(TUint16*)(_pMemory + _position);
   _position += sizeof(TUint16);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号32位整数。</T>
//
// @return 无符号32位整数
//============================================================
TUint32 FByteString::ReadUint32(){
   MO_ASSERT(_position + (TInt)sizeof(TUint32) <= _length);
   TUint32 value = *(TUint32*)(_pMemory + _position);
   _position += sizeof(TUint32);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号64位整数。</T>
//
// @param p:value 无符号64位整数
// @return 无符号64位整数
//============================================================
TUint64 FByteString::ReadUint64(){
   MO_ASSERT(_position + (TInt)sizeof(TUint64) <= _length);
   TUint64 value = *(TUint64*)(_pMemory + _position);
   _position += sizeof(TUint64);
   return value;
}

//============================================================
// <T>从字节流中读取一个32位浮点数。</T>
//
// @return 32位浮点数
//============================================================
TFloat FByteString::ReadFloat(){
   MO_ASSERT(_position + (TInt)sizeof(TFloat) <= _length);
   TFloat value = *(TFloat*)(_pMemory + _position);
   _position += sizeof(TFloat);
   return value;
}

//============================================================
// <T>从字节流中读取一个64位浮点数。</T>
//
// @return 64位浮点数
//============================================================
TDouble FByteString::ReadDouble(){
   MO_ASSERT(_position + (TInt)sizeof(TDouble) <= _length);
   TDouble value = *(TDouble*)(_pMemory + _position);
   _position += sizeof(TDouble);
   return value;
}

//============================================================
// <T>从字节流中读取一个8位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt FByteString::ReadString8(TChar8* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + length <= _length);
   if(length > 0){
      MO_LIB_MEMORY_COPY(pValue, capacity, _pMemory + _position + sizeof(TUint16), length);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + length;
   return length;
}

//============================================================
// <T>从字节流中读取一个16位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt FByteString::ReadString16(TChar16* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   TInt byteLength = sizeof(TChar16) * length;
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= _length);
   if(length > 0){
      MO_LIB_MEMORY_COPY(pValue, capacity, _pMemory + _position + sizeof(TUint16), byteLength);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + byteLength;
   return length;
}

//============================================================
// <T>从字节流中读取一个32位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt FByteString::ReadString32(TChar32* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   TInt byteLength = sizeof(TChar32) * length;
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= _length);
   if(length > 0){
      MO_LIB_MEMORY_COPY(pValue, capacity, _pMemory + _position + sizeof(TUint16), byteLength);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + byteLength;
   return length;
}

//============================================================
// <T>从字节流中读取一个字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt FByteString::ReadString(TChar* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   TInt byteLength = sizeof(TChar) * length;
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= _length);
   if(length > 0){
      MO_LIB_MEMORY_COPY(pValue, capacity, _pMemory + _position + sizeof(TUint16), byteLength);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + byteLength;
   return length;
}

//============================================================
// <T>从字节流中读取一个8位字符串。</T>
//
// @return 字符串
//============================================================
TString8 FByteString::ReadString8(){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + length <= _length);
   TString8 result;
   if(length > 0){
      result.ForceSize(length + 1);
      MO_LIB_MEMORY_COPY(result.Memory(), length, _pMemory + _position + sizeof(TUint16), length);
      result.SetLength(length);
   }
   _position += sizeof(TUint16) + length;
   return result;
}

//============================================================
// <T>从字节流中读取一个16位字符串。</T>
//
// @return 字符串
//============================================================
TString16 FByteString::ReadString16(){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   TInt byteLength = sizeof(TChar16) * length;
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= _length);
   TString16 result;
   if(length > 0){
      result.ForceSize(length + 1);
      MO_LIB_MEMORY_COPY(result.Memory(), byteLength, _pMemory + _position + sizeof(TUint16), byteLength);
      result.SetLength(length);
   }
   _position += sizeof(TUint16) + byteLength;
   return result;
}

//============================================================
// <T>从字节流中读取一个32位字符串。</T>
//
// @return 字符串
//============================================================
TString32 FByteString::ReadString32(){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   TInt byteLength = sizeof(TChar32) * length;
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= _length);
   TString32 result;
   if(length > 0){
      result.ForceSize(length + 1);
      MO_LIB_MEMORY_COPY(result.Memory(), byteLength, _pMemory + _position + sizeof(TUint16), byteLength);
      result.SetLength(length);
   }
   _position += sizeof(TUint16) + byteLength;
   return result;
}

//============================================================
// <T>从字节流中读取一个字符串。</T>
//
// @return 字符串
//============================================================
TString FByteString::ReadString(){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   TInt byteLength = sizeof(TChar) * length;
   MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= _length);
   TString result;
   if(length > 0){
      result.ForceSize(length + 1);
      MO_LIB_MEMORY_COPY(result.Memory(), byteLength, _pMemory + _position + sizeof(TUint16), byteLength);
      result.SetLength(length);
   }
   _position += sizeof(TUint16) + byteLength;
   return result;
}

//============================================================
TInt FByteString::ReadLine8(TChar8* pValue, TInt capacity){
   return 0;
}

//============================================================
TInt FByteString::ReadLine16(TChar16* pValue, TInt capacity){
   return 0;
}

//============================================================
TInt FByteString::ReadLine32(TChar32* pValue, TInt capacity){
   return 0;
}

//============================================================
TInt FByteString::ReadLine(TChar* pValue, TInt capacity){
   TCharC* pData = (TCharC*)(_pMemory + _position);
   TInt length = RChars::IndexOf(pData, capacity - 1, '\n');
   if(length > 0){
      MO_LIB_MEMORY_COPY(pValue, sizeof(TChar) * capacity, pData, sizeof(TChar) * length);
      if(pValue[length - 1] == '\r'){
         pValue[--length] = 0;
      }else{
         pValue[length] = 0;
      }
   }
   return length;
}

//============================================================
TInt FByteString::ReadLine(MString* pValue){
   MO_CHECK(pValue, return ENull);
   TChar* pData = pValue->Memory();
   TInt dataSize = pValue->Capacity();
   TCharC* pReader = (TCharC*)(_pMemory + _position);
   TInt length = RChars::IndexOf(pReader, dataSize, '\n');
   if(length > 0){
      // 修正位置
      _position += length + 1;
      // 读取数据
      MO_LIB_MEMORY_COPY(pData, sizeof(TChar) * dataSize, pReader, sizeof(TChar) * length);
      if(pData[length - 1] == '\r'){
         pValue->SetLength(--length);
      }else{
         pValue->SetLength(length);
      }
   }
   return length;
}

//============================================================
// <T>向字节流中写入一个有符号整数。</T>
//
// @param pData 字节数据
// @param length 数据长度
// @return 写入长度
//============================================================
TInt FByteString::Write(TCharC* pData, TInt length){
   MO_CHECK(pData, return -1);
   if(length == -1){
      length = RChars::Length(pData);
   }
   EnsureSize(_position + length);
   TByte* pMemory = (TByte*)(_pMemory + _position);
   MO_LIB_MEMORY_COPY(pMemory, sizeof(TChar) * _capacity, pData, sizeof(TChar) * length);
   _position += length;
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
   return length;
}

//============================================================
// <T>向字节流中写入字节数据。</T>
//
// @param pData 字节数据
// @param length 数据长度
// @return 写入长度
//============================================================
TInt FByteString::WriteBytes(TAny* pData, TInt length){
   EnsureSize(_position + length);
   TByte* pMemory = (TByte*)(_pMemory + _position);
   MO_LIB_MEMORY_COPY(pMemory, _capacity - _position, pData, length);
   _position += length;
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
   return length;
}

//============================================================
// <T>向字节流中写入一个布尔值。</T>
// <P>占用一个字节，为1表示真，为0表示假。</P>
//
// @param value 布尔值
//============================================================
void FByteString::WriteBool(TBool value){
   EnsureSize(_position + sizeof(TByte));
   *(TByte*)(_pMemory + _position) = value ? 1 : 0;
   _position += sizeof(TByte);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个有符号整数。</T>
//
// @param value 有符号整数
//============================================================
void FByteString::WriteInt(TInt value){
   EnsureSize(_position + sizeof(TInt));
   *(TInt*)(_pMemory + _position) = value;
   _position += sizeof(TInt);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个有符号8位整数。</T>
//
// @param value 有符号8位整数
//============================================================
void FByteString::WriteInt8(TInt8 value){
   EnsureSize(_position + sizeof(TInt8));
   *(TInt8*)(_pMemory + _position) = value;
   _position += sizeof(TInt8);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>在字节流中写入一个有符号16位整数。</T>
//
// @param value 有符号16位整数
//============================================================
void FByteString::WriteInt16(TInt16 value){
   EnsureSize(_position + sizeof(TInt16));
   *(TInt16*)(_pMemory + _position) = value;
   _position += sizeof(TInt16);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>在字节流中写入一个有符号32位整数。</T>
//
// @param value 有符号32位整数
//============================================================
void FByteString::WriteInt32(TInt32 value){
   EnsureSize(_position + sizeof(TInt32));
   *(TInt32*)(_pMemory + _position) = value;
   _position += sizeof(TInt32);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>在字节流中写入一个有符号64位整数。</T>
//
// @param value 有符号64位整数
//============================================================
void FByteString::WriteInt64(TInt64 value){
   EnsureSize(_position + sizeof(TInt64));
   *(TInt64*)(_pMemory + _position) = value;
   _position += sizeof(TInt64);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个无符号整数。</T>
//
// @param value 无符号整数
//============================================================
void FByteString::WriteUint(TUint value){
   EnsureSize(_position + sizeof(TUint));
   *(TUint*)(_pMemory + _position) = value;
   _position += sizeof(TUint);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个无符号8位整数。</T>
//
// @param value 无符号8位整数
//============================================================
void FByteString::WriteUint8(TUint8 value){
   EnsureSize(_position + sizeof(TUint8));
   *(TUint8*)(_pMemory + _position) = value;
   _position += sizeof(TUint8);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个无符号16位整数。</T>
//
// @param value 无符号16位整数
//============================================================
void FByteString::WriteUint16(TUint16 value){
   EnsureSize(_position + sizeof(TUint16));
   *(TUint16*)(_pMemory + _position) = value;
   _position += sizeof(TUint16);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个无符号32位整数。</T>
//
// @param value 无符号32位整数
//============================================================
void FByteString::WriteUint32(TUint32 value){
   EnsureSize(_position + sizeof(TUint32));
   *(TUint32*)(_pMemory + _position) = value;
   _position += sizeof(TUint32);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个无符号64位整数。</T>
//
// @param value 无符号64位整数
//============================================================
void FByteString::WriteUint64(TUint64 value){
   EnsureSize(_position + sizeof(TUint64));
   *(TUint64*)(_pMemory + _position) = value;
   _position += sizeof(TUint64);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个32位浮点数。</T>
//
// @param value 32位浮点数
//============================================================
void FByteString::WriteFloat(TFloat value){
   EnsureSize(_position + sizeof(TFloat));
   *(TFloat*)(_pMemory + _position) = value;
   _position += sizeof(TFloat);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个64位浮点数。</T>
//
// @param value 64位浮点数
//============================================================
void FByteString::WriteDouble(TDouble value){
   EnsureSize(_position + sizeof(TDouble));
   *(TDouble*)(_pMemory + _position) = value;
   _position += sizeof(TDouble);
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个8位字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void FByteString::WriteString8(TChar8C* pValue, TInt length){
   if(length < 0){
      length = RString8::Length(pValue);
   }
   EnsureSize(_position + sizeof(TUint16) + length);
   *(TUint16*)(_pMemory + _position) = length;
   _position += sizeof(TUint16);
   if(length > 0){
      MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, sizeof(TChar8) * length);
   }
   _position += sizeof(TChar8) * length;
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个16位字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void FByteString::WriteString16(TChar16C* pValue, TInt length){
   if(length < 0){
      length = RString16::Length(pValue);
   }
   EnsureSize(_position + sizeof(TUint16) + length);
   *(TUint16*)(_pMemory + _position) = length;
   _position += sizeof(TUint16);
   if(length > 0){
      MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, sizeof(TChar16) * length);
   }
   _position += sizeof(TChar16) * length;
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个32位字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void FByteString::WriteString32(TChar32C* pValue, TInt length){
   if(length < 0){
      length = RString32::Length(pValue);
   }
   EnsureSize(_position + sizeof(TUint16) + length);
   *(TUint16*)(_pMemory + _position) = length;
   _position += sizeof(TUint16);
   if(length > 0){
      MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, sizeof(TChar32) * length);
   }
   _position += sizeof(TChar32) * length;
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>向字节流中写入一个字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void FByteString::WriteString(TCharC* pValue, TInt length){
   if(length < 0){
      length = RString::Length(pValue);
   }
   EnsureSize(_position + sizeof(TUint16) + length);
   *(TUint16*)(_pMemory + _position) = length;
   _position += sizeof(TUint16);
   if(length > 0){
      MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, sizeof(TChar) * length);
   }
   _position += sizeof(TChar) * length;
   // 调整长度
   if(_position > _length){
      _length = _position;
   }
}

//============================================================
// <T>重置处理。</T>
//============================================================
void FByteString::Reset(){
   _position = 0;
}

MO_NAMESPACE_END
