#include "MoCmGeom.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
//<T>解析字符串内容。</T>
// 
// @param pValue 字符串
// @return 是否解析成功
//============================================================
TBool S{type_name}Point3::Parse(TCharC* pValue){
   // 检查长度
   TStringRefer value(pValue);
   TInt length = value.Length();
   if(length == 0){
      MO_ERROR(TC("It is invalid length."));
      return EFalse;
   }
   // 分割内容
   TFsText temp;
   TInt begin = 0;
   TInt index = value.IndexOf(',', begin);
   if(ENotFound  == index){
      MO_ERROR(TC("Splite value failure."));
      return EFalse;
   }
   // 解析内容X
   temp.Assign(value.SubStrC(begin, index));
   x = R{type_name}::Parse(temp.MemoryC());
   // 分割内容
   begin = index + 1;
   index = value.IndexOf(',', begin);
   if(ENotFound == index){
      MO_ERROR(TC("Splite value failure."));
      return EFalse;
   }
   // 解析内容Y
   temp.Assign(value.SubStrC(begin, index));
   y = R{type_name}::Parse(temp.MemoryC());
   // 解析内容Z
   temp.Assign(value.SubStrC(index + 1, length));
   z = R{type_name}::Parse(temp.MemoryC());
   return ETrue;
}

//============================================================
// <T>格式化内容为显示内容。</T>
//
// @param pBuffer 缓冲指针
// @param capacity 缓冲长度
// @return 字符串
//============================================================
TCharC* S{type_name}Point3::Format(TChar* pBuffer, TInt capacity){
   TStringRefer result(pBuffer, capacity);
   result.AssignFormat(TC("x={format}, y={format}, z={format}"), x, y, z);
   return pBuffer;
}

//============================================================
// <T>格式化内容为字符串。</T>
//
// @param pBuffer 缓冲指针
// @param capacity 缓冲长度
// @return 字符串
//============================================================
TCharC* S{type_name}Point3::ToString(TChar* pBuffer, TInt capacity){
   TStringRefer result(pBuffer, capacity);
   result.AppendFormat(TC("{format},{format},{format}"), x, y, z);
   return pBuffer;
}

MO_NAMESPACE_END
