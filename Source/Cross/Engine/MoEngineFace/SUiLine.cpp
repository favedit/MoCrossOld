#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造界面线。</T>
//============================================================
SUiLine::SUiLine(){
   color = 0;
   width = 0;
   styleCd = EUiLineStyle_Solid;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SUiLine::Unserialize(IDataInput* pInput){
   color = pInput->ReadInt32();
   width = pInput->ReadInt8();
   styleCd = (EUiLineStyle)pInput->ReadInt8();
   return ESuccess;
}

MO_NAMESPACE_END
