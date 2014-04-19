#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>读取TBool类型数据</T>
//
// @return 返回TBool类型数据
//============================================================
TBool FByteFileStream::ReadBool(){
   TBool value;
   TUint result = fread(&value, sizeof(TBool), 1, _handle);
   if(result > 0){
      return value;
   }
   return EFalse;
}

//============================================================
// <T>读取TUint类型数据</T>
//
// @return 返回TUint类型类型数据
//============================================================
TUint FByteFileStream::ReadUint(){
   TUint value;
   TUint result = fread(&value, sizeof(TUint), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TInt类型数据</T>
//
// @return 返回TInt类型类型数据
//============================================================
TInt FByteFileStream::ReadInt(){
   TInt value;
   TUint result = fread(&value, sizeof(TInt), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TUint8类型数据</T>
//
// @return 返回TUint8类型类型数据
//============================================================
TUint8 FByteFileStream::ReadUint8(){
   TUint8 value;
   TUint result = fread(&value, sizeof(TUint8), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}
//============================================================
// <T>读取TInt8类型数据</T>
//
// @return 返回TInt类型类型数据
//============================================================
TInt8 FByteFileStream::ReadInt8(){
   TInt8 value;
   TUint result = fread(&value, sizeof(TInt8), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TUint16类型数据</T>
//
// @return 返回TUint16类型类型数据
//============================================================
TUint16 FByteFileStream::ReadUint16(){
   TUint16 value;
   TUint result = fread(&value, sizeof(TUint16), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
   return value;
}
//============================================================
// <T>读取TInt16类型数据</T>
//
// @return 返回TInt类型类型数据
//============================================================
TInt16 FByteFileStream::ReadInt16(){
   TInt16 value;
   TUint result = fread(&value, sizeof(TInt16), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
   return value;
}

//============================================================
// <T>读取TUint32类型数据</T>
//
// @return 返回TUint32类型类型数据
//============================================================
TUint32 FByteFileStream::ReadUint32(){
   TUint32 value;
   TUint result = fread(&value, sizeof(TUint32), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TInt32类型数据</T>
//
// @return 返回TInt类型类型数据
//============================================================
TInt32 FByteFileStream::ReadInt32(){
   TInt32 value;
   TUint result = fread(&value, sizeof(TInt32), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TUint64类型数据</T>
//
// @return 返回TUint64类型类型数据
//============================================================
TUint64 FByteFileStream::ReadUint64(){
   TInt64 value;
   TUint result = fread(&value, sizeof(TUint64), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TInt64类型数据</T>
//
// @return 返回TInt类型类型数据
//============================================================
TInt64 FByteFileStream::ReadInt64(){
   TInt64 value;
   TUint result = fread(&value, sizeof(TInt64), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TFloat类型数据</T>
//
// @return 返回TFoalt类型类型数据
//============================================================
TFloat FByteFileStream::ReadFloat(){
   TFloat value;
   TUint result = fread(&value, sizeof(TFloat), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取TDouble类型数据</T>
//
// @return 返回TDouble类型类型数据
//============================================================
TDouble FByteFileStream::ReadDouble(){
   TDouble value;
   TUint result = fread(&value, sizeof(TDouble), 1, _handle);
   if(result > 0){
      return value;
   }
   return 0;
}

//============================================================
// <T>读取字符串类型数据，先读长度，后读数据。</T>
//
// @param pBuffer 存放读取到的数据的内存块指针。
// @param length 内存块的长度。
//============================================================
TInt FByteFileStream::ReadString(TChar8C* pBuffer, TInt length){
   TInt readed = 0;
   TUint result = fread(&readed, sizeof(TInt),1,_handle);
   if(result == sizeof(TUint32)){
      if(readed > 0){
         MO_ASSERT(readed <= length);
         result = fread((TAny*)pBuffer, sizeof(TChar8C) * readed, 1, _handle);
      }
   }
   return readed;
}

//============================================================
// <T>读取字符串类型数据，先读长度，后读数据,宽字符版本。</T>
//
// @param pBuffer 存放读取到的数据的内存块指针。
// @param length 内存块的长度。
//============================================================
TInt FByteFileStream::ReadString(TChar16C* pBuffer, TInt length){
   TInt readed = 0;
   TUint result = fread(&readed, sizeof(TInt),1,_handle);
   if(result == sizeof(TInt)){
      if(readed > 0){
         MO_ASSERT(readed <= length);
         result = fread((TAny*)pBuffer, sizeof(TChar16C) * readed , 1, _handle);
      }
   }
   return readed;
}
//============================================================
// <T>写入Tbool类型数据</T>
//============================================================
void FByteFileStream::WriteBoolean(TBool value){
   fwrite(&value, sizeof(TBool), 1, _handle);
}

//============================================================
// <T>写入TUint类型数据</T>
//============================================================
void FByteFileStream::WriteUInt(TUint value){
   fwrite(&value, sizeof(TUint), 1, _handle);
}
//============================================================
// <T>写入TInt类型数据</T>
//============================================================
void FByteFileStream::WriteInt(TInt value){
   fwrite(&value, sizeof(TInt), 1, _handle);
}

//============================================================
// <T>写入TUint8类型数据</T>
//============================================================
void FByteFileStream::WriteUInt8(TUint8 value){
   fwrite(&value, sizeof(TUint8), 1, _handle);
}
//============================================================
// <T>写入TInt8类型数据</T>
//============================================================
void FByteFileStream::WriteInt8(TInt8 value){
   fwrite(&value, sizeof(TInt8), 1, _handle);
}

//============================================================
// <T>写入TUint16类型数据</T>
//============================================================
void FByteFileStream::WriteUInt16(TUint16 value){
   fwrite(&value, sizeof(TUint16), 1, _handle);
}
//============================================================
// <T>写入TInt16类型数据</T>
//============================================================
void FByteFileStream::WriteInt16(TInt16 value){
   fwrite(&value, sizeof(TInt16), 1, _handle);
}

//============================================================
// <T>写入TUint32类型数据</T>
//============================================================
void FByteFileStream::WriteUInt32(TUint32 value){
   fwrite(&value, sizeof(TUint32), 1, _handle);
}

//============================================================
// <T>写入TInt32类型数据</T>
//============================================================
void FByteFileStream::WriteInt32(TInt32 value){
   fwrite(&value, sizeof(TInt32), 1, _handle);
}

//============================================================
//<T>写入TUint64类型数据</T>
//============================================================
void FByteFileStream::WriteUInt64(TUint64 value){
   fwrite(&value, sizeof(TUint64), 1, _handle);
}

//============================================================
//<T>写入TInt64类型数据</T>
//============================================================
void FByteFileStream::WriteInt64(TInt64 value){
   fwrite(&value, sizeof(TInt64), 1, _handle);
}

//============================================================
//<T>写入TFloat类型数据</T>
//============================================================
void FByteFileStream::WriteFloat(TFloat value){
   fwrite(&value , sizeof(TFloat), 1, _handle);
}

//============================================================
// <T>写入TDouble类型数据</T>
//============================================================
void FByteFileStream::WriteDouble(TDouble value){
   fwrite(&value, sizeof(TDouble), 1, _handle);
}

//============================================================
// <T>写入字符串类型数据，先写长度，后跟数据。</T>
//
// @param pBuffer  写入数据的指针
// @param length 写入数据长度
//============================================================
void FByteFileStream::WriteString(TChar8C* pBuffer, TInt length){
   fwrite(&length, sizeof(TUint32), 1, _handle);
   if(length > 0){
      fwrite(pBuffer, sizeof(TChar8C) * length, 1, _handle);
   }
}

//============================================================
// <T>写入字符串类型数据，先写长度，后跟数据。</T>
//
// @param pBuffer  写入数据的指针
// @param length 写入数据长度
//============================================================
void FByteFileStream::WriteStringA16(TChar8C* pBuffer, TInt length){
   TInt16 len = (TInt16)length;
   if(len < 0){
      len = strlen(pBuffer);
   }
   fwrite(&len, sizeof(TUint16), 1, _handle);
   if(len > 0){
      fwrite(pBuffer, sizeof(TChar8C) * len, 1, _handle);
   }
}

//============================================================
// <T>写入字符串类型数据，先写长度，后跟数据,宽字符版本。</T>
//
// @param pBuffer  写入数据的指针
// @param length 写入数据长度
//============================================================
void FByteFileStream::WriteString(TChar16C* pBuffer, TInt length){
   fwrite(&length, sizeof(TUint32), 1, _handle);
   if(length > 0){
      fwrite(pBuffer, sizeof(TChar16C) * length, 1, _handle);
   }
}

MO_NAMESPACE_END
