#include "MoE3Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D骨头。</T>
//============================================================
FBone3d::FBone3d(){
   MO_CLEAR(_pAnimation);
   _modelId = 0;
   _boneId = 0;
   MO_CLEAR(_pBoneResource);
   MO_CLEAR(_pTrackResource);
}

//============================================================
// <T>析构实体3D骨头。</T>
//============================================================
FBone3d::~FBone3d(){
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FBone3d::LoadResource(FRs3dBone* pResource){
   MO_CHECK(pResource, return ENull);
   _boneId = pResource->Id();
   _pBoneResource = pResource;
   _pTrackResource = pResource->Track();
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @param tick 时刻
// @return 处理结果
//============================================================
TResult FBone3d::Update(TTimeTick tick){
   MO_ERROR_CHECK(_pTrackResource, return EFailure,
         "Bone track is null. (tick=%d, track=0x%08X)", tick, _pTrackResource);
   // 计算帧信息
   SRs3dFrameInfo info;
   _pTrackResource->CalculateFrameInfo(info, tick);
   info.Update();
   // 计算矩阵
   _matrix.Assign(_pTrackResource->MatrixInvert());
   _matrix.Append(info.matrix);
   return ESuccess;
}

MO_NAMESPACE_END
