#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造动画段。</T>
//============================================================
FAnimationClip::FAnimationClip(){
   MO_CLEAR(_pAnimation);
   _optionData = EFalse;
   _directionCd = 0;
   _reverseOption = EFalse;
   _reverseCd = 0;
   _reverseDirection = 0;
   _delay = 0;
   _pFrames = MO_CREATE(FAnimationFrameVector);
}

//============================================================
// <T>析构动画段。</T>
//============================================================
FAnimationClip::~FAnimationClip(){
   TInt frameCount = _pFrames->Count();
   for(TInt n = 0; n < frameCount; n++){
      FAnimationFrame* pFrame = _pFrames->Get(n);
      MO_DELETE(pFrame);
   }
   MO_DELETE(_pFrames);
}

//============================================================
// <T>测试是否已经失效。</T>
//
// @return 是否已经失效
//============================================================
TBool FAnimationClip::TestInvalid(){
   TInt count = _pFrames->Count();
   for(TInt n = 0; n < count; n++){
      FAnimationFrame* pFrame = _pFrames->Get(n);
      if(!pFrame->TestInvalid()){
         return EFalse;
      }
   }
   return ETrue;
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FAnimationClip::Unserialize(IDataInput* pInput){
   // 读取属性
   _directionCd = pInput->ReadInt8();
   _size.Unserialize16(pInput);
   _baryCenter.Unserialize16(pInput);
   _delay = pInput->ReadInt32();
   // 读取桢集合
   TInt frameCount = pInput->ReadUint8();
   for(TInt n = 0; n < frameCount; n++){
      FAnimationFrame* pFrame = MO_CREATE(FAnimationFrame);
      pFrame->SetAnimation(_pAnimation);
      pFrame->SetClip(this);
      pFrame->Unserialize(pInput);
      _pFrames->Push(pFrame);
   }
   return ESuccess;
}

MO_NAMESPACE_END
