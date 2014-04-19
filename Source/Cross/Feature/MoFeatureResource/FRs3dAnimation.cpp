#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dAnimation, FInstance);

//============================================================
// <T>构造资源3D动画。</T>
//============================================================
FRs3dAnimation::FRs3dAnimation(){
   MO_CLEAR(_pModel);
   _frameTotal = 0;
   _frameTick = 0;
   _frameTotal = 0;
}

//============================================================
// <T>析构资源3D动画。</T>
//============================================================
FRs3dAnimation::~FRs3dAnimation(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dAnimation::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性内容
   _frameCount = pInput->ReadInt16();
   _frameTick = pInput->ReadInt16();
   _frameTotal = pInput->ReadInt32();
   // 读取跟踪列表
   TInt trackCount = pInput->ReadInt16();
   for(TInt n = 0; n < trackCount; n++){
      FRs3dTrack* pTrack = FRs3dTrack::InstanceCreate();
      pTrack->SetAnimation(this);
      pTrack->Unserialize(pInput);
      _tracks.Push(pTrack);
   }
   // 读取动画列表
   TInt movieCount = pInput->ReadInt16();
   for(TInt n = 0; n < movieCount; n++){
      FRs3dMovie* pMovie = FRs3dMovie::InstanceCreate();
      pMovie->Unserialize(pInput);
      _movies.Push(pMovie);
   }
   return ESuccess;
}

//============================================================
// <T>清空跟踪集合。</T>
//============================================================
void FRs3dAnimation::TrackClear(){
   _tracks.Clear();
}

//============================================================
// <T>清空动画集合。</T>
//============================================================
void FRs3dAnimation::MovieClear(){
   _movies.Clear();
}

MO_NAMESPACE_END
