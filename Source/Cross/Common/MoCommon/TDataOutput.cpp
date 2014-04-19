#include "MoCmStream.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造输出数据流的实例。</T>
//
// @return 实例指针
//============================================================
TDataOutput::TDataOutput(){
   _pOutput = NULL;
}

//============================================================
// <T>构造输出数据流的实例。</T>
//
// @param pOutput 输出接口
//============================================================
TDataOutput::TDataOutput(IOutput* pOutput){
   _pOutput = pOutput;
}

//============================================================
// <T>析构输出数据流的实例。</T>
//============================================================
TDataOutput::~TDataOutput(){
}

//============================================================
// <T>获得输出接口。</T>
//
// @return 输出接口
//============================================================
IOutput* TDataOutput::Output(){
   return _pOutput;
}

//============================================================
// <T>设置输出接口。</T>
//
// @param pOutput 输出接口
//============================================================
void TDataOutput::SetOutput(IOutput* pOutput){
   _pOutput = pOutput;
}

//============================================================
// <T>设置输出接口。</T>
//
// @param pOutput 输出接口
//============================================================
TByte* TDataOutput::PositionMemory(){
   MO_FATAL_UNSUPPORT();
   return NULL;
}

//============================================================
// <T>设置输出接口。</T>
//
// @param pOutput 输出接口
//============================================================
void TDataOutput::Skip(TInt length){
   MO_FATAL_UNSUPPORT();
}
   
//============================================================
// <T>向字节流中写入一个有符号整数。</T>
//
// @param pData 字节数据
// @param length 数据长度
// @return 写入长度
//============================================================
TInt TDataOutput::Write(TAnyC* pData, TInt length){
   return _pOutput->Write(pData, length);
}

//============================================================
// <T>向字节流中写入一个布尔值。</T>
// <P>占用一个字节，为1表示真，为0表示假。</P>
//
// @param value 布尔值
//============================================================
void TDataOutput::WriteBool(TBool value){
   _pOutput->Write(&value, sizeof(TBool));
}

//============================================================
// <T>向字节流中写入一个有符号整数。</T>
//
// @param value 有符号整数
//============================================================
void TDataOutput::WriteInt(TInt value){
   _pOutput->Write(&value, sizeof(TInt));
}

//============================================================
// <T>向字节流中写入一个有符号8位整数。</T>
//
// @param value 有符号8位整数
//============================================================
void TDataOutput::WriteInt8(TInt8 value){
   _pOutput->Write(&value, sizeof(TInt8));
}

//============================================================
// <T>在字节流中写入一个有符号16位整数。</T>
//
// @param value 有符号16位整数
//============================================================
void TDataOutput::WriteInt16(TInt16 value){
   _pOutput->Write(&value, sizeof(TInt16));
}

//============================================================
// <T>在字节流中写入一个有符号32位整数。</T>
//
// @param value 有符号32位整数
//============================================================
void TDataOutput::WriteInt32(TInt32 value){
   _pOutput->Write(&value, sizeof(TInt32));
}

//============================================================
// <T>在字节流中写入一个有符号64位整数。</T>
//
// @param value 有符号64位整数
//============================================================
void TDataOutput::WriteInt64(TInt64 value){
   _pOutput->Write(&value, sizeof(TInt64));
}

//============================================================
// <T>向字节流中写入一个无符号整数。</T>
//
// @param value 无符号整数
//============================================================
void TDataOutput::WriteUint(TUint value){
   _pOutput->Write(&value, sizeof(TUint));
}

//============================================================
// <T>向字节流中写入一个无符号8位整数。</T>
//
// @param value 无符号8位整数
//============================================================
void TDataOutput::WriteUint8(TUint8 value){
   _pOutput->Write(&value, sizeof(TUint8));
}

//============================================================
// <T>向字节流中写入一个无符号16位整数。</T>
//
// @param value 无符号16位整数
//============================================================
void TDataOutput::WriteUint16(TUint16 value){
   _pOutput->Write(&value, sizeof(TUint16));
}

//============================================================
// <T>向字节流中写入一个无符号32位整数。</T>
//
// @param value 无符号32位整数
//============================================================
void TDataOutput::WriteUint32(TUint32 value){
   _pOutput->Write(&value, sizeof(TUint32));
}

//============================================================
// <T>向字节流中写入一个无符号64位整数。</T>
//
// @param value 无符号64位整数
//============================================================
void TDataOutput::WriteUint64(TUint64 value){
   _pOutput->Write(&value, sizeof(TUint64));
}

//============================================================
// <T>向字节流中写入一个32位浮点数。</T>
//
// @param value 32位浮点数
//============================================================
void TDataOutput::WriteFloat(TFloat value){
   _pOutput->Write(&value , sizeof(TFloat));
}

//============================================================
// <T>向字节流中写入一个64位浮点数。</T>
//
// @param value 64位浮点数
//============================================================
void TDataOutput::WriteDouble(TDouble value){
   _pOutput->Write(&value, sizeof(TDouble));
}

//============================================================
// <T>向字节流中写入一个8位字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void TDataOutput::WriteString8(TChar8C* pValue, TInt length){
   if(length < 0){
      length = RString8::Length(pValue);
   }
   MO_ASSERT(length <= 0xFFFF);
   TUint16 size = (TUint16)length;
   _pOutput->Write(&size, sizeof(TUint16));
   if(length > 0){
      _pOutput->Write(pValue, sizeof(TChar8) * length);
   }
}

//============================================================
// <T>向字节流中写入一个16位字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void TDataOutput::WriteString16(TChar16C* pValue, TInt length){
   if(length < 0){
      length = RString16::Length(pValue);
   }
   MO_ASSERT(length <= 0xFFFF);
   TUint16 size = (TUint16)length;
   _pOutput->Write(&size, sizeof(TUint16));
   if(length > 0){
      _pOutput->Write(pValue, sizeof(TChar16) * length);
   }
}

//============================================================
// <T>向字节流中写入一个32位字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void TDataOutput::WriteString32(TChar32C* pValue, TInt length){
   if(length < 0){
      length = RString32::Length(pValue);
   }
   MO_ASSERT(length <= 0xFFFF);
   TUint16 size = (TUint16)length;
   _pOutput->Write(&size, sizeof(TUint16));
   if(length > 0){
      _pOutput->Write(pValue, sizeof(TChar32) * length);
   }
}

//============================================================
// <T>向字节流中写入一个字符串。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void TDataOutput::WriteString(TCharC* pValue, TInt length){
   if(length < 0){
      length = RString::Length(pValue);
   }
   MO_ASSERT(length <= 0xFFFF);
   TUint16 size = (TUint16)length;
   _pOutput->Write(&size, sizeof(TUint16));
   if(length > 0){
      _pOutput->Write(pValue, sizeof(TChar) * length);
   }
}

MO_NAMESPACE_END
