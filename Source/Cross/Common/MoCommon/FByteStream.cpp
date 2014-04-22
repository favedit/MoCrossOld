#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FByteStream, FInstance);

//============================================================
// <T>构造字节数据流。</T>
//============================================================
FByteStream::FByteStream(){
   _position = 0;
}

//============================================================
// <T>析构字节数据流。</T>
//============================================================
FByteStream::~FByteStream(){
}

//============================================================
// <T>获得位置。</T>
//
// @return 位置
//============================================================
TInt FByteStream::Position(){
   return _position;
}

//============================================================
// <T>设置位置。</T>
//
// @param position 位置
//============================================================
void FByteStream::SetPosition(TInt position){
   MO_ASSERT(position <= _length);
   _position = position;
}

//============================================================
// <T>获得位置内存。</T>
//
// @return 位置内存
//============================================================
TByte* FByteStream::PositionMemory(){
   return _pMemory + _position;
}

//============================================================
// <T>跳过指定长度的内存。</T>
//
// @param length 长度
//============================================================
void FByteStream::Skip(TInt length){
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
TInt FByteStream::Read(TAny* pData, TInt length){
   MO_ASSERT(_position + length <= _length);
   TByte* pMemory = (TByte*)(_pMemory + _position);
   MO_LIB_MEMORY_COPY(pData, length, pMemory, length);
   _position += length;
   return length;
}

//============================================================
// <T>从字节流中读取一个布尔值。</T>
// <P>占用一个字节，为1表示真，为0表示假。</P>
//
// @return 布尔值
//============================================================
TBool FByteStream::ReadBool(){
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
TInt FByteStream::ReadInt(){
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
TInt8 FByteStream::ReadInt8(){
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
TInt16 FByteStream::ReadInt16(){
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
TInt32 FByteStream::ReadInt32(){
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
TInt64 FByteStream::ReadInt64(){
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
TUint FByteStream::ReadUint(){
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
TUint8 FByteStream::ReadUint8(){
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
TUint16 FByteStream::ReadUint16(){
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
TUint32 FByteStream::ReadUint32(){
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
TUint64 FByteStream::ReadUint64(){
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
TFloat FByteStream::ReadFloat(){
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
TDouble FByteStream::ReadDouble(){
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
TInt FByteStream::ReadString8(TChar8* pValue, TInt capacity){
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
TInt FByteStream::ReadString16(TChar16* pValue, TInt capacity){
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
TInt FByteStream::ReadString32(TChar32* pValue, TInt capacity){
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
TInt FByteStream::ReadString(TChar* pValue, TInt capacity){
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
TString8 FByteStream::ReadString8(){
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
TString16 FByteStream::ReadString16(){
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
TString32 FByteStream::ReadString32(){
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
TString FByteStream::ReadString(){
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
// <T>向字节流中写入一个有符号整数。</T>
//
// @param pData 字节数据
// @param length 数据长度
// @return 写入长度
//============================================================
TInt FByteStream::Write(TAnyC* pData, TInt length){
   EnsureSize(_position + length);
   TByte* pMemory = (TByte*)(_pMemory + _position);
   MO_LIB_MEMORY_COPY(pMemory, _capacity, pData, length);
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
void FByteStream::WriteBool(TBool value){
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
void FByteStream::WriteInt(TInt value){
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
void FByteStream::WriteInt8(TInt8 value){
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
void FByteStream::WriteInt16(TInt16 value){
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
void FByteStream::WriteInt32(TInt32 value){
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
void FByteStream::WriteInt64(TInt64 value){
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
void FByteStream::WriteUint(TUint value){
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
void FByteStream::WriteUint8(TUint8 value){
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
void FByteStream::WriteUint16(TUint16 value){
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
void FByteStream::WriteUint32(TUint32 value){
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
void FByteStream::WriteUint64(TUint64 value){
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
void FByteStream::WriteFloat(TFloat value){
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
void FByteStream::WriteDouble(TDouble value){
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
void FByteStream::WriteString8(TChar8C* pValue, TInt length){
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
void FByteStream::WriteString16(TChar16C* pValue, TInt length){
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
void FByteStream::WriteString32(TChar32C* pValue, TInt length){
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
void FByteStream::WriteString(TCharC* pValue, TInt length){
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
void FByteStream::Reset(){
   _position = 0;
}

MO_NAMESPACE_END
