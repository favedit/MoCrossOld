#include "MoMtGeom.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SDoubleRectangle::Serialize(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteDouble(left);
   pOutput->WriteDouble(top);
   pOutput->WriteDouble(width);
   pOutput->WriteDouble(height);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SDoubleRectangle::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   left = pInput->ReadDouble();
   top = pInput->ReadDouble();
   width = pInput->ReadDouble();
   height = pInput->ReadDouble();
   return ESuccess;
}

MO_NAMESPACE_END
