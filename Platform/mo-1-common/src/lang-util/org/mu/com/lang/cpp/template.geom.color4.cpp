#include "MoCmGeom.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult S{type_name}Color4::Serialize(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->Write{type_name}(red);
   pOutput->Write{type_name}(green);
   pOutput->Write{type_name}(blue);
   pOutput->Write{type_name}(alpha);
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult S{type_name}Color4::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   red = pInput->Read{type_name}();
   green = pInput->Read{type_name}();
   blue = pInput->Read{type_name}();
   alpha = pInput->Read{type_name}();
   return ESuccess;
}

MO_NAMESPACE_END
