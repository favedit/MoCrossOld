#include "MoCmStream.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造输入数据流的实例。</T>
//
// @return 实例指针
//============================================================
TDataInput::TDataInput(){
   _pInput = NULL;
}

//============================================================
// <T>构造输入数据流的实例。</T>
//
// @param pInput 输入接口
//============================================================
TDataInput::TDataInput(IInput* pInput){
   _pInput = pInput;
}

//============================================================
// <T>析构输入数据流的实例。</T>
//============================================================
TDataInput::~TDataInput(){
}

//============================================================
// <T>获得输入接口。</T>
//
// @return 输入接口
//============================================================
IInput* TDataInput::Input(){
   return _pInput;
}

//============================================================
// <T>设置输入接口。</T>
//
// @param pInput 输入接口
//============================================================
void TDataInput::SetInput(IInput* pInput){
   _pInput = pInput;
}

//============================================================
// <T>读取数据</T>
//
// @return 返回读取长度
//============================================================
TInt TDataInput::Read(TAny* pData, TInt size){
   return _pInput->Read(pData, size);
}

//============================================================
// <T>从字节流中读取一个有符号整数。</T>
//
// @return 有符号整数
//============================================================
TInt TDataInput::ReadInt(){
   TInt value;
   _pInput->Read(&value, sizeof(TInt));
   return value;
}

//============================================================
// <T>从字节流中读取一个布尔值。</T>
// <P>占用一个字节，为1表示真，为0表示假。</P>
//
// @return 布尔值
//============================================================
TBool TDataInput::ReadBool(){
   TByte value = 0;
   _pInput->Read(&value, sizeof(TByte));
   return (value > 0) ? ETrue : EFalse;
}

//============================================================
// <T>从字节流中读取一个有符号8位整数。</T>
//
// @return 有符号8位整数
//============================================================
TInt8 TDataInput::ReadInt8(){
   TInt8 value;
   _pInput->Read(&value, sizeof(TInt8));
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号16位整数。</T>
//
// @return 有符号16位整数
//============================================================
TInt16 TDataInput::ReadInt16(){
   TInt16 value;
   _pInput->Read(&value, sizeof(TInt16));
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号32位整数。</T>
//
// @return 有符号32位整数
//============================================================
TInt32 TDataInput::ReadInt32(){
   TInt32 value;
   _pInput->Read(&value, sizeof(TInt32));
   return value;
}

//============================================================
// <T>从字节流中读取一个有符号64位整数。</T>
//
// @return 有符号64位整数
//============================================================
TInt64 TDataInput::ReadInt64(){
   TInt64 value;
   _pInput->Read(&value, sizeof(TInt64));
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号整数。</T>
//
// @return 无符号整数
//============================================================
TUint TDataInput::ReadUint(){
   TUint value;
   _pInput->Read(&value, sizeof(TUint));
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号8位整数。</T>
//
// @return 无符号8位整数
//============================================================
TUint8 TDataInput::ReadUint8(){
   TUint8 value;
   _pInput->Read(&value, sizeof(TUint8));
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号16位整数。</T>
//
// @return 无符号16位整数
//============================================================
TUint16 TDataInput::ReadUint16(){
   TUint16 value;
   _pInput->Read(&value, sizeof(TUint16));
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号32位整数。</T>
//
// @return 无符号32位整数
//============================================================
TUint32 TDataInput::ReadUint32(){
   TUint32 value;
   _pInput->Read(&value, sizeof(TUint32));
   return value;
}

//============================================================
// <T>从字节流中读取一个无符号64位整数。</T>
//
// @param p:value 无符号64位整数
// @return 无符号64位整数
//============================================================
TUint64 TDataInput::ReadUint64(){
   TUint64 value;
   _pInput->Read(&value, sizeof(TUint64));
   return value;
}

//============================================================
// <T>从字节流中读取一个32位浮点数。</T>
//
// @return 32位浮点数
//============================================================
TFloat TDataInput::ReadFloat(){
   TFloat value;
   _pInput->Read(&value, sizeof(TFloat));
   return value;
}

//============================================================
// <T>从字节流中读取一个64位浮点数。</T>
//
// @return 64位浮点数
//============================================================
TDouble TDataInput::ReadDouble(){
   TDouble value;
   _pInput->Read(&value, sizeof(TDouble));
   return value;
}

//============================================================
// <T>从字节流中读取一个8位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt TDataInput::ReadString8(TChar8* pValue, TInt capacity){
   TUint16 length;
   _pInput->Read(&length, sizeof(TUint16));
   if(length > 0){
      MO_ASSERT(length <= capacity);
      _pInput->Read(pValue, sizeof(TChar8) * length);
   }
   pValue[length] = 0;
   return length;
}

//============================================================
// <T>从字节流中读取一个16位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt TDataInput::ReadString16(TChar16* pValue, TInt capacity){
   TUint16 length;
   _pInput->Read(&length, sizeof(TUint16));
   if(length > 0){
      MO_ASSERT(length < capacity);
      _pInput->Read(pValue, sizeof(TChar16) * length);
   }
   pValue[length] = 0;
   return length;
}

//============================================================
// <T>从字节流中读取一个32位字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt TDataInput::ReadString32(TChar32* pValue, TInt capacity){
   TUint16 length;
   _pInput->Read(&length, sizeof(TUint16));
   if(length > 0){
      MO_ASSERT(length < capacity);
      _pInput->Read(pValue, sizeof(TChar32) * length);
   }
   pValue[length] = 0;
   return length;
}

//============================================================
// <T>从字节流中读取一个字符串。</T>
//
// @param pValue 字符串
// @param capacity 容量
// @return 读取长度
//============================================================
TInt TDataInput::ReadString(TChar* pValue, TInt capacity){
   TUint16 length;
   _pInput->Read(&length, sizeof(TUint16));
   if(length > 0){
      MO_ASSERT(length < capacity);
      _pInput->Read(pValue, sizeof(TChar) * length);
   }
   pValue[length] = 0;
   return length;
}

//============================================================
// <T>从字节流中读取一个8位字符串。</T>
//
// @return 字符串
//============================================================
TString8 TDataInput::ReadString8(){
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
TString16 TDataInput::ReadString16(){
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
TString32 TDataInput::ReadString32(){
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
TString TDataInput::ReadString(){
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

MO_NAMESPACE_END
