#include "MoFgStream.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造数据流。</T>
//============================================================
FDataStream::FDataStream(){
}

//============================================================
// <T>析构数据流。</T>
//============================================================
FDataStream::~FDataStream(){
}

//============================================================
// <T>在字节流中写入2个字节。</T>
//
// @param value1 字节1
// @param value2 字节2
//============================================================
void FDataStream::WriteByte2(TByte value1, TByte value2){
   TByte values[2];
   values[0] = value1;
   values[1] = value2;
   Write(values, sizeof(values));
}

//============================================================
// <T>在字节流中写入3个字节。</T>
//
// @param value1 字节1
// @param value2 字节2
// @param value3 字节3
//============================================================
void FDataStream::WriteByte3(TByte value1, TByte value2, TByte value3){
   TByte values[3];
   values[0] = value1;
   values[1] = value2;
   values[2] = value3;
   Write(values, sizeof(values));
}

//============================================================
// <T>在字节流中写入4个字节。</T>
//
// @param value1 字节1
// @param value2 字节2
// @param value3 字节3
// @param value4 字节4
//============================================================
void FDataStream::WriteByte4(TByte value1, TByte value2, TByte value3, TByte value4){
   TByte values[4];
   values[0] = value1;
   values[1] = value2;
   values[2] = value3;
   values[3] = value4;
   Write(values, sizeof(values));
}

//============================================================
// <T>在字节流中写入2个32位浮点数。</T>
//
// @param value1 浮点数1
// @param value2 浮点数2
//============================================================
void FDataStream::WriteFloat2(TFloat value1, TFloat value2){
   TFloat values[2];
   values[0] = value1;
   values[1] = value2;
   Write(values, sizeof(values));
}

//============================================================
// <T>在字节流中写入3个32位浮点数。</T>
//
// @param value1 浮点数1
// @param value2 浮点数2
// @param value3 浮点数3
//============================================================
void FDataStream::WriteFloat3(TFloat value1, TFloat value2, TFloat value3){
   TFloat values[3];
   values[0] = value1;
   values[1] = value2;
   values[2] = value3;
   Write(values, sizeof(values));
}

//============================================================
// <T>在字节流中写入4个32位浮点数。</T>
//
// @param value1 浮点数1
// @param value2 浮点数2
// @param value3 浮点数3
// @param value4 浮点数4
//============================================================
void FDataStream::WriteFloat4(TFloat value1, TFloat value2, TFloat value3, TFloat value4){
   TFloat values[4];
   values[0] = value1;
   values[1] = value2;
   values[2] = value3;
   values[3] = value4;
   Write(values, sizeof(values));
}

MO_NAMESPACE_END
