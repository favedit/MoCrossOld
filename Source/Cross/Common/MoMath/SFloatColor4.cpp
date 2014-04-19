#include "MoMtGeom.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SFloatColor4::Serialize(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteFloat(red);
   pOutput->WriteFloat(green);
   pOutput->WriteFloat(blue);
   pOutput->WriteFloat(alpha);
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SFloatColor4::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   red = pInput->ReadFloat();
   green = pInput->ReadFloat();
   blue = pInput->ReadFloat();
   alpha = pInput->ReadFloat();
   return ESuccess;
}

MO_NAMESPACE_END
