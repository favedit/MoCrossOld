#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>将数据转换为布尔类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToBool(EType typeCd, TAny* pValue, TBool& value){
   MO_ASSERT(pValue);
   switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         value = *(TBool*)pValue;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 result = *(TInt8*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 result = *(TInt16*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 result = *(TInt32*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 result = *(TInt64*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt result = *(TInt*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 result = *(TUint8*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 result = *(TUint16*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 result = *(TUint32*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 result = *(TUint64*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint result = *(TUint*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat result = *(TFloat*)pValue;
         value = (0.0f == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble result = *(TDouble*)pValue;
         value = (0.0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微妙)类型，并转换
      case EType_DateTime:{
         TDateTime result = *(TDateTime*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick result = *(TTimeTick*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan result = *(TTimeSpan*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 result = *(TChar8*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 result = *(TChar16*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 result = *(TChar32*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar result = *(TChar*)pValue;
         value = (0 == result)?EFalse:ETrue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RBool::IsTrue(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为有符号8位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToInt8(EType typeCd, TAny* pValue, TInt8& value){
    MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         value = *(TInt8*)pValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TInt8)tempValue;         
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TInt8)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RInt8::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为有符号16位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToInt16(EType typeCd, TAny* pValue, TInt16& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         value = *(TInt16*)pValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TInt16)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RInt16::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为有符号32位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToInt32(EType typeCd, TAny* pValue, TInt32& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TInt32)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RInt32::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为有符号64位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToInt64(EType typeCd, TAny* pValue, TInt64& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         value = *(TInt64*)pValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TInt64)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RInt64::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为无符号8位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToUint8(EType typeCd, TAny* pValue, TUint8& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         value = *(TUint8*)pValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TUint8)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RUint8::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为无符号16位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToUint16(EType typeCd, TAny* pValue, TUint16& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         value = *(TUint16*)pValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TUint16)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RUint16::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为无符号32位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToUint32(EType typeCd, TAny* pValue, TUint32& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         value = *(TUint32*)pValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TUint32)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RUint32::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为无符号64位整数类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToUint64(EType typeCd, TAny* pValue, TUint64& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         value = *(TUint64*)pValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TUint64)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RUint64::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
TBool RTypeConverter::ToFloat(EType typeCd, TAny* pValue, TFloat& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result ? 1.0f : 0.0f;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         value = *(TFloat*)pValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TFloat)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RFloat::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
TBool RTypeConverter::ToDouble(EType typeCd, TAny* pValue, TDouble& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         value = *(TDouble*)pValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TDouble)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RDouble::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为日期 (32位 - 秒)类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToDate(EType typeCd, TAny* pValue, TDate& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TDate)tempValue;
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为时间 (32位 - 秒)类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToTime(EType typeCd, TAny* pValue, TTime& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TTime)tempValue;
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为刻度 (32位 - 秒)类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToTick(EType typeCd, TAny* pValue, TTick& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         //TCharC* result = (TCharC*)pValue;
//         value = RTick::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为时段 (32位 - 秒)类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToSpan(EType typeCd, TAny* pValue, TSpan& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         //TCharC* result = (TCharC*)pValue;
//         TSpan = RSpan::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为日期时间 (64位 - 秒)类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToDateTime(EType typeCd, TAny* pValue, TDateTime& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         value = *(TDateTime*)pValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TDateTime)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         value = RDateTime::Parse(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为时间刻度 (64位 - 秒)类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToTimeTick(EType typeCd, TAny* pValue, TTimeTick& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         value = *(TTimeTick*)pValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         TTimeSpan tempValue = *(TTimeSpan*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TTimeTick)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         //TCharC* result = (TCharC*)pValue;
//         value = RTimeTick::(result);
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为时间段 (64位 - 秒)类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToTimeSpan(EType typeCd, TAny* pValue, TTimeSpan& value){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
      case EType_Bool:{
         TBool result = *(TBool*)pValue;
         value = result?ETrue:EFalse;
         return ETrue;
      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         TInt8 tempValue = *(TInt8*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         TInt16 tempValue = *(TInt16*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         TInt32 tempValue = *(TInt32*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         TInt64 tempValue = *(TInt64*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         TUint8 tempValue = *(TUint8*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         TUint16 tempValue = *(TUint16*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         TUint32 tempValue = *(TUint32*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         TUint64 tempValue = *(TUint64*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
         TFloat tempValue = *(TFloat*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
         TDouble tempValue = *(TDouble*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         TDateTime tempValue = *(TDateTime*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         TTimeTick tempValue = *(TTimeTick*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         value = *(TTimeSpan*)pValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         TChar8 tempValue = *(TChar8*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         TChar16 tempValue = *(TChar16*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         TChar32 tempValue = *(TChar32*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         TChar tempValue = *(TChar*)pValue;
         value = (TTimeSpan)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         //TCharC* result = (TCharC*)pValue;
//         value = RTimeSpan::
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
// <T>将字数据转换为变长字符指针类型。</T>
//
// @param typeCd 类型枚举
// @param pValue 数据指针
// @param value 返回值
// @return 处理结果
//============================================================
TBool RTypeConverter::ToString(EType typeCd, TAny* pValue, TCharC** ppValue){
   MO_ASSERT(pValue);
    switch(typeCd){
      // 判断是否为布尔类型，并转换
//      case EType_Bool:{
//         TBool result = *(TBool*)pValue;
//         **ppValue = result?ETrue:EFalse;
//         return ETrue;
//      }
      // 判断是否为有符号8位整数类型，并转换
      case EType_Int8:{
         //TInt8 tempValue = *(TInt8*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为有符号16位整数类型，并转换
      case EType_Int16:{
         //TInt16 tempValue = *(TInt16*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为有符号32位整数类型，并转换
      case EType_Int32:{
         //TInt32 tempValue = *(TInt32*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为有符号64位整数类型，并转换
      case EType_Int64:{
         //TInt64 tempValue = *(TInt64*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为变长有符号整数类型，并转换
      case EType_Int:{
         TInt tempValue = *(TInt*)pValue;
         *ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为无符号8位整数类型，并转换
      case EType_Uint8:{
         //TUint8 tempValue = *(TUint8*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为无符号16位整数类型，并转换
      case EType_Uint16:{
         //TUint16 tempValue = *(TUint16*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为无符号32位整数类型，并转换
      case EType_Uint32:{
         //TUint32 tempValue = *(TUint32*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为无符号64位整数类型，并转换
      case EType_Uint64:{
         //TUint64 tempValue = *(TUint64*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为变长无符号整数类型，并转换
      case EType_Uint:{
         TUint tempValue = *(TUint*)pValue;
         *ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为单精度浮点数 (32位)类型，并转换
      case EType_Float:{
//         TFloat tempValue = *(TFloat*)pValue;
//         *ppValue = (TCharC*)tempValue;
//         return ETrue;
      }
      // 判断是否为双精度浮点数 (64位)类型，并转换
      case EType_Double:{
//         TDouble tempValue = *(TDouble*)pValue;
//         *ppValue = (TCharC*)tempValue;
//         return ETrue;
      }
      // 判断是否为日期时间 (64位 - 微秒)类型，并转换
      case EType_DateTime:{
         //TDateTime tempValue = *(TDateTime*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为时间刻度 (64位 - 微秒)类型，并转换
      case EType_TimeTick:{
         //TTimeTick tempValue = *(TTimeTick*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为时间段 (64位 - 微秒)类型，并转换
      case EType_TimeSpan:{
         //TTimeSpan tempValue = *(TTimeSpan*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为8位字符类型，并转换
      case EType_Char8:{
         //TChar8 tempValue = *(TChar8*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为16位字符类型，并转换
      case EType_Char16:{
         //TChar16 tempValue = *(TChar16*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为32位字符类型，并转换
      case EType_Char32:{
         //TChar32 tempValue = *(TChar32*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符类型，并转换
      case EType_Char:{
         //TChar tempValue = *(TChar*)pValue;
         //*ppValue = (TCharC*)tempValue;
         return ETrue;
      }
      // 判断是否为变长字符指针类型，并转换
      case EType_String:{
         TCharC* result = (TCharC*)pValue;
         *ppValue = (TCharC*)result;
         return ETrue;
      }
      // 判断是否为结构类型，并转换
      case EType_Struct:{
         return EFalse;
      }
      // 判断是否为对象类型，并转换
      case EType_Object:{
         return EFalse;
      }
      // 判断是否为对象指针类型，并转换
      case EType_Ptr:{
         return EFalse;
      }
      // 未处理
      default:
         break;
   }
   return EFalse;
}

//============================================================
TBool RTypeConverter::Convert(EType sourceTypeCd, TAny* pSource, EType targetTypeCd, TAny** ppTarget){
   return ETrue;
}

MO_NAMESPACE_END
