#include "MoCmStream.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字节数据流。</T>
//============================================================
TLinkOutput::TLinkOutput(TAny* pOutputData, TInt outputCapacity){
   _pMemory = (TByte*)pOutputData;
   _capacity = outputCapacity;
   _position = 0;
   _length = 0;
}

//============================================================
// <T>关联内存。</T>
//============================================================
void TLinkOutput::Link(TAny* pOutputData, TInt outputCapacity){
   _pMemory = (TByte*)pOutputData;
   _capacity = outputCapacity;
   _position = 0;
   _length = 0;
}

//============================================================
// <T>获得当前位置。</T>
//
// @return 当前位置
//============================================================
TInt TLinkOutput::Position(){
   return _position;
}

//============================================================
// <T>获得长度。</T>
//
// @return 长度
//============================================================
TInt TLinkOutput::Length(){
   return _length;
}

//============================================================
// <T>获得容量。</T>
//
// @return 容量
//============================================================
TInt TLinkOutput::Capacity(){
   return _capacity;
}

//============================================================
// <T>获得内存指针。</T>
//
// @return 内存指针
//============================================================
TByteC* TLinkOutput::MemoryC(){
   return _pMemory;
}

//============================================================
// <T>获得内存指针。</T>
//
// @return 内存指针
//============================================================
TByte* TLinkOutput::Memory(){
   return _pMemory;
}

//============================================================
// <T>获得当前位置的内存指针。</T>
//
// @return 内存指针
//============================================================
TByte* TLinkOutput::PositionMemory(){
   return (_pMemory + _position);
}

//============================================================
// <T>将流位置移动一段位置。</T>
//
// @param length 长度
//============================================================
void TLinkOutput::Skip(TInt length){
   _position += length;
}

//============================================================
// <T>设置数据长度。</T>
//============================================================
TBool TLinkOutput::SetLength(TInt inputLength){
   if(inputLength > _capacity){
      return EFalse;
   }
   if(_length < inputLength){
      _length = inputLength;
   }
   return ETrue;
}

//============================================================
// <T>写入数据。</T>
//
// @param pData 数据
// @param length 长度
// @return 写入长度
//============================================================
TInt TLinkOutput::Write(TAnyC* pData, TInt length){
   TBool result = SetLength(_position + length);
   if(result){
      TByte* pMemory = (TByte*)(_pMemory + _position);
      MO_LIB_MEMORY_COPY(pMemory, _capacity, pData, length);
      _position += length;
      return length;
   }
   return -1;
}

//============================================================
// <T>写入一个布尔值。</T>
//============================================================
void TLinkOutput::WriteBool(TBool value){
   TBool result = SetLength(_position + sizeof(TByte));
   if(result){
      *(TByte*)(_pMemory + _position) = value;
      _position += sizeof(TByte);
   }
}

//============================================================
// <T>写入一个整数。</T>
//============================================================
void TLinkOutput::WriteInt(TInt value){
   TBool result = SetLength(_position + sizeof(TInt));
   if(result){
      *(TInt*)(_pMemory + _position) = value;
      _position += sizeof(TInt);
   }
}

//============================================================
// <T>写入一个一字节整数。</T>
//============================================================
void TLinkOutput::WriteInt8(TInt8 value){
   TBool result = SetLength(_position + sizeof(TInt8));
   if(result){
      *(TInt8*)(_pMemory + _position) = value;
      _position += sizeof(TInt8);
   }
}

//============================================================
// <T>写入一个二字节整数。</T>
//============================================================
void TLinkOutput::WriteInt16(TInt16 value){
   TBool result = SetLength(_position + sizeof(TInt16));
   if(result){
      *(TInt16*)(_pMemory + _position) = value;
      _position += sizeof(TInt16);
   }
}

//============================================================
// <T>写入一个四字节整数。</T>
//============================================================
void TLinkOutput::WriteInt32(TInt32 value){
   TBool result = SetLength(_position + sizeof(TInt32));
   if(result){
      *(TInt32*)(_pMemory + _position) = value;
      _position += sizeof(TInt32);
   }
}

//============================================================
// <T>写入一个八字节整数。</T>
//============================================================
void TLinkOutput::WriteInt64(TInt64 value){
   TBool result = SetLength(_position + sizeof(TInt64));
   if(result){
      *(TInt64*)(_pMemory + _position) = value;
      _position += sizeof(TInt64);
   }
}

//============================================================
// <T>写入一个一字节无符号整数。</T>
//============================================================
void TLinkOutput::WriteUint(TUint value){
   TBool result = SetLength(_position + sizeof(TUint));
   if(result){
      *(TUint*)(_pMemory + _position) = value;
      _position += sizeof(TUint);
   }
}

//============================================================
// <T>写入一个一字节无符号整数。</T>
//============================================================
void TLinkOutput::WriteUint8(TUint8 value){
   TBool result = SetLength(_position + sizeof(TUint8));
   if(result){
      *(TUint8*)(_pMemory + _position) = value;
      _position += sizeof(TUint8);
   }
}

//============================================================
// <T>写入一个二字节无符号整数。</T>
//============================================================
void TLinkOutput::WriteUint16(TUint16 value){
   TBool result = SetLength(_position + sizeof(TUint16));
   if(result){
      *(TUint16*)(_pMemory + _position) = value;
      _position += sizeof(TUint16);
   }
}

//============================================================
// <T>写入一个四字节无符号整数。</T>
//============================================================
void TLinkOutput::WriteUint32(TUint32 value){
   TBool result = SetLength(_position + sizeof(TUint32));
   if(result){
      *(TUint32*)(_pMemory + _position) = value;
      _position += sizeof(TUint32);
   }
}

//============================================================
// <T>写入一个八字节无符号整数。</T>
//============================================================
void TLinkOutput::WriteUint64(TUint64 value){
   TBool result = SetLength(_position + sizeof(TUint64));
   if(result){
      *(TUint64*)(_pMemory + _position) = value;
      _position += sizeof(TUint64);
   }
}

//============================================================
// <T>写入一个四字节浮点数。</T>
//============================================================
void TLinkOutput::WriteFloat(TFloat value){
   TBool result = SetLength(_position + sizeof(TFloat));
   if(result){
      *(TFloat*)(_pMemory + _position) = value;
      _position += sizeof(TFloat);
   }
}

//============================================================
// <T>写入一个八字节浮点数。</T>
//============================================================
void TLinkOutput::WriteDouble(TDouble value){
   TBool result = SetLength(_position + sizeof(TDouble));
   if(result){
      *(TDouble*)(_pMemory + _position) = value;
      _position += sizeof(TDouble);
   }
}

//============================================================
// <T>写入一个8位字符串。</T>
//============================================================
void TLinkOutput::WriteString8(TChar8C* pValue, TInt length){
   if(length < 0){
      length = RString8::Length(pValue);
   }
   TBool result = SetLength(_position + sizeof(TUint16) + length);
   if(result){
      *(TUint16*)(_pMemory + _position) = length;
      _position += sizeof(TUint16);
      MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, length);
      _position += length;
   }
}

//============================================================
// <T>写入一个16位字符串。</T>
//============================================================
void TLinkOutput::WriteString16(TChar16C* pValue, TInt length){
	if(length < 0){
		length = RString16::Length(pValue);
	}
	TBool result = SetLength(_position + sizeof(TUint16) + length);
	if(result){
		*(TUint16*)(_pMemory + _position) = length;
		_position += sizeof(TUint16);
		MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, length);
		_position += length;
	}
}

//============================================================
// <T>写入一个32位字符串。</T>
//============================================================
void TLinkOutput::WriteString32(TChar32C* pValue, TInt length){
	if(length < 0){
		length = RChar32s::Length(pValue);
	}
	TBool result = SetLength(_position + sizeof(TUint16) + length);
	if(result){
		*(TUint16*)(_pMemory + _position) = length;
		_position += sizeof(TUint16);
		MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, length);
		_position += length;
	}
}

//============================================================
// <T>写入一个字符串。</T>
//============================================================
void TLinkOutput::WriteString(TCharC* pValue, TInt length){
   if(length < 0){
      length = RString::Length(pValue);
   }
   TBool result = SetLength(_position + sizeof(TUint16) + length);
   if(result){
      *(TUint16*)(_pMemory + _position) = length;
      _position += sizeof(TUint16);
      MO_LIB_MEMORY_COPY(_pMemory + _position, _capacity - _position, pValue, length);
      _position += length;
   }
}

//============================================================
// <T>重置。</T>
//============================================================
void TLinkOutput::Reset(){
   _position = 0;
   _length = 0;
}

MO_NAMESPACE_END
