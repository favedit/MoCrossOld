#include "MoMtOutline.h"

MO_NAMESPACE_BEGIN

//============================================================
//<T>解析字符串内容。</T>
// 
// @param pValue 字符串
// @return 是否解析成功
//============================================================
TBool SFloatOutline3::Parse(TCharC* pValue){
   return ETrue;
}

//============================================================
// <T>格式化内容为显示内容。</T>
//
// @param pBuffer 缓冲指针
// @param capacity 缓冲长度
// @return 字符串
//============================================================
TCharC* SFloatOutline3::Format(TChar* pBuffer, TInt capacity){
   return pBuffer;
}

//============================================================
// <T>格式化内容为字符串。</T>
//
// @param pBuffer 缓冲指针
// @param capacity 缓冲长度
// @return 字符串
//============================================================
TCharC* SFloatOutline3::ToString(TChar* pBuffer, TInt capacity){
   return pBuffer;
}

MO_NAMESPACE_END
