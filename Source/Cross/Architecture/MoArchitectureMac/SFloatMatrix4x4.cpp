#include "MoMtMatrix.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SFloatMatrix4x4::SerializeData(IDataOutput* pOutput){
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SFloatMatrix4x4::UnserializeData(IDataInput* pInput){
   return ESuccess;
}

MO_NAMESPACE_END
