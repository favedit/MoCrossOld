#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dMovie, FInstance);

//============================================================
// <T>构造资源3D帧。</T>
//============================================================
FRs3dMovie::FRs3dMovie(){
   MO_CLEAR(_pAnimation);
   _playCd = 0;
   _frameBegin = 0;
   _frameEnd = 0;
   _frameRate = 0.0f;
}

//============================================================
// <T>析构资源3D帧。</T>
//============================================================
FRs3dMovie::~FRs3dMovie(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dMovie::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   _playCd = pInput->ReadInt8();
   _frameBegin = pInput->ReadInt16();
   _frameEnd = pInput->ReadInt16();
   _frameRate = pInput->ReadFloat();
   return ESuccess;
}

MO_NAMESPACE_END
