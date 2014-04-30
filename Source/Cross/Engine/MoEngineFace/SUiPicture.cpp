#include "MoEfDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造界面线。</T>
//============================================================
SUiPicture::SUiPicture(){
   resourceId = 0;
   alignCd = EControlImageAlign_None;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SUiPicture::Unserialize(IDataInput* pInput){
   resourceId = pInput->ReadInt32();
   alignCd = (EControlImageAlign)pInput->ReadInt8();
   location.Unserialize16(pInput);
   size.Unserialize16(pInput);
   padding.Unserialize16(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
