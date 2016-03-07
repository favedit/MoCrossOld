#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dModel, FResource3d);

//============================================================
// <T>构造资源3D模型。</T>
//============================================================
FRs3dModel::FRs3dModel(){
   _pSkeleton = MO_CREATE(FRs3dSkeleton);
   _pAnimation = MO_CREATE(FRs3dAnimation);
   _pAnimation->SetModel(this);
}

//============================================================
// <T>析构资源3D模型。</T>
//============================================================
FRs3dModel::~FRs3dModel(){
   MO_DELETE(_pSkeleton);
   MO_DELETE(_pAnimation);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dModel::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取父信息
   FResource3d::Unserialize(pInput);
   // 读取几何体集合
   TInt geometryCount = pInput->ReadInt16();
   for(TInt n = 0; n < geometryCount; n++){
      FRs3dGeometry* pGeometry = MO_CREATE(FRs3dGeometry);
      pGeometry->Unserialize(pInput);
      _geometrys.Push(pGeometry);
   }
   // 读取骨骼
   _pSkeleton->Unserialize(pInput);
   // 读取动画
   _pAnimation->Unserialize(pInput);
   // 关联骨头和跟踪
   GRs3dTrackPtrs& tracks = _pAnimation->Tracks();
   TInt trackCount = tracks.Count();
   for(TInt n = 0; n < trackCount; n++){
      FRs3dTrack* pTrack = tracks.Get(n);
      TInt boneId = pTrack->BoneId();
      FRs3dBone* pBone = _pSkeleton->Find(boneId);
      pBone->SetTrack(pTrack);
   }
   MO_DEBUG(TC("Unserialize model success. (code=%d, geometry_count=%d, track_count=%d)"),
         _code, geometryCount, trackCount);
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FRs3dModel::OnOpen(){
   TResult result = FResource3d::OnOpen();
   // 打开所有几何体
   TInt count = _geometrys.Count();
   for(TInt n = 0; n < count; n++){
      FRs3dGeometry* pGeometry = _geometrys.Get(n);
      //pGeometry->Open();
   }
   return result;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FRs3dModel::OnClose(){
   TResult result = FResource3d::OnClose();
   // 关闭所有几何体
   TInt count = _geometrys.Count();
   for(TInt n = 0; n < count; n++){
      FRs3dGeometry* pGeometry = _geometrys.Get(n);
      //pGeometry->Close();
   }
   return result;
}

MO_NAMESPACE_END
