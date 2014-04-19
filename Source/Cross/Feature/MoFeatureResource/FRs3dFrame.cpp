#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dFrame, FInstance);

//============================================================
// <T>构造资源3D帧。</T>
//============================================================
FRs3dFrame::FRs3dFrame(){
   _index = 0;
   _tick = 0;
   _alpha = 1.0f;
}

//============================================================
// <T>析构资源3D帧。</T>
//============================================================
FRs3dFrame::~FRs3dFrame(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dFrame::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   _tick = pInput->ReadUint16();
   _matrix.Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
