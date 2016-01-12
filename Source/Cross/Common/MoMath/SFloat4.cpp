#include "MoMtFloat.h"

MO_NAMESPACE_BEGIN
   
//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SFloat4::Serialize(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteFloat(data[0]);
   pOutput->WriteFloat(data[1]);
   pOutput->WriteFloat(data[2]);
   pOutput->WriteFloat(data[3]);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SFloat4::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   data[0] = pInput->ReadFloat();
   data[1] = pInput->ReadFloat();
   data[2] = pInput->ReadFloat();
   data[3] = pInput->ReadFloat();
   return ESuccess;
}

//============================================================
//<T>解析字符串内容。</T>
// 
// @param pValue 字符串
// @return 是否解析成功
//============================================================
TResult SFloat4::Parse(TCharC* pValue){
   // 检查长度
   TStringRefer value(pValue);
   TInt length = value.Length();
   if(length == 0){
      MO_ERROR(TC("It is invalid length."));
      return EFailure;
   }
   TFsText temp;
   TInt begin = 0;
   // 解析内容1
   TInt index = value.IndexOf(',', begin);
   if(ENotFound  == index){
      MO_ERROR(TC("Splite value failure."));
      return EFailure;
   }
   temp.Assign(value.SubStrC(begin, index));
   data[0] = RFloat::Parse(temp.MemoryC());
   value = value.RightStrC(value.Length() - index - 1);
   // 解析内容2
   index = value.IndexOf(',', begin);
   if(ENotFound  == index){
      MO_ERROR(TC("Splite value failure."));
      return EFailure;
   }
   temp.Assign(value.SubStrC(begin, index));
   data[1] = RFloat::Parse(temp.MemoryC());
   value = value.RightStrC(value.Length() - index - 1);
   // 解析内容3
   index = value.IndexOf(',', begin);
   if(ENotFound  == index){
      MO_ERROR(TC("Splite value failure."));
      return EFailure;
   }
   temp.Assign(value.SubStrC(begin, index));
   data[2] = RFloat::Parse(temp.MemoryC());
   // 解析内容4
   temp.Assign(value.SubStrC(index + 1, length));
   data[3] = RFloat::Parse(temp.MemoryC());
   return ESuccess;
}

//============================================================
// <T>格式化内容为显示内容。</T>
//
// @param pBuffer 缓冲指针
// @param capacity 缓冲长度
// @return 字符串
//============================================================
TCharC* SFloat4::Format(TChar* pBuffer, TInt capacity){
   TStringRefer result(pBuffer, capacity);
   result.AssignFormat(TC("x=%f, y=%f, z=%f, w=%f"), data[0], data[1], data[2], data[3]);
   return pBuffer;
}

//============================================================
// <T>格式化内容为字符串。</T>
//
// @param pBuffer 缓冲指针
// @param capacity 缓冲长度
// @return 字符串
//============================================================
TCharC* SFloat4::ToString(TChar* pBuffer, TInt capacity){
   TStringRefer result(pBuffer, capacity);
   result.AppendFormat(TC("%f,%f,%f,%f"), data[0], data[1], data[2], data[3]);
   return pBuffer;
}

MO_NAMESPACE_END
