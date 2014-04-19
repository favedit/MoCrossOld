#include "MoCmProperty.h"

MO_NAMESPACE_BEGIN

//============================================================
TProperty::TProperty(){
   MO_CLEAR(_pInfo);
   MO_CLEAR(_pData);
   _modifyCd = EModify_None;
}

//============================================================
TProperty::TProperty(SPropertyInfo* pInfo){
   _pInfo = pInfo;
   MO_CLEAR(_pData);
   _modifyCd = EModify_None;
}

//============================================================
TProperty::TProperty(SPropertyInfo* pInfo, TAny* pValue){
   _pInfo = pInfo;
   _pData = pValue;
   _modifyCd = EModify_None;
}

//============================================================
TProperty::TProperty(SPropertyInfo* pInfo, EModify modifyCd, TAny* pValue){
   _pInfo = pInfo;
   _pData = pValue;
   _modifyCd = modifyCd;
}

//============================================================
TBool TProperty::Attach(TProperty& property){
   return ETrue;
}

//============================================================
TBool TProperty::Assign(const TProperty* pProperty){
   return ETrue;
}

//============================================================
TBool TProperty::Modify(const TProperty* pProperty, EPropertyModify modifyCd){
   return ETrue;
}

//============================================================
TBool TProperty::IsValid(){
   return (NULL != _pInfo);
}

//============================================================
SPropertyInfo* TProperty::Info(){
   return _pInfo;
}

//============================================================
void TProperty::SetInfo(SPropertyInfo* pInfo){
   _pInfo = pInfo;
}

//============================================================
TAnyC* TProperty::GetC(){
   return _pData;
}

//============================================================
TAny* TProperty::Get(){
   return _pData;
}

//============================================================
void TProperty::Set(TAny* pValue){
   _pData = pValue;
}

//============================================================
void TProperty::Set(SPropertyInfo* pInfo, TAny* pValue){
   _pInfo = pInfo;
   _pData = pValue;
}

//============================================================
// <T>将数据转换为布尔类型。</T>
//
// @return 转换后的数据
//============================================================
TBool TProperty::AsBool(){
   TBool result = EFalse;
   if(NULL != _pInfo){
      //RTypeConverter::ToBool(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为有符号8位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TInt8 TProperty::AsInt8(){
   TInt8 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToInt8(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为有符号16位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TInt16 TProperty::AsInt16(){
   TInt16 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToInt16(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为有符号32位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TInt32 TProperty::AsInt32(){
   TInt32 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToInt32(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为有符号64位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TInt64 TProperty::AsInt64(){
   TInt64 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToInt64(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为无符号8位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TUint8 TProperty::AsUint8(){
   TUint8 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToUint8(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为无符号16位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TUint16 TProperty::AsUint16(){
   TUint16 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToUint16(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为无符号32位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TUint32 TProperty::AsUint32(){
   TUint32 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToUint32(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为无符号64位整数类型。</T>
//
// @return 转换后的数据
//============================================================
TUint64 TProperty::AsUint64(){
   TUint64 result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToUint64(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
TFloat TProperty::AsFloat(){
   TFloat result = 0;
   return result;
}

//============================================================
TDouble TProperty::AsDouble(){
   TDouble result = 0;
   return result;
}

//============================================================
// <T>将数据转换为日期 (32位 - 秒)类型。</T>
//
// @return 转换后的数据
//============================================================
TDate TProperty::AsDate(){
   TDate result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToDate(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为时间 (32位 - 秒)类型。</T>
//
// @return 转换后的数据
//============================================================
TTime TProperty::AsTime(){
   TTime result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToTime(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为刻度 (32位 - 秒)类型。</T>
//
// @return 转换后的数据
//============================================================
TTick TProperty::AsTick(){
   TTick result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToTick(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为时段 (32位 - 秒)类型。</T>
//
// @return 转换后的数据
//============================================================
TSpan TProperty::AsSpan(){
   TSpan result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToSpan(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为日期时间 (64位 - 秒)类型。</T>
//
// @return 转换后的数据
//============================================================
TDateTime TProperty::AsDateTime(){
   TDateTime result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToDateTime(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为时间刻度 (64位 - 秒)类型。</T>
//
// @return 转换后的数据
//============================================================
TTimeTick TProperty::AsTimeTick(){
   TTimeTick result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToTimeTick(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为时间段 (64位 - 秒)类型。</T>
//
// @return 转换后的数据
//============================================================
TTimeSpan TProperty::AsTimeSpan(){
   TTimeSpan result = 0;
   if(NULL != _pInfo){
      //RTypeConverter::ToTimeSpan(_pInfo->TypeCd(), _pData, result);
   }
   return result;
}

//============================================================
// <T>将数据转换为变长字符指针类型。</T>
//
// @return 转换后的数据
//============================================================
TCharC* TProperty::AsString(){
   TCharC** result = NULL;
   if(NULL != _pInfo){
      //RTypeConverter::ToString(_pInfo->TypeCd(), _pData, result);
   }
   return *result;
}

MO_NAMESPACE_END
