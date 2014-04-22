#ifndef __MO_CM_STREAM_H__
#define __MO_CM_STREAM_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_LOCK_H__
#include "MoCmLock.h"
#endif // __MO_CM_LOCK_H__

#ifndef __MO_CM_CLASS_H__
#include "MoCmClass.h"
#endif // __MO_CM_CLASS_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>检测流接口。</T>
//
// @history 130926 MAOCY 创建
//============================================================
class MO_CM_DECLARE IPeek{
public:
   MO_ABSTRACT ~IPeek(){}
public:
   MO_VIRTUAL TInt Peek(TAny* pData, TInt length) = 0;
};

//============================================================
// <T>检测数据流接口。</T>
//
// @history 130926 MAOCY 创建
//============================================================
class MO_CM_DECLARE IDataPeek : public IPeek{
public:
   MO_VIRTUAL TBool PeekBool() = 0;
   MO_VIRTUAL TInt PeekInt() = 0;
   MO_VIRTUAL TInt8 PeekInt8() = 0;
   MO_VIRTUAL TInt16 PeekInt16() = 0;
   MO_VIRTUAL TInt32 PeekInt32() = 0;
   MO_VIRTUAL TInt64 PeekInt64() = 0;
   MO_VIRTUAL TUint PeekUint() = 0;
   MO_VIRTUAL TUint8 PeekUint8() = 0;
   MO_VIRTUAL TUint16 PeekUint16() = 0;
   MO_VIRTUAL TUint32 PeekUint32() = 0;
   MO_VIRTUAL TUint64 PeekUint64() = 0;
   MO_VIRTUAL TFloat PeekFloat() = 0;
   MO_VIRTUAL TDouble PeekDouble() = 0;
   MO_VIRTUAL TInt PeekStringLength() = 0;
};

//============================================================
// <T>输入流接口。</T>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE IInput{
public:
   MO_ABSTRACT ~IInput(){}
public:
   MO_VIRTUAL TInt Read(TAny* pData, TInt length) = 0;
};

//============================================================
// <T>输入流接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IStreamInput : public IInput{
public:
   MO_ABSTRACT ~IStreamInput(){
   }
public:
   MO_VIRTUAL TBool Flip() = 0;
   MO_VIRTUAL TBool Close() = 0;
};

//============================================================
// <T>输入数据流接口。</T>
//
// @history 121121 MAOCY 创建
//============================================================
class MO_CM_DECLARE IDataInput : public IInput{
public:
   MO_VIRTUAL TByte* PositionMemory() = 0;
   MO_VIRTUAL void Skip(TInt length) = 0;
public:
   MO_VIRTUAL TBool ReadBool() = 0;
   MO_VIRTUAL TInt ReadInt() = 0;
   MO_VIRTUAL TInt8 ReadInt8() = 0;
   MO_VIRTUAL TInt16 ReadInt16() = 0;
   MO_VIRTUAL TInt32 ReadInt32() = 0;
   MO_VIRTUAL TInt64 ReadInt64() = 0;
   MO_VIRTUAL TUint ReadUint() = 0;
   MO_VIRTUAL TUint8 ReadUint8() = 0;
   MO_VIRTUAL TUint16 ReadUint16() = 0;
   MO_VIRTUAL TUint32 ReadUint32() = 0;
   MO_VIRTUAL TUint64 ReadUint64() = 0;
   MO_VIRTUAL TFloat ReadFloat() = 0;
   MO_VIRTUAL TDouble ReadDouble() = 0;
   MO_VIRTUAL TInt ReadString8(TChar8* pValue, TInt capacity) = 0;
   MO_VIRTUAL TInt ReadString16(TChar16* pValue, TInt capacity) = 0;
   MO_VIRTUAL TInt ReadString32(TChar32* pValue, TInt capacity) = 0;
   MO_VIRTUAL TInt ReadString(TChar* pValue, TInt capacity) = 0;
   MO_VIRTUAL TString8 ReadString8() = 0;
   MO_VIRTUAL TString16 ReadString16() = 0;
   MO_VIRTUAL TString32 ReadString32() = 0;
   MO_VIRTUAL TString ReadString() = 0;
};

//============================================================
// <T>输入数据流。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE TDataInput : public IDataInput{
protected:
   IInput* _pInput;
public:
   TDataInput();
   TDataInput(IInput* pInput);
   MO_ABSTRACT ~TDataInput();
public:
   IInput* Input();
   void SetInput(IInput* pInput);
public:
   MO_OVERRIDE TInt Read(TAny* pData, TInt size);
public:
   MO_OVERRIDE TBool ReadBool();
   MO_OVERRIDE TInt ReadInt();
   MO_OVERRIDE TInt8 ReadInt8();
   MO_OVERRIDE TInt16 ReadInt16();
   MO_OVERRIDE TInt32 ReadInt32();
   MO_OVERRIDE TInt64 ReadInt64();
   MO_OVERRIDE TUint ReadUint();
   MO_OVERRIDE TUint8 ReadUint8();
   MO_OVERRIDE TUint16 ReadUint16();
   MO_OVERRIDE TUint32 ReadUint32();
   MO_OVERRIDE TUint64 ReadUint64();
   MO_OVERRIDE TFloat ReadFloat();
   MO_OVERRIDE TDouble ReadDouble();
   MO_OVERRIDE TInt ReadString8(TChar8* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString16(TChar16* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString32(TChar32* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString(TChar* pValue, TInt capacity);
   MO_OVERRIDE TString8 ReadString8();
   MO_OVERRIDE TString16 ReadString16();
   MO_OVERRIDE TString32 ReadString32();
   MO_OVERRIDE TString ReadString();
};

//============================================================
// <T>数据输入流。</T>
//============================================================
class MO_CM_DECLARE TLinkInput : public IDataInput{
protected:
   TByte* _pMemory;
   TInt _length;
   TInt _position;
public:
   TLinkInput(TAny* pInputData = NULL, TInt inputLength = 0);
public:
   void Link(TAny* pInputData, TInt inputLength);
public:
   TInt Position();
   TInt Length();
   TByteC* MemoryC();
   TByte* Memory();
public:
   MO_OVERRIDE TByte* PositionMemory();
   MO_OVERRIDE void Skip(TInt length);
public:
   MO_OVERRIDE TInt Read(TAny* pData, TInt length);
public:
   MO_OVERRIDE TInt TestStringLength();
public:
   MO_OVERRIDE TBool ReadBool();
   MO_OVERRIDE TInt ReadInt();
   MO_OVERRIDE TInt8 ReadInt8();
   MO_OVERRIDE TInt16 ReadInt16();
   MO_OVERRIDE TInt32 ReadInt32();
   MO_OVERRIDE TInt64 ReadInt64();
   MO_OVERRIDE TUint ReadUint();
   MO_OVERRIDE TUint8 ReadUint8();
   MO_OVERRIDE TUint16 ReadUint16();
   MO_OVERRIDE TUint32 ReadUint32();
   MO_OVERRIDE TUint64 ReadUint64();
   MO_OVERRIDE TFloat ReadFloat();
   MO_OVERRIDE TDouble ReadDouble();
   MO_OVERRIDE TInt ReadString8(TChar8* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString16(TChar16* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString32(TChar32* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString(TChar* pValue, TInt capacity);
   MO_OVERRIDE TString8 ReadString8();
   MO_OVERRIDE TString16 ReadString16();
   MO_OVERRIDE TString32 ReadString32();
   MO_OVERRIDE TString ReadString();
public:
   void Reset();
};

//============================================================
// <T>输入流工具。</T>
//============================================================
class MO_CM_DECLARE RInput{
public:
   static TSize CalculateStringCapacity(const MString& value);
};

//============================================================
// <T>输出流接口。</T>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE IOutput{
public:
   MO_ABSTRACT ~IOutput(){}
public:
   MO_VIRTUAL TInt Write(TAnyC* pData, TInt length) = 0;
};

//============================================================
// <T>输出流接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IStreamOutput : public IOutput{
public:
   MO_ABSTRACT ~IStreamOutput(){
   }
public:
   MO_VIRTUAL TBool Flush() = 0;
   MO_VIRTUAL TBool Close() = 0;
};

//============================================================
// <T>输出数据流接口。</T>
//
// @history 121121 MAOCY 创建
//============================================================
class MO_CM_DECLARE IDataOutput : public IOutput{
public:
   MO_VIRTUAL TByte* PositionMemory() = 0;
   MO_VIRTUAL void Skip(TInt length) = 0;
public:
   MO_VIRTUAL void WriteBool(TBool value) = 0;
   MO_VIRTUAL void WriteInt(TInt value) = 0;
   MO_VIRTUAL void WriteInt8(TInt8 value) = 0;
   MO_VIRTUAL void WriteInt16(TInt16 value) = 0;
   MO_VIRTUAL void WriteInt32(TInt32 value) = 0;
   MO_VIRTUAL void WriteInt64(TInt64 value) = 0;
   MO_VIRTUAL void WriteUint(TUint value) = 0;
   MO_VIRTUAL void WriteUint8(TUint8 value) = 0;
   MO_VIRTUAL void WriteUint16(TUint16 value) = 0;
   MO_VIRTUAL void WriteUint32(TUint32 value) = 0;
   MO_VIRTUAL void WriteUint64(TUint64 value) = 0;
   MO_VIRTUAL void WriteFloat(TFloat value) = 0;
   MO_VIRTUAL void WriteDouble(TDouble value) = 0;
   MO_VIRTUAL void WriteString8(TChar8C* pValue, TInt length = -1) = 0;
   MO_VIRTUAL void WriteString16(TChar16C* pValue, TInt length = -1) = 0;
   MO_VIRTUAL void WriteString32(TChar32C* pValue, TInt length = -1) = 0;
   MO_VIRTUAL void WriteString(TCharC* pValue, TInt length = -1) = 0;
};

//============================================================
// <T>数据输出流。</T>
//============================================================
class MO_CM_DECLARE TLinkOutput : public IDataOutput{
protected:
   TByte* _pMemory;
   TInt _capacity;
   TInt _position;
   TInt _length;
public:
   TLinkOutput(TAny* pOutputData = NULL, TInt outputCapacity = 0);
public:
   void Link(TAny* pOutputData, TInt outputCapacity);
public:
   TInt Position();
   TInt Length();
   TInt Capacity();
   TByteC* MemoryC();
   TByte* Memory();
public:
   MO_OVERRIDE TByte* PositionMemory();
   MO_OVERRIDE void Skip(TInt length);
   TBool SetLength(TInt length);
public:
   MO_OVERRIDE TInt Write(TAnyC* pData, TInt length);
public:
   MO_OVERRIDE void WriteBool(TBool value);
   MO_OVERRIDE void WriteInt(TInt value);
   MO_OVERRIDE void WriteInt8(TInt8 value);
   MO_OVERRIDE void WriteInt16(TInt16 value);
   MO_OVERRIDE void WriteInt32(TInt32 value);
   MO_OVERRIDE void WriteInt64(TInt64 value);
   MO_OVERRIDE void WriteUint(TUint value);
   MO_OVERRIDE void WriteUint8(TUint8 value);
   MO_OVERRIDE void WriteUint16(TUint16 value);
   MO_OVERRIDE void WriteUint32(TUint32 value);
   MO_OVERRIDE void WriteUint64(TUint64 value);
   MO_OVERRIDE void WriteFloat(TFloat value);
   MO_OVERRIDE void WriteDouble(TDouble value);
   MO_OVERRIDE void WriteString8(TChar8C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString16(TChar16C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString32(TChar32C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString(TCharC* pValue, TInt length = -1);
public:
   void Reset();
};

//============================================================
// <T>输出数据流。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE TDataOutput : public IDataOutput{
protected:
   IOutput* _pOutput;
public:
   TDataOutput();
   TDataOutput(IOutput* pOutput);
   MO_ABSTRACT ~TDataOutput();
public:
   IOutput* Output();
   void SetOutput(IOutput* pOutput);
public:
   MO_OVERRIDE TByte* PositionMemory();
   MO_OVERRIDE void Skip(TInt length);
public:
   MO_OVERRIDE TInt Write(TAnyC* pData, TInt size);
public:
   MO_OVERRIDE void WriteBool(TBool value);
   MO_OVERRIDE void WriteInt(TInt value);
   MO_OVERRIDE void WriteInt8(TInt8 value);
   MO_OVERRIDE void WriteInt16(TInt16 value);
   MO_OVERRIDE void WriteInt32(TInt32 value);
   MO_OVERRIDE void WriteInt64(TInt64 value);
   MO_OVERRIDE void WriteUint(TUint value);
   MO_OVERRIDE void WriteUint8(TUint8 value);
   MO_OVERRIDE void WriteUint16(TUint16 value);
   MO_OVERRIDE void WriteUint32(TUint32 value);
   MO_OVERRIDE void WriteUint64(TUint64 value);
   MO_OVERRIDE void WriteFloat(TFloat value);
   MO_OVERRIDE void WriteDouble(TDouble value);
   MO_OVERRIDE void WriteString8(TChar8C* pMemory, TInt length = -1);
   MO_OVERRIDE void WriteString16(TChar16C* pMemory, TInt length = -1);
   MO_OVERRIDE void WriteString32(TChar32C* pMemory, TInt length = -1);
   MO_OVERRIDE void WriteString(TCharC* pMemory, TInt length = -1);
};

//============================================================
// <T>输出流工作类。</T>
//============================================================
class MO_CM_DECLARE ROutput{
public:
   static TSize CalculateStringCapacity(const MString& value);
};

//============================================================
// <T>字节数据流。</T>
//
// @history 100526 MAOCY 创建
//============================================================
class MO_CM_DECLARE FByteStream :
      public FInstance,
      public MArrayData<TByte>,
      public IDataInput,
      public IDataOutput
{
   MO_CLASS_DECLARE_INHERITS(FByteStream, FInstance);
protected:
   TInt _position;
public:
   FByteStream();
   MO_ABSTRACT ~FByteStream();
public:
   MO_OVERRIDE TInt Position();
   MO_OVERRIDE void SetPosition(TInt position);
   MO_OVERRIDE TByte* PositionMemory();
   MO_OVERRIDE void Skip(TInt length);
public:
   MO_OVERRIDE TInt Read(TAny* pData, TInt length);
   MO_OVERRIDE TBool ReadBool();
   MO_OVERRIDE TInt ReadInt();
   MO_OVERRIDE TInt8 ReadInt8();
   MO_OVERRIDE TInt16 ReadInt16();
   MO_OVERRIDE TInt32 ReadInt32();
   MO_OVERRIDE TInt64 ReadInt64();
   MO_OVERRIDE TUint ReadUint();
   MO_OVERRIDE TUint8 ReadUint8();
   MO_OVERRIDE TUint16 ReadUint16();
   MO_OVERRIDE TUint32 ReadUint32();
   MO_OVERRIDE TUint64 ReadUint64();
   MO_OVERRIDE TFloat ReadFloat();
   MO_OVERRIDE TDouble ReadDouble();
   MO_OVERRIDE TInt ReadString8(TChar8* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString16(TChar16* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString32(TChar32* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString(TChar* pValue, TInt capacity);
   MO_OVERRIDE TString8 ReadString8();
   MO_OVERRIDE TString16 ReadString16();
   MO_OVERRIDE TString32 ReadString32();
   MO_OVERRIDE TString ReadString();
public:
   MO_OVERRIDE TInt Write(TAnyC* pData, TInt length);
   MO_OVERRIDE void WriteBool(TBool value);
   MO_OVERRIDE void WriteInt(TInt value);
   MO_OVERRIDE void WriteInt8(TInt8 value);
   MO_OVERRIDE void WriteInt16(TInt16 value);
   MO_OVERRIDE void WriteInt32(TInt32 value);
   MO_OVERRIDE void WriteInt64(TInt64 value);
   MO_OVERRIDE void WriteUint(TUint value);
   MO_OVERRIDE void WriteUint8(TUint8 value);
   MO_OVERRIDE void WriteUint16(TUint16 value);
   MO_OVERRIDE void WriteUint32(TUint32 value);
   MO_OVERRIDE void WriteUint64(TUint64 value);
   MO_OVERRIDE void WriteFloat(TFloat value);
   MO_OVERRIDE void WriteDouble(TDouble value);
   MO_OVERRIDE void WriteString8(TChar8C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString16(TChar16C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString32(TChar32C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString(TCharC* pValue, TInt length = -1);
public:
   MO_OVERRIDE void Reset();
};
//------------------------------------------------------------
typedef MO_CM_DECLARE GPtr<FByteStream> GByteStreamPtr;

//============================================================
// <T>字节数据流。</T>
//
// @history 100526 MAOCY 创建
//============================================================
template <TInt S>
class TFixByteStream :
      public TFixBytes<S>,
      public IDataInput,
      public IDataOutput{
protected:
   TInt _position;
public:
   //------------------------------------------------------------
   // <T>构造字节数组文件。</T>
   TFixByteStream(){
      _position = 0;
   }
   //------------------------------------------------------------
   // <T>析构字节数组文件。</T>
   MO_ABSTRACT ~TFixByteStream(){
   }
public:
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_OVERRIDE TInt Position(){
      return _position;
   }
   //------------------------------------------------------------
   // <T>设置位置。</T>
   MO_OVERRIDE void SetPosition(TInt position){
      MO_ASSERT(position < this->_length);
      _position = position;
   }
   //------------------------------------------------------------
   // <T>获得位置内存。</T>
   MO_OVERRIDE TByte* PositionMemory(){
      return this->_pMemory + _position;
   }
   //------------------------------------------------------------
   // <T>跳过指定长度的内存。</T>
   MO_OVERRIDE void Skip(TInt length){
      MO_ASSERT(_position + length < this->_length);
      _position += length;
   }
public:
   //------------------------------------------------------------
   // <T>从字节流中读取一个指定长度的数据。</T>
   MO_OVERRIDE TInt Read(TAny* pData, TInt length){
      MO_ASSERT(_position + length <= this->_length);
      TByte* pMemory = (TByte*)(this->_pMemory + _position);
      MO_LIB_MEMORY_COPY(pData, length, pMemory, length);
      _position += length;
      return length;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个布尔值。</T>
   // <P>占用一个字节，为1表示真，为0表示假。</P>
   MO_OVERRIDE TBool ReadBool(){
      MO_ASSERT(_position + (TInt)sizeof(TByte) <= this->_length);
      TByte value = *(TByte*)(this->_pMemory + _position);
      _position += sizeof(TByte);
      return (value > 0);
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个有符号整数。</T>
   MO_OVERRIDE TInt ReadInt(){
      MO_ASSERT(_position + (TInt)sizeof(TInt) <= this->_length);
      TInt value = *(TInt*)(this->_pMemory + _position);
      _position += sizeof(TInt);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个有符号8位整数。</T>
   MO_OVERRIDE TInt8 ReadInt8(){
      MO_ASSERT(_position + (TInt)sizeof(TInt8) <= this->_length);
      TInt8 value = *(TInt8*)(this->_pMemory + _position);
      _position += sizeof(TInt8);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个有符号16位整数。</T>
   MO_OVERRIDE TInt16 ReadInt16(){
      MO_ASSERT(_position + (TInt)sizeof(TInt16) <= this->_length);
      TInt16 value = *(TInt16*)(this->_pMemory + _position);
      _position += sizeof(TInt16);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个有符号32位整数。</T>
   MO_OVERRIDE TInt32 ReadInt32(){
      MO_ASSERT(_position + (TInt)sizeof(TInt32) <= this->_length);
      TInt32 value = *(TInt32*)(this->_pMemory + _position);
      _position += sizeof(TInt32);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个有符号64位整数。</T>
   MO_OVERRIDE TInt64 ReadInt64(){
      MO_ASSERT(_position + (TInt)sizeof(TInt64) <= this->_length);
      TInt64 value = *(TInt64*)(this->_pMemory + _position);
      _position += sizeof(TInt64);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个无符号整数。</T>
   MO_OVERRIDE TUint ReadUint(){
      MO_ASSERT(_position + (TInt)sizeof(TUint) <= this->_length);
      TUint value = *(TUint*)(this->_pMemory + _position);
      _position += sizeof(TUint);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个无符号8位整数。</T>
   MO_OVERRIDE TUint8 ReadUint8(){
      MO_ASSERT(_position + (TInt)sizeof(TUint8) <= this->_length);
      TUint8 value = *(TUint8*)(this->_pMemory + _position);
      _position += sizeof(TUint8);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个无符号16位整数。</T>
   MO_OVERRIDE TUint16 ReadUint16(){
      MO_ASSERT(_position + (TInt)sizeof(TUint16) <= this->_length);
      TUint16 value = *(TUint16*)(this->_pMemory + _position);
      _position += sizeof(TUint16);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个无符号32位整数。</T>
   MO_OVERRIDE TUint32 ReadUint32(){
      MO_ASSERT(_position + (TInt)sizeof(TUint32) <= this->_length);
      TUint32 value = *(TUint32*)(this->_pMemory + _position);
      _position += sizeof(TUint32);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个无符号64位整数。</T>
   MO_OVERRIDE TUint64 ReadUint64(){
      MO_ASSERT(_position + (TInt)sizeof(TUint64) <= this->_length);
      TUint64 value = *(TUint64*)(this->_pMemory + _position);
      _position += sizeof(TUint64);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个32位浮点数。</T>
   MO_OVERRIDE TFloat ReadFloat(){
      MO_ASSERT(_position + (TInt)sizeof(TFloat) <= this->_length);
      TFloat value = *(TFloat*)(this->_pMemory + _position);
      _position += sizeof(TFloat);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个64位浮点数。</T>
   MO_OVERRIDE TDouble ReadDouble(){
      MO_ASSERT(_position + (TInt)sizeof(TDouble) <= this->_length);
      TDouble value = *(TDouble*)(this->_pMemory + _position);
      _position += sizeof(TDouble);
      return value;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个8位字符串。</T>
   MO_OVERRIDE TInt ReadString8(TChar8* pValue, TInt capacity){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + length <= this->_length);
      if(length > 0){
         MO_LIB_MEMORY_COPY(pValue, capacity, this->_pMemory + _position + sizeof(TUint16), length);
      }
      pValue[length] = 0;
      _position += sizeof(TUint16) + length;
      return length;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个16位字符串。</T>
   MO_OVERRIDE TInt ReadString16(TChar16* pValue, TInt capacity){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      TInt byteLength = sizeof(TChar16) * length;
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= this->_length);
      if(length > 0){
         MO_LIB_MEMORY_COPY(pValue, capacity, this->_pMemory + _position + sizeof(TUint16), byteLength);
      }
      pValue[length] = 0;
      _position += sizeof(TUint16) + byteLength;
      return length;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个32位字符串。</T>
   MO_OVERRIDE TInt ReadString32(TChar32* pValue, TInt capacity){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      TInt byteLength = sizeof(TChar32) * length;
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= this->_length);
      if(length > 0){
         MO_LIB_MEMORY_COPY(pValue, capacity, this->_pMemory + _position + sizeof(TUint16), byteLength);
      }
      pValue[length] = 0;
      _position += sizeof(TUint16) + byteLength;
      return length;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个字符串。</T>
   MO_OVERRIDE TInt ReadString(TChar* pValue, TInt capacity){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      TInt byteLength = sizeof(TChar) * length;
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= this->_length);
      if(length > 0){
         MO_LIB_MEMORY_COPY(pValue, capacity, this->_pMemory + _position + sizeof(TUint16), byteLength);
      }
      pValue[length] = 0;
      _position += sizeof(TUint16) + byteLength;
      return length;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个8位字符串。</T>
   MO_OVERRIDE TString8 ReadString8(){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + length <= this->_length);
      TString8 result;
      if(length > 0){
         result.ForceSize(length + 1);
         MO_LIB_MEMORY_COPY(result.Memory(), length, this->_pMemory + _position + sizeof(TUint16), length);
         result.SetLength(length);
      }
      _position += sizeof(TUint16) + length;
      return result;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个16位字符串。</T>
   MO_OVERRIDE TString16 ReadString16(){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      TInt byteLength = sizeof(TChar16) * length;
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= this->_length);
      TString16 result;
      if(length > 0){
         result.ForceSize(length + 1);
         MO_LIB_MEMORY_COPY(result.Memory(), byteLength, this->_pMemory + _position + sizeof(TUint16), byteLength);
         result.SetLength(length);
      }
      _position += sizeof(TUint16) + byteLength;
      return result;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个32位字符串。</T>
   MO_OVERRIDE TString32 ReadString32(){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      TInt byteLength = sizeof(TChar32) * length;
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= this->_length);
      TString32 result;
      if(length > 0){
         result.ForceSize(length + 1);
         MO_LIB_MEMORY_COPY(result.Memory(), byteLength, this->_pMemory + _position + sizeof(TUint16), byteLength);
         result.SetLength(length);
      }
      _position += sizeof(TUint16) + byteLength;
      return result;
   }
   //------------------------------------------------------------
   // <T>从字节流中读取一个字符串。</T>
   MO_OVERRIDE TString ReadString(){
      TUint16 length = *(TUint16*)(this->_pMemory + _position);
      TInt byteLength = sizeof(TChar) * length;
      MO_ASSERT(_position + (TInt)sizeof(TUint16) + byteLength <= this->_length);
      TString result;
      if(length > 0){
         result.ForceSize(length + 1);
         MO_LIB_MEMORY_COPY(result.Memory(), byteLength, this->_pMemory + _position + sizeof(TUint16), byteLength);
         result.SetLength(length);
      }
      _position += sizeof(TUint16) + byteLength;
      return result;
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个有符号整数。</T>
   MO_OVERRIDE TInt Write(TAnyC* pData, TInt length){
      this->EnsureSize(_position + length);
      TByte* pMemory = (TByte*)(this->_pMemory + _position);
      MO_LIB_MEMORY_COPY(pMemory, _capacity, pData, length);
      _position += length;
      return length;
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个布尔值。</T>
   MO_OVERRIDE void WriteBool(TBool value){
      this->EnsureSize(_position + sizeof(TByte));
      *(TByte*)(this->_pMemory + _position) = value ? 1 : 0;
      _position += sizeof(TByte);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个有符号整数。</T>
   MO_OVERRIDE void WriteInt(TInt value){
      this->EnsureSize(_position + sizeof(TInt));
      *(TInt*)(this->_pMemory + _position) = value;
      _position += sizeof(TInt);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个有符号8位整数。</T>
   MO_OVERRIDE void WriteInt8(TInt8 value){
      this->EnsureSize(_position + sizeof(TInt8));
      *(TInt8*)(this->_pMemory + _position) = value;
      _position += sizeof(TInt8);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>在字节流中写入一个有符号16位整数。</T>
   MO_OVERRIDE void WriteInt16(TInt16 value){
      this->EnsureSize(_position + sizeof(TInt16));
      *(TInt16*)(this->_pMemory + _position) = value;
      _position += sizeof(TInt16);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>在字节流中写入一个有符号32位整数。</T>
   MO_OVERRIDE void WriteInt32(TInt32 value){
      this->EnsureSize(_position + sizeof(TInt32));
      *(TInt32*)(this->_pMemory + _position) = value;
      _position += sizeof(TInt32);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>在字节流中写入一个有符号64位整数。</T>
   MO_OVERRIDE void WriteInt64(TInt64 value){
      this->EnsureSize(_position + sizeof(TInt64));
      *(TInt64*)(this->_pMemory + _position) = value;
      _position += sizeof(TInt64);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个无符号整数。</T>
   MO_OVERRIDE void WriteUint(TUint value){
      this->EnsureSize(_position + sizeof(TUint));
      *(TUint*)(this->_pMemory + _position) = value;
      _position += sizeof(TUint);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个无符号8位整数。</T>
   MO_OVERRIDE void WriteUint8(TUint8 value){
      this->EnsureSize(_position + sizeof(TUint8));
      *(TUint8*)(this->_pMemory + _position) = value;
      _position += sizeof(TUint8);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个无符号16位整数。</T>
   MO_OVERRIDE void WriteUint16(TUint16 value){
      this->EnsureSize(_position + sizeof(TUint16));
      *(TUint16*)(this->_pMemory + _position) = value;
      _position += sizeof(TUint16);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个无符号32位整数。</T>
   MO_OVERRIDE void WriteUint32(TUint32 value){
      this->EnsureSize(_position + sizeof(TUint32));
      *(TUint32*)(this->_pMemory + _position) = value;
      _position += sizeof(TUint32);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个无符号64位整数。</T>
   MO_OVERRIDE void WriteUint64(TUint64 value){
      this->EnsureSize(_position + sizeof(TUint64));
      *(TUint64*)(this->_pMemory + _position) = value;
      _position += sizeof(TUint64);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个32位浮点数。</T>
   MO_OVERRIDE void WriteFloat(TFloat value){
      this->EnsureSize(_position + sizeof(TFloat));
      *(TFloat*)(this->_pMemory + _position) = value;
      _position += sizeof(TFloat);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个64位浮点数。</T>
   MO_OVERRIDE void WriteDouble(TDouble value){
      this->EnsureSize(_position + sizeof(TDouble));
      *(TDouble*)(this->_pMemory + _position) = value;
      _position += sizeof(TDouble);
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个8位字符串。</T>
   MO_OVERRIDE void WriteString8(TChar8C* pValue, TInt length){
      if(length < 0){
         length = RString8::Length(pValue);
      }
      this->EnsureSize(_position + sizeof(TUint16) + length);
      *(TUint16*)(this->_pMemory + _position) = length;
      _position += sizeof(TUint16);
      if(length > 0){
         MO_LIB_MEMORY_COPY(this->_pMemory + _position, _capacity - _position, pValue, sizeof(TChar8) * length);
      }
      _position += sizeof(TChar8) * length;
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个16位字符串。</T>
   MO_OVERRIDE void WriteString16(TChar16C* pValue, TInt length){
      if(length < 0){
         length = RString16::Length(pValue);
      }
      this->EnsureSize(_position + sizeof(TUint16) + length);
      *(TUint16*)(this->_pMemory + _position) = length;
      _position += sizeof(TUint16);
      if(length > 0){
         MO_LIB_MEMORY_COPY(this->_pMemory + _position, _capacity - _position, pValue, sizeof(TChar16) * length);
      }
      _position += sizeof(TChar16) * length;
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个32位字符串。</T>
   MO_OVERRIDE void WriteString32(TChar32C* pValue, TInt length){
      if(length < 0){
         length = RString32::Length(pValue);
      }
      this->EnsureSize(_position + sizeof(TUint16) + length);
      *(TUint16*)(this->_pMemory + _position) = length;
      _position += sizeof(TUint16);
      if(length > 0){
         MO_LIB_MEMORY_COPY(this->_pMemory + _position, _capacity - _position, pValue, sizeof(TChar32) * length);
      }
      _position += sizeof(TChar32) * length;
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
   //------------------------------------------------------------
   // <T>向字节流中写入一个字符串。</T>
   MO_OVERRIDE void WriteString(TCharC* pValue, TInt length){
      if(length < 0){
         length = RString::Length(pValue);
      }
      this->EnsureSize(_position + sizeof(TUint16) + length);
      *(TUint16*)(this->_pMemory + _position) = length;
      _position += sizeof(TUint16);
      if(length > 0){
         MO_LIB_MEMORY_COPY(this->_pMemory + _position, _capacity - _position, pValue, sizeof(TChar) * length);
      }
      _position += sizeof(TChar) * length;
      // 调整长度
      if(_position > this->_length){
         this->_length = _position;
      }
   }
};

//============================================================
// <T>可序列化接口。</T>
//
// @history 100527 MAOCY 创建
//============================================================
class MO_CM_DECLARE ISerialize{
public:
   MO_ABSTRACT ~ISerialize(){
   }
public:
   MO_VIRTUAL TBool Serialize(IDataOutput* pOutput) = 0;
   MO_VIRTUAL TBool Unserialize(IDataInput* pInput) = 0;
};

//============================================================
// <T>读取器接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IReader{
public:
   //------------------------------------------------------------
   // <T>析构读取器接口。</T>
   MO_ABSTRACT ~IReader(){
   }
public:
   MO_VIRTUAL TInt Read(TChar* pData, TInt capacity) = 0;
};

//============================================================
// <T>读取器接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IStreamReader : public IReader{
public:
   //------------------------------------------------------------
   // <T>析构读取器接口。</T>
   MO_ABSTRACT ~IStreamReader(){
   }
public:
   MO_VIRTUAL TBool Flip() = 0;
   MO_VIRTUAL TBool Close() = 0;
};

//============================================================
// <T>读取器数据流接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IDataReader : public IReader{
public:
   //------------------------------------------------------------
   // <T>析构数据读取器接口。</T>
   MO_ABSTRACT ~IDataReader(){
   }
public:
   MO_VIRTUAL TInt ReadLine8(TChar8* pValue, TInt capacity) = 0;
   MO_VIRTUAL TInt ReadLine16(TChar16* pValue, TInt capacity) = 0;
   MO_VIRTUAL TInt ReadLine32(TChar32* pValue, TInt capacity) = 0;
   MO_VIRTUAL TInt ReadLine(TChar* pValue, TInt capacity) = 0;
   MO_VIRTUAL TInt ReadLine(MString* pValue) = 0;
};

//============================================================
// <T>读取器。</T>
//
// @history 100520 MAOCY 创建
//============================================================
class MO_CM_DECLARE TDataReader : public IDataReader{
protected:
   IReader* _pReader;
public:
   TDataReader();
   TDataReader(IReader* pReader);
   MO_ABSTRACT ~TDataReader();
public:
   IReader* Reader();
   void SetReader(IReader* pReader);
public:
   MO_OVERRIDE TInt Read(TChar* pData, TSize size);
};

//============================================================
// <T>书写器接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IWriter{
public:
   MO_ABSTRACT ~IWriter(){
   }
public:
   MO_VIRTUAL TInt Write(TCharC* pData, TInt length) = 0;
};

//============================================================
// <T>书写器接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IStreamWriter : public IWriter{
public:
   MO_ABSTRACT ~IStreamWriter(){
   }
public:
   MO_VIRTUAL TBool Flush() = 0;
   MO_VIRTUAL TBool Close() = 0;
};

//============================================================
// <T>书写器数据流接口。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE IDataWriter : public IWriter{
public:
   MO_ABSTRACT ~IDataWriter(){
   }
public:
   MO_VIRTUAL TInt WriteBytes(TAny* pData, TInt length) = 0;
   MO_VIRTUAL void WriteString8(TChar8C* pValue, TInt length = -1) = 0;
   MO_VIRTUAL void WriteString16(TChar16C* pValue, TInt length = -1) = 0;
   MO_VIRTUAL void WriteString32(TChar32C* pValue, TInt length = -1) = 0;
   MO_VIRTUAL void WriteString(TCharC* pValue, TInt length = -1) = 0;
};

//============================================================
// <T>输出数据流。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CM_DECLARE TDataWriter : public IDataWriter{
protected:
   IWriter* _pWriter;
public:
   TDataWriter();
   TDataWriter(IWriter* pWriter);
   MO_ABSTRACT ~TDataWriter();
public:
   IWriter* Writer();
   void SetWriter(IWriter* pWriter);
public:
   MO_OVERRIDE TInt Write(TCharC* pData, TSize size);
};

//============================================================
// <T>定长数据流。</T>
//
// @history 120211 MAOCY 创建
//============================================================
template <TInt S>
class MO_CM_DECLARE TFixStream :
      public TFixArray<TByte, S>,
      public IInput,
      public IOutput{
public:
   //------------------------------------------------------------
   TFixStream(){
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~TFixStream(){
   }
public:
   //------------------------------------------------------------
   MO_OVERRIDE TInt Read(TAny* pData, TSize size){
      return 0;
   }
   //------------------------------------------------------------
   MO_OVERRIDE TInt Write(TAnyC* pData, TSize size){
      return 0;
   }
};

//============================================================
// <T>字符数据流。</T>
//
// @history 140107 MAOCY 创建
//============================================================
class MO_CM_DECLARE FByteString :
      public FBytes,
      public IDataReader,
      public IDataWriter{
protected:
   TInt _position;
public:
   FByteString();
   MO_ABSTRACT ~FByteString();
public:
   MO_OVERRIDE TInt Position();
   MO_OVERRIDE void SetPosition(TInt position);
   MO_OVERRIDE TByte* PositionMemory();
   MO_OVERRIDE void Skip(TInt length);
public:
   MO_OVERRIDE TInt Read(TChar* pData, TInt capacity);
   MO_OVERRIDE TBool ReadBool();
   MO_OVERRIDE TInt ReadInt();
   MO_OVERRIDE TInt8 ReadInt8();
   MO_OVERRIDE TInt16 ReadInt16();
   MO_OVERRIDE TInt32 ReadInt32();
   MO_OVERRIDE TInt64 ReadInt64();
   MO_OVERRIDE TUint ReadUint();
   MO_OVERRIDE TUint8 ReadUint8();
   MO_OVERRIDE TUint16 ReadUint16();
   MO_OVERRIDE TUint32 ReadUint32();
   MO_OVERRIDE TUint64 ReadUint64();
   MO_OVERRIDE TFloat ReadFloat();
   MO_OVERRIDE TDouble ReadDouble();
   MO_OVERRIDE TInt ReadString8(TChar8* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString16(TChar16* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString32(TChar32* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadString(TChar* pValue, TInt capacity);
   MO_OVERRIDE TString8 ReadString8();
   MO_OVERRIDE TString16 ReadString16();
   MO_OVERRIDE TString32 ReadString32();
   MO_OVERRIDE TString ReadString();
   MO_OVERRIDE TInt ReadLine8(TChar8* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadLine16(TChar16* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadLine32(TChar32* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadLine(TChar* pValue, TInt capacity);
   MO_OVERRIDE TInt ReadLine(MString* pValue);
public:
   MO_OVERRIDE TInt Write(TCharC* pData, TInt length = -1);
   MO_OVERRIDE TInt WriteBytes(TAny* pData, TInt length);
   MO_OVERRIDE void WriteBool(TBool value);
   MO_OVERRIDE void WriteInt(TInt value);
   MO_OVERRIDE void WriteInt8(TInt8 value);
   MO_OVERRIDE void WriteInt16(TInt16 value);
   MO_OVERRIDE void WriteInt32(TInt32 value);
   MO_OVERRIDE void WriteInt64(TInt64 value);
   MO_OVERRIDE void WriteUint(TUint value);
   MO_OVERRIDE void WriteUint8(TUint8 value);
   MO_OVERRIDE void WriteUint16(TUint16 value);
   MO_OVERRIDE void WriteUint32(TUint32 value);
   MO_OVERRIDE void WriteUint64(TUint64 value);
   MO_OVERRIDE void WriteFloat(TFloat value);
   MO_OVERRIDE void WriteDouble(TDouble value);
   MO_OVERRIDE void WriteString8(TChar8C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString16(TChar16C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString32(TChar32C* pValue, TInt length = -1);
   MO_OVERRIDE void WriteString(TCharC* pValue, TInt length = -1);
public:
   MO_OVERRIDE void Reset();
};

MO_NAMESPACE_END

#endif // __MO_CM_STREAM_H__
