#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dTrack, FInstance);

//============================================================
// <T>构造资源3D跟踪。</T>
//============================================================
FRs3dTrack::FRs3dTrack(){
   MO_CLEAR(_pAnimation);
   _optionBoneScale = EFalse;
   _boneId = 0;
   _frameTick = 0;
}

//============================================================
// <T>析构资源3D跟踪。</T>
//============================================================
FRs3dTrack::~FRs3dTrack(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dTrack::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取骨骼信息
   _optionBoneScale = pInput->ReadBool();
   _boneId = pInput->ReadInt8();
   _frameTick = pInput->ReadInt16();
   // 读取转换矩阵
   _matrix.Unserialize(pInput);
   _matrixInvert.Assign(_matrix);
   _matrixInvert.Invert();
   //............................................................
   // 读取动画帧列表
   TInt frameCount = pInput->ReadInt16();
   for(TInt n = 0; n < frameCount; n++){
      FRs3dFrame* pFrame = FRs3dFrame::InstanceCreate();
      pFrame->SetIndex(n);
      pFrame->Unserialize(pInput);
      _frames.Push(pFrame);
   }
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dTrack::CalculateFrameInfo(SRs3dFrameInfo& info, TTimeTick tick){
   TInt frameCount = _frames.Count();
   if(frameCount == 0){
      return EFailure;
   }
   // 去掉负数
   if(tick < 0){
      tick = -tick;
   }
   // 计算间隔
   TInt span = (TInt)((TFloat)(TInt)tick * info.playRate);
   TInt index = (span / _frameTick) % frameCount;
   // 获得当前帧和下一帧
   FRs3dFrame* pCurrentFrame = _frames.Get(index);
   FRs3dFrame* pNextFrame = NULL;
   if(index < frameCount -1){
      pNextFrame = _frames.Get(index + 1);
   }else{
      pNextFrame = _frames.Get(0);
   }
   // 设置结果
   info.tick = tick;
   info.rate = (TFloat)(span % _frameTick) / (TFloat)_frameTick;
   info.currentPtr = pCurrentFrame;
   info.nextPtr = pNextFrame;
   return true;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dTrack::Clear(){
   _frames.Clear();
}

MO_NAMESPACE_END
