#include "MoCmStream.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造数据输入流。</T>
//============================================================
TLinkInput::TLinkInput(TAny* pInputData, TInt inputLength){
   _pMemory = (TByte*)pInputData;
   _length = inputLength;
   _position = 0;
}

//============================================================
// <T>关联内存。</T>
//============================================================
void TLinkInput::Link(TAny* pInputData, TInt inputLength){
   _pMemory = (TByte*)pInputData;
   _length = inputLength;
   _position = 0;
}

//============================================================
// <T>获得当前位置。</T>
//
// @return 当前位置
//============================================================
TInt TLinkInput::Position(){
   return _position;
}

//============================================================
// <T>获得长度。</T>
//
// @return 长度
//============================================================
TInt TLinkInput::Length(){
   return _length;
}

//============================================================
// <T>获得内存指针。</T>
//
// @return 内存指针
//============================================================
TByteC* TLinkInput::MemoryC(){
   return _pMemory;
}

//============================================================
// <T>获得内存指针。</T>
//
// @return 内存指针
//============================================================
TByte* TLinkInput::Memory(){
   return _pMemory;
}

//============================================================
// <T>获得当前位置的内存指针。</T>
//
// @return 内存指针
//============================================================
TByte* TLinkInput::PositionMemory(){
   return (_pMemory + _position);
}

//============================================================
// <T>将流位置移动一段位置。</T>
//
// @param length 长度
//============================================================
void TLinkInput::Skip(TInt length){
   _position += length;
}

//============================================================
// <T>读取数据。</T>
//
// @param pData 数据
// @param length 长度
// @return 读取长度
//============================================================
TInt TLinkInput::Read(TAny* pData, TInt length){
   TByte* pMemory = (TByte*)(_pMemory + _position);
   MO_LIB_MEMORY_COPY(pData, length, pMemory, length);
   _position += length;
   return length;
}

//============================================================
// <T>测试字符串长度。</T>
//
// @return 长度
//============================================================
TInt TLinkInput::TestStringLength(){
   return *(TUint16*)(_pMemory + _position);
}

//============================================================
// <T>从字节流中读取一个布尔值。</T>
// <P>占用一个字节，为1表示真，为0表示假。</P>
//
// @return 布尔值
//============================================================
TBool TLinkInput::ReadBool(){
   TByte value = *(TByte*)(_pMemory + _position);
   _position += sizeof(TByte);
   return (value > 0);
}

//============================================================
// <T>从字节流中读取一个有符号整数。</T>
//
// @return 有符号整数
//============================================================
TInt TLinkInput::ReadInt(){
   TInt value = *(TInt*)(_pMemory + _position);
   _position += sizeof(TInt);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号8位整数。</T>
//
// @return 有符号8位整数
//============================================================
TInt8 TLinkInput::ReadInt8(){
   TInt8 value = *(TInt8*)(_pMemory + _position);
   _position += sizeof(TInt8);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号16位整数。</T>
//
// @return 有符号16位整数
//============================================================
TInt16 TLinkInput::ReadInt16(){
   TInt16 value = *(TInt16*)(_pMemory + _position);
   _position += sizeof(TInt16);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号32位整数。</T>
//
// @return 有符号32位整数
//============================================================
TInt32 TLinkInput::ReadInt32(){
   TInt32 value = *(TInt32*)(_pMemory + _position);
   _position += sizeof(TInt32);
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号64位整数。</T>
//
// @return 有符号64位整数
//============================================================
TInt64 TLinkInput::ReadInt64(){
   TInt64 value = *(TInt64*)(_pMemory + _position);
   _position += sizeof(TInt64);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号整数。</T>
//
// @return 无符号整数
//============================================================
TUint TLinkInput::ReadUint(){
   TUint value = *(TUint*)(_pMemory + _position);
   _position += sizeof(TUint);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号8位整数。</T>
//
// @return 无符号8位整数
//============================================================
TUint8 TLinkInput::ReadUint8(){
   TUint8 value = *(TUint8*)(_pMemory + _position);
   _position += sizeof(TUint8);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号16位整数。</T>
//
// @return 无符号16位整数
//============================================================
TUint16 TLinkInput::ReadUint16(){
   TUint16 value = *(TUint16*)(_pMemory + _position);
   _position += sizeof(TUint16);
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号32位整数。</T>
//
// @return 无符号32位整数
//============================================================
TUint32 TLinkInput::ReadUint32(){
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
TUint64 TLinkInput::ReadUint64(){
   TUint64 value = *(TUint64*)(_pMemory + _position);
   _position += sizeof(TUint64);
   return value;
}

//============================================================
// <T>从字节流中读取一个32位浮点数。</T>
//
// @return 32位浮点数
//============================================================
TFloat TLinkInput::ReadFloat(){
   TFloat value = *(TFloat*)(_pMemory + _position);
   _position += sizeof(TFloat);
   return value;
}

//============================================================
// <T>从字节流中读取一个64位浮点数。</T>
//
// @return 64位浮点数
//============================================================
TDouble TLinkInput::ReadDouble(){
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
TInt TLinkInput::ReadString8(TChar8* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   if(length > 0){
      memcpy(pValue, _pMemory + _position + sizeof(TUint16), sizeof(TChar8) * length);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + sizeof(TChar8) * length;
   return length;
}

//============================================================
// <T>从字节流中读取一个16位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt TLinkInput::ReadString16(TChar16* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   if(length > 0){
      memcpy(pValue, _pMemory + _position + sizeof(TUint16), sizeof(TChar16) * length);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + sizeof(TChar16) * length;
   return length;
}

//============================================================
// <T>从字节流中读取一个32位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt TLinkInput::ReadString32(TChar32* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   if(length > 0){
      memcpy(pValue, _pMemory + _position + sizeof(TUint16), sizeof(TChar32) * length);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + sizeof(TChar32) * length;
   return length;
}

//============================================================
// <T>从字节流中读取一个字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt TLinkInput::ReadString(TChar* pValue, TInt capacity){
   TUint16 length = *(TUint16*)(_pMemory + _position);
   if(length > 0){
      memcpy(pValue, _pMemory + _position + sizeof(TUint16), sizeof(TChar) * length);
   }
   pValue[length] = 0;
   _position += sizeof(TUint16) + sizeof(TChar) * length;
   return length;
}

//============================================================
// <T>从字节流中读取一个8位字符串。</T>
//
// @return 字符串
//============================================================
TString8 TLinkInput::ReadString8(){
   TString8 result;
   TUint16 length;
   Read(&length, sizeof(TUint16));
   if(length > 0){
      result.EnsureSize(length + 1);
      Read(result.Memory(), sizeof(TChar8) * length);
      result.SetLength(length);
   }
   return result;
}

//============================================================
// <T>从字节流中读取一个16位字符串。</T>
//
// @return 字符串
//============================================================
TString16 TLinkInput::ReadString16(){
   TString16 result;
   TUint16 length;
   Read(&length, sizeof(TUint16));
   if(length > 0){
      result.EnsureSize(length + 1);
      Read(result.Memory(), sizeof(TChar16) * length);
      result.SetLength(length);
   }
   return result;
}

//============================================================
// <T>从字节流中读取一个32位字符串。</T>
//
// @return 字符串
//============================================================
TString32 TLinkInput::ReadString32(){
   TString32 result;
   TUint16 length;
   Read(&length, sizeof(TUint16));
   if(length > 0){
      result.EnsureSize(length + 1);
      Read(result.Memory(), sizeof(TChar32) * length);
      result.SetLength(length);
   }
   return result;
}

//============================================================
// <T>从字节流中读取一个字符串。</T>
//
// @return 字符串
//============================================================
TString TLinkInput::ReadString(){
   TString result;
   TUint16 length;
   Read(&length, sizeof(TUint16));
   if(length > 0){
      result.EnsureSize(length + 1);
      Read(result.Memory(), sizeof(TChar) * length);
      result.SetLength(length);
   }
   return result;
}

//============================================================
// <T>重置处理。</T>
//============================================================
void TLinkInput::Reset(){
   _position = 0;
}

MO_NAMESPACE_END
