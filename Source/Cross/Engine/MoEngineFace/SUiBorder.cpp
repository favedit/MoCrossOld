#include "MoEfDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造界面边框。</T>
//============================================================
SUiBorder::SUiBorder(){
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult SUiBorder::Unserialize(IDataInput* pInput){
   left.Unserialize(pInput);
   top.Unserialize(pInput);
   right.Unserialize(pInput);
   bottom.Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
